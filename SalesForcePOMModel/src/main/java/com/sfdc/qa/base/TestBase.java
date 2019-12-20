package com.sfdc.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.session.CapabilityTransform;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sfdc.qa.utils.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Properties properties;
	public static String userdir;
	public static WebDriverWait wait;
	public static String screenShotFolder;
	public static Logger logger;
	public static DesiredCapabilities dCap;
	
	// ----------- Page constants -----------------
	public static String accountTestScreenshotFolder;
	// --------------------------------------------
	
	/* Method 			: TestBase constructor
	 * Functionality	: Setup basic features here
	 */
	public TestBase(){
		System.out.println("----------------  TestBase() starts --------------------------");
		userdir = System.getProperty("user.dir");
		System.out.println("userdir :--- "+userdir);
//		screenShotFolder = userdir+"\\ScreenShots\\TestExecution@" + getSystemDate();
/*
		//-------------- update the screenshot folder in config.properties
		Properties propUpdate = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(userdir + "\\src\\main\\java\\com\\sfdc\\qa\\config\\test.properties"));
			propUpdate.load(fis);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String scrshotpath = propUpdate.getProperty("screenshotpath");
		System.out.println("test.properties path :-->>> " + userdir+
					"\\src\\main\\java\\com\\sfdc\\qa\\config\\test.properties");
		if (!scrshotpath.equalsIgnoreCase(screenShotFolder)) {
			File updatePropFile = new File(getClass().getClassLoader().getResource(userdir + "\\src\\main\\java\\com\\sfdc\\qa\\config\\test.properties").getFile());
			try {
				PropertiesConfiguration updateConfigProp = new PropertiesConfiguration(updatePropFile);
				updateConfigProp.setProperty("screenshotpath", "");
			} catch (ConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//------------------------------------------------

*/
		Properties prop1 = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(userdir 
						+ "\\src\\main\\java\\com\\sfdc\\qa\\config\\config.properties"));
			try {
				prop1.load(fis);
				System.out.println("screenshotfolder path in test.prop :---->>>" + prop1.getProperty("screenshotpath"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(
					new File(userdir + "/src/main/java/com/sfdc/qa/config/config.properties"));
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//----- create screenshot folder
		screenShotFolder = prop1.getProperty("screenshotpath");
		File scrShotFolder = new File(screenShotFolder);
//		if (!scrShotFolder.exists()) {
//			scrShotFolder.mkdir();
//		}
		scrShotFolder.mkdir();
		System.out.println("----------------  TestBase() end --------------------------");
	}
	
	/* Method			: initialization
	 * Functionality	: setup all the basic things required to start execution like driver, timeouts, browser setup etc....
	 * return 			: none
	 */
	public static void initalization(){
		System.out.println("------------------ TestBase/initalization() starts ----------------");
		if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", userdir+"//chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			dCap = DesiredCapabilities.chrome();
			dCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(options); 	
		}else if (properties.getProperty("firefox").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.gecko.driver", userdir+"//gecko.exe");
			driver = new FirefoxDriver();	
		}
		//*********************---------WebEvent Listener setup -------------***************************************
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		//*****************************************************************

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driver.get(properties.getProperty("url"));
		wait = new WebDriverWait(driver, 120);
		System.out.println("------------------ TestBase/initalization() end ----------------");
	}
	
	/* Method			: getSystemDate
	 * Functionality	: to fetch the System date in DDMMHHMMSS format
	 * return 			: date in String format
	 */
	public static String getSystemDate(){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String formatedDate = formatter.format(date).replace("-", "").replace(":", "").replace(" ", "-");
		return formatedDate;
	}

}
