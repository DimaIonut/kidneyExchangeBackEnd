package com.kidneyExchange.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalCycle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer cycleId;

  private Integer firstPatientId;
  private Integer firstDonorId;

  private Integer secondPatientId;
  private Integer secondDonorId;

  private Boolean twoCycle;

  private Integer thirdPatientId;
  private Integer thirdDonorId;

  private Boolean threeCycle;

  public FinalCycle(Integer firstPatientId, Integer firstDonorId, Integer secondPatientId,
      Integer secondDonorId) {
    this.firstPatientId = firstPatientId;
    this.firstDonorId = firstDonorId;
    this.secondPatientId = secondPatientId;
    this.secondDonorId = secondDonorId;
    this.twoCycle = true;
    this.threeCycle = false;
  }

  public FinalCycle(Integer firstPatientId, Integer firstDonorId, Integer secondPatientId,
      Integer secondDonorId, Integer thirdPatientId, Integer thirdDonorId,
      Boolean twoCycle) {
    this.firstPatientId = firstPatientId;
    this.firstDonorId = firstDonorId;
    this.secondPatientId = secondPatientId;
    this.secondDonorId = secondDonorId;
    this.twoCycle = twoCycle;
    this.thirdPatientId = thirdPatientId;
    this.thirdDonorId = thirdDonorId;
    this.threeCycle = true;
  }
}
