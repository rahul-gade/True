package com.mop.qa.SchedulerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class SchedulerLocation extends PageBase {
	public SchedulerLocation(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//div[contains(text(),'Location')]")
	WebElement subHeaderLocation;
	@FindBy(xpath = "//input[contains(@placeholder,'Location')]")
	WebElement inputLocation;
	@FindBy(xpath = "//img[contains(@src,'spinner')]")
	WebElement spinner;
	@FindBy(xpath = "//span[contains(text(),'PLACEHOLDER')]")
	WebElement selectLocation;
	@FindBy(xpath = "//mat-option[1]/span")
	WebElement location_1_text;
	@FindBy(xpath = "//mat-option[1]")
	WebElement location_1;

	@FindBy(xpath = "//span[contains(text(),'In Person')]")
	WebElement chkboxInPerson;
	@FindBy(xpath = "//span[contains(text(),'Dial In')]")
	WebElement chkboxDialIn;
	@FindBy(xpath = "//span[contains(text(),'Video Conference')]")
	WebElement chkboxVideoConf;
	@FindBy(xpath = "//mat-select[contains(@placeholder,'Bridge Number')]")
	WebElement drpdwnBridgeNumber;
//	@FindBy(xpath = "//span[contains(text(),'PLACEHOLDER')]")
//	WebElement bridgeNumber;
	String bridge = "//span[contains(text(),'PLACEHOLDER')]";

	@FindBy(xpath = "//div[contains(@class,'previousButton')]")
	WebElement btnPrevious;
	@FindBy(xpath = "//div[contains(text(),'NEXT')]")
	WebElement btnNext;
	@FindBy(xpath = "//*[@class='closeIcon']")
	WebElement btn_Close;

	public String selectedLocation = "";

	public void enterLocation(RemoteWebDriver driver, String location, String bridgeNumber) throws Exception {
		click(inputLocation, "Location Input");
		enterText(inputLocation, location, "Location Text");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//img[contains(@src,'spinner')]")).size() > 0);
		selectedLocation = location_1_text.getText().trim();
		click(location_1, "Location Suggestion 1");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//img[contains(@src,'spinner')]")).size() > 0);

		click(chkboxInPerson, "In Person");
		Thread.sleep(100);
		click(chkboxDialIn, "Dial In");
		Thread.sleep(100);
		click(chkboxVideoConf, "Video Conference");
		Thread.sleep(100);

		click(drpdwnBridgeNumber, "Bridge Number DropDown");
		Thread.sleep(100);
		click(driver.findElement(By.xpath(bridge.replace("PLACEHOLDER", bridgeNumber))), "Bridge Number Option");
		Thread.sleep(100);

		click(btnNext, "NEXT Button");
		Thread.sleep(1000);
	}
}
