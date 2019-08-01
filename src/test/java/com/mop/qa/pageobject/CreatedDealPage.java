package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class CreatedDealPage extends PageBase{
	public CreatedDealPage(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//a[@title='Quick Actions']")
	private WebElement quickAction;
	@FindBy(xpath = "//button[text()=' New Document ']")
	private WebElement newDocument;
	
	
	public void createNewDocument(RemoteWebDriver driver) throws Exception {
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(quickAction, "Quick Action");
		waitForVisibilityOfElement(newDocument);
		click(newDocument, "New Document");
		 Thread.sleep(1000);
	}
}
