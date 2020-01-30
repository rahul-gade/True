package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Company_ProjMgmt extends PageBase{

	public BM_Company_ProjMgmt(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//a[text()='Project Management']")
	WebElement tabProjMgmt;
	String li = "./parent::li";
	
	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(tabProjMgmt, "Project Management");
		Thread.sleep(250);
		if(tabProjMgmt.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Project Management Tab Active");
		else
			assertFalse("Project Management Tab not activated");
	}

}
