package com.mop.qa.InvestmentManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_HypothesisTab extends PageBase {

	public IM_HypothesisTab(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "a[routerlink=hypothesis]")
	WebElement hypthesisTab;
	@FindBy(css = "section.hypotheses-section")
	WebElement hypothesisSection;
	String createdHypothesis = "";

	@FindBy(css = "a.addnew-btn")
	WebElement addNew;
	@FindBy(css = "app-new-hypothesis")
	WebElement addHypoPopup;
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;
	@FindBy(css = "app-new-hypothesis mat-select")
	WebElement hypoCategory;
	String category = "//span[contains(text(),'CATEGORY')]";
	@FindBy(css = "input[name=hypothesisTitle]")
	WebElement inputTitle;
	@FindBy(css = "textarea[name=hypothesisDesc]")
	WebElement inputDesc; 
	@FindBy(css = "button.btn")
	WebElement addBtn;

	@FindBy(css = "a.library-btn")
	WebElement libraryBtn;
	@FindBy(css = "section.hypothesis-library-section")
	WebElement hypoLibrary;
	@FindBy(css = "a.back-btn")
	WebElement backBtn;

	public void createHypothesis(RemoteWebDriver driver, String cat, String title, String desc) throws Exception {
		Thread.sleep(250);
		click(hypthesisTab, "Hypothesis Tab");
		Thread.sleep(1000);

		if (hypothesisSection.isDisplayed())
			assertTrue("Hypothesis Section is displayed");
		else
			assertFalse("Hypothesis Section is NOT displayed");

		click(addNew, "Add New");
		Thread.sleep(250);
		if (addHypoPopup.isDisplayed()) {
			assertTrue("Add New Hypothesis is displayed");
			click(hypoCategory, "Category DropDown");
			Thread.sleep(250);
			click(category.replace("CATEGORY", cat), "Category Option");
			if (driver.findElements(By.cssSelector("a.library-btn")).size() > 0) {
				assertTrue("Library Btn Is displayed");
				click(libraryBtn, "Library btn");
				Thread.sleep(250);
				if (hypoLibrary.isDisplayed()) {
					assertTrue("Hypothesis Library is displayed");
					click(backBtn, "Back");
					Thread.sleep(250);
					assertTrue("Landed Back to create hypothesis popup",
							driver.findElements(By.cssSelector("section.hypothesis-library-section")).size() == 0);
				}
			} // no else needed here
			if (!addBtn.isEnabled())
				assertTrue("Add Button is diabled");
			else
				assertTrue("Add button is enabled by defult");
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmm");
			Date date = new Date();
			createdHypothesis += title + dateFormat.format(date);
			click(inputTitle, "Title");
			enterText(inputTitle, createdHypothesis, "Title");
			Thread.sleep(250);
			click(inputDesc, "Description");
			enterText(inputDesc, desc, "Description");
			Thread.sleep(250);
			if (addBtn.isEnabled()) {
				assertTrue("Add Button is enbled after entering fields.");
				click(addBtn, "Add");
				Thread.sleep(1500);
			}
		} else
			assertFalse("New Hypothesis POpup not displayed");

	}

}
