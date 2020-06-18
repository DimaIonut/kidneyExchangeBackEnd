package com.kidneyExchange.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatedCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cycleId;

    private Integer firstPatientId;
    private Integer firstDonorId;

    private Integer secondPatientId;
    private Integer secondDonorId;

    private Boolean isTwoCycle;

    private Integer thirdPatientId;
    private Integer thirdDonorId;

    private Boolean isThreeCycle;

    public ValidatedCycle(Integer firstPatientId, Integer firstDonorId, Integer secondPatientId, Integer secondDonorId) {
        this.firstPatientId = firstPatientId;
        this.firstDonorId = firstDonorId;
        this.secondPatientId = secondPatientId;
        this.secondDonorId = secondDonorId;
        this.isTwoCycle = true;
    }

    public ValidatedCycle(Integer firstPatientId, Integer firstDonorId, Integer secondPatientId, Integer secondDonorId, Integer thirdPatientId, Integer thirdDonorId,
        Boolean isTwoCycle) {
        this.firstPatientId = firstPatientId;
        this.firstDonorId = firstDonorId;
        this.secondPatientId = secondPatientId;
        this.secondDonorId = secondDonorId;
        this.isTwoCycle = isTwoCycle;
        this.thirdPatientId = thirdPatientId;
        this.thirdDonorId = thirdDonorId;
        this.isThreeCycle = true;
    }
}
