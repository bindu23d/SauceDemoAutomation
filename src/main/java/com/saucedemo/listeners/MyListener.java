package com.saucedemo.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucedemo.base.Base;
import com.saucedemo.utils.ExtentReporter;

public class MyListener extends Base implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		extentReport=ExtentReporter.generateExtentReport();

	}

	@Override
	public void onTestStart(ITestResult result) {

		String testName=result.getName();
		extentTest=extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Started Executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.PASS, testName + " is executed sucessfully");


	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();
		try {

			String destPath=failedScreenShot(testName);
			extentTest.addScreenCaptureFromPath(destPath);

		} 
		catch (Exception e) {

			e.printStackTrace();
		}
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testName + " is failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP,testName + " is skipped");


	}


	@Override
	public void onFinish(ITestContext context) {

		extentReport.flush();

	}

}
