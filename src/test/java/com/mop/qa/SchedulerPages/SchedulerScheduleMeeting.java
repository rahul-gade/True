package com.mop.qa.SchedulerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SchedulerScheduleMeeting extends PageBase {
	public SchedulerScheduleMeeting(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//div[contains(text(),'Schedule Meeting')]")
	WebElement subHeaderScheduleMeeting;
	@FindBy(xpath = "//div[contains(@class,'projectSelected')]")
	WebElement selectedProject;
	@FindBy(xpath = "//div[contains(@class,'fieldName')]")
	WebElement selectedMeetingTitle;
	@FindBy(xpath = "//div[contains(text(),'Date and Time')]/following-sibling::div")
	WebElement selectedDateTime;
	@FindBy(xpath = "//div[contains(text(),'Bridge')]/following-sibling::div")
	WebElement selectedBridge;
	@FindBy(xpath = "//div[contains(text(),'Location')]/following-sibling::div")
	WebElement selectedLocation;
	@FindBy(xpath = "//mat-select[contains(@panelclass,'flexibility')]")
	WebElement drpdwnFlexibility;
//	@FindBy(xpath = "//span[contains(text(),'PLACEHOLDER')]")
//	WebElement selectFlexibility;
	String selectFlexibility = "//span[contains(text(),'PLACEHOLDER')]";
	@FindBy(xpath = "//div[contains(@id,'addMore')]")
	WebElement linkAddMoreDetails;
	@FindBy(xpath = "//span[contains(text(),'ADD AGENDA')]")
	WebElement linkAddAgenda;
	@FindBy(xpath = "//textarea")
	WebElement agendaText;
	@FindBy(xpath = "//div[text()=' SAVE ']")
	WebElement saveBtn;
	@FindBy(xpath = "//div[contains(@class,'previousButton')]")
	WebElement btnPrevious;
	@FindBy(xpath = "//div[contains(@class,'buttonsContainer')]//div[contains(text(),'SCHEDULE')]")
	WebElement btnSchedule;
	@FindBy(xpath = "//*[@class='closeIcon']")
	WebElement btn_Close;

	public void checkMeetingDetails(RemoteWebDriver driver, String selectedDate, String selectedTime,
			String bridgeNumber, String selectedLocation2) throws Exception {
//		String finDateTime = selectedDateTime.getText().trim();
//		String finTime = finDateTime.substring(finDateTime.indexOf(',')+2);
//		String finDate = finDateTime.substring(0, finDateTime.indexOf(','));
//		
//		if((finTime.equals(selectedTime)) && (finDate.equals(selectedDate)))
//			assertTrue("Date and Time are same as entered during fill-up");
//		else
//			assertFalse("Date and Time are Not same as entered during fill-up");

		if (selectedBridge.getText().trim().equals(bridgeNumber))
			assertTrue("Bridge Number is correctly Saved.");
		else
			assertFalse("Bridge Number is NOT correctly Saved.");

		if (selectedLocation.getText().trim().equals(selectedLocation2))
			assertTrue("Location is saved Correctly.");
		else
			assertFalse("Location is NOT saved Correctly.");
//		System.out.println("\nDATE Saved from Before-> "+selectedDate);
//		System.out.println("\nDATE Obtained from Schedule Meeting Page-> "+finDate.replaceAll("[^a-zA-Z0-9]", ""));
//		System.out.println("\nTIME Saved from Before "+selectedTime);
//		System.out.println("\nTIME Obtained from Schedule Meeting Page-> "+finTime.replaceAll("[^a-zA-Z0-9]", ""));
//		System.out.println("********************************************\n");
	}

	public void enterFlexibilityAgenda(RemoteWebDriver driver, String flexibility, String Agenda) throws Exception {
		click(drpdwnFlexibility, "Flexibility");
		Thread.sleep(100);
		click(driver.findElement(By.xpath(selectFlexibility.replace("PLACEHOLDER", flexibility))),
				"Flexibility Option");
		Thread.sleep(100);
		click(linkAddAgenda, "Add Agenda");
		Thread.sleep(100);
		click(agendaText, "Agenda Text");
		agendaText.sendKeys(Agenda);
//		enterText(agendaText, Agenda, "Agenda Text");
		click(saveBtn, "Save");
		Thread.sleep(100);
	}

	public void scheduleMeeting(RemoteWebDriver driver) throws Exception {
		click(btnSchedule, "Schedule");
		Thread.sleep(1000);
		if (driver.findElements(By.cssSelector("section.rightSideContentContainer")).size() > 0) {
			assertTrue("Landed Back on Home Page.");
		}
	}

}
