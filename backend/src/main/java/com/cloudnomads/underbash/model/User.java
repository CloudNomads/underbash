package com.cloudnomads.underbash.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users") // Table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // usernames are unique
    @Column(unique = true, nullable = false)
    private String username;

	@Column(nullable = false)
    private String password;					// todo: insert a password management system

	@Column(name = "totalscore",nullable = false)
    private Integer total_score;				// todo: create a Score Class

	@Column(name = "online",nullable = false)
	private Boolean active;

    private Date accountCreated;

    public User() {}
    public User(String username, String password, Integer total_score, Boolean active, Date accountCreated) {
        this.username = username;
        this.password = password;
        this.total_score = total_score;
		this.active = active;
        this.accountCreated = accountCreated;
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

    public int getTotalScore(){
        return total_score;
    }

    public void setTotalScore(Integer total_score){
        this.total_score = total_score;
    }

	public Boolean isActive(){
		return active;
	}

	public void setActive(boolean active){
		this.active = active;
	}

    public Date getAccountCreated(){
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated){
        this.accountCreated = accountCreated;
    }
}
