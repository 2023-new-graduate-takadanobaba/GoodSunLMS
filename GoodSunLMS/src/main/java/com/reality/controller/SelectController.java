package com.reality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SelectController {

	@GetMapping("/dailyReport")
	public String dailyReport() {
		return "dailyReport";
	}
}
