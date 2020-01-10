package com.mop.qa.InvestmentManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_SystemRules_2 extends PageBase {

	public IM_SystemRules_2(RemoteWebDriver driver) {
		super(driver);
	}

//	controls
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;
	@FindBy(css = "button.next")
	WebElement nextArrow;
	@FindBy(css = "button.prev")
	WebElement prevArrow;

//	inputs - SysRules1
	@FindBy(xpath = "//div[text()='Deal Size']/parent::div//input")
	WebElement dealSize;
	@FindBy(xpath = "//div[text()='Sector']/parent::div//input")
	WebElement sector;
	@FindBy(xpath = "//div[text()='Stake']/parent::div//input")
	WebElement stake;
	@FindBy(xpath = "//div[text()='EBITDA Growth']/parent::div//input")
	WebElement ebitdaGrowth;

//	inputs - SysRules2
	@FindBy(xpath = "//div[text()='Revenue Growth']/parent::div//input")
	WebElement revenueGrowth;
	@FindBy(xpath = "//div[text()='Revenue Scale']/parent::div//input")
	WebElement revenueScale;

	@FindBy(xpath = "//div[@id='stepProgress']/span[1]")
	WebElement progressPointer1;
	@FindBy(xpath = "//div[@id='stepProgress']/span[2]")
	WebElement progressPointer2;
	@FindBy(xpath = "//div[@id='stepProgress']/span[3]")
	WebElement progressPointer3;

	public void enterDetails(RemoteWebDriver remoteDriver, String growth, String scale) throws Exception {
		click(prevArrow, "Previous Arrow");
		Thread.sleep(250);
		if (progressPointer1.getAttribute("class").contains("active")) {
			assertTrue("Landed to page 1 of System Rules");
			assertTrue("Deal Size is Disabled", !dealSize.isEnabled());
			assertTrue("Sector is disabled", !sector.isEnabled());
			assertTrue("Stake is Disabled", !stake.isEnabled());
			assertTrue("EBITDA Growth is disabled", !ebitdaGrowth.isEnabled());

			click(nextArrow, "Next Arrow");
			Thread.sleep(500);
			if (progressPointer2.getAttribute("class").contains("active")) {
				assertTrue("Landed on second page of System Rules");
				click(revenueGrowth, "Revenue Growth");
				enterText(revenueGrowth, growth, "Revenue Growth");
				Thread.sleep(250);
				click(revenueScale, "Revenue Scale");
				enterText(revenueScale, scale, "Revenue Scale");
				Thread.sleep(250);
				click(nextArrow, "next Arrow");
				Thread.sleep(1500);
				if (progressPointer3.getAttribute("class").contains("active"))
					assertTrue("landed on 3rd page of system rules");
				else
					assertFalse("Did not navigate to third page");
			} else
				assertFalse("Did Not navigate to second page");
		} else
			assertFalse("did not navigate to first page");
	}

}
