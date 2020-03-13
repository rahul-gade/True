package com.mop.qa.businessMGMT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Notes extends PageBase {

	public BM_Notes(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "li img[src*=notes]")
	WebElement NotesTab;
	@FindBy(css = "img.share-icon")
	WebElement quickActions;
	@FindBy(css = "img[src*='new-note']")
	WebElement newNote;
	@FindBy(css = "a.close-btn")
	WebElement closeBtn; // <== Note

	@FindBy(css = "span.new-note-icon")
	WebElement newNoteLink;
	String noteNode = "ul.bullet-list li:nth-of-type(NODE) div.nodeText";
	String tabTitle = "Automated Note from Tab";
	String qANTitle = "Automated Note from Quick Actions";
	int createdNotes = 0;
	@FindBy(css = "span.text-notification")
	WebElement badgeC;
	@FindBy(css = "button.btn")
	WebElement confirm;

	public void deleteNotes(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1500);
		int badgeCount = Integer.parseInt(badgeC.getText());
		while (badgeCount > 0) {
			WebElement delete = driver.findElementByCssSelector("div.notes-data-div-main img[src*=delete]");
			click(delete, "Delete Note");
			Thread.sleep(500);
			click(confirm, "Yes");
			Thread.sleep(1000);
			if (badgeCount == 1) {
				assertTrue("Note Deleted Successfully");
				badgeCount = 0;
				break;
			}
			if (Integer.parseInt(badgeC.getText()) < badgeCount) {
				assertTrue("Note Deleted successfully");
				badgeCount = Integer.parseInt(badgeC.getText());
			} else
				assertFalse("Note Not deleted");
		}
	}

	public void createNote(RemoteWebDriver driver, String source) throws Exception {
		int avNotes = driver.findElementsByCssSelector("div.notes-data-div-main").size();
		WebElement node = driver.findElementByCssSelector(noteNode.replace("NODE", "1"));
		if (source.contains("TAB"))
			enterText(node, tabTitle, "Note Data");
		else
			enterText(node, qANTitle, "NoteData");
		Thread.sleep(250);
		click(closeBtn, "close");
		Thread.sleep(1000);
		int newNotes = driver.findElementsByCssSelector("div.notes-data-div-main").size();
		if (newNotes > avNotes) {
			assertTrue("New Note Added Successfully");
			createdNotes++;
		}
	}

	public void openNewNote(RemoteWebDriver driver) throws Exception {
		click(newNoteLink, "NEW NOTE");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-new-note").size() > 0)
			assertTrue("New Note Pop-up is displayed");
		else
			assertFalse("New Note Pop-up not displayed");
	}

	public void openNewNoteQA(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(quickActions, "Quick Actions");
		Thread.sleep(250);
		click(newNote, "New Note");
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-new-note").size() > 0)
			assertTrue("New Note Pop-up is displayed");
		else
			assertFalse("New Note Pop-up not displayed");
	}

	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(NotesTab, "Notes");
		Thread.sleep(500);
		if (driver.findElementsByCssSelector("img[src*='notes-icon-active']").size() > 0)
			assertTrue("Notes tab is active");
		else
			assertFalse("Notes Tab Not activated");
	}

	public void confirmDelete(RemoteWebDriver driver, String tabTitle) throws Exception {
		if (driver.findElementsByXPath("//span[text()='" + tabTitle + "']").size() == 0)
			assertTrue("Note Successfully Deleted");
		else
			assertFalse("Note Not deleted from BM");
	}

}
