package com.mop.qa.businessMGMT;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_GoalDetails extends PageBase {

	public BM_GoalDetails(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Business Goals']")
	WebElement tabBusinessGoals;
	String li = "./parent::li";
	String savedGoal = "//a[contains(text(),'TITLE')]";

	@FindBy(css = "span.vgd-goal-type span.mat-select-value-text span")
	WebElement goalType;
	@FindBy(css = "span.vgd-title-text")
	WebElement title;
	@FindBy(css = "mat-select[name=goal-criticality] span.mat-select-value-text span") // for text too
	WebElement criticality;
	@FindBy(css = "mat-select[name=goal-assesment]")
	WebElement assessment;
	@FindBy(css = "button.vgd-archive-btn")
	WebElement archive;
	@FindBy(css = "a.bm-vgd-close-icon")
	WebElement close;

	@FindBy(css = "div.mat-slider-thumb")
	WebElement sliderThumb;
	@FindBy(css = "span.value")
	WebElement sliderLabel;
	@FindBy(css = "div.mat-slider-track-background")
	WebElement sliderBG;

	public void testDetails(RemoteWebDriver driver, String goalTitle, String type, String critic, String critCode)
			throws Exception {
		Actions action = new Actions(driver);
//✓		goal type
		if (goalType.getText().equalsIgnoreCase(type))
			assertTrue("Goal Type " + type + " is correct.");
		else
			assertFalse("Goal Type is not correct. Expected: " + type + " || Actual: " + goalType.getText());

//✓		goal title
		if (title.getText().contains(goalTitle))
			assertTrue("Goal Title " + goalTitle + " is correct.");
		else
			assertFalse("Goal Title is not correct. Expected: " + goalTitle + " || Actual: " + title.getText());

//		progress bar
		if (driver.findElementsByCssSelector("button.vgd-archive-btn").size() == 0)
			assertTrue("Archive button is not present when progress is less than 100%");
		else
			assertFalse("Archive button is present despite progress state");
		int width = sliderBG.getSize().getWidth();
		action.dragAndDropBy(sliderThumb, width, 0).perform();
		Thread.sleep(250);
		if (driver.findElementsByXPath("//*[contains(text(),'successfully')]").size() > 0) {
			assertTrue("Progress saved successfully");
			do {
				Thread.sleep(1000);
			} while (driver.findElementsByXPath("//*[contains(text(),'successfully')]").size() > 0);// toast message
		} else
			assertFalse("Toast Messages Not displayed");

//✓		archive button
		if (archive.isDisplayed())
			assertTrue("Archive Button Displayed when progress is set to 100%");
		else
			assertFalse("Archive Button is not displayed");

//✓		Criticality
		if (criticality.getText().equalsIgnoreCase(critic))
			assertTrue("Criticality is correctly saved");
		else
			assertFalse("criticality is not correctly saved\n" + critic + "   " + criticality.getText());

//✓ 		tabs
		List<WebElement> tabs = driver.findElementsByCssSelector("app-view-goal-details div.mat-tab-label");
		for (WebElement tab : tabs) {
			String name = tab.findElement(By.className("mat-tab-label-content")).getText();
			click(tab, name);
			Thread.sleep(500);
		}

//		Assessment - assess to vGood.
		click(assessment, "Goal Assessment DropDown");
		Thread.sleep(250);
		click("//mat-option//span[contains(text(),'Very Good')]", "Very Good");
		Thread.sleep(250);

		click(close, "Close");
		Thread.sleep(1500);

//		Scroll Down on Biz goals Page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		WebElement newGoal = driver.findElementByXPath(savedGoal.replace("TITLE", goalTitle))
				.findElement(By.xpath("./ancestor::mat-expansion-panel"));

		click(newGoal, "Goal: " + goalTitle);
		Thread.sleep(500);
		if (newGoal.findElements(By.cssSelector("img.rework-shap")).size() > 0)
			assertTrue("Assessment Added Successfully");
		else
			assertFalse("Assessment not added");
	}

	public void archiveGoal(RemoteWebDriver driver) throws Exception {
		Thread.sleep(500);
		click(archive, "ARCHIVE");
		do {
			Thread.sleep(1000);
		} while (driver.findElementsByCssSelector("img[src*='spinner']").size() > 0);
		if (tabBusinessGoals.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Landed back to business goals page");
		else
			assertFalse("Did Not Land back to business goals page");
	}
}
