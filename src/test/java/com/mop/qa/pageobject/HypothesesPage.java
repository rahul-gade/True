package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class HypothesesPage extends PageBase{
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
	private WebElement selectStatus;
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
	
	public void createHypothesis(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		click(tabHypotheses, "Hypotheses Tab");
		assertTrue("Clicked on Hypotheses Tab");
		Thread.sleep(1000);
		click(btnAddNew, "Add New Button");
		Thread.sleep(1000);
		assertTrue("Clicked on Add new button");
		if(driver.findElements(By.xpath("//div[@class='suggested-list']//li[1]")).size()>0) {
			click(selectHypothesis, "Select Suggested Hypothesis");
		}else {
		click(drpdownCategory, "Category Dropdown");
		Thread.sleep(200);
		click(selectCategory, "Select Dropdown");
		Thread.sleep(200);
		if(driver.findElements(By.xpath("//a[@class='library-btn ng-star-inserted']")).size()>0) {
		click(btnLibrary, "Library List");
		Thread.sleep(1000);
		click(selectList, "Select List");
		Thread.sleep(1000);
		}else {
			click(inputHypothesis, "Hypothesis");
			enterText(inputHypothesis, "New Hypothesis text", "Hypothesis");
		}
		}
		//div[@class="suggested-list"]//li[1]
		click(txtDescription, "Description");
		Thread.sleep(2000);
		enterText(txtDescription, "Hypotheses Description", "Description");
		Thread.sleep(1000);
		click(btnAdd, "ADD");
		Thread.sleep(2000);
		assertTrue("Successfully created a Hypotheses");
		Thread.sleep(2000);
		click(btnStatus, "STATUS");
		Thread.sleep(100);
		click(selectStatus, "STATUS");
		Thread.sleep(1000);
		click(btnAddNew, "Add New Button");
		Thread.sleep(1000);
		click(txtDescription, "New Post");
		Thread.sleep(1000);
		enterText(txtDescription, "Post Description", "Post");
		Thread.sleep(2000);
		click(btnPost, "Post Button");
		Thread.sleep(2000);
		if(Integer.parseInt(getText(countPost))>0) {
			assertTrue("Successfully created a Post");
		}
		click(txtComment, "Comment");
		Thread.sleep(1000);
		enterText(txtComment, "Enter your Comments", "Comment");
		txtComment.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		if(Integer.parseInt(getText(countComment))>0) {
			assertTrue("Successfully posted a Comment");
		}
		click(btnBack, "Back");
		Thread.sleep(1500);
		click(btnBack1, "Back");
		Thread.sleep(100);
	}
}
