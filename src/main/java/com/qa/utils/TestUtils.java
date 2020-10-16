package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.base.TestBase;

public class TestUtils extends TestBase {
	
	public static String testDataPath;
	public static Workbook workbook;
	public static Sheet sheet;
	
	public static void captureScreenShot(String fileName){
//		System.currentTimeMillis();
		File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			System.out.println("Userdir :-- " + userdir);
			FileUtils.copyFile(scrShot, new File(userdir+"//screenshots//"+fileName+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object[][] getTestData(String sheetName){
		String testDataPath = userdir + "\\src\\main\\java\\com\\qa\\testdata\\NewAccounts.xlsx";
//		String testDataPath = "D:\\workspace\\SFDCv02\\src\\main\\java\\com\\qa\\testdata\\NewAccounts.xlsx";
//		testDataPath = prop.getProperty("testDataPath");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(testDataPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++){
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}		
		return data;
	}

}
