package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class JRNuancesAndChallenges extends PageBase {
	public JRNuancesAndChallenges(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "(//a[@class='add-more-btn'])[12]")
	private WebElement libBusinessChallenges;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[13]")
	private WebElement libCulturalNuances;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[14]")
	private WebElement libChallenges;
	@FindBy(xpath = "(//div[@class='search-values']//li[@class='ng-star-inserted'])[1]")
	private WebElement select1;
	@FindBy(xpath = "(//div[@class='search-values']//li[@class='ng-star-inserted'])[3]")
	private WebElement select2;
	@FindBy(xpath = "//span[text()='ADD']")
	private WebElement btnAdd;
	@FindBy(xpath = "//span[text()='SEND FOR REVIEW']")
	private WebElement btnReview;
	@FindBy(xpath = "//span[text()='PUBLISH JR']")
	private WebElement btnPublishJR;
	@FindBy(xpath = "//span[text()='PUBLISH']")
	private WebElement btnPublish;
//	@FindBy(xpath = "//span[text()='SEND FOR REVIEW']")
//	private WebElement btnSendForReview;
	@FindBy(xpath = "//input[@formcontrolname='reason']")
	private WebElement txtReason;
	@FindBy(xpath = "//span[text()='CONFIRM']")
	private WebElement btnConfirm;
//	@FindBy(xpath = "//span[text()='VIEW DETAILS']")
//	private WebElement btnViewDetails;
	@FindBy(xpath = "//span[contains(text(),'DETAILS')]") //span[contains(text(),'DETAILS')]
	private WebElement btnJRDetails;
	@FindBy(xpath = "//input[@placeholder='Reviewers']")
	private WebElement inputReviewers;
	@FindBy(xpath = "//span[text()='SEND']")
	private WebElement btnSend;

	public void enterNuancesandChallengesDetails(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		if (driver.findElements(By.xpath("//mat-label[text()='Business Challenges ']")).size() > 0) {
			click(libBusinessChallenges, "Business Challenges");
			Thread.sleep(1000);
			click(select1, "Business Challenges 1st Selection");
			click(select2, "Business Challenges 2nd Selection");
			click(btnAdd, "ADD");
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//mat-label[text()='Cultural Nuances ']")).size() > 0) {
			click(libCulturalNuances, "Cultural Nuances");
			Thread.sleep(2000);
			click(select1, "Cultural Nuances 1st Selection");
			click(select2, "Cultural Nuances 2nd Selection");
			Thread.sleep(500);
			click(btnAdd, "ADD");
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//mat-label[text()='Challenges faced by role']")).size() > 0) {
			click(libChallenges, "Challenges");
			Thread.sleep(1000);
			click(select1, "Challenges 1st Selection");
			// click(select2, "Challenges 2nd Selection");
			click(btnAdd, "ADD");
			Thread.sleep(1000);
		}
	}

	public void selectJRFlow(RemoteWebDriver driver, String flow) throws Exception {
		if (flow.contains("publish")) {
			if (driver.findElements(By.xpath("//span[text()='PUBLISH']")).size() > 0) {
				click(btnPublish, "Publish JR");
				Thread.sleep(100);
			} else if (driver.findElements(By.xpath("//span[text()='PUBLISH JR']")).size() > 0) {
				click(btnPublishJR, "Publish JR");
				Thread.sleep(100);
			}
			click(txtReason, "Reason");
			enterText(txtReason, "None", "Reason");
			Thread.sleep(1000);
			click(btnConfirm, "Confirm");
			Thread.sleep(2000);
			if (driver.findElements(By.xpath("//span[text()='VIEW DETAILS']")).size()>0) {
				click(btnJRDetails, "JR Details");
				Thread.sleep(2000);
			} else if(driver.findElements(By.xpath("//span[text()='JR DETAILS']")).size()>0) {
				click(btnJRDetails, "JR Details");
				Thread.sleep(2000);
			}
		} else {
			click(btnReview, "Send for Review");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img[contains(@src,'spinner')]")).size() > 0);
			if (driver
					.findElements(By
							.xpath("//div[@class='recommendationsDiv']//div[@class='suggested-card ng-star-inserted']"))
					.size() > 0) {
				driver.findElement(By.xpath(
						"(//div[@class='recommendationsDiv']//div[@class='suggested-card ng-star-inserted'])[1]"))
						.click();
				Thread.sleep(1000);
			} else {
				assertFalse("No Reviewers");
			}
			Thread.sleep(1000);
			click(btnSend, "Send");
			Thread.sleep(2000);
			click(btnJRDetails, "JR Details");
			Thread.sleep(2000);
			click(btnPublish, "Publish JR");
			Thread.sleep(1000);
			click(txtReason, "Reason");
			enterText(txtReason, "None Test", "Reason");
			click(btnConfirm, "Confirm");
		}
	}

	public void enterMissingField(RemoteWebDriver driver, String flow) throws Exception {
		if (flow.contains("publish")) {
			click(btnPublish, "Publish JR");
		} else {
			click(btnReview, "Send for Review");
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//div[@class='cdk-overlay-container']")).size() > 0) {
			Thread.sleep(2000);
			if (driver.findElements(By.xpath("//mat-error[contains(@id,'mat-error')]")).size() > 0) {
				String error = driver.findElement(By.xpath("//mat-error[contains(@id,'mat-error')]")).getText();
				assertTrue("Field missing is : " + error);
			} else {
				assertFalse("Missing field is not displayed");
			}
		} else {
			assertFalse("Missing field is not displayed");
		}
	}
}
