package com.reality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.reality.form.LoginForm;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
//	@Autowired
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@PostMapping("/login")
		public String doLogin(LoginForm form, HttpSession session) {
			session.setAttribute("userName", form.getUserName());
			return "select";
		}
	
	@GetMapping("/select")
		public String select() {
		return "select";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
