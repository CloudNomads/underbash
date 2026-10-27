package com.cloudnomads.underbash.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable 
public class MatchQuestionId implements Serializable {
  private Long matchId;
  private Long questionId;

  // equals and hashCode methods.

  @Override
  public boolean equals(Object o){
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MatchQuestionId matchQuestionId = (MatchQuestionId) o;
    return Objects.equals(matchId, matchQuestionId.matchId) && Objects.equals(questionId, matchQuestionId.questionId);
  }

  @Override
  public int hashCode(){
    return Objects.hash(matchId, questionId);
  }

  // Getters and setters.
  
  public Long getMatchId(){
    return matchId;
  }

  public void setMatchId(Long matchid){
    this.matchId = matchid;
  }

  public Long getQuestionId(){
    return questionId;
  }

  public void setQuestionId(Long questionId){
    this.questionId = questionId;
  }
}
