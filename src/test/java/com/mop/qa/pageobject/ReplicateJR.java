package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class ReplicateJR extends PageBase{
	public ReplicateJR(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//div[contains(text(),'You can')]")
	private WebElement txtReplicate;
	@FindBy(xpath = "//a[@class='filter-btn']")
	private WebElement selectFilter;
	String select = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//a[text()='APPLY']")
	private WebElement btnApply;
	@FindBy(xpath = "//a[text()='Sector ']")
	private WebElement btnSector;
	@FindBy(xpath = "//a[text()='Location ']")
	private WebElement btnLocation;
	@FindBy(xpath = "//a[text()='Company ']")
	private WebElement btnCompany;
	@FindBy(xpath = "//a[text()='SKIP TO FORM']")
	private WebElement btnSkip;
	@FindBy(xpath = "//span[text()='PREVIEW JR']")
	private WebElement linkPreviewJR;
	@FindBy(xpath = "//div[@class='header' and contains(text(),'PREVIEW')]")
	private WebElement headerPreviewJR;
	@FindBy(xpath = "(//span[text()='REPLICATE'])[2]")
	private WebElement btnReplicate;
	@FindBy(xpath = "(//span[text()='REPLICATE'])[1]")
	private WebElement btnReplicate1;
	
	public void selectFilter(RemoteWebDriver driver, String position, String sector, String location, String company) throws Exception {
		
		Thread.sleep(1000);
		waitForVisibilityOfElement(txtReplicate);
		click(selectFilter, "Select Filter");
		Thread.sleep(3000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", position))).click();
		Thread.sleep(2000);
		click(btnSector, "Sector");
		Thread.sleep(2000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", sector))).click();
		Thread.sleep(2000);
		click(btnLocation, "Location");
		Thread.sleep(2000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", location))).click();
		Thread.sleep(2000);
		click(btnCompany, "Company");
		Thread.sleep(2000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", company))).click();
		click(btnApply, "Apply");
	}
	
	public void prieviewJR(RemoteWebDriver driver) throws Exception {	
//		do {
//			Thread.sleep(1000);
//     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		Thread.sleep(5000);
		click(linkPreviewJR, "Prieview JR");
		if(driver.findElements(By.xpath("//div[@class='header' and contains(text(),'PREVIEW')]")).size()>0) {
			assertTrue("Landed on Preview page");
			click(btnReplicate, "Replicate");	
		}else {
			assertFalse("Unable to click Preview Link");
		}
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
	}
	
	public void skipToForm() throws Exception {	
		Thread.sleep(2000);
		click(btnSkip, "SKIP TO FORM");
	}
	
	public void replicate(RemoteWebDriver driver) throws Exception {	
		Thread.sleep(1000);
		click(btnReplicate1, "Replicate");
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
	}
}
