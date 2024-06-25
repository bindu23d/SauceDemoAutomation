package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.actiondriver.ActionDriver;

public class OrderCompletePage {
	
	private WebDriver driver;
	@FindBy(xpath="//span[@class='title' and text()='Checkout: Complete!']")
	private WebElement checkoutCompleteTitle;
	@FindBy(id="back-to-products")
	private WebElement backHomeButton;
	@FindBy(xpath="//div[@id='shopping_cart_container']/a/span[@class='shopping_cart_badge']")
	private WebElement cartCount;
	ActionDriver action;

	/**
	 * Initializing elements using Page Factory
	 */
	public OrderCompletePage(WebDriver wdriver) {
		
		driver=wdriver;
		System.out.print("driver is here" + driver);
		PageFactory.initElements(driver, this);
		action=new ActionDriver(driver);
	}
	public boolean isCheckoutCompleteTitleVisible()
	{
		return action.isElementDisplayed(checkoutCompleteTitle);
		
	}
	public HomeProductsPage clickOnBackHome()
	{
		action.clickOnElement(backHomeButton);
		return new HomeProductsPage(driver);
		
				}
	
	
	


}
