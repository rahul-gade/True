package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class DealPipelineAllDeals extends PageBase {
	public DealPipelineAllDeals(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath = "//a[text()='Deal Pipeline']")
	private WebElement tabDealPipeline;
	@FindBy(xpath = "//a[text()='All Deals']")
	private WebElement tabAllDeals;
	@FindBy(xpath = "//span[text()='STAGE A']")
	private WebElement txtStage;
	@FindBy(xpath = "//span[text()='LAST UPDATED']")
	private WebElement txtLastUpdated;

	public void navigateTabs() throws Exception {
		Thread.sleep(1000);
		click(tabDealPipeline, "Deal PipeLine");
		if (txtStage.isDisplayed()) {
			assertTrue("Deal Pipeline tab is clicked and page is displayed");
		} else {
			assertFalse("Deal Pipeline tab is not clicked and page is not displayed");
		}
		Thread.sleep(3000);
		click(tabAllDeals, "All Deals");
		if (txtLastUpdated.isDisplayed()) {
			assertTrue("All Deals tab is clicked and page is displayed");
		} else {
			assertFalse("All Deals tab is not clicked and page is not displayed");
		}
	}
}
