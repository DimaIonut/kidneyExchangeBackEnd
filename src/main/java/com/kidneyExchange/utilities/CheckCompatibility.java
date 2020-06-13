package com.kidneyExchange.utilities;

import com.kidneyExchange.Entity.Donor;
import com.kidneyExchange.Entity.Patient;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckCompatibility {

  @Autowired(required = true)
  CompatibilityRules compatibilityRules;

  public boolean areCompatible(Patient patient, Donor donor) {

    compatibilityRules.createRules();

    for (Entry<String, List<String>> entry : compatibilityRules.getCompatibilityRules()
        .entrySet()) {
      String key = entry.getKey();
      List<String> values = entry.getValue();
      if (donor.getBloodType().equals(key)) {
        if (values.contains(patient.getBloodType())) {
          return true;
        }
      }
    }
    return false;
  }
}
