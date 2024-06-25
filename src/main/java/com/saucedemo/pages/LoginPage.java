package com.saucedemo.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.saucedemo.actiondriver.ActionDriver;
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
	ActionDriver action;
	

	/**
	 * Initializing elements using Page Factory
	 */
	public LoginPage(WebDriver wdriver) {
		
		driver=wdriver;
		System.out.print("driver is here" + driver);
		PageFactory.initElements(driver, this);
		action=new ActionDriver(driver);
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
		action.sendKeysTo(userName, prop.getProperty("username"));
		action.sendKeysTo(password, prop.getProperty("password"));
		action.clickOnElement(loginButton);
		return new HomeProductsPage(driver);
		
		}
public boolean invalidLogin() {
		
	action.sendKeysTo(userName, prop.getProperty("invalid_uname"));
	action.sendKeysTo(password, prop.getProperty("invalid_pwd"));
	action.clickOnElement(loginButton);
		return invalidLoginError.isDisplayed();
		
}
	public boolean isHomeMenuButtonVisible()
	{
		return action.isElementDisplayed(homeMenuButton);
		

}
}
