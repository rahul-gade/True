package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Company_BizGoals extends PageBase {

	public BM_Company_BizGoals(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Business Goals']")
	WebElement tabBusinessGoals;
	String li = "./parent::li";
	@FindBy(css = "button.btn")
	WebElement submit;
	@FindBy(xpath = "//span[text()='SAVE GOAL']")
	WebElement goalSave; // after action plan

	@FindBy(css = "button.new-goal")
	WebElement newGoalBtn;
	@FindBy(css = "div.add-new-goal-link")
	WebElement addGoalBtn;
	String savedGoal = "//a[contains(text(),'TITLE')]";

//	goal details
	@FindBy(css = "span.vgd-title-text")
	WebElement VGDTitle;
	@FindBy(css = "a.bm-vgd-close-icon")
	WebElement cross;

	public void submitAndVerifyGoal(RemoteWebDriver driver, String type, String goalTitle) throws Exception {
		if (type.contains("Idea"))
			click(submit, "Save Goal/Add Plan Button");
		else
			click(goalSave, "Save Goal Button");

		Thread.sleep(1000);
		if (tabBusinessGoals.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Landed back to business goals page");
		else
			assertFalse("Did Not Land back to business goals page");

//		Scroll Down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		if (driver.findElementsByXPath(savedGoal.replace("TITLE", goalTitle)).size() > 0) {
			do {
				Thread.sleep(1000);
			} while (driver.findElementsByCssSelector("div.custom-snackbar").size() > 0);
			assertTrue("Goal Added to list Successfully");
			WebElement newGoal = driver.findElementByXPath(savedGoal.replace("TITLE", goalTitle))
					.findElement(By.xpath("./ancestor::mat-expansion-panel"));

//			goal type options
			int opt = 0;
			if (type.contains("SIA"))
				opt = 1;
			else if (type.contains("100 Day"))
				opt = 2;
			else
				opt = 3;
			click(newGoal.findElement(By.xpath(".//mat-select")), "Goal Type Options");
			Thread.sleep(250);
			if (driver.findElementsByTagName("mat-option").size() == opt)
				assertTrue(opt + " Goal Type options are available");
			else
				assertFalse("Goal Type Options are not proper");
			Actions action = new Actions(driver);
			action.moveByOffset(-100, 0).click().build().perform();
			Thread.sleep(500);

//			goal details page
			click(newGoal.findElement(By.xpath(".//a[text()='View Details']")), "View Details");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-view-goal-details").size() > 0
					&& VGDTitle.getText().trim().equals(goalTitle))
				assertTrue("goal details page opened successfully");
			else
				assertFalse("Goal Details Page not opened");
			click(cross, "Close");
			Thread.sleep(1500);

//			check milestone cards - only for SIA/100 Day
			if (type.contains("SIA") || type.contains("100 Day")) {
				click(savedGoal.replace("TITLE", goalTitle), goalTitle);
				Thread.sleep(250);
				newGoal = driver.findElementByXPath(savedGoal.replace("TITLE", goalTitle))
						.findElement(By.xpath("./ancestor::mat-expansion-panel"));
				if (newGoal.findElements(By.cssSelector("div.milestones-blocks")).size() > 0)
					assertTrue("Milestones Are Displayed");
				else
					assertFalse("Milestones Are NOT Displayed");
			}
		}
	}

	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(tabBusinessGoals, "Business Goals");
		Thread.sleep(250);
		if (tabBusinessGoals.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Business Goals Tab Active");
		else
			assertFalse("Business Goals Tab not activated");
	}

	public void initiateNewGoal(RemoteWebDriver driver) throws Exception {
		Thread.sleep(500);
		if (driver.findElementsByCssSelector("img.goal-undefined-image").size() > 0) {
			assertTrue("No Goals Created, beginning flow");
			click(newGoalBtn, "New Goal");
		} else {
			assertTrue("Some Goal(s) exist, creating new");
			click(addGoalBtn, "Add New Goal");
		}
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-create-new-goal").size() > 0)
			assertTrue("Add new Goal Screen Displayed");
		else
			assertFalse("New goal screen not shown");
	}

}
