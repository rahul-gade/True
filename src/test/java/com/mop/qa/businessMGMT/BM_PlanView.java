package com.mop.qa.businessMGMT;

import java.util.List;

import org.openqa.selenium.By;
//app-view-hundred-day-plan <== Page App Component!!!
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

import jdk.internal.vm.annotation.ForceInline;

public class BM_PlanView extends PageBase {

	public BM_PlanView(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "span.business-goal-view-txt")
	WebElement goalView;
	@FindBy(css = "span.hdp-end-plan-btn")
	WebElement endPlan;
	@FindBy(css = "img[title=Previous]")
	WebElement prevBtn;
	@FindBy(css = "img[title=Next]")
	WebElement nextBtn;
	String vert = "//mat-panel-title[contains(text(),'VERTICAL')]/ancestor::mat-expansion-panel";
	String plan_hdp = "//span[contains(text(),'CODEIN')]";

//	sidebar
	@FindBy(css = "span.vgd-title-text")
	WebElement goalTitle;
	@FindBy(css = "span.vapd-title-text")
	WebElement aPlanTitle;
	@FindBy(css = "div.mat-slider-track-background")
	WebElement sliderBG;
	@FindBy(css = "div.mat-slider-thumb")
	WebElement sliderThumb;
	@FindBy(css = "button.vgd-ss-archive-btn")
	WebElement archive;
	@FindBy(css = "span.view-moredetails-txt")
	WebElement moreDetails;
	@FindBy(css = "a.bm-vgd-close-icon")
	WebElement closeGD;
	@FindBy(css = "a.bm-vapd-close-icon")
	WebElement closeAPD; 
	@FindBy(css = "img[alt=Close]")
	WebElement closeSidebar;

//	end plan
	@FindBy(css = "span.bm-ehdp-checkbox")
	List<WebElement> cBoxWhole;
	@FindBy(css = "button.bm-ehdp-confirmbtn")
	WebElement confirmBtn;
	@FindBy(css = "app-confirm-message-dialog button.btn")
	WebElement confirm;
	@FindBy(css = "app-view-sia-plan-details")
	WebElement SIAplanPage;

	String unended = null, ended = null;

	public void verifySidebar(RemoteWebDriver driver, String planName, String vertical, String Ac_Plan)
			throws Exception {
//		Goal Section
		WebElement hdpPanel = driver.findElementByXPath(vert.replace("VERTICAL", vertical));
		System.out.println(planName);
		click(hdpPanel.findElement(By.xpath(plan_hdp.replace("CODEIN", planName))), "Plan " + planName);
		Thread.sleep(500);
		assertTrue("Split Screen is displayed", driver.findElementsByClassName("aside-content").size() > 0);
		assertTrue("Goal Title is correctly displayed", goalTitle.getText().equals(planName));
//		more details
		click(moreDetails, "View More Details");
		Thread.sleep(250);
		if(driver.findElementsByClassName("bm-vgd-header").size()>0)
			assertTrue("Goal Details POp-up opened");
		else
			assertFalse("Goal Details POp-up not opened");
		click(closeGD, "Close");
		Thread.sleep(250);
		if(driver.findElementsByClassName("bm-vgd-header").size()==0)
			assertTrue("Goal Details POp-up closed");
		else
			assertFalse("Goal Details POp-up not closed");
//		progress slider
		fillSlider(driver);
		if (driver.findElementsByXPath("//*[contains(text(),'successfully')]").size() > 0) {
			assertTrue("Progress saved successfully");
		} else
			assertFalse("Toast Messages Not displayed");
		assertTrue("Archive Button Displayed when progress is set to 100%", archive.isDisplayed());

//		REFRESH PANEL after closing sidebar - check percentage
		click(closeSidebar, "Close Sidebar");
		Thread.sleep(250);
		hdpPanel = driver.findElementByXPath(vert.replace("VERTICAL", vertical));
		assertTrue("Split Screen is closed", driver.findElementsByClassName("aside-content").size() == 0);

		int perc = Integer
				.parseInt(driver.findElementByClassName("accor-header-percentage").getText().replaceAll("[^0-9]", ""));
		assertTrue("Percentage saved successfully", perc == 100);

//		Action plan subsection
		System.out.println(Ac_Plan);
		WebElement aPlan = hdpPanel.findElement(By.xpath(plan_hdp.replace("CODEIN", Ac_Plan)));
//		String aPTitle = aPlan.getText();
		click(aPlan, Ac_Plan);
		Thread.sleep(250);
		assertTrue("Split Screen is displayed", driver.findElementsByClassName("aside-content").size() > 0);
		if (aPlanTitle.getText().contains(Ac_Plan))
			assertTrue("Action Plan Title is correctly displayed");
		else
			assertFalse("Action Plan Title is not corectly displayed");
		
//		action plan view more details
		click(moreDetails, "View More Details");
		Thread.sleep(250);
		if(driver.findElementsByClassName("bm-vapd-title").size()>0)
			assertTrue("Action Plan Details POp-up opened");
		else
			assertFalse("Action Plan Details POp-up not opened");
		click(closeAPD, "Close");
		Thread.sleep(250);
		if(driver.findElementsByClassName("bm-vapd-title").size()==0)
			assertTrue("Action Plan Details POp-up closed");
		else
			assertFalse("Action Plan Details POp-up not closed");
		fillSlider(driver);
		if (driver.findElementsByXPath("//*[contains(text(),'successfully')]").size() > 0) {
			assertTrue("Progress saved successfully");
		} else
			assertFalse("Toast Messages Not displayed");
		click(closeSidebar, "Close Sidebar");
		Thread.sleep(250);
		assertTrue("Split Screen is closed", driver.findElementsByClassName("aside-content").size() == 0);
		perc = Integer.parseInt(
				driver.findElementByClassName("accor-subsection-percentage").getText().replaceAll("[^0-9]", ""));
		assertTrue("Percentage saved successfully", perc == 100);
		Thread.sleep(500);
	}

