package com.initBanking.testCases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.initBanking.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readConfig = new ReadConfig();
	public String baseURL = readConfig.getBaseURL();
	public String username = readConfig.getUserName();
	public String password = readConfig.getPassworg();
	public static WebDriver driver;

	public static Logger log;

	@Parameters("browser")
	@BeforeClass (groups= {"Sanity", "Regression"})
	public void setup(String br) throws InterruptedException {

		log = Logger.getLogger("initBanking");
		PropertyConfigurator.configure("Configrations//log4j.properties");
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			Thread.sleep(3000);
		}
		
		if(br.equals("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "//Drivers//edgedriver.exe");
			driver = new EdgeDriver();
		}
		log.info("Browser Launch");
		driver.manage().window().maximize();
		driver.get(baseURL);						
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	@AfterMethod
	public void afterMethod(){
		
	}

	public void validateTitle(String title) {
		if (driver.getTitle().equals(title)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		log.info("Title of the pages taken");
	}
	
 

	@AfterClass (groups= {"Sanity", "Regression"})
	public void tearDown() {
		driver.quit();
	}
	
	
	public static String takeSnapShot(String tname) throws Exception{
		//Convert web driver object to TakeScreenshot
		TakesScreenshot takesScreenshot =((TakesScreenshot) driver);
		//Call getScreenshotAs method to create image file
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		String fileWithPath= System.getProperty("user.dir")+"\\Screenshots\\"+tname;
		File DestFile= new File(fileWithPath);
		sourceFile.renameTo(DestFile);
		return fileWithPath;
		
		}

	public static String takeSnapShotSpecificArea(String tname, WebElement ele) throws Exception{
		//Convert web driver object to TakeScreenshot
		 WebElement logo = ele;
	        
	        // Get screenshot of the visible part of the web page
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        
	        // Convert the screenshot into BufferedImage
	        BufferedImage fullScreen = ImageIO.read(screenshot);
	        
	        //Find location of the webelement logo on the page
	        Point location = logo.getLocation();
	        
	        //Find width and height of the located element logo
	        int width = logo.getSize().getWidth();
	        int height = logo.getSize().getHeight();

		//cropping the full image to get only the logo screenshot
	        BufferedImage logoImage = fullScreen.getSubimage(location.getX(), location.getY(),
	                width, height);
	        ImageIO.write(logoImage, "png", screenshot);
	        
	        //Save cropped Image at destination location physically.
	        String fileWithPath= System.getProperty("user.dir")+"\\Screenshots\\"+tname+"1";
	        File DestFile= new File(fileWithPath);
	        screenshot.renameTo(DestFile);
			return fileWithPath;
	        }
}
