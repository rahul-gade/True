package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Home extends PageBase {

	public BM_Home(RemoteWebDriver driver) {
		super(driver);
	}

//	launchApp
	@FindBy(xpath = "//input[@name='loginfmt']")
	private WebElement userName;
	@FindBy(xpath = "//input[@name='passwd']")
	private WebElement password;
	@FindBy(xpath = "//input[@id='idSIButton9']")
	private WebElement next;
	@FindBy(xpath = "//input[@id='idBtn_Back']")
	private WebElement no;
	@FindBy(css = "div.center-navigation")
	WebElement centerNav;
	@FindBy(xpath = "//mat-option//span[contains(text(),'BUSINESS MANAGEMENT')]")
	WebElement BMNavOption;

//	landing page interactions
	@FindBy(css = "div.landing-deal-header")
	WebElement landingHeader;
	String sectorLink = "//span[contains(text(),'STAGE')]"; // XPATH <== for clicking in sector list
	@FindBy(css = "ul.sorting-list li:nth-of-type(1)")
	WebElement gridView;
	@FindBy(css = "ul.sorting-list li:nth-of-type(2)")
	WebElement listView;
	String cardMaker = "div.SECTOR div[title='COMPANY']"; // CSS ==> for finding the card
	@FindBy(css = "span.company-name")
	WebElement comapnyHeader;
	@FindBy(css = "span.hdp-heading-title")
	WebElement HDP_Heading;
	@FindBy(css = "a[routerlink='/Business-Management/']")
	WebElement backBtn;
	@FindBy(css = "img.bm-sia-cross-header")
	WebElement closePlanPage;

	public void findProject(RemoteWebDriver driver, String stage, String company, String sector) throws Exception {
		Thread.sleep(2000);
		assertTrue("Home Page Loaded Successfully", landingHeader.isDisplayed());
		WebElement targetCard = null;
		String cssField = cardMaker.replace("SECTOR", sector).replace("COMPANY", company);
		try {
			targetCard = driver.findElementByCssSelector(cssField).findElement(By.xpath("./parent::div"));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			assertFalse("Company Card Not Found");
		}
		click(targetCard, "Selected Comapany Card");
		Thread.sleep(1000);
		if (comapnyHeader.getText().contains(company))
			assertTrue(" Project Found and Landed on Company Page");
		else
			assertFalse("Did not land on company Page");
	}

	public void testBMHome(RemoteWebDriver driver, String sect, String company, String sector) throws Exception {
		Thread.sleep(2000);
		assertTrue("Home Page Loaded Successfully", landingHeader.isDisplayed());
		
//		list view
		click(listView, "List View");
		Thread.sleep(500);
		if (driver.findElements(By.cssSelector("grid-view-section")).size() == 0)
			assertTrue("Display changed to list View");
		else
			assertFalse("Display not changed to list view");
//		grid view
		click(gridView, "Grid View");
		Thread.sleep(500);
		if (driver.findElements(By.cssSelector("list-view-section")).size() == 0)
			assertTrue("Display changed to grid View");
		else
			assertFalse("Display not changed to grid view");
//		compile CSS Selector String for Project Card
		String cssField = cardMaker.replace("SECTOR", sector).replace("COMPANY", company);
//		make card Element
		WebElement targetCard = null;
		try {
			targetCard = driver.findElementByCssSelector(cssField).findElement(By.xpath("./parent::div"));
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse("Company Card not formed in BM");
		}
		click(targetCard, "Comapany Card");
		Thread.sleep(1000);
		if (comapnyHeader.getText().contains(company))
			assertTrue("Landed on Company Page");
		else
			assertFalse("Did not land on company Page");
		click(backBtn, "Back Button");
		Thread.sleep(1000);
		assertTrue("Landed Back to home Page", landingHeader.isDisplayed());

//		updating card element to avoid ==> Stale-Element-Reference-Exception
		targetCard = driver.findElementByCssSelector(cssField).findElement(By.xpath("./parent::div"));
		click(targetCard.findElement(By.xpath(".//app-blue-desktop-icon/div")), "Blue Desktop Icon");
		Thread.sleep(1000);
		if (HDP_Heading.getText().contains(company))
			assertTrue("Landed on corressponding plan page");
		else
			assertFalse("Did not land to plan page");
		click(closePlanPage, "Close Button");
		Thread.sleep(1000);
		assertTrue("Landed Back to home Page", landingHeader.isDisplayed());
	}

	public void launchApp(RemoteWebDriver driver, String url, String uName, String pwd) throws Exception {
		enterUrl(url);
		Thread.sleep(2000);
		click(userName, "User Name");
		enterText(userName, uName, "UserName");
		click(next, "Next");
		Thread.sleep(2000);
		click(password, "Password");
		enterText(password, pwd, "Password");
		click(next, "SignIn");
		Thread.sleep(2000);
		if (driver.findElementsByXPath("//input[@id='idBtn_Back']").size() > 0) {
			click(no, "Dont keep Signed-in");
			Thread.sleep(3000);
		}
		waitForPageLoad();
		click(centerNav, "Central Navigation");
		Thread.sleep(200);
		click(BMNavOption, "Business Management");
		waitForPageLoad();
		do {
			Thread.sleep(1000);
		} while (driver.findElementsByCssSelector("img[src*=spinner]").size() > 0);
	}

	public void openBM(RemoteWebDriver driver, String URL) throws Exception {
		if (driver.findElementsByClassName("center-navigation").size() == 0) {
			enterUrl(URL);
			Thread.sleep(1000);
			waitForPageLoad();
		}
		click(centerNav, "Central Navigation");
		Thread.sleep(200);
		click(BMNavOption, "Business Management");
		waitForPageLoad();
		do {
			Thread.sleep(1000);
		} while (driver.findElementsByCssSelector("img[src*=spinner]").size() > 0);
	}

}
