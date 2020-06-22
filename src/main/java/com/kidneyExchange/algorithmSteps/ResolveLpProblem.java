package com.kidneyExchange.algorithmSteps;

import lpsolve.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResolveLpProblem {

  @Autowired
  CreateMatrixAndVectors createMatrixAndVectors;

  public void createAndResolveLp() {

    try {
      int Ncol = createMatrixAndVectors.getC().length, j;

      LpSolve solver = LpSolve.makeLp(0, Ncol);

      int[] colno = new int[Ncol];
      double[] row = new double[Ncol];

      if (solver.getLp() != 0) {

        for (int t = 0; t < createMatrixAndVectors.getC().length; t++) {

          solver.setColName(t + 1, String.format("x" + (t + 1)));

//          solver.setBinary(t+1, true);
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
            System.out.println(solver.getColName(j + 1) + ": " + row[j]);
          }
        }
      }

      // delete the problem and free memory
      solver.deleteLp();
    } catch (LpSolveException e) {
      e.printStackTrace();
    }

  }

  public void start() {

    createAndResolveLp();
  }
}
