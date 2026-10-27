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

	public MatchQuestion(){}
	public MatchQuestion(String user_answer){ // , int positive_score, int negative_score
		this.user_answer = user_answer;
	}

	// Getters and setters

	public MatchQuestionId getMatchQuestionId(){
		return matchQuestionId;
	}

	public void setMatchQuestionId(MatchQuestionId matchQuestionId){
		this.matchQuestionId = matchQuestionId;
	}

	public void setUserAnswer(String user){user_answer = user;}

	public String getUserAnswer(){return user_answer;}
	// todo: compare the written user answer with the correct answer
}
