package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	//encapsulation
	public HomePage (WebDriver driver) {
		this.driver =driver;
		eleUtil = new ElementUtil(driver);
		
	}

	// By locators
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content >h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	// public page actions
	public String getHomePageTitle() {
		String title=	eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Home page title is ==>"+title);
		return title;
		
	}
	public String getHomePageURL() {
		String url =	eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
	
		System.out.println("Home page URL is ==>"+url);
		return url;
		
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.doIsElementDisplayed(logoutLink);
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}

	public List<String> getHeadersList() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(headers,AppConstants.SHORT_TIME_OUT);
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);

		}
		return headersValList;

	}
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("My search key is ==>"+searchKey);
		WebElement searchEle =eleUtil.waitForElementVisible(search,AppConstants.DEFAULT_TIME_OUT);
		eleUtil.doSendKeys(searchEle,searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}