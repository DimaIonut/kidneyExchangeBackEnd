package com.kidneyExchange.algorithmSteps;

import com.kidneyExchange.Entity.FinalCycle;
import com.kidneyExchange.Entity.FinalPair;
import com.kidneyExchange.Entity.PairPatientDonor;
import com.kidneyExchange.Entity.ValidatedCycle;
import com.kidneyExchange.repository.FinalCycleRepository;
import com.kidneyExchange.repository.PairPatientDonorRepository;
import com.kidneyExchange.repository.ValidatedCycleRepository;
import com.kidneyExchange.utilities.AlgorithmUtilities;
import java.util.ArrayList;
import java.util.List;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResolveLpProblem {

  @Autowired
  CreateMatrixAndVectors createMatrixAndVectors;

  @Autowired
  ValidatedCycleRepository validatedCycleRepository;

  @Autowired
  AlgorithmUtilities algorithmUtilities;

  @Autowired
  FinalCycleRepository finalCycleRepository;

  @Autowired
  PairPatientDonorRepository pairPatientDonorRepository;

  public void createAndResolveLp() {

    try {
      int Ncol = createMatrixAndVectors.getC().length, j;

      LpSolve solver = LpSolve.makeLp(0, Ncol);

      int[] colno = new int[Ncol];
      double[] row = new double[Ncol];

      if (solver.getLp() != 0) {

        for (int t = 0; t < createMatrixAndVectors.getC().length; t++) {

          solver.setColName(t + 1, String.format("x" + (t + 1)));

          solver.setBinary(t + 1, true);
        }

        solver.setAddRowmode(true);

        for (int i = 0; i < createMatrixAndVectors.A.length; i++) {
          for (j = 0; j < createMatrixAndVectors.A[0].length; j++) {
            colno[j] = j + 1;
            row[j] = createMatrixAndVectors.A[i][j];
          }
          solver.addConstraintex(j, row, colno, LpSolve.LE, createMatrixAndVectors.b[i][0]);
        }

        solver.setAddRowmode(false);

        for (j = 0; j < createMatrixAndVectors.c.length; j++) {

          colno[j] = j + 1;
          row[j] = createMatrixAndVectors.c[j][0];
        }

        solver.setObjFnex(j, row, colno);

        solver.setMaxim();

        solver.setVerbose(LpSolve.IMPORTANT);

        solver.writeLp("model.lp");

        if (solver.solve() == LpSolve.OPTIMAL) {

          System.out.println("Objective value: " + solver.getObjective());

          solver.getVariables(row);
          for (j = 0; j < Ncol; j++) {
//            System.out.println(solver.getColName(j + 1) + ": " + row[j]);
            if (row[j] == 1.0) {
              ValidatedCycle validatedCycle = validatedCycleRepository.findByCycleId(j + 1)
                  .orElse(null);
              if (algorithmUtilities.getCycleLength(validatedCycle) == 2) {
                if (algorithmUtilities
                    .checkFinalCycleExist(new FinalCycle(validatedCycle.getFirstPatientId(),
                        validatedCycle.getFirstDonorId(), validatedCycle.getSecondPatientId(),
                        validatedCycle.getSecondDonorId()))) {
                  finalCycleRepository.save(new FinalCycle(validatedCycle.getFirstPatientId(),
                      validatedCycle.getFirstDonorId(), validatedCycle.getSecondPatientId(),
                      validatedCycle.getSecondDonorId()));
                }
              } else if (algorithmUtilities.getCycleLength(validatedCycle) == 3) {
                if (algorithmUtilities
                    .checkFinalCycleExist(new FinalCycle(validatedCycle.getFirstPatientId(),
                        validatedCycle.getFirstDonorId(), validatedCycle.getSecondPatientId(),
                        validatedCycle.getSecondDonorId(), validatedCycle.getThirdPatientId(),
                        validatedCycle.getThirdDonorId(), validatedCycle.getTwoCycle()))) {
                  finalCycleRepository.save(new FinalCycle(validatedCycle.getFirstPatientId(),
                      validatedCycle.getFirstDonorId(), validatedCycle.getSecondPatientId(),
                      validatedCycle.getSecondDonorId(), validatedCycle.getThirdPatientId(),
                      validatedCycle.getThirdDonorId(), validatedCycle.getTwoCycle()));
                }
              }
            }
          }
        }
      }

      // delete the problem and free memory
      solver.deleteLp();
    } catch (LpSolveException e) {
      e.printStackTrace();
    }

  }

  private List<FinalPair> createFinalPairList() {

    List<FinalPair> finalPairs = new ArrayList<>();

    List<PairPatientDonor> pairPatientDonorList = pairPatientDonorRepository
        .findByTheyAreCompatible(true);

    List<FinalCycle> finalCycles = finalCycleRepository.findAll();

    pairPatientDonorList.forEach(pairPatientDonor ->
        finalPairs.add(new FinalPair(
            algorithmUtilities.getPatientCompleteNameById(pairPatientDonor.getPatientId()),
            pairPatientDonor.getBloodTypePatient(),
            algorithmUtilities.getDonorCompleteNameById(pairPatientDonor.getDonorId()),
            pairPatientDonor.getBloodTypeDonor())));

    finalCycles.forEach(finalCycle -> {

      if (algorithmUtilities.getFinalCycleLength(finalCycle) == 2) {

        finalPairs.add(new FinalPair(
            algorithmUtilities.getPatientCompleteNameById(finalCycle.getFirstPatientId()),
            algorithmUtilities.getPatientBloodTypeById(finalCycle.getFirstPatientId()),
            algorithmUtilities.getDonorCompleteNameById(finalCycle.getFirstDonorId()),
            algorithmUtilities.getDonorBloodTypeById(finalCycle.getFirstDonorId()),

            algorithmUtilities.getPatientCompleteNameById(finalCycle.getSecondPatientId()),
            algorithmUtilities.getPatientBloodTypeById(finalCycle.getSecondPatientId()),
            algorithmUtilities.getDonorCompleteNameById(finalCycle.getSecondDonorId()),
            algorithmUtilities.getDonorBloodTypeById(finalCycle.getSecondDonorId())
        ));
      } else if (algorithmUtilities.getFinalCycleLength(finalCycle) == 3) {

        finalPairs.add(new FinalPair(
            algorithmUtilities.getPatientCompleteNameById(finalCycle.getFirstPatientId()),
            algorithmUtilities.getPatientBloodTypeById(finalCycle.getFirstPatientId()),
            algorithmUtilities.getDonorCompleteNameById(finalCycle.getFirstDonorId()),
            algorithmUtilities.getDonorBloodTypeById(finalCycle.getFirstDonorId()),

            algorithmUtilities.getPatientCompleteNameById(finalCycle.getSecondPatientId()),
            algorithmUtilities.getPatientBloodTypeById(finalCycle.getSecondPatientId()),
            algorithmUtilities.getDonorCompleteNameById(finalCycle.getSecondDonorId()),
            algorithmUtilities.getDonorBloodTypeById(finalCycle.getSecondDonorId()),

            algorithmUtilities.getPatientCompleteNameById(finalCycle.getThirdPatientId()),
            algorithmUtilities.getPatientBloodTypeById(finalCycle.getThirdPatientId()),
            algorithmUtilities.getDonorCompleteNameById(finalCycle.getThirdDonorId()),
            algorithmUtilities.getDonorBloodTypeById(finalCycle.getThirdDonorId())
        ));
      }
    });

    return finalPairs;
  }

  public List<FinalPair> start() {

    createAndResolveLp();

    return createFinalPairList();
  }
}
