package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class OnePager extends PageBase{
	public OnePager(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	//----------------Industry Page------------//
	@FindBy(xpath = "(//div[text()=' Competition ']/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul/li")
	private WebElement competitionLike;
	@FindBy(xpath = "(//div[text()=' Competition ']/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement txtcompetitionLike;
	@FindBy(xpath = "(//div[text()=' Competition ']/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement competitionDisLike;
	@FindBy(xpath = "(//a[@class='link-dislike-btn'])[1]")
	private WebElement competitionLikeIcon;
	@FindBy(xpath = "(//div[text()=' Underlying industry growth ']/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement industryLike;
	@FindBy(xpath = "(//div[text()=' Underlying industry growth ']/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li")
	private WebElement industryDisLike;
	@FindBy(xpath = "(//div[text()=' Underlying industry growth ']/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement txtindustryDisLike;
	@FindBy(xpath = "(//a[@class='link-dislike-btn'])[3]")
	private WebElement industryDisLikeIcon;
	@FindBy(xpath = "(//span[text()='NEXT'])[1]/parent::button")
	private WebElement btnnext;
	
	//----------------Company Page------------//
	/*@FindBy(xpath = "//div[text()=' Customer Value Proposition & MOATS ']/following-sibling::div//label[text()='What We Like']")
	private WebElement customerLike;
	@FindBy(xpath = "//div[text()=' Customer Value Proposition & MOATS ']/following-sibling::div//label[text()='What We Dislike']")
	private WebElement customerDisLike;
	@FindBy(xpath = "//div[text()=' Leadership ']/following-sibling::div//label[text()='What We Like']")
	private WebElement leadershipLike;
	@FindBy(xpath = "//div[text()=' Leadership ']/following-sibling::div//label[text()='What We Dislike']")
	private WebElement leadershipDisLike;
	@FindBy(xpath = "(//a[@class='link-dislike-btn'])[5]")
	private WebElement leadershipLikeIcon;
	@FindBy(xpath = "//div[text()=' Financial Outcomes ']/following-sibling::div//label[text()='What We Like']")
	private WebElement financialLike;
	@FindBy(xpath = "//div[text()=' Financial Outcomes ']/following-sibling::div//label[text()='What We Dislike']")
	private WebElement financialDisLike;*/
	@FindBy(xpath = "(//span[text()='NEXT'])[2]")
	private WebElement btnnext2;
	
	//----------------Deal Dynamics Page------------//
	/*@FindBy(xpath = "//div[text()=' Valuation ']/following-sibling::div//label[text()='What We Like']")
	private WebElement valuationLike;
	@FindBy(xpath = "//div[text()=' Valuation ']/following-sibling::div//label[text()='What We Dislike']")
	private WebElement valuationDisLike;
	@FindBy(xpath = "(//div[text()=' Competition ']/following-sibling::div//label[text()='What We Like'])[2]")
	private WebElement dealCompetitionLike;
	@FindBy(xpath = "(//div[text()=' Competition ']/following-sibling::div//label[text()='What We Dislike'])[2]")
	private WebElement dealCompetitionDisLike;
	@FindBy(xpath = "(//a[@class='link-dislike-btn'])[12]")
	private WebElement exitStrategyLikeIcon;
	@FindBy(xpath = "//div[text()=' Exit strategy ']/following-sibling::div//label[text()='What We Like']")
	private WebElement exitStrategyLike;
	@FindBy(xpath = "//div[text()=' Exit strategy ']/following-sibling::div//label[text()='What We Dislike']")
	private WebElement exitStrategyDisLike;*/
	@FindBy(xpath = "(//span[text()='NEXT'])[3]")
	private WebElement btnnext3;
	
	//----------------TrueNorth Angles Page------------//
	@FindBy(xpath = "//div[text()=' Valuation ']/following-sibling::div//label[text()='What We Like']")
	private WebElement txtTrueNorthAngles;
	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='VIEW ONE PAGER']")
	private WebElement btnViewOnePager;
	
	
	public void enterOnePagerIndustry(RemoteWebDriver driver, String competitionWeLike, String competitionWeDisLike,String industryWeLike, String industryWeDisLike) throws Exception {
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(competitionLike, "What We Like");	
		txtcompetitionLike.sendKeys(Keys.ENTER);
		Thread.sleep(100);
		enterText(txtcompetitionLike, competitionWeLike, "What We Like");
		txtcompetitionLike.sendKeys(Keys.ENTER);
		Thread.sleep(100);
		click(competitionDisLike, "What We Dislike");
		Thread.sleep(100);
		competitionDisLike.sendKeys(Keys.ENTER);
		enterText(competitionDisLike, competitionWeDisLike, "What We Dislike");
		competitionDisLike.sendKeys(Keys.ENTER);
		Thread.sleep(100);
		click(competitionLikeIcon, "Competition Like Icon");	
		Thread.sleep(100);
		click(industryLike, "What We Like");	
		Thread.sleep(100);
		industryLike.sendKeys(Keys.ENTER);
		enterText(industryLike, industryWeLike, "What We Like");
		industryLike.sendKeys(Keys.ENTER);
		Thread.sleep(100);
		click(industryDisLike, "What We Dislike");
		txtindustryDisLike.sendKeys(Keys.ENTER);
		Thread.sleep(100);
		enterText(txtindustryDisLike, industryWeDisLike, "What We Dislike");
		txtindustryDisLike.sendKeys(Keys.ENTER);
		Thread.sleep(200);
	//	click(industryDisLikeIcon, "Industry DisLike Icon");	
	//	Thread.sleep(1000);
		Actions action = new Actions(remoteDriver);
		action.moveToElement(btnnext).click().build().perform();
		click(btnnext, "NEXT");
		Thread.sleep(100);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
	}
	
	public void enterOnePagerCompany(RemoteWebDriver driver, String customenrWeLike, String customerWeDisLike, String leadershipWeLike, String leadershipWeDisLike, String financialWeLike, String financialWeDisLike) throws Exception {
	/*	click(customerLike, "What We Like");	
		enterText(customerLike, customenrWeLike, "What We Like");
		Thread.sleep(1000);
		click(customerDisLike, "What We Dislike");	
		enterText(customerDisLike, customerWeDisLike, "What We Dislike");
		Thread.sleep(1000);
		click(leadershipLike, "What We Like");	
		enterText(leadershipLike, leadershipWeLike, "What We Like");
		Thread.sleep(1000);
		click(leadershipDisLike, "What We Dislike");	
		enterText(leadershipDisLike, leadershipWeDisLike, "What We Dislike");
		Thread.sleep(1000);
		click(leadershipLikeIcon, "Leadership Like Icon");	
		Thread.sleep(1000);
		click(financialLike, "What We Like");	
		enterText(financialLike, financialWeLike, "What We Like");
		Thread.sleep(1000);
		click(financialDisLike, "What We Dislike");	
		enterText(financialDisLike, financialWeDisLike, "What We Dislike");*/
		Thread.sleep(2000);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(btnnext2, "NEXT");
		Thread.sleep(2000);
	}
	
	public void enterOnePagerDealDynamics(RemoteWebDriver driver, String customenrWeLike, String customerWeDisLike, String leadershipWeLike, String leadershipWeDisLike, String financialWeLike, String financialWeDisLike) throws Exception {
	/*	click(customerLike, "What We Like");	
		enterText(customerLike, customenrWeLike, "What We Like");
		Thread.sleep(1000);
		click(customerDisLike, "What We Dislike");	
		enterText(customerDisLike, customerWeDisLike, "What We Dislike");
		Thread.sleep(1000);
		click(leadershipLike, "What We Like");	
		enterText(leadershipLike, leadershipWeLike, "What We Like");
		Thread.sleep(1000);
		click(leadershipDisLike, "What We Dislike");	
		enterText(leadershipDisLike, leadershipWeDisLike, "What We Dislike");
		Thread.sleep(1000);
		click(leadershipLikeIcon, "Leadership Like Icon");	
		Thread.sleep(1000);
		click(financialLike, "What We Like");	
		enterText(financialLike, financialWeLike, "What We Like");
		Thread.sleep(1000);
		click(financialDisLike, "What We Dislike");	
		enterText(financialDisLike, financialWeDisLike, "What We Dislike");*/
		Thread.sleep(1000);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		click(btnnext3, "NEXT");
		Thread.sleep(2000);
	}
	public void entertrueNorthAngles(RemoteWebDriver driver) throws Exception {
			Thread.sleep(1000);
			do {
				Thread.sleep(1000);
	     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
			click(btnSubmit, "SUBMIT");
			assertTrue("Successfully Submitted One Pager");
			Thread.sleep(2000);
			do {
				Thread.sleep(1000);
	     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
			click(btnViewOnePager, "View One Pager");
			assertTrue("Successfully clicked on View One Pager button");
		}
}
