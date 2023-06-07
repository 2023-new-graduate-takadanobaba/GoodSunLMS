package com.reality.form;

import java.util.ArrayList;
import java.util.Map;

public class DailyReportForm {
	
	// やったこと
	private ArrayList<DoneThings> doneThings;
	// 所感
	private String reflection;
	
	public DailyReportForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DailyReportForm(ArrayList<DoneThings> doneThings, String reflection) {
		super();
		this.doneThings = doneThings;
		this.reflection = reflection;
	}

	public ArrayList<DoneThings> getDoneThings() {
		return doneThings;
	}

	public void setDoneThings(ArrayList<DoneThings> doneThings) {
		this.doneThings = doneThings;
	}

	public String getReflection() {
		return reflection;
	}

	public void setReflection(String reflection) {
		this.reflection = reflection;
	}
	
}
