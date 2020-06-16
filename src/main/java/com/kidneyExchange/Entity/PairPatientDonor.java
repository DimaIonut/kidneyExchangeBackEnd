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
public class PairPatientDonor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer pairId;

    private Integer patientId;

    private String bloodTypePatient;

    private Integer donorId;

    private String bloodTypeDonor;

    private boolean theyAreCompatible;

    public PairPatientDonor(Integer patientId, String bloodTypePatient, Integer donorId, String bloodTypeDonor, boolean theyAreCompatible) {
        this.patientId = patientId;
        this.bloodTypePatient = bloodTypePatient;
        this.donorId = donorId;
        this.bloodTypeDonor = bloodTypeDonor;
        this.theyAreCompatible = theyAreCompatible;
    }
}
