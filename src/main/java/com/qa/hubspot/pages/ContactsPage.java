package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.JavaScriptUtil;

public class ContactsPage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	By createContact = By.xpath("(//span[text()='Create contact'])[1]");
	By createContactForm = By.xpath("(//span[text()='Create contact'])[2]");
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	
	By contactsNavigationLink = By.xpath("(//i18n-string[text()='Contacts'])[2]");
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public String getContactsPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}
	
	public String createNewContact(String emailID, String firstname, String lastname, String jobtitle) {
		elementUtil.waitForElementToBeClickable(createContact, 10);
		elementUtil.doClick(createContact);
		
		elementUtil.waitForElementToBePresent(email, 5).sendKeys(emailID);
		elementUtil.waitForElementToBePresent(firstName, 5).sendKeys(firstname);
		elementUtil.waitForElementToBePresent(lastName, 5).sendKeys(lastname);
		elementUtil.waitForElementToBePresent(jobTitle, 5).sendKeys(jobtitle);
		
		elementUtil.waitForElementToBeClickable(createContactForm, 10);
		jsUtil.clickElementByJS(elementUtil.getElement(createContactForm));
		
		String fullName = firstname+" "+lastname;
		String nameXpath = "(//span[text()='"+fullName+"'])[2]";
		elementUtil.waitForElementToBePresent(contactsNavigationLink, 10);
		String contactName = elementUtil.doGetText(By.xpath(nameXpath)).trim();
		elementUtil.doClick(contactsNavigationLink);
				
		return contactName;
		
		//elementUtil.waitForElementToBeClickable(contactsNavigationLink, 10);
		//elementUtil.doClick(contactsNavigationLink);
		
	}
	
	
}
