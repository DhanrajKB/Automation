package com.qa.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
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
import com.qa.pages.LoginPage;

public class ContactsTest extends TestBase {
	
	LoginPage loginPage;
	AccountsPage accountsPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeClass
	public void setup(){
		System.out.println("setup called in ContactsTest");
		initilization();
		loginPage = new LoginPage();
		homePage = loginPage.loginToSFDC();
	}
	
	@AfterClass
	public void teardown(){
		System.out.println("teardown called in ContactsTest");
		driver.close();
		driver.quit();
	}
	
	@Test(priority=1)
	public void goToContactsPage(){
		System.out.println("ContactsTest->navigationContact Test getting executed");
		contactsPage = homePage.navigateToContactsPage();
	}
	
//	@Test(dependsOnMethods="goToContactsPage")
	public void createContact(){
		System.out.println("ContactsTest->createContact Test getting executed");
		contactsPage.allContactsSelect();
		contactsPage.createContact("Agastya", "Dhanraj", "5446545458", "agasthyadhanraj@gmail.com");
	}
	
	@Test(dependsOnMethods="goToContactsPage")
	public void searchContact(){
		System.out.println("ContactsTest->searchContact Test getting executed");
		contactsPage.allContactsSelect();
		WebElement cont = contactsPage.searchForContact("Agasthya");
		if(cont!=null){
			System.out.println("contact searched :- " + cont.getText());
		}else{
			System.out.println("Contact search failed");
			Assert.fail("Contact Search failed");	
		}
	}
	


}
