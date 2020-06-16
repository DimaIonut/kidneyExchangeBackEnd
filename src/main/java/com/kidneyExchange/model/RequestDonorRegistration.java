package com.kidneyExchange.model;

import lombok.Data;

@Data
public class RequestDonorRegistration {

  private String firstName;

  private String lastName;

  private String bloodType;

  private String email;

  private String city;
}
