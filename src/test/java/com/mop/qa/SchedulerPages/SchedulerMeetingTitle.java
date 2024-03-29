package com.mop.qa.SchedulerPages;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	@FindBy(css = "div.sectionTitle")
	WebElement sectionTitle;
	
	public String m_Title = "";
	
	public void enterTitleandProject(RemoteWebDriver driver, String meetingTitle, String projectName) throws Exception{
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
			m_Title = meetingTitle + " - "+ formatter.format(date);
			click(inputMeetingTitle, "MeetingTitle");
			enterText(inputMeetingTitle, m_Title, "MeetingTitle");
			Thread.sleep(200);
			click(drpdwnProject, "Project DropDown");
			Thread.sleep(500);
			driver.findElement(By.xpath(project.replace("PLACEHOLDER", projectName))).click();
			Thread.sleep(500);
			click(btnNext, "NEXT button");
			Thread.sleep(1000);
			if(sectionTitle.getText().trim().equalsIgnoreCase("Attendees"))
				assertTrue("Landed on Attendees Page");
			else
				assertFalse("Attendees Page Not Displayed.");
	}
	
	
}
