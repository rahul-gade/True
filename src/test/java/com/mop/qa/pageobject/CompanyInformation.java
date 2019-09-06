package com.mop.qa.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class CompanyInformation extends PageBase {
	public CompanyInformation(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//*[@formcontrolname='companybg']/parent::div//span/label")
	private WebElement lblCompanyBg;
	@FindBy(xpath = "//*[@formcontrolname='brief']/parent::div//span/label")
	private WebElement lblCompanyBrief;
	@FindBy(xpath = "//*[@formcontrolname='leadership']/ancestor::div[2]//span/label")
	private WebElement lblCurrentLeadership;
	@FindBy(xpath = "(//*[@formcontrolname='stakeholdername']/parent::div//span/label)[1]")
	private WebElement lblShareHoderName;
	@FindBy(xpath = "(//*[@formcontrolname='sharePercentage']/parent::div//span/label)[1]")
	private WebElement lblSharePercentage;
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//textarea[@formcontrolname='companybg']")
	private WebElement txtCompanyBackground;
	String compBG = "";
	@FindBy(xpath = "//textarea[@formcontrolname='brief']")
	private WebElement txtProductBrief;
	@FindBy(xpath = "//input[@formcontrolname='leadership']")
	private WebElement txtCurrentLeadership;
	// @FindBy(xpath = "//span[contains(text(),'Bran')]")
	// private WebElement selectLeader;
	String selectLeader = "//*[contains(text(),'PLACEHOLDER')]";
	@FindBy(xpath = "//label[text()='Shareholder Name']/parent::span/parent::div/input")
	private WebElement txtShareHolder;
	@FindBy(xpath = "(//label[text()='Share Percentage']/parent::span/parent::div/input)[1]")
	private WebElement txtSharePercentage;
	@FindBy(xpath = "(//a[text()='ADD ANOTHER'])[1]")
	private WebElement linkAddAnother;
	@FindBy(xpath = "(//label[text()='Shareholder Name']/parent::span/parent::div/input)[2]")
	private WebElement txtShareHolder2;
	@FindBy(xpath = "(//label[text()='Share Percentage']/parent::span/parent::div/input)[2]")
	private WebElement txtSharePercentage2;
	@FindBy(xpath = "(//span[text()='NEXT'])[2]")
	private WebElement btnNext;
	@FindBy(xpath = "(//span[text()='NEXT'])[3]")
	private WebElement btnNext2;
	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='DEAL PAGE']")
	private WebElement btnDealPage;
	String verifySuccess = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;
	@FindBy(xpath = "//a[text()='Deal Details']")
	private WebElement tabDealDetails;
	int i = 1;

	public void enterCompanyDetails(RemoteWebDriver driver, String cmpInfo, String leader, String shareHolder,
			String percentage, String mandatory) throws Exception {
		if (i == 2) {
			BasicDealDetails basic = new BasicDealDetails(remoteDriver);
			mandatory = "Yes";
			basic.checkMandatoryField(lblCompanyBg, "Company Background");
			basic.checkMandatoryField(lblCompanyBrief, "Company Brief");
			basic.checkMandatoryField(lblCurrentLeadership, "Current Leadership");
			basic.checkMandatoryField(lblShareHoderName, "ShareHolder Name");
			basic.checkMandatoryField(lblSharePercentage, "Share Percentage");
		}
		if (mandatory.contains("No")) {
			click(btnNext, "Next");
			i++;
			mandatory = "Yes";
		} else {
			if (driver.findElement(By.xpath("//textarea[@formcontrolname='companybg']")).getAttribute("value")
					.isEmpty()) {
				click(txtCompanyBackground, "Company Background");
				enterText(txtCompanyBackground, cmpInfo, "Company Background");
			}
			compBG = txtCompanyBackground.getAttribute("value");
			Thread.sleep(1000);
			click(txtProductBrief, "Product/Service Brief");
			enterText(txtProductBrief, cmpInfo, "Product/Service Brief");
			Thread.sleep(1000);
			click(txtCurrentLeadership, "Current Leadership");
			enterText(txtCurrentLeadership, leader, "Current Leadership");
//			Thread.sleep(2000);
			// click(selectLeader, "Current Leadership");
//		driver.findElement(By.xpath(selectLeader.replace("PLACEHOLDER", leader))).click();
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ARROW_DOWN);
			action.sendKeys(Keys.ENTER);
			action.sendKeys(Keys.RETURN);
			action.build().perform();
			click(txtShareHolder, "Key ShareHolders");
			enterText(txtShareHolder, shareHolder, "Key ShareHolders");
			click(txtSharePercentage, "Share Percentage");
			enterText(txtSharePercentage, percentage, "Share Percentage");
			click(btnNext, "Next");
		}
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
	}

	public void editCompanyDetails(RemoteWebDriver driver, String cmpBackground, String cmpInfo, String leader,
			String shareHolder, String percentage, String section) throws Exception {

		if (!cmpBackground.equals("NA")) {
			click(txtCompanyBackground, "Company Background");
			driver.findElement(By.xpath("//textarea[@formcontrolname='companybg']")).clear();
			enterText(txtCompanyBackground, cmpBackground, "Company Name");
			Thread.sleep(2000);
		}
		if (!cmpInfo.equals("NA")) {
			click(txtProductBrief, "Product Brief");
			driver.findElement(By.xpath("//textarea[@formcontrolname='brief']")).clear();
			enterText(txtProductBrief, cmpInfo, "Product Brief");
			Thread.sleep(2000);
		}
		if (!leader.equals("NA")) {
			click(txtCurrentLeadership, "Current Leadership");
			driver.findElement(By.xpath("//input[@formcontrolname='leadership']")).clear();
			enterText(txtCurrentLeadership, leader, "Current Leadership");
			Thread.sleep(2000);
			driver.findElement(By.xpath(selectLeader.replace("PLACEHOLDER", leader))).click();
			Thread.sleep(2000);
		}
		if (!shareHolder.equals("NA")) {
			click(txtShareHolder, "ShareHolder Name");
			driver.findElement(By.xpath("//label[text()='Shareholder Name']/parent::span/parent::div/input")).clear();
			enterText(txtShareHolder, shareHolder, "ShareHolder Name");
			int len = driver.findElement(By.xpath("//label[text()='Shareholder Name']/parent::span/parent::div/input"))
					.getText().length();
			if (len <= 150) {
				assertTrue("ShareHolder accepts less than 150 characters");
			} else {
				assertFalse("ShareHolder accepts more than 150 characters");
			}
			Thread.sleep(2000);
		}
		if (!percentage.equals("NA")) {
			click(txtSharePercentage, "Share Percentage");
			driver.findElement(By.xpath("(//label[text()='Share Percentage']/parent::span/parent::div/input)[1]"))
					.clear();
			enterText(txtSharePercentage, percentage, "Share Percentage");
			Thread.sleep(2000);
		}

		if (section.contains("Company Basic Details") || section.contains("Company Background")
				|| section.contains("Product Brief") || section.contains("Current Leadership")
				|| section.contains("Key ShareHolders")) {
			click(btnNext, "Next");
			Thread.sleep(1000);
			click(btnNext2, "Next");
			Thread.sleep(1000);
		}
		/*
		 * click(btnSubmit, "Submit"); Thread.sleep(1000); click(btnDealPage,
		 * "Deal Page"); Thread.sleep(2000);
		 * if(driver.findElement(By.xpath(verifySuccess.replace("PLACEHOLDER",
		 * projectName))).isDisplayed()) { assertTrue("Deal is Successfully edited");
		 * }else { assertFalse("Deal is not edited"); }
		 */

	}

	public void verifyCompanyInfo(RemoteWebDriver driver, String companyName) throws Exception {
		if (verifyText(pageTitle, "Company Information")) {
			assertTrue("Page Title is as expected");
		}
		if (driver.findElements(By.xpath("//span[contains(@class,'active')]")).size() > 0) {
			for (int i = 1; i <= 4; i++) {
				if (driver.findElement(By.xpath("//div[@id='stepProgress']/span[" + i + "]")).getAttribute("class")
						.contains("active")) {
					assertTrue("Progress bar shows " + i + " out of 4th location");
					break;
				}
			}
		}
		for (int i = 1; i <= 3; i++) {
			String label = driver.findElement(By.xpath(
					"(//div[text()=' Enter the information of the company selected ']/parent::div//div[contains(@class,'field-infix')]//label[contains(@class,'field-label')])["
							+ i + "]"))
					.getText();
			assertTrue("Labels on Company Information page are " + label);
		}
		for (int i = 1; i <= 2; i++) {
			String label = driver
					.findElement(By.xpath("(//h3[text()='Key Shareholders']/parent::div//label)[" + i + "]")).getText();
			assertTrue("Labels on Key Shareholders section are " + label);
		}
		if (driver.findElements(By.xpath("//*[@name='Shareholder Name']//a[text()='ADD ANOTHER']")).size() > 0) {
			assertTrue("ADD Another link is displayed");
		} else {
			assertFalse("Add Another Link is not displayed");
		}
		if (driver.findElements(By.xpath("(//span[text()='NEXT'])[2]")).size() > 0) {
			assertTrue("NEXT button is displayed");
		} else {
			assertFalse("NEXT button is not displayed");
		}
		// String dispProjectName = getText(txtProjectName);
		String dispCompanyOverview = txtCompanyBackground.getAttribute("value");
		if (dispCompanyOverview.isEmpty() && companyName.isEmpty()) {
			assertTrue("Company Overview is not displayed when Company field is blank");
		} else if (!dispCompanyOverview.isEmpty() && companyName.isEmpty()) {
			assertFalse("Company Overview is displayed when Company field is blank");
		} else {
			assertTrue("Company Overview is displayed when Company field is not blank");
		}
	}
}
