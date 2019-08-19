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
	// private static final Logger LOGGER =// Logger.getLogger(CnngoPageWebMobile.class.getName());
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//textarea[@formcontrolname='companybg']")
	private WebElement txtCompanyBackground;
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

	public void enterCompanyDetails(RemoteWebDriver driver, String cmpInfo, String leader, String shareHolder, String percentage) throws Exception {
		// click(txtCompanyBackground, "Company Background");
		// enterText(txtCompanyBackground, cmpInfo, "Company Background");
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
}
