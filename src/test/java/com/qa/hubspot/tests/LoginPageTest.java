package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubsot.pages.HomePage;
import com.qa.hubsot.pages.LoginPage;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101: design Login page feature")
@Feature("US - 105: design test cases for login page feature")
public class LoginPageTest {

	// pre condition --> Test -->actual vs expected --> post step
	// @BeforeTest --> @Test -->Assert --> @AfterTest
	// browser , url -->test google title -> Google vs Google ->close browser
	//Test execution will be picked up in Alphabetic order in TestNG
	
	Properties prop;
	WebDriver driver;
	
	BasePage basePage;
	LoginPage loginPage;
	ElementUtil elementUtil;
		
	@BeforeTest   //annotation should be associated with method
	public void setup() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop); //Initialize the browser
		loginPage = new LoginPage(driver);	
		elementUtil = new ElementUtil(driver);
	}
	
	@Test(priority=1)
	@Description("Verify Login page Title test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);   //Static variable
	}
	
	@Test(priority=2)
	@Description("Verify Sign up link test..")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLinkTest() {
		boolean flag = loginPage.checkSignUpLink();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	@Description("Verify Show Password link test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifyShowPasswordLinkTest() {
		boolean flag = loginPage.checkShowPasswordLink();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=4)
	@Description("Verify Forgot Password link test..")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyForgotPasswordLinkTest() {
		boolean flag = loginPage.checkForgotPasswordLink();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=5, enabled=false)   //this test will be skipped as enabled=false
	@Description("Verify Login with SSO link test..")
	@Severity(SeverityLevel.NORMAL)
	public void verifySSOButtonTest() {
		String SSOText = loginPage.checkSSOButton();
		Assert.assertEquals(SSOText, Constants.LOGIN_PAGE_SSO_TEXT, "Verify SSO Login is failed");
		
	}
	
	
	@Test(priority=6)
	@Description("Verify user is able to Login - feature test..")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getAccountName(), prop.getProperty("accountname"), "Login is failed");
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();  //close the browser
	}
	
}
