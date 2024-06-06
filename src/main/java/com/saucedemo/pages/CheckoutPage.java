package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	private WebDriver driver;
	@FindBy(xpath="//span[@class='title' and text()='Checkout: Your Information']")
	private WebElement checkoutTitle;
	@FindBy(id="first-name")
	private WebElement firstName;
	@FindBy(id="last-name")
	private WebElement lastName;
	@FindBy(id="postal-code")
	private WebElement postalCode;
	@FindBy(id="cancel")
	private WebElement cancelButton;
	@FindBy(id="continue")
	private WebElement continueButton;

	public CheckoutPage(WebDriver wdriver)
	{
		driver=wdriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isCheckOutTitleVisible()
	{
		if (checkoutTitle.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public ShoppingCartPage clickOnCancel()
	{
		cancelButton.click();
		return new ShoppingCartPage(driver);
	}
	public OverviewPage clickOnContinue()
	{
		firstName.sendKeys("Bindu");
		lastName.sendKeys("Sengar");
		postalCode.sendKeys("08536");
		continueButton.click();
		return new OverviewPage(driver);
	}
}
