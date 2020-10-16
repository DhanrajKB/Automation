package com.qa.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AccountsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class ForTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	AccountsPage accountPage;
	@FindBy(xpath="//table[@data-aura-class='uiVirtualDataTable']")
	WebElement accountsTable;
	
	@BeforeClass
	public void setup(){
		initilization();
		loginPage = new LoginPage();
		homePage = loginPage.loginToSFDC();
		PageFactory.initElements(driver, this);
	}
	
	@AfterClass
	public void teardown(){
		driver.close();
		driver.quit();
	}
	
	@Test
	public void testxpath(){
		accountPage = homePage.navigateToAccountsPage();
		accountPage.clickAccountListDropDown();
		accountPage.selectAllAccountsInList();
		System.out.println(accountsTable.getAttribute("class"));
		List<WebElement> ths = accountsTable.findElements(By.tagName("th"));
		System.out.println("ths.size() :-->> " + ths.size());
		for(WebElement th:ths){
			String ariaLabel = th.getAttribute("aria-label");
			System.out.println(ariaLabel);
		}
		
	}

	
	
	
}
