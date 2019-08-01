package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class JRNavigateTabs extends PageBase{
	public JRNavigateTabs(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//span[text()='IN PROGRESS']")
	WebElement tabInProgress;
	@FindBy(xpath = "//span[text()='IN PROGRESS']/following-sibling::span")
	WebElement countInProgress;
	@FindBy(xpath = "//span[text()='LIVE']")
	WebElement tabLive;
	@FindBy(xpath = "//span[text()='LIVE']/following-sibling::span")
	WebElement countLive;
	@FindBy(xpath = "//span[text()='ON HOLD']")
	WebElement tabOnHold;
	@FindBy(xpath = "//span[text()='ON HOLD']/following-sibling::span")
	WebElement countOnHold;
	@FindBy(xpath = "//span[text()='TERMINATED']")
	WebElement tabTerminated;
	@FindBy(xpath = "//span[text()='TERMINATED']/following-sibling::span")
	WebElement countTerminated;
	public void navigateTabs(RemoteWebDriver driver) throws Exception {
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(tabLive, "LIVE");
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		String live = getText(countLive, "LIVE");
		assertTrue("Count of Live deals is "+live);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		if(driver.findElements(By.xpath("//span[text()='ON HOLD']")).size()>0) {
		click(tabOnHold, "ON HOLD");
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		String onHold = getText(countOnHold, " ON HOLD");
		assertTrue("Count of ON HOLD deals is "+onHold);
		}
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		if(driver.findElements(By.xpath("//span[text()='TERMINATED']")).size()>0) {
		click(tabTerminated, "TERMINATED");
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		String terminated = getText(countTerminated, " TERMINATED");
		assertTrue("Count of TERMINATED deals is "+terminated);
		}
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(tabInProgress, "IN PROGRESS");
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		String inProgress = getText(countInProgress, " IN PROGRESS");
		assertTrue("Count of IN PROGRESS deals is "+inProgress);
	}

}
