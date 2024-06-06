package com.saucedemo.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.saucedemo.base.Base;
import com.saucedemo.pages.HomeProductsPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailPage;
import com.saucedemo.pages.ShoppingCartPage;

public class HomeProductsPageTest extends Base {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomeProductsPage homeProductsPage;
	private ProductDetailPage productDetailPage;
	private ShoppingCartPage shoppingCartPage;

	@BeforeMethod
	public void navigateToHomePage()

	{
		init_Driver();
		launchApp();
		System.out.print("driver is " + getDriver());
		driver=getDriver();
		loginPage=new LoginPage(driver);
		Assert.assertEquals(loginPage.getLoginPageTitle(), "Swag Labs","Not on Login page");
		homeProductsPage=loginPage.login();

	}
	@Test(priority=0)
	public void verifyOnHomeProductsPage() throws Throwable
	{

		Assert.assertEquals(loginPage.isHomeMenuButtonVisible(),true);
	}


	@Test(priority=1)	
	public void verifyPriceLowToHighDisplayed() throws Throwable
	{
		homeProductsPage.selectLowToHighFromDropDown();

		Assert.assertEquals(homeProductsPage.isPriceLowToHighDisplayed(), true);
	}
	@Test(priority=2)	
	public void verifyPriceHighToLowDisplayed() throws Throwable
	{
		homeProductsPage.selectHighToLowPriceFromDropDown();

		Assert.assertEquals(homeProductsPage.isPriceHighToLowDisplayed(), true);
	}


	@Test(priority=3)	
	public void verifyNamesInAscendingOderDisplayed() throws Throwable
	{
		homeProductsPage.selectNamesInAscendingOrder();

		Assert.assertEquals(homeProductsPage.isNameInAscendingOrderDisplayed(), true);
	}
	@Test(priority=4)	
	public void verifyNamesInDescendingOderDisplayed() throws Throwable
	{
		homeProductsPage.selectNamesInDescendingOrder();

		Assert.assertEquals(homeProductsPage.isNameInDescendingOrderDisplayed(), true);
	}
	@Test(priority=5)	
	public void verifyAddToCart() throws Throwable
	{ 

		homeProductsPage.selectHighToLowPriceFromDropDown();

		homeProductsPage.clickOnAddToCart(0);
		Assert.assertEquals(homeProductsPage.getCartCount(),1);
		Assert.assertEquals(homeProductsPage.isRemoveOptionDisplayed(0), true);
	}
	@Test(priority=6)	
	public void verifyAddingTwoItems() throws Throwable
	{ 


		homeProductsPage.selectHighToLowPriceFromDropDown();
		homeProductsPage.clickOnAddToCart(0);
		homeProductsPage.clickOnAddToCart(1);
		Assert.assertEquals(homeProductsPage.getCartCount(),2);
		Assert.assertEquals(homeProductsPage.isRemoveOptionDisplayed(0), true);
		Assert.assertEquals(homeProductsPage.isRemoveOptionDisplayed(1), true);
	}
	@Test(priority=7)	
	public void verifyClickingOnProductLink() throws Throwable
	{ 
		String expectedProductName=homeProductsPage.getProductName(0);
		productDetailPage=homeProductsPage.clickOnProduct(0);
		Assert.assertEquals(productDetailPage.getProductName(),expectedProductName);

	}
	@Test(priority=8)	
	public void verifyClickingOnCart() throws Throwable
	{ 

		shoppingCartPage=homeProductsPage.clickOnCart();
		Assert.assertTrue(shoppingCartPage.isCartPageTitleTextVisible());

	}
	@AfterMethod
	public void tearDown(){
		driver.quit();

	}
}





















