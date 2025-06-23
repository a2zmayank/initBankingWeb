package com.initBanking.testCases;

import org.testng.annotations.Test;

import com.initBanking.pageObjects.LoginPage;
import com.initBanking.pageObjects.LogoutPage;

public class TC_Logout_02 extends BaseClass{
	
	
	@Test (groups= {"Regression"})
	public void logoutTest() throws InterruptedException {
		LogoutPage lg= new LogoutPage(driver);
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
		
		Thread.sleep(3000);
		lg.logoutPage();
		
	}
}
