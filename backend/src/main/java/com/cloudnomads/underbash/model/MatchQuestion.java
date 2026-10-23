package com.cloudnomads.underbash.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// import 

@Entity
@Table (name = "match_questions")
public class MatchQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (unique = true,nullable = true)
	private String question;
	private String correct_answer;
	private String user_answer;

	private boolean correct;

	public MatchQuestion(){}
	public MatchQuestion(String question, String correct_answer, String user_answer, boolean correct){ // , int positive_score, int negative_score
		this.question = question;
		this.correct_answer = correct_answer;
		this.user_answer = user_answer;
		this.correct = correct;
	}

	// Getters and setters

	public void setQuestion(String q){question = q;}
	public void setCorrectAnswer(String correct){correct_answer = correct;}
	public void setUserAnswer(String user){user_answer = user;}
	public void setIsCorrect(boolean correct){this.correct = correct;}

	public String getQuestion(){return question;}
	public String getCorrectAnswer(){return correct_answer;}
	public String getUserAnswer(){return user_answer;}
	public boolean getCorrect(){return correct;}
	// todo: compare the written user answer with the correct answer
}
