package com.mop.qa.InvestmentManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_SystemRules extends PageBase{

	public IM_SystemRules(RemoteWebDriver driver) {
		super(driver);
	}

//	nav
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;
	@FindBy(css = "button.next")
	WebElement nextArrow;
	
//	inputs
	@FindBy(xpath = "//div[text()='Deal Size']/parent::div//input")
	WebElement dealSize;
	@FindBy(xpath = "//div[text()='Sector']/parent::div//input")
	WebElement sector;
	@FindBy(xpath = "//div[text()='Stake']/parent::div//input")
	WebElement stake;
	@FindBy(xpath = "//div[text()='EBITDA Growth']/parent::div//input")
	WebElement ebitdaGrowth;
	
//	buttons
	@FindBy(xpath = "(//span[text()='SAVE FOR LATER'])[1]")
	WebElement saveBtn_1;
	@FindBy(xpath = "(//span[text()='NEXT'])[1]")
	WebElement nextBtn_1;
	
	@FindBy(xpath = "//div[@id='stepProgress']/span[1]")
	WebElement progressPointer1;
	@FindBy(xpath = "//div[@id='stepProgress']/span[2]")
	WebElement progressPointer2;
	
	
	public void enterDetails(RemoteWebDriver driver, String size, String ruleSector, String ruleStake,
			String ebitda) throws Exception {
		Thread.sleep(500);
		if(progressPointer1.getAttribute("class").contains("active")) {
			assertTrue("Progress Bar is at position 1");
			click(dealSize, "Deal Size");
			enterText(dealSize, size, "Deal Size");
			Thread.sleep(250);
			
			click(sector, "Sector");
			enterText(sector, ruleSector, "Sector");
			Thread.sleep(250);
			
			click(stake, "Stake");
			enterText(stake, ruleStake, "Stake");
			Thread.sleep(250);
			
			click(ebitdaGrowth, "EBITDA Growth");
			enterText(ebitdaGrowth, ebitda, "EBITDA Growth");
			Thread.sleep(250);
			
			click(nextBtn_1, "Next");
			Thread.sleep(1500);
			if(progressPointer2.getAttribute("class").contains("active"))
				assertTrue("Progress pointer is in position 2");
			else
				assertFalse("Progresspointer Not Proper");
		} else
			assertFalse("Progresspointer Not Proper");
	}
	
	
}
