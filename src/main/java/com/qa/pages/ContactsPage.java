package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	//----- webelements/object repository
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Contact']")
	WebElement contactsTab;
	@FindBy(xpath="//a[@title='Select List View']")
	WebElement contactListDropDown;
	@FindBy(xpath="//div[contains(@class,'listContent')]//child::ul[@role='listbox']/li/a/span[text()='All Contacts']")
	WebElement allContactsInListDropDown;
	@FindBy(xpath="//li[@class='slds-button slds-button--neutral']/a[@title='New']")
	WebElement btnNewContact;
//	@FindBy(xpath="//table[contains(@class,'table forceRecordLayout')]")
	@FindBy(xpath="//table/thead//child::span[@title='Name']//ancestor::table")
	WebElement tableContacts;
	@FindBy(xpath="//*/span[@data-aura-class='uiPicklistLabel']/span[text()='Type']//parent::span//following-sibling::div")
	WebElement liType;
	
	//---- contact creation elements
	@FindBy(xpath="//a[@class='select']")
	WebElement liContactNameSaluatation;
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement txtContactFirstName;
	@FindBy(xpath="//input[@placeholder='Middle Name']")
	WebElement txtContactMiddleName;
	@FindBy(xpath="//input[@placeholder='Last Name']")
	WebElement txtContactLastName;
	@FindBy(xpath="//input[@placeholder='Suffix']")
	WebElement txtContactSuffix;
	@FindBy(xpath="//input[@title='Search Accounts']")
	WebElement txtSearchAccounts;
	@FindBy(xpath="//ul[@class='lookup__list  visible']")
	WebElement lookupSearchAccounts;
	@FindBy(xpath="//label/span[text()='Phone']//parent::label//following-sibling::input")
	WebElement txtContactPhNo;
	@FindBy(xpath="//label/span[text()='Email']//parent::label//following-sibling::input")
	WebElement txtContactEmail;
	@FindBy(xpath="//label/span[text()='Department']//parent::label//following-sibling::input")
	WebElement txtDepartment;
	@FindBy(xpath="//button[@title='Save']")
	WebElement btnContactSave;
	@FindBy(xpath="//button[@title='Save & New']")
	WebElement btnSavenNew;
	@FindBy(xpath="//button[@title='Cancel']")
	WebElement btnContactCancel;
	
	
	//----- initialize webelements
	public ContactsPage(){
		PageFactory.initElements(driver, this);
	}
	
	//---- actions/methods
	
	public void validateContactsPage(){
		String title = driver.getTitle();
		if(title.contains("Contacts | Salesforce")){
			System.out.println("Contacts Page Validation Succeeded");
		}else{
			clickOnContactsTab();
		}
	}
	
	public void clickOnContactsTab(){
		contactsTab.click();
	}
	
	public void clickOnContactsListDropDown(){
		contactListDropDown.click();
	}
	
	public void clickOnAllContactsIndropDown(){
		allContactsInListDropDown.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void allContactsSelect(){
		clickOnContactsListDropDown();
		clickOnAllContactsIndropDown();
	}
	
	public void clickOnNewContactBtn(){
		btnNewContact.click();
	}
	
	public void clickOnType(){
		liType.click();
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
	public void createContact(String firstName,String lastName,String phoneNo, String email){
		clickOnNewContactBtn();
		liContactNameSaluatation.click();
		selectContactSalutation("Mr.");
		enterContactFirstName(firstName);
		enterContactLastName(lastName);
		enterContactPhoneNumber(phoneNo);
		enterContactEmailId(email);
		clickOnContactSaveBtn();
	}
	
	public WebElement searchForContact(String contactName){
		System.out.println(tableContacts.getAttribute("class"));
		List<WebElement> trs = tableContacts.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		System.out.println("tr count :-- " + trs.size());
		for(WebElement tr:trs){
			System.out.println("Result :- " + tr.findElement(By.tagName("a")).getText());
			String actContact = tr.findElement(By.tagName("a")).getText();
			if (actContact.equalsIgnoreCase(contactName)) {
				System.out.println("contact found :- " + actContact);
				return tr.findElement(By.tagName("a"));
			}
		}
		return null;
	}

}
