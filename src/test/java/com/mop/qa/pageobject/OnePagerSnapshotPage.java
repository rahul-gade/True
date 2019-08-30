package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class OnePagerSnapshotPage extends PageBase {
	public OnePagerSnapshotPage(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//div[text()='Company']")
	private WebElement tabCompany;
	@FindBy(xpath = "//div[text()='Industry']")
	private WebElement tabIndustry;
	@FindBy(xpath = "//div[text()='Deal Dynamics']")
	private WebElement tabDealDynamics;
	@FindBy(xpath = "//div[text()='TN Angle']")
	private WebElement tabTNAngle;
	@FindBy(xpath = "//div[text()='Tic']")
	private WebElement tabTIC;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;

	@FindBy(xpath = "//div[text()=' Company Information ']")
	private WebElement collapseCompInfo;
	@FindBy(xpath = "//div[text()=' Industry Information ']")
	private WebElement collapseIndustryInfo;
	@FindBy(xpath = "//div[text()=' Point of View on Deal ']")
	private WebElement collapsePOVonDeal;

	public void collapseSections(RemoteWebDriver driver) throws Exception {
		click(collapseCompInfo, "Company Information.");
		Thread.sleep(100);
		click(collapseIndustryInfo, "Industry Information.");
		Thread.sleep(100);
		click(collapsePOVonDeal, "Point of View On Deal.");
		Thread.sleep(100);
	}

	public void expandSections(RemoteWebDriver driver) throws Exception {
		click(collapseCompInfo, "Company Information.");
		Thread.sleep(100);
		click(collapseIndustryInfo, "Industry Information.");
		Thread.sleep(100);
		click(collapsePOVonDeal, "Point of View On Deal.");
		Thread.sleep(100);
	}

	public void navigateTabs(RemoteWebDriver driver, String likeTextGiven, String dislikeTextGiven) throws Exception {
		int listItems;
//		int tabCount = driver.findElements(By.xpath("//div[@class='mat-tab-label-content']")).size();

		click(tabTNAngle, "TN Angle");
		Thread.sleep(1000);

		click(tabDealDynamics, "Deal Dynamics");
		Thread.sleep(1000);
		click(driver.findElement(By.xpath("//div[text()=' Exit strategy ']")), " Exit strategy ");
		Thread.sleep(200);
		click(driver.findElement(By.xpath("//div[text()=' Valuation ']")), "Valuation");
		listItems = driver.findElements(By.xpath("//ol")).size();
		for (int i = 1; i <= listItems; i++) {
			String text = driver.findElement(By.xpath("(//ol/li)[" + i + "]")).getText();
			if (i % 2 != 0) {
				if (!text.equals(likeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			} else {
				if (!text.equals(dislikeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			}
		}
		click(tabCompany, "Company");
		Thread.sleep(1000);
		click(driver.findElement(By.xpath("//div[text()=' Company Deal dynamics ']")), "Company Deal dynamics");
		Thread.sleep(200);
		click(driver.findElement(By.xpath("//div[text()=' Customer Value Proposition & MOATS ']")),
				"Customer Value Proposition & MOATS");
		listItems = driver.findElements(By.xpath("//ol")).size();
		for (int i = 1; i <= listItems; i++) {
			String text = driver.findElement(By.xpath("(//ol/li)[" + i + "]")).getText();
			if (i % 2 != 0) {
				if (!text.equals(likeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			} else {
				if (!text.equals(dislikeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			}
		}
		click(tabIndustry, "Industry");
		Thread.sleep(1000);
		click(driver.findElement(By.xpath("//div[text()=' Expected Industry Growth ']")), "Expected Industry Growth");
		Thread.sleep(200);
		click(driver.findElement(By.xpath("//div[text()=' Underlying industry growth ']")),
				"Underlying industry growth");
		Thread.sleep(200);
		click(driver.findElement(By.xpath("//div[text()=' Competition ']")), "Competition");
		listItems = driver.findElements(By.xpath("//ol")).size();
		for (int i = 1; i <= listItems; i++) {
			String text = driver.findElement(By.xpath("(//ol/li)[" + i + "]")).getText();
			if (i % 2 != 0) {
				if (!text.equals(likeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			} else {
				if (!text.equals(dislikeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			}
		}
		click(tabTIC, "TIC");
		Thread.sleep(1000);
		click(driver.findElement(By.xpath("//div[text()=' test1 ']")), "test1");
		listItems = driver.findElements(By.xpath("//ol")).size();
		for (int i = 1; i <= listItems; i++) {
			String text = driver.findElement(By.xpath("(//ol/li)[" + i + "]")).getText();
			if (i % 2 != 0) {
				if (!text.equals(likeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			} else {
				if (!text.equals(dislikeTextGiven)) {
					assertFalse("Text doesn not matches the one given during filling");
					break;
				}
			}
		}
		assertTrue("Successfully navigated through all tabs in One Pager Snapshot Page");
		Thread.sleep(1000);
		click(btnClose, "Close Button");
		Thread.sleep(2000);
		assertTrue("Successfully clicked on Close Button");
	}
}
