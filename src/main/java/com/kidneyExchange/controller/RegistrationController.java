package com.kidneyExchange.controller;


import com.kidneyExchange.model.RequestRegistration;
import com.kidneyExchange.service.RegistrationService;
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
public class RegistrationController {

  @Autowired
  private RegistrationService registrationService;

  private static Logger logger = LogManager.getLogger(RegistrationController.class);

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public ResponseEntity<?> creataNewUser(@RequestBody RequestRegistration registrationRequest)
      throws Exception {

    return registrationService.registrate(registrationRequest);
  }
}
