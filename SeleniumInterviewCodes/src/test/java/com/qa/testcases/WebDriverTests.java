package com.qa.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.libs.LoginPageLibs;
import com.qa.utilites.WebUtils;

public class WebDriverTests extends TestBase {
	
//	public WebDriver driver;
	public String url;
	public String searchString;
	public WebUtils webUtils;
	public LoginPageLibs LoginPageLibs;
	
	@BeforeTest
	public void setup(){
		System.out.println("####----------- WebDriverTests Test Execution starts --------######");
		webUtils = new WebUtils();
		LoginPageLibs = new LoginPageLibs();
		browserSetup();
		LoginPageLibs.openurl(driver);
		searchString = "new year";
//		browser = properties.getProperty("browser");
//		if (browser.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//			driver = new ChromeDriver();
//		}
//		wait = new WebDriverWait(driver, 30);
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		searchString = "new year";
//		url = properties.getProperty("url");
//		System.out.println(url);
//		driver.get(url);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void waitForPage(){
		System.out.println("------------ TestCase for test page uploaded or not -------------");
		if (driver.findElement(By.name("q")).isEnabled()) {
			System.out.println("The page loaded successfully");
		}
	}
	
	@Test(dependsOnMethods="waitForPage")
	public void searchSuggestions(){
		System.out.println("--------------- TC:==>>> searchSuggestions starts -------------------");
		driver.findElement(By.name("q")).sendKeys(searchString);
//		List<WebElement> suggestLists = driver.findElement(By.xpath("//ul[@role='listbox']")).findElements(By.xpath("//li[@role='presentation']"));
		List<WebElement> suggestLists = driver.findElements(By.xpath("//ul[@role='listbox']/li[@role='presentation']"));

		//ul[@role='listbox']/li[@role='presentation']
		System.out.println("elements list :---->>>> " + suggestLists.size());
		for (WebElement ele:suggestLists) {
			System.out.println(ele.getText());
		}
		WebElement eleScreen = driver.findElement(By.xpath("//ul[@role='listbox']"));
		webUtils.takeEntireScreenShot(driver,"mainPage");
		webUtils.takeElementScreenShot(driver, driver.findElement(By.xpath("//div[@id='hplogo']/a/img")),"dayLogo");
		webUtils.takeElementScreenShot(driver, eleScreen,"searchElement");
	}
	
	
	@AfterTest
	public void teardown(){
		driver.close();
		driver.quit();
		System.out.println("####----------- WebDriverTests Test Execution completed --------######");
	}

}
