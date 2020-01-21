package com.mop.qa.InvestmentManagement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_DSum_CompFinThesis extends PageBase {

	public IM_DSum_CompFinThesis(RemoteWebDriver driver) {
		super(driver);
	}

//	company information
	@FindBy(xpath = "//div[@class='dd-panel'][1]")
	WebElement companySection;
	@FindBy(xpath = "//div[@class='dd-panel'][1]//a")
	WebElement editCompany;
	@FindBy(xpath = "//p[text()='Listed']/following-sibling::mat-form-field")
	WebElement listed;
	String listOption = "//span[text()='OPTION']";
	@FindBy(xpath = "//p[text()='Market Cap (INR Mn)']//following-sibling::mat-form-field//input")
	WebElement marketCap;
	@FindBy(xpath = "//p[text()='Headquarters']//following-sibling::mat-form-field//input")
	WebElement hq;
	@FindBy(xpath = "//p[text()='Founder/CEO']//following-sibling::mat-form-field//input")
	WebElement CEO;
	@FindBy(xpath = "//p[text()='Product / Service ']//following-sibling::app-bullet-list//ul")
	WebElement prodList;
	@FindBy(css = "div.percentage input")
	List<WebElement> percList;
	@FindBy(css = "div.shareholders-name input")
	List<WebElement> sHoldList;
	@FindBy(xpath = "//p[text()='Geographic Focus ']//following-sibling::mat-form-field/div")
	WebElement geoFocus;
	@FindBy(xpath = "(//p[text()='Key Markets']//following-sibling::ul//mat-form-field)[1]")
	WebElement markerIndia;
	@FindBy(xpath = "(//p[text()='Key Markets']//following-sibling::ul//mat-form-field)[2]")
	WebElement markerInterNational;

//	global
	@FindBy(css = "span.dd-title")
	WebElement dealHeader;
	@FindBy(css = "button.btn")
	WebElement saveBtn; // also SUBMIT BTN while in rule run view
	@FindBy(css = "button.link")
	WebElement cancelBtn;

//	financials
	@FindBy(xpath = "//div[@class='dd-panel'][2]")
	WebElement finSection;

//	attractiveness Index
	@FindBy(css = "div.attractiveness-content")
	WebElement indexCard;
	@FindBy(css = "div.header span")
	WebElement header;
	@FindBy(css = "div.header a")
	WebElement closeBtn;
	@FindBy(css = "div.tn-rule-run-button")
	WebElement ruleRunBtn;
	@FindBy(css = "table.rules-log-table")
	WebElement ruleTable;
	@FindBy(css = "h4.title") // System Rule Run View
	WebElement sysRuleView;

//	sysrule inputs
	@FindBy(xpath = "//div[text()='Deal Size']/parent::div//input")
	WebElement dealSize;
	@FindBy(xpath = "//div[text()='Sector']/parent::div//input")
	WebElement sector;
	@FindBy(xpath = "//div[text()='Stake']/parent::div//input")
	WebElement stake;
	@FindBy(xpath = "//div[text()='EBITDA Growth']/parent::div//input")
	WebElement ebitdaGrowth;
	@FindBy(xpath = "//div[text()='Revenue Growth']/parent::div//input")
	WebElement revenueGrowth;
	@FindBy(xpath = "//div[text()='Revenue Scale']/parent::div//input")
	WebElement revenueScale;
	@FindBy(xpath = "//div[text()='ROCE']/parent::div//input")
	WebElement roce;
	@FindBy(xpath = "//div[text()='Gross Margin']/parent::div//input")
	WebElement grossMargin;
	@FindBy(css = "app-tn-rule-run-submit-dialog")
	WebElement submitRuleDialog;
	@FindBy(css = "a.link")
	WebElement goToDealPage;

//	Thesis - Risks
	@FindBy(xpath = "//div[text()=' 2. IT Services Section 2 ']//following-sibling::div//app-bullet-list[1]//ul")
	WebElement thesis_2;
	@FindBy(xpath = "//div[text()=' 2. IT Services Section 2 ']//following-sibling::div//app-bullet-list[2]//ul")
	WebElement risk_2;
	@FindBy(xpath = "//div[@class='dd-panel'][3]")
	WebElement thesisSection;
	@FindBy(xpath = "//div[@class='dd-panel'][3]//a")
	WebElement editThesis;
	@FindBy(css = "a[routerLink='hypothesis']")
	WebElement hypothesisTab;
	@FindBy(css = "a[routerLink='summary']")
	WebElement detailsTab;

	public void enterThesis(RemoteWebDriver driver) throws Exception {
		if (driver.findElements(By.cssSelector("a[routerLink='hypothesis']")).size() > 0)
			assertFalse("Hypothesis Tab Already Active");
		else
			assertTrue("Hypothesis Tab Inactive by default");

		Actions action = new Actions(driver);
		action.moveToElement(thesisSection).perform();
		click(editThesis, "Edit Thesis Section");
		Thread.sleep(250);
		action.moveToElement(thesis_2).click().build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		WebElement thesisList = thesis_2.findElement(By.xpath("./li[1]"));
		action.moveToElement(thesisList).click().build().perform();
		action.sendKeys("Thesis Data Part 1").build().perform();
		Thread.sleep(250);
		action.sendKeys(Keys.ENTER).build().perform();
		thesisList = thesis_2.findElement(By.xpath("./li[2]"));
		action.moveToElement(thesisList).click().build().perform();
		action.sendKeys("Thesis Data Part 2").build().perform();
		Thread.sleep(250);

		click(saveBtn, "Save");
		Thread.sleep(250);
		if (driver.findElements(By.cssSelector("a[routerLink='hypothesis']")).size() > 0) {
			assertTrue("Hypothesis Tab Activated");
			action.moveToElement(hypothesisTab).click().build().perform();
			Thread.sleep(500);
			if (driver.findElements(By.cssSelector("section.hypotheses-section")).size() > 0)
				assertTrue("Hypothessis Section Opened");
			else
				assertFalse("Hypothessis Section Opened");
			Thread.sleep(500);

		} else
			assertFalse("Hypothesis Tab Not Activated");
	}

	public void editAttractiveness(RemoteWebDriver remoteDriver, String dealSize2, String ruleSector, String ruleStake,
			String ebitda, String growth, String scale, String rOCE2, String margin) throws Exception {
		click(dealSize, "Deal Size");
		dealSize2 = String.valueOf(Integer.parseInt(dealSize2) + 5);
		enterText(dealSize, dealSize2, "Deal Size");
		Thread.sleep(250);

		click(ebitdaGrowth, "EBITDA Growth");
		ebitda = String.valueOf(Integer.parseInt(ebitda) + 7);
		enterText(ebitdaGrowth, ebitda, "EBITDA Growth");
		Thread.sleep(250);

		click(roce, "ROCE");
		rOCE2 = String.valueOf(Integer.parseInt(rOCE2) - 23);
		enterText(roce, rOCE2, "ROCE");
		Thread.sleep(250);

		click(saveBtn, "Submit");
		Thread.sleep(1000);
		if (submitRuleDialog.isDisplayed())
			assertTrue("Submit System Rules page is displayed");
		else
			assertFalse("Submit System rules dialog not displayed");

		click(goToDealPage, "Go To Deal Page");
		Thread.sleep(2000);
		if (dealHeader.isDisplayed())
			assertTrue("Landed on deal details Page, with project name: " + dealHeader.getText().trim());
		else
			assertFalse("Deal Details Page did not Open.");
	}

	public void testAttractiveness(RemoteWebDriver driver) throws Exception {
		if (indexCard.isDisplayed())
			assertTrue("Attractiveness Index is Displayed");
		else
			assertFalse("Attractiveness Index is NOT Displayed");

		click(indexCard, "Attractiveness Card");
		Thread.sleep(250);
		if (header.getText().equals("LOG"))
			assertTrue("Log Page is displayed");
		else
			assertFalse("Log Page is not Displayed");
		click(closeBtn, "Close");
		assertTrue("Landed Back on deall summary Page", indexCard.isDisplayed());

		click(indexCard, "Attractiveness Card");
		Thread.sleep(250);
		assertTrue("Rules Log Table is displayed", ruleTable.isDisplayed());
		click(ruleRunBtn, "TN Rule Run View");
		Thread.sleep(250);
		if (driver.findElementsByXPath("//h4[text()='System Rule Run View']").size() > 0)
			assertTrue("Landed on System Rule Run View");
		else
			assertFalse("System Rule Run View didn't open");
		Thread.sleep(500);
	}

	public void analyzeCompanyInfo(RemoteWebDriver driver, String cap, String HQ, String ceo, String prods,
			String holders, String perces, String focus, String india, String international) throws Exception {
//		String[] services = prods.split(",");
		String[] sHolders = holders.split(",");
		String[] sPerc = perces.split(",");
		String[] Focus = focus.split(",");
		String[] mark_Ind = india.split(",");
		String[] mark_Int = international.split(",");
		Actions action = new Actions(driver);

		action.moveToElement(companySection).perform();
		click(editCompany, "Edit Pencil");
		Thread.sleep(250);
		click(listed, "Listed Option");
		Thread.sleep(250);
		click("//mat-option//span[text()='Yes']", "Yes");
		Thread.sleep(250);
		if (marketCap.isDisplayed()) {
			assertTrue("Market Capital Field is displayed");
			click(marketCap, "Market Cap");
			enterText(marketCap, cap, "Market Cap");
		} else
			assertFalse("Market Capital Field is not displayed");
// 		HQ+CEO ==================================
		Thread.sleep(250);
		click(hq, "Headquarters");
		enterText(hq, HQ, "HeadQuarters");
		Thread.sleep(250);
		click(CEO, "CEO");
		enterText(CEO, ceo, "CEO");
// 		ShareHolders ============================
		for (int i = 0; i < percList.size(); i++) {
			click(percList.get(i), "Percentage" + i + 1);
			enterText(percList.get(i), sPerc[i], "Percentage" + i + 1);
			Thread.sleep(250);
			click(sHoldList.get(i), "ShareHolder" + i + 1);
			enterText(sHoldList.get(i), sHolders[i], "Shareholder" + i + 1);
			Thread.sleep(250);
		}
// 		Product/Service==========================
//		click(prodList, "Products/Services");
//		Thread.sleep(250);
//		for (int i = 0; i < services.length; i++) {
//			WebElement bullet = prodList.findElement(By.xpath("./li[" + (i + 1) + "]"));
//			action.sendKeys(Keys.ENTER).build().perform();
//			enterText(bullet, services[i], "Bullet "+i + 1);
//			if (i < services.length - 1) {
//				action.sendKeys(Keys.ENTER).build().perform();
//			}
//		}

//		geoFocus
		action.moveToElement(finSection).perform();
		click(geoFocus, "Geographic Focus");
//		action.moveToElement(geoFocus).click().build().perform();
		Thread.sleep(250);
		for (String s : Focus) {
			click("//span[contains(text(),'" + s + "')]", "Option " + s);
			Thread.sleep(100);
		}
		action.moveToElement(companySection.findElement(By.xpath("./div/div"))).click().build().perform();
		Thread.sleep(100);

//		key markets
		click(markerIndia, "Market India");
		Thread.sleep(250);
		for (String s : mark_Ind) {
			click("//span[contains(text(),'" + s + "')]", "Option " + s);
			Thread.sleep(100);
		}
		action.moveToElement(companySection.findElement(By.xpath("./div/div"))).click().build().perform();
		Thread.sleep(250);
		click(markerInterNational, "Market International");
		Thread.sleep(250);
		for (String s : mark_Int) {
			click("//span[contains(text(),'" + s + "')]", "Option " + s); // span[contains(text(),'')]
			Thread.sleep(100);
		}
		action.moveToElement(companySection.findElement(By.xpath("./div/div"))).click().build().perform();
		Thread.sleep(250);
		click(saveBtn, "CancelButton");
		Thread.sleep(1000);
	}
}
