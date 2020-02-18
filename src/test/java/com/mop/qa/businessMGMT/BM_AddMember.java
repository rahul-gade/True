package com.mop.qa.businessMGMT;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_AddMember extends PageBase {

	public BM_AddMember(RemoteWebDriver driver) {
		super(driver);
	}

	String memberName = "";

//	add member popup
	@FindBy(css = "a.add-link")
	WebElement add;
	@FindBy(css = "input[formcontrolname=searchTitle]")
	WebElement searchProfile;
	@FindBy(css = "span.searching-text")
	WebElement addAsProfile;
	@FindBy(css = "app-confirm-new-profile-type")
	WebElement profileTypeDialog;
	@FindBy(xpath = "//div[text()='PC']") // ancestor::mat-radio-button
	WebElement PCRadio;
	String profile = "//span[contains(text(),'NAME')]";
	@FindBy(css = "img.cross-header")
	WebElement close;

//	add new profile popup
	@FindBy(css = "input[formcontrolname=designation]")
	WebElement designation;
	@FindBy(css = "input[formcontrolname=location]") // input
	WebElement location;
	String locx = "//mat-option/span[contains(text(),'LOCATION')]"; // input
	@FindBy(css = "input[formcontrolname=contact_number]") // create using DATE
	WebElement contact;
	@FindBy(css = "div.phone-wrapper span.slider")
	WebElement makePhoneDefault;
	@FindBy(css = "input[formcontrolname=email_id]") // hard code
	WebElement mail;
	@FindBy(css = "div.emailRows span.slider")
	WebElement makeMailDefault;
	@FindBy(css = "button.btn")
	WebElement submit;

	public void verifyMember(RemoteWebDriver driver, String name) throws Exception {
		if (driver.findElementsByXPath(profile.replace("NAME", name)).size() > 0)
			assertTrue("Member Added Successfully");
		else
			assertFalse("Member Not Added");

		click(close, "Close");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-add-memebers").size() == 0)
			assertTrue("Landed Back to company page");
		else
			assertFalse("Did not land to copany page");
	}

	public void addProfile(RemoteWebDriver driver, String loc, String desig, String phone, String email)
			throws Exception {
		click(designation, "Designation");
		enterText(designation, desig, "Designation");
		Thread.sleep(250);
		click(location, "Location");
		enterText(location, loc, "Location");
		do {
			Thread.sleep(1000);
		} while (driver.findElementsByCssSelector("img[src*='spinner']").size() > 0);
		click(locx.replace("LOCATION", loc), "Location Option - " + loc);
		Thread.sleep(250);

		DateFormat dateFormat = new SimpleDateFormat("yyyymmHHmm");
		Date date = new Date();
		click(contact, "Phone Number");
		enterText(contact, dateFormat.format(date), "Phone Number");
		Thread.sleep(250);
		click(mail, "E-Mail");
		enterText(mail, dateFormat.format(date) + email, "E-Mail");
		Thread.sleep(250);

		click(submit, "SUBMIT");
		Thread.sleep(100);
		if (driver.findElementsByXPath("//*[contains(text(),'default contact')]").size() > 0)
			assertTrue("Contact error message displayed properly");
		else
			assertFalse("error toast not diaplayed");
		Thread.sleep(2500);
		click(makePhoneDefault, "Default Phone Number"); // make contact default

		click(submit, "SUBMIT");
		Thread.sleep(100);
		if (driver.findElementsByXPath("//*[contains(text(),'default email')]").size() > 0)
			assertTrue("Contact error message displayed properly");
		else
			assertFalse("error toast not diaplayed");
		Thread.sleep(2500);
		click(makeMailDefault, "Default Email"); // make mail default

		Thread.sleep(250);
		click(submit, "SUBMIT");
		Thread.sleep(1500);
		if (driver.findElementsByTagName("app-add-new-profile").size() > 0)
			assertFalse("Pop-up still Present"); // clear this thing
		else
			assertTrue("Landed back to Add Team Members Pop-up");
	}

	public void addMember(RemoteWebDriver driver, String pName) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("HHmm");
		Date date = new Date();
		click(searchProfile, "Search Profile");
		memberName += pName + dateFormat.format(date);
		enterText(searchProfile, memberName, "Search Profile");
		Thread.sleep(250);
		click(addAsProfile, "Add " + memberName + " As Profile");
		Thread.sleep(500);
		assertTrue("Profile Type Selection Dialog opened", profileTypeDialog.isDisplayed());
//		type selection
		click(PCRadio, "PC");
		click(submit, "SUBMIT");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-add-new-profile").size() > 0)
			assertTrue("Add new profile dialog displayed");
		else
			assertFalse("Add new profile dialog NOT displayed");
	}

	public void openPopUp(RemoteWebDriver driver) throws Exception {
		click(add, "ADD");
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-add-memebers").size() > 0)
			assertTrue("Add Member pop-up is displayed");
		else
			assertFalse("Add Member pop-up is displayed");
	}
}
