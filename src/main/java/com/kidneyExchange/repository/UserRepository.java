package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNameAndPassword(String name, String password);

    Optional<User> findByName(String name);

}
