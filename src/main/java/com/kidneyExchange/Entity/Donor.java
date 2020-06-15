package com.kidneyExchange.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donor {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String bloodType;

  private String gene;

  private String email;

  private String city;

  private Date arrivingTime;

  public Donor(String firstName, String lastName, String email, String city, String bloodType, Date arrivingTime) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.city = city;
    this.bloodType = bloodType;
    this.arrivingTime = arrivingTime;
  }
}
