package com.mop.qa.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class CompanyFinancials extends PageBase{
	public CompanyFinancials(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	//private static final Logger LOGGER = Logger.getLogger(CnngoPageWebMobile.class.getName());
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "(//span[text()='NEXT'])[3]")
	private WebElement btnNext;
	public void enterCompanyDetails() throws Exception {
		
		click(btnNext, "Next");
	}
}
