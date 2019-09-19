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

	int checkCount = 0;
	// ==========WebElements Deal Details Page==========//
	@FindBy(xpath = "(//a[@class='edit-icon'])[1]")
	private WebElement companyBasicDetails;
	@FindBy(xpath = "//div[@class='shadow-card']/div[text()='Likeability']")
	private WebElement likeabilityCard;
	@FindBy(xpath = "//div[@class='shadow-card']/div[text()='Likeability']")
	private WebElement probabilityCard;
	@FindBy(xpath = "//div[text()=' Company Information ']")
	private WebElement compInfo;
	@FindBy(xpath = "//div[text()=' Financials ']")
	private WebElement compFinancials;
	@FindBy(xpath = "//div[text()=' Industry Information ']")
	private WebElement industryInfo;
	@FindBy(xpath = "//a/span[contains(text(),'Rules')]")
	private WebElement rulesLink;
	@FindBy(xpath = "//a[text()='ADD']")
	private WebElement addMemberLink;
	@FindBy(xpath = "//a[@title='Quick Actions']")
	private WebElement quickAction;

	// ==========WebElements MileStone DropDown==========//
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
	@FindBy(xpath = "//span[text()=' IDEAS']")
	private WebElement optIdeas;

	// ==========WebElements Quick Actions DropDown==========//
	@FindBy(xpath = "//button[text()=' New Task ']")
	private WebElement newTask;
	@FindBy(xpath = "//button[text()=' New note ']")
	private WebElement newNote;
	@FindBy(xpath = "//button[text()=' New Meeting ']")
	private WebElement newMeeting;
	@FindBy(xpath = "//button[text()=' New Document ']")
	private WebElement newDocument;

	// ==========WebElements PassDeal Popup==========//
	@FindBy(xpath = "//textarea[@placeholder='Reasons']")
	private WebElement reasons;
	@FindBy(xpath = "//span[text()='PASS DEAL']")
	private WebElement passDealBtn;
	@FindBy(xpath = "//h4[text()='Reason for Passing the Deal']/parent::div/following-sibling::div")
	private WebElement passReasonTxt;
	
	// ==========WebElements New Document Popup==========//
	@FindBy(xpath = "//a[text()=' One Pager ']")
	private WebElement onePager;
	@FindBy(xpath = "//a[text()=' Field Report ']")
	private WebElement fieldReport;
	@FindBy(xpath = "//a[text()=' IC Note ']")
	private WebElement ICNote;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement closeBtn;

	String Option = "//mat-option[OPTION]";
	
	// ==========WebElements Recent Activities==========//
	@FindBy(xpath = "//a[@title='Recent Activities']")
	private WebElement recentActs;
	@FindBy(xpath = "//span[contains(@class,'badge')]")
	private WebElement recentActBadge;
	//div[@class='activity-header']                         --RecentActivities Header
	//div[@class='activity-header']/span					--Unread Activities Badge [getText for number]

	public void checkDealPageElements(RemoteWebDriver driver) throws Exception {
		Actions action = new Actions(driver);
		if (companyBasicDetails.isDisplayed()) {
			action.moveToElement(companyBasicDetails).perform();
			assertTrue("Basic Info is displayed");
		} else
			assertFalse("Basic Info is not Displayed");
		if (likeabilityCard.isDisplayed()) {
			action.moveToElement(likeabilityCard).perform();
			assertTrue("Likeability is displayed");
		} else
			assertFalse("Likeability is not Displayed");
		if (probabilityCard.isDisplayed()) {
			action.moveToElement(probabilityCard).perform();
			assertTrue("Probability is displayed");
		} else
			assertFalse("Probability is not Displayed");
		if (compInfo.isDisplayed()) {
			action.moveToElement(compInfo).perform();
			assertTrue("Company Information is displayed");
		} else
			assertFalse("Company Information is not Displayed");
		if (compFinancials.isDisplayed()) {
			action.moveToElement(compFinancials).perform();
			assertTrue("Company Financials is displayed");
		} else
			assertFalse("Company Financials is not Displayed");
		if (industryInfo.isDisplayed()) {
			action.moveToElement(industryInfo).perform();
			assertTrue("Industry Information is displayed");
		} else
			assertFalse("Industry Information is not Displayed");
//		if (rulesLink.isDisplayed()) {
//			action.moveToElement(rulesLink).perform();
//			assertTrue("Rules Passed/Failed HyperLink is displayed");
//		} else
//			assertFalse("Rules Passed/Failed HyperLink is not Displayed");
		if (industryInfo.isDisplayed()) {
			action.moveToElement(industryInfo).perform();
			assertTrue("Industry Information is displayed");
		} else
			assertFalse("Industry Information is not Displayed");
		if (addMemberLink.isDisplayed()) {
			action.moveToElement(addMemberLink).perform();
			assertTrue("Add Member Link is displayed");
		} else
			assertFalse("Add Member Link is not Displayed");
		if (quickAction.isDisplayed()) {
			action.moveToElement(quickAction).perform();
			assertTrue("Quick Actions Link is displayed");
		} else
			assertFalse("Quick Actions Link is not Displayed");

	}

	public void checkQuickActoinDDown(RemoteWebDriver driver) throws Exception {
		Actions action = new Actions(driver); 
		click(quickAction, "Quick Actions"); 
		Thread.sleep(500);
		if (newTask.isDisplayed()) {
			action.moveToElement(newTask).perform();
			assertTrue("New Task Button is displayed");
		} else
			assertFalse("New Task Button is not Displayed");
		if (newNote.isDisplayed()) {
			action.moveToElement(newNote).perform();
			assertTrue("New Note Button is displayed");
		} else
			assertFalse("New Note Button is not Displayed");
		if (newMeeting.isDisplayed()) {
			action.moveToElement(newMeeting).perform();
			assertTrue("New Meeting Button is displayed");
		} else
			assertFalse("New Meeting Button is not Displayed");
		if (newDocument.isDisplayed()) {
			action.moveToElement(newDocument).perform();
			assertTrue("New Document Button is displayed");
		} else
			assertFalse("New Document Button is not Displayed");
	}

	public void checkNewDocument(RemoteWebDriver driver) throws Exception {
		click(newDocument, "New Document");
		Thread.sleep(500);
		if(driver.findElements(By.xpath("//app-create-new-document")).size() > 0) {
			assertTrue("'Create New Document' Pop-up Window is displayed.");
			if(onePager.isDisplayed())
				assertTrue("One Pager Option is displayed");
			else
				assertFalse("One Pager Option is not displayed");
			if(fieldReport.isDisplayed())
				assertTrue("Field Report Option is displayed");
			else
				assertFalse("Field Report Option is not displayed");
			if(ICNote.isDisplayed())
				assertTrue("IC Note Option is displayed");
			else
				assertFalse("IC Note Option is not displayed");
			if(closeBtn.isDisplayed())
				assertTrue("Close Button is displayed");
			else
				assertFalse("Close Button is displayed");
			
			click(closeBtn, "Close Button");
			Thread.sleep(500);
			
		} else
			assertFalse("'Create New Document' Pop-up Window is not displayed.");
	}
	
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
				assertTrue("All " + items + " Options in Drop-Down List are Disabled and change is not possible.");
			else
				assertFalse("All " + items + " Options in Drop-Down List are not Disabled.");
		} else
			assertFalse("Passing Reason Pop-up Window is not displayed");
	}

	public void testRecentActivities(RemoteWebDriver driver) throws Exception {
		Actions action = new Actions(remoteDriver);
		if (checkCount == 0) {
			Thread.sleep(1000);
			action.moveToElement(recentActs).click().build().perform();
			if (driver.findElements(By.xpath("//div[@class='activity-header']")).size() > 0) {
				assertTrue("Recent Activities card displayed.");
				assertTrue("Unread activities shown in badge: " + recentActBadge.getText().trim());
				action.moveByOffset(200, 10).click().build().perform();
				Thread.sleep(100);
				action.moveToElement(recentActs).click().build().perform();
				if (!(driver.findElements(By.xpath("//span[contains(@class,'badge')]")).size() > 0))
					assertTrue("Unread activities badge becomes Nil on second viewing.");
				action.moveByOffset(200, 10).click().build().perform();
				Thread.sleep(200);
				checkCount++;
			} else
				assertFalse("Recent Activities card NOT displayed.");
		} else {
			action.moveToElement(recentActs).click().build().perform();
			Thread.sleep(200);
			if(recentActBadge.getText().trim().equals("1"))
				assertTrue("New Activity is reflected in Recent Activities.");
			else
				assertFalse("New Activity is NOT reflected in Recent Activities.");
			action.moveByOffset(200, 10).click().build().perform();
			Thread.sleep(200);
			action.moveToElement(recentActs).click().build().perform();
			Thread.sleep(200);
			action.moveByOffset(200, 10).click().build().perform();
			Thread.sleep(200);
			
		}
	}
}
