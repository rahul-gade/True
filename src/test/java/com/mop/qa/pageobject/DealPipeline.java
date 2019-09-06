package com.mop.qa.pageobject;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.testbase.PageBase;
import com.mop.qa.testbase.TestBase;

public class DealPipeline extends PageBase {
	public DealPipeline(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	public ReadDataSheet rds = new ReadDataSheet();
	TestBase tBase = new TestBase();
	public String currentTest = tBase.currentTest;

//	==========WebElements==========
	@FindBy(xpath = "//a[text()='Deal Pipeline']")
	private WebElement dealPipelineTab;
	@FindBy(xpath = "//span[text()='STAGE A']")
	private WebElement stageA;
	@FindBy(xpath = "//span[text()='STAGE B']")
	private WebElement stageB;
	@FindBy(xpath = "//span[text()='STAGE C']")
	private WebElement stageC;
	@FindBy(xpath = "//span[text()='STAGE D']")
	private WebElement stageD;
	@FindBy(xpath = "//span[text()='IDEAS']")
	private WebElement ideas;
	@FindBy(xpath = "//a[text()=' SUMMARY ']")
	private WebElement summary;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement onePagerClose;
	@FindBy(xpath = "(//div[@class='summary-content']/ul)[1]/li/a/span[@class='document-icon']")
	private WebElement onePagerDocIcon;
	@FindBy(xpath = "//div[text()='Pipeline summary']/following-sibling::ul//span")
	private WebElement dateRange;

	private String stage = "//span[text()='STAGENAME']";
	// ====(//div[@class='summary-content']/ul)[1]/li/a/span[@class='document-icon']
	// ====(//div[@class='summary-content']/ul)[1]/li

	public void dealPipeline(RemoteWebDriver driver) throws Exception {
		click(dealPipelineTab, "Deal Pipeline Tab");
		Thread.sleep(1000);
		click(summary, "Summary View");
		Thread.sleep(200);
	}

	public void stageTest(RemoteWebDriver driver) throws Exception {
		String[] stageList = { "STAGE A", "STAGE B", "STAGE C", "STAGE D", "IDEAS" };
		for (int i = 1; i <= 5; i++) {
			if (driver.findElement(By.xpath(stage.replace("STAGENAME", stageList[i - 1]))) != null) {
				click(driver.findElement(By.xpath(stage.replace("STAGENAME", stageList[i - 1]))), stageList[i - 1]);
				Thread.sleep(500);
			}
		}
	}

	public void summaryView(RemoteWebDriver driver) throws Exception {
		click(summary, "Summary Button");
		Thread.sleep(1000);
		if (driver.findElements(By.xpath("//div[@class='title' and text()='Pipeline summary']")).size() > 0) {
			assertTrue("Landed on pipeline summary view page");
			if (driver.findElement(By.xpath("//li[text()=' pagers added ']")) != null
					&& driver.findElement(By.xpath("//li[text()=' progressed ']")) != null
					&& driver.findElement(By.xpath("//li[text()=' Passed ']")) != null) {
				assertTrue("Data is shown in 3 different categories");
			} else
				assertFalse("Categories are not dislayed.");
		} else
			assertFalse("pipeline summary view page is not displayed.");
	}

	public void newOnePagers(RemoteWebDriver driver) throws Exception {
		if (driver.findElement(By.xpath("//li[text()=' pagers added ']/span")) != null) {
			int OnePagers = Integer
					.parseInt(driver.findElement(By.xpath("//li[text()=' pagers added ']/span")).getText());
			if (driver.findElements(By.xpath("(//div[@class='summary-content']/ul)[1]/li")).size() > 0) {
				if (driver
						.findElements(
								By.xpath("(//div[@class='summary-content']/ul)[1]/li/a/span[@class='document-icon']"))
						.size() == OnePagers) {
					assertTrue("Quick Links to One Pagers displayed for each listing in NEW ONE PAGERS item.");
					click(onePagerDocIcon, "One Pager Link 1");
					Thread.sleep(2000);
					if (driver.findElement(By.xpath("//div[@class='one-pager-view-header']")) != null) {
						assertTrue("Landed on One Pager View");
						click(onePagerClose, "One Pager Close Button");
						Thread.sleep(1000);
						click(summary, "Summary View Button");
					} else
						assertFalse("One Pager View is not displayed.");
				} else
					assertFalse("Quick Links are not displayed.");
			}
		}
	}

	public void dateRange(RemoteWebDriver driver) throws Exception {
		StringBuffer dates = new StringBuffer(dateRange.getText().trim());
		String year = dates.substring(dates.indexOf(",") + 1);
		dates.delete(dates.indexOf(","), dates.length());
		StringBuffer date1 = new StringBuffer(dates.substring(0, 6)).append(year);
		StringBuffer date2 = new StringBuffer(dates.substring(9)).append(year);
		DateTimeFormatter form = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate d1 = LocalDate.parse(date1, form);
		LocalDate d2 = LocalDate.parse(date2, form);
		int days = Math.abs(Period.between(d2, d1).getDays());
		if (days == 7)
			assertTrue("Period of Data shown is 7 DAYS.");
		else
			assertFalse("Perios of Data shown is not 7 DAYS.");
	}

	public void dealsProgressed(RemoteWebDriver driver) throws Exception {
		if (driver.findElement(By.xpath("//div[@class='summary-content']/ul[2]")) != null) {
			assertTrue("List of Deal Progressed is displayed.");
			if (driver.findElement(By.xpath("//li[text()=' progressed ']/span")).getText() != null) {
				int count = Integer
						.parseInt(driver.findElement(By.xpath("//li[text()=' progressed ']/span")).getText());
				assertTrue("Count is displayed in Header as: "+count);
				if (driver.findElements(By.xpath("//div[@class='summary-content']/ul[2]/li")).size() == count) {
					assertTrue("List contains " + count + " deals.");
					if (driver.findElement(
							By.xpath("//div[@class='summary-content']/ul[2]/li/span[@class='stage-content']")) != null)
						assertTrue("Stage Change is shown.");
					else
						assertFalse("Stage Change is not shown.");
				} else
					assertFalse(
							"Deals Progressed list contains different number of elements from that shown in header.");
			} else
				assertFalse("Count is not displayed in Header.");
		} else
			assertFalse("List of Deal Progressed is not displayed.");
	}
}
