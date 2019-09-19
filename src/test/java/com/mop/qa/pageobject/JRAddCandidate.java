package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mop.qa.testbase.PageBase;

public class JRAddCandidate extends PageBase {
	public JRAddCandidate(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//span[text()='LIVE']")
	WebElement tabLive;
	@FindBy(xpath = "//span[text()='LIVE']/following-sibling::span")
	WebElement countLive;
	@FindBy(xpath = "//a[text()='Longlisted ']")
	WebElement tabLongList;
	@FindBy(xpath = "//a[text()=' Add Candidate ']")
	WebElement addCandidate;
	@FindBy(xpath = "//p[contains(text(),'Candidate')]")
	WebElement addCandidateHeader;
	@FindBy(xpath = "//input[@formcontrolname='nameData']")
	WebElement txtName;
	@FindBy(xpath = "//input[@formcontrolname='companyNameData']")
	WebElement txtCompany;
	@FindBy(xpath = "//input[@formcontrolname='positionData']")
	WebElement txtPosition;
	String selectPosition = "//span[text()=' PLACEHOLDER ']";
	@FindBy(xpath = "//input[@formcontrolname='emailIDData']")
	WebElement txtEmailId;
	@FindBy(xpath = "//input[@formcontrolname='contactNoData']")
	WebElement txtContactNumber;
	@FindBy(xpath = "//span[text()='ADD']")
	WebElement btnAdd;
	@FindBy(xpath = "/div/div/img")
	WebElement loadImg;
	@FindBy(xpath = "(//a[text()='SHORTLIST'])[1]")
	WebElement linkShortlist;
	@FindBy(xpath = "//span[text()='GO AHEAD']")
	WebElement btnGoAhead;
	@FindBy(xpath = "//span[text()='NO THANKS']")
	WebElement btnNoThanks;
	@FindBy(xpath = "//a[contains(@routerlink,'Talent-Acquisition')]")
	WebElement btnBack;
	@FindBy(xpath = "//div[text()='Longlist']")
	WebElement linkLonglist;
	@FindBy(xpath = "//div[@class='drafts-list ng-star-inserted'][]//div[@class='data']")
	WebElement labelCompany;
	@FindBy(xpath = "//div[@class='drafts-list ng-star-inserted'][]//div[@class='position']")
	WebElement labelPosition;
	@FindBy(xpath = "//div[@class='drafts-list ng-star-inserted'][]//div[@class='status']")
	WebElement labelPositionq;
	@FindBy(xpath = "//a[text()='Shortlisted ']")
	WebElement tabShortListed;
	@FindBy(xpath = "(//button[@class='mat-icon-button'])[1]")
	WebElement btnChoose;
	@FindBy(xpath = "//span[text()='ADD TO PROSPECT']")
	WebElement linkAddToProspect;
	@FindBy(xpath = "//span[text()='REJECT']")
	WebElement linkReject;
	@FindBy(xpath = "//div[@class='header']")
	WebElement txtHeader;
	@FindBy(xpath = "(//div[@class='mat-slider-thumb'])[1]")
	WebElement sliderOurInterest;
	@FindBy(xpath = "(//li/span[text()='4'])[1]")
	WebElement pointOurInterest;
	@FindBy(xpath = "(//div[@class='mat-slider-thumb'])[2]")
	WebElement sliderCandidateInterest;
	@FindBy(xpath = "(//li/span[text()='4'])[2]")
	WebElement pointCandidateInterest;
	@FindBy(xpath = "//a[text()='Prospects ']")
	WebElement tabProspect;
	@FindBy(xpath = "//a[text()='Prospects ']/span")
	WebElement countProspect;

	public void navigateLongList(RemoteWebDriver driver, String position, String company) throws Exception {

		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
//		waitForVisibilityOfElement(tabLive);
		click(tabLive, "LIVE");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		String live = getText(countLive, "LIVE");
		int count = Integer.parseInt(live);
		for (int i = 1; i <= count; i++) {
			String pos = driver
					.findElement(By.xpath("(//div[@class='drafts-list ng-star-inserted'][" + i + "]//li/div)[1]"))
					.getText();
			String comp = driver
					.findElement(By.xpath("(//div[@class='drafts-list ng-star-inserted'][" + i + "]//li/div)[2]"))
					.getText();
			if (pos.contains(position) && comp.contains(company)) {
				driver.findElement(By.xpath("(//div[@class='drafts-list ng-star-inserted'][" + i + "]//li/div)[1]"))
						.click();
				break;
			}
		}
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img[contains(@src,'spinner')]")).size() > 0);
		Thread.sleep(1000);
		click(tabLongList, "Long List");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
	}

	public void addCandidate(RemoteWebDriver driver) throws Exception {
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		click(addCandidate, "Add Candidate");
		Thread.sleep(2000);
	}

	public void enterCandidateDetails(RemoteWebDriver driver, String name, String company, String position,
			String email, String contact) throws Exception {
		if (driver.findElements(By.xpath("//p[contains(text(),'Candidate')]")).size() > 0) {
			assertTrue("Candidate details page is displayed");
			DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
			Date date = new Date();
			String num = dateFormat.format(date);
			click(txtName, "Name");
			name = name + num;
			enterText(txtName, name, "Name");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			click(txtCompany, "Company");
			enterText(txtCompany, company, "Company");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			driver.findElement(By.xpath(selectPosition.replace("PLACEHOLDER", company))).click();
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			click(txtPosition, "Position");
			enterText(txtPosition, position, "Position");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			driver.findElement(By.xpath(selectPosition.replace("PLACEHOLDER", position))).click();
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			click(txtEmailId, "Email Id");
			email = num + email;
			enterText(txtEmailId, email, "Email Id");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			click(txtContactNumber, "Contact Number");
			contact = contact.substring(0, 5);
			contact = contact + num.substring(5, 10);
			enterText(txtContactNumber, contact, "Contact Number");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			click(btnAdd, "Add");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			if (driver.findElements(By.xpath("//a[text()='SHORTLIST']")).size() > 0) {
				assertTrue("Candidate added successfully");
			} else {
				assertFalse("Candidate details page is not displayed");
			}
		}
	}

	public void selectJRWithLonglist(RemoteWebDriver driver, String position, String company) throws Exception {
		int count = driver.findElements(By.xpath("//div[@class='drafts-list ng-star-inserted']")).size();
		for (int i = 1; i < count; i++) {
			String pos = driver
					.findElement(
							By.xpath("//div[@class='drafts-list ng-star-inserted'][" + i + "]//div[@class='position']"))
					.getText();
			String comp = driver
					.findElement(By.xpath("//div[@class='drafts-list ng-star-inserted'][" + "]//div[@class='data']"))
					.getText();
			if (pos.contains(position) && comp.contains(company)) {
				if (driver
						.findElements(By
								.xpath("//div[@class='drafts-list ng-star-inserted'][" + i + "]//div[@class='status']"))
						.size() > 0) {
					driver.findElement(By.xpath("//div[@class='drafts-list ng-star-inserted'][" + i
							+ "]//div[@class='status' and text()='Longlist']")).click();
				}
			}
		}
	}

	public void shortlistCandidate(RemoteWebDriver driver) throws Exception {
		int count = 0;
		if (driver.findElements(By.xpath("//a[text()='SHORTLIST']")).size() > 0) {
			if (driver.findElements(By.xpath("//a[text()='Shortlisted ']/span")).size() > 0) {
				String number = driver.findElement(By.xpath("//a[text()='Shortlisted ']/span")).getText();
				count = Integer.parseInt(number);
			}
			assertTrue("ShortList option available");
			click(linkShortlist, "ShortList");
			Thread.sleep(100);
			// click(btnGoAhead, "Go Ahead");
			click(btnNoThanks, "No Thanks");
			Thread.sleep(100);
			click(tabShortListed, "ShortListed");
			String numbers = driver.findElement(By.xpath("//a[text()='Shortlisted ']/span")).getText();
			int count1 = Integer.parseInt(numbers);
			if (count1 > count) {
				assertTrue("Candidate is Shortlisted");
			} else {
				assertFalse("Candidate is not Shortlisted");
			}
		} else {
			assertFalse("Candidate details page is not displayed");
		}
	}

	public void addToProspect(RemoteWebDriver driver) throws Exception {
		click(btnChoose, "Choose Button");
		Thread.sleep(100);
		click(linkAddToProspect, "Add To Prospect");
		if (driver.findElements(By.xpath("//div[@class='header']")).size() > 0) {
			assertTrue("Landed on Add Prospect Page");
		} else {
			assertTrue("Choose Button not clicked");
		}
		Actions action = new Actions(driver);
		action.clickAndHold(sliderOurInterest);
		action.moveToElement(pointOurInterest);
		action.release(pointOurInterest);
		action.build().perform();
		Thread.sleep(100);
		action.clickAndHold(sliderCandidateInterest);
		action.moveToElement(pointCandidateInterest);
		action.release(pointCandidateInterest);
		action.build().perform();
		Thread.sleep(100);
		click(btnAdd, "ADD");
		Thread.sleep(200);
		click(tabProspect, "Prospect tab");
		String numbers = countProspect.getText();
		int count = Integer.parseInt(numbers);
		if (count > 0) {
			assertTrue("Candidate is added as Prospect");
		} else {
			assertFalse("Candidate is not added as Prospect");
		}
	}
}
