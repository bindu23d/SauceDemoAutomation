package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.actiondriver.ActionDriver;

public class ProductDetailPage {
	@FindBy(xpath = "//div[@class='inventory_details']//button[text()='Add to cart']")
	private WebElement addToCartButton;
	@FindBy(xpath = "//div[@class='inventory_details']//button[text()='Remove']")
	private WebElement removeButton;
	@FindBy(id = "back-to-products")
	private WebElement backtoProductsPageButton;
	@FindBy(xpath="//*[@id=\"shopping_cart_container\"]/a")
	private WebElement cartLink;
	@FindBy(xpath="//div[@class='inventory_details']//div[@class='inventory_details_name large_size']")
	private WebElement productName;
	@FindBy(xpath="//div[@id='shopping_cart_container']/a/span[@class='shopping_cart_badge']")
	private WebElement cartCountContainer;
	private WebDriver driver;
	ActionDriver action;
public ProductDetailPage(WebDriver wdriver) {
		
		driver=wdriver;
		System.out.print("driver is here" + driver);
		PageFactory.initElements(driver, this);
		action=new ActionDriver(driver);
	}
public void addToCartProduct()
{
	action.clickOnElement(addToCartButton);
	
	}
public boolean isBackToProductsButtonDisplayed()
{
	return action.isElementDisplayed(backtoProductsPageButton);
	}
public HomeProductsPage goBackToProductsPage()
{
	action.clickOnElement(backtoProductsPageButton);
	return new HomeProductsPage(driver);
	}
public ShoppingCartPage clickOnCart()
{
	action.clickOnElement(cartLink);
	return new ShoppingCartPage(driver);
	
	}
public String getProductName()
{
	return productName.getText();
	}
public int getCartCount()
{
if(cartCountContainer.isDisplayed())
	{
	int count=Integer.parseInt(cartCountContainer.getText());
	System.out.println("Cart Count is " +count);
	return count;
	}
else
{
	return 0;
	}
	}
public boolean isRemoveProductButtonDisplayed()
{
	return action.isElementDisplayed(removeButton);
	}
public void clickOnRemove()
{
	action.clickOnElement(removeButton);
	}
public boolean isAddToCartButtonDisplayed()
{
	return action.isElementDisplayed(addToCartButton);
	}
}
