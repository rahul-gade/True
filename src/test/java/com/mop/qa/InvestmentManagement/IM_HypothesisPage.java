package com.mop.qa.InvestmentManagement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.pageobject.FileUpload;
import com.mop.qa.testbase.PageBase;

public class IM_HypothesisPage extends PageBase {

	public IM_HypothesisPage(RemoteWebDriver driver) {
		super(driver);
	}

//	comments
	@FindBy(css = "section.comments-section")
	WebElement commentSection;
	@FindBy(css = "input[formcontrolname=commentText]")
	WebElement inputComment;
	@FindBy(css = "div.comments-header span.value")
	WebElement commentCount;
	@FindBy(css = "div.comment-card") // forward search 'a' for cross button.
	List<WebElement> commentCards;
	@FindBy(css = "div.extra-menu") // probably not needed.
	WebElement commentOverflow;
	@FindBy(xpath = "//button[text()=' Delete ']")
	WebElement deleteComment;
	@FindBy(xpath = "//button[text()=' Reply ']")
	WebElement reply2Comment;
	@FindBy(css = "div.reply-comment") // forward search 'a' for cross button.
	WebElement replyIndicator;
//	posts
	@FindBy(css = "section.post-section")
	WebElement postSection;
	@FindBy(css = "section.post-section a")
	WebElement addNewPost;
	@FindBy(css = "div.post-header span.value")
	WebElement postCount;
	@FindBy(css = "textarea[formcontrolname=postContent]")
	WebElement pTitle;
	@FindBy(css = "a.attachment-btn")
	WebElement attachment;
	@FindBy(xpath = "//a[text()='DMS']")
	WebElement dms;
	@FindBy(css = "label.drag-section")
	WebElement fileDrag;
	@FindBy(css = "div.file-selected")
	WebElement uploadedFile;
	@FindBy(css = "button.btn")
	WebElement upload;
	@FindBy(css = "div.post-action li:nth-of-type(1)")
	WebElement editPost;
	@FindBy(css = "div.post-action li:nth-of-type(2)")
	WebElement deletePost;
	@FindBy(css = "div.post-description")
	WebElement postDesc;

	@FindBy(css = "section.hypotheses-section")
	WebElement hypothesisSection;
	@FindBy(css = "app-hypothesis-details")
	WebElement hypothesisDetails;
	@FindBy(css = "div.description-section div.title")
	WebElement hypothesisTitle;
	@FindBy(css = "span.type")
	WebElement hypothesisType;
	@FindBy(css = "ul.right-action li:nth-of-type(1)")
	WebElement editHypothesis;
	@FindBy(css = "ul.right-action li:nth-of-type(2)")
	WebElement deleteHypothesis;
	@FindBy(css = "ul.left-action li:nth-of-type(1) mat-form-field")
	WebElement status;
	@FindBy(css = "ul.left-action li:nth-of-type(2) mat-form-field")
	WebElement visibility;
	@FindBy(css = "input[name=hypothesisTitle]")
	WebElement inputTitle;
	@FindBy(css = "textarea[name=hypothesisDesc]")
	WebElement inputDesc;
	@FindBy(css = "a.link")
	WebElement backButton;

	public void deleteHypothesis(RemoteWebDriver driver, String title) throws Exception {
		click(deleteHypothesis, "Delete Hypothesis");
		Thread.sleep(500);
		if (driver.findElements(By.xpath("//*[contains(text(),'hypothesis deleted successfully')]")).size() > 0)
			assertTrue("Toast message deleted successfully");
		else
			assertFalse("Toast message not found");

		Thread.sleep(2000);
		if (hypothesisSection.isDisplayed())
			assertTrue("Landed back on hypothesis tab");
		else
			assertFalse("did not Land back on hypothesis tab");

		if (driver.findElements(By.xpath("//p[contains(text(),'" + title + "')]")).size() == 0)
			assertTrue("Hypothesis deleted succesfuly");
		else
			assertFalse("Hypothesis not deleted");
	}

