// vim:fileencoding=utf-8:foldmethod=marker


package com.cloudnomads.underbash.model;

import jakarta.persistence.*;

import java.time.LocalDate;

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

	@Column(nullable = false,updatable = false)
	private LocalDate created_at;

	public User() {}
	public User(String username, String password, Integer total_score, Boolean active) {
		this.username = username;
		this.password = password;
		this.total_score = total_score;
		this.active = active;
		this.created_at = LocalDate.now();
	}

	//{{{ Setters and Getters operators

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

	public LocalDate getCreatedAt(){
		return created_at;
	}

	//}}}
}
