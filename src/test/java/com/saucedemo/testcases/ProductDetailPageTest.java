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

public class ProductDetailPageTest extends Base {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomeProductsPage homeProductsPage;
	private ProductDetailPage productDetailPage;
	private ShoppingCartPage shoppingCartPage;
	@BeforeMethod
	public void navigateToProductDetailPage() throws Throwable

	{
		init_Driver();
		launchApp();
		System.out.print("driver is " + getDriver());
		driver=getDriver();
		loginPage=new LoginPage(driver);
		Assert.assertEquals(loginPage.getLoginPageTitle(), "Swag Labs","Not on Login page");
		homeProductsPage=loginPage.login();
		Assert.assertEquals(loginPage.isHomeMenuButtonVisible(),true);
		homeProductsPage.selectLowToHighFromDropDown();
		productDetailPage=homeProductsPage.clickOnProduct(0);
	}
	@Test(priority=0)
	public void verifyOnProductDetailsPage() throws Throwable

	{
		Assert.assertEquals(productDetailPage.isBackToProductsButtonDisplayed(),true);
		Thread.sleep(3000);

	}
	@Test(priority=1)
	public void verifyAddProductToCart() throws Throwable

	{

		productDetailPage.addToCartProduct();
		Assert.assertEquals(productDetailPage.getCartCount(),1);
		Assert.assertEquals(productDetailPage.isRemoveProductButtonDisplayed(), true);
		Thread.sleep(3000);
	}
	@Test(priority=2,enabled=false)
	public void verifyRemoveProduct() throws Throwable

	{

		productDetailPage.clickOnRemove();
		Assert.assertEquals(productDetailPage.getCartCount(),0);
		Assert.assertEquals(productDetailPage.isAddToCartButtonDisplayed(), true);
		Thread.sleep(3000);

	}
	@Test(priority=3)
	public void verifyClickingOnCart() throws Throwable

	{

		shoppingCartPage=productDetailPage.clickOnCart();
		Assert.assertTrue(shoppingCartPage.isCartPageTitleTextVisible());

	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
