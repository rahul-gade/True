package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class CreatedDealPage extends PageBase {
	public CreatedDealPage(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//a[@title='Quick Actions']")
	private WebElement quickAction;
	@FindBy(xpath = "//button[text()=' New Document ']")
	private WebElement newDocument;

	// ==========Added WebElements==========//
	@FindBy(xpath = "//span[@class='nav-label']/following-sibling::mat-form-field")
	private WebElement nxtMStoneDDown;
	@FindBy(xpath = "//div[contains(@class,'mat-select-panel mat-primary')]")
	private WebElement MstoneDdownList;

	@FindBy(xpath = "//span[text()=' ONE PAGER']")
	private WebElement optOnePager;
	@FindBy(xpath = "//span[text()=' PRELIMINARY IC NOTE']")
	private WebElement optPreICNote;
	@FindBy(xpath = "//span[text()=' PRE-DD IC NOTE']")
	private WebElement optPreDDICNote;
	@FindBy(xpath = "//span[text()=' POST-DD IC NOTE']")
	private WebElement optPostDDICNote;
	@FindBy(xpath = "//span[text()=' CLOSED']")
	private WebElement optClosed;
	@FindBy(xpath = "//span[text()=' PASSED']")
	private WebElement optPassed;

	@FindBy(xpath = "//textarea[@placeholder='Reasons']")
	private WebElement reasons;
	@FindBy(xpath = "//span[text()='PASS DEAL']")
	private WebElement passDealBtn;
	@FindBy(xpath = "//h4[text()='Reason for Passing the Deal']/parent::div/following-sibling::div")
	private WebElement passReasonTxt;

	String Option = "//mat-option[OPTION]";

	public void createNewDocument(RemoteWebDriver driver) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		click(quickAction, "Quick Action");
		waitForVisibilityOfElement(newDocument);
		click(newDocument, "New Document");
		Thread.sleep(1000);
	}

	public void dropDown(RemoteWebDriver driver) throws Exception {
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		if (driver.findElements(By.xpath("//span[@class='nav-label']/following-sibling::mat-form-field")).size() > 0) {
			click(nxtMStoneDDown, "Next Milestone Dropdown");
			Thread.sleep(500);
		}
		if (driver.findElements(By.xpath("//div[contains(@class,'mat-select-panel mat-primary')]")).size() > 0)
			assertTrue("Next Milestone Drop-Down is Displayed.");
		else
			assertFalse("Next Milestone Drop-Down is not Displayed.");

		if (optOnePager.isDisplayed()) {
			action.moveToElement(optOnePager).perform();
			assertTrue("One Pager Option is Displayed");
		} else
			assertFalse("One Pager Option is not Displayed");

		if (optPreICNote.isDisplayed()) {
			action.moveToElement(optPreICNote).perform();
			assertTrue("Pre IC Note Option is Displayed");
		} else
			assertFalse("Pre IC Note Option not is Displayed");

		if (optPreDDICNote.isDisplayed()) {
			action.moveToElement(optPreDDICNote).perform();
			assertTrue("Pre DD-IC Note Option is Displayed");
		} else
			assertFalse("Pre DD-IC Note Option not is Displayed");

		if (optPostDDICNote.isDisplayed()) {
			action.moveToElement(optPostDDICNote).perform();
			assertTrue("Post DD-IC Note option is Displayed");
		} else
			assertFalse("Post DD-IC Note option is Displayed");

		if (optClosed.isDisplayed()) {
			action.moveToElement(optClosed).perform();
			assertTrue("Closed Option is Displayed");
		} else
			assertFalse("Closed Option is not Displayed");

		if (optPassed.isDisplayed()) {
			action.moveToElement(optPassed).perform();
			assertTrue("Passed Option is Displayed");
		} else
			assertFalse("Passed Option is not Displayed");
	}

	public void passDeal(RemoteWebDriver driver, String passReason) throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(optPassed).click().build().perform();
		Thread.sleep(500);
		if (driver.findElements(By.xpath("//app-passing-reason")).size() > 0) {
			assertTrue("Passing Reason Pop-up Window is displayed");
			click(reasons, "Reasons TextArea");
			Thread.sleep(650);
			enterText(reasons, passReason, "Pass Reason");
			action.moveToElement(passDealBtn).click().build().perform();
			Thread.sleep(1000);
			if (driver.findElements(By.xpath("//h4[text()='Reason for Passing the Deal']")).size() > 0) {
				if (passReasonTxt.getText().trim().equals(passReason))
					assertTrue("Passing Reason is Saved and displayed with heading as 'Reason for Passing the Deal'");
				else
					assertFalse("Passing Reason is Not Displayed.");
			}
			click(nxtMStoneDDown, "Next Milestone Drop-Down List");
			Thread.sleep(650);
			int items = driver.findElements(By.xpath("//mat-option")).size();
			int itemsDisabled = driver.findElements(By.xpath("//mat-option[contains(@class,'mat-option-disabled')]"))
					.size();
			if (items == itemsDisabled)
				assertTrue("All "+items+" Options in Drop-Down List are Disabled and change is not possible.");
			else
				assertFalse("All "+items+" Options in Drop-Down List are not Disabled.");
		} else
			assertFalse("Passing Reason Pop-up Window is not displayed");
	}
}
