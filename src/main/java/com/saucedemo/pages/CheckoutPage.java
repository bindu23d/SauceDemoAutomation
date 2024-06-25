package com.saucedemo.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.actiondriver.ActionDriver;

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
	ActionDriver action;

	public CheckoutPage(WebDriver wdriver)
	{
		driver=wdriver;
		PageFactory.initElements(driver, this);
		action=new ActionDriver(driver);
	}

	public boolean isCheckOutTitleVisible()
	{
		return action.isElementDisplayed(checkoutTitle);
	}
	public ShoppingCartPage clickOnCancel()
	{
		action.clickOnElement(cancelButton);
		return new ShoppingCartPage(driver);
	}
	public OverviewPage clickOnContinue() throws IOException
	{
		String uname=action.getDataFromExcel("UserData").getRow(1).getCell(0).getStringCellValue();
		String lname=action.getDataFromExcel("UserData").getRow(1).getCell(1).toString();
		String zipCode=action.getDataFromExcel("UserData").getRow(1).getCell(2).toString();
		action.sendKeysTo(firstName, uname);
		action.sendKeysTo(lastName,lname);
		action.sendKeysTo(postalCode, zipCode);
	    action.clickOnElement(continueButton);
		return new OverviewPage(driver);
	}
}
