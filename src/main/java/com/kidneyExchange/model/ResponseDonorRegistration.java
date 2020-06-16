package com.kidneyExchange.model;

import lombok.Data;

@Data
public class ResponseDonorRegistration {

  private Boolean userIsInDatabase;

  public ResponseDonorRegistration(Boolean userIsInDatabase) {
    this.userIsInDatabase = userIsInDatabase;
  }
}
