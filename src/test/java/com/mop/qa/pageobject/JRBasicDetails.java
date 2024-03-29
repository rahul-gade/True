package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class JRBasicDetails extends PageBase {
	public JRBasicDetails(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//input[@id='company_name']")
	private WebElement inputCompanyName;
	@FindBy(xpath = "//input[@id='sector']")
	private WebElement inputSector;
	@FindBy(xpath = "//input[@id='position']")
	private WebElement inputPosition;
	String select = "//span[text()='PLACEHOLDER']";
	String select1 = "//span[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'PLACEHOLDER')]";
	@FindBy(xpath = "//input[@id='location']")
	private WebElement inputLocation;
	@FindBy(xpath = "//span[text()='CREATE JR']")
	private WebElement btnCreateJr;
	@FindBy(xpath = "//span[text()='CREATE']")
	private WebElement btnCreate;
	@FindBy(xpath = "//span[text()='PROCEED TO FORM']")
	private WebElement btnFillForm;
	@FindBy(xpath = "//span[text()='BACK TO REQUISITIONS']")
	private WebElement btnGoToListing;
	@FindBy(xpath = "(//span[text()='NEXT'])[1]")
	private WebElement btnNext;
	@FindBy(xpath = "//div[contains(text(),'You can')]")
	private WebElement txtReplicate;
	@FindBy(xpath = "//span[text()='IN PROGRESS']")
	private WebElement tabInProgress;

	public void enterBasicDetails(RemoteWebDriver driver, String companyName, String position, String location,
			String sector) throws Exception {
		Actions action = new Actions(remoteDriver);
		String company = companyName.toUpperCase();
		Thread.sleep(1000);
		if (driver.findElements(By.xpath("//input[@id='company_name']")).size() > 0) {
			click(inputCompanyName, "Company Name");
			for (int i = 0; i < companyName.length(); i++) {
				char c = companyName.charAt(i);
				String s = new StringBuilder().append(c).toString();
				inputCompanyName.sendKeys(s);
				Thread.sleep(5);
			}
//			Thread.sleep(1500);
			waitForVisibilityOfElement("//span[contains(text(),'" + company + "')]");
			if (driver.findElement(By.xpath("//span[contains(text(),'" + company + "')]")) != null) {
				driver.findElement(By.xpath("//span[contains(text(),'" + company + "')]")).click();
			} else
				assertFalse("Company ddown not found.");
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//input[@id='sector']")).size() > 0) {
			click(inputSector, "Sector");
			enterText(inputSector, sector, "Sector");
			Thread.sleep(1000);
			if (driver.findElements(By.xpath(select.replace("PLACEHOLDER", sector))).size() > 0) {
				WebElement sect = driver.findElement(By.xpath(select.replace("PLACEHOLDER", sector)));
				action.moveToElement(sect).click().build().perform();
			} else
				assertFalse("Sector Drop-down List Not Displayed.");
		}

		click(inputPosition, "Position");
		enterText(inputPosition, position, "Position");
		Thread.sleep(2000);
		if (driver.findElements(By.xpath(select.replace("PLACEHOLDER", position))).size() > 0) {
			WebElement pos = driver.findElement(By.xpath(select.replace("PLACEHOLDER", position)));
			action.moveToElement(pos).click().build().perform();
		} else
			assertFalse("Position Drop-down List Not Displayed.");

		click(inputLocation, "Location");
		enterText(inputLocation, location, "Location");
		Thread.sleep(2000);
		driver.findElement(By.xpath(select.replace("PLACEHOLDER", location))).click();

		if (driver.findElements(By.xpath("//mat-error")).size() > 0) {
			if (driver.findElement(By.xpath("(//mat-error)[1]")).getText().contains("Company")) {
				assertFalse("Company Name Field is missing");
			}
			if (driver.findElement(By.xpath("(//mat-error)[2]")).getText().contains("Position")) {
				assertFalse("Position Field is missing");
			}
			if (driver.findElement(By.xpath("(//mat-error)[3]")).getText().contains("Location")) {
				assertFalse("Location Field is missing");
			}
		}
		
		
		if (driver.findElements(By.xpath("//span[text()='CREATE JR']")).size()>0) {
			click(btnCreateJr, "Create JR");
			Thread.sleep(2000);
			/*
			 * do { Thread.sleep(1000); }
			 * while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
			 */
		} else if(driver.findElements(By.xpath("//span[text()='CREATE']")).size()>0) {
			click(btnCreate, "Create JR");
			Thread.sleep(2000);
		}
	}

	public void fillForm(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		click(btnFillForm, "Fill Form");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img[contains(@src,'spinner')]")).size() > 0);
		click(btnNext, "Next");
//		do {
//			Thread.sleep(1000);
//		} while (driver.findElements(By.xpath("(//div/div/img)[1]")).size() > 0);
		Thread.sleep(5000);
	}

	public void goToListing(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		click(btnGoToListing, "Go To Listing");
		Thread.sleep(1000);
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
	}

	public void verifyCreatedJR(RemoteWebDriver driver, String position, String company) throws Exception {
		click(tabInProgress, "In Progress Tab");
		Thread.sleep(3000);
		int count = driver.findElements(By.xpath("//div[@class='drafts-list ng-star-inserted']")).size();
		for (int i = 1; i <= count; i++) {
			String pos = driver
					.findElement(
							By.xpath("//div[@class='drafts-list ng-star-inserted'][" + i + "]//div[@class='position']"))
					.getText();
			String comp = driver
					.findElement(
							By.xpath("//div[@class='drafts-list ng-star-inserted'][" + i + "]//div[@class='data']"))
					.getText();
			if (pos.contains(position) && comp.contains(company)) {
				if (driver
						.findElements(By
								.xpath("//div[@class='drafts-list ng-star-inserted'][" + i + "]//div[@class='status']"))
						.size() > 0) {
					String status = driver
							.findElement(By.xpath(
									"//div[@class='drafts-list ng-star-inserted'][" + i + "]//div[@class='status']"))
							.getText();
					if (status.contains("Completed")) {
						assertTrue("Status of new JR is " + status);
						String[] completedCount = status.split("/");
						int complete = Integer.parseInt(completedCount[0]);
						int dot = driver.findElements(By.xpath("//div[@class='drafts-list ng-star-inserted'][" + i
								+ "]//div[@class='status']/parent::li//span[@class='blue-dot']")).size();
						if (complete == dot) {
							assertTrue("Blue dots and completed steps are equal i.e. " + dot);
							driver.findElement(By.xpath(
									"//div[contains(text(),'Drafts')]/following-sibling::div[@class='drafts-list ng-star-inserted']["
											+ i + "]//div[@class='position']"))
									.click();
							Thread.sleep(5000);
							click(btnNext, "Next");
							Thread.sleep(1000);
							break;
						} else {
							assertFalse("Bluedots and completed steps do not match");
						}
					} else {
						assertFalse("Completed step is not displayed");
					}
				}
			} else {
				assertFalse("Newly created JR is not displayed in drafts");
			}
		}
	}

}
