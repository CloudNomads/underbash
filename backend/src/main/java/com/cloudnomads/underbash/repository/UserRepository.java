package com.cloudnomads.underbash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudnomads.underbash.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String username);
	public boolean existsByUsername(String username);
	// public List<User> findByTotalScore(Integer total_score);
	public List<User> findAllByTotalScore();
}