	public void editHypothesis(RemoteWebDriver driver, String title) throws Exception {
		String option = "//span[contains(text(),'OPTION')]";
		Thread.sleep(500);
//		negated
		click(status, "Status");
		Thread.sleep(250);
		click(option.replace("OPTION", "VALIDATED"), "Validated");
		Thread.sleep(250);
		click(backButton, "Back");
		Thread.sleep(3000);
		if (driver.findElements(By.cssSelector("a.success")).size() > 0)
			assertTrue("Status edited succesfully");
		else
			assertFalse("Status not changed");
		click("//p[contains(text(),'" + title + "')]", "selected hypothesis");
		Thread.sleep(1500);
//		validated
		click(status, "Status");
		Thread.sleep(250);
		click(option.replace("OPTION", "NEGATED"), "Negated");
		Thread.sleep(250);
		click(backButton, "Back");
		Thread.sleep(3000);
		if (driver.findElements(By.cssSelector("a.error")).size() > 0)
			assertTrue("Status edited succesfully");
		else
			assertFalse("Status not changed");
		click("//p[contains(text(),'" + title + "')]", "selected hypothesis");

//		visibility
		click(visibility, "Visibility");
		Thread.sleep(500);
		click(option.replace("OPTION", "DPM VISIBLE"), "DPM VISIBLE");
		Thread.sleep(250);

//		edit
		click(editHypothesis, "Edit Hypothesis");
		Thread.sleep(1000);
		if (driver.findElements(By.xpath(option.replace("OPTION", "EDIT"))).size() > 0)
			assertTrue("Edit Hypothesis POp-up opened");
		else
			assertFalse("Edit Hypothesis pop-up did not open");

		title += " -EDITED";
		click(inputTitle, "Title");
		enterText(inputTitle, title, "Hypothesis Title");
		Thread.sleep(250);
		click(inputDesc, "Description");
		enterText(inputDesc, "Edited Hypothesis Description", "Description");
		Thread.sleep(250);
		click(upload, "Update");
		Thread.sleep(1000);

		if (driver.findElements(By.xpath("//*[contains(text(),'" + title + "')]")).size() > 0)
			assertTrue("Hypothesis Updated successfully");
		else
			assertFalse("Hypothesis not edited successfully");

	}

	public void deletePost(RemoteWebDriver driver) throws Exception {
		int pCount = Integer.parseInt(postCount.getText().trim());
		Thread.sleep(500);
		click(deletePost, "Delete Icon On post card");
		Thread.sleep(250);

		if (driver.findElements(By.cssSelector("app-confirm-dialog")).size() > 0)
			assertTrue("Delete post dialog appeared");
		else
			assertFalse("Delete post dialog not displayed");

		click(upload, "DELETE");
		Thread.sleep(1500);

		if (Integer.parseInt(postCount.getText().trim()) < pCount)
			assertTrue("post deleted successfully");
		else
			assertFalse("Post not deleted");
	}

	public void editPost(RemoteWebDriver driver, String postTitle) throws Exception {
		Thread.sleep(500);
		click(editPost, "Edit Pencil");
		Thread.sleep(250);

		if (driver.findElements(By.xpath("//span[text()='EDIT POST']")).size() > 0)
			assertTrue("Edit Post POp-up displayed");
		else
			assertFalse("Edit Post POp-up is not displayed");

		click(pTitle, "POst Title");
		enterText(pTitle, postTitle + " -EDITED", "POst Title");
		Thread.sleep(250);

		click(upload, "POST");
		Thread.sleep(1000);

		if (postDesc.getText().contains("EDITED"))
			assertTrue("POst Title Updated successfully");
		else
			assertFalse("POst Title Not udated");
	}

	public void addPost(RemoteWebDriver driver, String postTitle, String filePath, String wrongPath) throws Exception {
		Thread.sleep(1000);
		int pCount = Integer.parseInt(postCount.getText().trim());
		click(addNewPost, "ADD NEW");
		Thread.sleep(250);
		if (driver.findElements(By.xpath("//span[text()='NEW POST']")).size() > 0)
			assertTrue("NEW POST pop-up displayed");
		else
			assertFalse("NEW POST Pop-up not displayed");

		click(pTitle, "POst Title");
		enterText(pTitle, postTitle, "POst Title");
		Thread.sleep(250);

		click(attachment, "Attach Document");
		Thread.sleep(250);
		if (driver.findElements(By.xpath("//div[text()=' ATTACH A DOCUMENT TO THE POST ']")).size() > 0)
			assertTrue("Attach Document Pop-up displayed");
		else
			assertFalse("Attach Document Pop-up NOT displayed");
		click(dms, "DMS");
		Thread.sleep(100);
		if (upload.isEnabled())
			assertFalse("Upload button already enabled");
		click(fileDrag, "File DropBox");
		Thread.sleep(1000);
		FileUpload file = new FileUpload(remoteDriver);
		file.fileUpload(wrongPath);
		Thread.sleep(2000);

		if (driver.findElements(By.cssSelector("p.error-message")).size() > 0)
			assertTrue("error message properly shown");
		else
			assertFalse("error message not properly shown");

		click(fileDrag, "File DropBox");
		Thread.sleep(1000);
		FileUpload newfile = new FileUpload(remoteDriver);
		newfile.fileUpload(filePath);
		Thread.sleep(2000);

		if (uploadedFile.isDisplayed())
			assertTrue("Uploaded file is shown");
		else
			assertFalse("Uploaded file is NOT shown");

		if (upload.isEnabled())
			assertTrue("Upload Button enabled after successful file upload");
		else
			assertFalse("Upload button still disabled");

		click(upload, "UPLOAD");
		Thread.sleep(2500);
		if (driver.findElements(By.xpath("//span[text()='NEW POST']")).size() > 0)
			assertTrue("Landed back to new post pop-up");
		else
			assertFalse("Did not land back to new post pop-up");
		if (driver.findElements(By.cssSelector("div.new-document-card")).size() > 0)
			assertTrue("Uploaded file displayed in card");
		else
			assertFalse("Uploaded file card not shown");

		click(upload, "POST");
		Thread.sleep(1000);

		if (Integer.parseInt(postCount.getText().trim()) > pCount)
			assertTrue("New Post Added successfully");
		else
			assertFalse("New Post not added successfully");
	}

