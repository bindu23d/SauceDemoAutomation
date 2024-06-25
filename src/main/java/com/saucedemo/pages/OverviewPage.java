package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.actiondriver.ActionDriver;



public class OverviewPage{
	private WebDriver driver;
	@FindBy(xpath="//span[@class='title' and text()='Checkout: Overview']")
	private WebElement overviewTitle;
	@FindBy(css="div.inventory_item_price")
	private List<WebElement> itemPrices;
	@FindBy(css="div.summary_subtotal_label")
	private WebElement displayedItemsTotal;
	@FindBy(css="div.summary_tax_label")
	private WebElement displayedTax;
	@FindBy(css="div.summary_total_label")
	private WebElement displayedTotalPrice;
	@FindBy(id="cancel")
	private WebElement cancelButton;
	@FindBy(id="finish")
	private WebElement finishButton;
	ActionDriver action;
public OverviewPage(WebDriver wdriver) {
		
		driver=wdriver;
		System.out.print("driver is here" + driver);
		PageFactory.initElements(driver, this);
		action=new ActionDriver(driver);
	}
public boolean isOverviewTitleVisible()
{
	return action.isElementDisplayed(overviewTitle);
	
}
public float getTotalPriceOfItems()
{
	if(!itemPrices.isEmpty())
	{
	float sum=0;
	for (int i=0;i<itemPrices.size();i++)
	{
		String price=itemPrices.get(i).getText().substring(1);
		
	    sum=sum+Float.parseFloat(price);
	    
	}
	System.out.println("Sum is "+ sum);
	return sum;
	}
	else
		return 0;
	}
public float getDisplayedTotal()
{
	
	
		String total=displayedItemsTotal.getText().substring(13);
		float displayedTotal=Float.parseFloat(total);

		System.out.println("Displayed Sum is "+ displayedTotal);
	    
		return displayedTotal;  
	}
public float getTax()
{
	float price=getTotalPriceOfItems();
	float tax=(float)Math.round((price*0.08)*100)/100;
	System.out.println("calculated tax is "+ tax);
	
	return tax;
	
		}
public float getDisplayedTax()
{
	
	
		String tax=displayedTax.getText().substring(6);
		float displayedTax=Float.parseFloat(tax);
		System.out.println("Displayed tax is "+ displayedTax);

		
		return displayedTax;  
	}

public float getDisplayedFinalTotal()
{
	
	
		String total=displayedTotalPrice.getText().substring(8);
		float displayedTotal=Float.parseFloat(total);
		System.out.println("Displayed FinalTotal is "+ displayedTotal);
		return displayedTotal;  
	}
public float finalTotalPrice()
{
	float sum=getTotalPriceOfItems();
	float tax=getTax();
	float finalTotal=sum+tax;
	System.out.println("Final Total is "+ finalTotal);
	return finalTotal;
	
	}
public HomeProductsPage clickOnCancel()
{
	action.clickOnElement(cancelButton);
	return new HomeProductsPage(driver);
	
			}

public OrderCompletePage clickOnFinish()
{
	action.clickOnElement(finishButton);
	return new OrderCompletePage(driver);
	
			}
	
}
	




