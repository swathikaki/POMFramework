package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubsot.pages.ContactsPage;
import com.qa.hubsot.pages.HomePage;
import com.qa.hubsot.pages.LoginPage;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest {
	
	Properties prop;
	WebDriver driver;
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
		
	@BeforeTest   //annotation should be associated with method
	public void setup() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop); //Initialize the browser
		loginPage = new LoginPage(driver);	 
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));   //Login is the pre-condition for Home page Test
		contactsPage = homePage.goToContactsPage();
	}
	
	@Test(priority=1)
	public void verifyContactsPageTitle() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts page Title is: "+ title);
		Assert.assertEquals(title,  Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority=2, dataProvider = "getContactsTestData")
	public void createNewContactTest(String email, String firstname, String lastname, String jobtitle) {
		String name = contactsPage.createNewContact(email, firstname, lastname,  jobtitle);
		Assert.assertEquals(name, firstname+" "+lastname);
		
	}
	

	@AfterTest
	public void tearDown() {
		driver.quit();  //close the browser
	}
	
	
	

}
