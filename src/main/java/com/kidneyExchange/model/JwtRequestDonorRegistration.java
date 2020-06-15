package com.kidneyExchange.model;

import lombok.Data;

@Data
public class JwtRequestDonorRegistration {

  private String firstName;

  private String lastName;

  private String bloodType;

  private String email;

  private String city;
}
