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
	@FindBy(css = "div.view-more")
	WebElement viewMore;
	String note = "//div[contains(text(),'NOTETEXT')]";
	@FindBy(css = "div.unfiled-notes-count")
	WebElement notesCount;

//	note options
	@FindBy(css = "div.action-icon")
	WebElement noteAction;
	@FindBy(xpath = "//div[text()=' DELETE ']")
	WebElement delete;
	@FindBy(css = "button.createButtonEnabled")
	WebElement delBtn;

	public void deleteNote(RemoteWebDriver remoteDriver, String tabTitle) throws Exception {
		int notes = Integer.parseInt(notesCount.getText().trim());
		click(note.replace("NOTETEXT", tabTitle), "Note Created from Tab");
		Thread.sleep(500);
		click(noteAction, "Notes Action Menu");
		Thread.sleep(250);
		click(delete, "DELETE");
		Thread.sleep(250);
		click(delBtn, "Yes, Confirm");

		if (Integer.parseInt(notesCount.getText().trim()) < notes)
			assertTrue("Note Deleted form note Taker successfully");
		else
			assertFalse("Note Not deleted");
	}

	public void findBMNotes(RemoteWebDriver driver, String tabTitle, String qANTitle) throws Exception {
//		click VIEW MORE if available
		if (driver.findElementsByClassName("view-more").size() > 0)
			click(viewMore, "View More");
//		tab note
		if (driver.findElementsByXPath(note.replace("NOTETEXT", tabTitle)).size() > 0) {
			assertTrue("Note created from Notes Tab in BM found");
			click(note.replace("NOTETEXT", tabTitle), "Note Created from Tab");
			Thread.sleep(500);
		} else
			assertFalse("Note created from Notes Tab in BM Not found");

//		quick actions note
		if (driver.findElementsByXPath(note.replace("NOTETEXT", qANTitle)).size() > 0) {
			assertTrue("Note created from Quick Actions in BM found");
			click(note.replace("NOTETEXT", qANTitle), "Note Created from Tab");
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
//		click VIEW MORE if available
		if (driver.findElementsByClassName("view-more").size() > 0)
			click(viewMore, "View More");
		
//		quick actions note
		if (driver.findElementsByXPath(note.replace("NOTETEXT", qANTitle)).size() == 0) {
			assertTrue("Note Deleted successfuly");
		} else
			assertFalse("Note not deleted from Note taker");
	}
}
