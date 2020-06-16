package com.kidneyExchange.model;

import lombok.Data;

@Data
public class ResponsePatientRegistration {

  private Boolean userIsInDatabase;

  public ResponsePatientRegistration(Boolean userIsInDatabase) {
    this.userIsInDatabase = userIsInDatabase;
  }
}
