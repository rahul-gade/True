package com.mop.qa.SchedulerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SchedulerMeetingTitle extends PageBase{
	public SchedulerMeetingTitle(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	
	@FindBy(xpath = "//div[contains(@class,'headerText')]")
	WebElement newMeetingHeader;
	@FindBy(xpath = "//div[contains(@class,'scheduleDeepwork')]")
	WebElement linkScheduleDeepwork;
	@FindBy(xpath = "//div[contains(@class,'sectionTitle')]")
	WebElement subHeaderTitle;	
	@FindBy(xpath = "//input[@placeholder='Meeting Title']")
	WebElement inputMeetingTitle;
	@FindBy(xpath = "//span[text()='Project']")
	WebElement drpdwnProject;
	String project = "//span[contains(text(),' PLACEHOLDER')]";
	@FindBy(xpath = "//div[contains(text(),'NEXT')]")
	WebElement btnNext;
	@FindBy(css = "div.headerCloseIcon")
	WebElement popUpClose;
	@FindBy(css = "span.icon")
	WebElement newSchedule;
	
	
	public void enterTitleandProject(RemoteWebDriver driver, String meetingTitle, String projectName) throws Exception{
		Thread.sleep(1000);
		click(newSchedule, "Schedule");
		Thread.sleep(500);
		if(newMeetingHeader.getText().trim().equals("NEW MEETING")) {
			assertTrue("Landed on New Meeting Screen");
			click(inputMeetingTitle, "MeetingTitle");
			enterText(inputMeetingTitle, meetingTitle, "MeetingTitle");
			Thread.sleep(200);
			click(drpdwnProject, "Project DropDown");
			Thread.sleep(500);
			driver.findElement(By.xpath(project.replace("PLACEHOLDER", projectName))).click();
			Thread.sleep(500);
			click(btnNext, "NEXT button");
			Thread.sleep(1000);
		} else
			assertFalse("New Meeting Screen did not open.");
	}
	
	
}
