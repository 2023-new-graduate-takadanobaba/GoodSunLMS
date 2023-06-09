package com.reality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reality.entity.User;
import com.reality.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/adduser")
	public String addUser(@ModelAttribute User user) {
		return "addUser";
	}
	
	@PostMapping("/adduser")
	public String doAddUser(@ModelAttribute User user) {
		userRepository.save(user);
		return "user";
	}
}
