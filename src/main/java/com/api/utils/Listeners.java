package com.api.utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{
	
	public ExtentSparkReporter sparkReporter = null;
	public ExtentReports extent = null;
	public ExtentTest test = null;
	
	public void onStart(ITestContext testContext) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-reports/report.html");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("REST API Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Hostname", "petstore.swagger.io");
		extent.setSystemInfo("QA'ed", "gankit279");
		extent.setSystemInfo("Environment", "QA");
	  }
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case passed is "+result.getName());
	  }
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case failed is "+result.getName());
		test.log(Status.FAIL, "Test case failed is "+result.getThrowable()); 
	  }
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case skipped is "+result.getName());
	  }
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	  }
}
