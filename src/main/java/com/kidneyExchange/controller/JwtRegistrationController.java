package com.kidneyExchange.controller;


import com.kidneyExchange.Entity.User;
import com.kidneyExchange.model.JwtRequestRegistration;
import com.kidneyExchange.model.JwtResponseRegistration;
import com.kidneyExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Date;

@RestController
@CrossOrigin
public class JwtRegistrationController {

    @Autowired
    UserRepository userRepository;

    private static Logger logger = LogManager.getLogger(JwtRegistrationController.class);

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public ResponseEntity<?> creataNewUser(@RequestBody JwtRequestRegistration registrationRequest)
        throws Exception {

        logger.info("User " + registrationRequest.getUsername() + " registration (at " + new Date() + ")");

        User user = new User(registrationRequest.getUsername(), registrationRequest.getEmail(), registrationRequest.getPassword(), registrationRequest.getType());

        User newUser = userRepository.save(user);

        if (userRepository.findByUserIdAndUsername(newUser.getUserId(), newUser.getUsername()) != null) {
                return ResponseEntity.ok(new JwtResponseRegistration("V-ati inregistrat cu succes!", true));
        }
        else {
            return ResponseEntity.ok(new JwtResponseRegistration("Din pacate inregistrarea nu a avut loc!", false));
        }
    }
}
