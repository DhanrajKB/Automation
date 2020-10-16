package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.TestBase;

public class HomePage extends TestBase {
	
	//--WebElements
	//------- tabs elements
	@FindBy(xpath="//*/nav[@role='navigation']//child::a[@title='Home']")
	public WebElement homeTab;
//	@FindBy(xpath="//nav[@role='navigation']//child::a[@title='Accounts']/span")
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Account']")
	public WebElement accountsTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Contact']")
	public WebElement contactsTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Lead']")
	public WebElement leadsTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Opportunity']")
	public WebElement opptyTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Task']")
	public WebElement tasksTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Event']")
	public WebElement calenderTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='Dashboard']")
	public WebElement dashboardsTab;
	@FindBy(xpath="//*/one-app-nav-bar-item-root[@data-id='ContentNote']")
	public WebElement notesTab;
	@FindBy(xpath="//label[text()='Search by object type']//following-sibling::div//child::div[contains(@class,'dropdown-trigger')]")
	public WebElement dropDownUniSearchObjectType;
	@FindBy(xpath="//input[@title='Search Accounts']")
	public WebElement ipUniversalSearchString;
	@FindBy(xpath="//ul[@aria-label='Suggested For You']")
	public WebElement liDropDownObjectList;
	@FindBy(xpath="//ul[contains(@class,'lookup__list')]")
	public WebElement liUniversalLookUpWhenVisible;
	
	//------ homepage elements
	@FindBy(xpath="//a[text()='here']")
	WebElement lnkHere;
	@FindBy(xpath="//div[@id='errorDesc']")
	WebElement divError;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	
	//---- actions
	public Boolean validateHomePage(){
		if(driver.getTitle().equalsIgnoreCase("Home | Salesforce")){
			return true;
		}
		return false;
	}
	public AccountsPage navigateToAccountsPage(){
		accountsTab.click();
		return new AccountsPage();
	}
	
	public ContactsPage navigateToContactsPage(){
		contactsTab.click();
		return new ContactsPage();
	}
	
	public LeadsPage navigateToLeadsPage(){
		leadsTab.click();
		return new LeadsPage();
	}
	
	public OpptyPage navigateToOpptyPage(){
		opptyTab.click();
		return new OpptyPage();
	}
	
	public void universalSearch(String searchForObject,String searchString){
		//---- select the object from dropdonwn
		dropDownUniSearchObjectType.click();
		if(searchForObject.equalsIgnoreCase("All")){
			driver.findElement(By.xpath("//ul[@aria-label='Suggested For You']//li//child::span[@title='All']")).click();
		}else{
//			searchString = searchString.toLowerCase();
			List<WebElement> lis = driver.findElements(By.xpath("//ul[@aria-label='All Searchable Items']//li"));
			System.out.println("lis in universalSearch() :- " + lis.size());
			for(int i=1;i<lis.size();i++){
				String linkText = lis.get(i).getText();
				System.out.println("link Text in univsersalSearch() :-- "+linkText);
				if(linkText.equalsIgnoreCase(searchForObject)){
					lis.get(i).click();
					break;
				}
			}
			try {
				if(dropDownUniSearchObjectType.isEnabled()){
					dropDownUniSearchObjectType.sendKeys(Keys.ESCAPE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//------- enter the string in input
		ipUniversalSearchString.sendKeys(searchString);
		wait.until(ExpectedConditions.elementToBeClickable(liUniversalLookUpWhenVisible));
		if(liUniversalLookUpWhenVisible.isDisplayed()){
			List<WebElement> searchStrLis =  liUniversalLookUpWhenVisible.findElements(By.tagName("li"));
			System.out.println("searchStrLis.size() in universalSearch() :- " + searchStrLis.size());
			for(WebElement searchStrLi : searchStrLis){
				String searchResult = searchStrLi.findElement(By.tagName("span")).getAttribute("class");
				System.out.println("searchString-Title :- " + searchResult);
				System.out.println("searchStrLi.getAttribute('class') :-- "+ searchStrLi.getAttribute("class"));
				if(searchResult.equalsIgnoreCase(searchString)){
					searchStrLi.click();
					break;
				}else if(searchResult.toLowerCase().contains(searchString.toLowerCase())){
					searchStrLi.click();
					break;
				}
			}
		}
		try {
			if(liUniversalLookUpWhenVisible.isDisplayed()){
				liUniversalLookUpWhenVisible.sendKeys(Keys.ESCAPE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}


