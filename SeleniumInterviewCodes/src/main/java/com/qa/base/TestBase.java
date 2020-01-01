package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {
	
	public String userdir;
	public Properties properties;
	public String chromeDriverPath;
	public String browser;
	public WebDriver driver;
	public WebDriverWait wait;
	public String currentDate;

	public TestBase() {
		super();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		currentDate = formatter.format(date).replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "-");
		userdir = System.getProperty("user.dir");
		properties = new Properties();
		try {
			properties.load(new FileInputStream(userdir+"\\src\\main\\java\\com\\qa\\config\\config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		chromeDriverPath = userdir+"\\chromedriver.exe";
		System.out.println("ChromeDriver path :--->>> " + chromeDriverPath);
	}
	
	
	public void browserSetup(){
		System.out.println("####----------- Setting up browser in TestBase/browserSetup() --------######");
		browser = properties.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		}
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	

}
