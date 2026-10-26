package com.cloudnomads.underbash.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long questionId;

  private String question;
  private String correctAnswer;

  // Might want to give a different score depending on
  // the question difficulty.
  private Integer difficulty;

  public Question(){
  }

  public Question(String question, String correctAnswer, Integer difficulty){
    this.question = question;
    this.correctAnswer = correctAnswer;
    this.difficulty = difficulty;
  }

  // Getters and setter
  public Long getQuestion_id(){
    return questionId;
  }

  public void setQuestion_id(Long questionId){
    this.questionId = questionId;
  }

  public String getQuestion(){
    return question;
  }

  public void setQuestion(String question){
    this.question = question;
  }

  public String getCorrectAnswer(){
    return correctAnswer;
  }

  public void setCorrectAnswer(String correctAnswer){
    this.correctAnswer = correctAnswer;
  }

  public Integer getDifficulty(){
    return difficulty;
  }

  public void setDifficulty(Integer difficulty){
    this.difficulty = difficulty;
  }
}
