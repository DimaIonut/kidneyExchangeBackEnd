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

    private Boolean twoCycle;

    private Integer thirdPatientId;
    private Integer thirdDonorId;

    private Boolean threeCycle;

    public ValidatedCycle(Integer firstPatientId, Integer firstDonorId, Integer secondPatientId, Integer secondDonorId) {
        this.firstPatientId = firstPatientId;
        this.firstDonorId = firstDonorId;
        this.secondPatientId = secondPatientId;
        this.secondDonorId = secondDonorId;
        this.twoCycle = true;
        this.threeCycle = false;
    }

    public ValidatedCycle(Integer firstPatientId, Integer firstDonorId, Integer secondPatientId, Integer secondDonorId, Integer thirdPatientId, Integer thirdDonorId,
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
