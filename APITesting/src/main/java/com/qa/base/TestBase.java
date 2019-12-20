package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	public String userdir;
	
	public TestBase() {
		userdir = System.getProperty("user.dir");
		prop = new Properties();
		try {
			prop.load(new FileInputStream(
					new File(userdir+"\\src\\main\\java\\com\\qa\\config\\config.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
