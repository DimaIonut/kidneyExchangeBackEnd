package com.kidneyExchange.algorithmSteps;

import com.kidneyExchange.Entity.Patient;
import com.kidneyExchange.repository.PatientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TwoCycleAndPiMaximised {

  @Autowired
  PatientRepository patientRepository;

   private void createPairs() {

     List<Patient> patients = patientRepository.findByHasPairOrderByArrivingTimeAsc(false);
   }

   public void start() {

     createPairs();
   }
}
