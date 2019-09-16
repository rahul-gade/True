package com.mop.qa.pageobject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class HypothesesPage extends PageBase {
	public HypothesesPage(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	String proJectName = "";
	String hypothesisTitle = "Hypothesis-";
	String hypothesisDesc = "New Hypotheses Description";

	// =============New Hypothesis=============//
	@FindBy(xpath = "//a[@routerlink='hypothesis']")
	private WebElement hypothesisTab;
	@FindBy(xpath = "(//a[@class='addnew-btn'])[1]")
	private WebElement btnAddNew;
	@FindBy(xpath = "(//div[@class='mat-form-field-flex'])[3]")
	private WebElement drpdownCategory;
	@FindBy(xpath = "//a[contains(@class,'library-btn')]")
	private WebElement hypoLibrary;
	@FindBy(xpath = "//a[@class='back-btn']")
	private WebElement hypoLibraryBack;
	@FindBy(xpath = "(//span[@class='mat-option-text'])[1]")
	private WebElement selectCategory;
	@FindBy(xpath = "//div[text()='Suggested Hypotheses']")
	private WebElement suggestList;
	@FindBy(xpath = "//div[@class='suggested-list']//li[1]")
	private WebElement selectHypothesis;
	@FindBy(xpath = "(//div[@class='filtered-list']//li)[1]")
	private WebElement selectList;
	@FindBy(xpath = "//input[@name='hypothesisTitle']")
	private WebElement hypothesisTitleInput;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnAdd;
	@FindBy(xpath = "//a[@class='link']")
	private WebElement btnBack;
	@FindBy(xpath = "//li[@class='active']/a[@class='link']")
	private WebElement btnBack1;
	String hypoVerify = "//p[contains(text(),'HypothesisTITLE')]";

	// =============Hypothesis Details=============//
	@FindBy(xpath = "//div[@class='description' and contains(text(),'New Hypotheses Description')]")
	private WebElement hypDesc;
	@FindBy(xpath = "//div[contains(text(),'Last modified')]")
	private WebElement lastModDate;
	@FindBy(xpath = "//span[text()='IN PROGRESS']")
	private WebElement statusDDown;
	@FindBy(xpath = "(//span[@class='mat-option-text'])[1]")
	private WebElement statusOpt1;
	@FindBy(xpath = "//span[text()='ONLY TEAM']")
	private WebElement visibDDown;
	@FindBy(xpath = "(//span[@class='mat-option-text'])[3]")
	private WebElement visibOpt3;
	String hypoDetailsTitle = "//div[contains(text(),'HypothesisTITLE')]";

	// =============Post & Comments=============//
	@FindBy(xpath = "//a[text()='add new']")
	private WebElement newPost;
	@FindBy(xpath = "//textarea")
	private WebElement txtDescription;
	@FindBy(xpath = "//div[text()=' Posts ']//span[@class='value']")
	private WebElement countPost;
	@FindBy(xpath = "//span[text()='POST']")
	private WebElement btnPost;
	@FindBy(xpath = "//a[text()=' Attach Document ']")
	private WebElement attachDoc;
	@FindBy(xpath = "//div[@class='document-card']")
	private WebElement postDoc;
	@FindBy(xpath = "//div[@class='post-action']//ul/li[1]")
	private WebElement editPost;
	@FindBy(xpath = "//div[@class='post-action']//ul/li[2]")
	private WebElement deletePost;
	@FindBy(xpath = "//span[text()='Delete']")
	private WebElement delete;
	@FindBy(xpath = "//div[text()=' One Pager ']")
	private WebElement postLink;
	@FindBy(xpath = "//div[@class='one-pager-view-header']")
	private WebElement onePagerNew;

	String postDescription = "Sample Description for post!";
	@FindBy(xpath = "//input[@formcontrolname='commentText']")
	private WebElement txtComment;
	@FindBy(xpath = "//div[text()=' Comments ']//span[@class='value']")
	private WebElement countComment;

	// =============Post & Comments=============//
	@FindBy(xpath = "//ul[contains(@class,'right-action')]/li[1]")
	private WebElement editHypoBtn;
	@FindBy(xpath = "//ul[contains(@class,'right-action')]/li[2]")
	private WebElement deleteHypoBtn;

	@FindBy(xpath = "//span[text()='UPDATE']")
	private WebElement updateHypo;
	
	// =============SUP Flow=============//
	@FindBy(xpath = "//div[@class='note ng-star-inserted']/a")
	private WebElement SUPcta;
	@FindBy(xpath = "//a[contains(@class,'page-arrow')]")
	private WebElement pageArrow;
	@FindBy(xpath = "//p[@class='title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement supCloseBtn;
	
	public void createHypothesis(RemoteWebDriver driver) throws Exception {
		Actions action = new Actions(remoteDriver);
		proJectName = driver.findElement(By.xpath("//span[@class='dd-title']")).getText().trim();
		Thread.sleep(1000);
		click(hypothesisTab, "Hypotheses Tab");
		assertTrue("Clicked on Hypotheses Tab");
		Thread.sleep(1000);
		click(btnAddNew, "Add New Button");
		Thread.sleep(1000);
		assertTrue("Clicked on Add new button");

		if (driver.findElement(By.xpath("//span[text()='NEW HYPOTHESIS']")) != null) {
			assertTrue("landed on New Hypothesis Page.");

			// Testing Hypothesis Library
			if (driver.findElements(By.xpath("//a[contains(@class,'library-btn')]")).size() > 0) {
				assertTrue("Hypothesis with Library Displayed");
				click(hypoLibrary, "Hypothesis Library.");
				Thread.sleep(500);
				if (driver.findElement(By.xpath("//div[text()=' HYPOTHESIS LIBRARY ']")) != null) {
					assertTrue("Landed on Hypothesis With Library.");
					click(hypoLibraryBack, "Back Button");
					Thread.sleep(500);
				}
			} else
				assertTrue("Hypothesis With Library not Displayed.");

			// Testing Hypothesis Category DropDown.
			if (driver.findElement(By.xpath("//app-new-hypothesis//mat-select")) != null) {
				click(driver.findElement(By.xpath("//app-new-hypothesis//mat-select")), "Category Dropdown.");
				Thread.sleep(500);
				click(selectCategory, "Category 1");
				assertTrue("Category Dropdown is available.");
			}

			// Testing Suggested List & Enter Details from List/Manually
			if (driver.findElements(By.xpath("//div[text()='Suggested Hypotheses']")).size() > 0) {
				assertTrue("Suggeseted Hypothesis List is Available.");
				if (driver.findElements(By.xpath("//div[@class='suggested-list']//li")).size() == 3) {
					assertTrue("Suggested list shows 3 items.");
				} else
					assertFalse("suggested List does not show 3 items");
				DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
				Date date = new Date();
				String num = dateFormat.format(date);
				hypothesisTitle = selectHypothesis.getText().trim() + "-" + num;
				click(selectHypothesis, "Suggested Hypothesis 1");
				Thread.sleep(1000);
				click(hypothesisTitleInput, "Hypothesis Title");
				Thread.sleep(1000);
				enterText(hypothesisTitleInput, hypothesisTitle, "Hypothesis Title Text");
				Thread.sleep(1000);
				click(txtDescription, "Description");
				Thread.sleep(1000);
				enterText(txtDescription, hypothesisDesc, "Description Text.");
				Thread.sleep(1000);
			} else {
				DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
				Date date = new Date();
				String num = dateFormat.format(date);
				hypothesisTitle = hypothesisTitle + num;
				assertTrue("Suggested List Not Available; Filling Details Manually.");
				click(hypothesisTitleInput, "Hypothesis Title");
				Thread.sleep(1000);
				enterText(hypothesisTitleInput, hypothesisTitle, "Hypothesis Title Text");
				Thread.sleep(1000);
				enterText(txtDescription, hypothesisDesc, "Description Text.");
				Thread.sleep(1000);
			}

			click(btnAdd, "Submit Hypothesis");
			Thread.sleep(1000);
			click(btnBack, "Back to Hypothesis Landing Page.");
			Thread.sleep(1000);

			if (driver.findElement(By.xpath(hypoVerify.replace("HypothesisTITLE", hypothesisTitle))) != null) {
				action.moveToElement(
						driver.findElement(By.xpath(hypoVerify.replace("HypothesisTITLE", hypothesisTitle)))).perform();
				assertTrue("New Hypothesis is added to Hypothesis List");
			} else
				assertFalse("New Hypothesis is Not Saved.");
		} else
			assertFalse("Hypothesis Page not displayed.");
	}

	public void hypothesisDetails(RemoteWebDriver driver) throws Exception {
		Actions action = new Actions(remoteDriver);
		click(driver.findElement(By.xpath(hypoVerify.replace("HypothesisTITLE", hypothesisTitle))),
				"Newly created Hypothesis");
		Thread.sleep(1000);
		if (driver.findElement(By.xpath(hypoDetailsTitle.replace("HypothesisTITLE", hypothesisTitle))) != null) {
			assertTrue("Newly created Hypothesis details Page opened.");
			if (hypDesc.isDisplayed())
				assertTrue("Hypothesis Description is Displayed.");
			else
				assertFalse("Hypothesis Description is not Displayed.");

			if (lastModDate.isDisplayed())
				assertTrue("Last Modified Date is Displayed.");
			else
				assertFalse("Last Modified Date is not Displayed.");

			click(statusDDown, "Status DropDown");
			Thread.sleep(500);
			click(statusOpt1, "In Progress");
			Thread.sleep(500);
			click(visibDDown, "Visibility DropDown");
			Thread.sleep(500);
			click(visibOpt3, "Only Team");

			if (driver.findElement(By.xpath("//section[@class='post-section']")) != null) {
				action.moveToElement(driver.findElement(By.xpath("//section[@class='post-section']"))).perform();
				assertTrue("Posts Section is Displayed");
			} else
				assertFalse("Posts Section is Not Present");

			if (driver.findElement(By.xpath("//section[@class='comments-section']")) != null) {
				action.moveToElement(driver.findElement(By.xpath("//section[@class='comments-section']"))).perform();
				assertTrue("Comments Section is Displayed");
			} else
				assertFalse("Comments Section is Not Present");
		} else
			assertFalse("Newly created Hypothesis details Page not opened.");
	}

	public void newPost(RemoteWebDriver driver) throws Exception {
		click(newPost, "Add New Post");
		Thread.sleep(100);
		if (driver.findElement(By.xpath("//span[text()='NEW POST']")) != null) {
			int posts = Integer.parseInt(getText(countPost));
			assertTrue("Landed on New Post Page");
			click(txtDescription, "Post Description.");
			enterText(txtDescription, postDescription, "Post Description");
			click(attachDoc, "Attach Document Button");
			Thread.sleep(500);
			if (driver.findElements(By.xpath("//div[text()=' ATTACH A DOCUMENT TO THE POST ']")).size() > 0) {
				assertTrue("Landed on Attach New Document Page.");
				if (postDoc.isDisplayed())
					click(postDoc, "Document OnePager for Post");
				else
					click(hypoLibraryBack, "Back Button");
			}
			click(btnPost, "Post Button");
			Thread.sleep(1000);
			if (Integer.parseInt(getText(countPost)) > posts) {
				assertTrue("Successfully created a Post");
			}
			Thread.sleep(500);
		} else
			assertFalse("New Post Page did not open!");

		// ======Check postLink=======
		click(postLink, "One Pager Post Link");
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		if (tabs.size() > 1) {
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			if (driver.findElements(By.xpath("//div[@class='one-pager-view-header']")).size() > 0) {
				assertTrue("One Pager Opened.");
				driver.close();
				driver.switchTo().window(tabs.get(0));
			}
		} else
			assertFalse("Post Link did not open.");
	}

	public void newComment(RemoteWebDriver driver) throws Exception {
		int comments = Integer.parseInt(getText(countComment));
		click(txtComment, "Comment");
		Thread.sleep(1000);
		String longText = "Cupcake ipsum dolor. Sit amet marshmallow topping cheesecake muffin. Halvah croissant candy canes bonbon candy. Apple pie jelly beans topping carrot cake danish tart cake cheesecake. Muffin danish chocolate soufflé pastry icing bonbon oat cake. And A few more words.";
		enterText(txtComment, longText, "Comment");

		String sentText = driver.findElement(By.xpath("//input[@placeholder='New Comment']")).getAttribute("value");
		if (sentText.length() > 250)
			assertFalse("Comment Input accepts more than 250 characters");
		else {
			assertTrue("Comment Input accepts exactly 250 characters");
			txtComment.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			if (Integer.parseInt(getText(countComment)) > comments) {
				assertTrue("Successfully posted a Comment");
			}
			Thread.sleep(500);
		}
	}

	public void editHypothesis(RemoteWebDriver driver) throws Exception {
		hypothesisTitle += " - EDITED";
		hypothesisDesc = hypothesisDesc.replace("New", "Edited");
		Actions action = new Actions(remoteDriver);
		click(editHypoBtn, "Edit Hypothesis Button");
		Thread.sleep(1000);
		if (driver.findElement(By.xpath("//span[text()='EDIT HYPOTHESIS']")) != null) {
			assertTrue("Landed on Edit Hypothesis Page.");
			enterText(hypothesisTitleInput, hypothesisTitle, "Edited Hypothesis Title");
			Thread.sleep(500);
			enterText(txtDescription, hypothesisDesc, "Description Text.");
			Thread.sleep(5000);
			click(updateHypo, "Update Button");
			Thread.sleep(1000);

			if (driver.findElement(By.xpath(hypoDetailsTitle.replace("HypothesisTITLE", hypothesisTitle))) != null) {
				assertTrue("Hypothesis Successfully Updated.");
			} else
				assertFalse("Hypothesis Not Updated.");

			if (driver.findElements(By.xpath("//span[contains(text(),'Title has changed from')]")).size() > 0) {
				action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Title has changed from')]")))
						.perform();
				assertTrue("Change is recorded as a comment.");
			}
		}
	}

	public void editPost(RemoteWebDriver driver) throws Exception {
		postDescription += " - EDITED";
		Actions action = new Actions(remoteDriver);
		action.moveToElement(editPost).perform();
		click(editPost, "Edit Post");
		Thread.sleep(500);
		click(txtDescription, "Post Description.");
		enterText(txtDescription, postDescription, "Post Description");
		click(btnPost, "Post Button");
		Thread.sleep(1000);
		if (driver.findElements(By.xpath("//div[@class='post-description' and contains(text(),' - EDITED')]"))
				.size() > 0)
			assertTrue("Post Successfully Updated.");
	}

	public void deletePost(RemoteWebDriver driver) throws Exception {
		int posts = Integer.parseInt(getText(countPost));
		click(deletePost, "Delete Post Button");
		Thread.sleep(500);
		click(delete, "Delete");
		Thread.sleep(1000);
		if (Integer.parseInt(getText(countPost)) == posts - 1) {
			assertTrue("Post Successfully Deleted.");
		}
		Thread.sleep(1000);
		click(btnBack, "Back");
		Thread.sleep(100);
	}

	public void SUPverify(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		click(SUPcta, "SUP CTA");
		Thread.sleep(1000);
		if(driver.findElements(By.xpath("//p[@class='title']")).size()>0) {
			assertTrue("Landed on SUP Landing page");
			String title= driver.findElement(By.xpath("//p[@class='title']")).getText().trim();
			if(title.equals(proJectName)) {
				assertTrue("Project Name is displayed as: "+title);
			}
			if(driver.findElements(By.xpath("//div[contains(@class,'sup-step-1')]/section[@class='one-pager-panel']")).size()>0) {
				assertTrue("Sup is at one Pager section");
				click(pageArrow, "Next page Arrow");
				Thread.sleep(500);
				if(driver.findElements(By.xpath("//section[contains(@class,'hypotheses-section')]")).size()>0)
					assertTrue("Hypothesis SUP Page Displayed.");
				click(pageArrow, "Next page Arrow");
				Thread.sleep(500);
				if(driver.findElements(By.xpath("//div[contains(@class,'sup-step-1')]/section[@class='one-pager-panel']")).size()>0) 
					assertTrue("landed back to SUP One Pager");
			} else
				assertFalse("Unexpected SUP Landing Page.");
		} else
			assertFalse("SUP Landing page Not Displayed");
		click(supCloseBtn, "SUP Close Button");
		Thread.sleep(500);
	}

//	Below stuff is porbably not relevant anymore.... advised to keep till the end of design. 
//	{
//				click(btnStatus, "STATUS");
//				Thread.sleep(100);
//				click(ststusValidated, "STATUS");
//				Thread.sleep(1000);
//				click(btnAddNew, "Add New Button");
//				Thread.sleep(1000);
//				click(txtDescription, "New Post");
//				Thread.sleep(1000);
//				enterText(txtDescription, "Post Description", "Post");
//				Thread.sleep(2000);
//				click(btnPost, "Post Button");
//				Thread.sleep(2000);
//				if (Integer.parseInt(getText(countPost)) > 0) {
//					assertTrue("Successfully created a Post");
//				}

//
//				// go back to deal details page, then to all deals.
//				click(btnBack, "Back");
//				Thread.sleep(1500);
//				click(btnBack1, "Back");
//				Thread.sleep(100);
//	}

}
