package com.sfdc.qa.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sfdc.qa.base.TestBase;
import com.sfdc.qa.pages.AccountsPage;
import com.sfdc.qa.pages.HomePage;
import com.sfdc.qa.pages.LoginPage;
import com.sfdc.qa.utils.TestUtil;

public class AccountsTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	AccountsPage accountsPage;
	TestUtil testUtil;
	String sheetName = "Accounts";
	//public static String accountTestScreenshotFolder;
	
	public AccountsTest() {
		super();
		System.out.println("--############ AccountsTest() Super #############--- ");
	}
	
	@BeforeClass
	public void setup(){
		System.out.println("--****************** Accounts Test Cases starts ******************---");
		accountTestScreenshotFolder =screenShotFolder +"\\AccountsTest-"+getSystemDate();
		File file = new File(accountTestScreenshotFolder);
		file.mkdir();
		initalization();
		logger = Logger.getLogger(AccountsTest.class);
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.loginSFDC();
	}
	
	@BeforeTest
	public void beforeTest(){
		System.out.println("==================== Before test in AccountsTest ===============================");
	}
	
	@BeforeMethod
	public void testBaseline(){
		System.out.println("-------------- AccountsTest/testBaseline() starts -----------------");
		homePage.clickOnHomeTab();
		accountsPage = homePage.clickOnAccountsTab();
		System.out.println("-------------- AccountsTest/testBaseline() end -----------------");
	}
	
	@Test(priority = 1)
	public void clickOnAllAccounts(){
		System.out.println("---------------- AccountsTest/clickOnAllAccounts starts -----------------");
		accountsPage.selectAllAccounts();
		System.out.println(accountsPage.getSystemDate());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("---------------- AccountsTest/clickOnAllAccounts end -----------------");
	}
	
	@DataProvider
	public Object[][] getNewAccountData(){
		Object[][] data =  TestUtil.getSheetData(sheetName);
		return data;
	}
	
	@Test(priority=2, dependsOnMethods="clickOnAllAccounts", dataProvider="getNewAccountData")
	public void createNewAccounts(String accountName, String phoneNo, String type,String employeeNos, String industry){
		System.out.println("------------------- AccountsTest/createNewAccounts() starts --------------------");
		accountsPage.selectAllAccounts();
		accountsPage.clickOnNewButton();
		accountsPage.enterNewAccountDetails(accountName, phoneNo, type, employeeNos,industry);
		System.out.println("Screenshot location :-->> " + testUtil.captureScreenShot(accountTestScreenshotFolder, accountName));
		System.out.println("------------------- AccountsTest/createNewAccounts() end --------------------");
	}
	
	@AfterTest
	public void teardown(){
		driver.close();
		driver.quit();
		System.out.println("--****************** Account Test Cases end******************---");
	}

}
