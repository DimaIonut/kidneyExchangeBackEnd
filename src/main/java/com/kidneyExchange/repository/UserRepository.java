package com.kidneyExchange.repository;

import com.kidneyExchange.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUserIdAndUsername(Integer id, String username);

    Optional<User> findByUsername(String username);
}