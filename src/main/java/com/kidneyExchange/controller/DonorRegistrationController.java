package com.kidneyExchange.controller;

import com.kidneyExchange.model.RequestDonorRegistration;
import com.kidneyExchange.service.DonorRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DonorRegistrationController {

  @Autowired
  private DonorRegistrationService donorRegistrationService;

  @RequestMapping(value = "/donorRegistration", method = RequestMethod.POST)
  public ResponseEntity<?> donorRegistration(
      @RequestBody RequestDonorRegistration requestDonorRegistration)
      throws Exception {

    return donorRegistrationService.registrateDonor(requestDonorRegistration);
  }
}
