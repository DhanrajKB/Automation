package com.sfdc.qa.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sfdc.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static Workbook workbook;
	public static Sheet sheet;
	
	public static String TESTDATA_SHEET_PATH = "D:\\workspace\\SalesForcePOMModel\\src\\main\\java\\com\\sfdc\\qa\\testdata\\TestData.xlsx";
	
	public void switchTOFrame(String frameName){
		driver.switchTo().frame(frameName);
	}
	
	
	public static Object[][] getSheetData(String sheetName){
		BufferedInputStream file = null;
		try {
			file = new BufferedInputStream(new FileInputStream(TESTDATA_SHEET_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			workbook = WorkbookFactory.create(file);
			sheet = workbook.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("sheet.getLastRowNum() :-->> " + sheet.getLastRowNum());
		System.out.println("sheet.getRow(0).getLastCellNum() :-->> " + sheet.getRow(0).getLastCellNum());
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
	
	
	public static String captureScreenShot(String folder,String filename){
		String filepath = folder+"\\"+filename+getSystemDate()+".jpg";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filepath;
	}
	
	
	
}
