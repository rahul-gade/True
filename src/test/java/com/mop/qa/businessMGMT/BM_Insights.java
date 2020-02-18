package com.mop.qa.businessMGMT;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.pageobject.FileUpload;
import com.mop.qa.testbase.PageBase;

public class BM_Insights extends PageBase {

	public BM_Insights(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "span.company-name")
	WebElement companyName;
	@FindBy(css = "img[src*=back]")
	WebElement backBtn;
	@FindBy(css = "ul.navigation-menu")
	WebElement navMenu;
	boolean checked = false;

//	check accordion
	@FindBy(css = "mat-expansion-panel:nth-of-type(1)")
	WebElement accordion_1;
	@FindBy(css = "span.inf")
	WebElement postCount;
	@FindBy(css = "a.close-btn")
	WebElement close;
	@FindBy(css = "mat-select[name=goal-assesment]")
	WebElement assessment;
	String option = "//mat-option//span[contains(text(),'OPTION')]";
	@FindBy(xpath = "//div[text()=' NOT APPLICABLE ']")
	WebElement nA;
	@FindBy(xpath = "//div[text()=' ADD TO GOALS ']")
	WebElement addToGoals;
	
	String cardName ="";
	String vert = "";

//	post pop-up
	@FindBy(css = "textarea[formcontrolname='postContent']")
	WebElement postTitle;
	@FindBy(css = "a.attachment-btn")
	WebElement attachDoc;
	@FindBy(css = "label.drag-section")
	WebElement dragSection;
	@FindBy(css = "button.btn")
	WebElement submitBtn;

	public void addToGoal(RemoteWebDriver driver) throws Exception {
		List<WebElement> navs = navMenu.findElements(By.tagName("li"));
		for (WebElement nav : navs) {
			int count = Integer.parseInt(nav.findElement(By.tagName("p")).getText().replaceAll("/", ""));
			vert = nav.getText().trim().replaceAll("[^a-zA-Z]", "");
			click(nav, vert);
			if (count > 0) {
				int accordions = driver.findElementsByTagName("mat-expansion-panel").size();
				WebElement l_acc = driver
						.findElementByCssSelector("mat-expansion-panel:nth-of-type(" + accordions + ")");
				cardName = l_acc.findElement(By.cssSelector("span.customer")).getText();
//				Scroll Down
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

				click(l_acc.findElement(By.cssSelector("img[src*=overflow]")), "Overflow Menu in " + cardName);
				Thread.sleep(250);
				click(addToGoals, "ADD TO GOALS");
				Thread.sleep(500);
				break;
			}
		}
	}

	public void markNA(RemoteWebDriver driver) throws Exception {
		List<WebElement> navs = navMenu.findElements(By.tagName("li"));
		for (WebElement nav : navs) {
			int count = Integer.parseInt(nav.findElement(By.tagName("p")).getText().replaceAll("/", ""));
			String tabName = nav.getText().trim().replaceAll("[^a-zA-Z]", "");
			click(nav, tabName);
			if (count > 0) {
				int accordions = driver.findElementsByTagName("mat-expansion-panel").size();
				WebElement l_acc = driver
						.findElementByCssSelector("mat-expansion-panel:nth-of-type(" + accordions + ")");
				String iName = l_acc.findElement(By.cssSelector("span.customer")).getText();
//				Scroll Down
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

				click(l_acc.findElement(By.cssSelector("img[src*=overflow]")), "Overflow Menu in " + iName);
				Thread.sleep(250);
				click(nA, "NOT APPLICABLE");
				Thread.sleep(250);
				l_acc = driver.findElementByCssSelector("mat-expansion-panel:nth-of-type(" + accordions + ")");
				if (l_acc.getAttribute("class").contains("bm-makeapplicable-active"))
					assertTrue(iName + " made Inapplicable");
				else
					assertFalse(iName + " still Applicable");
				if (l_acc.findElements(By.className("update-status")).size() > 0)
					assertTrue("UPdate status is: " + l_acc.findElement(By.className("update-status")).getText());
				else
					assertFalse("Update Status Not shown");
				click(backBtn, "Back");
				Thread.sleep(250);
				break;
			}
		}
	}

	public void makeApplicable(RemoteWebDriver driver) throws Exception {
		List<WebElement> navs = navMenu.findElements(By.tagName("li"));
		for (WebElement nav : navs) {
			int count = Integer.parseInt(nav.findElement(By.tagName("p")).getText().replaceAll("/", ""));
			String tabName = nav.getText().trim().replaceAll("[^a-zA-Z]", "");
			click(nav, tabName);
			if (count > 0) {
				int accordions = driver.findElementsByTagName("mat-expansion-panel").size();
				WebElement l_acc = driver
						.findElementByCssSelector("mat-expansion-panel:nth-of-type(" + accordions + ")");
				String iName = l_acc.findElement(By.cssSelector("span.customer")).getText();
//				Scroll Down
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

				click(l_acc.findElement(By.cssSelector("span.bm-bi-makeapplicable")), "Make Applicable - " + iName);
				Thread.sleep(250);
//				click(nA, "NOT APPLICABLE");
//				Thread.sleep(250);
				l_acc = driver.findElementByCssSelector("mat-expansion-panel:nth-of-type(" + accordions + ")");
				if (!l_acc.getAttribute("class").contains("bm-makeapplicable-active"))
					assertTrue(iName + " made Applicable");
				else
					assertFalse(iName + " still Not Applicable");
				if (l_acc.findElements(By.className("update-status")).size() > 0)
					assertTrue("UPdate status is: " + l_acc.findElement(By.className("update-status")).getText());
				else
					assertFalse("Update Status Not shown");
				click(backBtn, "Back");
				Thread.sleep(250);
				break;
			}
		}
	}

