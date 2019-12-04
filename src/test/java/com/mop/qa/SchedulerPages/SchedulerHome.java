package com.mop.qa.SchedulerPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SchedulerHome extends PageBase {
	public SchedulerHome(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	// Landing Page Interaction Elements.
	@FindBy(xpath = "//div[contains(@class,'headerText')]")
	WebElement newMeetingHeader;
	@FindBy(css = "span.icon")
	WebElement newSchedule;
	@FindBy(css = "div.headerCloseIcon")
	WebElement popUpClose;
	@FindBy(xpath = "//div[@class='DarkslotBorder']/ancestor::div[contains(@class,'mainViewDayContentsSingleSlot')]/following-sibling::div") 
	List<WebElement> availableSlots;
	String currentSlot = "(//div[@class='DarkslotBorder']/ancestor::div[contains(@class,'mainViewDayContentsSingleSlot')]/following-sibling::div)[NUMBER]";
	String meetingCard = "//div[contains(@class,'slotContentContainer')]";
	String emptySlot = "//div[@class='allMeetingsInASlot']";
	@FindBy(css = "div.allMeetingsInASlot")
	WebElement clickableSlot;

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
				if (driver.findElements(By.cssSelector("span.icon")).size() > 0)
					assertTrue("+ icon for SCHEDULE is displayed.");
				else
					assertFalse("+ icon for SCHEDULE is NOT displayed.");
				if (driver.findElements(By.cssSelector("span.rightIcon")).size() > 0)
					assertTrue("Toggle Icon is displayed.");
				else
					assertFalse("Toggle Icon is NOT displayed.");

				click(newSchedule, "Schedule");
				Thread.sleep(500);
				if (driver.findElements(By.xpath("//div[text()=' NEW MEETING ']")).size() > 0) {
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

	public void testMeetingInvite(RemoteWebDriver driver) {
		// TODO Auto-generated method stub
	}

	public void testCreatedMeeting(RemoteWebDriver driver, String m_Title) {
		// TODO Auto-generated method stub
	}

	public void findSlot(RemoteWebDriver driver) throws Exception {
//		Integer slots = availableSlots.size();
//		for(Integer i=1; i<=slots; i++) {
//			if(driver.findElements(By.xpath(currentSlot.replace("NUMBER", i.toString())+meetingCard)).size() == 0) {
//				click(driver.findElement(By.xpath(currentSlot.replace("NUMBER", i.toString())+emptySlot)),"Empty Slot");
//				Thread.sleep(1000);
//				if(driver.findElements(By.tagName("app-new-meeting-schedule")).size() >0) {
//					assertTrue("Journey Started");
//					break;
//				} else
//					assertFalse("Failing As Expected.");
//					
//			}
//		}

//		for (WebElement slot : availableSlots) {
//			if (slot.findElements(By.xpath("." + meetingCard)).size() == 0) {
//				try {
//					click(slot.findElement(By.xpath("." + emptySlot)), "Empty Slot");
//				} catch (Exception e) {
//					System.out.println(e);
//					System.out.println("Not Found, Trying Again with Next!");
//					continue;
//				}
//				Thread.sleep(1000);
//				if (driver.findElements(By.tagName("app-new-meeting-schedule")).size() > 0) {
//					System.out.println("Journey Started");
//					assertTrue("Journey Started");
//					break;
//				}
//			}
//		}

		for (WebElement slot : availableSlots) {
			if (slot.findElements(By.cssSelector("div.slotContentContainer")).size() == 0) {
				try {
					click(slot.findElement(By.cssSelector("div.allMeetingsInASlot")), "Empty Slot");
				} catch (Exception e) {
					continue;
				}
				Thread.sleep(1000);
				if (driver.findElements(By.tagName("app-new-meeting-schedule")).size() > 0) {
					assertTrue("Journey Started");
					break;
				}
			}
		}
		if (driver.findElements(By.tagName("app-new-meeting-schedule")).size() == 0)
			assertFalse("No Slots Available Free in the day");
	}
	
	public void startScheduleJourney(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		click(newSchedule, "Schedule");
		Thread.sleep(500);
		if(newMeetingHeader.getText().trim().equals("NEW MEETING"))
			assertTrue("Landed on New Meeting Screen");
		else
			assertFalse("New Meeting Screen Not Opened.");
	}
}
