package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class EditDealHomePage extends PageBase {
	public EditDealHomePage(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}
	@FindBy(xpath = "(//a[@class='edit-icon'])[1]")
	private WebElement editCompanyBasicDetails;
	@FindBy(xpath = "//div[contains(text(),'Company')]/a[@class='edit-icon']")
	private WebElement editCompanyBackground;
	@FindBy(xpath = "//div[contains(text(),'Product')]/a[@class='edit-icon']")
	private WebElement editProductBrief;
	@FindBy(xpath = "//div[contains(text(),'Current')]/a[@class='edit-icon']")
	private WebElement editCurrentLeadership;
	@FindBy(xpath = "//span[contains(text(),'Key')]/preceding-sibling::a[@class='edit-icon']")
	private WebElement editKeyShareHolders;
	@FindBy(xpath = "//div[contains(text(),'Financials')]/a[@class='edit-icon']")
	private WebElement editFinancials;
	@FindBy(xpath = "//div[contains(text(),'Industry')]/a[@class='edit-icon']")
	private WebElement editIndustrySpecifics;
	@FindBy(xpath = "//span[contains(text(),'Competitor')]/preceding-sibling::a[@class='edit-icon']")
	private WebElement editCompetitorShare;
	@FindBy(xpath = "(//a[text()='INPUT NOW'])[1]")
	private WebElement inputLikeability;
	@FindBy(xpath = "//span[text()='SUBMIT']/parent::button")
	private WebElement btnSubmit;
	@FindBy(xpath = "//a[text()='INPUT NOW']")
	private WebElement inputProbability;
	@FindBy(xpath = "//label[text()='Reasons for the above']/following-sibling::ul")
	private WebElement txtReason;
	@FindBy(xpath = "//label[text()='Reasons for the above']/following-sibling::ul/li")
	private WebElement inputReason;
	@FindBy(xpath = "(//a[@class='action-btn'])[2]")
	private WebElement btnClose;
	@FindBy(xpath = "//a[text()='ADD']")
	private WebElement btnAdd;
	@FindBy(xpath = "//div[@class='header']")
	private WebElement header;
	@FindBy(xpath = "//label[text()='Search Profile']")
	private WebElement labelProfile;
	@FindBy(xpath = "//label[contains(text(),'Search')]/ancestor::div[1]/input")
	private WebElement inputProfile;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnCloseMember;
	String selectName = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//a[@title='Folder']")
	private WebElement btnFolder;
	@FindBy(xpath = "//p[text()='One Pager']")
	private WebElement labelPager;
	@FindBy(xpath = "//div[text()='DMS']")
	private WebElement tabDMS;
//	@FindBy(xpath = "//div[text()=' Custom DMS Document ']")
	@FindBy(xpath = "//span[text()=' test.jpg ']")
	private WebElement labelCustom;
	@FindBy(xpath = "//div[@class='mat-slider-thumb']")
	private WebElement likeabilitySlider;
	String btnBack = "//span[text()='PLACEHOLDER']/ancestor::ul//a[@class='link']";
//	private WebElement btnBack;

	public void editSection(RemoteWebDriver driver, String section) throws Exception {
//		do {
//			Thread.sleep(1000);
//		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		Thread.sleep(3000);
		switch (section) {
		case "Company Basic Details":
			click(editCompanyBasicDetails, "Company Basic Details");
			break;
		case "Company Background":
			click(editCompanyBackground, "Company Background");
			break;
		case "Product Brief":
			click(editProductBrief, "Product Brief");
			break;
		case "Current Leadership":
			click(editCurrentLeadership, "Current Leadership");
			break;
		case "Key ShareHolders":
			click(editKeyShareHolders, "Key ShareHolders");
			break;
		case "Financials":
			click(editFinancials, "Financials");
			break;
		case "Industry Specifics":
			click(editIndustrySpecifics, "Industry Specifics");
			break;
		case "Competitor Share":
			click(editCompetitorShare, "Competitor Share");
			break;
		}
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
	}

	public void editLikeability(RemoteWebDriver driver, String impact, String financial, String attraction,
			String likeability, String reason) throws Exception {
		click(inputLikeability, "Likeability");
		waitForVisibilityOfElement(btnSubmit);
		int imp = Integer.parseInt(impact);
		int fin = Integer.parseInt(financial);
		int att = Integer.parseInt(attraction);
		int like = Integer.parseInt(likeability);

		for (int i = 0; i < imp; i++) {
			driver.findElement(By.xpath("(//a[text()='+'])[1]")).click();
		}
		Thread.sleep(1000);
		for (int i = 0; i < fin; i++) {
			driver.findElement(By.xpath("(//a[text()='+'])[3]")).click();
		}
		Thread.sleep(1000);
		for (int i = 0; i < att; i++) {
			driver.findElement(By.xpath("(//a[text()='+'])[2]")).click();
		}
		Thread.sleep(1000);
		// driver.findElement(By.xpath("//li/span[text()='"+like+"']")).click();
		Actions action = new Actions(remoteDriver);
		action.clickAndHold(likeabilitySlider)
				.moveToElement(driver.findElement(By.xpath("//li/span[text()='" + like + "']"))).release().build()
				.perform();
		Thread.sleep(1000);
		click(txtReason, "Reason");
		Thread.sleep(1000);
		txtReason.sendKeys(Keys.ENTER);
		enterText(txtReason, reason, "Reason");
		click(btnSubmit, "Submit Button");
		if (driver.findElements(By.xpath("(//a[@class='action-btn'])[2]")).size() > 0) {
			click(btnClose, "Close");
		}
	}

	public void editProbability(RemoteWebDriver driver, String probability, String reason) throws Exception {
			Thread.sleep(1000);
		click(inputProbability, "Probability");
		waitForVisibilityOfElement(btnSubmit);
		int pro = Integer.parseInt(probability);
		Thread.sleep(1000);
		Actions action = new Actions(remoteDriver);
		action.clickAndHold(likeabilitySlider)
				.moveToElement(driver.findElement(By.xpath("//li/span[text()='" + pro + "']"))).release().build()
				.perform();
		Thread.sleep(1000);
		click(txtReason, "Reason");
		Thread.sleep(1000);
		txtReason.sendKeys(Keys.ENTER);
		enterText(txtReason, reason, "Reason");
		txtReason.sendKeys(Keys.ENTER);
		click(btnSubmit, "Submit Button");
		if (driver.findElements(By.xpath("(//a[@class='action-btn'])[2]")).size() > 0) {
			click(btnClose, "Close");
		}
		
	}

	public void addContact(RemoteWebDriver driver, String profile) throws Exception {
		click(btnAdd, "ADD");
		waitForVisibilityOfElement(header);
		Thread.sleep(1000);
		// click(labelProfile, "Profile");
		// Thread.sleep(1000);
		// labelProfile.sendKeys(Keys.ENTER);
		click(inputProfile, "Profile");
		enterText(inputProfile, profile, "Profile");
		Thread.sleep(2000);
		driver.findElement(By.xpath(selectName.replace("PLACEHOLDER", profile))).click();
		Thread.sleep(1000);
		click(btnCloseMember, "Close");
	}

	public void validateFolder(RemoteWebDriver driver, String project) throws Exception {
		click(btnFolder, "Folder");
		waitForVisibilityOfElement(labelPager);
		if (labelPager.isDisplayed()) {
			assertTrue("System tab is displayed");
		}
		Thread.sleep(1000);
		click(tabDMS, "DMS");
		Thread.sleep(1000);
		waitForVisibilityOfElement(labelCustom);
		if (labelCustom.isDisplayed()) {
			assertTrue("DMS tab is Clicked");
		}
		driver.findElement(By.xpath(btnBack.replace("PLACEHOLDER", project))).click();
	}
}
