package com.kidneyExchange.model;

import lombok.Data;

@Data
public class ResponseTogetherRegistration {

  private Boolean usersIsInDatabase;

  public ResponseTogetherRegistration(Boolean usersIsInDatabase) {
    this.usersIsInDatabase = usersIsInDatabase;
  }
}
