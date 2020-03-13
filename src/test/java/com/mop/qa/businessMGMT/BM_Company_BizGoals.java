package com.mop.qa.businessMGMT;

import java.util.ArrayList;
import java.util.List;

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

	@FindBy(css = "img[src*=back]")
	WebElement backBtn;
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
	@FindBy(css = "div.business-goal-archived")
	WebElement archived;
	@FindBy(css = "img[alt=Revert]")
	WebElement revert;
	@FindBy(css = "img[title=back]")
	WebElement back;

//	goal details
	@FindBy(css = "span.vgd-title-text")
	WebElement VGDTitle;
	@FindBy(css = "a.bm-vgd-close-icon")
	WebElement cross;

//	insights
	@FindBy(css = "div.business-insight-container button")
	WebElement beginInsights;
	@FindBy(css = "div.business-insight-container2")
	WebElement BizInsightCard;
	@FindBy(css = "div.blue")
	WebElement blueMarker;
	@FindBy(css = "div.grey")
	WebElement greyMarker;

	@FindBy(css = "span.business-goal-view-txt")
	WebElement goalView;
	@FindBy(css = "span.hdp-end-plan-btn")
	WebElement endPlan;

	String gTitle = "//a[contains(text(),'GTITLE')]",
			plan = "./ancestor::mat-expansion-panel//p[contains(text(),'PLAN')]", 
			HDP_1 = null, HDP_2 = null,
			SIA = null;

	List<String> A_P = new ArrayList<String>();

	public void verifyTransformation(RemoteWebDriver driver, String endedPlan, String unendedPlan) throws Exception {
		String expPanel = "/ancestor::mat-expansion-panel//span[text()='STATE']";
		String using = "";
		Actions action = new Actions(driver);

//		scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		unended Plan
		using = gTitle.replace("GTITLE", unendedPlan) + expPanel.replace("STATE", "100 Day Plan");
		if (driver.findElementsByXPath(using).size() > 0) {
			click(using, "Unended Plan State");
			assertTrue("Unended plan " + unendedPlan + " is in 100 Day Plan state");
			Thread.sleep(250);
			action.moveByOffset(-100, 0).click().perform();
			Thread.sleep(250);
		} else
			assertFalse("Unended plan is not in 100 Day Plan state");

//		ended plan
		using = gTitle.replace("GTITLE", endedPlan) + expPanel.replace("STATE", "SIA");
		if (driver.findElementsByXPath(using).size() > 0) {
			assertTrue("Ended plan " + endedPlan + " is moved to SIA state");
			click(using, "Ended Plan State");
			Thread.sleep(250);
			action.moveByOffset(-100, 0).click().perform();
			Thread.sleep(250);
		} else
			assertFalse("Ended Plan is not moved to SIA state.");
	}

	public void collectGoals(RemoteWebDriver driver, String gTitles, String pTitles) throws Exception {
		String[] titles = gTitles.split(",");
		List<WebElement> hdp = driver.findElementsByXPath(gTitle.replace("GTITLE", titles[0]));
		HDP_1 = hdp.get(0).getText();
		HDP_2 = hdp.get(1).getText();
		SIA = driver.findElementByXPath(gTitle.replace("GTITLE", titles[1])).getText();

		for (WebElement e : hdp) {
			click(e, hdp.get(0).getText());
			Thread.sleep(750);
			A_P.add(e.findElement(By.xpath(plan.replace("PLAN", pTitles))).getText());
		}
	}

	public void checkArchive(RemoteWebDriver driver, String goalTitle) throws Exception {
		Thread.sleep(1000);
		if (driver.findElementsByXPath(savedGoal.replace("TITLE", goalTitle)).size() == 0) {
			assertTrue("Goal Successfully removed from default goal list");
			click(archived, "ARCHIVED");
			Thread.sleep(1000);
			if (driver.findElementsByXPath(savedGoal.replace("TITLE", goalTitle)).size() > 0)
				assertTrue("Goal Successfully Added to archive");
			else
				assertFalse("Goal Not found in archive page");

			click(revert, "Revert");
			Thread.sleep(250);
			if (driver.findElementsByXPath("//*[contains(text(),'unarchived')]").size() > 0) {
				assertTrue("Unarchived successfully");
				do {
					Thread.sleep(1000);
				} while (driver.findElementsByXPath("//*[contains(text(),'unarchived')]").size() > 0);
			} else
				assertTrue("Toast Message not displayed");

			do {
				Thread.sleep(1000);
			} while (driver.findElementsByCssSelector("img[src*='spinner']").size() > 0);
			click(back, "Archive Back");
			Thread.sleep(1500);
			if (driver.findElementsByXPath(savedGoal.replace("TITLE", goalTitle)).size() > 0)
				assertTrue("Goal Successfully added back to active list");
			else
				assertFalse("Goal NOT added back to active list");
		} else
			assertFalse("Goal not removed from list");

	}

	public void openGoalDetails(RemoteWebDriver driver, String goalTitle) throws Exception {
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
			click(savedGoal.replace("TITLE", goalTitle), goalTitle);

//			goal details page
			click(newGoal.findElement(By.xpath(".//a[text()='View Details']")), "View Details");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-view-goal-details").size() > 0
					&& VGDTitle.getText().equals(goalTitle))
				assertTrue("goal details page opened successfully");
			else
				assertFalse("Goal Details Page not opened");
		}

	}

	public void validateMarker(RemoteWebDriver driver, String color) throws Exception {
		Integer value = 0;
		switch (color) {
		case "blue":
			value = Integer.parseInt(blueMarker.getAttribute("style").replaceAll("[^0-9]", ""));
			break;
		case "grey":
			value = Integer.parseInt(greyMarker.getAttribute("style").replaceAll("[^0-9]", ""));
			break;
		}

		if (value > 0)
			assertTrue(color + "Progress Bars Updated");
		else
			assertFalse(color + "Progress Bars not updated");
	}

	public void openBizInsights(RemoteWebDriver driver) throws Exception {
		if (driver.findElementsByCssSelector("div.business-insight-container").size() > 0) {
			assertTrue("Business Insights Not Started Yet");
			click(beginInsights, "Begin");
		} else if (driver.findElementsByCssSelector("div.business-insight-container2").size() > 0) {
			assertTrue("Business Insights Already Started");
			click(BizInsightCard, "Business Insights Card");
		}
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-business-insight").size() > 0)
			assertTrue("Business Insights Sreen opened");
		else
			assertFalse("Business Insights Page Did not Open");
	}

	public void submitAndVerifyGoal(RemoteWebDriver driver, String type, String goalTitle) throws Exception {
		if (type.contains("Idea"))
			click(submit, "Save Goal/Add Plan Button");
		else
			click(goalSave, "Save Goal Button");
		Thread.sleep(500);

//		if coming from business Insights
		if (driver.findElementsByTagName("app-business-insight").size() > 0)
			click(backBtn, "Back Arrow");

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

	public void checkPlanState(RemoteWebDriver driver) throws Exception {
		if (goalView.getText().contains("SIA"))
			assertTrue("VIEW button changed to VIEW SIA");
		else
			assertFalse("VIEW Button not changed to VIEW SIA");
	}

	public void openPlan(RemoteWebDriver driver) throws Exception {
		click(goalView, "View");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-view-hundred-day-plan").size() > 0)
			assertTrue("HUndred day plan page opened");
		else
			assertFalse("HUndred day plan page opened");
	}

//	temporary
	public void openSIA(RemoteWebDriver driver) throws Exception {
		click(goalView, "View");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-view-sia-plan-details").size() > 0)
			assertTrue("SIA Plan page opened");
		else
			assertFalse("SIA Plan page opened");
	}
}
