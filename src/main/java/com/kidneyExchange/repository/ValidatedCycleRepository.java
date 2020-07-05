package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.ValidatedCycle;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidatedCycleRepository extends JpaRepository<ValidatedCycle, Integer> {

  Optional<ValidatedCycle> findByCycleId(Integer cycleId);

  Optional<ValidatedCycle> findByFirstPatientIdAndFirstDonorIdAndSecondPatientIdAndSecondDonorIdAndTwoCycleAndThirdPatientIdAndThirdDonorIdAndThreeCycle(
      Integer firstPatientId, Integer firstDonorId, Integer secondPatientId, Integer secondDonorId,
      Boolean twoCycle, Integer thirdPatientId, Integer thirdDonorId, Boolean threeCycle);
}