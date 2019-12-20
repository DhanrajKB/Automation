package com.sfdc.qa.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.sfdc.qa.base.TestBase;
import com.sfdc.qa.pages.HomePage;
import com.sfdc.qa.pages.LoginPage;
import com.sfdc.qa.pages.OpptiesPage;
import com.sfdc.qa.utils.TestUtil;

public class OpptiesTest extends TestBase {
	
	TestUtil testUtil;
	LoginPage loginPage;
	HomePage homePage;
	OpptiesPage opptiesPage;
	
	public OpptiesTest(){
		super();
		System.out.println("---################# OpprtiesTest super() #################---------");
	}
	
	@BeforeClass
	public void setup(){
		System.out.println("------------------ OpptiesTest/setup() starts ------------------");
		initalization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.loginSFDC();
		System.out.println("------------------ OpptiesTest/setup() end ------------------");
	}
	
	@BeforeTest
	public void beforeTess(){
		System.out.println("==================== Before test in OpptiesTest ===============================");
	}
	
}
