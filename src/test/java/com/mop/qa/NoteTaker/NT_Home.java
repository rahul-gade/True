package com.mop.qa.NoteTaker;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class NT_Home extends PageBase {

	public NT_Home(RemoteWebDriver driver) {
		super(driver);
	}

//	@FindBy(css = "div.unfiled-notes-header")
//	WebElement unfiledNotes;
	@FindBy(css = "div.unfiled-notes-header-text")
	WebElement unfiledNotes;
	String note = "//div[contains(text(),'NOTETEXT')]";
	String bmC = "//span[contains(text(),'BMCXX')]";
	@FindBy(css = "div.unfiled-notes-count")
	WebElement notesCount;

//	note options
	@FindBy(css = "div.action-icon")
	WebElement noteAction;
	@FindBy(xpath = "//div[text()=' DELETE ']")
	WebElement delete;
	@FindBy(css = "button.createButtonEnabled")
	WebElement delBtn;

	public void deleteNote(RemoteWebDriver driver, String tabTitle) throws Exception {
		click(note.replace("NOTETEXT", tabTitle), "Note Created from Tab");
		Thread.sleep(500);
		click(noteAction, "Notes Action Menu");
		Thread.sleep(250);
		click(delete, "DELETE");
		Thread.sleep(250);
		click(delBtn, "Yes, Confirm");

//		confirm toast message and note delete
		if (driver.findElementsByXPath("//*[contains(text(),'deleted successfully')]").size() > 0)
			assertTrue("Toast message displayed");
		else
			assertFalse("Toast message not displayed");
		Thread.sleep(1000);
		if (driver.findElementsByXPath(note.replace("NOTETEXT", tabTitle)).size() == 0)
			assertTrue("Note Deleted Successfully");
		else
			assertFalse("Note not deleted.");
	}

	public void findBMNotes(RemoteWebDriver driver, String tabTitle, String qANTitle, String company) throws Exception {
//		Close the Unfiled notes section
		click(unfiledNotes, "Unfiled Notes Header");

//		change company name to add preceeding text -> 'BM-'
		company = "BM-" + company;
//		tab note
		if (driver.findElementsByXPath(note.replace("NOTETEXT", tabTitle)).size() > 0) {
			click(note.replace("NOTETEXT", tabTitle), "Note Created from Tab");
			assertTrue("Note created from Notes Tab in BM found");
			if (driver.findElementsByXPath(bmC.replace("BMCXX", company)).size() > 0)
				assertTrue("Note is filed to: " + company);
			else
				assertFalse("Note is not filed to corressponding company");
			Thread.sleep(500);
		} else
			assertFalse("Note created from Notes Tab in BM Not found");

//		quick actions note
		if (driver.findElementsByXPath(note.replace("NOTETEXT", qANTitle)).size() > 0) {
			click(note.replace("NOTETEXT", qANTitle), "Note Created from Quick Actions");
			assertTrue("Note created from Quick Actions in BM found");
			if (driver.findElementsByXPath(bmC.replace("BMCXX", company)).size() > 0)
				assertTrue("Note is filed to: " + company);
			else
				assertFalse("Note is not filed to corressponding company");
			Thread.sleep(500);
		} else
			assertFalse("Note created from Quick Actions in BM Not found");
	}

	public void openNoteTaker(RemoteWebDriver driver, String uRL) throws Exception {
		enterUrl(uRL);
		Thread.sleep(1000);
		waitForPageLoad();
		if (driver.findElementsByTagName("app-note-taker-sidebar").size() > 0)
			assertTrue("Note Taker Loaded successfully");
		else
			assertFalse("Note Taker Not Loaded");
	}

	public void confirmDelete(RemoteWebDriver driver, String qANTitle) throws Exception {
//		Close the Unfiled notes section
		click(unfiledNotes, "Unfiled Notes Header");

//		quick actions note
		if (driver.findElementsByXPath(note.replace("NOTETEXT", qANTitle)).size() == 0) {
			assertTrue("Note Deleted successfuly");
		} else
			assertFalse("Note not deleted from Note taker");
	}
}
