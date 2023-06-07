package com.reality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reality.form.DailyReportForm;
import com.reality.util.Form2Excel;

import jakarta.servlet.http.HttpSession;

@Controller
public class SelectController {

	@GetMapping("/dailyReport")
	public String dailyReport(@ModelAttribute DailyReportForm dailyReportForm) {
		return "dailyReport";
	}
	
	@PostMapping("/doDailyReport")
	public String doDailyReport(@ModelAttribute DailyReportForm dailyReportForm, HttpSession session) throws Exception {
		Form2Excel excel = new Form2Excel();
		excel.runForm2Excel(dailyReportForm, session);
		session.setAttribute("done", true);
		return "redirect:/";
	}
	
	@GetMapping("/attendanceSystem")
	public String attendanceSystem() {
		return "attendanceSystem";
	}
}
