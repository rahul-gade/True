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
	@FindBy(xpath = "(//div[text()=' Competition ']//a[@class='link-dislike-btn'])[1]")
	private WebElement competitionLikeIcon;
	@FindBy(xpath = "(//div[text()=' Underlying industry growth ']//a[@class='link-dislike-btn'])[1]")
	private WebElement industryLikeIcon;
	@FindBy(xpath = "(//div[text()=' Underlying industry growth ']/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement industryLike;
	@FindBy(xpath = "(//div[text()=' Underlying industry growth ']/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul/li")
	private WebElement industryDisLike;
	@FindBy(xpath = "(//div[text()=' Underlying industry growth ']/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement txtindustryDisLike;
	@FindBy(xpath = "(//div[text()=' Expected Industry Growth ']//a[@class='link-dislike-btn'])[1]")
	private WebElement industryDisLikeIcon;
	@FindBy(xpath = "(//span[text()='NEXT'])[1]/parent::button")
	private WebElement btnnext;
	
	//----------------Company Page------------//
	@FindBy(xpath = "//div[text()=' Customer Value Proposition & MOATS ']/following-sibling::div//label[text()='What We Like']/following-sibling::ul")
	private WebElement customerLike;
	@FindBy(xpath = "//div[text()=' Customer Value Proposition & MOATS ']/following-sibling::div//label[text()='What We Like']/following-sibling::ul/li")
	private WebElement txtcustomerLike;
	@FindBy(xpath = "//div[text()=' Customer Value Proposition & MOATS ']/following-sibling::div//label[text()='What We Dislike']/following-sibling::ul")
	private WebElement customerDisLike;
	@FindBy(xpath = "(//div[text()=' Customer Value Proposition & MOATS ']//a[@class='link-dislike-btn'])[1]")
	private WebElement customerLikeIcon;
	@FindBy(xpath = "//div[text()=' Leadership ']/following-sibling::div//label[text()='What We Like']/following-sibling::ul")
	private WebElement leadershipLike;
	@FindBy(xpath = "//div[text()=' Leadership ']/following-sibling::div//label[text()='What We Dislike']/following-sibling::ul")
	private WebElement leadershipDisLike;
	@FindBy(xpath = "(//div[text()=' Leadership ']//a[@class='link-dislike-btn'])[1]")
	private WebElement leadershipLikeIcon;
		@FindBy(xpath = "//div[text()=' Financial Outcomes ']/following-sibling::div//label[text()='What We Like']/following-sibling::ul")
	private WebElement financialLike;
	@FindBy(xpath = "//div[text()=' Financial Outcomes ']/following-sibling::div//label[text()='What We Dislike']/following-sibling::ul")
	private WebElement financialDisLike;
	@FindBy(xpath = "(//button[@class='btn mat-flat-button'])[2]")
	private WebElement btnnext2;
	
	//----------------Deal Dynamics Page------------//
	@FindBy(xpath = "//div[text()=' Valuation ']/following-sibling::div//label[text()='What We Like']/following-sibling::ul")
	private WebElement valuationLike;
	@FindBy(xpath = "//div[text()=' Valuation ']/following-sibling::div//label[text()='What We Dislike']/following-sibling::ul")
	private WebElement valuationDisLike;
	@FindBy(xpath = "(//div[text()=' Competition ']/following-sibling::div//label[text()='What We Like'])[2]/following-sibling::ul")
	private WebElement dealCompetitionLike;
	@FindBy(xpath = "(//div[text()=' Competition ']/following-sibling::div//label[text()='What We Dislike'])[2]/following-sibling::ul")
	private WebElement dealCompetitionDisLike;
	@FindBy(xpath = "(//div[text()=' Competition ']//a[@class='link-dislike-btn'])[4]")
	private WebElement competition1LikeIcon;
	@FindBy(xpath = "(//div[text()=' Exit strategy ']//a[@class='link-dislike-btn'])[1]")
	private WebElement exitStrategyLikeIcon;
	@FindBy(xpath = "//div[text()=' Exit strategy ']/following-sibling::div//label[text()='What We Like']/following-sibling::ul")
	private WebElement exitStrategyLike;
	@FindBy(xpath = "//div[text()=' Exit strategy ']/following-sibling::div//label[text()='What We Dislike']/following-sibling::ul")
	private WebElement exitStrategyDisLike;
	@FindBy(xpath = "(//span[text()='NEXT'])[3]")
	private WebElement btnnext3;
	
	//----------------TrueNorth Angles Page------------//
	@FindBy(xpath = "//div[text()=' Valuation ']/following-sibling::div//label[text()='What We Like']/following-sibling::ul")
	private WebElement txtTrueNorthAngles;
	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='VIEW ONE PAGER']")
	private WebElement btnViewOnePager;
	
	
	public void enterOnePagerIndustry(RemoteWebDriver driver, String competitionWeLike, String competitionWeDisLike,String industryWeLike, String industryWeDisLike) throws Exception {
		Actions action = new Actions(remoteDriver);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		if(driver.findElements(By.xpath("(//span[text()='NEXT'])[1]/ancestor::form//div[text()=' Competition ']")).size()>0){
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
		action.moveToElement(competitionLikeIcon).click().build().perform();
		}
		//click(competitionLikeIcon, "Competition Like Icon");
		if(driver.findElements(By.xpath("//div[text()=' Underlying industry growth ']")).size()>0){
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
		action.moveToElement(industryLikeIcon).click().build().perform();
		Thread.sleep(100);
		//click(industryDisLikeIcon, "Industry DisLike Icon");
		Thread.sleep(1000);
		if(driver.findElements(By.xpath("//div[text()=' Expected Industry Growth ']")).size()>0){
		action.moveToElement(industryDisLikeIcon).click().build().perform();
		}
		action.moveToElement(btnnext).click().build().perform();
		//click(btnnext, "NEXT");
		Thread.sleep(100);
		}
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
	}
	
	public void enterOnePagerCompany(RemoteWebDriver driver, String customenrWeLike, String customerWeDisLike, String leadershipWeLike, String leadershipWeDisLike, String financialWeLike, String financialWeDisLike) throws Exception {
		Actions action = new Actions(remoteDriver);
		//click(customerLike, "What We Like");
		if(driver.findElements(By.xpath("//div[text()=' Customer Value Proposition & MOATS ']")).size()>0){
		action.moveToElement(customerLike).click().build().perform();
	//	action.moveToElement(customerLike).sendKeys(customenrWeLike);
		enterText(customerLike, customenrWeLike, "What We Like");
		Thread.sleep(1000);
		//click(customerDisLike, "What We Dislike");	
		action.moveToElement(customerDisLike).click().build().perform();
	//	action.moveToElement(customerDisLike).sendKeys(customerWeDisLike);
		enterText(customerDisLike, customerWeDisLike, "What We Dislike");
		action.moveToElement(customerLikeIcon).click().build().perform();
		Thread.sleep(1000);
	//	click(leadershipLike, "What We Like");	
		}
		if(driver.findElements(By.xpath("//div[text()=' Leadership ']")).size()>0){
		action.moveToElement(leadershipLike).click().build().perform();
		enterText(leadershipLike, leadershipWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(leadershipDisLike, "What We Dislike");	
		action.moveToElement(leadershipLike).click().build().perform();
		enterText(leadershipDisLike, leadershipWeDisLike, "What We Dislike");
		Thread.sleep(1000);
	//	click(leadershipLikeIcon, "Leadership Like Icon");	
		action.moveToElement(leadershipLikeIcon).click().build().perform();
		Thread.sleep(1000);
	//	click(financialLike, "What We Like");
		}
		if(driver.findElements(By.xpath("//div[text()=' Financial Outcomes ']")).size()>0){
		action.moveToElement(financialLike).click().build().perform();
		enterText(financialLike, financialWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(financialDisLike, "What We Dislike");	
		action.moveToElement(financialDisLike).click().build().perform();
		enterText(financialDisLike, financialWeDisLike, "What We Dislike");
		Thread.sleep(2000);
		}
	//	click(btnnext2, "NEXT");
		action.moveToElement(btnnext2).click().build().perform();
		Thread.sleep(1000);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
	}
	
	public void enterOnePagerDealDynamics(RemoteWebDriver driver, String customenrWeLike, String customerWeDisLike, String leadershipWeLike, String leadershipWeDisLike, String financialWeLike, String financialWeDisLike) throws Exception {
		//click(customerLike, "What We Like");
		Actions action = new Actions(remoteDriver);
		if(driver.findElements(By.xpath("//div[text()=' Valuation ']")).size()>0){
		action.moveToElement(valuationLike).click().build().perform();
		enterText(valuationLike, customenrWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(customerDisLike, "What We Dislike");	
		action.moveToElement(valuationDisLike).click().build().perform();
		enterText(valuationDisLike, customerWeDisLike, "What We Dislike");
		Thread.sleep(1000);
	//	click(leadershipLike, "What We Like");	
		}
		if(driver.findElements(By.xpath("//div[text()=' Deal Dynamics ']/ancestor::div//div[text()=' Competition ']")).size()>0){
		action.moveToElement(dealCompetitionLike).click().build().perform();
		enterText(dealCompetitionLike, leadershipWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(leadershipDisLike, "What We Dislike");	
		action.moveToElement(dealCompetitionDisLike).click().build().perform();
		enterText(dealCompetitionDisLike, leadershipWeDisLike, "What We Dislike");
		Thread.sleep(1000);
	//	click(leadershipLikeIcon, "Leadership Like Icon");	
		action.moveToElement(competition1LikeIcon).click().build().perform();
		Thread.sleep(1000);
	//	click(financialLike, "What We Like");	
		}
		if(driver.findElements(By.xpath("//div[text()=' Exit strategy ']")).size()>0){
		action.moveToElement(exitStrategyLike).click().build().perform();
		enterText(exitStrategyLike, financialWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(financialDisLike, "What We Dislike");	
		action.moveToElement(exitStrategyDisLike).click().build().perform();
		enterText(exitStrategyDisLike, financialWeDisLike, "What We Dislike");
		Thread.sleep(1000);
		action.moveToElement(exitStrategyLikeIcon).click().build().perform();
		}
	//	click(btnnext3, "NEXT");
		action.moveToElement(btnnext3).click().build().perform();
		Thread.sleep(1000);
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
	}
	public void entertrueNorthAngles(RemoteWebDriver driver) throws Exception {
			Thread.sleep(1000);
			do {
				Thread.sleep(1000);
	     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
			//click(btnSubmit, "SUBMIT");
			Actions action = new Actions(remoteDriver);
			action.moveToElement(btnSubmit).click().build().perform();
			assertTrue("Successfully Submitted One Pager");
			Thread.sleep(2000);
			do {
				Thread.sleep(1000);
	     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
			click(btnViewOnePager, "View One Pager");
			assertTrue("Successfully clicked on View One Pager button");
		}
}
