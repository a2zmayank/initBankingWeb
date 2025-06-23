package com.initBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.initBanking.testCases.TC_LoginTest_001;

public class LogoutPage {
	
	WebDriver driver;
	public LogoutPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		System.out.println(driver);
	}

	@FindBy(xpath="//a[normalize-space()='Logout']")
	@CacheLookup
	WebElement clickLogout;
	
	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")
	@CacheLookup
	WebElement preLogoutElement;
	
	public void logoutPage() throws InterruptedException {
		Thread.sleep(3000);
		preLogoutElement.click();
		System.out.println("Pre Logout Element");
		Thread.sleep(3000);
		Actions ac= new Actions(driver);
		WebDriverWait wait= new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(clickLogout));
	
		ac.moveToElement(clickLogout).click().build().perform();;
		System.out.println("Logout");
		Thread.sleep(5000);
		//clickLogout.click();
	}
}
