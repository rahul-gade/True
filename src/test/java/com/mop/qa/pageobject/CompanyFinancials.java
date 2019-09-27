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
	String tableCell = "//tbody/tr[ROW]/td[COLUMN]";

	// DOWNLOAD REPORT ELEMENTS:
	@FindBy(xpath = "//div[@class='action-list']//li[2]/a")
	private WebElement reportMenu;
	@FindBy(xpath = "//button[@title='Download CMIE Financial data']")
	private WebElement downloadBtn;
	// p[text()='Download CMIE Data']
	@FindBy(xpath = "//span[text()='CANCEL']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement closeBtn;
	@FindBy(xpath = "(//div[@class='dd-header clearfix']//a)[1]")
	private WebElement backBtn;
	
	String editCell = "//tbody[@class='ui-treetable-tbody']/tr[1]/td[COL]/p-treetablecelleditor"; //input

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

	public void tableData(RemoteWebDriver driver) throws Exception {
		if (driver.findElements(By.xpath("//tbody")).size() > 0) {
			int rows = driver.findElements(By.xpath("//tbody/tr")).size();
			if(rows>0)
				assertTrue("Prefilld Parameters are available for manual filling.");
//			int r = 1;
			int i, j;
			for (j = 1; j <= rows; j++) {
//				System.out.println(r);
				String row = tableCell.replace("ROW", String.valueOf(j));
				for (i = 2; i <= 6; i++) {
					if (!(driver.findElement(By.xpath(row.replace("COLUMN", String.valueOf(i)))).getText()
							.contains("-"))) {
						assertTrue("Prefilled Data found in the table.");
						break;
					}
				}
				if (i <= 6)
					break;
			}
			if (j > rows) {
				CompanyInformation BG = new CompanyInformation(driver);
				if (BG.compBG.isEmpty()) {
					assertTrue("Empty table displayed");
				} else
					assertFalse("Table not prepopulated with CMIE Data.");
			}

		}
	}

	public void enterCompanyDetails() throws Exception {
		click(btnNext, "Next 3");
	}

	public void testDownload(RemoteWebDriver driver) throws Exception {
		Thread.sleep(500);
		click(reportMenu, "Menu Button");
		Thread.sleep(500);
		if (driver.findElements(By.xpath("//button[@title='Download CMIE Financial data']")).size() > 0) {
			click(downloadBtn, "Download Financials");
			Thread.sleep(500);
			if (driver.findElements(By.xpath("//p[text()='Download CMIE Data']")).size() > 0) {
				assertTrue("Download Confirmation Pop-Up Displayed.");
				click(cancelBtn, "Cancel Download.");
				click(closeBtn, "Close");
				Thread.sleep(500);
				click(backBtn, "back to MyDeals.");
			} else
				assertFalse("Download Confirmation Pop-Up NOT Displayed.");
		} else
			assertTrue("Table is either empty or data is not available.");
	}

	public void fillTable(RemoteWebDriver driver) throws Exception {
		for(int col=2; col<=6; col++) {
			String currentCell = editCell.replace("COL", String.valueOf(col));
			click(driver.findElement(By.xpath(currentCell)), "Cell: "+(col-1));
			enterText(driver.findElement(By.xpath(currentCell+"/input")), String.valueOf(col+4), "Input: "+(col-1));
			Thread.sleep(500);
		}
	}
}
