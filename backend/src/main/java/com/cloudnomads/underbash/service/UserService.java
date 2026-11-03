package com.cloudnomads.underbash.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudnomads.underbash.model.User;
import com.cloudnomads.underbash.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;


@Service 
public class UserService{
	private final UserRepository repo;

	public UserService(UserRepository repo){
		this.repo = repo;
	}

	public UserRepository getRepo(){
		return repo;
	}

	@Transactional
	public User add(String username,String password){
		if (repo.existsByUsername(username)) {
			throw new RuntimeException("User not registered: this username just exists in this server!");      // todo: create a UserErrorException which extends RuntimeException
		}

		User u = new User(username,password,0,true);
		return repo.save(u);
	}

	@Transactional 
	public boolean remove(String username){      // todo: insert a password management system
		if(repo.existsByUsername(username)){
			User u = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));

			repo.delete(u);
		}

		return true;
	}

	// ----------------------------------------------------------------------------------------------------

	@Transactional(readOnly = true)
	public User getUser(String username){
		return repo.findByUsername(username)
		.orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Transactional(readOnly = true)
	public List<User> getAllUsers(){
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	public User getUserByTotScore(String username,int tot_score){
		return repo.findByUsername(username)
		.filter(u -> u.getUsername().equals(username) && u.getTotalScore() == tot_score)
		.orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Transactional(readOnly = true)
	public List<User> getAllUsersByTotScore(int tot_score){
		return repo.findAllByTotalScore(tot_score);
	}

	@Transactional(readOnly = true)
	public User getUserByActive(String username,boolean active){
		return repo.findByUsername(username)
		.filter(u -> u.getUsername().equals(username) && u.isActive() == active)
		.orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Transactional(readOnly = true)
	public List<User> getAllUsersByActive(boolean active){
		return repo.findAllByActive(active);
	}

	// ----------------------------------------------------------------------------------------------------

	@Transactional 
	public User registerUser(String username,String password){
		return add(username, password);
	}

	@Transactional 
	public boolean loginUser(String username,String password){
		// User u = getUser(username);
		// boolean b = false;
		//
		// if(u.getPassword().equals(password)){
		// 	b = true;
		// 	u.setActive(b);
		// 	repo.save(u);
		// }else{
		// 	System.out.println("Password incorrect: try again!");
		// }
		//
		// return b;

		return repo.findByUsername(username).map(u -> {
			if(u.getPassword().equals(password)){
				u.setActive(true);
				repo.save(u);

				return true;
			}

			return false;
		}).orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Transactional 
	public boolean logoutUser(String username){
		// User u = getUser(username);
		//
		// u.setActive(false);
		// repo.save(u);
		//
		// return true;

		return repo.findByUsername(username).map(u -> {
			if(u != null){
				u.setActive(false);
				repo.save(u);

				return true;
			}
			return false;
		}).orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Transactional 
	public User updateUser(String us_name,String new_username,String new_password,Integer new_tot_score){
		User u = getUser(us_name);

		if(new_username != null && !new_username.isBlank()) {
			if(repo.existsByUsername(new_username)) throw new RuntimeException("This username already exists in the server!");
			u.setUsername(new_username);
		}
		if(new_password != null && !new_password.isBlank()) u.setPassword(new_password);
		if(new_tot_score != null) u.setTotalScore(new_tot_score);

		return repo.save(u);
	}

	@Transactional
	public boolean deleteUser(String username){
		// return repo.findByUsername(username).map(u ->{
		// 	repo.delete(u);
		// 	return true;
		// }).orElse(false);
		return remove(username);
	}
}
