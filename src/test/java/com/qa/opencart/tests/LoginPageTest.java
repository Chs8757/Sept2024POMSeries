package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: design login page for oepn cart ")
@Story("US 101:Design the various features of open cart")
@Feature("Feature 50:")
public class LoginPageTest extends BaseTest {
	
	@Description("Checking Login page title")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageTitleTest() {
		
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	
	  @Test public void loginPageURLTest() { String actURL =
	  loginPage.getLoginPageURL();
	  Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),
	  AppError.URL_NOT_FOUND_ERROR); }
	 
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist(), AppError.ELEMENT_NOT_FOUND_ERROR);
	}

	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
}
