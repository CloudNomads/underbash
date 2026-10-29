package com.cloudnomads.underbash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudnomads.underbash.model.User;

import java.util.Optional;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
  // Optional returns null when user is not found in the database.
  Optional<User> findByUsername(String username);
}
