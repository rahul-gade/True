package com.mop.qa.pageobject;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class DealHome extends PageBase {
	public DealHome(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//a[contains(text(),'NEW DEAL')]/span")
	private WebElement btnNewDeal;
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//input[@name='projectName']")
	private WebElement txtProjectName;
	@FindBy(xpath = "//input[@formcontrolname='dealOwner']/preceding-sibling::mat-chip")
	private WebElement txtDealOwner;
	@FindBy(xpath = "//input[@formcontrolname='companyName']")
	private WebElement txtCompanyName;
	@FindBy(xpath = "//mat-select[@formcontrolname='sector']")
	private WebElement sectors;
	String selectSector = "//span[text()='PLACEHOLDER']";
	String selectCompanyName = "//span[contains(text(),'PLACEHOLDER')]";
	String rdBtnSector = "//div[text()='PLACEHOLDER']";
	@FindBy(xpath = "//label[text()='UPLOAD']")
	private WebElement linkUpload;
	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='Create the Deal anyway']")
	private WebElement fillMyself;
	@FindBy(xpath = "//span[contains(text(),'IB')]")
	private WebElement requestIB;
	@FindBy(xpath = "//label[text()='UPLOAD']")
	private WebElement fileUpload;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;
	@FindBy(xpath = "//span[contains(text(),'YES')]")
	private WebElement btnDiscardDeal;
	@FindBy(xpath = "//span[text()='GO BACK']")
	private WebElement btnGoBack;
	@FindBy(xpath = "//a[text()='My Deals']")
	private WebElement txtMyDeals;
	String owner = "//*[contains(text(),'PLACEHOLDER')]";

	@FindBy(xpath = "//*[@formcontrolname='subSector']")
	private WebElement txtSubSector;
	String selectSubsector = "//span[text()=' PLACEHOLDER ']";
	@FindBy(xpath = "//input[@formcontrolname='dealSize']")
	private WebElement txtDealSize;
	String rdBtnStake = "//div[text()='PLACEHOLDER']/parent::label";
	@FindBy(xpath = "//p[@class='title']")
	private WebElement dealPopup;

	public void verifyDealOwner(RemoteWebDriver driver, String dOwner) throws Exception {
		click(btnNewDeal, "New Deal");
		Thread.sleep(3000);
		WebElement dealOwner = driver.findElement(By.xpath(owner.replace("PLACEHOLDER", dOwner)));
		if (getText(dealOwner).contains(dOwner)) {
			assertTrue("Deal Owner is displayed as " + getText(dealOwner));
		} else {
			assertFalse("Deal Owner is different than logged in User");
		}
	}

	public void cancelDeal(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		click(btnNewDeal, "New Deal");
		Thread.sleep(3000);
		click(btnClose, "Close Button");
		waitForVisibilityOfElement(btnDiscardDeal);
		if (driver.findElements(By.xpath("//span[text()='YES']")).size() > 0) {
			click(btnGoBack, "Go Back");
			if (pageTitle.getText().contains("Deal Specifics")) {
				assertTrue("Go Back Button is clicked and Add new Deal Screen is displayed ");
			} else {
				assertFalse("Go Back Button is not clicked");
			}
			click(btnClose, "Close Button");
			waitForVisibilityOfElement(btnDiscardDeal);
			click(btnDiscardDeal, "DISCARD DEAL");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			if (driver.findElements(By.xpath("//a[text()='My Deals']")).size() > 0) {
				assertTrue("Discard Deal Button is clicked and IM Landing page is displayed ");
			} else {
				assertFalse("Discard Deal Button is not clicked");
			}
		} else {
			assertFalse("Close Button is not clicked");
		}
	}

	public void createDealHomePage(RemoteWebDriver driver, String projectName, String companyName, String sector,
			String filePath, String mandatory, String subSector, String dealSize, String stake) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);

		// check page title.
		click(btnNewDeal, "New Deal");
		Thread.sleep(1000);
		verifyText(pageTitle, "Deal Specifics");
		if (pageTitle.getText().contains("Deal Specifics")) {
			assertTrue("PageTitle is as expected");
		}

		// enter project name
		click(txtProjectName, "Project Name");
		enterText(txtProjectName, projectName, "Project Name");
		String given = driver.findElement(By.xpath("//input[@name='projectName']")).getText();
		if (given.length() > 50) {
			assertFalse("Project name Accepts more than 50 characters");
		} else {
			assertTrue("Project name Accepts upto 50 characters.");
		}

		// enter company name
		if (!mandatory.contains("No")) {
			click(txtCompanyName, "Company Name");
			enterText(txtCompanyName, companyName, "Company Name");
			Thread.sleep(2000);
			driver.findElement(By.xpath(selectCompanyName.replace("PLACEHOLDER", companyName))).click();
			Thread.sleep(1000);
			String cName = driver.findElement(By.xpath("//input[@formcontrolname='companyName']")).getText();
			if (cName.length() > 50) {
				assertFalse("Company Name Accepts more than 50 characters");
			} else {
				assertTrue("Company Name Accepts upto 50 characters.");
			}
		}

		// enter sector
		if (driver.findElements(By.xpath(rdBtnSector.replace("PLACEHOLDER", sector))).size() > 0) {
			driver.findElement(By.xpath(rdBtnSector.replace("PLACEHOLDER", sector))).click();
		} else if (driver.findElements(By.xpath("//mat-select[@formcontrolname='sector']")).size() > 0) {
			click(sectors, "Sector Dropdown");
			driver.findElement(By.xpath(selectSector.replace("PLACEHOLDER", sector))).click();
		}

		if (driver.findElements(By.xpath("//*[@formcontrolname='subSector']")).size() > 0) {
			// enter subsector
			click(txtSubSector, "Sub Sector");
			Thread.sleep(1000);
			driver.findElement(By.xpath(selectSubsector.replace("PLACEHOLDER", subSector))).click();
		}
		if (driver.findElements(By.xpath("//input[@formcontrolname='dealSize']")).size() > 0) {
			// enter dealSize
			click(txtDealSize, "Deal Size");
			enterText(txtDealSize, dealSize, "Deal Size");
			Thread.sleep(1000);
		}

		// click stake radio button.
		if (driver.findElements(By.xpath(rdBtnStake.replace("PLACEHOLDER", stake))).size() > 0) {
			driver.findElement(By.xpath(rdBtnStake.replace("PLACEHOLDER", stake))).click();
			driver.findElement(By.xpath(rdBtnStake.replace("PLACEHOLDER", stake))).click();
		}
		// file upload.
		click(fileUpload, "UPLOAD");
		Thread.sleep(3000);
		FileUpload file = new FileUpload(remoteDriver);
		file.fileUpload(filePath);
		Thread.sleep(2000);
		/*
		 * Alert alert = driver.switchTo().alert(); alert.sendKeys(filePath); Robot r =
		 * new Robot(); r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
		 */
		waitForVisibilityOfElement(btnSubmit);
		click(btnSubmit, "Submit");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
	}

	public void selectFlow(RemoteWebDriver driver, String flow) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		verifyText(dealPopup, "Add New Deal");

		if (flow.contains("Myself")) {
			click(fillMyself, "Create Deal Anyway");
			Thread.sleep(1000);
		} else {
			click(requestIB, "RequestIB");
			Thread.sleep(1000);
		}
	}

	public void flowForDelete(RemoteWebDriver driver, String flow) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		verifyText(dealPopup, "Add New Deal");
		click(fillMyself, "FILL IN THE DETAILS MYSELF");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		click(driver.findElement(By.xpath("//a[@class='close-btn']")), "Close Button");
		Thread.sleep(1000);
		click(driver.findElement(By.xpath("//a[contains(@routerlink,'my-deals')]")), "Back to Pipeline");
		Thread.sleep(1000);
	}
}
