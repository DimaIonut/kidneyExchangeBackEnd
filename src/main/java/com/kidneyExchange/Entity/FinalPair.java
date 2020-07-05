package com.kidneyExchange.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalPair {

  private String nameP1;
  private String bloodP1;
  private String nameD1;
  private String bloodD1;

  private String nameP2;
  private String bloodP2;
  private String nameD2;
  private String bloodD2;

  private String nameP3;
  private String bloodP3;
  private String nameD3;
  private String bloodD3;

  public FinalPair(String nameP1, String bloodP1, String nameD1, String bloodD1, String nameP2,
      String bloodP2, String nameD2, String bloodD2) {

    this.nameP1 = nameP1;
    this.bloodP1 = bloodP1;
    this.nameD1 = nameD1;
    this.bloodD1 = bloodD1;
    this.nameP2 = nameP2;
    this.bloodP2 = bloodP2;
    this.nameD2 = nameD2;
    this.bloodD2 = bloodD2;
  }

  public FinalPair(String nameP1, String bloodP1, String nameD1, String bloodD1) {

    this.nameP1 = nameP1;
    this.bloodP1 = bloodP1;
    this.nameD1 = nameD1;
    this.bloodD1 = bloodD1;
  }
}
