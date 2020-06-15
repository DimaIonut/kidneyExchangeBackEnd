package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.Patient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

  Optional<Patient> findByFirstName(String name);
}
