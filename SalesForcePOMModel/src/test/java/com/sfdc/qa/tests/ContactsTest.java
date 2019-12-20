package com.sfdc.qa.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.sfdc.qa.base.TestBase;
import com.sfdc.qa.pages.ContactsPage;
import com.sfdc.qa.pages.HomePage;
import com.sfdc.qa.pages.LoginPage;
import com.sfdc.qa.utils.TestUtil;

public class ContactsTest extends TestBase{
	
	TestUtil testUtil;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	public ContactsTest() {
		super();
		System.out.println("--############ ContactTest() Super #############--- ");
	}

	@BeforeClass
	public void setup(){
		System.out.println("--****************** Accounts Test Cases starts ******************---");
		initalization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.loginSFDC();
	}
	
	@BeforeTest
	public void beforeTess(){
		System.out.println("==================== Before test in ContactsTest ===============================");
	}
	
	@AfterClass
	public void teardown(){
		driver.close();
		driver.quit();
	}

}
