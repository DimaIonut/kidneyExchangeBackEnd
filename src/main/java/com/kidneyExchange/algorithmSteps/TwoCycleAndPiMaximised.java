package com.kidneyExchange.algorithmSteps;

import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.Entity.PairPatientDonor;
import com.kidneyExchange.Entity.Patient;
import com.kidneyExchange.Entity.ValidatedCycle;
import com.kidneyExchange.repository.DonorRepository;
import com.kidneyExchange.repository.PairPatientDonorRepository;
import com.kidneyExchange.repository.PatientRepository;
import com.kidneyExchange.repository.ValidatedCycleRepository;
import com.kidneyExchange.utilities.AlgorithmUtilities;
import com.kidneyExchange.utilities.CheckCompatibility;
import java.util.Date;
import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwoCycleAndPiMaximised {

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  DonorRepository donorRepository;

  @Autowired
  AlgorithmUtilities algorithmUtilities;

  @Autowired
  PairPatientDonorRepository pairPatientDonorRepository;

  @Autowired
  CheckCompatibility checkCompatibility;

  @Autowired
  ValidatedCycleRepository validatedCycleRepository;

  private void createPairs() {

    List<Patient> patients = patientRepository.findByHasPairOrderByArrivingTimeAsc(false);

    List<Donor> donors = donorRepository.findByHasPairOrderByArrivingTimeAsc(false);

    patients.forEach(patient -> {
      if (patient.getBloodType().equals("0")) {
        Donor donorZero = algorithmUtilities.getDonorsBy(donors, "0").get(0);
        if (donorZero != null) {
          algorithmUtilities.insertPairsAndUpdateStatus(patient, donorZero);
          donors.removeIf(donor1 -> donor1.getId() == donorZero.getId());
        }
      }
    });

    patients.forEach(patient -> {
      if (patient.getBloodType().equals("A")) {
        Donor donorA = algorithmUtilities.getDonorsBy(donors, "0", "A").get(0);
        if (donorA != null) {
          algorithmUtilities.insertPairsAndUpdateStatus(patient, donorA);
          donors.removeIf(donor1 -> donor1.getId() == donorA.getId());
        }
      }
    });

    patients.forEach(patient -> {
      if (patient.getBloodType().equals("B")) {
        if (algorithmUtilities.getDonorsBy(donors, "0", "B") != null) {
          Donor donorB = algorithmUtilities.getDonorsBy(donors, "0", "B").get(0);
          if (donorB != null) {
            algorithmUtilities.insertPairsAndUpdateStatus(patient, donorB);
            donors.removeIf(donor1 -> donor1.getId() == donorB.getId());
          }
        }
      }
    });

    patients.forEach(patient -> {
      if (patient.getBloodType().equals("AB")) {
        if (!donors.isEmpty()) {
          Donor donorAB = donors.get(0);
          if (donorAB != null) {
            algorithmUtilities.insertPairsAndUpdateStatus(patient, donorAB);
            donors.removeIf(donor1 -> donor1.getId() == donorAB.getId());
          }
        }
      }
    });

    List<Patient> reloadPatientList = patientRepository.findByHasPairOrderByArrivingTimeAsc(false);

    List<Donor> reloadDonorList = donorRepository.findByHasPairOrderByArrivingTimeAsc(false);

    while (!reloadDonorList.isEmpty() && !reloadPatientList.isEmpty()) {
      if (reloadDonorList.size() >= reloadPatientList.size()) {
        reloadPatientList.forEach(patient -> {
          Donor donor = reloadDonorList.get(0);
          algorithmUtilities.insertPairsAndUpdateStatus(patient, donor);
          reloadDonorList.removeIf(donor1 -> donor1.getId() == donor.getId());
        });
        reloadPatientList.clear();
      } else {
        reloadDonorList.forEach(donor -> {
          Patient patient = reloadPatientList.get(0);
          algorithmUtilities.insertPairsAndUpdateStatus(patient, donor);
          reloadPatientList.removeIf(patient1 -> patient1.getId() == patient.getId());
        });
        reloadDonorList.clear();
      }
    }
  }

//  public static List<Donor> altruisticDonors = donorRepository.findByHasPairOrderByArrivingTimeAsc(false);

  private void addFictivePatientsForAltruisticDonors() {

    List<Donor> donors = donorRepository.findByHasPairOrderByArrivingTimeAsc(false);

    Patient patient = new Patient("pacient", "fictiv", null, null, "AB", new Date(), true);

    donors.forEach(donor -> {
      patient.setId(algorithmUtilities.getLastIdByPatient() + 1);
      algorithmUtilities.insertPairOfAltruisticDonor(patient, donor);
    });
  }

  private void createDirectedGraphAndCycles() {

    List<PairPatientDonor> pairPatientDonorList = pairPatientDonorRepository
        .findByTheyAreCompatible(false);

    SimpleDirectedWeightedGraph<PairPatientDonor, DefaultWeightedEdge> directedGraph = new SimpleDirectedWeightedGraph<>(
        DefaultWeightedEdge.class);

    pairPatientDonorList.forEach(vertex -> directedGraph.addVertex(vertex));

    DefaultWeightedEdge edge;
    // create Edges and adding Weight per every edge
    for (int i = 0; i < pairPatientDonorList.size(); i++) {
      for (int j = 0; j < pairPatientDonorList.size(); j++) {
        if (i != j && checkCompatibility
            .areCompatible(pairPatientDonorList.get(i).getBloodTypeDonor(),
                pairPatientDonorList.get(j).getBloodTypePatient())) {
          edge = directedGraph.addEdge(pairPatientDonorList.get(i), pairPatientDonorList.get(j));
          directedGraph.setEdgeWeight(edge, Math.random());
          System.out.println(
              directedGraph.getEdge(pairPatientDonorList.get(i), pairPatientDonorList.get(j)));
        }
      }
    }
    // identified cycles having 2 or 3 size
    for (int i = 0; i < pairPatientDonorList.size(); i++) {
      for (int j = 0; j < pairPatientDonorList.size(); j++) {
        if (directedGraph.getEdge(pairPatientDonorList.get(i), pairPatientDonorList.get(j)) != null
            && directedGraph.getEdge(pairPatientDonorList.get(j), pairPatientDonorList.get(i))
            != null) {
          validatedCycleRepository.save(
              new ValidatedCycle(pairPatientDonorList.get(i).getPatientId(),
                  pairPatientDonorList.get(i).getDonorId(),
                  pairPatientDonorList.get(j).getPatientId(),
                  pairPatientDonorList.get(j).getDonorId()));
          for (int k = 0; k < pairPatientDonorList.size(); k++) {
            if (directedGraph.getEdge(pairPatientDonorList.get(i), pairPatientDonorList.get(j))
                != null
                && directedGraph.getEdge(pairPatientDonorList.get(j), pairPatientDonorList.get(k))
                != null
                && directedGraph.getEdge(pairPatientDonorList.get(k), pairPatientDonorList.get(i))
                != null) {
              validatedCycleRepository
                  .save(new ValidatedCycle(pairPatientDonorList.get(i).getPatientId(),
                      pairPatientDonorList.get(i).getDonorId(),
                      pairPatientDonorList.get(j).getPatientId(),
                      pairPatientDonorList.get(j).getDonorId(),
                      pairPatientDonorList.get(k).getPatientId(),
                      pairPatientDonorList.get(k).getDonorId(), true));
            }
          }
        } else {
          for (int k = 0; k < pairPatientDonorList.size(); k++) {
            if (directedGraph.getEdge(pairPatientDonorList.get(i), pairPatientDonorList.get(j))
                != null
                && directedGraph.getEdge(pairPatientDonorList.get(j), pairPatientDonorList.get(k))
                != null
                && directedGraph.getEdge(pairPatientDonorList.get(k), pairPatientDonorList.get(i))
                != null) {
              validatedCycleRepository
                  .save(new ValidatedCycle(pairPatientDonorList.get(i).getPatientId(),
                      pairPatientDonorList.get(i).getDonorId(),
                      pairPatientDonorList.get(j).getPatientId(),
                      pairPatientDonorList.get(j).getDonorId(),
                      pairPatientDonorList.get(k).getPatientId(),
                      pairPatientDonorList.get(k).getDonorId(), false));
            }
          }
        }
      }
    }
  }

  public void start() {

    createPairs();
    addFictivePatientsForAltruisticDonors();
    createDirectedGraphAndCycles();
  }
}
