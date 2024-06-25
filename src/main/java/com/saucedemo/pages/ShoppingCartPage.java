package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.actiondriver.ActionDriver;

public class ShoppingCartPage {
	@FindBy(xpath = "//div[@id='header_container']//span[text()='Your Cart']")
	private WebElement cartPageTitleText;
	@FindBy(id = "continue-shopping")
	private WebElement continueShoppingButton;
	@FindBy(id = "checkout")
	private WebElement checkoutButton;
	
	private WebDriver driver;
	ActionDriver action;
	
public ShoppingCartPage(WebDriver wdriver)
{
	driver=wdriver;
	PageFactory.initElements(driver, this);
	action=new ActionDriver(driver);
	}
public boolean isCartPageTitleTextVisible()
{
	return action.isElementDisplayed(cartPageTitleText);
}
public HomeProductsPage clickOnContinueShopping()
{
	action.clickOnElement(continueShoppingButton);
	return new HomeProductsPage(driver);
	}
public CheckoutPage clickOnCheckout()
{
	action.clickOnElement(checkoutButton);
	return new CheckoutPage(driver);
	}

}
