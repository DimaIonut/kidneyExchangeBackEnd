package com.kidneyExchange.controller;

import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.Entity.PairPatientDonor;
import com.kidneyExchange.Entity.Patient;
import com.kidneyExchange.model.RequestPatientRegistration;
import com.kidneyExchange.model.RequestTogetherRegistration;
import com.kidneyExchange.model.ResponsePatientRegistration;
import com.kidneyExchange.model.ResponseTogetherRegistration;
import com.kidneyExchange.repository.DonorRepository;
import com.kidneyExchange.repository.PairPatientDonorRepository;
import com.kidneyExchange.repository.PatientRepository;
import com.kidneyExchange.utilities.CheckCompatibility;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PatientRegistationController {

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  DonorRepository donorRepository;

  @Autowired
  PairPatientDonorRepository pairPatientDonorRepository;

  @Autowired
  CheckCompatibility checkCompatibility;

  private static Logger logger = LogManager.getLogger(PatientRegistationController.class);

  @RequestMapping(value = "/patientTogetherRegistration", method = RequestMethod.POST)
  public ResponseEntity<?> togetherRegistration(
      @RequestBody RequestTogetherRegistration requestTogetherRegistration)
      throws Exception {

    logger.info("User ");

    Patient patient = new Patient(requestTogetherRegistration.getFirstNamePatient(),
        requestTogetherRegistration.getLastNamePatient(),
        requestTogetherRegistration.getEmailPatient(),
        requestTogetherRegistration.getCityPatient(),
        requestTogetherRegistration.getBloodTypePatient(), new Date(), true);

    Donor donor = new Donor(requestTogetherRegistration.getFirstNameDonor(),
        requestTogetherRegistration.getLastNameDonor(), requestTogetherRegistration.getEmailDonor(),
        requestTogetherRegistration.getCityDonor(), requestTogetherRegistration.getBloodTypeDonor(),
        new Date(), true);

    patientRepository.save(patient);

    donorRepository.save(donor);

    pairPatientDonorRepository.save(
        new PairPatientDonor(patient.getId(), patient.getBloodType(), donor.getId(),
            donor.getBloodType(), checkCompatibility.areCompatible(patient, donor)));

    if (patientRepository
        .findByFirstNameAndLastNameAndBloodType(requestTogetherRegistration.getFirstNamePatient(),
            requestTogetherRegistration.getLastNamePatient(),
            requestTogetherRegistration.getBloodTypePatient()).orElse(null) != null
        && donorRepository
        .findByFirstNameAndLastNameAndBloodType(requestTogetherRegistration.getFirstNameDonor(),
            requestTogetherRegistration.getLastNameDonor(),
            requestTogetherRegistration.getBloodTypeDonor()).orElse(null) != null
        && pairPatientDonorRepository.findByPatientIdAndDonorId(patient.getId(), donor.getId())
        .orElse(null) != null) {
      return ResponseEntity.ok(new ResponseTogetherRegistration(true));
    } else {
      return ResponseEntity.ok(new ResponseTogetherRegistration(false));
    }
  }

  @RequestMapping(value = "/patientRegistration", method = RequestMethod.POST)
  public ResponseEntity<?> patientRegistration(
      @RequestBody RequestPatientRegistration requestPatientRegistration)
      throws Exception {

    patientRepository.save(new Patient(requestPatientRegistration.getFirstName(),
        requestPatientRegistration.getLastName(), requestPatientRegistration.getEmail(),
        requestPatientRegistration.getCity(), requestPatientRegistration.getBloodType(),
        new Date(),false));

    if (patientRepository
        .findByFirstNameAndLastNameAndBloodType(requestPatientRegistration.getFirstName(),
            requestPatientRegistration.getLastName(), requestPatientRegistration.getBloodType())
        .orElse(null) != null) {
      return ResponseEntity.ok(new ResponsePatientRegistration(true));
    }
    else {
      return ResponseEntity.ok(new ResponsePatientRegistration(false));
    }
  }
}
