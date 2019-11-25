package com.mop.qa.SchedulerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SchedulerHome extends PageBase {
	public SchedulerHome(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	//Landing Page Interaction Elements. 
	@FindBy(css = "span.icon")
	WebElement newSchedule;
	@FindBy(css = "div.headerCloseIcon")
	WebElement popUpClose; 
	
	public void testHomePage(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		if (driver.findElements(By.cssSelector("div.main-header")).size() > 0) {
			assertTrue("Top Header is displayed.");
			if (driver.findElements(By.cssSelector(".secondLevelHeaderContainer")).size() > 0) {
				assertTrue("Second Level Header is displayed.");
				if (driver.findElements(By.xpath("//span[text()=' Schedule ']")).size() > 0)
					assertTrue("SCHEDULE is diaplayed.");
				else
					assertFalse("SCHEDULE is NOT diaplayed.");
				if(driver.findElements(By.cssSelector("span.icon")).size()>0)
					assertTrue("+ icon for SCHEDULE is displayed.");
				else
					assertFalse("+ icon for SCHEDULE is NOT displayed.");
				if(driver.findElements(By.cssSelector("span.rightIcon")).size()>0)
					assertTrue("Toggle Icon is displayed.");
				else
					assertFalse("Toggle Icon is NOT displayed.");
				
				click(newSchedule, "Schedule");
				Thread.sleep(500);
				if(driver.findElements(By.xpath("//div[text()=' NEW MEETING ']")).size()>0) {
					assertTrue("New Meeting Pop-Up is displayed.");
					click(popUpClose, "Close Icon");
					Thread.sleep(500);
				} else
					assertFalse("New Meeting Pop-Up is NOT displayed.");
			} else
				assertFalse("Second Level Header is NOT displayed.");
		} else
			assertFalse("Top Header is displayed.");
	}

	public void testMeetingInvit(RemoteWebDriver driver) {
		//TODO
	}
	
	public void testCreatedMeeting (RemoteWebDriver driver, String m_Title) {
		//TODO
	}
}
