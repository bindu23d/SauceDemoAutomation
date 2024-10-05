package com.saucedemo.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.testng.annotations.AfterSuite;
import org.openqa.selenium.io.FileHandler;

public class Base {
	public static Properties prop;

	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();

	public  static Properties loadConfig() {


		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/Configuration/Config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	public static WebDriver init_Driver()
	{
		Properties prop=loadConfig();
		String browser = prop.getProperty("browser");
		System.out.println("Browser is  " + browser);
		if (browser.equalsIgnoreCase("Chrome")) {


			tlDriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("FireFox")) {


			tlDriver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("IE")) {


			tlDriver.set(new InternetExplorerDriver());
		}
		else {
			System.out.println("Please enter the correct browser value");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		return getDriver();

	}
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}


	public static void launchApp()
	{

		Properties prop=loadConfig();
		System.out.print("the url is" +prop.getProperty("url"));

		//Launching the URL
		getDriver().get(prop.getProperty("url"));

	}
	public String failedScreenShot(String testMethodName)
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
		String destPath=System.getProperty("user.dir")+"/ScreenShots/"+ testMethodName + "_" + TimeStamp+".png";

		try
		{
			FileHandler.copy(srcFile, new File(destPath));

		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return destPath;




	}

}


