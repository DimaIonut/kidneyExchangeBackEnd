package com.kidneyExchange.service;

import com.kidneyExchange.Entity.User;
import com.kidneyExchange.controller.RegistrationController;
import com.kidneyExchange.model.RequestRegistration;
import com.kidneyExchange.model.ResponseRegistration;
import com.kidneyExchange.repository.UserRepository;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

  @Autowired
  private UserRepository userRepository;

  private static Logger logger = LogManager.getLogger(RegistrationController.class);

  public ResponseEntity<?> registrate(RequestRegistration requestRegistration) {

    logger.info(
        "User " + requestRegistration.getUsername() + " registration (at " + new Date() + ")");

    userRepository
        .save(new User(requestRegistration.getUsername(), requestRegistration.getEmail(),
            requestRegistration.getPassword(), requestRegistration.getType()));

    if (userRepository
        .findByUsernameAndEmail(requestRegistration.getUsername(), requestRegistration.getEmail())
        .orElse(null) != null) {
      return ResponseEntity.ok(new ResponseRegistration("V-ati inregistrat cu succes!", true));
    } else {
      return ResponseEntity
          .ok(new ResponseRegistration("Din pacate inregistrarea nu a avut loc!", false));
    }
  }

}
