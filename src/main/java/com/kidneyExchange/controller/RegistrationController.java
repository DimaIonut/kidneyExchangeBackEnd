package com.kidneyExchange.controller;


import com.kidneyExchange.Entity.User;
import com.kidneyExchange.model.RequestRegistration;
import com.kidneyExchange.model.ResponseRegistration;
import com.kidneyExchange.repository.DonorRepository;
import com.kidneyExchange.repository.PatientRepository;
import com.kidneyExchange.repository.UserRepository;
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
public class RegistrationController {

  @Autowired
  UserRepository userRepository;


  private static Logger logger = LogManager.getLogger(RegistrationController.class);

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public ResponseEntity<?> creataNewUser(@RequestBody RequestRegistration registrationRequest)
      throws Exception {

    logger.info(
        "User " + registrationRequest.getUsername() + " registration (at " + new Date() + ")");

    userRepository
        .save(new User(registrationRequest.getUsername(), registrationRequest.getEmail(),
            registrationRequest.getPassword(), registrationRequest.getType()));

    if (userRepository
        .findByUsernameAndEmail(registrationRequest.getUsername(), registrationRequest.getEmail())
        .orElse(null) != null) {
      return ResponseEntity.ok(new ResponseRegistration("V-ati inregistrat cu succes!", true));
    } else {
      return ResponseEntity
          .ok(new ResponseRegistration("Din pacate inregistrarea nu a avut loc!", false));
    }
  }
}
