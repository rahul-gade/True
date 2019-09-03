package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class HypothesesPage extends PageBase {
	public HypothesesPage(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//a[@routerlink='hypothesis']")
	private WebElement tabHypotheses;
	@FindBy(xpath = "(//a[@class='addnew-btn'])[1]")
	private WebElement btnAddNew;
	@FindBy(xpath = "(//div[@class='mat-form-field-flex'])[3]")
	private WebElement drpdownCategory;
//	@FindBy(xpath = "//span[text()=' LEADERSHIP ' ]")
	@FindBy(xpath = "(//span[@class='mat-option-text'])[1]")
	private WebElement selectCategory;
	@FindBy(xpath = "//div[@class='suggested-list']//li[1]")
	private WebElement selectHypothesis;
	@FindBy(xpath = "//a[@class='library-btn ng-star-inserted']")
	private WebElement btnLibrary;
	@FindBy(xpath = "(//div[@class='filtered-list']//li)[1]")
	private WebElement selectList;
	@FindBy(xpath = "//input[@name='hypothesisTitle']")
	private WebElement inputHypothesis;
	@FindBy(xpath = "//textarea")
	private WebElement txtDescription;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnAdd;
	@FindBy(xpath = "//span[text()='IN PROGRESS']")
	private WebElement btnStatus;
	@FindBy(xpath = "//span[text()=' VALIDATED ']")
	private WebElement ststusValidated;
	@FindBy(xpath = "//span[text()='POST']")
	private WebElement btnPost;
	@FindBy(xpath = "//div[text()=' Posts ']//span[@class='value']")
	private WebElement countPost;
	@FindBy(xpath = "//input[@formcontrolname='commentText']")
	private WebElement txtComment;
	@FindBy(xpath = "//div[text()=' Comments ']//span[@class='value']")
	private WebElement countComment;
	@FindBy(xpath = "//a[@class='link']")
	private WebElement btnBack;
	@FindBy(xpath = "//li[@class='active']/a[@class='link']")
	private WebElement btnBack1;

	String hypothesisTitle = "New Hypothesis Title";
	String hypothesisDesc = "New Hypotheses Description";

	//THE CREATE HYPOTHESIS PAGE IS TERRIBLY DAMAGED, CORRECTING THIS IS PRIORITY ONE!
	public void createHypothesis(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		click(tabHypotheses, "Hypotheses Tab");
		assertTrue("Clicked on Hypotheses Tab");
		Thread.sleep(1000);
		click(btnAddNew, "Add New Button");
		Thread.sleep(1000);
		assertTrue("Clicked on Add new button");

		// check library option.
		if (driver.findElements(By.xpath("//a[@class='library-btn ng-star-inserted']")).size() > 0)
			assertTrue("Hypothesis with library option is available.");
		else
			assertFalse("Hypothesis with library option is not available.");

		// check category dropdown for new hypothesis.
		if (driver.findElements(By.xpath("(//div[@class='mat-form-field-flex'])[3]")).size() > 0) {
			click(drpdownCategory, "Category Dropdown");
			Thread.sleep(1000);
			assertTrue("Dropdown for categories is displayed.");
			click(selectCategory, "Select Dropdown");
			Thread.sleep(200);
		} else
			assertFalse("Dropdown for categories is not displayed.");

		// enter hypothesis title - check suggested list/through library.
		if (driver.findElements(By.xpath("//div[@class='suggested-list']")).size() > 0) {
			if (driver.findElements(By.xpath("//div[@class='suggested-list']//li")).size() == 3)
				assertTrue("Suggested List displays 3 items.");
			else
				assertFalse("Suggested List does not display 3 items.");
			click(selectHypothesis, "Select Suggested Hypothesis");
			Thread.sleep(200);
			this.hypothesisTitle = driver
					.findElement(By.xpath("//input[@name='hypothesisTitle']/following-sibling::span/label")).getText()
					.trim();
			System.out.println("========:::::::<<  "+hypothesisTitle+"  >>:::::::========");
		} else if (driver.findElements(By.xpath("//a[@class='library-btn ng-star-inserted']")).size() > 0) {
			click(btnLibrary, "Library List");
			Thread.sleep(1000);
			click(selectList, "Select List");
			Thread.sleep(200);
			this.hypothesisTitle = driver
					.findElement(By.xpath("//input[@name='hypothesisTitle']/following-sibling::span/label")).getText()
					.trim();
			System.out.println(hypothesisTitle);
			Thread.sleep(1000);
		} else {
			click(inputHypothesis, "Hypothesis");
			enterText(inputHypothesis, hypothesisTitle, "Hypothesis");
			Thread.sleep(1000);
			System.out.println(hypothesisTitle);
		}

		// enter hypo details
		click(txtDescription, "Description");
		Thread.sleep(2000);
		enterText(txtDescription, hypothesisDesc, "Description");
		Thread.sleep(1000);
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		assertTrue("Successfully created a Hypotheses");
		Thread.sleep(1000);
		
//		go back to hypothesis landing page.
		click(btnBack, "Back to Hypothesis Landing Page");
		Thread.sleep(1000);
		click(btnBack1, "Back to Investment Home");
		Thread.sleep(100);
	}
	
	//SOME WORK AS BEEN DEONE ON THE HYPOTHESIS, VERIFY WHAT'S DONE. 
	// hypothesis page. ststus-post-comment.
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
//				click(txtComment, "Comment");
//				Thread.sleep(1000);
//				enterText(txtComment, "Enter your Comments", "Comment");
//				txtComment.sendKeys(Keys.ENTER);
//				Thread.sleep(1000);
//				if (Integer.parseInt(getText(countComment)) > 0) {
//					assertTrue("Successfully posted a Comment");
//				}
//
//				// go back to deal details page, then to all deals.
//				click(btnBack, "Back");
//				Thread.sleep(1500);
//				click(btnBack1, "Back");
//				Thread.sleep(100);
//	}
	
}
