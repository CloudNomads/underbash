package com.cloudnomads.underbash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudnomads.underbash.model.MatchQuestion;
import com.cloudnomads.underbash.model.MatchQuestionId;

@Repository 
public interface MatchQuestionRepository extends JpaRepository<MatchQuestion, MatchQuestionId> {
}
