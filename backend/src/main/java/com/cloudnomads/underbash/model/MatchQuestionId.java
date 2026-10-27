package com.cloudnomads.underbash.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable 
public class MatchQuestionId implements Serializable {
  private Long matchId;
  private Long questionId;

  // Getters and setters

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
