package com.kidneyExchange.service;

import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.controller.DonorRegistrationController;
import com.kidneyExchange.model.RequestDonorRegistration;
import com.kidneyExchange.model.ResponseDonorRegistration;
import com.kidneyExchange.repository.DonorRepository;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DonorRegistrationService {

  @Autowired
  private DonorRepository donorRepository;

  private static Logger logger = LogManager.getLogger(DonorRegistrationController.class);

  public ResponseEntity<?> registrateDonor(RequestDonorRegistration requestDonorRegistration) {

    logger.info("Donor Registration " + requestDonorRegistration.getFirstName() + " "
        + requestDonorRegistration.getLastName() + "was insert in database...");

    Donor donor = new Donor(requestDonorRegistration.getFirstName(),
        requestDonorRegistration.getLastName(), requestDonorRegistration.getEmail(),
        requestDonorRegistration.getCity(), requestDonorRegistration.getBloodType(), new Date(),
        false);

    donorRepository.save(donor);

    if (donorRepository
        .findByFirstNameAndLastNameAndBloodType(requestDonorRegistration.getFirstName(),
            requestDonorRegistration.getLastName(), requestDonorRegistration.getBloodType())
        .orElse(null) != null) {
      return ResponseEntity.ok(new ResponseDonorRegistration(true));
    } else {
      return ResponseEntity.ok(new ResponseDonorRegistration(false));
    }
  }
}
