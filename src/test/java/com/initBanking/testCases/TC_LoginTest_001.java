package com.initBanking.testCases;

import org.testng.annotations.Test;

import com.initBanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {

	@Test (groups= {"Sanity", "Regression"})
	public void loginTest() {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		log.info("Entered Username");
		lp.setPassword(password);
		log.info("Entered Password");
		lp.clickLoginButton();
		log.info("Click Login Button");
		String title = "OrangeHRM";
		validateTitle(title);
		log.info("Login sucessfully");
		log.assertLog(true, "Login Successfully");
		System.out.println("Test");

	}

}
