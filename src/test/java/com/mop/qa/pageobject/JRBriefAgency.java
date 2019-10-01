package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class JRBriefAgency extends PageBase {
	public JRBriefAgency(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//a[text()=' Brief Agency ']")
	private WebElement linkBriefAgency;
	@FindBy(xpath = "(//a[text()='VIEW BRIEF'])[1]")
	private WebElement viewBrief;
	@FindBy(xpath = "(//a[@class='close-btn'])[2]")
	private WebElement btnClose;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnBriefClose;
	@FindBy(xpath = "(//a[text()='BRIEF'])[1]")
	private WebElement linkBrief;
	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement inputTo;
	String selectto = "//div[text()=' PLACEHOLDER']";
	@FindBy(xpath = "//*[@placeholder='Brief']")
	private WebElement inputBrief;
	@FindBy(xpath = "//label[text()='Shareables']/parent::div//mat-checkbox[1]")
	private WebElement chkboxShareables;
	@FindBy(xpath = "//label[text()='Deliverables']/parent::div//mat-radio-button[1]")
	private WebElement chkboxLongList;
	@FindBy(xpath = "//span[text()='Submit']")
	private WebElement btnSubmit;

	public void addBrief(RemoteWebDriver driver, String to, String brief) throws Exception {
		Thread.sleep(1000);
		waitForPageLoad();
		click(linkBriefAgency, "Brief Agency");
		Thread.sleep(1000);
		click(linkBrief, "Brief");
		if (driver.findElements(By.xpath("//div[@class='header']")).size() > 0) {
			assertTrue("Landed on Brief Agency page");
			click(inputTo, "To");
			enterText(inputTo, to, "To");
			Thread.sleep(1000);
			driver.findElement(By.xpath(selectto.replace("PLACEHOLDER", to))).click();
//			driver.findElement(By.xpath("//mat-option[1]")).click();
			Thread.sleep(100);
			if (driver.findElements(By.xpath("//*[@placeholder='Brief']")).size() > 0) {
				click(inputBrief, "Brief");
				enterText(inputBrief, brief, "Brief");
				Thread.sleep(100);
			}
			click(chkboxShareables, "Agency CheckBox");
			click(chkboxLongList, "Long List CheckBox");
			click(btnSubmit, "Submit");
		}
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		click(btnBriefClose, "Close Button");
	}

	public void viewBrief(RemoteWebDriver driver) throws Exception {
		Thread.sleep(1000);
		click(linkBriefAgency, "Brief Agency");
		Thread.sleep(1000);

		click(viewBrief, "View Brief");
		if (driver.findElements(By.xpath("//div[@class='header']")).size() > 0) {
			assertTrue("Landed on Brief Agency page");
			click(btnClose, "Back Button");
			Thread.sleep(1000);
		}
		click(btnBriefClose, "Close Button");
	}
}
