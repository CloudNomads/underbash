package com.cloudnomads.underbash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudnomads.underbash.model.MatchGame;

@Repository 
public interface MatchGameRepository extends JpaRepository<MatchGame, Long> {
}
