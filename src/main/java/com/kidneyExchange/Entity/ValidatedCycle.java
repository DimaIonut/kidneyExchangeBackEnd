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

    private boolean isOnlyTwoCycle;

    private Integer thirdPatientId;
    private Integer thirdDonorId;

    private boolean isThreeCycle;
}
