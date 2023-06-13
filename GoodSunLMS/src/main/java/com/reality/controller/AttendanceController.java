package com.reality.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		User user = userRepository.getReferenceById(Integer.parseInt(session.getAttribute("userId").toString()));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		
		List<Attendance> attdArr = attendanceRepository.findAll();
		for(int i=0; i<attdArr.size(); i++) {
			if(sdf.format(attdArr.get(i).getDate()).equals(dateStr) && attdArr.get(i).getUser().getId()==user.getId()) {
				model.addAttribute("stat", "attendanceError");
				return "error";
			}
		}
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
		model.addAttribute("stat", "attendance");
		return "loading";
	}
	

	
	@GetMapping("/findAllAttendance")
	public String findAllAttendance(Model model, HttpSession session) {
		User user = userRepository.getReferenceById(Integer.parseInt(session.getAttribute("userId").toString()));
		model.addAttribute("attendance", attendanceRepository.findByUser(user));
		return "findAllAttendance";
	}
}
