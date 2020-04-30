package com.qa.hubsot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	
	//By header = By.cssSelector("h1.private-page__title");
	By header = By.cssSelector("h1.private-header__heading--solo");
	By accountName = By.cssSelector("span.account-name ");
	
	//By locators for Contacts page
	By contactsLinkPrimary = By.id("nav-primary-contacts-branch");
	By contactsLinkSecondary= By.id("nav-secondary-contacts");
	
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	@Step("get Home page Title...")
	public String getHomePageTitle() {
		String title = elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
		System.out.println("Title from getHomePageTitle: "+ title);
		return title;
		//return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
	}
	
	@Step("get Home page Header...")
	public String getHomePageHeader() {
		if(elementUtil.doIsDisplayed(header)) {
			System.out.println("Header is: "+ elementUtil.doGetText(header));
			return elementUtil.doGetText(header);
		}
		return null;
	}
	
	@Step("get Account name on Home page...")
	public String getAccountName() {
		elementUtil.waitForElementToBePresent(accountName, 10);
		if(elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	
	
	private void clickOnContacts() {
		elementUtil.waitForElementToBePresent(contactsLinkPrimary, 10);
		elementUtil.doClick(contactsLinkPrimary);
		elementUtil.waitForElementToBePresent(contactsLinkSecondary, 5);
		elementUtil.doClick(contactsLinkSecondary);
		
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	

}
