package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.Donor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Integer> {

  Optional<Donor> findByFirstName(String name);

  Optional<Donor> findByFirstNameAndLastNameAndBloodType(String firstName, String lastName,
      String bloodType);

  List<Donor> findByHasPairOrderByArrivingTimeAsc(Boolean hasPair);

}