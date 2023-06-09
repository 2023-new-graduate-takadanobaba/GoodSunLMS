package com.reality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.reality.repository.AttendanceRepository;
import com.reality.repository.UserRepository;

@Controller
public class ManualnputController {
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/manualInput")
	public String manualInput() {
		return "manualInput";
	}
}
