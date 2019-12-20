package com.sfdc.qa.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sfdc.qa.base.TestBase;

public class HomePage extends TestBase {
	
	AccountsPage accountPage;
	
	//--------- web objects 
	@FindBy(xpath="//a[@title='Quarterly Performance']")
	WebElement quarterlyPerformance;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='home']")
	WebElement homeTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Account']")
	WebElement accountsTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Contact']")
	WebElement contactsTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Lead']")
	WebElement leadsTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Opportunity']]")
	WebElement opptyTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Task']")
	WebElement tasksTab;
	
	
	

	/* Method 			: HomePage constructor
	 * Functionality	: Setup basic features and initialise webelements 
	 */
	public HomePage() {
		//super();
		System.out.println("-------------- HomePage() starts ---------------");
		PageFactory.initElements(driver, this);
		System.out.println("Homepage Title :- " + driver.getTitle());
		System.out.println("-------------- HomePage() end ---------------");
	}
	
	/* Method 			: validatePage
	 * Functionality	: validates the login page with elements
	 * return			: none
	 */
	public void validateHomePage(){
		System.out.println("-------------- HomePage/validateHomePage() starts ---------------");
		System.out.println("Homepage Title :- " + driver.getTitle());
		assertEquals(driver.getTitle(), "Home | Salesforce");
		wait.until(ExpectedConditions.elementToBeClickable(homeTab));
		System.out.println("-------------- HomePage/validateHomePage() end ---------------");
	}
	
	/* Method 			: validateTabs
	 * Functionality	: validates all the tabs
	 * return			: none
	 */
	public void validateTabs(){
		System.out.println("------------- HomePage/validateTabs() starts ---------------------");
		accountsTab.click();
		contactsTab.click();
		leadsTab.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//opptyTab.click();
		System.out.println("------------- HomePage/validateTabs() end ---------------------");
	}
	
	
	 public void clickOnHomeTab(){
		 System.out.println("-------------- HomePage/clickOnHomeTab() starts----------------- ");
		 wait.until(ExpectedConditions.elementToBeClickable(homeTab));
		 homeTab.click();
		 System.out.println("-------------- HomePage/clickOnHomeTab() ends----------------- ");
	 }
	 
	 
	/* Method 			: clickOnAccountsTab
	 * Functionality	: navigates to accounts tab
	 * return			: AccountPage
	 */
	public AccountsPage clickOnAccountsTab(){
		accountsTab.click();
		return new AccountsPage();
	}
	
	/* Method 			: clickOnContactsTab
	 * Functionality	: navigates to Contacts tab
	 * return			: ContactsPage
	 */
	public ContactsPage clickOnContactsTab(){
		contactsTab.click();
		return new ContactsPage();
	}
	

}
