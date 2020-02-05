package com.mop.qa.businessMGMT;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class BM_Company_VisMis extends PageBase {

	public BM_Company_VisMis(RemoteWebDriver driver) {
		super(driver);
	}

	String vision = "Sample Vision Text";
	String mission = "Sample Mission Text";
	String value = "Value - ";

	@FindBy(xpath = "//a[text()='Vision & Mission']")
	WebElement tabVisionMission;
	String li = "./parent::li";

	@FindBy(css = "div.add-vision-content a")
	WebElement addDetails;
	@FindBy(css = "span.edit-txt")
	WebElement editDetails;
	@FindBy(css = "app-add-vision-mission")
	WebElement visPopUp;
	@FindBy(css = "input[formcontrolname=vision]")
	WebElement visionInput;
	@FindBy(css = "input[formcontrolname=mission]")
	WebElement missionInput;
	@FindBy(css = "app-bullet-list ul")
	WebElement valueList;
	@FindBy(css = "button.btn")
	WebElement addBtn;

	public void editVisionMission(RemoteWebDriver driver) throws Exception {
		click(editDetails, "Edit Vision and Mission");
		Thread.sleep(500);
		assertTrue("Add Vision Pop-up displayed", visPopUp.isDisplayed());
		click(visionInput, "Vision");
		enterText(visionInput, vision + " - EDITED", "Vision");
		Thread.sleep(500);
		click(missionInput, "Mission");
		enterText(missionInput, mission + " - EDITED", "Mission");
		Thread.sleep(500);
		Actions action = new Actions(driver);
		action.moveToElement(valueList).click().build().perform();
		int i;
		for (i = 1; i <= 4; i++) {
			WebElement val = valueList.findElement(By.xpath("./li[" + i + "]"));
			action.moveToElement(val).click().build().perform();
			val.clear();
			action.moveToElement(val).click().build().perform();
			action.sendKeys(value + i + " - EDITED").build().perform();
			Thread.sleep(250);
		}
		if (i > 4)
			assertTrue("Values List updated successfully");
		else
			assertFalse("values list not populated");
		click(addBtn, "Add Button");
		Thread.sleep(500);
		if (driver.findElementsByTagName("app-add-vision-mission").size() == 0)
			assertTrue("landed back to vision and mission page");
		else
			assertFalse("POp-up did not close");
	}

	public boolean addVisionMission(RemoteWebDriver driver) throws Exception {
		if (driver.findElementsByClassName("add-vision-content").size() == 0) {
			assertTrue("Vision and Mission already entered");
			return false;
		} else {
			assertTrue("Vision and Mission section empty");
			click(addDetails, "Add Details");
			Thread.sleep(500);
			assertTrue("Add Vision Pop-up displayed", visPopUp.isDisplayed());
			click(visionInput, "Vision");
			enterText(visionInput, vision, "Vision");
			Thread.sleep(500);
			click(missionInput, "Mission");
			enterText(missionInput, mission, "Mission");
			Thread.sleep(500);
			Actions action = new Actions(driver);
			action.moveToElement(valueList).click().build().perform();
			int i;
			for (i = 1; i <= 4; i++) {
				action.sendKeys(Keys.ENTER).build().perform();
				WebElement val = valueList.findElement(By.xpath("./li[" + i + "]"));
				action.moveToElement(val).click().build().perform();
				action.sendKeys(value + i).build().perform();
				Thread.sleep(250);
			}
			if (i > 4)
				assertTrue("Values List populated successfully");
			else
				assertFalse("values list not populated");
			click(addBtn, "Add Button");
			Thread.sleep(500);
			if (driver.findElementsByTagName("app-add-vision-mission").size() == 0)
				assertTrue("landed back to vision and mission page");
			else
				assertFalse("POp-up did not close");
			return true;
		}
	}

	public void leadHere(RemoteWebDriver driver) throws Exception {
		Thread.sleep(250);
		click(tabVisionMission, "Vision And Mission");
		Thread.sleep(250);
		if (tabVisionMission.findElement(By.xpath(li)).getAttribute("class").contains("active"))
			assertTrue("Vision and Mission Tab Active");
		else
			assertFalse("Vision and Mission Tab not activated");
	}
}
