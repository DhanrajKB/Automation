package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.TestBase;

import freemarker.cache.MruCacheStorage;

public class AccountsPage extends TestBase{

	//---- webelements or objects
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Account']")
	public WebElement accountsTab;
	@FindBy(xpath="//a[@title='Select List View']")
	WebElement accountsListDropdown;
	@FindBy(xpath="//div[contains(@class,'listContent')]//child::ul[@role='listbox']/li/a/span[text()='All Accounts']")
	WebElement allAccountsInListDropDown;
	@FindBy(xpath="//li[@class='slds-button slds-button--neutral']/a[@title='New']")
	WebElement btnNewInAccountPage;
	@FindBy(xpath="//label/span[text()='Account Name']//parent::label//following-sibling::div//child::input")
	WebElement txtAccountName;
	@FindBy(xpath="//*/span[@data-aura-class='uiPicklistLabel']/span[text()='Type']//parent::span//following-sibling::div")
	WebElement liType;
	@FindBy(xpath="//label/span[text()='Phone']/parent::label//following-sibling::input")
	WebElement txtPhone;
	@FindBy(xpath="//label/span[text()='Employees']/parent::label//following-sibling::input")
	WebElement txtEmployees;
	@FindBy(xpath="//*/span[@data-aura-class='uiPicklistLabel']/span[text()='Industry']//parent::span//following-sibling::div")
	WebElement liIndustry;
	@FindBy(xpath="//button[@title='Save']")
	WebElement btnSave;
	@FindBy(xpath="//button[@title='Cancel']")
	WebElement btnCancel;
	@FindBy(xpath="//*[@data-component-id='force_relatedListContainer']//child::div[@class='forceRelatedListContainer']/div[1]/div[1]//child::a[@title='New Contact']")
	WebElement btnNewContact;
	@FindBy(xpath="//table/thead//child::span[@title='Account Name']//ancestor::table")
	WebElement tableAccounts;
	
	//---- contact creation elements
	@FindBy(xpath="//a[@class='select']")
	WebElement liContactNameSaluatation;
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement txtContactFirstName;
	@FindBy(xpath="//input[@placeholder='Middle Name']")
	WebElement txtContactMiddleName;
	@FindBy(xpath="//input[@placeholder='Last Name']")
	WebElement txtContactLastName;
	@FindBy(xpath="//label/span[text()='Phone']//parent::label//following-sibling::input")
	WebElement txtContactPhNo;
	@FindBy(xpath="//label/span[text()='Email']//parent::label//following-sibling::input")
	WebElement txtContactEmail;
	@FindBy(xpath="//label/span[text()='Department']//parent::label//following-sibling::input")
	WebElement txtDepartment;
	@FindBy(xpath="//button[@title='Save']")
	WebElement btnContactSave;
	@FindBy(xpath="//button[@title='Cancel']")
	WebElement btnContactCancel;
	


	//---- initialize elements
	public AccountsPage(){
		PageFactory.initElements(driver, this);
	}

	//----- actions/methods
	public Boolean validateAccountsPage(){
		if (driver.getTitle().equalsIgnoreCase("Recently Viewed | Accounts | Salesforce")) {
			return true;
		}
		return false;
	}

