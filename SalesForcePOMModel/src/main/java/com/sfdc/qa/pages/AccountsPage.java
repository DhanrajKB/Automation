package com.sfdc.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sfdc.qa.base.TestBase;
import com.sfdc.qa.utils.TestUtil;

public class AccountsPage extends TestBase {

	public LoginPage loginPage;
	public HomePage homePage;
	public TestUtil testUtil;

	//------ web objects
	@FindBy(xpath ="//a[@title='Select List View']")
	WebElement viewAccountsDropdown;
	@FindBy(xpath="//a[@role='option']/span[text()='All Accounts']")
	WebElement allAccountsInDropDown;
	@FindBy(xpath = "//a[@role='button']/div[text()='New']//parent::a")
	WebElement newBtn;
	@FindBy(xpath = "//label/span[text()='Account Name']//parent::label//following-sibling::div//child::input")
	WebElement accountNameTxt;
	@FindBy(xpath = "//span/span[text()='Type']//parent::span//following-sibling::div")
	WebElement typeDropdownInNewAccounts;
	@FindBy(xpath="//li[@role='presentation']/a[@title='Customer']//ancestor::ul")
	WebElement CustomerType_List; 
	@FindBy(xpath="//li[@role='presentation']/a[@title='Banking']//ancestor::ul")
	WebElement IndustryType_List;
	@FindBy(xpath = "//div[@class='select-options']//li[@role='presentation']/a[text()='Customer']")
	WebElement customer_Type;
	@FindBy(xpath = "//span/span[text()='Industry']//parent::span//following-sibling::div//child::a")
	WebElement industryDropdownInNewAccounts;
	@FindBy(xpath = "//div[@class='select-options']//li[@role='presentation']/a[text()='Chemicals']")
	WebElement chemicals_Industry;
	@FindBy(xpath = "//label/span[text()='Phone']//parent::label//following-sibling::input")
	WebElement phoneTxt;
	@FindBy(xpath = "//span[text()='Employees']//parent::label//following-sibling::input")
	WebElement employeesTxt;
	@FindBy(xpath = "//button[@title='Save']/span[text()='Save']")
	WebElement saveBtn;
	@FindBy(xpath = "//button[@title='Cancel']/span[text()='Cancel']")
	WebElement cancelBtn;



	// ----------- Constructor 
	/* Method 			: AccountsPage constructor
	 * Functionality	: Setup basic features and initialise webelements 
	 */
	public AccountsPage() {
		//super();
		PageFactory.initElements(driver, this);
	}

	/* Method 			: selectAllAccounts
	 * Functionality	: select the ALL Accounts from the AccountsList dropdown
	 * Input parameter	: none
	 * return			: none
	 */
	public void selectAllAccounts(){
		System.out.println("----------- AccountsPage/selectAllAcounts starts -------------");
		try {
			viewAccountsDropdown.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			allAccountsInDropDown.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("----------- AccountsPage/selectAllAcounts end -------------");
	}

	/* Method 			: clickOnNewButton
	 * Functionality	: click on New button on Accounts page
	 * input parameter	: 
	 * return			: none
	 */
	public void clickOnNewButton(){
		System.out.println("---------- AccountPage/clickOnNewButton starts ---------------");
		try {
			Thread.sleep(3000);
//			try {
//				wait.until(ExpectedConditions.elementToBeClickable(newBtn));
//			} catch (Exception e) {
//				e.printStackTrace();
//				selectAllAccounts();
//				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//			}
			newBtn.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------- AccountPage/clickOnNewButton end ---------------");
	}
	

	public void enterNewAccountDetails(String accountName, String phoneNo, String type, String employessNos, String industry){
		System.out.println("---------- AccountPage/enterNewAccountDetails starts ---------------");
		try {
//			selectAllAccounts();
//			clickOnNewButton();
			String account = accountName+ "-" + getSystemDate();
			accountNameTxt.sendKeys(account);
			Thread.sleep(2000);
			typeDropdownInNewAccounts.click();
			try {
				CustomerType_List.findElement(By.linkText(type)).click();
			} catch (Exception e) {
				CustomerType_List.findElement(By.linkText("Customer")).click();
				e.printStackTrace();
			}
			industryDropdownInNewAccounts.click();
			try {
				IndustryType_List.findElement(By.linkText(industry)).click();
			} catch (Exception e) {
				IndustryType_List.findElement(By.linkText("Communications")).click();
				e.printStackTrace();
			}
			
			//chemicals_Industry.click();
			phoneTxt.sendKeys(phoneNo);
			employeesTxt.sendKeys(employessNos);
			testUtil.captureScreenShot(accountTestScreenshotFolder, "entryDetailsOfAccount - "+account);
			saveBtn.click();
			Thread.sleep(2000);
			System.out.println("Created account successfully :-->> " + account);
		} catch (Exception e) {
			cancelBtn.click();
			System.out.println("Account creation failed");
			e.printStackTrace();
		}
		System.out.println("---------- AccountPage/enterNewAccountDetails end ---------------");
	}




}
