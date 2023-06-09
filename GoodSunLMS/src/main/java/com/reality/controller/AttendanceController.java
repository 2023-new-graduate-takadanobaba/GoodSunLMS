package com.reality.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.reality.entity.Attendance;
import com.reality.entity.User;
import com.reality.repository.AttendanceRepository;
import com.reality.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AttendanceController {
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/attendanceSystem")
	public String attendanceSystem() {
		return "attendanceSystem";
	}
	
	@PostMapping("/attendanceRegister")
	public String attendanceRegister(Model model, HttpSession session) {
		Attendance attendance = new Attendance();
		User user = userRepository.findByUserName(session.getAttribute("userName").toString());
		Date date = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		attendance.setDate(date);
		attendance.setStartTime("9:00");
		attendance.setEndTime("18:00");
		attendance.setWorkHours("8:00");
		attendance.setPlace("高田馬場事務所");
		attendance.setProject("新入社員研修");
		attendance.setRemarks("やっとむ屋");
		attendance.setUser(user);
		attendanceRepository.save(attendance);
		model.addAttribute("attendance", attendance);
		return "attendanceMessage";
	}
	

	
	@GetMapping("/findAllAttendance")
	public String findAllAttendance(Model model) {
		model.addAttribute("attendance", attendanceRepository.findAll());
		return "findAllAttendance";
	}
}
