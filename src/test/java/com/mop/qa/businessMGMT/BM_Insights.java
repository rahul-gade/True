package com.mop.qa.businessMGMT;

import java.util.List;

import org.openqa.selenium.By;
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

//	post pop-up
	@FindBy(css = "textarea[formcontrolname='postContent']")
	WebElement postTitle;
	@FindBy(css = "a.attachment-btn")
	WebElement attachDoc;
	@FindBy(css = "label.drag-section")
	WebElement dragSection;
	@FindBy(css = "button.btn")
	WebElement submitBtn;

	public void addPost(RemoteWebDriver driver, String pTitle, String filePath) throws Exception {
		List<WebElement> navs = navMenu.findElements(By.tagName("li"));

		for (WebElement nav : navs) {
			int count = Integer.parseInt(nav.findElement(By.tagName("p")).getText().replaceAll("/", ""));
			String tabName = nav.getText().trim().replaceAll("[^a-zA-Z]", "");
			click(nav, tabName);
			if (count > 0) {
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
				if(Integer.parseInt(postCount.getText().trim()) > 0)
					assertTrue("Post Added Successfully");
				else
					assertFalse("Post Not Added");
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
			System.out.println(tabName + "\t\t-->" + count); // show
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
