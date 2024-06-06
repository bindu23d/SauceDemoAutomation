package com.saucedemo.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.saucedemo.base.Base;
import com.saucedemo.pages.LoginPage;

public class LoginPageTest extends Base {
	
	
	private LoginPage loginPage;
	private WebDriver driver;
	@BeforeMethod
	public void navigateToLginPage()
	
	{
		init_Driver();
		launchApp();
		System.out.print("driver is " + getDriver());
		driver=getDriver();
		loginPage=new LoginPage(driver);
		
		
	}
	@Test(priority=0)
	public void verifyLoginPageTitle() throws Throwable
	{
	Assert.assertEquals(loginPage.getLoginPageTitle(), "Swag Labs","Not on Login page");
	
	Thread.sleep(3000);
	}
	@Test(priority=1)
	public void verifyLogin() throws Throwable
	{
	loginPage.login();
	Assert.assertEquals(loginPage.isHomeMenuButtonVisible(),true);
	Thread.sleep(3000);
	}
	@Test(priority=2)
	public void verifyInvaildLogin() throws Throwable
	{
		boolean isActualErrorPresent;
		isActualErrorPresent=loginPage.invalidLogin();
	Assert.assertEquals(isActualErrorPresent,true);
	Thread.sleep(3000);
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
