package com.saucedemo.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.base.Base;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.HomeProductsPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.OverviewPage;
import com.saucedemo.pages.ProductDetailPage;
import com.saucedemo.pages.ShoppingCartPage;

public class CheckoutPageTest extends Base {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomeProductsPage homeProductsPage;
	private ProductDetailPage productDetailPage;
	private ShoppingCartPage shoppingCartPage;
	private CheckoutPage checkoutPage;
	private OverviewPage overviewPage;

	@BeforeMethod
	public void navigateToCheckout() throws Throwable
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
    	productDetailPage.addToCartProduct();
		homeProductsPage=productDetailPage.goBackToProductsPage();
		homeProductsPage.selectLowToHighFromDropDown();
		productDetailPage=homeProductsPage.clickOnProduct(1);
		productDetailPage.addToCartProduct();
		shoppingCartPage=productDetailPage.clickOnCart();
		checkoutPage=shoppingCartPage.clickOnCheckout();

	}
	@Test(priority=0)
	public void verifyOnCheckoutPage() throws Throwable
	{
		//Thread.sleep(2000);

		Assert.assertTrue(checkoutPage.isCheckOutTitleVisible());
	}
	@Test(priority=1)
	public void verifyCancelButton() throws Throwable
	{
		shoppingCartPage=checkoutPage.clickOnCancel();
		//Thread.sleep(2000);

		Assert.assertEquals(shoppingCartPage.isCartPageTitleTextVisible(),true);
	}
	@Test(priority=2)
	public void verifyContinueCheckoutButton() throws Throwable
	{
		overviewPage=checkoutPage.clickOnContinue();
		//Thread.sleep(2000);
		Assert.assertEquals(overviewPage.isOverviewTitleVisible(),true);


	}
	@AfterMethod
	public void tearDown(){
		driver.quit();


	}
}
