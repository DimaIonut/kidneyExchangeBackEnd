package com.kidneyExchange.controller;

import com.kidneyExchange.model.JwtRequestLogin;
import com.kidneyExchange.service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

  @Autowired
  private JwtAuthenticationService jwtAuthenticationService;

  private JwtRequestLogin jwtRequestLogin = new JwtRequestLogin();

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody JwtRequestLogin authenticationRequest)
      throws Exception {

    return jwtAuthenticationService.authenticateUser(authenticationRequest);
  }
}
