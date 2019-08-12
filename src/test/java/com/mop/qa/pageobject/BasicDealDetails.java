package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BasicDealDetails extends PageBase{
	public BasicDealDetails(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//input[@name='projectName']")
	private WebElement txtProjectName;
	@FindBy(xpath = "//input[@formcontrolname='companyName']")
	private WebElement txtCompanyName;
	@FindBy(xpath = "//input[@formcontrolname='trueNorthName']")
	private WebElement txtTrueNorthName;
	@FindBy(xpath = "//*[@formcontrolname='subSector']")
	private WebElement txtSubSector;
	String selectSubsector = "//span[text()=' PLACEHOLDER ']";
	@FindBy(xpath = "//input[@formcontrolname='otherSector']")
	private WebElement txtOtherSector;
	@FindBy(xpath = "//input[@formcontrolname='sourceName']")
	private WebElement txtSourceName;
	String selectSourceName = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//input[@formcontrolname='dealSize']")
	private WebElement txtDealSize;
	String rdBtnStake = "//div[text()='PLACEHOLDER']/parent::label";
	@FindBy(xpath = "(//span[text()='NEXT'])[1]")
	private WebElement btnNext;
	@FindBy(xpath = "(//span[text()='NEXT'])[2]")
	private WebElement btnNext2;
	@FindBy(xpath = "(//span[text()='NEXT'])[3]")
	private WebElement btnNext3;
	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;
	String selectCompanyName = "//span[contains(text(),'PLACEHOLDER')]";
	String rdBtnSector = "//div[text()='PLACEHOLDER']";
	@FindBy(xpath = "//span[text()='DEAL PAGE']")
	private WebElement btnDealPage;
	String verifySuccess = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//div[contains(@class,'field-block')]//label[contains(@class,'field-label')]")
	private WebElement label;
	@FindBy(xpath = "//button[@class='step-btn next mat-button']/span")
	private WebElement btnNavigateForward;
	@FindBy(xpath = "//div[text()=' Company Information ']")
	private WebElement titleCompInfo; 
	@FindBy(xpath = "//input[@name='otherStake']")
	private WebElement inputStake; 
	
public void verifyDealDetails(RemoteWebDriver driver, String projectName, String companyName, String sector, String navigate) throws Exception {
	if(verifyText(pageTitle, "Basic Deal Details")){	
	assertTrue("Page Title is as expected");
	}
	if(driver.findElements(By.xpath("//span[@class='icon active ng-star-inserted']")).size()>0){
		for(int i=1;i<=4;i++){
			if(driver.findElement(By.xpath("//div[@id='stepProgress']/span["+i+"]")).getAttribute("class").contains("active")){
		assertTrue("Progress bar shows "+i+" out of 4th location");
		break;
			}
		}
	}
	for (int i=1;i<=9;i++){
		String label = driver.findElement(By.xpath("(//div[contains(@class,'field-block')]//label[contains(@class,'field-label')])["+i+"]")).getText();
		assertTrue("Labels on Deal Details page are "+label);
	}
	//	String dispProjectName = getText(txtProjectName);
	String dispProjectName = txtProjectName.getAttribute("value");
		if(dispProjectName.contains(projectName)) {
		assertTrue("Project Name is "+dispProjectName);
		}
		else {
			assertFalse("Project Name is not as expected");
		}
		String dispCompanyName = txtCompanyName.getAttribute("value");
		if(dispCompanyName.contains(companyName)) {
			assertTrue("Company Name is "+dispCompanyName);
		}
		else {
			assertFalse("Company Name is not as expected");
		}
		String dispTrueNorthName = txtTrueNorthName.getAttribute("value");;
		if(dispTrueNorthName.contains(companyName)) {
			assertTrue("TrueNorth Name is "+dispTrueNorthName);
		}
		else {
			assertFalse("TrueNorth Name is not as expected");
		}
	//	String dispSector = driver.findElement(By.xpath("//mat-radio-button[contains(@class,'checked')]//div[@class='mat-radio-label-content']")).getText();
		String dispSector = driver.findElement(By.xpath("//div[@class='mat-select-value']/span/span")).getText();
		if(dispSector.contains(sector)) {
			assertTrue("Sector selected is "+dispSector);
		}
		else {
			assertFalse("Sector selected is not as expected");
		}
		if(navigate.contains("Yes")){
			click(btnNavigateForward, "Forward Button");
			Thread.sleep(2000);
			if(titleCompInfo.isDisplayed()){
				assertTrue("Forward Button is clicked and user is navigated to Next Page.");
			}else{
				assertFalse("Forward Button is not clicked.");
			}
		}
}
public void enterDealDetails(RemoteWebDriver driver, String subSector, String source, String dealSize, String stake, String stakePercent) throws Exception {
	click(txtSubSector, "Sub Sector");
	Thread.sleep(1000);
	driver.findElement(By.xpath(selectSubsector.replace("PLACEHOLDER", subSector))).click();
	//enterText(txtSubSector, subSector, "Sub Sector");
	Actions action = new Actions(driver);
	action.moveToElement(txtSourceName);
	click(txtSourceName, "SourceName");
	enterText(txtSourceName, source, "Source Name");
	Thread.sleep(2000);
	driver.findElement(By.xpath(selectSourceName.replace("PLACEHOLDER", source))).click();
	click(txtDealSize, "Deal Size");
	enterText(txtDealSize, dealSize, "Deal Size");
	Thread.sleep(1000);
	driver.findElement(By.xpath(rdBtnStake.replace("PLACEHOLDER", stake))).click();
	driver.findElement(By.xpath(rdBtnStake.replace("PLACEHOLDER", stake))).click();
	Thread.sleep(1000);
	click(inputStake, "Stake Percentage");
	enterText(inputStake, stakePercent, "Stake Percentage");
	click(btnNext, "Next");
	do {
		Thread.sleep(1000);
 	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
}

public void editBasicDealDetails(RemoteWebDriver driver, String projectName, String companyName, String trueNorthName, String sector, String subSector, String otherSector, String source, String dealSize, String stake, String section) throws Exception {
	if(!projectName.equals("NA")) {
		click(txtProjectName, "Project Name");
		driver.findElement(By.xpath("//input[@name='projectName']")).clear();
		enterText(txtProjectName, projectName, "Project Name");
		Thread.sleep(2000);
	}
	if(!companyName.equals("NA")) {
		click(txtCompanyName, "Company Name");
		driver.findElement(By.xpath("//input[@formcontrolname='companyName']")).clear();
		enterText(txtCompanyName, companyName, "Company Name");
		Thread.sleep(3000);	
		driver.findElement(By.xpath(selectCompanyName.replace("PLACEHOLDER", companyName))).click();
		Thread.sleep(2000);	
	}
	if(!trueNorthName.equals("NA")) {
		click(txtTrueNorthName, "TrueNorth Name");
		driver.findElement(By.xpath("//input[@formcontrolname='trueNorthName']")).clear();
		enterText(txtTrueNorthName, trueNorthName, "TrueNorth Name");
		Thread.sleep(2000);
	}
	if(!sector.equals("NA")) {
		driver.findElement(By.xpath(rdBtnSector.replace("PLACEHOLDER", sector))).click();
		Thread.sleep(2000);
		if(sector.equals("Other")) {
			click(txtOtherSector, "SubSector Name");
			Thread.sleep(1000);
			enterText(txtOtherSector, otherSector, "Other Sector Name");
		}
	}
	if(!source.equals("NA")) {
		click(txtSourceName, "Source Name");
		driver.findElement(By.xpath("//input[@formcontrolname='sourceName']")).clear();
		enterText(txtSourceName, source, "Source Name");
		Thread.sleep(2000);
		driver.findElement(By.xpath(selectSourceName.replace("PLACEHOLDER", source))).click();
		Thread.sleep(2000);
	}
	if(!dealSize.equals("NA")) {
		click(txtDealSize, "Deal size");
		driver.findElement(By.xpath("//input[@formcontrolname='dealSize']")).clear();
		enterText(txtDealSize, dealSize, "TrueNorth Name");
		Thread.sleep(2000);
	}
	if(!stake.equals("NA")) {
		driver.findElement(By.xpath(rdBtnStake.replace("PLACEHOLDER", stake))).click();
		driver.findElement(By.xpath(rdBtnStake.replace("PLACEHOLDER", stake))).click();
		Thread.sleep(2000);
	}
	
	if(section.contains("Company Basic Details")) {
	click(btnNext, "Next");
	Thread.sleep(1000);
	}
}

}