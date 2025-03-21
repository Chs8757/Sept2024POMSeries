package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	//encapsulation
	public LoginPage (WebDriver driver) {
		this.driver =driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	//1.By locator:page objects
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn =By.xpath("//input[@value='Login']");
	
	private By forgotPwdLink = By.linkText("Forgotten Password");
	
	public String getLoginPageTitle() {
		String title=	eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("login page title is ==>"+title);
		return title;
		
	}
	public String getLoginPageURL() {
		String url =	eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
	
		System.out.println("login page URL is ==>"+url);
		return url;
		
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsElementDisplayed(forgotPwdLink);
		
	}
	
	public HomePage doLogin(String username,String pwd) {
		System.out.println("The app credentials are==>"+username + ":" +pwd);
		
		eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		return new HomePage(driver);
	}
	
}
