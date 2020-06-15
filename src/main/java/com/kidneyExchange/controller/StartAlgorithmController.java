package com.kidneyExchange.controller;

import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.Entity.Patient;
import com.kidneyExchange.repository.DonorRepository;
import com.kidneyExchange.repository.PatientRepository;
import com.kidneyExchange.utilities.CheckCompatibility;
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

  private static Logger logger = LogManager.getLogger(StartAlgorithmController.class);

  @RequestMapping(value="/start_find_compatibility", method = RequestMethod.GET)
  public boolean test() {

    Donor donor = donorRepository.findByFirstName("Ionut").orElse(null);

    Patient patient = patientRepository.findByFirstName("George").orElse(null);

    boolean compatibility = checkCompatibility.areCompatible(patient, donor);
    logger.info(
        "Values of isCompatibility function having patient " + patient.getFirstName() + " and donor "
            + donor.getFirstName() + " : " + checkCompatibility.areCompatible(patient, donor));

    return compatibility;
  }
}
