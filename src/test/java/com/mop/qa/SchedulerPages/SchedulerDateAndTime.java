package com.mop.qa.SchedulerPages;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SchedulerDateAndTime extends PageBase {
	public SchedulerDateAndTime(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//div[contains(text(),'Date and Time')]")
	WebElement subHeaderDateAndTime;
	@FindBy(xpath = "//mat-select[contains(@panelclass,'durationDropdown')]")
	WebElement drpdwnDuration;
	@FindBy(xpath = "//div[contains(@class,'customizeText')]")
	WebElement linkCustomize;
	@FindBy(xpath = "//div[contains(@class,'previousButton')]")
	WebElement btnPrevious;
	@FindBy(xpath = "//div[contains(text(),'NEXT')]")
	WebElement btnNext;
	@FindBy(xpath = "//*[@class='closeIcon']")
	WebElement btn_Close;
	@FindBy(xpath = "//div[@class='rightSideNextIcon']/img")
	WebElement slotRightArrow;
	@FindBy(xpath = "//app-new-meeting-schedule//img[@id='openSchedulerCalendar']")
	WebElement startDatePicker;
	@FindBy(xpath = "//app-scheduler-calendar//div[contains(@style,'background-image')]")
	WebElement defaultDate;
	@FindBy(xpath = "//app-scheduler-calendar//div[contains(@style,'linear-gradient')]/parent::div/following-sibling::div")
	WebElement nextToDefDate;
	@FindBy(xpath = "//img[contains(@src,'next-s-secondary')]")
	WebElement nextMonthArrow;
	@FindBy(css = "div#doneButton")
	WebElement doneButton;
	@FindBy(xpath = "//div[@class='startDateTimeOptions']")
	WebElement startTime;
	@FindBy(xpath = "//div[@class='endDateTimeOptions']")
	WebElement finishTime;
	
	String selectDuration = "//mat-option[contains(@class,'datTeime1')]//span[contains(text(),'PLACEHOLDER')]";
	String selectTimeSlot = "//div[contains(@class,'autoSuggestedSlots')]//div[contains(text(),'PLACEHOLDER')]";
	String timeOption = "//span[text()=' PLACEHOLDER ']";

	public String selectedDate = "";
	public String selectedTime = "";

	public void selectTimeSlot(RemoteWebDriver driver, String duration) throws Exception {
//		duration
		click(drpdwnDuration, "Duration Dropdown");
		Thread.sleep(500);
		if (driver.findElement(By.xpath("//mat-option[1]")).getText().equals("0.5 HOUR")
				&& driver.findElement(By.xpath("//mat-option[20]")).getText().equals("2 WEEKS"))
			assertTrue("Duration options available from 0.5 HOUR - 2 WEEKS");
		if (driver.findElements(By.xpath(selectDuration.replace("PLACEHOLDER", duration))).size() > 0) {
			driver.findElement(By.xpath(selectDuration.replace("PLACEHOLDER", duration))).click();
			Thread.sleep(100);
		} else
			assertFalse("Duration option not found");
		Thread.sleep(200);
		
//		time slot [find no. of slots, if more than 1, select last! ;)]
		int slots = driver.findElements(By.xpath("//div[@class='autoSuggestedSlots']//*[contains(text(),'M')]")).size();
		if (slots > 1)
			click(driver.findElement(
					By.xpath("(//div[@class='autoSuggestedSlots']//*[contains(text(),'M')])[" + slots + "]")),
					"Last Available Time Slot.");
		else {
			while(slots<1) {
				click(slotRightArrow, "Next Slot Set.");
				slots = driver.findElements(By.xpath("//div[@class='autoSuggestedSlots']//*[contains(text(),'M')]")).size();
			}
			click(driver.findElement(
					By.xpath("(//div[@class='autoSuggestedSlots']//*[contains(text(),'M')])[" + slots + "]")),
					"Last Available Time Slot.");
		}
		Thread.sleep(500);

		String fDate = driver.findElement(By.cssSelector("div.dateReflector")).getText().trim();
		String fTime = driver.findElement(By.cssSelector("div.timeReflector")).getText().trim();
		selectedDate = fDate.replaceAll("[^0-9A-Za-z]", "").replace("st", "").replace("nd", "").replace("rd", "")
				.replace("th", "");
		System.out.println("LOG============SCHEDULER DATE AND TIME============\nDATE:   "+selectedDate);
//		selectedTime = fTime.replaceAll("[^0-9A-Z]", "");
		System.out.println("LOG============SCHEDULER DATE AND TIME============\nTIME:   "+fTime);

		click(btnNext, "Next Button");
		Thread.sleep(1000);
	}

	public void enterCustomeDateTime(RemoteWebDriver driver, String beginTime, String endTime) throws Exception {
		click(linkCustomize, "Custom Date");
		Thread.sleep(200);
		click(startDatePicker, "Date Picker Start Date.");
		Thread.sleep(100);
		
		//DATE - next date of month.
		Integer lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		Integer defDate = Integer.parseInt(defaultDate.getText().trim());
		if(defDate==lastDay) {
			click(nextMonthArrow, "Next Month");
			click(driver.findElement(By.xpath("(//div[text()=' 1 '])[1]")),"First Date of Next Month.");
		} else
			click(nextToDefDate, "Next Day");
		click(doneButton, "Done");
		Thread.sleep(100);
		
		//TIME - 
		click(startTime, "Start Time");
		Thread.sleep(200);
		click(driver.findElement(By.xpath(timeOption.replace("PLACEHOLDER", beginTime))), "Begin Time Option");
		click(finishTime, "End Time");
		Thread.sleep(200);
		click(driver.findElement(By.xpath(timeOption.replace("PLACEHOLDER", endTime))), "End Time Option");
		
		click(btnNext, "Next Button");
	}
}
