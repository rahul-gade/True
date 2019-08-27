package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class OnePagerPartial extends PageBase {

	public OnePagerPartial(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	@FindBy(xpath = "(//span[text()='Performance']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement performanceLike;
	@FindBy(xpath = "(//span[text()='Performance']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement performanceDislike;
	@FindBy(xpath = "(//span[text()='Performance']/parent::div//a[@class='link-dislike-btn'])[3]")
	private WebElement performanceLikeIcon;
	@FindBy(xpath = "(//span[text()='Review']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement reviewLike;
	@FindBy(xpath = "(//span[text()='Review']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement reviewDislike;
	@FindBy(xpath = "(//span[text()='Review']/parent::div//a[@class='link-dislike-btn'])[3]")
	private WebElement reviewDislikeIcon;
	@FindBy(xpath = "//div[text()=' TIC ']/ancestor::div[2]//span[text()='NEXT']/parent::button")
	private WebElement btnTICNext;
	@FindBy(xpath = "(//span[text()='test1']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement testLike;
	@FindBy(xpath = "(//span[text()='test1']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement testDislike;
	@FindBy(xpath = "(//span[text()='test1']/parent::div//a[@class='link-dislike-btn'])[3]")
	private WebElement testLikeBtn;

	// ----------------INDUSTRY PAGE------------//
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement competitionLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li")
	private WebElement txtcompetitionLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement competitionDisLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div//a[@class='link-dislike-btn'])[3]")
	private WebElement competitionLikeIcon;
	@FindBy(xpath = "(//span[text()='Underlying industry growth']/parent::div//a[@class='link-dislike-btn'])[3]")
	private WebElement industryLikeIcon;
	@FindBy(xpath = "(//span[text()='Underlying industry growth']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement industryLike;
	@FindBy(xpath = "(//span[text()='Underlying industry growth']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement industryDisLike;
	@FindBy(xpath = "(//span[text()='Underlying industry growth']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement txtindustryDisLike;
	@FindBy(xpath = "(//span[text()='Underlying industry growth']/parent::div//a[@class='link-dislike-btn'])[3]")
	private WebElement industryDisLikeIcon;
	@FindBy(xpath = "(//span[text()='Expected Industry Growth']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement expectedIndustryLike;
	@FindBy(xpath = "(//span[text()='Expected Industry Growth']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement expectedIndustryDislike;
	@FindBy(xpath = "(//span[text()='Expected Industry Growth']/parent::div//a[@class='link-dislike-btn'])[3]")
	private WebElement expectedIndustryDislikeIcon;
	@FindBy(xpath = "(//span[text()='NEXT'])[2]/parent::button")
	private WebElement btnnext;

	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement closeBtn;

	public void enterOnePagerTIC(RemoteWebDriver driver, String likeText, String dislikeText) throws Exception {
		Actions action = new Actions(remoteDriver);
		Thread.sleep(1500);
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		if (driver.findElements(By.xpath("//span[text()='test1']")).size() > 0) {
			action.moveToElement(testLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='test1']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(likeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(testDislike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='test1']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(dislikeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(testLikeBtn).click().build().perform();
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//span[text()='Performance']")).size() > 0) {
			action.moveToElement(performanceLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Performance']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(likeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(performanceDislike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Performance']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(dislikeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(performanceLikeIcon).click().build().perform();
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//span[text()='Review']")).size() > 0) {
			action.moveToElement(reviewLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Review']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(likeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(reviewDislike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Review']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(dislikeText).build().perform();
			Thread.sleep(1000);
		}
		action.moveToElement(btnTICNext).click().build().perform();
		Thread.sleep(100);
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);

	}

	public void enterOnePagerIndustry(RemoteWebDriver driver, String likeText, String dislikeText) throws Exception {
		Actions action = new Actions(remoteDriver);
		Thread.sleep(2000);
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);
		if (driver.findElements(By.xpath("(//span[text()='NEXT'])[2]/ancestor::form//span[text()='Competition']"))
				.size() > 0) {
			action.moveToElement(competitionLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(likeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(competitionLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(dislikeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(competitionLikeIcon).click().build().perform();
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//span[text()='Underlying industry growth']")).size() > 0) {
			action.moveToElement(industryLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Underlying industry growth']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(likeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(industryDisLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Underlying industry growth']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(dislikeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(industryLikeIcon).click().build().perform();
			Thread.sleep(1000);
		}
		if (driver.findElements(By.xpath("//span[text()='Expected Industry Growth']")).size() > 0) {
			action.moveToElement(expectedIndustryLike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Expected Industry Growth']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(likeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(expectedIndustryDislike).click().build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.moveToElement(driver.findElement(By.xpath(
					"(//span[text()='Expected Industry Growth']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li[1]")))
					.click().build().perform();
			action.sendKeys(dislikeText).build().perform();
			Thread.sleep(1000);

			action.moveToElement(expectedIndustryDislikeIcon).click().build().perform();
			Thread.sleep(1000);
		}
		click(btnnext, "NEXT");
		Thread.sleep(100);
		do {
			Thread.sleep(1000);
		} while (driver.findElements(By.xpath("//div/div/img")).size() > 0);

	}

	public void closePager(RemoteWebDriver remoteDriver) throws Exception {
		click(closeBtn, "'Close-One-Pager' Button");
		Thread.sleep(500);
	}

	public void clickNextBtn(RemoteWebDriver remoteDriver) throws Exception {
		click(btnTICNext, "TIC Page Next Button");
		Thread.sleep(500);
	}

	public void industryPageTest(RemoteWebDriver driver, String likeText, String disLikeText) throws Exception {
		String[] enteredText = new String[6];
		enteredText[0] = driver.findElement(By.xpath(
				"(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li"))
				.getText().trim();
		enteredText[1] = driver.findElement(By.xpath(
				"(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li"))
				.getText().trim();
		enteredText[2] = driver.findElement(By.xpath(
				"(//span[text()='Underlying industry growth']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li"))
				.getText().trim();
		enteredText[3] = driver.findElement(By.xpath(
				"(//span[text()='Underlying industry growth']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li"))
				.getText().trim();
		enteredText[4] = driver.findElement(By.xpath(
				"(//span[text()='Expected Industry Growth']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li"))
				.getText().trim();
		enteredText[5] = driver.findElement(By.xpath(
				"(//span[text()='Expected Industry Growth']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li"))
				.getText().trim();
		int i;
		for (i = 0; i < enteredText.length; i++) {
			if (i % 2 == 0) {
				if (!enteredText[i].equals(likeText)) {
					assertFalse("Preveiously Entered Data not saved in OnePager Form.");
					break;
				}
			} else {
				if (!enteredText[i].equals(disLikeText)) {
					assertFalse("Preveiously Entered Data not saved in OnePager Form.");
					break;
				}
			}
		}
		if (i == enteredText.length)
			assertTrue("Preveiously Entered Data is saved in OnePager Form.");
	}
}
