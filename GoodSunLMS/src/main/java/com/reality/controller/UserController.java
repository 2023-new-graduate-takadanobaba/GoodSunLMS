package com.reality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reality.entity.User;
import com.reality.form.LoginForm;
import com.reality.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/adduser")
	public String addUser(@ModelAttribute LoginForm form) {
		return "addUser";
	}
	
	@PostMapping("/adduser")
	public String doAddUser(@Valid @ModelAttribute LoginForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "addUser";
		}
		User user = new User();
		user.setUserName(form.getUserName());
		user.setPassword(form.getPassword());
		userRepository.save(user);
		return "user";
	}
}
