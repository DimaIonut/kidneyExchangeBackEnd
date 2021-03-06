package com.kidneyExchange.controller;

import com.kidneyExchange.Entity.FinalPair;
import com.kidneyExchange.algorithmSteps.CreateMatrixAndVectors;
import com.kidneyExchange.algorithmSteps.CreatePairsAndDirectedGraphAndCycles;
import com.kidneyExchange.algorithmSteps.ResolveLpProblem;
import com.kidneyExchange.repository.DonorRepository;
import com.kidneyExchange.repository.PatientRepository;
import com.kidneyExchange.utilities.CheckCompatibility;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StartAlgorithmController {

  @Autowired
  CheckCompatibility checkCompatibility;

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  DonorRepository donorRepository;

  @Autowired
  CreateMatrixAndVectors createMatrixAndVectors;

  @Autowired
  CreatePairsAndDirectedGraphAndCycles createPairsAndDirectedGraphAndCycles;

  @Autowired
  ResolveLpProblem resolveLpProblem;

  private static Logger logger = LogManager.getLogger(StartAlgorithmController.class);

  @RequestMapping(value = "/start_find_compatibility", method = RequestMethod.GET)
  public List<FinalPair> startAlgorithm() {

    createPairsAndDirectedGraphAndCycles.start();

    createMatrixAndVectors.start();

    return resolveLpProblem.start();
  }
}
