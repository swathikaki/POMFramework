package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1. By locators
	By username = By.id("username");
	By password = By.id("password");
	By loginButton=By.id("loginBtn");
	By signUpLink=By.linkText("Sign up123");
	By showPasswordLink = By.xpath("//span[@id='password-help']/button/span");
	By forgotPasswordLink = By.xpath("//small[@id='password-description']/a/i18n-string");
	By ssoButton = By.xpath("//button[@id='ssoBtn']/i18n-string");
	By ssoLoginText = By.xpath("//form[@id='hs-login']/div/i18n-string");
	
	
	//2. constructor of the Login page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. page actions/methods, In page classes we should never verify anything
	
	@Step("get Login page title is..")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	@Step("Check Signup link on Login page..")
	public boolean checkSignUpLink() {
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	@Step("Check Shw Password link on Login page..")
	public boolean checkShowPasswordLink() {
		return elementUtil.doIsDisplayed(showPasswordLink);
	}
	
	@Step("Check Forgot Password link on Login page..")
	public boolean checkForgotPasswordLink() {
		return elementUtil.doIsDisplayed(forgotPasswordLink);
	}
	
	@Step("Check Login with SSO link on Login page..")
	public String checkSSOButton() {
		if(driver.findElement(ssoButton).isDisplayed()) {
			driver.findElement(ssoButton).click();
			return driver.findElement(ssoLoginText).getText();
		}
		else {
			return null;
		}
	}
	
	@Step("Login with - username : {0} and password: {1}")
	public HomePage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		//TimeUtil.mediumWait();
		
		return new HomePage(driver);  //after login, POM should return next page object
	}
}
