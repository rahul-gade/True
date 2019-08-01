package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.CreateNewDocument;
import com.mop.qa.pageobject.CreatedDealPage;
import com.mop.qa.pageobject.DealPipelineAllDeals;
import com.mop.qa.pageobject.EditDealHomePage;
import com.mop.qa.pageobject.HypothesesPage;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.pageobject.OnePager;
import com.mop.qa.pageobject.OnePagerSnapshotPage;
import com.mop.qa.testbase.TestBase;

public class TestOnePager extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestOnePager.class.getName());
	@Test
	public void enterIndustryDetails() throws Exception  {
	 try{
		 InvestmentHome inst = new InvestmentHome(remoteDriver);
		 String projectName = rds.getValue("DATA", currentTest, "ProjectName");
		 String companyName = rds.getValue("DATA", currentTest, "CompanyName");
		 String sector = rds.getValue("DATA", currentTest, "Sector");
		 String startURL = rds.getValue("DATA", currentTest, "URL");
		 String uname = rds.getValue("DATA", currentTest, "UserName");
		 String pwd = rds.getValue("DATA", currentTest, "Password");
		 inst.launchApp(startURL, uname, pwd);
		 Thread.sleep(1000);
		 inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
		 OnePager pager = new OnePager(remoteDriver);
		 CreatedDealPage cdp = new CreatedDealPage(remoteDriver);
		 cdp.createNewDocument(remoteDriver);
		 CreateNewDocument cnd = new CreateNewDocument(remoteDriver);
		 String document = rds.getValue("HYPOTHESIS", currentTest, "DocumentName");
		 cnd.createNewDocument(remoteDriver, document); 
		 String competitionWeLike = rds.getValue("HYPOTHESIS", currentTest, "CompetitionLike");
		 String competitionWeDisLike = rds.getValue("HYPOTHESIS", currentTest, "CompetitionDisLike");
		 String industryWeLike = rds.getValue("HYPOTHESIS", currentTest, "IndustryLike");
		 String industryWeDisLike = rds.getValue("HYPOTHESIS", currentTest, "IndustryDisLike");
		 pager.enterOnePagerIndustry(remoteDriver, competitionWeLike, competitionWeDisLike, industryWeLike, industryWeDisLike);
		 pager.enterOnePagerCompany(remoteDriver, competitionWeLike, competitionWeDisLike, industryWeDisLike, industryWeLike, industryWeLike, industryWeLike);
		 pager.enterOnePagerDealDynamics(remoteDriver, competitionWeLike, competitionWeLike, competitionWeLike, competitionWeLike, competitionWeLike, competitionWeLike);
		 pager.entertrueNorthAngles(remoteDriver);
		 OnePagerSnapshotPage snap = new OnePagerSnapshotPage(remoteDriver);
		 snap.navigateTabs();
		 HypothesesPage hyppage = new HypothesesPage(remoteDriver);
		 hyppage.createHypothesis(remoteDriver);
		 inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
		 EditDealHomePage dealhome = new EditDealHomePage(remoteDriver);
		 String impact = rds.getValue("HYPOTHESIS", currentTest, "Impact");
		 String financial = rds.getValue("HYPOTHESIS", currentTest, "Financial");
		 String attraction = rds.getValue("HYPOTHESIS", currentTest, "Attraction");
		 String likeability = rds.getValue("HYPOTHESIS", currentTest, "Likeability");
		 String reason = rds.getValue("HYPOTHESIS", currentTest, "Reason");
		 String probability = rds.getValue("HYPOTHESIS", currentTest, "Probability");
		 String probreason = rds.getValue("HYPOTHESIS", currentTest, "ProbReason");
		 String profile = rds.getValue("HYPOTHESIS", currentTest, "Profile");
		 dealhome.editLikeability(remoteDriver, impact, financial, attraction, likeability, reason);
		 dealhome.editProbability(remoteDriver, probability, probreason);
		 dealhome.addContact(remoteDriver, profile);
		 dealhome.validateFolder(remoteDriver, projectName);
		 DealPipelineAllDeals tabs = new DealPipelineAllDeals(remoteDriver);
		 tabs.navigateTabs();
	 }catch(Exception e){
		 LOGGER.info(e);
		 }
 }
}
