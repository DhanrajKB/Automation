package com.sfdc.qa.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.openqa.jetty.html.Script;
import org.testng.annotations.Test;

import com.sfdc.qa.base.TestBase;

public class FirstScriptToExecute extends TestBase {
	
	public String screenShotPath;
	
	public FirstScriptToExecute() {
		super();
	}
	
	@Test(priority = 0)
	public void setup(){
//		String scrfolder = userdir+"\\ScreenShots";
//		try {
//			FileUtils.cleanDirectory(new File(scrfolder));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		screenShotPath = userdir + "\\ScreenShots\\TestExecution@" + getSystemDate();
		try {
			PropertiesConfiguration updateProp = new PropertiesConfiguration(userdir 
					+ "\\src\\main\\java\\com\\sfdc\\qa\\config\\config.properties");
			updateProp.setProperty("screenshotpath", userdir+"\\ScreenShots\\TestExecution@" + getSystemDate());
			updateProp.save();
		} catch (ConfigurationException e1) {
			e1.printStackTrace();
		}
	}

}
