package com.mop.qa.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.mop.qa.testbase.PageBase;

public class IndustryInformation extends PageBase {
	public IndustryInformation(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "//*[@formcontrolname='industrySize']/parent::div//span/label")
	private WebElement lblIndustrySize;
	@FindBy(xpath = "//*[@formcontrolname='targetMarketSizePercent']/parent::div//span/label")
	private WebElement lblTgtCompMktShare;
	@FindBy(xpath = "//*[@formcontrolname='last3YearGrowthPercent']/parent::div//span/label")
	private WebElement lblGrowthLast3;
	@FindBy(xpath = "//*[@formcontrolname='last5YearGrowthPercent']/parent::div//span/label")
	private WebElement lblGrowthLast5;
	@FindBy(xpath = "//*[@formcontrolname='predicted3YearGrowthPercent']/parent::div//span/label")
	private WebElement lblPredicted3;
	@FindBy(xpath = "//*[@formcontrolname='predicted5YearGrowthPercent']/parent::div//span/label")
	private WebElement lblPredicted5;
	@FindBy(xpath = "(//*[@formcontrolname='stakeholdername']/parent::div//span/label)[2]")
	private WebElement lblCompetitorName;
	@FindBy(xpath = "(//*[@formcontrolname='sharePercentage']/parent::div//span/label)[2]")
	private WebElement lblSharePercentage;
	@FindBy(xpath = "//div[@class='popup-title']")
	private WebElement pageTitle;
	@FindBy(xpath = "//input[@formcontrolname='industrySize']")
	private WebElement txtIndutrySize;
	@FindBy(xpath = "//input[@formcontrolname='targetMarketSizePercent']")
	private WebElement txtTargetCompMarketShare;
	@FindBy(xpath = "//input[@formcontrolname='last3YearGrowthPercent']")
	private WebElement txtLast3Year;
	@FindBy(xpath = "//input[@formcontrolname='last5YearGrowthPercent']")
	private WebElement txtLast5Year;
	@FindBy(xpath = "//input[@formcontrolname='predicted3YearGrowthPercent']")
	private WebElement txtPredicted3Year;
	@FindBy(xpath = "//input[@formcontrolname='predicted5YearGrowthPercent']")
	private WebElement txtPredicted5Year;
	@FindBy(xpath = "(//input[@formcontrolname='stakeholdername'])[2]")
	private WebElement txtCompetitorName;
	@FindBy(xpath = "(//input[@formcontrolname='sharePercentage'])[2]")
	private WebElement txtCompetitorPercentage;
	@FindBy(xpath = "(//input[@formcontrolname='stakeholdername'])[3]")
	private WebElement txtCompetitorName2;
	@FindBy(xpath = "(//input[@formcontrolname='sharePercentage'])[3]")
	private WebElement txtCompetitorPercentage2;
	@FindBy(xpath = "//span[text()='SUBMIT']/parent::button")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='DEAL PAGE']")
	private WebElement btnDealPage;
	@FindBy(xpath = "//span[text()='BACK TO PIPELINE']")
	private WebElement btnBackToPipeline;
	String verifySuccess = "//span[text()='PLACEHOLDER']";
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;
	@FindBy(xpath = "//a[text()='Deal Details']")
	private WebElement tabDealDetails;
	@FindBy(xpath = "//button[@class='step-btn prev mat-button']/span")
	private WebElement btnNavigateBack;
	@FindBy(xpath = "//button[@class='step-btn next mat-button']/span")
	private WebElement btnNavigateForward;
	@FindBy(xpath = "//div[contains(text(),'Financials')]")
	private WebElement headerFinancial;
	int i = 1;

	public void enterIndustryDetails(RemoteWebDriver driver, String projectName, String indSize, String trgtMktShare,
			String last3year, String last5year, String pred3year, String pred5year, String competitorName,
			String percent, String flow, String mandatory) throws Exception {
		if (i == 2) {
			BasicDealDetails basic = new BasicDealDetails(remoteDriver);
			mandatory = "Yes";
			basic.checkMandatoryField(lblIndustrySize, "Industry Size");
			basic.checkMandatoryField(lblTgtCompMktShare, "Target Company Market Share");
			basic.checkMandatoryField(lblGrowthLast3, "Growth In Last 3 Years");
			basic.checkMandatoryField(lblGrowthLast5, "Growth In Last 5 Years");
			basic.checkMandatoryField(lblPredicted3, "Predicted 3 Year Growth");
			basic.checkMandatoryField(lblPredicted5, "Predicted 5 Year Growth");
			basic.checkMandatoryField(lblCompetitorName, "Competitor Name");
			basic.checkMandatoryField(lblSharePercentage, "Share Percentage");
		}
		if (mandatory.contains("No")) {
			click(btnSubmit, "Submit");
			i++;
			mandatory = "Yes";
		} else {
			click(txtIndutrySize, "Industry Size");
			enterText(txtIndutrySize, indSize, "Industry Size");
			String industrySize = driver.findElement(By.xpath("//input[@formcontrolname='industrySize']"))
					.getAttribute("value");
			if (industrySize.length() > 16) {
				assertFalse("Industry Size field accepts more than 16 characters");
			} else {
				assertTrue("Industry Size field do not accept more than 16 characters");
			}
			Thread.sleep(100);
			if (driver.findElements(By.xpath("//input[@formcontrolname='targetMarketSizePercent']")).size() > 0) {
				click(txtTargetCompMarketShare, "Target Company Market Share");
				enterText(txtTargetCompMarketShare, trgtMktShare, "Target Company Market Share");
				String mrktSize = driver.findElement(By.xpath("//input[@formcontrolname='targetMarketSizePercent']"))
						.getAttribute("value");
				if (mrktSize.length() > 2) {
					assertFalse("Target Company Market Share field accepts more than 2 characters");
				} else {
					assertTrue("Target Company Market Share field do not accept more than 2 characters");
				}
			}
			Thread.sleep(100);
			click(txtLast3Year, "Last 3 Year Growth");
			enterText(txtLast3Year, last3year, "Last 3 Year Growth");
			String last3Size = driver.findElement(By.xpath("//input[@formcontrolname='last3YearGrowthPercent']"))
					.getAttribute("value");
			if (last3Size.length() > 2) {
				assertFalse("Last 3 Year Growth field accepts more than 2 characters");
			} else {
				assertTrue("Last 3 Year Growth field do not accept more than 2 characters");
			}
			Thread.sleep(100);
			click(txtLast5Year, "Last 5 Year Growth");
			enterText(txtLast5Year, last5year, "Last 5 Year Growth");
			String last5Size = driver.findElement(By.xpath("//input[@formcontrolname='last5YearGrowthPercent']"))
					.getAttribute("value");
			if (last5Size.length() > 2) {
				assertFalse("Last 5 Year Growth field accepts more than 2 characters");
			} else {
				assertTrue("Last 5 Year Growth field do not accept more than 2 characters");
			}
			Thread.sleep(100);
			click(txtPredicted3Year, "Predicted 3 Year Growth");
			enterText(txtPredicted3Year, pred3year, "Predicted 3 Year Growth");
			String pred3Size = driver.findElement(By.xpath("//input[@formcontrolname='predicted3YearGrowthPercent']"))
					.getAttribute("value");
			if (pred3Size.length() > 2) {
				assertFalse("Predicted 3 Year Growth field accepts more than 2 characters");
			} else {
				assertTrue("Predicted 3 Year Growth field do not accept more than 2 characters");
			}
			Thread.sleep(100);
			click(txtPredicted5Year, "Predicted 5 Year Growth");
			enterText(txtPredicted5Year, pred5year, "Predicted 5 Year Growth");
			String pred5Size = driver.findElement(By.xpath("//input[@formcontrolname='predicted5YearGrowthPercent']"))
					.getAttribute("value");
			if (pred5Size.length() > 2) {
				assertFalse("Predicted 5 Year Growth field accepts more than 2 characters");
			} else {
				assertTrue("Predicted 5 Year Growth field do not accept more than 2 characters");
			}
			Thread.sleep(100);
			click(txtCompetitorName, "Competitor Name");
			enterText(txtCompetitorName, competitorName, "Competitor Name");
			String compSize = driver.findElement(By.xpath("(//input[@formcontrolname='stakeholdername'])[2]"))
					.getAttribute("value");
			if (compSize.length() > 32) {
				assertFalse("Competitor name field accepts more than 32 characters");
			} else {
				assertTrue("Competitor name field do not accept more than 32 characters");
			}
			Thread.sleep(100);
			click(txtCompetitorPercentage, "Share Percentage");
			enterText(txtCompetitorPercentage, percent, "Share Percentage");
			do {
				Thread.sleep(1000);
			} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
			click(btnSubmit, "Submit");
		}
		/*
		 * if(driver.findElements(By.xpath("//span[text()='SUBMIT']")).size()>0) {
		 * Actions action = new Actions(remoteDriver);
		 * action.moveToElement(btnSubmit).click().build().perform(); }
		 */
		/*
		 * do { Thread.sleep(1000); }
		 * while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		 */
		if (driver.findElements(By.xpath("//span[text()='BACK TO FORM']")).size() > 0) {
			driver.findElement(By.xpath("//span[text()='BACK TO FORM']")).click();
			assertTrue("Mandatory fields are Missing and Back to form buton is clicked");
//			enterText(driver.findElement(By.xpath("(//textarea)[1]")), "Sample Overview", "Company Overview");
//			click(driver.findElement(By.xpath("//button[contains(@class,'next')]")), "Next Arrow");
//			Thread.sleep(500);
//			click(driver.findElement(By.xpath("//button[contains(@class,'next')]")), "Next Arrow");
//			Thread.sleep(500);
//			click(btnSubmit, "Submit");
//			if (driver.findElements(By.xpath("//span[text()='DEAL PAGE']")).size() > 0
//					&& driver.findElements(By.xpath("//span[text()='BACK TO PIPELINE']")).size() > 0) {
//				assertTrue("Success Pop up is displayed succesfully");
//			}
			
		} else {
			if (driver.findElements(By.xpath("//span[text()='DEAL PAGE']")).size() > 0
					&& driver.findElements(By.xpath("//span[text()='BACK TO PIPELINE']")).size() > 0) {
				assertTrue("Success Pop up is displayed succesfully");
			}
			if (flow.contains("Back")) {
				click(btnBackToPipeline, "Back to Pipeline");
				Thread.sleep(1000);
				do {
					Thread.sleep(1000);
				} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
				if (driver.findElements(By.xpath("//a[text()='My Deals']")).size() > 0) {
					assertTrue("Deal is Successfully created");
				}
			} else {
				click(btnDealPage, "Deal Page");
				Thread.sleep(1000);
				do {
					Thread.sleep(1000);
				} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
				if (driver.findElement(By.xpath(verifySuccess.replace("PLACEHOLDER", projectName))).isDisplayed()) {
					assertTrue("Deal is Successfully created");
				} else {
					assertFalse("Deal is not created");
				}
			}
		}
	}

	public void verifyIndustryDetailsPage(RemoteWebDriver driver) throws Exception {
		String industrySize = driver.findElement(By.xpath("//input[@formcontrolname='industrySize']"))
				.getAttribute("value");
		if (industrySize.length() > 16) {
			assertFalse("Industry Size field accepts more than 16 characters");
		} else {
			assertTrue("Industry Size field do not accept more than 16 characters");
		}
		Thread.sleep(100);
		String mrktSize = driver.findElement(By.xpath("//input[@formcontrolname='targetMarketSizePercent']"))
				.getAttribute("value");
		if (mrktSize.length() > 2) {
			assertFalse("Target Company Market Share field accepts more than 2 characters");
		} else {
			assertTrue("Target Company Market Share field do not accept more than 2 characters");
		}
		String mrktSizePercent = driver
				.findElement(By.xpath(
						"//input[@formcontrolname='targetMarketSizePercent']/parent::div/following-sibling::div/span"))
				.getText();
		if (mrktSizePercent.contains("%")) {
			assertTrue("Target Company Market Share is displayed as %");
		} else {
			assertFalse("Target Company Market Share is not displayed as %");
		}
		Thread.sleep(100);
		String last3Size = driver.findElement(By.xpath("//input[@formcontrolname='last3YearGrowthPercent']"))
				.getAttribute("value");
		if (last3Size.length() > 2) {
			assertFalse("Last 3 Years Growth field accepts more than 2 characters");
		} else {
			assertTrue("Last 3 Years Growth field do not accept more than 2 characters");
		}
		String last3Percent = driver
				.findElement(By.xpath(
						"//input[@formcontrolname='last3YearGrowthPercent']/parent::div/following-sibling::div/span"))
				.getText();
		if (last3Percent.contains("%")) {
			assertTrue("Last 3 Years Growth is displayed as %");
		} else {
			assertFalse("Last 3 Years Growth is not displayed as %");
		}
		Thread.sleep(100);
		String last5Size = driver.findElement(By.xpath("//input[@formcontrolname='last5YearGrowthPercent']"))
				.getAttribute("value");
		if (last5Size.length() > 2) {
			assertFalse("Last 5 Years Growth field accepts more than 2 characters");
		} else {
			assertTrue("Last 5 Years Growth field do not accept more than 2 characters");
		}
		String last5Percent = driver
				.findElement(By.xpath(
						"//input[@formcontrolname='last5YearGrowthPercent']/parent::div/following-sibling::div/span"))
				.getText();
		if (last5Percent.contains("%")) {
			assertTrue("Last 5 Years Growth is displayed as %");
		} else {
			assertFalse("Last 5 Years Growth is not displayed as %");
		}
		Thread.sleep(100);
		String pred3Size = driver.findElement(By.xpath("//input[@formcontrolname='predicted3YearGrowthPercent']"))
				.getAttribute("value");
		if (pred3Size.length() > 2) {
			assertFalse("Predicted 3 Years Growth field accepts more than 2 characters");
		} else {
			assertTrue("Predicted 3 Years Growth field do not accept more than 2 characters");
		}
		String predicted3Percent = driver.findElement(By.xpath(
				"//input[@formcontrolname='predicted3YearGrowthPercent']/parent::div/following-sibling::div/span"))
				.getText();
		if (predicted3Percent.contains("%")) {
			assertTrue("Predicted 3 Years Growth is displayed as %");
		} else {
			assertFalse("Predicted 3 Years Growth is not displayed as %");
		}
		Thread.sleep(100);
		String pred5Size = driver.findElement(By.xpath("//input[@formcontrolname='predicted5YearGrowthPercent']"))
				.getAttribute("value");
		if (pred5Size.length() > 2) {
			assertFalse("Predicted 5 Year Growth field accepts more than 2 characters");
		} else {
			assertTrue("Predicted 5 Year Growth field do not accept more than 2 characters");
		}
		String predicted5Percent = driver.findElement(By.xpath(
				"//input[@formcontrolname='predicted5YearGrowthPercent']/parent::div/following-sibling::div/span"))
				.getText();
		if (predicted5Percent.contains("%")) {
			assertTrue("Predicted 5 Years Growth is displayed as %");
		} else {
			assertFalse("Predicted 5 Years Growth is not displayed as %");
		}
		Thread.sleep(100);
		String compSize = driver.findElement(By.xpath("(//input[@formcontrolname='stakeholdername'])[2]"))
				.getAttribute("value");
		if (compSize.length() > 32) {
			assertFalse("Competitor name field accepts more than 32 characters");
		} else {
			assertTrue("Competitor name field do not accept more than 32 characters");
		}
		Thread.sleep(100);
		String shareSize = driver.findElement(By.xpath("(//input[@formcontrolname='sharePercentage'])[2]"))
				.getAttribute("value");
		if (shareSize.length() > 2) {
			assertFalse("Share Percentage field accepts more than 2 characters");
		} else {
			assertTrue("Share Percentage field do not accept more than 2 characters");
		}
		String sharePercent = driver
				.findElement(By.xpath(
						"(//input[@formcontrolname='sharePercentage'])[2]/parent::div/following-sibling::div/span"))
				.getText();
		if (sharePercent.contains("%")) {
			assertTrue("Share Percentage is displayed as %");
		} else {
			assertFalse("Share Percentage is not displayed as %");
		}
		Thread.sleep(100);
		int add = driver.findElements(By.xpath("//*[@name='Competitor Name']//a[text()='ADD ANOTHER']")).size();
		int i = 1;
		if (add > 0) {
			assertTrue("Add Another link is displayed");
			do {
				driver.findElement(By.xpath("//*[@name='Competitor Name']//a[text()='ADD ANOTHER']")).click();
				i = i + 1;
				Thread.sleep(100);
			} while (driver.findElements(By.xpath("//*[@name='Competitor Name']//a[@class='add-link link-disabled']"))
					.size() != 1);
			assertTrue("User is able to add " + i + " Competitors");
		} else {
			assertFalse("Add Another link is not displayed");
		}
	}

	public void editIndustryDetails(RemoteWebDriver driver, String projectName, String editprojectName, String indSize,
			String trgtMktShare, String last3year, String last5year, String pred3year, String pred5year,
			String competitorName, String percent, String section, String navigate) throws Exception {
		if (!indSize.equals("NA")) {
			click(txtIndutrySize, "Industry Size");
			driver.findElement(By.xpath("//input[@formcontrolname='industrySize']")).clear();
			enterText(txtIndutrySize, indSize, "Industry Size");
			Thread.sleep(2000);
		}
		if (!trgtMktShare.equals("NA")) {
			click(txtTargetCompMarketShare, "Target Company Market Share");
			driver.findElement(By.xpath("//input[@formcontrolname='targetMarketSizePercent']")).clear();
			enterText(txtTargetCompMarketShare, trgtMktShare, "Target Company Market Share");
			Thread.sleep(2000);
		}
		if (!last3year.equals("NA")) {
			click(txtLast3Year, "Last 3 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='last3YearGrowthPercent']")).clear();
			enterText(txtLast3Year, last3year, "Last 3 Year Growth");
			Thread.sleep(2000);
		}
		if (!last5year.equals("NA")) {
			click(txtLast5Year, "Last 5 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='last5YearGrowthPercent']")).clear();
			enterText(txtLast5Year, last5year, "Last 5 Year Growth");
			Thread.sleep(2000);
		}
		if (!pred3year.equals("NA")) {
			click(txtPredicted3Year, "Predicted 3 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='predicted3YearGrowthPercent']")).clear();
			enterText(txtPredicted3Year, pred3year, "Predicted 3 Year Growth");
			Thread.sleep(2000);
		}
		if (!pred5year.equals("NA")) {
			click(txtPredicted5Year, "Predicted 5 Year Growth");
			driver.findElement(By.xpath("//input[@formcontrolname='predicted5YearGrowthPercent']")).clear();
			enterText(txtPredicted5Year, pred5year, "Predicted 5 Year Growth");
			Thread.sleep(2000);
		}
		if (!competitorName.equals("NA")) {
			click(txtCompetitorName, "Competitor Name");
			driver.findElement(By.xpath("(//input[@formcontrolname='stakeholdername'])[2]")).clear();
			enterText(txtCompetitorName, competitorName, "Competitor Name");
			Thread.sleep(2000);
		}
		if (!percent.equals("NA")) {
			click(txtCompetitorPercentage, "Share Percentage");
			driver.findElement(By.xpath("(//input[@formcontrolname='sharePercentage'])[2]")).clear();
			enterText(txtCompetitorPercentage, percent, "Share Percentage");
			Thread.sleep(2000);
		}
		if (navigate.contains("Yes")) {
			navigateBack();
			Thread.sleep(1000);
			navigateForward();
			Thread.sleep(1000);
			if (!indSize.equals("NA")) {
				String size = driver.findElement(By.xpath("//input[@formcontrolname='industrySize']"))
						.getAttribute("value");
				if (indSize.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
			if (!trgtMktShare.equals("NA")) {
				String size = driver.findElement(By.xpath("//input[@formcontrolname='targetMarketSizePercent']"))
						.getAttribute("value");
				if (trgtMktShare.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
			if (!last3year.equals("NA")) {
				String size = driver.findElement(By.xpath("//input[@formcontrolname='last3YearGrowthPercent']"))
						.getAttribute("value");
				if (last3year.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
			if (!last5year.equals("NA")) {
				String size = driver.findElement(By.xpath("//input[@formcontrolname='last5YearGrowthPercent']"))
						.getAttribute("value");
				if (last5year.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
			if (!pred3year.equals("NA")) {
				String size = driver.findElement(By.xpath("//input[@formcontrolname='predicted3YearGrowthPercent']"))
						.getAttribute("value");
				if (pred3year.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
			if (!pred5year.equals("NA")) {
				String size = driver.findElement(By.xpath("//input[@formcontrolname='predicted5YearGrowthPercent']"))
						.getAttribute("value");
				if (pred5year.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
			if (!competitorName.equals("NA")) {
				String size = driver.findElement(By.xpath("(//input[@formcontrolname='stakeholdername'])[2]"))
						.getAttribute("value");
				if (competitorName.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
			if (!percent.equals("NA")) {
				String size = driver.findElement(By.xpath("(//input[@formcontrolname='sharePercentage'])[2]"))
						.getAttribute("value");
				if (percent.contains(size)) {
					assertTrue("Updated details are displayed as expected");
				} else {
					assertFalse("Updated details are not displayed as expected");
				}
			}
		}
		click(btnSubmit, "Submit");
		Thread.sleep(2000);
		click(btnDealPage, "Deal Page");
		Thread.sleep(2000);
		String proj;
		if (editprojectName.equals("NA")) {
			proj = projectName;
		} else {
			proj = editprojectName;
		}
		if (driver.findElement(By.xpath(verifySuccess.replace("PLACEHOLDER", proj))).isDisplayed()) {
			assertTrue("Deal is Successfully edited");
		} else {
			assertFalse("Deal is not edited");
		}
	}

	public void navigateBack() throws Exception {
		click(btnNavigateBack, "Back Button");
		Thread.sleep(2000);
		if (headerFinancial.isDisplayed()) {
			assertTrue("Back Button is clicked and user is navigated to Previous Page.");
		} else {
			assertFalse("Back Button is not clicked.");
		}
	}

	public void navigateForward() throws Exception {
		click(btnNavigateForward, "Forward Button");
		Thread.sleep(2000);
		if (txtIndutrySize.isDisplayed()) {
			assertTrue("Forward Button is clicked and user is navigated to Next Page.");
		} else {
			assertFalse("Forward Button is not clicked.");
		}
	}
}
