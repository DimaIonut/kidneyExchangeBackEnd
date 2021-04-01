package com.kidneyExchange.service;

import com.kidneyExchange.Entity.User;
import com.kidneyExchange.config.JwtTokenUtil;
import com.kidneyExchange.controller.JwtAuthenticationController;
import com.kidneyExchange.model.JwtRequestLogin;
import com.kidneyExchange.model.JwtResponseLogin;
import com.kidneyExchange.repository.UserRepository;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

  private AuthenticationManager authenticationManager;

  private JwtTokenUtil jwtTokenUtil;

  private JwtUserDetailsService jwtInMemoryUserDetailsService;

  private UserRepository userRepository;

  private static Logger logger = LogManager.getLogger(JwtAuthenticationController.class);

  @Autowired
  public JwtAuthenticationService(
      AuthenticationManager authenticationManager,
      JwtTokenUtil jwtTokenUtil,
      JwtUserDetailsService jwtInMemoryUserDetailsService,
      UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
    this.userRepository = userRepository;
  }

  public ResponseEntity<?> authenticateUser(JwtRequestLogin authenticationRequest)
      throws Exception {

    User user = userRepository.findByUsernameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword()).orElse(null);

    if(user != null){
      authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

      final UserDetails userDetails = jwtInMemoryUserDetailsService
          .loadUserByUsername(authenticationRequest.getUsername());

      final String token = jwtTokenUtil.generateToken(userDetails);

      return ResponseEntity.ok(new JwtResponseLogin(token));}
    return null;
  }

  private void authenticate(String username, String password) throws Exception {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      logger.info("User ~~" + username + "~~ is successfully authenticated");
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }

}