	public void clickAccountListDropDown(){
		accountsListDropdown.click();
	}
	public void selectAllAccountsInList(){
		clickAccountListDropDown();
		allAccountsInListDropDown.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickOnNewAccountBtn(){
		btnNewInAccountPage.click();
		//		wait.until(ExpectedConditions.visibilityOf(txtAccountName));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void enterNewAccountName(String accountName){
		txtAccountName.sendKeys(accountName);
	}

	public void clickOnType(){
		liType.click();
	}

	public Boolean selectType(String typeOfCustomer){
		if(driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()){
			List<WebElement> types = driver.findElements(By.xpath("//*/ul[@role='presentation']/li"));
			for (WebElement ele : types) {
				if (ele.findElement(By.tagName("a")).getAttribute("title").equalsIgnoreCase(typeOfCustomer)) {
					ele.click();
					return true;
				}
			}
			return false;
		}else{
			clickOnType();
			if(driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()){
				List<WebElement> types = driver.findElements(By.xpath("//*/ul[@role='presentation']/li"));
				for (WebElement ele : types) {
					if (ele.findElement(By.tagName("a")).getAttribute("title").equalsIgnoreCase(typeOfCustomer)) {
						ele.click();
						return true;
					}
				}
				return false;
		}
		return false;
		}
	}
	
	public void enterPhoneNumber(String phoneNumber){
		txtPhone.sendKeys(phoneNumber);
	}
	
	public void enterNumberofEmployees(String noOfEmployees){
		txtEmployees.sendKeys(noOfEmployees);
	}
	
	public void clickOnIndustry(){
		liIndustry.click();
	}
	
	public Boolean selectIndustryFromList(String industry){
		if(driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()){
			List<WebElement> types = driver.findElements(By.xpath("//*/ul[@role='presentation']/li"));
			for (WebElement ele : types) {
				if (ele.findElement(By.tagName("a")).getAttribute("title").equalsIgnoreCase(industry)) {
					ele.click();
					return true;
				}
			}
			return false;
		}else{
			clickOnType();
			if(driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()){
				List<WebElement> types = driver.findElements(By.xpath("//*/ul[@role='presentation']/li"));
				for (WebElement ele : types) {
					if (ele.findElement(By.tagName("a")).getAttribute("title").equalsIgnoreCase(industry)) {
						ele.click();
						return true;
					}
				}
				return false;
		}
		return false;
		}
	}
	
	public void clickOnSaveBtn(){
		btnSave.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnCanceBtn(){
		btnCancel.click();
	}
	
	public void clickOnNewContactBtn(){
		wait.until(ExpectedConditions.elementToBeClickable(btnNewContact));
		btnNewContact.click();
	}
	
	public void enterContactFirstName(String contactFirstName){
		txtContactFirstName.sendKeys(contactFirstName);
	}
	
	public void enterContactLastName(String contactLastName){
		txtContactLastName.sendKeys(contactLastName);
	}
	
	public void enterContactPhoneNumber(String contactPhoneNo){
		txtContactPhNo.sendKeys(contactPhoneNo);
	}
	
	public void enterContactEmailId(String contactEmailId){
		txtContactEmail.sendKeys(contactEmailId);
	}
	
	public void clickOnContactSaveBtn(){
		btnContactSave.click();
	}
	
	public void clickOnContactCancelBtn(){
		btnContactCancel.click();
	}
	
	public void createNewAccount(String accountName,String customerType,String phoneNo,
			String industryType,String noOfEmployees){
		accountsTab.click();
		selectAllAccountsInList();
		clickOnNewAccountBtn();
		enterNewAccountName(accountName);
		selectType(customerType);
		enterPhoneNumber(phoneNo);
		selectIndustryFromList(industryType);
		enterNumberofEmployees(noOfEmployees);
		clickOnSaveBtn();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();;
		}
	}
	
	
	public void enterContactDetails(String firstName,String lastName,String phoneNo, String email){
		liContactNameSaluatation.click();
		selectContactSalutation("Mr.");
		enterContactFirstName(firstName);
		enterContactLastName(lastName);
		enterContactPhoneNumber(phoneNo);
		enterContactEmailId(email);
		clickOnContactSaveBtn();
	}
	
	public Boolean selectContactSalutation(String salutation){
		try {
			if(driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()){
				List<WebElement> types = driver.findElements(By.xpath("//*/ul[@role='presentation']/li"));
				for (WebElement ele : types) {
					if (ele.findElement(By.tagName("a")).getAttribute("title").equalsIgnoreCase(salutation)) {
						ele.click();
						return true;
					}
				}
				if (driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()) {
					driver.findElement(By.xpath("//*/ul[@role='presentation']/li/a[@title='Mr.']")).click();
					return true;
				}
			}else{
				clickOnType();
				if(driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()){
					List<WebElement> types = driver.findElements(By.xpath("//*/ul[@role='presentation']/li"));
					for (WebElement ele : types) {
						if (ele.findElement(By.tagName("a")).getAttribute("title").equalsIgnoreCase(salutation)) {
							ele.click();
							return true;
						}
					}
					if (driver.findElement(By.xpath("//*/ul[@role='presentation']")).isDisplayed()) {
						driver.findElement(By.xpath("//*/ul[@role='presentation']/li/a[@title='Mr.']")).click();
						return true;
					}
			}
			return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	public WebElement searchForAccounts(String AccountName){
		System.out.println(tableAccounts.getAttribute("class"));
		List<WebElement> trs = tableAccounts.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		System.out.println("tr count :-- " + trs.size());
		for(WebElement tr:trs){
//			System.out.println("Result :- " + tr.findElement(By.tagName("th")).findElement(By.tagName("a")).getAttribute("title"));
			System.out.println("Result :- " + tr.findElement(By.tagName("a")).getAttribute("title"));
			String actAccount = tr.findElement(By.tagName("a")).getAttribute("title");
			if (actAccount.equalsIgnoreCase(AccountName)) {
				System.out.println("account found :- " + actAccount);
				return tr.findElement(By.tagName("a"));
			}
		}
		return null;
	}

	
}
