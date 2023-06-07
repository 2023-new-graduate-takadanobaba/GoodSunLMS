package com.reality.util;

import java.util.Map;

public class DailyReportForm {
	
	// やったこと
	// <やったこと, 〇△×>
	private Map<String, String> doneThings;
	// 所感
	private String reflection;
	
	public DailyReportForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DailyReportForm(Map<String, String> doneThings, String reflection) {
		super();
		this.doneThings = doneThings;
		this.reflection = reflection;
	}
	
	public Map<String, String> getDoneThings() {
		return doneThings;
	}
	public void setDoneThings(Map<String, String> doneThings) {
		this.doneThings = doneThings;
	}
	public String getReflection() {
		return reflection;
	}
	public void setReflection(String reflection) {
		this.reflection = reflection;
	}
	
	
	
}
