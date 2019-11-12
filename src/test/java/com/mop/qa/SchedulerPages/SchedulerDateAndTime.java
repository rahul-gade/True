package com.mop.qa.SchedulerPages;

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
//	@FindBy(xpath = "//mat-option[contains(@class,'datTeime1')]//span[contains(text(),'PLACEHOLDER')]")
//	WebElement selectDuration;
	String selectDuration = "//mat-option[contains(@class,'datTeime1')]//span[contains(text(),'PLACEHOLDER')]";
//	@FindBy(xpath = "//div[contains(@class,'autoSuggestedSlots')]//div[contains(text(),'PLACEHOLDER')]")
//	WebElement selectTimeSlot;
	String selectTimeSlot = "//div[contains(@class,'autoSuggestedSlots')]//div[contains(text(),'PLACEHOLDER')]";
	@FindBy(xpath = "//div[contains(@class,'customizeText')]")
	WebElement linkCustomize;
	@FindBy(xpath = "//div[contains(@class,'previousButton')]")
	WebElement btnPrevious;
	@FindBy(xpath = "//div[contains(text(),'NEXT')]")
	WebElement btnNext;
	@FindBy(xpath = "//*[@class='closeIcon']")
	WebElement btn_Close;

	public String selectedDate = "";
	public String selectedTime = "";

	public void enterDateAndTime(RemoteWebDriver driver, String duration) throws Exception {
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
		Thread.sleep(500);

		String fDate = driver.findElement(By.cssSelector("div.dateReflector")).getText().trim();
		String fTime = driver.findElement(By.cssSelector("div.timeReflector")).getText().trim();
		selectedDate = fDate.replaceAll("[^0-9A-Za-z]", "").replace("st", "").replace("nd", "").replace("rd", "")
				.replace("th", "");
		selectedTime = fTime.replaceAll("[^0-9A-Z]", "");

		click(btnNext, "Next Button");
		Thread.sleep(1000);
	}

	public void enterCustomeDateTime(RemoteWebDriver driver, String beginDate, String endDate, String beginTime,
			String endTime) throws Exception {
		// TODO for custom date and time.
	}
}
