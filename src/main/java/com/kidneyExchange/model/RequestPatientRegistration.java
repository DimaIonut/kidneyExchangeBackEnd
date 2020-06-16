package com.kidneyExchange.model;

import lombok.Data;

@Data
public class RequestPatientRegistration {

  private String firstName;

  private String lastName;

  private String bloodType;

  private String email;

  private String city;
}
