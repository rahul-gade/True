package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.mop.qa.testbase.PageBase;

public class CreateNewDocument extends PageBase{
	public CreateNewDocument(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	String typeOfDocument = "//a[text()=' PLACEHOLDER ']";
	public void createNewDocument(RemoteWebDriver driver, String documentType) throws Exception {
		driver.findElement(By.xpath(typeOfDocument.replace("PLACEHOLDER", documentType))).click();
	}
}
