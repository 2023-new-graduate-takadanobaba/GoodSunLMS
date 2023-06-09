package com.reality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.reality.repository.AttendanceRepository;

@Controller
public class AttendanceController {
	
	@Autowired
	AttendanceRepository attendanceRepository;

	@GetMapping("/attendanceSystem")
	public String attendanceSystem() {
		return "attendanceSystem";
	}
	
	@PostMapping("/attendanceRegister")
	public String attendanceRegister() {
		return "attendanceMessage";
	}
	
	@GetMapping("/findAllAttendance")
	public String findAllAttendance(Model model) {
		model.addAttribute("attendance", attendanceRepository.findAll());
		return "findAllAttendance";
	}
}
