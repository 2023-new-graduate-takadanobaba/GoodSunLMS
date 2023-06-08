package com.reality.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	@GetMapping("/select")
	public String login() {
		return "select";
	}
	
	@PostMapping("/select")
		public String doLogin(LoginForm form, HttpSession session) {
			session.setAttribute("userName", form.getUserName());
			Date date = new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
			session.setAttribute("date", sdf.format(date));
			session.removeAttribute("done");
			return "select";
		}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
