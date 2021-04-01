package com.kidneyExchange.service;

import com.kidneyExchange.Entity.FinalPair;
import com.kidneyExchange.algorithmSteps.CreateMatrixAndVectors;
import com.kidneyExchange.algorithmSteps.CreatePairsAndDirectedGraphAndCycles;
import com.kidneyExchange.algorithmSteps.ResolveLpProblem;
import com.kidneyExchange.controller.StartAlgorithmController;
import com.kidneyExchange.repository.DonorRepository;
import com.kidneyExchange.repository.PatientRepository;
import com.kidneyExchange.utilities.CheckCompatibility;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartAlgorithmService {

  private CheckCompatibility checkCompatibility;

  private PatientRepository patientRepository;

  private DonorRepository donorRepository;

  private CreateMatrixAndVectors createMatrixAndVectors;

  private CreatePairsAndDirectedGraphAndCycles createPairsAndDirectedGraphAndCycles;

  private ResolveLpProblem resolveLpProblem;

  private static Logger logger = LogManager.getLogger(StartAlgorithmController.class);

  @Autowired
  public StartAlgorithmService(CheckCompatibility checkCompatibility,
      PatientRepository patientRepository,
      DonorRepository donorRepository,
      CreateMatrixAndVectors createMatrixAndVectors,
      CreatePairsAndDirectedGraphAndCycles createPairsAndDirectedGraphAndCycles,
      ResolveLpProblem resolveLpProblem) {
    this.checkCompatibility = checkCompatibility;
    this.patientRepository = patientRepository;
    this.donorRepository = donorRepository;
    this.createMatrixAndVectors = createMatrixAndVectors;
    this.createPairsAndDirectedGraphAndCycles = createPairsAndDirectedGraphAndCycles;
    this.resolveLpProblem = resolveLpProblem;
  }

  public List<FinalPair> startAlgorithm() {

    createPairsAndDirectedGraphAndCycles.start();

    createMatrixAndVectors.start();

    return resolveLpProblem.start();
  }


}
