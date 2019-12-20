package com.sfdc.qa.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sfdc.qa.base.TestBase;
import com.sfdc.qa.pages.HomePage;
import com.sfdc.qa.pages.LoginPage;
import com.sfdc.qa.utils.TestUtil;

public class LoginTest extends TestBase {
	
	public LoginPage loginPage;
	public HomePage homePage;
	public TestUtil testUtil;
	
	public LoginTest(){
		super();
		System.out.println("--############ LoignTest() Super #############--- ");
	}
	
	@BeforeClass
	public void setup(){
		System.out.println("--****************** Login Test Cases starts ******************---");
		System.out.println("-------------- LoginTest/setup() starts -----------------");
		initalization();
		logger = Logger.getLogger(LoginTest.class);
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		loginPage.validatePage();
		System.out.println("-------------- LoginTest/setup() end -----------------");
	}
	
	@BeforeTest
	public void beforeTest(){
		System.out.println("==================== Before test in LoginTest ===============================");
	}
	
	@Test(priority = 0)
	public void loginPageTitleValidation(){
		System.out.println("-------------- LoginTest/loginPageTitleValidation() starts -----------------");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Login | Salesforce");
		System.out.println("-------------- LoginTest/loginPageTitleValidation() end -----------------");
	}
	
	@Test(priority=1)
	public void loginToSFDC(){
		System.out.println("-------------- LoginTest/loginToSFDC() starts -----------------");
		homePage = loginPage.loginSFDC();
		System.out.println("-------------- LoginTest/loginToSFDC() end -----------------");
	}
	
	@Test(priority=2, dependsOnMethods="loginToSFDC")
	public void tabValidation(){
		System.out.println("-------------- LoginTest/tabValidation() starts -----------------");
		//loginToSFDC();
		homePage.validateTabs();
		System.out.println("-------------- LoginTest/tabValidation() end -----------------");
	}
	
	
	@AfterClass
	public void teardown(){
		System.out.println("-------------- LoginTest/teardown() starts -----------------");
		driver.close();
		driver.quit();
		System.out.println("-------------- LoginTest/teardown() end -----------------");
		System.out.println("--****************** Login Test Cases end ******************---");
	}
}