	public void assesInsight(RemoteWebDriver driver, String assess) throws Exception {
		click(assessment, "Assess Now");
		Thread.sleep(250);
		click(option.replace("OPTION", assess), "Assessment option: " + assess);
		if (driver.findElementsByXPath("//*[contains(text(),'assessment updated')]").size() > 0)
			assertTrue("Assessment Updated successfuly!");
		else
			assertFalse("Assessment Not Updated");

		click(backBtn, "Back");
		Thread.sleep(250);
	}

	public void addPost(RemoteWebDriver driver, String pTitle, String filePath) throws Exception {
		List<WebElement> navs = navMenu.findElements(By.tagName("li"));

		for (WebElement nav : navs) {
			int count = Integer.parseInt(nav.findElement(By.tagName("p")).getText().replaceAll("/", ""));
			String tabName = nav.getText().trim().replaceAll("[^a-zA-Z]", "");
			click(nav, tabName);
			if (count > 0) {
				int pCount = Integer.parseInt(postCount.getText().trim());
				String iName = accordion_1.findElement(By.cssSelector("span.customer")).getText();
				click(accordion_1, iName);
				Thread.sleep(250);
				if (accordion_1.getAttribute("class").contains("mat-expanded"))
					assertTrue(iName + " is expanded");
				else
					assertFalse(iName + " Not Expanded");

				click(accordion_1.findElement(By.cssSelector("span.new-post")), "New Post");
				Thread.sleep(250);

//				add post 
				click(postTitle, "Post Title");
				enterText(postTitle, pTitle, "Post Title");
				Thread.sleep(250);
				click(attachDoc, "Attach Document");
				Thread.sleep(250);
				if (driver.findElementsByClassName("hypothesis-library-section").size() > 0)
					assertTrue("File Upload Pop-up displayed");
				else
					assertFalse("File Upload pop-up not displayed");

				click(dragSection, "Drag Section");
				Thread.sleep(3000);
				FileUpload file = new FileUpload(remoteDriver);
				file.fileUpload(filePath);
				Thread.sleep(2000);

				click(submitBtn, "Upload");
				Thread.sleep(1000);

				if (driver.findElementsByClassName("hypothesis-library-section").size() == 0)
					assertTrue("Landed ack to new Post Pop-Up page");
				else
					assertFalse("Screen didn't change");

				click(submitBtn, "POST");
				Thread.sleep(250);
				if (Integer.parseInt(postCount.getText().trim()) > pCount)
					assertTrue("Post Added Successfully");
				else
					assertFalse("Post Not Added");

				if (accordion_1.findElements(By.className("update-status")).size() > 0)
					assertTrue("UPdate status is: " + accordion_1.findElement(By.className("update-status")).getText());
				else
					assertFalse("Update Status Not shown");
				break;
			}
		}
	}

	public void testInsightPage(RemoteWebDriver driver, String company) throws Exception {
//		company name
		if (companyName.getText().trim().contains(company))
			assertTrue("Company Name shown in header of the page");
		else
			assertFalse("Comppany name is different/not present");

		assertTrue("Navigation menu is displayed", navMenu.isDisplayed());
		List<WebElement> navs = navMenu.findElements(By.tagName("li"));
//		click(navs.get(0), "first - Navigation Tab");

//		navigation
		for (WebElement nav : navs) {
			String tabName = nav.getText().trim().replaceAll("[^a-zA-Z]", "");
			int count = Integer.parseInt(nav.findElement(By.tagName("p")).getText().replaceAll("/", ""));
			if (count > 0 && !checked)
				checkAccordion(driver);
			click(nav, tabName);
			Thread.sleep(250);
			if (nav.getAttribute("class").contains("active-segment"))
				assertTrue(tabName + ": is Activated");
			else
				assertTrue("Activation failed");
			System.out.println(tabName + "\t-->" + count); // show
			if (driver.findElementsByTagName("mat-expansion-panel").size() == count)
				assertTrue("Available Insights are equal to the count shown in tab");
			else
				assertFalse("Available Insights are equal to the count shown in tab");
		}
	}

	private void checkAccordion(RemoteWebDriver driver) throws Exception {
		String iName = accordion_1.findElement(By.cssSelector("span.customer")).getText();
		click(accordion_1, iName);
		Thread.sleep(250);
		if (accordion_1.getAttribute("class").contains("mat-expanded"))
			assertTrue(iName + " is expanded");
		else
			assertFalse(iName + " Not Expanded");

		click(accordion_1.findElement(By.cssSelector("span.new-post")), "New Post");
		Thread.sleep(250);
		if (driver.findElementsByTagName("app-new-post").size() > 0)
			assertTrue("New Post Pop-up Opened");
		else
			assertFalse("New Post Pop-up did not open");
		click(close, "Cross Button");
		Thread.sleep(250);
		click(accordion_1, iName);
		Thread.sleep(100);

//		overflow
		click(accordion_1.findElement(By.cssSelector("img[src*=overflow]")), "Overflow");
		Thread.sleep(250);
		if (driver.findElementsByClassName("mat-menu-content").size() > 0) {
			assertTrue("Overflow menu properly displayed");
			Actions action = new Actions(driver);
			action.moveByOffset(-100, 0).click().build().perform();
		} else
			assertFalse("Overflow menu not displayed");
		checked = true;
	}
}
