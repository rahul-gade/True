package com.mop.qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.testbase.PageBase;
import com.mop.qa.testbase.TestBase;

public class InvestmentHome extends PageBase{
	public InvestmentHome(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	public ReadDataSheet rds = new ReadDataSheet();
	int i = 1;
	TestBase tBase = new TestBase();
	public String currentTest =  tBase.currentTest;	
	@FindBy(xpath = "//div[@class='mat-form-field-infix']")
	private WebElement domainOptions;
	@FindBy(xpath = "//span[contains(text(),'PROJECT')]")
	private WebElement selectDomain;
	@FindBy(xpath = "//input[@name='loginfmt']")
	private WebElement userName;
	@FindBy(xpath = "//input[@name='passwd']")
	private WebElement password;
	@FindBy(xpath = "//input[@id='idSIButton9']")
	private WebElement next;
	@FindBy(xpath = "//input[@id='idBtn_Back']")
	private WebElement no;
	@FindBy(xpath = "(//div[@class='mat-select-arrow'])[1]")
	private WebElement dropdown;
	@FindBy(xpath = "//span[contains(text(),'TALENT')]")
	private WebElement talent;
	@FindBy(xpath = "//span[contains(text(),'INVESTMENT')]")
	private WebElement investment;
	@FindBy(xpath = "(//h3[text()='DRAFTS']/following-sibling::div/div)[1]")
	private WebElement draftCard;
	@FindBy(xpath = "(//div[@class='action'])[1]//a")
	private WebElement btnDelete;
	@FindBy(xpath = "//span[text()='GO BACK']")
	private WebElement btnBack;
	@FindBy(xpath = "//span[text()='DELETE DEAL']")
	private WebElement btnDeleteDeal;
	@FindBy(xpath = "//a[@class='close-btn']")
	private WebElement btnClose;
	@FindBy(xpath = "//a[text()='Deal Details']")
	private WebElement tabDealDetails;
	
	String txtProjectName = "(//h3[text()='PLACEHOLDER']/following-sibling::div/div)[1]/div[@class='card-header']";
	String txtCompanytName = "(//h3[text()='PLACEHOLDER']/following-sibling::div/div)[1]//h4";
	String txtSector = "(//h3[text()='PLACEHOLDER']/following-sibling::div/div)[1]//h5";
	String txtSize = "(//h3[text()='PLACEHOLDER']/following-sibling::div/div)[1]//div[@class='dollar ng-star-inserted']";
	String txtStake = "(//h3[text()='PLACEHOLDER']/following-sibling::div/div)[1]//div[@class='minorty']";
	
	
	String totalCards = "//div[@class='card ng-star-inserted']";
	String projectName = "(//div[@class='card-header' and text()=' PLACEHOLDER '])["+i+"]";
	String companyName = "(//h4[text()='PLACEHOLDER'])["+i+"]";
	String sector  = "(//h5[text()='PLACEHOLDER'])["+i+"]";
	String ownerImage = "(//div[@class='img']/img)["+i+"]";
	String liveDeal = "(//h3[text()='LIVE DEALS']/following-sibling::div/div//div[text()=' PLACEHOLDER '])[1]";
	String card = "(//h3[text()='LIVE DEALS']/following-sibling::div/div)[1]";
			
	public void launchApp(String url, String uname, String pwd) throws Exception {
		enterUrl(url);
		Thread.sleep(2000);
		click(userName, "User Name");
		enterText(userName, uname, "UserName");
		click(next, "Next");
		Thread.sleep(2000);
		click(password, "Password");
		enterText(password, pwd, "Password");
		click(next, "SignIn");
		Thread.sleep(2000);
		click(no, "Dont keep Signed-in");
		Thread.sleep(3000);
		waitForPageLoad();
		click(dropdown, "Module Dropdown");
		String module;
		if(url.contains("Talent-Acquisition")){
			module = "TA";
			click(talent, "Talent Acquisition");
			Thread.sleep(1000);
		}else if(url.contains("Investment-Management")){
			module = "IM";
			click(investment, "Investment");
			Thread.sleep(1000);
		}
	}
	public void findDeal(RemoteWebDriver driver, String projName, String compName, String sect) throws Exception {
		Thread.sleep(3000);
		String pName = driver.findElement(By.xpath(projectName.replace("PLACEHOLDER", projName))).getText();
		String cName = driver.findElement(By.xpath(companyName.replace("PLACEHOLDER", compName))).getText();
		String sec = driver.findElement(By.xpath(sector.replace("PLACEHOLDER", sect))).getText();
		int total = driver.findElements(By.xpath(totalCards)).size();
		for (i=1;i<=total;i++) {
			if(pName.contains(projName) && cName.contains(compName) && sec.contains(sect)) {
				Thread.sleep(2000);
				driver.findElement(By.xpath(projectName.replace("PLACEHOLDER", projName))).click();
				assertTrue("Project being edited is " +pName );
				do {
					Thread.sleep(1000);
		     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
				break;
			}else {
				assertFalse("Project Not Clicked");
			}
		}
	}
	
	public void findLiveDeal(RemoteWebDriver driver, String projName, String compName, String sect) throws Exception {
		//verifyNameAndTime(remoteDriver, projName);	
		do {
			Thread.sleep(1000);
     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
		int size = driver.findElements(By.xpath(("//h3[text()='LIVE DEALS']/following-sibling::div/div"))).size();
		for(int i = 1;i<=size;i++) {
		String pName = driver.findElement(By.xpath("(//h3[text()='LIVE DEALS']/following-sibling::div//div[@class='card-header'])["+i+"]")).getText();
		String cName = driver.findElement(By.xpath("(//h3[text()='LIVE DEALS']/following-sibling::div//h4)["+i+"]")).getText();
		String sec = driver.findElement(By.xpath("(//h3[text()='LIVE DEALS']/following-sibling::div//h5)["+i+"]")).getText();
		String ownerImage = driver.findElement(By.xpath("(//div[@class='img']/img)["+i+"]")).getAttribute("title");
		if(pName.trim().equalsIgnoreCase(projName) && cName.trim().equalsIgnoreCase(compName) && sec.trim().equalsIgnoreCase(sect) && !ownerImage.isEmpty()) {
			driver.findElement(By.xpath("(//h3[text()='LIVE DEALS']/following-sibling::div//div[@class='card-header'])["+i+"]")).click();
			assertTrue("Project being edited is " +pName );
			do {
				Thread.sleep(1000);
	     	} while(driver.findElements(By.xpath("//div/div/img")).size()>0) ;
			break;
		}
		}
	}
	public void verifyNameAndTime(RemoteWebDriver driver, String projName) throws Exception {
		String name = driver.findElement(By.xpath("//h4")).getText();
		String time = driver.findElement(By.xpath("//h4/preceding-sibling::ul/li[@class='ng-star-inserted']")).getText();
		if(name.contains(projName)){
			assertTrue("Project name displayed is " +name);
		}else{
			assertFalse("Project name displayed is " +name);
		}
		if(!time.isEmpty()){
			assertTrue("Project " +time);
		}else{
			assertFalse("Saved time is not displayed");
		}
	}
	
	public void editClose() throws Exception {
			click(btnClose, "Close Button");
			Thread.sleep(2000);
			if(tabDealDetails.isDisplayed()){
				assertTrue("Close Button is clicked and user is navigated back to the deal details page.");
			}else{
				assertFalse("Close Button is not clicked.");
			}
	}
	public void deleteDeal(RemoteWebDriver driver, String projName, String compName, String sect) throws Exception {
		Thread.sleep(2000);
		int size = driver.findElements(By.xpath(("//h3[text()='DRAFTS']/following-sibling::div/div"))).size();
		for(int i = 1;i<=size;i++) {
		String pName = driver.findElement(By.xpath("(//h3[text()='DRAFTS']/following-sibling::div//div[@class='card-header'])["+i+"]")).getText();
		String cName = driver.findElement(By.xpath("(//h3[text()='DRAFTS']/following-sibling::div//h4)["+i+"]")).getText();
		String sec = driver.findElement(By.xpath("(//h3[text()='DRAFTS']/following-sibling::div//h5)["+i+"]")).getText();
		String ownerImage = driver.findElement(By.xpath("(//div[@class='img']/img)["+i+"]")).getAttribute("title");
		if(pName.trim().equalsIgnoreCase(projName) && cName.trim().equalsIgnoreCase(compName) && sec.trim().equalsIgnoreCase(sect) && !ownerImage.isEmpty()) {
			driver.findElement(By.xpath("(//h3[text()='DRAFTS']/following-sibling::div//div[@class='action']/a)["+i+"]")).click();
			assertTrue("Delete Button is clicked");
			Thread.sleep(2000);
			click(btnDeleteDeal, "Delete Deal");
			Thread.sleep(2000);
			 pName = driver.findElement(By.xpath("(//h3[text()='DRAFTS']/following-sibling::div//div[@class='card-header'])["+i+"]")).getText();
			 cName = driver.findElement(By.xpath("(//h3[text()='DRAFTS']/following-sibling::div//h4)["+i+"]")).getText();
			 sec = driver.findElement(By.xpath("(//h3[text()='DRAFTS']/following-sibling::div//h5)["+i+"]")).getText();
			if(pName.trim().equalsIgnoreCase(projName) && cName.trim().equalsIgnoreCase(compName) && sec.trim().equalsIgnoreCase(sect) && !ownerImage.isEmpty()){
				assertFalse("Project is not Deleted");
			}else{
				assertTrue("Project is Deleted");
			}
			break;
		}
		}
		
	}
	
	public void verifyDraftsDeals(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		String	pName = txtProjectName.replace("PLACEHOLDER", "DRAFTS");
		String	cName = txtCompanytName.replace("PLACEHOLDER", "DRAFTS");
		String	sec = txtSector.replace("PLACEHOLDER", "DRAFTS");	
		String projectName = driver.findElement(By.xpath(pName)).getText();
		String companyName =driver.findElement(By.xpath(cName)).getText();
		String sectorName =driver.findElement(By.xpath(sec)).getText();
		if(!projectName.isEmpty()&&!companyName.isEmpty()&&!sectorName.isEmpty()&&btnDelete.isDisplayed()){
		assertTrue("Draft Deals Details");
		assertTrue("Project Name is Displayed as "+projectName);
		assertTrue("Company Name is Displayed as "+companyName);
		assertTrue("Sector is Displayed as "+sectorName);
		assertTrue("Delete button is Displayed.");
		}
		else{
			if(projectName.isEmpty()){
			assertFalse("Project Name is not Displayed");
			}
			if(companyName.isEmpty()){
				assertFalse("Company Name is not Displayed");
				}
			if(sectorName.isEmpty()){
				assertFalse("Sector is not Displayed");
				}
			if(btnDelete.isDisplayed()){
				assertFalse("Delete button is not Displayed");
				}
		}
	}
	
	public void verifyLiveAndPastDeals(RemoteWebDriver driver) throws Exception {
		String pName =  null;
		String cName = null;
		String sec = null;
		String textSize = null;
		String textStake = null;
		for(int i =0; i<2;i++){
			Thread.sleep(2000);
			if(i==0){
		pName = txtProjectName.replace("PLACEHOLDER", "LIVE DEALS");
		cName = txtCompanytName.replace("PLACEHOLDER", "LIVE DEALS");
		sec = txtSector.replace("PLACEHOLDER", "LIVE DEALS");
		textSize = txtSize.replace("PLACEHOLDER", "LIVE DEALS");
		textStake = txtStake.replace("PLACEHOLDER", "LIVE DEALS");
		String ownerImage = driver.findElement(By.xpath("((//h3[text()='LIVE DEALS']/following-sibling::div/div)[1]//div[@class='img']/img)[1]")).getAttribute("title");
			}else{
				pName = txtProjectName.replace("PLACEHOLDER", "PAST DEALS");
				cName = txtCompanytName.replace("PLACEHOLDER", "PAST DEALS");
				sec = txtSector.replace("PLACEHOLDER", "PAST DEALS");
				textSize = txtSize.replace("PLACEHOLDER", "PAST DEALS");
				textStake = txtStake.replace("PLACEHOLDER", "PAST DEALS");
				String ownerImage = driver.findElement(By.xpath("((//h3[text()='PAST DEALS']/following-sibling::div/div)[1]//div[@class='img']/img)[1]")).getAttribute("title");
			}
		String projectName = driver.findElement(By.xpath(pName)).getText();
		String companyName =driver.findElement(By.xpath(cName)).getText();
		String sectorName =driver.findElement(By.xpath(sec)).getText();
		String dealSize = driver.findElement(By.xpath(textSize)).getText();
		String dealStake = driver.findElement(By.xpath(textStake)).getText();
		if(!projectName.isEmpty()&&!companyName.isEmpty()&&!sectorName.isEmpty()&&!dealSize.isEmpty()&&!ownerImage.isEmpty()){
		if(i==0){
			assertTrue("Live Deal Details");
		}else{
			assertTrue("Past Deal Details");
		}
		assertTrue("Project Name is Displayed as "+projectName);
		assertTrue("Company Name is Displayed as "+companyName);
		assertTrue("Sector is Displayed as "+sectorName);
		assertTrue("Deal Size is Displayed as "+dealSize);
		assertTrue("Deal Stake is Displayed as "+dealStake);
		assertTrue("Deal Team is displayed");
		}
		else{
			if(projectName.isEmpty()){
			assertFalse("Project Name is not Displayed");
			}
			if(companyName.isEmpty()){
				assertFalse("Company Name is not Displayed");
				}
			if(sectorName.isEmpty()){
				assertFalse("Sector is not Displayed");
				}
			if(dealSize.isEmpty()){
				assertFalse("Deal Size is not Displayed");
				}
			if(dealStake.isEmpty()){
				assertFalse("Deal Stake is not Displayed");
				}
			}
		}
	}
}
