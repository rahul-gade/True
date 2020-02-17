package com.mop.qa.businessMGMT;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Folders extends PageBase{

	public BM_Folders(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "li img[src*=document]")
	WebElement docsTab;
	
	
	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(docsTab, "Documents");
		Thread.sleep(500);
		if (driver.findElementsByCssSelector("img[src*='document-active-icon']").size() > 0)
			assertTrue("Documents tab is active");
		else
			assertFalse("Documents Tab Not activated");
	}
}
