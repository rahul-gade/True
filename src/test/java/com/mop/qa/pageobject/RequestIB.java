package com.mop.qa.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class RequestIB extends PageBase{
	public RequestIB(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	//private static final Logger LOGGER = Logger.getLogger(CnngoPageWebMobile.class.getName());
	@FindBy(xpath = "//input[@role='combobox']")
	private WebElement txtrecepients;
	@FindBy(xpath = "//input[@placeholder='Subject']")
	private WebElement txtSubject;
	@FindBy(xpath = "//textarea[@placeholder='Email Body']")
	private WebElement txtEmailBody;
	@FindBy(xpath = "//span[text()='SEND']")
	private WebElement btnsend;
	@FindBy(xpath = "(//a[@class=\"close-btn\"])[2]")
	private WebElement btnClose;
	
	public void requestInformation(String recepients) throws Exception {
		click(txtrecepients, "Receipients");
		enterText(txtrecepients, recepients, "Receipients");
		Thread.sleep(2000);
		click(txtSubject, "Subject");
		Thread.sleep(3000);
		if(btnsend.isEnabled()) {
			click(btnClose, "Close");
		}
	}
}
