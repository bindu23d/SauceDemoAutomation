package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.saucedemo.actiondriver.ActionDriver;

public class HomeProductsPage {
	@FindBy(xpath="//span[@class='title' and text()='Products']")
	private WebElement productsTitle;
	@FindBy(xpath = "//*[@class='product_sort_container']")
	private WebElement sortProducts;
	@FindBy(xpath="//div[@class='inventory_list']//following::button")
	private List<WebElement> itemAddToCart;
	@FindBy(xpath="//div[@class='inventory_list']/div")
	private List<WebElement> inventoryItems;
	@FindBy(css="div.inventory_item_price")
	private List<WebElement> prices;
	@FindBy(css="div.inventory_item_description a div.inventory_item_name")
	private List<WebElement> productNames;
	@FindBy(xpath="//*[@id=\"shopping_cart_container\"]/a")
	private WebElement cartLink;
	@FindBy(xpath="//div[@id='shopping_cart_container']/a/span[@class='shopping_cart_badge']")
	private WebElement cartCount;
	@FindBy(id="react-burger-menu-btn")
	private WebElement homeMenuButton;
	private WebDriver driver;
	private String productName;
    ActionDriver action;

	/** 
	 * Initializing elements using Page Factory
	 */

	public HomeProductsPage(WebDriver wdriver) {
		driver=wdriver;
		System.out.print("driver is here" + driver);
		PageFactory.initElements(driver, this);
		action=new ActionDriver(driver);


	}
	public boolean isProductsTitleVisible()
	{
		return action.isElementDisplayed(productsTitle);
	}


	public boolean isHomeMenuButtonVisible()
	{
		return action.isElementDisplayed(homeMenuButton);
	}


	/**
	 * Selecting low to high price from drop down
	 */

	public void selectLowToHighFromDropDown() throws Throwable
	{
		String text="Price (low to high)";
		action.selectValueFromDropDownByVisibleTxt(sortProducts, text);
	}
	public boolean isPriceLowToHighDisplayed() throws Throwable
	{
		List<Float> actualPriceList = new ArrayList<Float>();
		for (WebElement e : prices)
		{
			String price=e.getText().substring(1);
			actualPriceList.add(Float.parseFloat(price));
		}

		// make a copy of the list
		List<Float> sortedPrices = new ArrayList<Float>(actualPriceList);

		// sort the list
		Collections.sort(sortedPrices);
		System.out.println("Sorted List is " +sortedPrices);
		System.out.println("Actual List is " +actualPriceList);
		if(sortedPrices.equals(actualPriceList))
		{
			return true;
		}
		else
		{
			return false;
		}


	}
	public void selectHighToLowPriceFromDropDown() throws Throwable
	{
		String text="Price (high to low)";
		action.selectValueFromDropDownByVisibleTxt(sortProducts, text);
}
	public boolean isPriceHighToLowDisplayed() throws Throwable
	{
		List<Float> actualPriceList = new ArrayList<Float>();
		for (WebElement e : prices)
		{
			String price=e.getText().substring(1);
			actualPriceList.add(Float.parseFloat(price));
		}

		// make a copy of the list
		List<Float> sortedPrices = new ArrayList<Float>(actualPriceList);

		// sort the list
		Collections.sort(sortedPrices,Collections.reverseOrder());
		System.out.println("Sorted List is " +sortedPrices);
		System.out.println("Actual List is " +actualPriceList);
		if(sortedPrices.equals(actualPriceList))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void selectNamesInAscendingOrder() throws Throwable
	{
		String text="Name (A to Z)";
		action.selectValueFromDropDownByVisibleTxt(sortProducts, text);
}
	public boolean isNameInAscendingOrderDisplayed() throws Throwable
	{
		List<String> actualNameList = new ArrayList<String>();
		for (WebElement e : productNames)
		{
			String productName=e.getText();
			actualNameList.add(productName);
		}

		// make a copy of the list
		List<String> sortedNames = new ArrayList<String>(actualNameList);

		// sort the list
		Collections.sort(sortedNames);
		System.out.println("Sorted List is " +sortedNames);
		System.out.println("Actual List is " +actualNameList);
		if(sortedNames.equals(actualNameList))
		{
			return true;
		}
		else
		{
			return false;
		}


	}
	public void selectNamesInDescendingOrder() throws Throwable
	{
		String text="Name (Z to A)";
		action.selectValueFromDropDownByVisibleTxt(sortProducts, text);
}
	public boolean isNameInDescendingOrderDisplayed() throws Throwable
	{
		List<String> actualNameList = new ArrayList<String>();
		for (WebElement e : productNames)
		{
			String productName=e.getText();
			actualNameList.add(productName);
		}

		// make a copy of the list
		List<String> sortedNames = new ArrayList<String>(actualNameList);

		// sort the list
		Collections.sort(sortedNames,Collections.reverseOrder());
		System.out.println("Sorted List is " +sortedNames);
		System.out.println("Actual List is " +actualNameList);
		if(sortedNames.equals(actualNameList))
		{
			return true;
		}
		else
		{
			return false;
		}


	}
	/**
	 * Adding two items and saving their names in the list to verify later
	 * @param firstItemAtIndex
	 * @param secondItemAtIndex
	 * @throws Throwable 
	 */

	public void AddTwoItems() throws Throwable {


		String price=inventoryItems.get(0).findElement(By.cssSelector("div.inventory_item_description div.inventory_item_price")).getText();


		System.out.println("Price for the first item is "+price);

		String price2=inventoryItems.get(1).findElement(By.cssSelector("div.inventory_item_description div.inventory_item_price")).getText();
		System.out.println("Price for the second item is "+price2);
		inventoryItems.get(0).findElement(By.xpath("//div[@class='inventory_item_description']//button[text()='Add to cart']")).click();

		inventoryItems.get(1).findElement(By.xpath("//div[@class='inventory_item_description']//button[text()='Add to cart']")).click();
	}
	public ProductDetailPage clickOnProduct(int index)
	{
		inventoryItems.get(index).findElement(By.cssSelector("div.inventory_item_description div.inventory_item_name")).click();

		return new ProductDetailPage(driver); 
	}
	public String getProductName(int index) {
		return productName=inventoryItems.get(index).findElement(By.cssSelector("div.inventory_item_description div.inventory_item_name")).getText();

	}
	public void clickOnAddToCart(int index)
	{
		inventoryItems.get(index).findElement(By.xpath("//div[@class='inventory_item_description']//button[text()='Add to cart']")).click();

	}
	public boolean isRemoveOptionDisplayed(int index)
	{
		if(inventoryItems.get(index).findElement(By.xpath("//div[@class='inventory_item_description']//button[text()='Remove']")).isDisplayed())
		{
			return true;
		}
		else {
			return false;
		}
	}
	public int getCartCount()
	{ 
		int productCount;
		if(cartCount.isDisplayed())
		{
			productCount=Integer.parseInt(cartCount.getText());
			return productCount;
		}
		else
		{
			return 0;
		}
	}
	public ShoppingCartPage clickOnProduct()
	{
		action.clickOnElement(cartLink);
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage clickOnCart()
	{
		action.clickOnElement(cartLink);
		return new ShoppingCartPage(driver);
	}
}
