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
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement competitionLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement txtcompetitionLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement competitionDisLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div//a[@class='link-dislike-btn'])[1]")
	private WebElement competitionLikeIcon;
	
	@FindBy(xpath = "(//span[text()='Underlying industry growth']/parent::div//a[@class='link-dislike-btn'])[1]")
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
	
	//----------------Company Page------------//
	@FindBy(xpath = "(//span[text()='Customer Value Proposition & MOATS']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement customerLike;
	@FindBy(xpath = "(//span[text()='Customer Value Proposition & MOATS']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement txtcustomerLike;
	@FindBy(xpath = "(//span[text()='Customer Value Proposition & MOATS']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement customerDisLike;
	@FindBy(xpath = "(//span[text()='Customer Value Proposition & MOATS']/parent::div//a[@class='link-dislike-btn'])[1]")
	private WebElement customerLikeIcon;
	
	@FindBy(xpath = "(//span[text()='Leadership']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement leadershipLike;
	@FindBy(xpath = "(//span[text()='Leadership']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement leadershipDisLike;
	@FindBy(xpath = "(//span[text()='Leadership']/parent::div//a[@class='link-dislike-btn'])[1]")
	private WebElement leadershipLikeIcon;
	
	@FindBy(xpath = "(//span[text()='Company Deal dynamics']/parent::div/following-sibling::div//label[text()='What We Like'])[1]/following-sibling::ul")
	private WebElement compDynamicsLike;
	@FindBy(xpath = "(//span[text()='Company Deal dynamics']/parent::div/following-sibling::div//label[text()='What We Dislike'])[1]/following-sibling::ul")
	private WebElement compDynamicsDislike;
	
	@FindBy(xpath = "(//span[text()='NEXT'])[3]/parent::button")
	private WebElement btnnext2;
	
	//----------------Deal Dynamics Page------------//
	@FindBy(xpath = "//div[text()='Valuation']/following-sibling::div//label[text()='What We Like']/following-sibling::ul")
	private WebElement valuationLike;
	@FindBy(xpath = "//div[text()='Valuation']/following-sibling::div//label[text()='What We Dislike']/following-sibling::ul")
	private WebElement valuationDisLike;
	
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Like'])[2]/following-sibling::ul")
	private WebElement dealCompetitionLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div/following-sibling::div//label[text()='What We Dislike'])[2]/following-sibling::ul")
	private WebElement dealCompetitionDisLike;
	@FindBy(xpath = "(//span[text()='Competition']/parent::div//a[@class='link-dislike-btn'])[4]")
	private WebElement competition1LikeIcon;
	
	@FindBy(xpath = "(//span[text()='Exit strategy']/parent::div//a[@class='link-dislike-btn'])[1]")
	private WebElement exitStrategyLikeIcon;
	@FindBy(xpath = "(//span[text()='Exit strategy']/parent::div/following-sibling::div//label[text()='What We Like'])/following-sibling::ul")
	private WebElement exitStrategyLike;
	@FindBy(xpath = "(//span[text()='Exit strategy']/parent::div/following-sibling::div//label[text()='What We Dislike'])/following-sibling::ul")
	private WebElement exitStrategyDisLike;
	
	@FindBy(xpath = "(//span[text()='NEXT'])[4]")
	private WebElement btnnext3;
	
	//----------------TrueNorth Angles Page------------//
	@FindBy(xpath = "//label[text()='True North Angles']/following-sibling::ul")
	private WebElement txtTrueNorthAngles;
	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='VIEW ONE PAGER']")
	private WebElement btnViewOnePager;
	
	//----------------TIC Page------------//
	@FindBy(xpath = "//div[text()=' TIC ']/ancestor::div[2]//span[text()='NEXT']/parent::button")
	private WebElement btnTICNext; 
	
	
	public void enterOnePagerIndustry(RemoteWebDriver driver, String competitionWeLike, String competitionWeDisLike,String industryWeLike, String industryWeDisLike) throws Exception {
		Actions action = new Actions(remoteDriver);
		Thread.sleep(2000);
		action.moveToElement(btnTICNext).click().build().perform();
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		if(driver.findElements(By.xpath("(//span[text()='NEXT'])[2]/ancestor::form//span[text()='Competition']")).size()>0){
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
		if(driver.findElements(By.xpath("//span[text()='Underlying industry growth']")).size()>0){
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
		if(driver.findElements(By.xpath("//span[text()='Expected Industry Growth']")).size()>0){
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
		if(driver.findElements(By.xpath("//span[text()='Customer Value Proposition & MOATS']")).size()>0){
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
		if(driver.findElements(By.xpath("//span[text()='Leadership']")).size()>0){
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
	//	click(compDynamicsLike, "What We Like");
		}
		if(driver.findElements(By.xpath("//span[text()='Financial Outcomes']")).size()>0){
		action.moveToElement(compDynamicsLike).click().build().perform();
		enterText(compDynamicsLike, financialWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(compDynamicsDislike, "What We Dislike");	
		action.moveToElement(compDynamicsDislike).click().build().perform();
		enterText(compDynamicsDislike, financialWeDisLike, "What We Dislike");
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
		if(driver.findElements(By.xpath("//span[text()='Valuation']")).size()>0){
		action.moveToElement(valuationLike).click().build().perform();
		enterText(valuationLike, customenrWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(customerDisLike, "What We Dislike");	
		action.moveToElement(valuationDisLike).click().build().perform();
		enterText(valuationDisLike, customerWeDisLike, "What We Dislike");
		Thread.sleep(1000);
	//	click(leadershipLike, "What We Like");	
		}
		if(driver.findElements(By.xpath("(//div[text()=' Deal Dynamics ']/ancestor::div//span[text()='Competition'])[2]")).size()>0){
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
	//	click(compDynamicsLike, "What We Like");	
		}
		if(driver.findElements(By.xpath("//span[text()='Exit strategy']")).size()>0){
		action.moveToElement(exitStrategyLike).click().build().perform();
		enterText(exitStrategyLike, financialWeLike, "What We Like");
		Thread.sleep(1000);
	//	click(compDynamicsDislike, "What We Dislike");	
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
