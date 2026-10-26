package com.cloudnomads.underbash.model;

import jakarta.persistence.*;

// import 

@Entity
@Table (name = "match_questions")
public class MatchQuestion {

	// note: to uniquely identify a match question the matchId (from MatchGame.java) and 
	// the question itself is needed. This means that the same question can't be asked twice in the same match.
	// Need for @Embeddable and @EmbeddableId to achieve this.

	@EmbeddedId 
	private MatchQuestionId matchQuestionId;

	private String user_answer;

	private boolean correct;

	public MatchQuestion(){}
	public MatchQuestion(String user_answer, boolean correct){ // , int positive_score, int negative_score
		this.user_answer = user_answer;
		this.correct = correct;
	}

	// Getters and setters

	public void setUserAnswer(String user){user_answer = user;}
	public void setIsCorrect(boolean correct){this.correct = correct;}

	public String getUserAnswer(){return user_answer;}
	public boolean getCorrect(){return correct;}
	// todo: compare the written user answer with the correct answer
}
