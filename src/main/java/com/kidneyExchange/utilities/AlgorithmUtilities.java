package com.kidneyExchange.utilities;

import com.kidneyExchange.Entity.AltruisticDonor;
import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.Entity.PairPatientDonor;
import com.kidneyExchange.Entity.Patient;
import com.kidneyExchange.Entity.ValidatedCycle;
import com.kidneyExchange.repository.AltruisticDonorRepository;
import com.kidneyExchange.repository.DonorRepository;
import com.kidneyExchange.repository.PairPatientDonorRepository;
import com.kidneyExchange.repository.PatientRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmUtilities {

  @Autowired
  PairPatientDonorRepository pairPatientDonorRepository;

  @Autowired
  CheckCompatibility checkCompatibility;

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  DonorRepository donorRepository;

  @Autowired
  AltruisticDonorRepository altruisticDonorRepository;

  public void insertPairsAndUpdateStatus(Patient patient, Donor donor) {

    pairPatientDonorRepository.save(
        new PairPatientDonor(patient.getId(), patient.getBloodType(), donor.getId(),
            donor.getBloodType(), checkCompatibility.areCompatible(patient, donor)));

    Patient patientUpdated = patientRepository.findById(patient.getId()).get();
    patientUpdated.setHasPair(true);
    patientRepository.save(patientUpdated);

    Donor donorUpdated = donorRepository.findById(donor.getId()).get();
    donorUpdated.setHasPair(true);
    donorRepository.save(donorUpdated);
  }

  public List<Donor> getDonorsBy(List<Donor> donors, String bloodType) {

    List<Donor> myDonorList = new ArrayList<>();

    donors.forEach(donor -> {
      if (donor.getBloodType().equals(bloodType)) {
        myDonorList.add(donor);
      }
    });
    if (!myDonorList.isEmpty()) {
      return myDonorList;
    } else {
      return null;
    }
  }

  public List<Donor> getDonorsBy(List<Donor> donors, String bloodType1, String bloodType2) {

    List<Donor> myDonorList = new ArrayList<>();

    donors.forEach(donor -> {
      if (donor.getBloodType().equals(bloodType1) || donor.getBloodType().equals(bloodType2)) {
        myDonorList.add(donor);
      }
    });
    if (!myDonorList.isEmpty()) {
      return myDonorList;
    } else {
      return null;
    }
  }

  public Integer getLastIdByPatient() {

    List<Patient> patients = patientRepository.findByOrderByIdAsc();

    return patients.get(patients.size() - 1).getId();
  }

  public void insertPairOfAltruisticDonor(Patient patient, Donor donor) {

    patientRepository.save(patient);

    altruisticDonorRepository.save(
        new AltruisticDonor(donor.getId(), donor.getFirstName(), donor.getLastName(),
            donor.getBloodType()));

    pairPatientDonorRepository
        .save(new PairPatientDonor(patient.getId(), patient.getBloodType(), donor.getId(),
            donor.getBloodType(), false));

    Donor donorUpdated = donorRepository.findById(donor.getId()).get();
    donorUpdated.setHasPair(true);
    donorRepository.save(donorUpdated);
  }

  public Boolean cycleContainsDonor(ValidatedCycle validatedCycle, PairPatientDonor donor) {

    if (validatedCycle.getFirstDonorId() == donor.getDonorId() ||
        validatedCycle.getSecondDonorId() == donor.getDonorId() ||
        validatedCycle.getThirdDonorId() == donor.getDonorId()) {
      return true;
    }
    return false;
  }

  public int getCycleLength(ValidatedCycle validatedCycle) {

    if (validatedCycle.getTwoCycle() && !validatedCycle.getThreeCycle()) {
      return 2;
    }
    return 3;
  }

  public int[][] removeFirstRowAndColumn(int[][] matrix) {

    int[][] localMatrix = new int[matrix.length-1][matrix[0].length-1];
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; j++) {
        localMatrix[i-1][j-1] = matrix[i][j];
      }
    }
    return localMatrix;
  }

  public int [][] removeFirstElement(int[][] vector) {

    int [][] localVector = new int[vector.length-1][vector[0].length];
    for(int i = 1; i < vector.length; i++) {
      localVector[i-1][0] = vector[i][0];
    }

    return localVector;
  }
}
