package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Company_BizGoals extends PageBase{

	public BM_Company_BizGoals(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Business Goals']")
	WebElement tabBusinessGoals;
	String li = "./parent::li";
	
	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(tabBusinessGoals, "Business Goals");
		Thread.sleep(250);
		if(tabBusinessGoals.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Business Goals Tab Active");
		else
			assertFalse("Business Goals Tab not activated");
	}
	
}
