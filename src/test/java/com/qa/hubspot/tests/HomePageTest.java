package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic - 102: design Home page feature")
@Feature("US - 106: design test cases for Home page feature")
@Listeners(com.qa.hubspot.listeners.TestAllureListener.class)
public class HomePageTest {
	
	Properties prop;
	WebDriver driver;
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
		
	@BeforeTest   //annotation should be associated with method
	public void setup() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop); //Initialize the browser
		loginPage = new LoginPage(driver);	 
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));   //Login is the pre-condition for Home page Test
		
	}
	
	@Test(priority=1)
	@Description("Verify Home page Title test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("Home page Title is: "+ title);
		Assert.assertEquals(title,  Constants.HOME_PAGE_TITLE, "Home page title not found");
	}
	
	@Test(priority=2, enabled=false)
	@Description("Verify Home page Header test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println("Home page Header is: "+ header);
		Assert.assertEquals(header,  Constants.HOME_PAGE_HEADER, "Header is not found");
	}
	
	@Test(priority=3)
	@Description("Verify User has logged into the application test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoggedInUserTest() {
		String accountName = homePage.getAccountName();
		System.out.println("Logged in Account name is: "+ accountName);
		Assert.assertEquals(accountName,  prop.getProperty("accountname"), "Login is account name is not found");
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();  //close the browser
	}
	

}
