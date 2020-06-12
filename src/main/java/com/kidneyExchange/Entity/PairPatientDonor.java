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
    private String pairId;

    private Integer patientId;

    private Integer donorId;

    private boolean theyAreCompatible;
}
