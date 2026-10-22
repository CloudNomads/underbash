package com.cloudnomads.underbash.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// import 

@Entity
@Table (name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (unique = true,nullable = true)
	private String question;
	private String correct_answer;
	private String user_answer;
	// @Column (unique = true,nullable = false)
	// private int positive_score;
	// @Column (unique = true,nullable = false)
	// private int negative_score;

	private boolean correct;

	public Question(){}
	public Question(String question, String correct_answer, String user_answer, boolean correct){ // , int positive_score, int negative_score
		this.question = question;
		this.correct_answer = correct_answer;
		this.user_answer = user_answer;
		this.correct = correct;
	// 	this.positive_score = positive_score;
	// 	this.negative_score = negative_score;
	}

	public void setQuestion(String q){question = q;}
	public void setCorrectAnswer(String correct){correct_answer = correct;}
	public void setUserAnswer(String user){user_answer = user;}
	// public void setPositiveScore(int score){positive_score = score;}
	// public void setNegativeScore(int score){negative_score = score;}
	public void setIsCorrect(boolean correct){this.correct = correct;}

	public String getQuestion(){return question;}
	public String getCorrectAnswer(){return correct_answer;}
	public String getUserAnswer(){return user_answer;}
	public boolean getCorrect(){return correct;}
	// public int getPositiveScore(){return positive_score;}
	// public int getNegativeScore(){return negative_score;}

	// public boolean isCorrect(){return true;} // todo: compare the written user answer with the correct answer
}
