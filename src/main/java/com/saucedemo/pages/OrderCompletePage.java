package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletePage {
	
	private WebDriver driver;
	@FindBy(xpath="//span[@class='title' and text()='Checkout: Complete!']")
	private WebElement checkoutCompleteTitle;
	@FindBy(id="back-to-products")
	private WebElement backHomeButton;
	@FindBy(xpath="//div[@id='shopping_cart_container']/a/span[@class='shopping_cart_badge']")
	private WebElement cartCount;

	/**
	 * Initializing elements using Page Factory
	 */
	public OrderCompletePage(WebDriver wdriver) {
		
		driver=wdriver;
		System.out.print("driver is here" + driver);
		PageFactory.initElements(driver, this);
	}
	public boolean isCheckoutCompleteTitleVisible()
	{
		if (checkoutCompleteTitle.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public HomeProductsPage clickOnBackHome()
	{
		backHomeButton.click();
		return new HomeProductsPage(driver);
		
				}
	
	
	


}
