package com.mop.qa.businessMGMT;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

import net.bytebuddy.asm.Advice.Unused;

public class BM_AddActionPlan extends PageBase {

	public BM_AddActionPlan(RemoteWebDriver driver) {
		super(driver);
	}

//	datepicker
	@FindBy(css = "button.mat-calendar-period-button")
	WebElement periodBtn;
	String cell = "//td//div[text()='CELL']"; // vary with YYYY-MMM-D
//	calendar validations - method solution pending TODO
	@FindBy(css = "input[formcontrolname='start_date']")
	WebElement sDate;
	@FindBy(css = "input[formcontrolname='end_date']")
	WebElement eDate;

	@FindBy(css = "input[formcontrolname=plan_title]")
	WebElement title;
	@FindBy(xpath = "//input[@formcontrolname='start_date']/ancestor::mat-form-field//mat-datepicker-toggle")
	WebElement startDatePicker;
	@FindBy(xpath = "//input[@formcontrolname='end_date']/ancestor::mat-form-field//mat-datepicker-toggle")
	WebElement endDatePicker;
	@FindBy(css = "mat-select[formcontrolname=pc_company_personnel_id]")
	WebElement pc_SPOC;
	String option = "//mat-option//span[contains(text(),'OPTION')]";

//	milestone fields
	@FindBy(css = "div.add-milestone-box")
	WebElement addMSBox;
	@FindBy(css = "input[placeholder='Milestone 1']")
	WebElement MSTitle;
	@FindBy(css = "app-add-milestone mat-datepicker-toggle")
	WebElement MSDatePicker;

//	metric fields
	@FindBy(css = "div.add-metrics-box")
	WebElement addMetricBox;
	@FindBy(css = "input[placeholder='Metric 1']")
	WebElement metricTitle;
	@FindBy(css = "input[placeholder='Unit']")
	WebElement unit;
	
//	common for milestone and metric - TODO These are not used yet, incorporate somewhere!!!
	@FindBy(css = "span.add-another-link")
	WebElement addAnother;
	@FindBy(css = "span.remove-link")
	WebElement saveGoal;

	public void addNewPlan(RemoteWebDriver driver, String... fields) throws Exception {
//		initiating common fields from arguments - GOAL TYPE-value in fields[0] to be used directly
		String dateStart = fields[1];
		String dateEnd = fields[2];
		String planTitle = fields[3];
		String pc_spoc = fields[4];
		String mileStone = fields[5];
		String metric = null;
		String metricUnit = null;
//		enter data
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmm");
		Date date = new Date();
		click(title, "Title");
		enterText(title, planTitle + dateFormat.format(date), "Title");
		click(startDatePicker, "Start Date");
		selectDate(driver, "START");
		Thread.sleep(250);
		click(endDatePicker, "End Date");
		selectDate(driver, "END");
		click(pc_SPOC, "PC S.P.O.C.");
		Thread.sleep(250);
		click(option.replace("OPTION", pc_spoc), "PC Option" + pc_spoc);
//		milestone
		click(addMSBox, "Add Milestone");
		Thread.sleep(250);
		if (MSTitle.isDisplayed()) {
			assertTrue("New Milestone fields are displayed");
			click(MSTitle, "Milestone Title");
			enterText(MSTitle, mileStone, "MileStone Title");
			Thread.sleep(250);
			click(MSDatePicker, "Milestone DatePicker");
			selectDate(driver, "END");
			Thread.sleep(250);
		} else
			assertFalse("New Milestone Fields not displayed");

//		metrics
		if (fields[0].contains("SIA")) {
			metric = fields[6];
			metricUnit = fields[7];
			click(addMetricBox, "Add Metric");
			Thread.sleep(250);
			if (metricTitle.isDisplayed()) {
				assertTrue("New Metric fields are displayed");
				click(metricTitle, "Metric Title");
				enterText(metricTitle, metric, "Metric Title");
				Thread.sleep(250);
				click(unit, "Metric Unit");
				enterText(unit, metricUnit, "Metric Unit");
			} else
				assertFalse("New Metric Fields not displayed");
		}
	}

	public void selectDate(RemoteWebDriver driver, String dateType) throws Exception {
		DateFormat dF = new SimpleDateFormat("YYYY-MMM-dd");
		Calendar c = Calendar.getInstance();
		if (dateType.contains("END"))
			c.add(Calendar.DAY_OF_MONTH, 30);
		else
			c.add(Calendar.DAY_OF_MONTH, 1);
		String[] date = dF.format(c.getTime()).toUpperCase().split("-");
		click(periodBtn, "Date Period");
		for (String s : date) {
			if (s.length() < 3) // handling single digit date
				s = String.valueOf(Integer.parseInt(s));
			click(cell.replace("CELL", s), "Calendar Cell: " + s);
			Thread.sleep(100);
		}
	}

//	calendar validations - unused and completely not working method
//	public void checkDateValidity(RemoteWebDriver driver, String min, String max, String type) throws Exception {
//		System.out.println("FROM THE PAGE====================Start Date Min");
//		String dynString = sDate.getAttribute("min");
//		SimpleDateFormat dF = new SimpleDateFormat("YYYY-MMM-dd");
//		Date d = dF.parse(dynString.substring(0, dynString.indexOf('T')));
//		System.out.println(d);
//		System.out.println("\nFROM THE DATA ENTERED");
//		System.out.println(min);
//	}
}
