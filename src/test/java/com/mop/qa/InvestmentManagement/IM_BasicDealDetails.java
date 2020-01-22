package com.mop.qa.InvestmentManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.pageobject.FileUpload;
import com.mop.qa.testbase.PageBase;

public class IM_BasicDealDetails extends PageBase {

	public IM_BasicDealDetails(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "a[routerlink*=new-deal]")
	WebElement newDeal;
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;

//	inputs [deal-specifics]
	@FindBy(css = "input[formcontrolname=companyName]")
	WebElement companyName;
	@FindBy(xpath = "(//div[@class='mat-checkbox-inner-container'])[1]")
	WebElement makePrjName;
	@FindBy(css = "input[formcontrolname=projectName]")
	WebElement projectName;
	@FindBy(css = "mat-select[formcontrolname=industry]")
	WebElement industry;
	@FindBy(css = "mat-select[formcontrolname=sector]")
	WebElement sector;
	@FindBy(css = "mat-select[formcontrolname=subSector]")
	WebElement subSector;
	String stakeOpt = "//div[text()='STAKE-NAME']";
	String matOption = "//mat-option/span[contains(text(),'OPTION')]";
	@FindBy(css = "label.upload-btn")
	WebElement uploadBtn;
//	inputs [deal Team]
	@FindBy(css = "input[formcontrolname=dealSponsors]")
	WebElement dealSponsor;
	@FindBy(css = "input[formcontrolname=dealOwner]")
	WebElement dealOwner;
	@FindBy(xpath = "(//mat-checkbox)[2]")
	WebElement sameAsSponsor;
	@FindBy(css = "input[formcontrolname=dealTeamMembers]")
	WebElement dealTeamMembers;

	@FindBy(css = "button[type=submit]")
	WebElement submit;
	@FindBy(css = "button[routerlink*=my-deals]")
	WebElement discardConfirm;

	String createdProject = "";
	String pastSection = "//h3[text()='PAST DEALS']/parent::div//div[contains(text(),'DEAL-NAME')]";
	String newSection = "//h3[text()='NEW']/parent::div//div[contains(text(),'DEAL-NAME')]";

//	delete confirm
	@FindBy(css = "app-confirm-dialog")
	WebElement deleteDialog;
	@FindBy(css = "app-confirm-dialog button")
	WebElement delete;

	@FindBy(css = "h4.sub-title")
	WebElement pageHeading;

//	create deal confirm dialog
	@FindBy(css = "app-confirm-dialog button.btn")
	WebElement createAnyway;
	@FindBy(xpath = "//span[text()='Pass the Deal']")
	WebElement passDeal;
	@FindBy(css = "span.dd-title")
	WebElement dealTitle;

	public void enterBasicDetails(RemoteWebDriver driver, String company, String project, String ind, String sect,
			String subSect, String stake, String filePath, String sponsor, String owner, String teamMembers)
			throws Exception {
		String hideJS = "arguments[0].style.visibility='hidden'";
		String showJS = "arguments[0].style.visibility='visible'";
		Actions action = new Actions(driver);
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);

		// =================================SECTION 1================================
		// company
		click(companyName, "Company Name");
		enterText(companyName, company, "Company Name");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//span[text()='" + company + "']")).size() == 0);
		Thread.sleep(500);
		click("//span[text()='" + company + "']", "Company Option");
		Thread.sleep(500);

		// make project name checkbox
		WebElement companyField = driver.findElement(By.cssSelector("app-company-name mat-form-field"));
		driver.executeScript(hideJS, companyField);
		action.moveToElement(makePrjName).click().build().perform();
		driver.executeScript(showJS, companyField);
		if (driver.findElements(By.cssSelector("input[aria-checked=true]")).size() > 0)
			assertTrue("'Cicked Make this the project name' successfully");
		else
			assertFalse("Click CheckBox Unsuccessful");
		assertTrue("Project Name field is disabled and Company Name is Copied", !projectName.isEnabled());
		System.out.println(projectName.getAttribute("textContent"));
		// reset make project name check box
		driver.executeScript(hideJS, companyField);
		action.moveToElement(makePrjName).click().build().perform();
		driver.executeScript(showJS, companyField);
		if (driver.findElements(By.cssSelector("input[aria-checked=true]")).size() == 0)
			assertTrue("Unchecked 'Make This the Project Name'");
		else
			assertFalse("Click CheckBox Unsuccessful");

