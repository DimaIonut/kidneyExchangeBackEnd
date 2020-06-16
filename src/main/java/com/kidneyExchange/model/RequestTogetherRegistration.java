package com.kidneyExchange.model;

import lombok.Data;

@Data
public class RequestTogetherRegistration {

  private String firstNamePatient;

  private String lastNamePatient;

  private String emailPatient;

  private String cityPatient;

  private String bloodTypePatient;

  private String firstNameDonor;

  private String lastNameDonor;

  private String emailDonor;

  private String cityDonor;

  private String bloodTypeDonor;
}
