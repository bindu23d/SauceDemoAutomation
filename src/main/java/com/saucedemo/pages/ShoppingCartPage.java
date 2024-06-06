package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
	@FindBy(xpath = "//div[@id='header_container']//span[text()='Your Cart']")
	private WebElement cartPageTitleText;
	@FindBy(id = "continue-shopping")
	private WebElement continueShoppingButton;
	@FindBy(id = "checkout")
	private WebElement checkoutButton;
	
	private WebDriver driver;
	
public ShoppingCartPage(WebDriver wdriver)
{
	driver=wdriver;
	PageFactory.initElements(driver, this);
	}
public boolean isCartPageTitleTextVisible()
{
	if (cartPageTitleText.isDisplayed())
	{
		return true;
	}
	else
	{
		return false;
	}
}
public HomeProductsPage clickOnContinueShopping()
{
	continueShoppingButton.click();
	return new HomeProductsPage(driver);
	}
public CheckoutPage clickOnCheckout()
{
	checkoutButton.click();
	return new CheckoutPage(driver);
	}

}
