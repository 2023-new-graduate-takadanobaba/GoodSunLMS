package com.reality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManualDeleteController {

	@GetMapping("/manualDelete")
	public String manualDelete() {
		return "manualDelete";
	}
}
