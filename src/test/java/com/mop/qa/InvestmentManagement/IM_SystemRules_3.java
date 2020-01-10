package com.mop.qa.InvestmentManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_SystemRules_3 extends PageBase{

	public IM_SystemRules_3(RemoteWebDriver driver) {
		super(driver);
	}


//	controls
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;
	@FindBy(css = "button.next")
	WebElement nextArrow;
	@FindBy(css = "button.prev")
	WebElement prevArrow;

//	buttons
	@FindBy(xpath = "(//span[text()='SAVE FOR LATER'])[1]")
	WebElement saveBtn_1;
	@FindBy(xpath = "(//span[text()='SUBMIT'])")
	WebElement submit;
	
	@FindBy(xpath = "//div[@id='stepProgress']/span[1]")
	WebElement progressPointer1;
	@FindBy(xpath = "//div[@id='stepProgress']/span[2]")
	WebElement progressPointer2;
	@FindBy(xpath = "//div[@id='stepProgress']/span[3]")
	WebElement progressPointer3;
	
//	inputs - SysRules2
	@FindBy(xpath = "//div[text()='Revenue Growth']/parent::div//input")
	WebElement revenueGrowth;
	@FindBy(xpath = "//div[text()='Revenue Scale']/parent::div//input")
	WebElement revenueScale;
	
//	inputs - SysRules3
	@FindBy(xpath = "//div[text()='ROCE']/parent::div//input")
	WebElement roce;
	@FindBy(xpath = "//div[text()='Gross Margin']/parent::div//input")
	WebElement grossMargin;	
	
	@FindBy(css = "app-tn-rule-submit-dialog")
	WebElement submitRuleDialog;
	
	public void enterDetails(RemoteWebDriver driver, String rOCE, String margin) throws Exception{
		click(prevArrow, "Previous Arrow");
		Thread.sleep(250);
		if (progressPointer2.getAttribute("class").contains("active")) {
			assertTrue("Landed to page 2 of System Rules");
			assertTrue("Revenue Growth is Disabled", !revenueGrowth.isEnabled());
			assertTrue("Revenue Scale is disabled", !revenueScale.isEnabled());
			click(nextArrow, "Next Arrow");
			Thread.sleep(500);
			if (progressPointer3.getAttribute("class").contains("active")) {
				assertTrue("Landed on second page of System Rules");
				click(roce, "ROCE");
				enterText(roce, rOCE, "ROCE");
				Thread.sleep(250);
				click(grossMargin, "Gross Margin");
				enterText(grossMargin, margin, "Gross Margin");
				Thread.sleep(250);
				click(submit, "Submit Button");
				Thread.sleep(1500);
				if(submitRuleDialog.isDisplayed())
					assertTrue("Submit System Rules page is displayed");
				else
					assertFalse("Submit System rules dialog not displayed");
			} else
				assertFalse("Did Not navigate to second page");

		} else
			assertFalse("did not navigate to first page");
	}

}