	public void endPlan(RemoteWebDriver driver, String... hdps) throws Exception {
		click(endPlan, "END PLAN");
		Thread.sleep(250);
		assertTrue("End Plan Pop-up displayed",
				driver.findElementsByTagName("app-closing-hundred-day-plan").size() > 0);
//		goal counter
		int gCount = Integer
				.parseInt(driver.findElementByClassName("bm-goal-count").getText().replaceAll("[^0-9]", ""));
		if (gCount == cBoxWhole.size())
			assertTrue("Selected Goals Count reflected correctly - " + gCount + " goals");
//		check goal selections
		for (int i = 0; i < cBoxWhole.size(); i++) {
			if (i == 0)
				ended = hdps[i];
			WebElement cBox = cBoxWhole.get(i).findElement(By.xpath("./mat-checkbox"));
			if (cBoxWhole.get(i).getText().contains(hdps[i]) && cBox.getAttribute("class").contains("checked"))
				assertTrue(hdps[i] + " is checked by default!");
			else
				assertFalse(hdps[i] + " is Not checked by default!");
//			deselect last goal
			if (i == cBoxWhole.size() - 1) {
				click(cBox, "CheckBox for " + hdps[i]);
				unended = hdps[i];
				if (!cBox.getAttribute("class").contains("checked"))
					assertTrue("Goal " + hdps[i] + " deselected successfully");
				else
					assertFalse("Goal not deselected.");
			}
		}
//		goal count recheck
		gCount = Integer.parseInt(driver.findElementByClassName("bm-goal-count").getText().replaceAll("[^0-9]", ""));
		if (gCount == cBoxWhole.size() - 1)
			assertTrue("Selected Goals Count reflected correctly - " + gCount + " goals");

//		finally end
		click(confirmBtn, "Confirm");
		Thread.sleep(250);
		click(confirm, "Confirm in Final Dialog");
		Thread.sleep(1500);
		if (SIAplanPage.isDisplayed())
			assertTrue("Landed on SIA Plan Page");
		else
			assertFalse("Did not land on SIA Plan Page");
	}

	void fillSlider(RemoteWebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		int width = sliderBG.getSize().getWidth();
		action.dragAndDropBy(sliderThumb, width, 0).perform();
		Thread.sleep(250);
	}

}
