package com.kidneyExchange.service;

import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.Entity.PairPatientDonor;
import com.kidneyExchange.Entity.Patient;
import com.kidneyExchange.controller.PatientRegistationController;
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
import org.springframework.stereotype.Service;

@Service
public class PatientRegistrationService {


  private PatientRepository patientRepository;

  @Autowired
  private DonorRepository donorRepository;

  @Autowired
  private PairPatientDonorRepository pairPatientDonorRepository;

  @Autowired
  private CheckCompatibility checkCompatibility;

  private static Logger logger = LogManager.getLogger(PatientRegistationController.class);

  @Autowired
  public PatientRegistrationService(PatientRepository patientRepository,
      DonorRepository donorRepository,
      PairPatientDonorRepository pairPatientDonorRepository,
      CheckCompatibility checkCompatibility) {
    this.patientRepository = patientRepository;
    this.donorRepository = donorRepository;
    this.pairPatientDonorRepository = pairPatientDonorRepository;
    this.checkCompatibility = checkCompatibility;
  }

  public PatientRegistrationService() {
  }

  public ResponseEntity<?> togetherWithDonorRegistration(
      RequestTogetherRegistration requestTogetherRegistration) {

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

  public ResponseEntity<?> patientRegistration(RequestPatientRegistration requestPatientRegistration) {

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
