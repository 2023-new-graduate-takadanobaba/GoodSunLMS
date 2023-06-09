package com.reality.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reality.entity.User;
import com.reality.form.LoginForm;
import com.reality.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SessionController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/index")
	public String index(@ModelAttribute LoginForm form) {
		return "index";
	}
	
	@PostMapping("/login")
	public String doLogin(@Valid @ModelAttribute LoginForm form, BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "index";
		}
		
		String userName = form.getUserName();
		String password = form.getPassword();
		User user = userRepository.findByUserNameAndPassword(userName, password);
		
		if (user != null) {
			session.setAttribute("userName", user.getUserName());
			return "redirect:/select";
		} else {
			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
			return "index";
		}
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
	public String logout(@ModelAttribute LoginForm form, HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
