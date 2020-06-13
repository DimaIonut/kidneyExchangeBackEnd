package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.Donor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Integer> {

  Optional<Donor> findByName(String name);

}