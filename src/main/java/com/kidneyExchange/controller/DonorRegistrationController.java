package com.kidneyExchange.controller;

import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.model.RequestDonorRegistration;
import com.kidneyExchange.model.ResponseDonorRegistration;
import com.kidneyExchange.repository.DonorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@CrossOrigin
public class DonorRegistrationController {

  @Autowired
  DonorRepository donorRepository;

  private static Logger logger = LogManager.getLogger(DonorRegistrationController.class);

  @RequestMapping(value = "/donorRegistration", method = RequestMethod.POST)
  public ResponseEntity<?> donorRegistration(
      @RequestBody RequestDonorRegistration requestDonorRegistration)
      throws Exception {

    logger.info("Donor Registration " + requestDonorRegistration.getFirstName() + " "
        + requestDonorRegistration.getLastName() + "was insert in database...");

    Donor donor = new Donor(requestDonorRegistration.getFirstName(),
        requestDonorRegistration.getLastName(), requestDonorRegistration.getEmail(),
        requestDonorRegistration.getCity(), requestDonorRegistration.getBloodType(), new Date(), false);

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
