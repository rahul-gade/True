package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class JRTeamDetails extends PageBase{
	public JRTeamDetails(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	
	@FindBy(xpath = "//input[@id='reportingTo']")
	private WebElement inputReportingTo;
	String select = "//div[text()=' PLACEHOLDER']";
	@FindBy(xpath = "//input[@id='reportees']")
	private WebElement inputReportees;
	@FindBy(xpath = "//input[@id='stakeholders']")
	private WebElement inputStakeholders;
	@FindBy(xpath = "(//mat-chip)[5]")
	private WebElement selectStakeholders;
	@FindBy(xpath = "(//span[text()='NEXT'])[3]")
	private WebElement btnNext;
	
public void enterTeamDetails(RemoteWebDriver driver, String reportingTo, String[] reportees, String stakeholders) throws Exception {
		try{
		Thread.sleep(1000);
		click(inputReportingTo, "Reporting To");
		enterText(inputReportingTo, reportingTo, "Reporting To");
		Thread.sleep(1000);
		if(driver.findElements(By.xpath(select.replace("PLACEHOLDER", reportingTo))).size()>0){
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", reportingTo))).click();
		Thread.sleep(1000);
		}else{
			assertFalse("Reporting To not found");
		}
		for (int i=0;i<reportees.length;i++) {
		click(inputReportees, "Reportees");
		enterText(inputReportees, reportees[i], "Reportees");
		Thread.sleep(1000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", reportees[i]))).click();
		}
		click(inputStakeholders, "Stakeholder");
		enterText(inputStakeholders, stakeholders, "Stakeholder");
		Thread.sleep(1000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", stakeholders))).click();
		Thread.sleep(2000);
		//click(selectStakeholders, "Stakeholder");
		click(btnNext, "Next");
		Thread.sleep(1000);
		}catch(Exception e){
			e.getMessage();
		}
	}
}
