package com.cloudnomads.underbash.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cloudnomads.underbash.service.UserService;
import com.cloudnomads.underbash.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService service;

	public UserController(UserService service){
		this.service = service;
	}

	// REGISTER
	@PostMapping("/register")
	public User register(
		@RequestParam String init_username,
		@RequestParam String init_password
	){
		return service.registerUser(init_username,init_password);
	}

	// LOGIN
	@PostMapping("/login")
	public boolean login(
		@RequestParam String username,
		@RequestParam String password
	){
		return service.loginUser(username, password);
	}

	// LOGOUT
	@PostMapping("/logout")
	public boolean logout(@RequestParam String username){
		return service.logoutUser(username);
	}

	// UPDATE
	@PostMapping("/update")
	public User update(
		@RequestParam String curr_username,
		@RequestParam(required = false) String new_username,
		@RequestParam(required = false) String new_password,
		@RequestParam(required = false) Integer new_tot_score
	){
		return service.updateUser(curr_username,new_username,new_password,new_tot_score);
	}

	// DELETE
	@PostMapping("/delete")
	public boolean delete(@RequestParam String username){
		return service.deleteUser(username);
	}

	// GET USER/ALL
	@GetMapping("/get")
	public User get(@RequestParam String username){
		return service.getUser(username);
	}

	@GetMapping("/get-all")
	public List<User> getAll(){
		return service.getAllUsers();
	}

	@GetMapping("/get-by-score")
	public User getByScore(
		@RequestParam String username,
		@RequestParam int tot_score
	){
		return service.getUserByTotScore(username,tot_score);
	}

	@GetMapping("/get-all-by-score")
	public List<User> getAllByScore(@RequestParam int tot_score){
		return service.getAllUsersByTotScore(tot_score);
	}

	@GetMapping("/get-user-online")
	public User getByActive(
		@RequestParam String username,
		@RequestParam boolean status
	){
		return service.getUserByActive(username,status);
	}

	@GetMapping("/get-all-online")
	public List<User> getAllByActive(@RequestParam boolean status){
		return service.getAllUsersByActive(status);
	}

	@GetMapping("/get-by-date-created")
	public User getUserByAccountCreated(String username,LocalDate created){
		return service.getUserByCreationDate(username,created);
	}

	@GetMapping("/get-all-by-date-created")
	public List<User> getAllByAccountCreated(@RequestParam LocalDate created){
		return service.getAllUsersByCreationDate(created);
	}
}
