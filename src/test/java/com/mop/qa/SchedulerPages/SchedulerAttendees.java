package com.mop.qa.SchedulerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SchedulerAttendees extends PageBase {
	public SchedulerAttendees(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//div[contains(text(),'Attendees')]")
	WebElement subHeaderAttendees;
	@FindBy(xpath = "//input[@placeholder='Attendees']")
	WebElement inputAttendees;
	@FindBy(xpath = "//div[contains(@class,'cardCopy')]")
	WebElement listAttendees;
	@FindBy(xpath = "//div[contains(@class,'cardCopy')][1]")
	WebElement contactCard_1;
	@FindBy(xpath = "//div[contains(@class,'addProjectTeam')]")
	WebElement linkAddProjectTeam;
	@FindBy(xpath = "//img[contains(@src,'plus')]")
	WebElement btnPlus;
	@FindBy(xpath = "//img[contains(@src,'minus')]")
	WebElement btnMinus;
	@FindBy(xpath = "//div[contains(@class,'previousButton')]")
	WebElement btnPrevious;
	@FindBy(xpath = "//div[contains(text(),'NEXT')]")
	WebElement btnNext;
	@FindBy(xpath = "//*[@class='closeIcon']")
	WebElement btn_Close;

	public void enterAttendees(RemoteWebDriver driver, String attend, String addAttend) throws Exception{
		//Attendee by Search
		click(inputAttendees, "Attendees Input");
		Thread.sleep(500);
		String att = "//div[text()='HOLDER']";
		enterText(inputAttendees, attend, "User Name");
		if(driver.findElements(By.xpath(att.replace("HOLDER", attend))).size()>0) {
			click(driver.findElement(By.xpath(att.replace("HOLDER", attend))), attend);
			if(driver.findElement(By.xpath("//mat-chip/span")).getText().trim().equals(attend))
				assertTrue("attendee added");
		}
		else
			assertFalse(attend+ " User Not Found");
		Thread.sleep(500);
		
		//Attendee by contact Cards
		click(contactCard_1, "Contact Card 1");
		Thread.sleep(100);
		if(driver.findElements(By.xpath("//mat-chip")).size()>0)  			//CHANGE COMPARE TO '1'
			assertTrue("Attendee added by Contact Card");
		else
			assertFalse("Attendee not added by Contact Card");
		
		//Attendee by 'add project team'
		click(linkAddProjectTeam, "Add Project Team"); 
		Thread.sleep(100);
		if(driver.findElements(By.xpath("//mat-chip")).size()>2)
			assertTrue("Attendee added by Add Project Team");
		else
			assertTrue("Attendee not added by Add Project Team");
		
		//plus button.
		for (int i = 1; i <= Integer.parseInt(addAttend); i++) {
			click(btnPlus, "+ Attendee");
		}
		click(btnNext, "Next");
		Thread.sleep(1000);
	}

}
