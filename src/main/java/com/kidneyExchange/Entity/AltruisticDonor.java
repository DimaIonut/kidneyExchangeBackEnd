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
public class AltruisticDonor {

  @Id
  private Integer id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String bloodType;

  private String email;

  private String city;

  public AltruisticDonor(Integer id, String firstName, String lastName, String bloodType) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.bloodType = bloodType;
  }
}
