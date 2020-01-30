package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Company_VisMis extends PageBase{

	public BM_Company_VisMis(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Vision & Mission']")
	WebElement tabVisionMission;
	String li = "./parent::li"; 
	
	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(tabVisionMission, "Vision And Mission");
		Thread.sleep(250);
		if(tabVisionMission.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Vision and Mission Tab Active");
		else
			assertFalse("Vision and Mission Tab not activated");
	}
}
