package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utils.TestUtils;
import com.qa.utils.WebEventListener;


public class TestBase {
	
	public static WebDriver driver;
	public static String userdir;
	public static Properties prop;
	public static String url;
	public static WebDriverWait wait;
	public static Actions actions;
	public static TestUtils testUtils;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){
		userdir = System.getProperty("user.dir");
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(userdir + "//src/main//java//com//qa//config//config.properties"));
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		url = prop.getProperty("url");
//		testUtils = new TestUtils();
		//-------- Below code kills any excel process running
		List<String> processToKill = new ArrayList<String>();
		processToKill.add("NewAccounts.xlsx - Excel");
		processToKill.add("Expense Sheet.xlsx - Excel");
		for (String processName : processToKill) {
			try {
				Runtime runtime = Runtime.getRuntime();
				System.out.println("Opearting System :- "+ System.getProperty("os.name"));
				if (System.getProperty("os.name").toLowerCase().contains("windows")) {
					System.out.println("Inside if");
//					runtime.exec("taskkill /t /f /im "+processName);
					//runtime.exec("taskkill /T /F /PID " + Long.parseLong(processName)); // Supply the PID value as string
		            runtime.exec("taskkill /F /FI \"WINDOWTITLE eq " + processName + "\" /T"); // Supply the window title bar text.
		            // If you want to kill only a single instance of the 
		            // named process then get its PID value and use:
		            // "taskkill /T /F /PID PID_Value"  OR you can provide
		            // the window title and use:
		            // "taskkill /F /FI \"WINDOWTITLE eq " + processName + "\" /T"
				}
				runtime.freeMemory();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void initilization(){
		System.setProperty("webdriver.chrome.driver", userdir+"//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		options.addArguments("--disable-notifications");
//		options.addArguments(
////				   "--verbose",
////				   "--headless",
//				   "--disable-web-security",
//				   "--ignore-certificate-errors",
//				   "--allow-running-insecure-content",
//				   "--allow-insecure-localhost"
////				   "--no-sandbox",
////				   "--disable-gpu"
//				  );
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(options);
		}
		
		//------- WebDriver Event listener
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		//--------------------------------------------
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get(url);
		
		wait = new WebDriverWait(driver, 120);
		actions = new Actions(driver);
	}

}
