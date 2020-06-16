package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.PairPatientDonor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PairPatientDonorRepository extends JpaRepository<PairPatientDonor, Integer> {

  Optional<PairPatientDonor> findByPatientIdAndDonorId(Integer patientId, Integer donorId);
}
