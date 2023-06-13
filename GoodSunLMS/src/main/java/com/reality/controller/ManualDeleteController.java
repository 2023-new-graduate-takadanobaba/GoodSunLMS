package com.reality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.reality.entity.User;
import com.reality.repository.AttendanceRepository;
import com.reality.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManualDeleteController {
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/manualDelete")
	public String manualDelete(Model model, HttpSession session) {
		User user = userRepository.findByUserName(session.getAttribute("userName").toString());
		model.addAttribute("attendance", attendanceRepository.findByUser(user));
		return "manualDelete";
	}
	
//	@PostMapping("/doManualDelete")
//	public String doManualDelete(Date date, String startTime, Model model, HttpSession session) {
//		Attendance attendance = new Attendance();
//		User user = userRepository.findByUserName(session.getAttribute("userName").toString());
//		
//		return "findAllAttendance";
//	}
}
