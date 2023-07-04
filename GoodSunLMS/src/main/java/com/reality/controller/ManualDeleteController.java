package com.reality.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

	/**
	 * 登録した勤怠情報の削除画面の表示
	 */
	@GetMapping("/manualDelete")
	public String manualDelete(Model model, HttpSession session) {
		User user = userRepository.getReferenceById(Integer.parseInt(session.getAttribute("userId").toString()));
		model.addAttribute("attendance", attendanceRepository.findByUserOrderByDateAsc(user));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dateStr = sdf.format(date);
		session.setAttribute("date", dateStr);
		return "manualDelete";
	}
	
	/**
	 * 登録した勤怠情報の削除画面の表示（降順）
	 */
    @GetMapping("/findByDateDescManualDelete")
    public String findByDateDescManualDelete(Model model, HttpSession session) {
    	User user = userRepository.getReferenceById(Integer.parseInt(session.getAttribute("userId").toString()));
    	model.addAttribute("attendance", attendanceRepository.findByUserOrderByDateDesc(user));
    	return "manualDelete";
    }
    
	/**
	 * 登録した勤怠情報の削除画面の表示（昇順）
	 */
    @GetMapping("/findByDateAscManualDelete")
    public String findByDateAscManualDelete(Model model, HttpSession session) {
    	User user = userRepository.getReferenceById(Integer.parseInt(session.getAttribute("userId").toString()));
    	model.addAttribute("attendance", attendanceRepository.findByUserOrderByDateAsc(user));
    	return "manualDelete";
    }
	
	/**
	 * 登録した勤怠情報を月別に検索して表示
	 * 
	 * @param month 月
	 */
	@GetMapping("/findByMonthManualDelete")
    public String findByMonthManualDelete(String month, Model model, HttpSession session) {
        Integer monInt = Integer.parseInt(month.split("-")[1]);
        model.addAttribute("attendance", attendanceRepository.findByMMAndUserIdOrderByDateAsc(
                            monInt, Integer.parseInt(session.getAttribute("userId").toString())));
        return "manualDelete";
    }
	
	/**
	 * 登録した勤怠情報を削除
	 */
	@PostMapping("/doManualDeleteAjax")
	@Transactional(rollbackFor = Exception.class)
	public String doManualDelete1(Integer aId) throws ParseException {
		attendanceRepository.deleteById(aId);
		return "redirect:/manualDelete";
	}
}
