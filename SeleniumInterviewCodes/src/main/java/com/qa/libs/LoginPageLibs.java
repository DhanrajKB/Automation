package com.qa.libs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.utilites.WebUtils;

public class LoginPageLibs extends TestBase {
	
	public String url;
	public String searchString;
	public WebUtils webUtils;
	
	
	public LoginPageLibs() {
		webUtils = new WebUtils();
	}
	
	
	public void openurl(WebDriver driver){
		url = properties.getProperty("url");
		System.out.println(url);
		driver.get(url);
	}

}
