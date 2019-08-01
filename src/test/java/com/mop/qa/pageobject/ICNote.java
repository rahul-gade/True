package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class ICNote extends PageBase{
	public ICNote(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//label[@for='fileInput']")
	private WebElement fileUpload;
	@FindBy(xpath = "//span[text()='UPLOAD']")
	private WebElement btnUpload;
	
	public void createIcNote(String filePath) throws Exception {
		click(fileUpload, "UPLOAD");
		Thread.sleep(2000);
		FileUpload file = new FileUpload(remoteDriver);
		file.fileUpload(filePath);
		waitForVisibilityOfElement(btnUpload);
		click(btnUpload, "Submit");
	}
}
