package com.sfdc.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Factory;

import com.sfdc.qa.base.TestBase;
import com.sfdc.qa.utils.TestUtil;

public class LoginPage extends TestBase {
	
	HomePage homePage;
	TestUtil testUtil;
	//----------------- webobjects
	@FindBy(id="username")
	WebElement usernameTxt;
	
	@FindBy(id="password")
	WebElement passwordTxt;
	
	@FindBy(id="Login")
	WebElement loginBtn;
	
	
	///---------------------- Actions / methods
	
	/* Method 			: LoginPage constructor
	 * Functionality	: Setup basic features here
	 */
	public LoginPage(){
		//super();
		//testUtil = new TestUtil();
		System.out.println("-----------------  LoginPage starts -------------------------");
		PageFactory.initElements(driver, this);
	}
	
	
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	/* Method 			: validatePage
	 * Functionality	: validates the login page with elements
	 * return			: none
	 */
	public void validatePage(){
		System.out.println("-----------------  LoginPage/ValidatePage() starts -----------------------");
		Assert.assertEquals(driver.getTitle(), "Login | Salesforce","Login Page Title not matched");
		wait.until(ExpectedConditions.visibilityOf(usernameTxt));
		wait.until(ExpectedConditions.visibilityOf(passwordTxt));
		wait.until(ExpectedConditions.visibilityOf(loginBtn));
		System.out.println("Title of the login page :- " + driver.getTitle());
		System.out.println("-----------------  LoginPage/ValidatePage() ends -----------------------");
	}
	
	/* Method 			: loginSFDC
	 * Functionality	: Login to SFDC with username/password
	 * return			: Homepage object
	 */
	
	public HomePage loginSFDC(){
		usernameTxt.sendKeys(properties.getProperty("username"));
		passwordTxt.sendKeys(properties.getProperty("password"));
		loginBtn.click();
		return new HomePage();
	}
	
	
}
