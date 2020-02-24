package com.mop.qa.businessMGMT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_PlanView extends PageBase {

	public BM_PlanView(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "span.business-goal-view-txt")
	WebElement goalView;
	@FindBy(css = "span.hdp-end-plan-btn")
	WebElement endPlan;

//	app-view-hundred-day-plan <== Page App Component!!!
	public void endPlan(RemoteWebDriver driver) throws Exception {
		click(goalView, "View");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-view-hundred-day-plan").size() > 0)
			assertTrue("HUndred day plan page opened");
		else
			assertFalse("HUndred day plan page NOT opened");

		click(endPlan, "END PLAN");
		Thread.sleep(250);
		if (driver.findElementsByTagName("app-closing-hundred-day-plan").size() > 0)
			assertTrue("End PLan Pop-up displayed");
		else
			assertFalse("End PLan Pop-up NOT displayed");
	}

}
