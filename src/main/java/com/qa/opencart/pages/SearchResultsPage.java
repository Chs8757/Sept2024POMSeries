package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage (WebDriver driver) {
		this.driver =driver;
		eleUtil = new ElementUtil(driver);
	}
	//By
	
	private By productResults =By.cssSelector("div.product-thumb");
	
	
	public int getproductResults() {
		int resultCount	=eleUtil.waitForElementsVisible(productResults, AppConstants.SHORT_TIME_OUT).size();
	
		System.out.println("the prdt result count is ==>"+productResults);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("prduct name is==>"+productName);
		eleUtil.doClick(By.linkText(productName));
		
	    return new ProductInfoPage(driver);
	    
	}
	
}
