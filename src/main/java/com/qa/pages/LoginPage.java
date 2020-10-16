package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(xpath="//input[@id='username']")
	public WebElement txtUsername;
	@FindBy(xpath="//input[@id='password']")
	public WebElement txtPassword;
	@FindBy(xpath="//input[@id='Login']")
	public WebElement btnLogin;
	@FindBy(xpath="//nav[@role='navigation']//child::a[@title='Home']")
	public WebElement homeTab;
	@FindBy(xpath="//a[text()='here']")
	WebElement lnkHere;
	@FindBy(xpath="//div[@id='errorDesc']")
	WebElement divError;
	@FindBy(xpath="//a[text()='Remind Me Later']")
	WebElement lnkRemindMeLater;
	
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//---- Actions
	public String validateLoginPage(){
		return driver.getTitle();
	}
	
	
	public HomePage loginToSFDC(){
		if (txtUsername.isDisplayed()) {
			txtUsername.sendKeys(prop.getProperty("username"));
			txtPassword.sendKeys(prop.getProperty("password"));
			btnLogin.click();
			try{
				if (lnkRemindMeLater.isDisplayed()) {
					lnkRemindMeLater.click();
					System.out.println("Verify Phone page appeared");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			try {
				if (divError.isDisplayed()) {
					lnkHere.click();
					System.out.println("Insufficient Privilages appeared");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (driver.getTitle().equalsIgnoreCase("Home | Salesforce")) {
				System.out.println("Login Successful");
				return new HomePage();
			}
		}else{
			System.out.println("Login Page not appeared");
		}
		return null;
	}

}
