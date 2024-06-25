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
import com.saucedemo.pages.OrderCompletePage;
import com.saucedemo.pages.OverviewPage;
import com.saucedemo.pages.ProductDetailPage;
import com.saucedemo.pages.ShoppingCartPage;

public class OrderCompletePageTest extends Base{
	private WebDriver driver;
	private LoginPage loginPage;
	private HomeProductsPage homeProductsPage;
	private ProductDetailPage productDetailPage;
	private ShoppingCartPage shoppingCartPage;
	private CheckoutPage checkoutPage;
	private OverviewPage overviewPage;
	private OrderCompletePage orderCompletePage;

	@BeforeMethod
	public void navigateToFinalPage() throws Throwable
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
		overviewPage=checkoutPage.clickOnContinue();
		orderCompletePage=overviewPage.clickOnFinish();
		
	}
	@Test(priority=0)
	public void verifyOnFinalPage() throws Throwable
	{
		//Thread.sleep(2000);
		
		Assert.assertTrue(orderCompletePage.isCheckoutCompleteTitleVisible());
	}
	@Test(priority=1)
	public void verifyClickingOnBackHome() throws Throwable
	{
		homeProductsPage=orderCompletePage.clickOnBackHome();
		//Thread.sleep(2000);
		
		Assert.assertEquals(homeProductsPage.isProductsTitleVisible(),true);
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