		// project name
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmm");
		Date date = new Date();
		click(projectName, "Project Name");
		createdProject = project + dateFormat.format(date);
		enterText(projectName, createdProject, "Project Name");

		// industry Name
		click(industry, "industry DropDown");
		Thread.sleep(250);
		click(matOption.replace("OPTION", ind), "Industry Option");
		Thread.sleep(250);

		// sector Name
		click(sector, "Sector DropDown");
		Thread.sleep(250);
		click(matOption.replace("OPTION", sect), "Sector Option");
		Thread.sleep(250);

		// Sub Sector Name
		click(subSector, "Sub-Sector DropDown");
		Thread.sleep(250);
		click(matOption.replace("OPTION", subSect), "SubSector Option");
		Thread.sleep(250);

		// stake radio
		click(stakeOpt.replace("STAKE-NAME", stake), "Stake option");
		Thread.sleep(250);

		// FileUpload
		click(uploadBtn, "UPLOAD");
		Thread.sleep(3000);
		FileUpload file = new FileUpload(remoteDriver);
		file.fileUpload(filePath);
		Thread.sleep(2000);

//		=================================SECTION 2================================
//		deal sponsor
		click(dealSponsor, "Sponsor");
		enterText(dealSponsor, sponsor, "Deal Sponsor");
		Thread.sleep(500);
		click("//mat-option", "Option");
		if (driver.findElements(By.cssSelector("app-deal-sponsor mat-chip")).size() == 1) {
			assertTrue("sponsor chip added successfully");
			if (driver.findElement(By.cssSelector("app-deal-sponsor mat-chip")).getText().contains(sponsor))
				assertTrue("sponsor added properly");
			else
				assertFalse("Sponsor not correct");
//			assertTrue("Same as Sponsor Checkbox Enabled", sameAsSponsor.isEnabled());
		} else
			assertFalse("sponsor chip not added");

//		deal owner
		WebElement ownerChip = driver.findElement(By.cssSelector("app-deal-owner mat-chip"));
		if (ownerChip.getText().contains(owner)) {
			assertTrue("deal owner chip prefilled with logged in user");
			WebElement ownerField = driver.findElement(By.cssSelector("app-deal-owner mat-form-field"));
			driver.executeScript(hideJS, ownerField);
			action.moveToElement(sameAsSponsor).click().build().perform();
			driver.executeScript(showJS, ownerField);
			Thread.sleep(250);
			if (driver.findElements(By.cssSelector("input[aria-checked=true]")).size() > 0) {
				assertTrue("Cicked 'Same As Sponsor' successfully");
				ownerChip = driver.findElement(By.cssSelector("app-deal-owner mat-chip")); // this is a new chip - need
																							// to update
				assertTrue("Owner Chip Updated", ownerChip.getText().contains(sponsor));
			} else
				assertFalse("Click CheckBox Unsuccessful");
			driver.executeScript(hideJS, ownerField);
			action.moveToElement(sameAsSponsor).click().build().perform();
			driver.executeScript(showJS, ownerField);
			Thread.sleep(250);
			assertTrue("Deal Owner Mat Chip cleared",
					driver.findElements(By.cssSelector("app-deal-owner mat-chip")).size() == 0);
//			enter deal owner
			click(dealOwner, "Deal Owner");
			enterText(dealOwner, owner, "Deal Owner");
			Thread.sleep(250);
			click("//mat-option", "Owner Option");
			Thread.sleep(250);
			ownerChip = driver.findElement(By.cssSelector("app-deal-owner mat-chip"));
			assertTrue("Owner Chip Added Successfully", ownerChip.getText().contains(owner));
		}
		Thread.sleep(1000);
//			team members [optional]
		// TODO
	}

	public void submitAndSelectFlow(RemoteWebDriver driver, String flow) throws Exception {
		click(submit, "SUBMIT");
		Thread.sleep(5000);
		if (driver.findElements(By.cssSelector("app-confirm-dialog")).size() > 0) {
			assertTrue("Confirm Dialog Box is Displayed!");
			switch (flow) {
			case "Create Anyway":
				System.out.println("Running Create Anyway");
				click(createAnyway, "Create Deal Anyway");
				Thread.sleep(2000);
				if (dealTitle.getText().contains(createdProject))
					assertTrue("Landed on Deal Summary Page");
				else
					assertFalse("Deal Summary Page did not open");
				break;

			case "Pass Deal":
				System.out.println("Running Pass Deal");
				click(passDeal, "Pass The Deal");
				Thread.sleep(2000);
				if (newDeal.isDisplayed()) {
					assertTrue("Landed back t my deals page");
					if (driver.findElements(By.xpath(pastSection.replace("DEAL-NAME", createdProject))).size() > 0) {
						Actions action = new Actions(driver);
						action.moveToElement(
								driver.findElement(By.xpath(pastSection.replace("DEAL-NAME", createdProject))))
								.perform();
						assertTrue("Deal Present in Past Deals Section");
					} else
						assertFalse("Deal not added to past deals");
				} else
					assertFalse("Did Not land to my deals page");
				break;
			}
		}
	}

	public void subMitAndDelete(RemoteWebDriver driver) throws Exception {
		click(submit, "SUBMIT");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//img[contains(@src,'spinner')]")).size() > 0);
		if (pageHeading.isDisplayed() && pageHeading.getText().contains("System Rules")) {
			assertTrue("landed on system rules form");
			click(closeBtn, "Close Button");
			Thread.sleep(1000);
			if (newDeal.isDisplayed()) {
				assertTrue("Landed back t my deals page");
				int available = driver.findElements(By.xpath(newSection.replace("DEAL-NAME", createdProject))).size();
				WebElement deal = driver.findElement(By.xpath(newSection.replace("DEAL-NAME", createdProject)));
				click(deal.findElement(By.xpath("./parent::div//div[contains(@class,'action')]/a")), "Delete Button");
				Thread.sleep(250);
				assertTrue("Delete Confirm Dialog displayed", deleteDialog.isDisplayed());
				click(delete, "Delete Button");
				Thread.sleep(2500);
				int avUpdate = driver.findElements(By.xpath(newSection.replace("DEAL-NAME", createdProject))).size();
				System.out.println(available);
				System.out.println(avUpdate);
				assertTrue("Deal Deleted Successfully", available - avUpdate == 1);
			} else
				assertTrue("Did not land to IM Home");
		} else
			assertFalse("System Rules Page did not open");
	}

	public void submitAndOpenDraft(RemoteWebDriver driver) throws Exception {
		click(submit, "SUBMIT");
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//img[contains(@src,'spinner')]")).size() > 0);
		click(closeBtn, "Close Button");
		Thread.sleep(1000);
		if (newDeal.isDisplayed()) {
			assertTrue("Landed back t my deals page");
			WebElement deal = driver.findElement(By.xpath(newSection.replace("DEAL-NAME", createdProject)));
			click(deal.findElement(By.xpath("./parent::div")), "Deal Card");
			Thread.sleep(1000);
			if (pageHeading.isDisplayed() && pageHeading.getText().contains("System Rules"))
				assertTrue("Landed on system Rules page");
			else
				assertFalse("System Rules Page did not open - just submitted");
		} else
			assertTrue("Did not land to IM Home");
	}
}
