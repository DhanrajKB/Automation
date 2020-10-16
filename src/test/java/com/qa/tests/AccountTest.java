package com.qa.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AccountsPage;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utils.TestUtils;
/**
 * @author dhanraj
 * @functionality Account Test cases
 */
public class AccountTest extends TestBase {
	
	LoginPage loginPage;
	AccountsPage accountsPage;
	ContactsPage contactsPage;
	HomePage homePage;
//	TestUtils testUtils;
	
	@BeforeClass
	public void setup(){
		System.out.println("setup called in AccountTest");
		initilization();
		loginPage = new LoginPage();
		homePage = loginPage.loginToSFDC();
	}
	
	@AfterClass
	public void teardown(){
		System.out.println("teardown called in AccountTest");
		driver.close();
		driver.quit();
	}
	
	@Test(priority=1)
	public void validateLogin(){
		System.out.println("AccountTest->validateLogin Test getting executed");
		try{
			accountsPage = homePage.navigateToAccountsPage();
			TestUtils.captureScreenShot("Login_Success");
		}catch(Exception e){
			TestUtils.captureScreenShot("Login_Failed");
		}
		
	}
	
//	@Test(priority=2)
	public void createAccount(){
		System.out.println("AccountTest->createAccount Test getting executed");
		try{
			accountsPage.clickAccountListDropDown();
			accountsPage.selectAllAccountsInList();
			accountsPage.clickOnNewAccountBtn();
			accountsPage.enterNewAccountName("Agasthya Sports");
			accountsPage.clickOnType();
			accountsPage.selectType("Investor");
			accountsPage.enterPhoneNumber("9611388003");
			accountsPage.clickOnIndustry();
			accountsPage.selectIndustryFromList("Chemicals");
			accountsPage.clickOnSaveBtn();
			TestUtils.captureScreenShot("create_Account_Success");
		}catch(Exception e){
			TestUtils.captureScreenShot("create_Account_Failed");
		}
	}
	
	String accountName = "Agasthya Constructions";
	String customerType = "Investor";
	String phoneNo = "9611355899";
	String industryType = "Chemicals";
	String noOfEmployees = "65465";
	
	@DataProvider
	public Object[][] getAccountData(){
		Object data[][] = TestUtils.getTestData("Accounts");
		return data;
	}
	
	@Test(priority=2,dataProvider="getAccountData")
	public void createAccountUsingDataProvider(String accountName,String customerType,String phoneNo,
			String industryType,String noOfEmployees){
		System.out.println("AccountTest->createAccountUsingDataProvider Test getting executed");
		try{
			accountsPage.createNewAccount(accountName, customerType, phoneNo, industryType, noOfEmployees);
			TestUtils.captureScreenShot("create_Account_Success");
		}catch(Exception e){
			e.printStackTrace();
			TestUtils.captureScreenShot("create_Account_Failed");
		}
	}
	
	@Test(priority=3)
	public void searchAccount(){
		System.out.println("AccountTest->searchAccount Test getting executed");
		WebElement accSearchResult = accountsPage.searchForAccounts("Agasthya");
		if (accSearchResult!=null) {
			System.out.println("Searched account name :-- " + accSearchResult.getAttribute("title"));
		}else {
			Assert.fail("Account search for '" + "Agasthya" + "' is failed");
			System.out.println("Account search for '" + "Agasthya" + "' is failed");
		}
	}
	
	@Test(priority=4)
	public void createContact(){
		System.out.println("AccountTest->createContact Test getting executed");
		try{
			accountsPage.clickOnNewContactBtn();
			accountsPage.enterContactDetails("Appu", "Bhangari", "8971746338", "dhanraj.taurus@gmail.com");
			TestUtils.captureScreenShot("Contact_Creation_Success");
		}catch(Exception e){
			e.printStackTrace();
			TestUtils.captureScreenShot("Contact_Creation_Failed");
		}
	}
	
	
	
	
	
	
}
