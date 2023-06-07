package com.reality.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.reality.form.DailyReportForm;

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
	
	private void buildExcel() throws IOException {
		// excel生成
		
		// template利用
		String templatePath = "/Excel_template.xlsx";
		InputStream tmpFile = this.getClass().getResourceAsStream(templatePath);

		wb = new HSSFWorkbook(tmpFile);
		ws = wb.getSheetAt(0);
		
		// Cell処理...
		int row_pos = 2;
		
		
	}
	
	private void doExcel(DailyReportForm drf) throws IOException {
		// form情報整え
		
		
		
	}
	
	private void setValue(int row_pos, int col_pos, Object value) throws Exception {
		// create and set cell
		if (ws.getRow(row_pos) == null) {
			ws.createRow(row_pos);
		}
		if (ws.getRow(row_pos).getCell(col_pos) == null) {
			HSSFCell newCell = ws.getRow(row_pos).createCell(col_pos);
			try {
				newCell.setCellStyle(ws.getRow(2).getCell(1).getCellStyle());
			} catch (Exception ex) {
				String exm = ex.getMessage();
				System.out.println(exm);
				System.out.println(ex.getStackTrace());
			}
		}

		// if NULL
		if (value == null) {
			System.err.println("Value is null");
			return;
		}
		String className = value.getClass().getName();
//		System.out.println(value + " is " + className);

		if (className == "java.lang.String") {
			ws.getRow(row_pos).getCell(col_pos).setCellValue((String) value);
		} else {
			throw new Exception("Cell format not supported: " + className);
		}		
	}
}
