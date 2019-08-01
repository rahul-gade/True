package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;


public class JRBenchmark extends PageBase{
	public JRBenchmark(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "//div[text()='INR']")
	private WebElement selectCurrency;
	@FindBy(xpath = "//span[text()='CTC Min']/ancestor::div[1]/input")
	private WebElement inputCTCMin;
	@FindBy(xpath = "(//div[@class='mat-select-arrow'])[4]")
	private WebElement minCTCDropdown;
	@FindBy(xpath = "//span[text()='LAKHS']")
	private WebElement selectLakh;
	@FindBy(xpath = "//span[text()='CTC Max']/ancestor::div[1]/input")
	private WebElement inputCTCMax;
	@FindBy(xpath = "(//div[@class='mat-select-arrow'])[5]")
	private WebElement maxCTCDropdown;
	@FindBy(xpath = "(//span[text()='LAKHS'])[2]")
	private WebElement selectMaxLakh;
	@FindBy(xpath = "//input[@id='benchmark_candidate']")
	private WebElement txtBenchmarkCandidate;
	String select = "//div[text()=' PLACEHOLDER']";
	@FindBy(xpath = "//input[@id='targetCompany']")
	private WebElement txtTargetCompany;
	@FindBy(xpath = "//span[text()='Preferred Sector']/ancestor::div[1]//div[@class='mat-select-arrow']")
	private WebElement drpdownSector;
//	@FindBy(xpath = "(//div[@class='cdk-overlay-pane']//span)[1]")
//	private WebElement selectSector;
	String sector = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//input[@id='subSector']")
	private WebElement inputSubSector;
	String subSector = "//div[text()=' PLACEHOLDER ']";
	@FindBy(xpath = "(//span[text()='NEXT'])[5]")
	private WebElement btnNext;
	@FindBy(xpath = "(//div[@class='suggested-card ng-star-inserted'])[1]")
	private WebElement selectInterviewers;
	@FindBy(xpath = "//input[@id='interviewers']")
	private WebElement inputInterviewer;
	String interviewer = "//div[text()=' PLACEHOLDER']";
	@FindBy(xpath = "(//span[text()='NEXT'])[6]")
	private WebElement btnNext1;
	@FindBy(xpath = "//button[@class='step-btn next mat-button']/span")
	private WebElement arrowNext;
	
	
	public void enterBenchmarkDetails(RemoteWebDriver driver, String min, String max, String candidate, String company, String sectors, String subsector) throws Exception {	
		Thread.sleep(1000);
		click(selectCurrency, "Currency");
		Thread.sleep(1000);
		click(inputCTCMin, "Minimum CTC");
		enterText(inputCTCMin, min, "Minimum CTC");
		Thread.sleep(1000);
		click(minCTCDropdown, "Minimum CTC Dropdown");
		Thread.sleep(1000);
		click(selectLakh, "Select Minimum CTC Dropdown");
		Thread.sleep(1000);
		click(inputCTCMax, "Maximum CTC");
		enterText(inputCTCMax, max, "Maximum CTC");
		Thread.sleep(1000);
		click(maxCTCDropdown, "Maximum CTC Dropdown");
		Thread.sleep(1000);
		click(selectMaxLakh, "Select Maximum CTC Dropdown");
		Thread.sleep(1000);
		click(txtBenchmarkCandidate, "Benchmark Candidate");
		enterText(txtBenchmarkCandidate, candidate, "Benchmark Candidate");
		Thread.sleep(1000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", candidate))).click();
		Thread.sleep(1000);
		click(txtTargetCompany, "Target Company");
		enterText(txtTargetCompany, company, "Target Company");
		if(driver.findElements(By.xpath("//span[text()='Preferred Sector']/ancestor::div[1]//div[@class='mat-select-arrow']")).size()>0) {
			click(drpdownSector, "Desired Sector");
			Thread.sleep(100);
			WebElement selectSector = driver.findElement(By.xpath(sector.replace("PLACEHOLDER", sectors)));
			Actions action = new Actions(driver);
			action.moveToElement(selectSector);
			click(selectSector, "Sector");
			Thread.sleep(100);
			click(inputSubSector, "SubSector");
			Thread.sleep(500);
			enterText(inputSubSector, subsector, "SubSector Text");
			WebElement selectSubSector = driver.findElement(By.xpath(subSector.replace("PLACEHOLDER", subsector)));
			action.moveToElement(selectSubSector);
			click(selectSubSector, "Select SubSector");
		}
		click(btnNext, "NEXT");
		Thread.sleep(1000);
	}
	
		public void selectInterviewer(RemoteWebDriver driver, String candidate, String choose) throws Exception {
			if(!choose.contains("No")) {
				enterInterviewer(remoteDriver, candidate);
			}else {
			click(arrowNext, "Arrow NEXT");
			Thread.sleep(1000);	
			}
	}
		
		public void enterInterviewer(RemoteWebDriver driver, String candidate) throws Exception {
			if(driver.findElements(By.xpath("(//div[@class='suggested-card ng-star-inserted'])[1]")).size()>0) {
				click(selectInterviewers, "Interviewers");
				Thread.sleep(1000);
				}
				else {
					click(inputInterviewer, "Interviewer");
					enterText(inputInterviewer, candidate, "Interviewer");
					Thread.sleep(1000);
					driver.findElement(By.xpath(interviewer.replace("PLACEHOLDER", candidate))).click();
				}
				click(btnNext1, "NEXT");
				Thread.sleep(1000);
		}
}
