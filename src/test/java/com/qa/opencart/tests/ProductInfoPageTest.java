package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		homePage=loginPage.doLogin("swapnaselenium@gmail.com", "Testing123");
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
						};
		}
	
	
	@Test(dataProvider="getProductData")
	public void productSearchHeaderTest(String searchKey,String productName) {
		searchResultsPage=homePage.doSearch(searchKey);
		productInfoPage =searchResultsPage.selectProduct(productName);
		String actualProductHeader =productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, productName);
	}
	
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
			};
		}
	
	@DataProvider
	public Object[][] getProductImagesSheetData(){
	Object productData[][]=	ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
		return productData;
	}
	
	@Test(dataProvider="getProductImagesSheetData")
	public void productImagesCountTest(String searchKey,String productName,String expectedImagesCount) {
		searchResultsPage=homePage.doSearch(searchKey);
		productInfoPage =searchResultsPage.selectProduct(productName);
		int actualProductImagesCount =productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualProductImagesCount, Integer.parseInt(expectedImagesCount));
	}

	
	
	
	
	@Test
	public void productInfoTest() {
		searchResultsPage=homePage.doSearch("macBook");
		productInfoPage =searchResultsPage.selectProduct("MacBook Pro");
		
	Map<String,String> productInfoMap=	productInfoPage.getProductInfo();
	productInfoMap.forEach((k,v) ->System.out.println(k+ ":" +v));
	
	SoftAssert softassert= new SoftAssert();
	Assert.assertEquals(productInfoMap.get("header"),"MacBook Pro");
	
	Assert.assertEquals(productInfoMap.get("Brand"),"Apple");
	Assert.assertEquals(productInfoMap.get("Availability"),"In Stock");
	Assert.assertEquals(productInfoMap.get("Product Code"),"Product 18");
	Assert.assertEquals(productInfoMap.get("Reward Points"),"800");
	
	Assert.assertEquals(productInfoMap.get("price"),"$2,000.00");
	Assert.assertEquals(productInfoMap.get("exTax"),"$2,000.00");
	softassert.assertAll();
	}
}
