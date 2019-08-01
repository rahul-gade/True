package com.mop.qa.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mop.qa.testbase.PageBase;

public class IndustryInformation extends PageBase{
	public IndustryInformation(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	//private static final Logger LOGGER = Logger.getLogger(CnngoPageWebMobile.class.getName());
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//input[@formcontrolname='industrySize']")
	private WebElement txtIndutrySize;
	@FindBy(xpath = "//input[@formcontrolname='targetMarketSizePercent']")
	private WebElement txtTargetCompMarketShare;
	@FindBy(xpath = "//input[@formcontrolname='last3YearGrowthPercent']")
	private WebElement txtLast3Year;
	@FindBy(xpath = "//input[@formcontrolname='last5YearGrowthPercent']")
	private WebElement txtLast5Year;
	@FindBy(xpath = "//input[@formcontrolname='predicted3YearGrowthPercent']")
	private WebElement txtPredicted3Year;
	@FindBy(xpath = "//input[@formcontrolname='predicted5YearGrowthPercent']")
	private WebElement txtPredicted5Year;
	@FindBy(xpath = "(//input[@formcontrolname='stakeholdername'])[2]")
	private WebElement txtCompetitorName;
	@FindBy(xpath = "(//input[@formcontrolname='sharePercentage'])[2]")
	private WebElement txtCompetitorPercentage;
	@FindBy(xpath = "(//input[@formcontrolname='stakeholdername'])[3]")
	private WebElement txtCompetitorName2;
	@FindBy(xpath = "(//input[@formcontrolname='sharePercentage'])[3]")
	private WebElement txtCompetitorPercentage2;
	@FindBy(xpath = "//span[text()='SUBMIT']/parent::button")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='DEAL PAGE']")
	private WebElement btnDealPage;
	String verifySuccess = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;
	@FindBy(xpath = "//a[text()='Deal Details']")
	private WebElement tabDealDetails;
	
	public void enterIndustryDetails(RemoteWebDriver driver, String projectName, String indSize, String trgtMktShare, String last3year, String last5year, String pred3year, String pred5year, String competitorName, String percent) throws Exception {
		click(txtIndutrySize, "Industry Size");
		enterText(txtIndutrySize, indSize, "Industry Size");
		Thread.sleep(100);
		click(txtTargetCompMarketShare, "Target Company Market Share");
		enterText(txtTargetCompMarketShare, trgtMktShare, "Target Company Market Share");
		Thread.sleep(100);
		click(txtLast3Year, "Last 3 Year Growth");
		enterText(txtLast3Year, last3year, "Last 3 Year Growth");
		Thread.sleep(100);
		click(txtLast5Year, "Last 5 Year Growth");
		enterText(txtLast5Year, last5year, "Last 5 Year Growth");
		Thread.sleep(100);
		click(txtPredicted3Year, "Predicted 3 Year Growth");
		enterText(txtPredicted3Year, pred3year, "Predicted 3 Year Growth");
		Thread.sleep(100);
		click(txtPredicted5Year, "Predicted 5 Year Growth");
		enterText(txtPredicted5Year, pred5year, "Predicted 5 Year Growth");
		Thread.sleep(100);
		click(txtCompetitorName, "Competitor Name");
		enterText(txtCompetitorName, competitorName, "Competitor Name");
		Thread.sleep(100);
		click(txtCompetitorPercentage, "Share Percentage");
		enterText(txtCompetitorPercentage, percent, "Share Percentage");
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(btnSubmit, "Submit");
		/*if(driver.findElements(By.xpath("//span[text()='SUBMIT']")).size()>0) {
		Actions action = new Actions(remoteDriver);
		action.moveToElement(btnSubmit).click().build().perform();
		}*/
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(btnDealPage, "Deal Page");
		Thread.sleep(1000);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		if(driver.findElement(By.xpath(verifySuccess.replace("PLACEHOLDER", projectName))).isDisplayed()) {
			assertTrue("Deal is Successfully created");
		}else {
			assertFalse("Deal is not created");
		}
	}
	
	public void editIndustryDetails(RemoteWebDriver driver, String projectName, String editprojectName, String indSize, String trgtMktShare, String last3year, String last5year, String pred3year, String pred5year, String competitorName, String percent, String section) throws Exception {
		if(!indSize.equals("NA")) {
			click(txtIndutrySize, "Industry Size");
			driver.findElement(By.xpath("//input[@formcontrolname='industrySize']")).clear();
			enterText(txtIndutrySize, indSize, "Industry Size");
			Thread.sleep(2000);
		}
		if(!trgtMktShare.equals("NA")) {
			click(txtTargetCompMarketShare, "Target Company Market Share");
			driver.findElement(By.xpath("//input[@formcontrolname='targetMarketSizePercent']")).clear();
			enterText(txtTargetCompMarketShare, trgtMktShare, "Target Company Market Share");
			Thread.sleep(2000);
		}
		if(!last3year.equals("NA")) {
			click(txtLast3Year, "Last 3 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='last3YearGrowthPercent']")).clear();
			enterText(txtLast3Year, last3year, "Last 3 Year Growth");
			Thread.sleep(2000);
		}
		if(!last5year.equals("NA")) {
			click(txtLast5Year, "Last 5 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='last5YearGrowthPercent']")).clear();
			enterText(txtLast5Year, last5year, "Last 5 Year Growth");
			Thread.sleep(2000);
		}
		if(!pred3year.equals("NA")) {
			click(txtPredicted3Year, "Predicted 3 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='predicted3YearGrowthPercent']")).clear();
			enterText(txtPredicted3Year, pred3year, "Predicted 3 Year Growth");
			Thread.sleep(2000);
		}
		if(!pred5year.equals("NA")) {
			click(txtPredicted5Year, "Predicted 5 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='predicted5YearGrowthPercent']")).clear();
			enterText(txtPredicted5Year, pred5year, "Predicted 5 Year Growth");
			Thread.sleep(2000);
		}
		if(!competitorName.equals("NA")) {
			click(txtCompetitorName, "Competitor Name");
			driver.findElement(By.xpath("(//input[@formcontrolname='stakeholdername'])[2]")).clear();
			enterText(txtCompetitorName, competitorName, "Competitor Name");
			Thread.sleep(2000);
		}
		if(!percent.equals("NA")) {
			click(txtCompetitorPercentage, "Share Percentage");
			driver.findElement(By.xpath("(//input[@formcontrolname='sharePercentage'])[2]")).clear();
			enterText(txtCompetitorPercentage, percent, "Share Percentage");
			Thread.sleep(2000);
		}
		
		click(btnSubmit, "Submit");
		Thread.sleep(2000);
		click(btnDealPage, "Deal Page");
		Thread.sleep(2000);
		String proj;
		if(editprojectName.equals("NA")){
			proj=projectName;
		}else{
			proj=editprojectName;
		}
		if(driver.findElement(By.xpath(verifySuccess.replace("PLACEHOLDER", proj))).isDisplayed()) {
			assertTrue("Deal is Successfully edited");
		}else {
			assertFalse("Deal is not edited");
		}
	}
}
