package com.cloudnomads.underbash.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long question_id;

  private String question;
  private String answer;

  // Might want to give a different score depending on
  // the question difficulty.
  private Integer scoreQuestion;

  public Question(){
  }

  public Question(String question, String answer, Integer scoreQuestion){
    this.question = question;
    this.answer = answer;
    this.scoreQuestion = scoreQuestion;
  }

  // Getters and setter
  public Long getQuestion_id(){
    return question_id;
  }

  public void setQuestion_id(Long question_id){
    this.question_id = question_id;
  }

  public String getQuestion(){
    return question;
  }

  public void setQuestion(String question){
    this.question = question;
  }

  public String getAnswer(){
    return answer;
  }

  public void setAnswer(String answer){
    this.answer = answer;
  }

  public Integer getScoreQuestion(){
    return scoreQuestion;
  }

  public void setScoreQuestion(Integer scoreQuestion){
    this.scoreQuestion = scoreQuestion;
  }
}
