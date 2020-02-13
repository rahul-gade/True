package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Company_Overview extends PageBase {

	public BM_Company_Overview(RemoteWebDriver driver) {
		super(driver);
	}

//	quickActions
	@FindBy(css = "img.share-icon")
	WebElement quickActions;
	@FindBy(css = "img[src*='new-task']")
	WebElement newTask;
	@FindBy(css = "img[src*='new-note']")
	WebElement newNote;
	@FindBy(css = "img[src*='new-meet']")
	WebElement newMeeting;
	@FindBy(css = "img[src*='close']")
	WebElement close; // <== Meeting + Task + upload-MIS
	@FindBy(css = "a.close-btn")
	WebElement closeBtn; // <== Note

//	tabs
	@FindBy(xpath = "//a[text()='Overview']")
	WebElement tabOverview;
	String li = "./parent::li"; // <== to test active state

//	other pop-ups
	@FindBy(css = "a.add-link")
	WebElement add;
	@FindBy(css = "span.document")
	WebElement uploadMIS;
	@FindBy(css = "button.btn")
	WebElement confirmBtn;
	@FindBy(css = "ul.right-content a.link svg")
	WebElement recents;

	public void checkPopUps(RemoteWebDriver driver) throws Exception {
//		upload MIS
		click(uploadMIS, "Upload M.I.S.");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-upload-mis-dialog").size() > 0) {
			assertTrue("Upload MIS pop-up is displayed");
			click(close, "Cross Button");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-confirm-message-dialog").size() > 0) {
				assertTrue("Confirm Dialog displayed");
				click(confirmBtn, "Yes");
				Thread.sleep(750);
				if (driver.findElementsByTagName("app-upload-mis-dialog").size() == 0)
					assertTrue("Landed Back to COmpany Page");
				else
					assertFalse("Pop-up did not close");
			} else
				assertFalse("Confirm Dialog NOT displayed");
		} else
			assertFalse("Upload MIS pop-up is not displayed");

//		Recent Activities
		click(recents, "Recent activities");
		Thread.sleep(250);
		if (driver.findElements(By.cssSelector("div.activity-header")).size() > 0)
			assertTrue("Recent Activities Box displayed");
		else
			assertFalse("Recent Activities Box not displayed");
		Actions act = new Actions(driver);
		act.moveByOffset(50, 0).click().build().perform();
		Thread.sleep(500);

//		Add Team Members
		click(add, "ADD");
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-add-memebers").size() > 0) {
			assertTrue("Add Member pop-up is displayed");
			click(close, "Cross Button");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-add-memebers").size() == 0)
				assertTrue("Landed Back to Company Page");
			else
				assertFalse("Pop-up did not close");
		} else
			assertFalse("Add Member pop-up is not displayed");
	}

	public void checkQuickActions(RemoteWebDriver driver) throws Exception {
		Thread.sleep(500);
		click(quickActions, "Quick Actions");
		Thread.sleep(250);
		if (driver.findElements(By.cssSelector("div.mat-menu-content")).size() > 0)
			assertTrue("Quick Actions Menu Displayed");
		else
			assertFalse("Quick Actions Menu Not Displayed");
//		task pop-up
		click(newTask, "New Task");
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-new-task-wrapper").size() > 0) {
			assertTrue("New task pop-up is displayed");
			click(close, "Cross Button");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-new-task-wrapper").size() == 0)
				assertTrue("Landed Back to Company Page");
			else
				assertFalse("Pop-up did not close");
		} else
			assertFalse("New task pop-up is not displayed");

//		note pop-up
		click(quickActions, "Quick Actions");
		Thread.sleep(250);
		click(newNote, "New Note");
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-new-note").size() > 0) {
			assertTrue("New note pop-up is displayed");
			click(closeBtn, "Cross Button");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-new-note").size() == 0)
				assertTrue("Landed Back to COmpany Page");
			else
				assertFalse("Pop-up did not close");
		} else
			assertFalse("New note pop-up is not displayed");

//		meeting pop-up
		click(quickActions, "Quick Actions");
		Thread.sleep(250);
		click(newMeeting, "New Meeting");
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-new-meeting").size() > 0) {
			assertTrue("New Meeting pop-up is displayed");
			click(close, "Cross Button");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-new-meeting").size() == 0)
				assertTrue("Landed Back to COmpany Page");
			else
				assertFalse("Pop-up did not close");
		} else
			assertFalse("New meeting pop-up is not displayed");
	}

	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(tabOverview, "Overview");
		Thread.sleep(250);
		if (tabOverview.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Overview Tab Active");
		else
			assertFalse("Overview Tab not activated");
	}
}
