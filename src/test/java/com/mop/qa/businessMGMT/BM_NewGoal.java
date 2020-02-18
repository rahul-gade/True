package com.mop.qa.businessMGMT;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_NewGoal extends PageBase {

	public BM_NewGoal(RemoteWebDriver driver) {
		super(driver);
	}

//	datePicker
	@FindBy(css = "button.mat-calendar-period-button")
	WebElement periodBtn;
	String cell = "//div[text()='CELL']"; // vary with YYYY->MMM->D
	String dateStart = null, dateEnd = null;

//	basic data
	@FindBy(css = "input[formcontrolname=title]")
	WebElement title;
	@FindBy(css = "mat-select[formcontrolname=vertical]")
	WebElement vertical;
	@FindBy(css = "mat-select[formcontrolname=vertical] span.mat-select-value-text span")
	WebElement filledVert;
	String fV = "";
	@FindBy(css = "mat-select[formcontrolname=goal_type]")
	WebElement goalType;
	String option = "//mat-option/span[contains(text(),'OPTION')]"; // Also for S.P.O.C.
	String crit = "div.criticalityDefault:nth-of-type(SETCRITICALITY)"; // 1=Low; 2=Medium; 3=High;
	@FindBy(css = "button.btn")
	WebElement submit;

//	Additional Fields
	@FindBy(xpath = "//input[@formcontrolname='start']/ancestor::mat-form-field//mat-datepicker-toggle")
	WebElement startDatePicker;
	@FindBy(xpath = "//input[@formcontrolname='end']/ancestor::mat-form-field//mat-datepicker-toggle")
	WebElement endDatePicker;
	@FindBy(css = "mat-select[formcontrolname=tn_spoc]")
	WebElement tnSPOC;
	@FindBy(css = "mat-select[formcontrolname=pc_spoc]")
	WebElement pcSPOC;

//	sia specific fields - METRICS
	@FindBy(css = "div.add-metrics-box")
	WebElement addMetrics;
	@FindBy(css = "input[placeholder='Metric 1']")
	WebElement metric_1;
	@FindBy(css = "input[placeholder='Unit']")
	WebElement metric_1Unit;
	@FindBy(css = "span.remove-link")
	WebElement removeLink;

	public void checkAutoFill(RemoteWebDriver driver, String cardName, String vert) throws Exception {
		if (title.getAttribute("value").trim().equalsIgnoreCase(cardName))
			assertTrue("Title " + cardName + " is autofilled");
		else
			assertFalse("Title is not autofilled/incorrectly filled");

		if (vert.equalsIgnoreCase(filledVert.getText().trim())) {
			assertTrue("Vertical " + vert + " is autofilled");
			fV = filledVert.getText().trim();
		} else
			assertFalse("Vertical is not autofilled/incorrectly filled");
	}

//	used by 100 Day Goal & SIA Goal - Don't use for IDEA
	public void submitAndVerify(RemoteWebDriver driver, String type, String goalTitle) throws Exception {
		click(submit, "Save Goal/Add Plan Button");
		Thread.sleep(1000);
		if (driver.findElementsByTagName("app-add-action-plan").size() > 0)
			assertTrue("Landed on Add Action Plan Page");
		else
			assertFalse("Add Action Plan Page did not open");
	}

	public void enterAdditionalFields(RemoteWebDriver driver, String tnSpoc, String pcSpoc) throws Exception {
//		date
		click(startDatePicker, "Start Date");
		Thread.sleep(250);
		selectDate(driver, "START");
		click(endDatePicker, "End Date");
		Thread.sleep(250);
		selectDate(driver, "END");
		Thread.sleep(2000);
//		S.P.O.C.
		click(tnSPOC, "True North S.P.O.C.");
		Thread.sleep(250);
		click(option.replace("OPTION", tnSpoc), "Option - " + tnSpoc);
		Thread.sleep(250);
		click(pcSPOC, "True North S.P.O.C.");
		Thread.sleep(250);
		click(option.replace("OPTION", pcSpoc), "Option - " + pcSpoc);
		Thread.sleep(250);
	}

	public void testAdditionalFields(RemoteWebDriver driver, String type) throws Exception {
		assertTrue("Start Date field is displayed", startDatePicker.isDisplayed());
		assertTrue("End Date field is displayed", endDatePicker.isDisplayed());
		assertTrue("TN S.P.O.C. field is displayed", tnSPOC.isDisplayed());
		assertTrue("Portfolio Company S.P.O.C. field is displayed", pcSPOC.isDisplayed());
//		for SIA
		if (type.contains("SIA")) {
			assertTrue("Add Metrics Field is displayed", addMetrics.isDisplayed());
			click(addMetrics, "ADD Metrics");
			Thread.sleep(100);
			if (metric_1.isDisplayed()) {
				assertTrue("Metric field added");
				click(removeLink, "Remove Link");
				Thread.sleep(100);
			} else
				assertFalse("Metric field did not appear");
		}
	}

	public void fillbasicData(RemoteWebDriver driver, String goalTitle, String vert, String type, String criticality,
			String critCode) throws Exception {
		click(title, "Title");
		enterText(title, goalTitle, "Title");
		Thread.sleep(500);
		click(vertical, "Vertical");
		Thread.sleep(500);
		click(option.replace("OPTION", vert), "Vertical Option - " + vert);
		Thread.sleep(500);
		click(goalType, "Type");
		Thread.sleep(500);
		click(option.replace("OPTION", type), "Type Option - " + type);
		Thread.sleep(500);
		WebElement critOpt = driver.findElementByCssSelector(crit.replace("SETCRITICALITY", critCode));
		click(critOpt, "Criticality Option: " + criticality);
		Thread.sleep(500);
	}

//	angular datepicker handler method
	public void selectDate(RemoteWebDriver driver, String dateType) throws Exception {
		DateFormat dF = new SimpleDateFormat("YYYY-MMM-dd");
		Calendar c = Calendar.getInstance();
		if (dateType.contains("END")) {
			c.add(Calendar.DAY_OF_MONTH, 30);
			dateStart = dF.format(c.getTime());
		} else {
			c.add(Calendar.DAY_OF_MONTH, 1);
			dateEnd = dF.format(c.getTime());
		}
		String[] date = dF.format(c.getTime()).toUpperCase().split("-");
		click(periodBtn, "Date Period");
		for (String s : date) {
			if (s.length() < 3)
				s = String.valueOf(Integer.parseInt(s));
			click(cell.replace("CELL", s), "Calendar Cell: " + s);
			Thread.sleep(100);
		}
	}
}
