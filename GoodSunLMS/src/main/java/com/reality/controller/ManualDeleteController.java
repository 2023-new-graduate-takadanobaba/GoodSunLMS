package com.reality.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@PostMapping("/doManualDelete")
	public String doManualDelete(Date date, String startTime, Model model, HttpSession session) {
		User user = userRepository.findByUserName(session.getAttribute("userName").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		startTime = removeFirstChar(startTime);
		
		attendanceRepository.deleteByDateAndStartTime(date, startTime);
		return "findAllAttendance";
	}
	
	//　時刻の入力形式変更 ex) 09:00 >> 9:00
		private String removeFirstChar (String str) {
			if(str.startsWith("0")) {
				str = str.substring(1);
			}		
			return str;
		}
}
