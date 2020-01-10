package com.mop.qa.InvestmentManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_Home extends PageBase {

	public IM_Home(RemoteWebDriver driver) {
		super(driver);
	}

//	launchApp
	@FindBy(xpath = "//input[@name='loginfmt']")
	private WebElement userName;
	@FindBy(xpath = "//input[@name='passwd']")
	private WebElement password;
	@FindBy(xpath = "//input[@id='idSIButton9']")
	private WebElement next;
	@FindBy(xpath = "//input[@id='idBtn_Back']")
	private WebElement no;
	@FindBy(css = "div.center-navigation")
	WebElement centerNav;
	@FindBy(xpath = "//mat-option//span[contains(text(),'INVESTMENT')]")
	WebElement IMNavOption;

//	tabs
	@FindBy(css = "a[routerlink*=pipeline]")
	WebElement dealPipeline;
	@FindBy(css = "a[routerlink*=my-deals]")
	WebElement myDeals;
	@FindBy(css = "a[routerlink*=all-deals]")
	WebElement allDeals;
	@FindBy(css = "ul.left-navigation li.active a")
	WebElement activeTab;
	@FindBy(css = "h3.deal-section-title")
	List<WebElement> sections;

//	view type
	@FindBy(css = "ul.sorting-list li:nth-of-type(1)")
	WebElement gridView;
	@FindBy(css = "ul.sorting-list li:nth-of-type(2)")
	WebElement listView;

//	new-deal
	@FindBy(css = "a[routerlink*=new-deal]")
	WebElement newDeal;
	@FindBy(css = "div.popup-title")
	WebElement newDealPopup;
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;
	@FindBy(css = "app-discard-new-deal-dialog")
	WebElement discardPopup;
	@FindBy(css = "button[routerlink*=my-deals]")
	WebElement discardConfirm;
	@FindBy(xpath = "//span[text()='GO BACK']")
	WebElement discardCancel;

//	deal locators [replace 'DEAL-NAME' WITH Deal Name]
	String newSection = "//h3[text()='NEW']/parent::div//div[contains(text(),'DEAL-NAME')]";
	String liveSection = "//h3[text()='LIVE DEALS']/parent::div//div[contains(text(),'DEAL-NAME')]";
	String pastSection = "//h3[text()='PAST DEALS']/parent::div//div[contains(text(),'DEAL-NAME')]";
	String anySection = "//div[contains(text(),'DEAL-NAME')]";

	@FindBy(css = "app-confirm-dialog")
	WebElement deleteDialog;
	@FindBy(css = "app-confirm-dialog button")
	WebElement delete;
	
//	system rules
	@FindBy(css = "h4.sub-title")
	WebElement pageHeading;

	public void launchApp(RemoteWebDriver driver, String url, String uName, String pwd) throws Exception {
		enterUrl(url);
		Thread.sleep(2000);
		click(userName, "User Name");
		enterText(userName, uName, "UserName");
		click(next, "Next");
		Thread.sleep(2000);
		click(password, "Password");
		enterText(password, pwd, "Password");
		click(next, "SignIn");
		Thread.sleep(2000);
		if (driver.findElementsByXPath("//input[@id='idBtn_Back']").size() > 0) {
			click(no, "Dont keep Signed-in");
			Thread.sleep(3000);
		}
		waitForPageLoad();
		click(centerNav, "Central Navigation");
		Thread.sleep(200);
		click(IMNavOption, "Investment");
		waitForPageLoad();
	}

	public void checkHomePage(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		assertTrue("Active Tab is 'My Deals'", activeTab.getText().equals("My Deals"));
		assertTrue("New Deal link is displayed", newDeal.isDisplayed());
//		view mode
		click(listView, "List View");
		Thread.sleep(4000);
		if (driver.findElementsByTagName("app-deal-grid-view").size() == 0)
			assertTrue("Deals are shown in list view");
		else
			assertFalse("View did not change");
		click(gridView, "Grid View");
		Thread.sleep(4000);
		if (driver.findElementsByTagName("app-deal-list-view").size() == 0)
			assertTrue("Deals are shown in grid view");
		else
			assertFalse("View did not change");
		
//		tabs
		assertTrue("Deal Pipeline Tab is displayed", dealPipeline.isDisplayed());
		assertTrue("All Deals Tab is displayed", allDeals.isDisplayed());
//		sections
		assertTrue("Three Sections Present", sections.size() == 3);
		Actions action = new Actions(driver);
		for (WebElement section : sections) {
			action.moveToElement(section).build().perform();
			action.moveByOffset(0, 125).perform();
			Thread.sleep(500);
			assertTrue("Section " + section.getText() + " is displayed properly");
		}
	}

	public void checkNewDealPopup() throws Exception {
		click(newDeal, "New Deal");
		Thread.sleep(200);
		assertTrue("New Deal Pop-up Opened", newDealPopup.getText().contains("Basic Deal Details"));
		click(closeBtn, "Cancel Deal");
		Thread.sleep(200);
		assertTrue("Discard Deal Pop-up displayed", discardPopup.isDisplayed());
		click(discardCancel, "Go Back");
		Thread.sleep(200);
		assertTrue("Landed back on 'Basic Deal Details' Page", newDealPopup.getText().contains("Basic Deal Details"));
		click(closeBtn, "Cancel Deal");
		Thread.sleep(200);
		click(discardConfirm, "Confirm Cancel Deal");
		Thread.sleep(1500);
		assertTrue("Landed Back on 'My Deals' Page", newDeal.isDisplayed());
	}

	public void startDealJourney() throws Exception {
		click(newDeal, "New Deal");
		Thread.sleep(200);
		assertTrue("New Deal Pop-up Opened", newDealPopup.getText().contains("Basic Deal Details"));
	}

//	probably a Useless Method
	public void checkDraftCount(RemoteWebDriver driver, String project) throws Exception {
		if (driver.findElements(By.xpath(newSection.replace("DEAL-NAME", project))).size() > 1)
			assertFalse("More than 1 draft exists");
		else { // TODO
		}
	}

	public void deleteAllAutoDrafts(RemoteWebDriver driver) throws Exception {
		int drafts = driver.findElements(By.xpath(newSection.replace("DEAL-NAME", "Automation Test"))).size();
		while (drafts > 0) {
			System.out.println(drafts);
			WebElement deal = driver.findElement(By.xpath(newSection.replace("DEAL-NAME", "Automation Test")));
			click(deal.findElement(By.xpath("./parent::div//div[contains(@class,'action')]/a")), "Delete Button");
			Thread.sleep(250);
			click(delete, "Delete Button");
			Thread.sleep(250);
			drafts = driver.findElements(By.xpath(newSection.replace("DEAL-NAME", "Automation Test"))).size();
		}
	}
	//Useless Method - till system rules get done
	public void openDraft(RemoteWebDriver driver) throws Exception{
		WebElement deal = driver.findElement(By.xpath(newSection.replace("DEAL-NAME", "Automation Test")));
		click(deal.findElement(By.xpath("./parent::div")), "Deal Card");
		Thread.sleep(2000);
	}

}
