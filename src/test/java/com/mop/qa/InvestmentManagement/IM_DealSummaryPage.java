package com.mop.qa.InvestmentManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_DealSummaryPage extends PageBase {

	public IM_DealSummaryPage(RemoteWebDriver driver) {
		super(driver);
	}

//	pop-up's
	@FindBy(css = "app-add-memeber")
	WebElement addMemberPop;
	@FindBy(css = "app-create-note")
	WebElement addNewNote;
	@FindBy(css = "app-create-new-document")
	WebElement addNewDocument;
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;

//	page Header
	@FindBy(css = "span.dd-title")
	WebElement dealName;
	@FindBy(css = "a[routerLink*=my-deals]")
	WebElement backArrow;
	@FindBy(css = "div.dd-header span.mat-select-value-text span")
	WebElement viewedStage;
	@FindBy(css = "a[title='Quick Actions']")
	WebElement quickActions;
//	quick Actions DropDown
	@FindBy(css = "ul.quick-action-menu")
	WebElement quickActionsDropDown;
	@FindBy(xpath = "//button[contains(text(),'New Task')]")
	WebElement newTask;
	@FindBy(xpath = "//button[contains(text(),'New note')]")
	WebElement newNote;
	@FindBy(xpath = "//button[contains(text(),'New Meeting')]")
	WebElement newMeeting;
	@FindBy(xpath = "//button[contains(text(),'New Document')]")
	WebElement newDocument;
//	deal Stage options
	@FindBy(css = "div.mat-select-panel")
	WebElement dealStageOpt;
	@FindBy(xpath = "//mat-option//span[contains(text(),'NEW')]")
	WebElement newStage;
	@FindBy(xpath = "//mat-option//span[contains(text(),'ONE PAGER')]")
	WebElement onePagerApproved;
	@FindBy(xpath = "//mat-option//span[contains(text(),'PRELIM IC')]")
	WebElement prelimICApproved;
	@FindBy(xpath = "//mat-option//span[contains(text(),'NBO EXECUTED')]")
	WebElement nboExecuted;
	@FindBy(xpath = "//mat-option//span[contains(text(),'FINAL IC')]")
	WebElement finalICApproved;
	@FindBy(xpath = "//mat-option//span[contains(text(),'DEAL SIGNED')]")
	WebElement dealSigned;
	@FindBy(xpath = "//mat-option//span[contains(text(),'PASSED')]")
	WebElement passed;
	@FindBy(xpath = "//mat-option//span[contains(text(),'CLOSED')]")
	WebElement closed;
//	sub-header
	@FindBy(css = "a.add-link")
	WebElement addMember;
	@FindBy(css = "span.rule-score span")
	WebElement ruleScore;
	@FindBy(css = "a[title='Recent Activities']")
	WebElement recentActivities;
	@FindBy(css = "span.badge")
	WebElement countBadge;
//	tabs and section
	@FindBy(xpath = "//a[text()='Deal Details']")
	WebElement dealDetails;
	@FindBy(xpath = "//a[text()='Hypothesis']")
	WebElement hypothesis;
	@FindBy(css = "a[routerLink=documents]")
	WebElement folders;
	@FindBy(css = "a[routerLink=notes]")
	WebElement notes;

//	sector details data
	@FindBy(xpath = "//p[text()='Sub-sector']/following-sibling::p")
	WebElement subSectorData;
	@FindBy(xpath = "//p[text()='Stake']/following-sibling::p")
	WebElement stakeData;
	@FindBy(xpath = "//ul[contains(@class,'sector-details')]//span[text()='$']/parent::p")
	WebElement dealSizeData;
	@FindBy(xpath = "//p[text()='Source']/following-sibling::p")
	WebElement sourceData;
	@FindBy(xpath = "//p[text()='R&A Status']/following-sibling::p/span")
	WebElement rnaCircle;
	@FindBy(xpath = "//p[text()='Portfolio Fit']/following-sibling::p/span")
	WebElement portCircle;
//	sector edit
	@FindBy(css = "div.dd-sector-details")
	WebElement sectorSection;
	@FindBy(css = "im-sector-details a.edit-icon")
	WebElement editSector;
	@FindBy(css = "input[formcontrolname=sourceName]")
	WebElement source;
	@FindBy(css = "mat-select[formcontrolname='RNAStatusControl']")
	WebElement rnaStatus;
	@FindBy(css = "mat-select[formcontrolname='portfolioFitControl']")
	WebElement portfolioFit;
	String likeOptions = "mat-option[value=OPTION]"; // positive - negative - neutral --- REPLACE 'OPTION' [css]

//	universal
	@FindBy(css = "button.btn")
	WebElement saveBtn;
	@FindBy(css = "button.link")
	WebElement cancelBtn;

//	financials
	@FindBy(xpath = "//div[@class='dd-panel'][2]")
	WebElement finSection;

//	attractiveness Index
	@FindBy(css = "div.attractiveness-content")
	WebElement indexCard;

	public void analyzePage(RemoteWebDriver driver) throws Exception {
		Actions action = new Actions(driver);
		assertTrue("Deal Name is shown as: " + dealName.getText().trim(), dealName.isDisplayed());
//		deal stage
		if (viewedStage.getText().contains("NEW")) {
			assertTrue("Default deal Stage is 'NEW'");
			click(viewedStage, "Deal Stage Dropdown");
			Thread.sleep(250);
			assertTrue("Deal Stage options are displayed", dealStageOpt.isDisplayed());
			click(newStage, "New Stage");
		} else
			assertFalse("default deal stage is not 'NEW'");
//		quick actions
		click(quickActions, "Quick Actions");
		Thread.sleep(250);
		assertTrue("Quick Actions Drop Down displayed", quickActionsDropDown.isDisplayed());
//		new note popup
		click(newNote, "New Note");
		Thread.sleep(250);
		if (addNewNote.isDisplayed())
			assertTrue("New Note Popup is displayed");
		else
			assertFalse("New Note pop-up is not displayed");
		click(closeBtn, "Close");
		Thread.sleep(250);
		assertTrue("Landed back to deal summary", driver.findElements(By.cssSelector("app-create-note")).size() == 0);
//		new document popup
		click(quickActions, "Quick Actions");
		Thread.sleep(250);
		click(newDocument, "New Document");
		Thread.sleep(250);
		if (addNewDocument.isDisplayed())
			assertTrue("New Document Popup is displayed");
		else
			assertFalse("New Document pop-up is not displayed");
		click(closeBtn, "Close");
		Thread.sleep(250);
		assertTrue("Landed back to deal summary",
				driver.findElements(By.cssSelector("app-create-new-document")).size() == 0);
//		add member popup
		click(addMember, "Add");
		Thread.sleep(250);
		if (addMemberPop.isDisplayed())
			assertTrue("Add Member Popup is displayed");
		else
			assertFalse("Add member pop-up is not displayed");
		click(closeBtn, "Close");
		Thread.sleep(250);
		assertTrue("Landed back to deal summary", driver.findElements(By.cssSelector("app-add-memeber")).size() == 0);
//		rule score
		if (ruleScore.isDisplayed())
			assertTrue("Rule Score is displayed with value: " + ruleScore.getText() + " %");
		else
			assertFalse("Rule Score is not displayed");
//		recent activities
		click(recentActivities, "Recent Activities");
		if (countBadge.isDisplayed())
			assertTrue("Unread activities is shown as count: " + countBadge.getText());
		action.moveByOffset(50, 0).click().build().perform();
		Thread.sleep(500);

		if (finSection.isDisplayed()) {
			action.moveToElement(indexCard).build().perform();
			assertTrue("Financials Section is displayed");
		} else
			assertFalse("Financials Section is not displayed");
	}

	public void analyzeSectorDetails(RemoteWebDriver driver, String dealSource, String RNA, String portfolio)
			throws Exception {
		Actions action = new Actions(driver);
		if (subSectorData.isDisplayed()) {
			action.moveToElement(subSectorData).perform();
			assertTrue("Subsector is displayed as: " + subSectorData.getText());
		} else
			assertFalse("Subsector is NOT displayed");
		Thread.sleep(100);
		if (stakeData.isDisplayed()) {
			action.moveToElement(stakeData).perform();
			assertTrue("Stake is displayed as: " + stakeData.getText());
		} else
			assertFalse("Stake is NOT displayed");
		Thread.sleep(100);
		if (dealSizeData.isDisplayed()) {
			action.moveToElement(dealSizeData).perform();
			assertTrue("Deal Size is displayed as:" + dealSizeData.getText());
		} else
			assertFalse("Deal Size is NOT displayed");
		Thread.sleep(100);
		action.moveToElement(sectorSection).perform();
		click(editSector, "Edit Sector Pencil Icon");
		Thread.sleep(100);
		click(source, "Source");
		enterText(source, dealSource, "Source");
		Thread.sleep(250);
		if (driver.findElements(By.xpath("//mat-option")).size() > 0) {
			assertTrue("Source Options Displayed");
			click("//mat-option", "Source Option");
		} else
			assertFalse("Source options are not displayed");
		Thread.sleep(250);
		click(rnaStatus, "RNA Status");
		Thread.sleep(250);
		click(driver.findElement(By.cssSelector(likeOptions.replace("OPTION", RNA))), "RNA Option");
		Thread.sleep(250);
		click(portfolioFit, "Portfolio Fir");
		Thread.sleep(250);
		click(driver.findElement(By.cssSelector(likeOptions.replace("OPTION", portfolio))), "POrtfolio Option");
		Thread.sleep(250);
		click(saveBtn, "Save");
		Thread.sleep(250);
		if (sourceData.isDisplayed()) {
			action.moveToElement(sourceData).perform();
			assertTrue("Source is displayed as:" + sourceData.getText());
		} else
			assertFalse("Source is NOT Saved");
		action.moveToElement(rnaCircle).perform();
		assertTrue("R&A Status Circle is shown", rnaCircle.isDisplayed());
		assertTrue("Portfolio Circle is shown", portCircle.isDisplayed());
	}
}
