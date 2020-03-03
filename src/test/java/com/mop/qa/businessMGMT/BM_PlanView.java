package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
//app-view-hundred-day-plan <== Page App Component!!!
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

import jdk.internal.vm.annotation.ForceInline;

public class BM_PlanView extends PageBase {

	public BM_PlanView(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "span.business-goal-view-txt")
	WebElement goalView;
	@FindBy(css = "span.hdp-end-plan-btn")
	WebElement endPlan;
	@FindBy(css = "img[title=Previous]")
	WebElement prevBtn;
	@FindBy(css = "img[title=Next]")
	WebElement nextBtn;
	String vert = "//mat-panel-title[contains(text(),'VERTICAL')]/ancestor::mat-expansion-panel";

//	sidebar
	@FindBy(css = "span.vgd-title-text")
	WebElement goalTitle;
	@FindBy(css = "span.vapd-title-text")
	WebElement aPlanTitle;
	@FindBy(css = "div.mat-slider-track-background")
	WebElement sliderBG;
	@FindBy(css = "button.vgd-ss-archive-btn")
	WebElement archive;
	@FindBy(css = "img[alt=Close]")
	WebElement closeSidebar;

//	end plan
	@FindBy(css = "span.bm-ehdp-checkbox")
	WebElement cBoxWhole;
	@FindBy(xpath = "//mat-checkbox")
	WebElement cBoxState;
	@FindBy(css = "button.bm-ehdp-confirmbtn")
	WebElement confirmBtn;

	public void verifySideBar(RemoteWebDriver driver, String planName, String vertical) throws Exception {
//		Goal Section
		WebElement hdpPanel = driver.findElementByXPath(vert.replace("VERTICAL", vertical));
		click(hdpPanel.findElement(By.className("bm-sia-goal-accordion-header-left")), "Plan " + planName);
		Thread.sleep(500);
		assertTrue("Split Screen is displayed", driver.findElementsByClassName("aside-content").size() > 0);
		assertTrue("Goal Title is correctly displayed", goalTitle.getText().trim().equals(planName));
		fillSlider(driver);
		if (driver.findElementsByXPath("//*[contains(text(),'successfully')]").size() > 0) {
			assertTrue("Progress saved successfully");
		} else
			assertFalse("Toast Messages Not displayed");
		assertTrue("Archive Button Displayed when progress is set to 100%", archive.isDisplayed());
//		close slider
		click(closeSidebar, "Close Sidebar");
		Thread.sleep(250);
		assertTrue("Split Screen is closed", driver.findElementsByClassName("aside-content").size() == 0);
		int perc = Integer
				.parseInt(driver.findElementByClassName("accor-header-percentage").getText().replaceAll("[^0-9]", ""));
		assertTrue("Percentage saved successfully", perc == 100);

//		Action plan subsection - Refresh Panel
		hdpPanel = driver.findElementByXPath(vert.replace("VERTICAL", vertical));
		WebElement aPlan = hdpPanel.findElement(By.cssSelector("span.bm-sia-goal-accor-subsection-title"));
		String aPTitle = aPlan.getText();
		click(aPlan, "100 Day Goal Action Plan");
		Thread.sleep(250);
		assertTrue("Split Screen is displayed", driver.findElementsByClassName("aside-content").size() > 0);
		if (aPlanTitle.getText().trim().equals(aPTitle))
			assertTrue("Action Plan Title is correctly displayed");
		else
			assertFalse("Action Plan Title is not corectly displayed");
		fillSlider(driver);
		if (driver.findElementsByXPath("//*[contains(text(),'successfully')]").size() > 0) {
			assertTrue("Progress saved successfully");
		} else
			assertFalse("Toast Messages Not displayed");
		click(closeSidebar, "Close Sidebar");
		Thread.sleep(250);
		assertTrue("Split Screen is closed", driver.findElementsByClassName("aside-content").size() == 0);
		perc = Integer.parseInt(
				driver.findElementByClassName("accor-subsection-percentage").getText().replaceAll("[^0-9]", ""));
		assertTrue("Percentage saved successfully", perc == 100);
		Thread.sleep(500);
	}

	public void endPlan(RemoteWebDriver driver, String hDP) throws Exception {
		click(endPlan, "END PLAN");
		Thread.sleep(250);
		assertTrue("End PLan Pop-up displayed",
				driver.findElementsByTagName("app-closing-hundred-day-plan").size() > 0);

		if (cBoxWhole.getText().contains(hDP) && cBoxState.getAttribute("class").contains("checked"))
			assertTrue(hDP + " is checked");
		else
			assertFalse("Pending 100 day plan not shown/checkbox not checked");
//
//		click(confirmBtn, "Confirm");
//		Thread.sleep(500);
//		assertTrue("Pop-up closed", driver.findElementsByTagName("app-closing-hundred-day-plan").size() == 0);

	}

	void fillSlider(RemoteWebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		int width = sliderBG.getSize().getWidth();
		action.moveToElement(sliderBG).moveByOffset(width / 2-1, 0).click().build().perform();
		Thread.sleep(250);
	}
}
