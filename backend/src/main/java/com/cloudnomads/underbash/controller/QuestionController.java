package com.cloudnomads.underbash.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.cloudnomads.underbash.repository.QuestionRepository;
import com.cloudnomads.underbash.model.Question;

import com.cloudnomads.underbash.repository.MatchQuestionRepository;
import com.cloudnomads.underbash.model.MatchQuestion;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

  private final QuestionRepository questionRepository;
  private final MatchQuestionRepository matchQuestionRepository;

  public QuestionController(QuestionRepository questionRepository, MatchQuestionRepository matchQuestionRepository){
    this.questionRepository = questionRepository;
    this.matchQuestionRepository = matchQuestionRepository;
  }

  // Route to GET the question shown up.
  @GetMapping("/show")
  public List<Question> getQuestionShown(){
    return questionRepository.findAll();
  }

  // Route to POST the user's answer.
  @PostMapping("/post")
  public ResponseEntity<String> postAnswer(@RequestBody MatchQuestion matchQuestion){
    matchQuestionRepository.save(matchQuestion);

    return ResponseEntity.status(HttpStatus.CREATED).body("Successfully saved match question.");
  }
}