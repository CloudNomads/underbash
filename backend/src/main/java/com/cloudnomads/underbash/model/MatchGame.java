package com.cloudnomads.underbash.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matchgames")
public class MatchGame {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long matchId;

  // Number of questions
  private Integer numberOfQuestions;
  // Seconds of the match
  private Double seconds;
  // Total score of the match
  private Integer totalMatchScore;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public MatchGame(){
  }

  public MatchGame(Integer numberOfQuestions, Double seconds, User user, Integer totalMatchScore){
    this.numberOfQuestions = numberOfQuestions;
    this.seconds = seconds;
    this.user = user;
    this.totalMatchScore = totalMatchScore;
  }

  // Getters and setters
  public Long getMatchId(){
    return matchId;
  }

  public void setMatchId(Long matchId){
    this.matchId = matchId;
  }

  public Integer getNumberOfQuestions(){
    return numberOfQuestions;
  }

  public void setNumberOfQuestions(Integer numberOfQuestions){
    this.numberOfQuestions = numberOfQuestions;
  }

  public Double getSeconds(){
    return seconds;
  }

  public void setSeconds(Double seconds) {
    this.seconds = seconds;
  }

  public User getUser(){
    return user;
  }

  public void setUser(User user){
    this.user = user;
  }

  public Integer getTotalMatchScore(){
    return totalMatchScore;
  }

  public void setTotalMatchScore(Integer totalMatchScore){
    this.totalMatchScore = totalMatchScore;
  }
}