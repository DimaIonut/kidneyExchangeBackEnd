package com.kidneyExchange.model;

import lombok.Data;

@Data
public class JwtResponseDonorRegistration {

  private Boolean userIsInDatabase;

  public JwtResponseDonorRegistration(Boolean userIsInDatabase) {
    this.userIsInDatabase = userIsInDatabase;
  }
}
