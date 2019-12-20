package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;

public class TestBase {
	
	public String userdir;
	public Properties prop;
	public Logger logger;
	
	
	public TestBase() {
		userdir = System.getProperty("user.dir");
		prop = new Properties();
		try {
			prop.load(new FileInputStream(userdir + "//src//main//java/com//qa//config//config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger = Logger.getLogger("RestAPITestinForEmployeeDB");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
	
	

}
