package com.mop.qa.InvestmentManagement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_DealPipeline extends PageBase {

	public IM_DealPipeline(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "a[routerlink*=pipeline]")
	WebElement dealPipeline;
	@FindBy(css = "a[routerlink*=my-deals]")
	WebElement myDeals;
	@FindBy(css = "a[routerlink*=all-deals]")
	WebElement allDeals;
	@FindBy(css = "ul.left-navigation li.active a")
	WebElement activeTab;
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;
	@FindBy(css = "input[placeholder=Search]")
	WebElement searchBar;
	@FindBy(css = "ul.filter-action-list li a")
	WebElement summaryBtn;

	@FindBy(xpath = "//mat-select-trigger")
	WebElement activityDDown;

	@FindBy(css = "div.header")
	WebElement header;
	@FindBy(css = "ul.deal-inventory-list li")
	List<WebElement> inventoryList;

	public void checkDealPipeline(RemoteWebDriver driver) throws Exception {
		Thread.sleep(500);
		click(dealPipeline, "Deal Pipeline");
		Thread.sleep(2500);
		assertTrue("Active Tab is 'Deal Pipeline'", activeTab.getText().equals("Deal Pipeline"));
//		if (header.getText().contains("SUMMARY"))
//			assertTrue("Heading is proper");
//		else
//			assertFalse("heading is not proper");
		if(summaryBtn.isDisplayed()) {
			click(summaryBtn, "Summary Button");
			Thread.sleep(1000);
		}
		if (inventoryList.size() == 6)
			assertTrue("six sections shown with count");
		else
			assertFalse("Inventory view does not have six sections");
		click(activityDDown, "Activities For DropDown");
		Thread.sleep(100);
		if (driver.findElements(By.cssSelector("div.mat-select-panel")).size() > 0) {
			assertTrue("Week Drop Down displayed");
			Actions action = new Actions(driver);
			action.moveToElement(dealPipeline).click().build().perform();
		} else
			assertFalse("Week Drop Down NOT displayed");

		click(closeBtn, "Close Btn");
		Thread.sleep(500);
		if (searchBar.isDisplayed())
			assertTrue("Landed on summary page");
		else
			assertFalse("Summary Page not shown");

		click(summaryBtn, "Summary Btn");
		Thread.sleep(500);
		if (header.getText().contains("SUMMARY"))
			assertTrue("Landed Back to Inventory Summary Page");
		else
			assertFalse("Link Back Not working properly");
	}

}
