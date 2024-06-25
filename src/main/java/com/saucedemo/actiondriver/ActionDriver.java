package com.saucedemo.actiondriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.saucedemo.base.Base;

public class ActionDriver extends Base{
	private WebDriver driver;
	private WebDriverWait wait;
	private FileInputStream fis;
	public ActionDriver(WebDriver wdriver) {

		driver=wdriver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	

	public  void waitForVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public  void waitForElementToBeClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void clickOnElement(WebElement element) {
		waitForElementToBeClickable(element);
		element.click();
	}

	public void sendKeysTo(WebElement element, String text){
		waitForVisible(element);
		element.clear();
		element.sendKeys(text);
	}
	public void selectValueFromDropDownByVisibleTxt(WebElement element, String value) {
		waitForVisible(element);
		Select option = new Select(element);
		option.selectByVisibleText(value);
	}
	public boolean isElementDisplayed(WebElement element) {
		waitForVisible(element);
		if(element.isDisplayed())
		{
			return true;
		}
		else 
			{
			return false;
			}
	}
	public XSSFSheet getDataFromExcel(String sheetName) throws IOException
	{ 

		fis = new FileInputStream(
				System.getProperty("user.dir") + "/TestData/testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);


		workbook.close();

		return sheet;


	}





}
