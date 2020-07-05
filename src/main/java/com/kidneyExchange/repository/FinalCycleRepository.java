package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.FinalCycle;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinalCycleRepository extends JpaRepository<FinalCycle, Integer> {

  Optional<FinalCycle> findByFirstPatientIdAndFirstDonorIdAndSecondPatientIdAndSecondDonorIdAndTwoCycleAndThirdPatientIdAndThirdDonorIdAndThreeCycle(
      Integer firstPatientId, Integer firstDonorId, Integer secondPatientId, Integer secondDonorId,
      Boolean twoCycle, Integer thirdPatientId, Integer thirdDonorId, Boolean threeCycle);
}
