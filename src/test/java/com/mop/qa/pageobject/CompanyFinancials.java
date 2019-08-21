package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import com.mop.qa.testbase.PageBase;

public class CompanyFinancials extends PageBase {
	public CompanyFinancials(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	// private static final Logger LOGGER =
	// Logger.getLogger(CnngoPageWebMobile.class.getName());

	@FindBy(xpath = "//button[@class='step-btn prev mat-button']")
	private WebElement arrowPrev;
	@FindBy(xpath = "//button[@class='step-btn next mat-button']")
	private WebElement arrowNext;
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "(//span[text()='NEXT'])[3]")
	private WebElement btnNext;

	public void checkDealTitle(RemoteWebDriver driver, String name) throws Exception {
		if (driver.findElement(By.xpath("//h4")).getText().length() > 0) {
			assertTrue("Project Name is Displayed");
			String projectName = driver.findElement(By.xpath("//h4")).getText();
			if (projectName.equals(name))
				assertTrue("Project Name Displayed is same as entered name - " + name);
			else
				assertFalse("Project Name Displayed is Different from Entered name.");
		}
	}

	public void checkLastSaveTime(RemoteWebDriver driver) throws Exception {
		if (driver.findElement(By.xpath("//li[contains(text(),'Saved at')]")) != null) {
			assertTrue("Last Saved Time is displayed");
		} else {
			assertFalse("Last Saved Time is displayed");
		}
	}

	public void checkPageTitle(RemoteWebDriver driver) throws Exception {
		if (verifyText(pageTitle, "Company Financials")) {
			assertTrue("Page Title is as expected");
		}
		if (driver.findElements(By.xpath("//span[@class='icon ng-star-inserted active']")).size() > 0) {
			for (int i = 1; i <= 4; i++) {
				if (driver.findElement(By.xpath("//div[@id='stepProgress']/span[" + i + "]")).getAttribute("class")
						.contains("active")) {
					assertTrue("Progress bar shows at" + i + " out of 4th location");
					break;
				}
			}
		}
	}

	public void checkNavButtons(RemoteWebDriver driver) throws Exception {
		click(arrowNext, "Next Arrow");
		Thread.sleep(1000);
		if (driver.findElements(By.xpath("//div[@class='popup-title' and text()=' Industry Information ']")).size() > 0)
			assertTrue("Next arrow is clicked and 'Industry Information' Page is displayed.");
		else
			assertFalse("Next arrow is clicked but 'Industry Information' Page is not displayed.");
		click(arrowPrev, "Previous Arrow");
		Thread.sleep(1000);
		if (driver.findElements(By.xpath("//div[@class='popup-title' and text()=' Company Financials ']")).size() > 0)
			assertTrue("Previous arrow is clicked and 'Company Financials' Page is displayed.");
		else
			assertFalse("Previous arrow is clicked but 'Company Financials' Page is not displayed.");
	}

	public void enterCompanyDetails() throws Exception {
		click(btnNext, "Next");
	}
}
