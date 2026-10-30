package com.cloudnomads.underbash.controller;

import org.springframework.web.bind.annotation.*;

import com.cloudnomads.underbash.repository.*;

import com.cloudnomads.underbash.model.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  // Route to REGISTER the user.
  @PostMapping("/register")
  public void register(@RequestBody User user){
    userRepository.save(user);
  }

  // Route to LOGIN.
}
