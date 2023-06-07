package com.reality.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.ui.Model;

import com.reality.form.DailyReportForm;

import jakarta.servlet.http.HttpSession;

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
	
	private DailyReportForm dailyReportForms = new DailyReportForm();
	
	public void runForm2Excel() {
		
	}
	
	
	private void buildExcel(HttpSession session) throws Exception {
		// excel生成
		
		// template利用
		String templatePath = "/Excel_template.xlsx";
		InputStream tmpFile = this.getClass().getResourceAsStream(templatePath);

		wb = new HSSFWorkbook(tmpFile);
		ws = wb.getSheetAt(0);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat fileSdf = new SimpleDateFormat("MMdd");
		
		// Cell処理...
		int row_pos = 4;
		int col_pos = 1;
		// 日付
		this.setValue(2, 1, sdf.format(new Date()));
		// 今やったこと
		for (int i = 0; i < dailyReportForms.getDoneThings().size(); i++) {
			// 1行15字
			this.setValue(row_pos, col_pos++, dailyReportForms.getDoneThings().get(i).getThings());
			this.setValue(row_pos, col_pos++, dailyReportForms.getDoneThings().get(i).getCompleteness());
			// 1行20字
			this.setValue(row_pos, col_pos++, dailyReportForms.getDoneThings().get(i).getImprovement());
			row_pos++;
		}
		// 所感 1行40字
		this.setValue(11, 1, dailyReportForms.getReflection());	
		
		// output
		
 		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String outputFilePath = desktopDir.getAbsolutePath();
		String outputFileName = fileSdf.format(new Date()) +"_" + session.getAttribute("username") + ".xlsx";
		Files.createDirectories(new File(outputFilePath).toPath());
		FileOutputStream stream = new FileOutputStream(outputFilePath + outputFileName);
		wb.write(stream);
		stream.close();

		wb.close();

		System.out.println("JOB_DONE");
	}
	
	private void doExcel(DailyReportForm drf) throws IOException {
		// form情報整え
		
		for (int i = 0; i < drf.getDoneThings().size(); i++) {
			// やったことある？と判断
			// ないだったら削除
			if (drf.getDoneThings().get(i).getThings()==null||drf.getDoneThings().get(i).getThings().isEmpty()) {
				drf.getDoneThings().remove(i);
				i--;
			}
			// 改行
			drf.getDoneThings().get(i).getThings().replaceAll("(.{15})", "\n");
			drf.getDoneThings().get(i).getImprovement().replaceAll("(.{20})", "\n");
		}
		
		drf.getReflection().replaceAll("(.{40})", "\n");
		
		dailyReportForms.setDoneThings(drf.getDoneThings());
		dailyReportForms.setReflection(drf.getReflection());
				
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
