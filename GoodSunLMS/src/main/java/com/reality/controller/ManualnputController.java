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
public class ManualnputController {
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/manualInput")
	public String manualInput() {
		return "manualInput";
	}
	
	@PostMapping("/doManualInput")
	public String doManualInput(String startTime, String endTime, String workHours,
			String division, String project, String place, String remarks, Model model, HttpSession session) {
			Attendance attendance = new Attendance();
			User user = userRepository.findByUserName(session.getAttribute("userName").toString());
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = sdf.format(date);
			
			
			// 14:00
			Integer intStr = Integer.parseInt(startTime.split(":")[0]); // String[]
			//String{14, 00}
			//String[0]
			  
			  
			List<Attendance> attdArr = attendanceRepository.findAll();
			for (int i = 0; i < attdArr.size(); i++) {
				if (sdf.format(attdArr.get(i).getDate()).equals(dateStr) && attdArr.get(i).getUser().getId()==user.getId()) {
					if(removeFirstChar(startTime).equals("9:00") || removeFirstChar(endTime).equals("18:00")) {
						model.addAttribute("stat", "attendanceError");
						return "error";
					}	
				}
			}
			
			attendance.setDate(date);
			attendance.setStartTime(removeFirstChar(startTime));
			attendance.setEndTime(removeFirstChar(endTime));
			attendance.setWorkHours(removeFirstChar(workHours));
			attendance.setDivision(division);
			attendance.setPlace(place);
			attendance.setProject(project);
			attendance.setRemarks(remarks);
			attendance.setUser(user);
			attendanceRepository.save(attendance);
			model.addAttribute("attendance");
			return "attendanceMessage";
		}
	
	//　時刻の入力形式変更 ex) 09:00 >> 9:00
	private String removeFirstChar (String str) {
		if(str.startsWith("0")) {
			str = str.substring(1);
		}		
		return str;
	}
	
}
