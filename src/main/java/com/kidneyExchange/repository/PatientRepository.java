package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.Patient;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

  Optional<Patient> findByFirstName(String name);

  Optional<Patient> findByFirstNameAndLastNameAndBloodType(String firstName, String lastName, String bloodType);

  List<Patient> findByHasPairOrderByArrivingTimeAsc(Boolean hasPair);

  Optional<Patient> findById(Integer id);

  List<Patient> findByOrderByIdAsc();
}
