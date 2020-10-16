package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AccountsPage;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LeadsPage;
import com.qa.pages.LoginPage;
import com.qa.pages.OpptyPage;
import com.qa.utils.TestUtils;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	AccountsPage accountsPage;
	ContactsPage contactsPage;
	LeadsPage leadsPage;
	OpptyPage opptyPage;
	TestUtils testUtils;
	
	@BeforeClass
	public void setup(){
		System.out.println("setup called in HomePageTest");
		initilization();
		loginPage = new LoginPage();
		homePage = loginPage.loginToSFDC();
	}
	
	@AfterClass
	public void teardown(){
		System.out.println("teardown called in HomePageTest");
		driver.close();
		driver.quit();
	}
	
//	@Test(priority=1)
//	public void loginSFDC(){
//		homePage = loginPage.loginToSFDC();
//	}
	
//	@Test(priority=2)
	public void createNewAccount(){
		System.out.println("HomePageTest->createNewAccount Test getting executed");
		if(homePage.validateHomePage()) {
			accountsPage = homePage.navigateToAccountsPage();
			accountsPage.clickAccountListDropDown();
			accountsPage.selectAllAccountsInList();
			accountsPage.clickOnNewAccountBtn();
			accountsPage.enterNewAccountName("DhanrajKB");
		}else{
			Assert.fail();
		}
//		accountsPage = homePage.navigateToAccountsPage();
//		accountsPage.clickAccountListDropDown();
//		accountsPage.selectAllAccountsInList();
//		accountsPage.clickOnNewAccountBtn();
//		accountsPage.inputNewAccountName("DhanrajKB");
	}
	
	@Test(priority=3)
	public void searchAccount(){
		System.out.println("HomePageTest->searchAccount Test getting executed");
		try{
			homePage.universalSearch("Accounts", "Agasthya Prop");
		}catch(Exception e){
			e.printStackTrace();
			TestUtils.captureScreenShot("searchAccountError");
		}
	}
	

}
