package com.reality.form;

import java.util.ArrayList;
import java.util.Map;

public class DailyReportForm {
	
	// やったこと
	private DoneThings doneThings;
	// 所感
	private String reflection;
	
	private ArrayList<DoneThings> doneThingsList = new ArrayList<>();
	
	public DailyReportForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DailyReportForm(ArrayList<DoneThings> doneThingsList, String reflection) {
		super();
		this.doneThingsList = doneThingsList;
		this.reflection = reflection;
	}

	public DoneThings getDoneThings() {
		return doneThings;
	}
	public void setDoneThings(DoneThings doneThings) {
		this.doneThings = doneThings;
		setDoneThingsList(doneThings);
	}
	public ArrayList<DoneThings> getDoneThingsList() {
		return doneThingsList;
	}
	public void setDoneThingsList(ArrayList<DoneThings> doneThingsList) {
		this.doneThingsList = doneThingsList;
	}
	public void setDoneThingsList(DoneThings doneThings) {
		this.doneThingsList.add(doneThings);
	}
	public String getReflection() {
		return reflection;
	}

	public void setReflection(String reflection) {
		this.reflection = reflection;
	}
	
}
