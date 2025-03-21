package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;



//@Listeners(ChainTestListener.class)
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	 
	protected Properties prop;
	
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	
	@BeforeTest
	public void setup() {
		df= new DriverFactory();
		prop=df.initProp();
		driver =df.initDriver(prop);
		loginPage = new LoginPage(driver);
	//	homePage = new HomePage(driver);
	}
	
	
	  @AfterMethod 
	  public void attachScreenshot() {
	  ChainTestListener.embed(DriverFactory.getScreenshotFile(),"image/png"); 
	  }
	 
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
