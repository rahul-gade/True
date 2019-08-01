package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class OnePagerSnapshotPage extends PageBase{
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
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;
	
	public void navigateTabs() throws Exception {
		Thread.sleep(1000);
		click(tabIndustry, "Industry");
		Thread.sleep(1000);
		click(tabDealDynamics, "Deal Dynamics");
		Thread.sleep(1000);
		click(tabTNAngle, "TN Angle");
		Thread.sleep(1000);
		click(tabCompany, "Company");
		assertTrue("Successfully navigated through all tabs in One Pager Snapshot Page");
		Thread.sleep(1000);
		click(btnClose, "Close Button");
		Thread.sleep(2000);
		assertTrue("Successfully clicked on Close Button");
	}
}