	public void deleteComments(RemoteWebDriver driver) throws Exception {
		int commentsCount = Integer.parseInt(commentCount.getText().trim());
		while (driver.findElements(By.cssSelector("div.extra-menu")).size() > 0) {
			click(commentOverflow, "Overflow menu");
			Thread.sleep(250);
			click(deleteComment, "Delete");
			Thread.sleep(250);
			int newCount = Integer.parseInt(commentCount.getText().trim());
			if (newCount < commentsCount) {
				assertTrue("Comment Deleted successfully");
				commentsCount = newCount;
			} else
				assertFalse("Comment not deleted");
		}
	}

	public void addComment(RemoteWebDriver driver, String comment, String commentReply) throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//p[text()='No Comments Yet']"))).build().perform();
		click(inputComment, "Input comment");
		enterText(inputComment, comment, "Comment");
		Thread.sleep(250);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(250);
		int c = 0;
		if (driver.findElements(By.cssSelector("div.comment-card")).size() > 0) {
			c = driver.findElements(By.cssSelector("div.comment-card")).size();
			assertTrue("Comment successfully added");
		} else
			assertFalse("Comment not added");
		click(commentOverflow, "Comment Menu");
		Thread.sleep(250);
		click(reply2Comment, "Reply");
		if (replyIndicator.isDisplayed()) {
			assertTrue("Reply to selected comment dispayed");
			click(inputComment, "Input comment");
			enterText(inputComment, commentReply, "Comment");
			Thread.sleep(250);
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(250);
			if (driver.findElements(By.cssSelector("div.comment-card")).size() > c)
				assertTrue("Reply published");
			else
				assertFalse("Reply not published");
		}
	}

	public void validateHypothesis(RemoteWebDriver driver, String category, String title) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//img[contains(@src,'spinner')]")).size() > 0);
		if (hypothesisDetails.isDisplayed()) {
			Actions action = new Actions(driver);
			assertTrue("Landed on hypothesis Details Page");
			assertTrue("Hypothesis title saved correctly", hypothesisTitle.getText().contains(title));
			assertTrue("Hypothesis category saved correctly", hypothesisType.getText().contains(category));
			assertTrue("posts section is displayed", postSection.isDisplayed());
			action.moveToElement(driver.findElement(By.xpath("//p[text()='No Comments Yet']"))).build().perform();
			assertTrue("comments section is displayed", commentSection.isDisplayed());

			click(backButton, "Back");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//img[contains(@src,'spinner')]")).size() > 0);
			if (hypothesisSection.isDisplayed())
				assertTrue("Landed back to hypothesis Tab on deal details page");
			else
				assertFalse("did not Land back to hypothesis Tab on deal details page");

			if (driver.findElementsByXPath("//p[contains(text(),'" + title + "')]").size() > 0) {
				action.moveToElement(driver.findElementByXPath("//p[contains(text(),'" + title + "')]")).build()
						.perform();
				assertTrue("Hypothesis added to the list");
				click("//p[contains(text(),'" + title + "')]", "selected hypothesis");
				Thread.sleep(2000);
				if (hypothesisDetails.isDisplayed()) {
					assertTrue("Landed on hypothesis Details Page");
				} else
					assertFalse("Hypothesis Page did not open");
			} else
				assertFalse("Hypothesis NOT added to the list");
			// probably a few more validations needded.
			// TODO
		} else
			assertFalse("Hypothesis Page did not open");
	}

}
