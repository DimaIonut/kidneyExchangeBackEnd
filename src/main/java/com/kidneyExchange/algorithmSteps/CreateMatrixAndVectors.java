package com.kidneyExchange.algorithmSteps;

import com.kidneyExchange.Entity.PairPatientDonor;
import com.kidneyExchange.Entity.ValidatedCycle;
import com.kidneyExchange.Entity.AltruisticDonor;
import com.kidneyExchange.repository.AltruisticDonorRepository;
import com.kidneyExchange.repository.PairPatientDonorRepository;
import com.kidneyExchange.repository.ValidatedCycleRepository;
import com.kidneyExchange.utilities.AlgorithmUtilities;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class CreateMatrixAndVectors {

  @Autowired
  ValidatedCycleRepository validatedCycleRepository;

  @Autowired
  PairPatientDonorRepository pairPatientDonorRepository;

  @Autowired
  AltruisticDonorRepository altruisticDonorRepository;

  @Autowired
  AlgorithmUtilities algorithmUtilities;

  public int A[][];

  public int b[][];

  public int c[][];

  private void createMatrix() {

    List<ValidatedCycle> validatedCycles = validatedCycleRepository.findAll();

    int m = validatedCycles.size();

    List<PairPatientDonor> donorList = pairPatientDonorRepository
        .findByTheyAreCompatible(false);

    List<AltruisticDonor> altruisticDonors = altruisticDonorRepository.findAll();

    int n = donorList.size() - altruisticDonors.size();

    int k = altruisticDonors.size();

    A = new int[n + 2 * k + 1][m + k + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (algorithmUtilities
            .cycleContainsDonor(validatedCycles.get(j - 1), donorList.get(i - 1))) {
          A[i][j] = 1;
        }
      }
    }

    for (int i = 1; i <= k; i++) {
      for (int j = 1; j <= m; j++) {
        if (algorithmUtilities
            .cycleContainsDonor(validatedCycles.get(j - 1), donorList.get(n + i - 1))) {
          A[n + 2 * i - 1][j] = 1;
        }
      }

      for (int j = 1; j <= k; j++) {
        if (i == j) {
          A[n + 2 * i - 1][m + j] = 1;
        }
      }

      for (int j = 1; j <= m; j++) {
        if (algorithmUtilities
            .cycleContainsDonor(validatedCycles.get(j - 1), donorList.get(n + i - 1))) {
          A[n + 2 * i][j] = -1;
        }
      }

      for (int j = 1; j <= k; j++) {
        if (i == j) {
          A[n + 2 * i][m + j] = -1;
        }
      }

    }

    A = algorithmUtilities.removeFirstRowAndColumn(A);
//    displays
    System.out.println("Afisare A ----------------------------------------");
    for (int i = 0; i < (n + 2 * k); i++) {
      for (int j = 0; j < (m + k); j++) {
        System.out.print(A[i][j]);
      }
      System.out.print("\n");
    }
  }

  private void createVectors() {

    List<ValidatedCycle> validatedCycles = validatedCycleRepository.findAll();

    int m = validatedCycles.size();

    List<PairPatientDonor> donorList = pairPatientDonorRepository
        .findByTheyAreCompatible(false);

    List<AltruisticDonor> altruisticDonors = altruisticDonorRepository.findAll();

    int n = donorList.size() - altruisticDonors.size();

    int k = altruisticDonors.size();

    b = new int[n + 2 * k+1][1];

    for (int i = 1; i <= n; i++) {
      b[i][0] = 1;
    }

    for (int i = 1; i <= k; i++) {
      b[n + 2 * i - 1][0] = 1;
      b[n + 2 * i][0] = -1;
    }

    b = algorithmUtilities.removeFirstElement(b);
    // displays b
    System.out.println("Afisare b ------------------------------------------");
    for (int i = 0; i < (n + 2 * k); i++) {
      System.out.println(b[i][0]);
    }

    c = new int[m + k][1];

    for (int j = 0; j < m; j++) {
      c[j][0] = algorithmUtilities.getCycleLength(validatedCycles.get(j));
    }

    // displays c
    System.out.println("Afisare c ----------------------------------");
    for (int i = 0; i < (m + k); i++) {
      System.out.println(c[i][0]);
    }

  }

  public void start() {

    createMatrix();
    createVectors();
  }
}
