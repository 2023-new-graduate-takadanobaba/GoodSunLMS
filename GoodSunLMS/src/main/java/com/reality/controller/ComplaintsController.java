package com.reality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComplaintsController {

	@PostMapping("/complaints")
	public String complaints() {
		return "complaintsMessage";
	}
}
