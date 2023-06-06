package com.reality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {
//	@Autowired
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@PostMapping("/login")
		public String doLogin() {
			return "select";
		}
}
