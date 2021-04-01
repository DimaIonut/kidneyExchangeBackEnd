package com.kidneyExchange.controller;

import com.kidneyExchange.model.RequestPatientRegistration;
import com.kidneyExchange.model.RequestTogetherRegistration;
import com.kidneyExchange.service.PatientRegistrationService;
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
  private PatientRegistrationService patientRegistrationService = new PatientRegistrationService();

  @RequestMapping(value = "/patientTogetherRegistration", method = RequestMethod.POST)
  public ResponseEntity<?> togetherRegistration(
      @RequestBody RequestTogetherRegistration requestTogetherRegistration)
      throws Exception {

      return patientRegistrationService.togetherWithDonorRegistration(requestTogetherRegistration);
  }

  @RequestMapping(value = "/patientRegistration", method = RequestMethod.POST)
  public ResponseEntity<?> patientRegistration(
      @RequestBody RequestPatientRegistration requestPatientRegistration)
      throws Exception {

      return patientRegistrationService.patientRegistration(requestPatientRegistration);
  }
}
