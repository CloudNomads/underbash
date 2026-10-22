package com.cloudnomads.underbash.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // usernames are unique
    @Column(unique = true, nullable = false)
    private String username;
     
    private String password;
    private Integer totalScore;

    public User() {
    }

    public User(String username, String password, Integer totalScore) {
        this.username = username;
        this.password = password;
        this.totalScore = totalScore;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore(){
        return totalScore;
    }

    public void setScore(Integer totalScore){
        this.totalScore = totalScore;
    }
}