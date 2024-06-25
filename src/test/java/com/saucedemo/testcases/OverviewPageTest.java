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

public class OverviewPageTest extends Base {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomeProductsPage homeProductsPage;
	private ProductDetailPage productDetailPage;
	private ShoppingCartPage shoppingCartPage;
	private CheckoutPage checkoutPage;
	private OverviewPage overviewPage;
	private OrderCompletePage orderCompletePage;

	@BeforeMethod
	public void navigateToOverview() throws Throwable
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

	}
	@Test(priority=0)
	public void verifyOnOverviewPage() throws Throwable
	{
		//Thread.sleep(2000);

		Assert.assertTrue(overviewPage.isOverviewTitleVisible());
	}
	@Test(priority=1)
	public void verifyTotalPriceWithoutTax() throws Throwable
	{
		//Thread.sleep(2000);

		Assert.assertEquals(overviewPage.getDisplayedTotal(),overviewPage.getTotalPriceOfItems());
	}
	@Test(priority=2)
	public void verifyTax() throws Throwable
	{
		//Thread.sleep(2000);

		Assert.assertEquals(overviewPage.getDisplayedTax(),overviewPage.getTax());
	}
	@Test(priority=3)
	public void verifyFinalTotal() throws Throwable
	{
		//Thread.sleep(2000);

		Assert.assertEquals(overviewPage.getDisplayedFinalTotal(),overviewPage.finalTotalPrice());
	}
	@Test(priority=4)
	public void verifyClickingOnCancel() throws Throwable
	{
		homeProductsPage=overviewPage.clickOnCancel();
		//Thread.sleep(2000);

		Assert.assertEquals(homeProductsPage.isProductsTitleVisible(),true);
	}
	@Test(priority=5)
	public void verifyClickingOnFinish() throws Throwable
	{
		orderCompletePage=overviewPage.clickOnFinish();
		//Thread.sleep(2000);

		Assert.assertEquals(orderCompletePage.isCheckoutCompleteTitleVisible(),true);
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
