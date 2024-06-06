package com.saucedemo.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.saucedemo.base.Base;


public class LoginPage extends Base {
	@FindBy(name = "user-name") 
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(id="login-button")
	private WebElement loginButton;
	@FindBy(xpath="//div[@class='login-box']//div[@class='error-message-container error']")
	WebElement invalidLoginError;
	@FindBy(id="react-burger-menu-btn")
	private WebElement homeMenuButton;
	private WebDriver driver;
	private Properties prop;

	/**
	 * Initializing elements using Page Factory
	 */
	public LoginPage(WebDriver wdriver) {
		
		driver=wdriver;
		System.out.print("driver is here" + driver);
		PageFactory.initElements(driver, this);
		prop=loadConfig();
	}

	public String getLoginPageTitle()
	{
		
		return driver.getTitle();
	}
	/**
	 * Login using user name and password from config.properties file
	 * under Configuration folder,initialized in BaseClass
	 */
	public HomeProductsPage login() {
		
		userName.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		loginButton.click();
		return new HomeProductsPage(driver);
		
		}
public boolean invalidLogin() {
		
	userName.sendKeys(prop.getProperty("invalid_uname"));
	password.sendKeys(prop.getProperty("invalid_pwd"));
		loginButton.click();
		return invalidLoginError.isDisplayed();
		
}
	public boolean isHomeMenuButtonVisible()
	{
		if (homeMenuButton.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
