package com.mop.qa.businessMGMT;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_PlanView_SIA extends PageBase {

	public BM_PlanView_SIA(RemoteWebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[text()='Business Goals']")
	WebElement tabBusinessGoals;
	String li = "./parent::li";

	String findPlan = "//span[contains(text(),'PLANNAME')]";
	@FindBy(css = "span.bm-sia-heading-title")
	WebElement heading;
	@FindBy(css = "img[alt=Close]")
	WebElement closeSidebar;
	@FindBy(css = "img.bm-sia-cross-header")
	WebElement closeSIA;

//	tabs
	@FindBy(xpath = "//app-view-sia-plan-details//span[text()='SIA']")
	WebElement tab_SIA;
	@FindBy(xpath = "//app-view-sia-plan-details//span[text()='SCORECARD']")
	WebElement tab_ScoreCard;
	@FindBy(xpath = "//app-view-sia-plan-details//span[text()='MIS']")
	WebElement tab_MIS;
	@FindBy(css = "mat-dialog-container div.mat-tab-label-active span")
	WebElement activeTab;

//	month slider
	@FindBy(css = "span.bm-sia-sliding-month")
	WebElement monthSlide;
	@FindBy(css = "app-view-sia-plan-details img[src*=next]")
	WebElement slideRight;
	@FindBy(css = "app-view-sia-plan-details img[src*=previous]")
	WebElement slideLeft;
	
	public void verifyHDPandClose(RemoteWebDriver driver, String endedPlan, String unendedPlan) throws Exception {
//		slide left
		click(slideLeft, "Left Slider");
		Thread.sleep(500);
		if(monthSlide.getText().contains("100 Day End"))
			assertTrue("Landed on '100 Day End' Page");
		else
			assertFalse("Did not Land on '100 Day End' Page");
//		ended HDP
		if (driver.findElementsByXPath(findPlan.replace("PLANNAME", endedPlan)).size() > 0) {
			click(findPlan.replace("PLANNAME", endedPlan), "Plan: " + endedPlan);
			assertTrue("Plan " + endedPlan + " is shown in 100 day end");
		} else
			assertFalse("Plan " + endedPlan + " is not shown in 100 day end");
//		unended HDP
		if (driver.findElementsByXPath(findPlan.replace("PLANNAME", unendedPlan)).size() > 0) {
			click(findPlan.replace("PLANNAME", unendedPlan), "Plan: " + unendedPlan);
			assertTrue("Plan " + unendedPlan + " is shown in 100 day end");
		} else
			assertFalse("Plan " + unendedPlan + " is not shown in 100 day end");
//		slide right
		Calendar cal = Calendar.getInstance();
		String month = new SimpleDateFormat("MMM YYYY").format(cal.getTime());
		click(slideRight, "Slide Right");
		Thread.sleep(500);
		if(monthSlide.getText().contains(month))
			assertTrue("Landed Back to SIA View Page");
		else
			assertFalse("Did not land to SIA view page");
//		close and land back to BizGoals Page
		click(closeSIA, "Close SIA view Cross");
		Thread.sleep(1000);
		if (tabBusinessGoals.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Business Goals Tab Active");
		else
			assertFalse("Business Goals Tab not activated");
	}

	public void verifySIAPlans(RemoteWebDriver driver, String endedPlan, String unendedPlan, String SIA_Plan)
			throws Exception {
//		System.out.println("ENDED ==> \t" + endedPlan);
//		System.out.println("UNENDED ==> \t" + unendedPlan);
//		System.out.println(SIA_Plan);

		if (driver.findElementsByXPath(findPlan.replace("PLANNAME", endedPlan)).size() > 0) {
			click(findPlan.replace("PLANNAME", endedPlan), "Plan: " + endedPlan);
			assertTrue("Plan " + endedPlan + " moved to SIA Plans");
		} else
			assertFalse("Plan " + endedPlan + " not moved to SIA Plans");

		if (driver.findElementsByXPath(findPlan.replace("PLANNAME", unendedPlan)).size() == 0) {
			assertTrue("Plan " + unendedPlan + " not moved to SIA Plans");
		} else
			assertFalse("Plan " + unendedPlan + " also moved to SIA Plans");

		if (driver.findElementsByXPath(findPlan.replace("PLANNAME", SIA_Plan)).size() > 0) {
			click(findPlan.replace("PLANNAME", SIA_Plan), "Plan: " + SIA_Plan);
			assertTrue("Plan " + SIA_Plan + " is available in SIA View");
		} else
			assertFalse("Plan " + SIA_Plan + " is not displayed in SIA Plans");
		
		click(closeSidebar, "Close SideBar");
		Thread.sleep(500);
	}

	public void testSIAPage(RemoteWebDriver driver, String company) throws Exception {
//		heading
		if (heading.getText().equals(company))
			assertTrue("'100 Day Plan' removed from heading");
		else
			assertFalse("'100 Day Plan' not removed from heading");
		assertTrue("SIA TAB is active by default", activeTab.getText().contains("SIA"));
		if (driver.findElementsByClassName("hdp-end-plan-btn").size() == 0)
			assertTrue("END PLAN button removed from page");
		else
			assertFalse("END PLAN button not removed from page");

//		tabs
		assertTrue("SCORECARD Tab is Available", tab_ScoreCard.isDisplayed());
		assertTrue("MIS Tab is Available", tab_MIS.isDisplayed());
		assertTrue("SIA Tab is Available", tab_SIA.isDisplayed());
		click(tab_ScoreCard, "SCORECARD");
		Thread.sleep(500);
		assertTrue("SCORECARD TAB is activated", activeTab.getText().contains("SCORECARD"));
		click(tab_MIS, "MIS");
		Thread.sleep(500);
		assertTrue("MIS TAB is activated", activeTab.getText().contains("MIS"));
		click(tab_SIA, "SIA");
		Thread.sleep(500);
		assertTrue("SIA TAB is activated", activeTab.getText().contains("SIA"));

		assertTrue("Month Slider is displayed", monthSlide.isDisplayed());

	}



}
