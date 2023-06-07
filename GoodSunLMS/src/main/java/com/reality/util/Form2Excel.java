package com.reality.util;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Form2Excel {
	
	/*
	 * DailyReportForm Class
	 * 	- DoneThings
	 * 		- things
	 * 		- Completeness
	 * 	- String
	 * やったこと: Map<やったこと, 〇△×> ......String,String
	 * 所感......String
	 * 
	 */
	
	HSSFWorkbook wb;
	HSSFSheet ws;
	
	private ArrayList<DailyReportForm> dailyReportForms = new ArrayList<>();
	
	private void buildExcel() {
		// excel生成
	}
	
	private void doExcel() {
		// form情報整え
		
	}
}
