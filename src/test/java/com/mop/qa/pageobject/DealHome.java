package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class DealHome extends PageBase{
	public DealHome(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//a[contains(text(),'NEW DEAL')]/span")
	private WebElement btnNewDeal;
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//input[@name='projectName']")
	private WebElement txtProjectName;
	@FindBy(xpath = "//input[@formcontrolname='dealOwner']/preceding-sibling::mat-chip")
	private WebElement txtDealOwner;
	@FindBy(xpath = "//input[@formcontrolname='companyName']")
	private WebElement txtCompanyName;
	@FindBy(xpath = "//mat-select[@formcontrolname='sector']")
	private WebElement sectors;
	String selectSector = "//span[text()='PLACEHOLDER']";
	String selectCompanyName = "//span[contains(text(),'PLACEHOLDER')]";
	String rdBtnSector = "//div[text()='PLACEHOLDER']";
	@FindBy(xpath = "//label[text()='UPLOAD']")
	private WebElement linkUpload;
	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//a[contains(text(),'FILL') or contains(text(),'PROCEED')]")
	private WebElement fillMyself;
	@FindBy(xpath = "//span[contains(text(),'IB')]")
	private WebElement requestIB;
	@FindBy(xpath = "//label[text()='UPLOAD']")
	private WebElement fileUpload;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;
	@FindBy(xpath = "//span[contains(text(),'YES')]")
	private WebElement btnDiscardDeal;
	@FindBy(xpath = "//span[text()='GO BACK']")
	private WebElement btnGoBack;
	@FindBy(xpath = "//a[text()='My Deals']")
	private WebElement txtMyDeals;
	String owner = "//*[contains(text(),'PLACEHOLDER')]";
	
public void verifyDealOwner(RemoteWebDriver driver, String dOwner) throws Exception {
		click(btnNewDeal, "New Deal");
		Thread.sleep(1000);
		WebElement dealOwner = driver.findElement(By.xpath(owner.replace("PLACEHOLDER", dOwner)));
		if(getText(dealOwner).contains(dOwner)) {
			assertTrue("Deal Owner is displayed as "+getText(dealOwner));
		}else {
			assertFalse("Deal Owner is different than logged in User");
		}
	}
	
public void cancelDeal(RemoteWebDriver driver) throws Exception {	
		click(btnNewDeal, "New Deal");
		Thread.sleep(1000);
		click(btnClose, "Close Button");
		waitForVisibilityOfElement(btnDiscardDeal);
		if(driver.findElements(By.xpath("//span[text()='YES']")).size()>0) {
			click(btnGoBack, "Go Back");
			if(pageTitle.getText().contains("Deal Specifics")) {
				assertTrue("Go Back Button is clicked and Add new Deal Screen is displayed ");
			}else {
				assertFalse("Go Back Button is not clicked");
			}
			click(btnClose, "Close Button");
			waitForVisibilityOfElement(btnDiscardDeal);
			click(btnDiscardDeal, "DISCARD DEAL");
			do {
				Thread.sleep(1000);
	     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
			if(driver.findElements(By.xpath("//a[text()='My Deals']")).size()>0) {
				assertTrue("Discard Deal Button is clicked and IM Landing page is displayed ");
			}else {
				assertFalse("Discard Deal Button is not clicked");
			}
		}else {
			assertFalse("Close Button is not clicked");
		}
	}
	
	public void createDealHomePage(RemoteWebDriver driver, String projectName, String companyName, String sector, String filePath) throws Exception {
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(btnNewDeal, "New Deal");
		Thread.sleep(1000);
		verifyText(pageTitle, "Deal Specifics");
		if(pageTitle.getText().contains("Deal Specifics")) {
			assertTrue("PageTitle is as expected");
		}
		click(txtProjectName, "Project Name");
		enterText(txtProjectName, projectName, "Project Name");
		click(txtCompanyName, "Company Name");
		enterText(txtCompanyName, companyName, "Company Name");
		Thread.sleep(2000);	
		driver.findElement(By.xpath(selectCompanyName.replace("PLACEHOLDER", companyName))).click();
		Thread.sleep(1000);	
		if(driver.findElements(By.xpath(rdBtnSector.replace("PLACEHOLDER", sector))).size()>0){
		driver.findElement(By.xpath(rdBtnSector.replace("PLACEHOLDER", sector))).click();
		}else if(driver.findElements(By.xpath("//mat-select[@formcontrolname='sector']")).size()>0){
			click(sectors, "Sector Dropdown");
			driver.findElement(By.xpath(selectSector.replace("PLACEHOLDER", sector))).click();
		}
		Thread.sleep(1000);
		click(fileUpload, "UPLOAD");
		FileUpload file = new FileUpload(remoteDriver);
		file.fileUpload(filePath);
		waitForVisibilityOfElement(btnSubmit);
		click(btnSubmit, "Submit");
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;		
	}
	
	public void selectFlow(RemoteWebDriver driver, String flow) throws Exception {
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		verifyText(pageTitle, "Success!");
		if(flow.contains("Myself")) {
			click(fillMyself, "FILL IN THE DETAILS MYSELF");
			Thread.sleep(1000);
		}else {
			click(requestIB, "RequestIB");
			Thread.sleep(1000);
		}
	}
}
