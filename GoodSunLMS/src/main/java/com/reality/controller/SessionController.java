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
	
	@GetMapping("/layout_view")
	public String layout_view() {
		return "layout_view";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/loading")
	public String loading() {
		return "loading";
	}
	
	@GetMapping("/select")
	public String login(HttpSession session) {
		return "select";
	}
	
	@PostMapping("/select")
		public String doLogin(LoginForm form, HttpSession session) {
			session.setAttribute("userName", form.getUserName());
			Date date = new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
			session.setAttribute("date", sdf.format(date));
			return "select";
		}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
