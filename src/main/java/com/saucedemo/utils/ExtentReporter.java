package com.saucedemo.utils;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucedemo.base.Base;

public class ExtentReporter extends Base {
	private static Properties prop;
	public static ExtentReports generateExtentReport()
	{
		prop=loadConfig();
		
		ExtentReports extentReport=new ExtentReports();
		
		ExtentSparkReporter SparkRepoter=new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReports/extentreport.html");
		SparkRepoter.config().setTheme(Theme.DARK);
		SparkRepoter.config().setReportName("SauceDemo Automation Tests Report");
		SparkRepoter.config().setDocumentTitle("SD Automation Report");
		extentReport.attachReporter(SparkRepoter);
		extentReport.setSystemInfo("ProjectName", "MySauceDemoProject");
		extentReport.setSystemInfo("Author", "Bindu");
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		return extentReport;
	
		
		
		
			
			
		
	}

}
