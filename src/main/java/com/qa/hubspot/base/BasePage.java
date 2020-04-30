package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.hubspot.utils.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/*****
	 * this method is used to initialize the driver on the basis of given browser
	 * @param browser
	 * @return WebDriver
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browser = prop.getProperty("browser");  //Reading browser from properties file
		
		System.out.println("Browser name is: "+ browser);
		optionsManager = new OptionsManager(prop);
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browser.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println(browser+" is not found, please pass the correct browser name");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		
		TimeUtil.mediumWait();
				
		return getDriver();
	}

	/***
	 * this method is used to init/load the properties from config file
	 * @return Properties
	 */
	
	public Properties init_prop(){
		 prop = new Properties();
		 String path = null;
		 String env = null;
		 
		 
		 try {
			 
			 env = System.getProperty("env");
			 if(env == null) {
				 path = "./src/main/java/com/qa/hubspot/config/config.properties";
			 }
			 else {
				 switch(env) {
				 case "qa":
					 path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
					 break;
				 case "dev":
					 path = "./src/main/java/com/qa/hubspot/config/dev.config.properties";
					 break;
				 case "stage":
					 path = "./src/main/java/com/qa/hubspot/config/stage.config.properties";
					 break;
				default:
					System.out.println("Please pass the correct environment variable...");
					break;
				 }
				 
			 }
						 
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return prop;
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	
}
