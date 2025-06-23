package com.initBanking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.initBanking.testCases.BaseClass;

public class TestListnerReport implements ITestListener {

	ExtentSparkReporter  htmlReporter;//user interface/ look & feel of the report
	ExtentReports reports;//common information
	ExtentTest test;//entries for test
	
	public void configureReport()
	{
	
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName = "ExtendReport-" + timestamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//add system information/environment info to reports
		reports.setSystemInfo("Machine:", "testpc1");
		reports.setSystemInfo("OS", "windows 11");
		reports.setSystemInfo("user name:", "Mayank Gautam");
		
		//configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
		htmlReporter.config().setReportName("Extend Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		
	}

	//OnStart method is called when any Test starts.
	public void onStart(ITestContext Result)					
	{		
		configureReport();
		
		String OS= Result.getCurrentXmlTest().getParameter("os");
		reports.setSystemInfo("OS", OS);
		
		String browser= Result.getCurrentXmlTest().getParameter("browser");
		reports.setSystemInfo("OS", browser);
		
		List<String> includeGroups= Result.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			reports.setSystemInfo("Group", includeGroups.toString());
		}
		
		System.out.println("On Start method invoked....");  	
		
	}	

	//onFinish method is called after all Tests are executed
	public void onFinish(ITestContext Result) 					
	{		
		System.out.println("On Finished method invoked....");  	
		reports.flush();//it is mandatory to call flush method to ensure information is written to the started reporter.

	}		



	// When Test case get failed, this method is called.		

	public void onTestFailure(ITestResult Result) 					
	{		
		System.out.println("Name of test method failed:" + Result.getName() );  	
		test = reports.createTest(Result.getName());
		try {
			String imgPath=BaseClass.takeSnapShot(Result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName() ,ExtentColor.RED));
		
	}
	
	
	

	// When Test case get Skipped, this method is called.		

	public void onTestSkipped(ITestResult Result)					
	{		
		System.out.println("Name of test method skipped:" + Result.getName() );  		

		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is: " + Result.getName() ,ExtentColor.YELLOW));
	}			

	// When Test case get Started, this method is called.		

	public void onTestStart(ITestResult Result)					
	{		
		System.out.println("Name of test method started:" + Result.getName() );  		

	}		

	// When Test case get passed, this method is called.		

	public void onTestSuccess(ITestResult Result)					
	{		
		System.out.println("Name of test method sucessfully executed:" + Result.getName() );  		

		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: " + Result.getName() ,ExtentColor.GREEN));
	}		


	public void onTestFailedButWithinSuccessPercentage(ITestResult Result)					
	{		

	}		
}
