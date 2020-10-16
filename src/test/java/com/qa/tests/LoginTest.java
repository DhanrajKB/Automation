package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeClass
	public void setup(){
		System.out.println("setup called in LoginTest");
		initilization();
		loginPage = new LoginPage();
	}
	
	@AfterClass
	public void teardown(){
		System.out.println("teardown test called in LoginTest");
		driver.close();
		driver.quit();
	}
	
	@Test(priority=1)
	public void checkForLoginPageAppeared(){
		System.out.println("LoginTest->checkForLoginPageAppeared Test getting executed");
		String loginTitle = loginPage.validateLoginPage();
		Assert.assertEquals(loginTitle, "Login | Salesforce");
		
	}
	@Test(priority=2)
	public void loginSfdc(){
		System.out.println("LoginTest->loginSfdc Test getting executed");
		homePage = loginPage.loginToSFDC();
	}

	
}
