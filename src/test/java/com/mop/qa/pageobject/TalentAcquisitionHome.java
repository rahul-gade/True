package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class TalentAcquisitionHome extends PageBase{
	public TalentAcquisitionHome(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//span[text()='NEW JR']")
	private WebElement btnNewJr;
	
	public void clickNewJr(RemoteWebDriver driver) throws Exception {
		waitForPageLoad();
		waitForVisibilityOfElement(btnNewJr);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		
		click(btnNewJr, "New JR");
		assertTrue("Successfully Clicked on New JR");
	}
}
