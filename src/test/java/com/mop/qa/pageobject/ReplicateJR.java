package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class ReplicateJR extends PageBase {
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

	public void selectFilter(RemoteWebDriver driver, String position, String sector, String location, String company)
			throws Exception {

		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img[contains(@src,'spinner')]")).size() > 0);
		waitForVisibilityOfElement(txtReplicate);
		click(selectFilter, "Select Filter");
		Thread.sleep(3000);
		if (driver.findElements(By.xpath(select.replace("PLACEHOLDER", position))).size() > 0) {
			driver.findElement(By.xpath(select.replace("PLACEHOLDER", position))).click();
			Thread.sleep(2000);
		} else
			assertFalse("Position Option Not found.");
		click(btnSector, "Sector");
		Thread.sleep(2000);
		if (driver.findElements(By.xpath(select.replace("PLACEHOLDER", sector))).size() > 0) {
			driver.findElement(By.xpath(select.replace("PLACEHOLDER", sector))).click();
			Thread.sleep(2000);
		} else
			assertFalse("Sector Option Not found.");
		click(btnLocation, "Location");
		Thread.sleep(2000);
		if (driver.findElements(By.xpath(select.replace("PLACEHOLDER", location))).size() > 0) {
			driver.findElement(By.xpath(select.replace("PLACEHOLDER", location))).click();
			Thread.sleep(2000);
		} else
			assertFalse("Location Option Not found.");
		click(btnCompany, "Company");
		Thread.sleep(2000);
		if (driver.findElements(By.xpath(select.replace("PLACEHOLDER", company))).size() > 0) {
			driver.findElement(By.xpath(select.replace("PLACEHOLDER", company))).click();
			Thread.sleep(1000);
		} else
			assertFalse("Comapny Option Not found.");
		click(btnApply, "Apply");
	}

	public void prieviewJR(RemoteWebDriver driver) throws Exception {
//		do {
//			Thread.sleep(1000);
//     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		Thread.sleep(5000);
		Actions action = new Actions(remoteDriver);
		action.moveToElement(linkPreviewJR).click().build().perform();
//		click(linkPreviewJR, "Prieview JR");
		if (driver.findElements(By.xpath("//div[@class='header' and contains(text(),'PREVIEW')]")).size() > 0) {
			assertTrue("Landed on Preview page");
			click(btnReplicate, "Replicate");
		} else {
			assertFalse("Unable to click Preview Link");
		}
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
	}

	public void skipToForm(RemoteWebDriver driver) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img[contains(@src,'spinner')]")).size() > 0);
		click(btnSkip, "SKIP TO FORM");
	}

	public void replicate(RemoteWebDriver driver) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img[contains(@src,'spinner')]")).size() > 0);
		click(btnReplicate1, "Replicate");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
	}
}
