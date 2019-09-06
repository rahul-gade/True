package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.CreatedDealPage;
import com.mop.qa.pageobject.DealPipelineAllDeals;
import com.mop.qa.pageobject.EditDealHomePage;
import com.mop.qa.pageobject.HypothesesPage;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestHypothesis extends TestBase{
	private static final Logger LOGGER = Logger.getLogger(TestHypothesis.class.getName());
	
	@Test
	public void testHypothesisPage() throws Exception{
		try {
			//Loading HomePage
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String projectName = rds.getValue("DATA", currentTest, "ProjectName");
			String companyName = rds.getValue("DATA", currentTest, "CompanyName");
			String sector = rds.getValue("DATA", currentTest, "Sector");
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			Thread.sleep(1000);
			
			//Hypothesis Work!
			HypothesesPage hypPage = new HypothesesPage(remoteDriver);
			inst.findOnePagerDeal(remoteDriver);
			
//			CreatedDealPage cdp = new CreatedDealPage(remoteDriver);
//			cdp.checkQuickActoinDDown(remoteDriver);
//			cdp.checkNewDocument(remoteDriver);
			hypPage.createHypothesis(remoteDriver);
//			EditDealHomePage dealhome = new EditDealHomePage(remoteDriver);
//			String impact = rds.getValue("HYPOTHESIS", currentTest, "Impact");
//			String financial = rds.getValue("HYPOTHESIS", currentTest, "Financial");
//			String attraction = rds.getValue("HYPOTHESIS", currentTest, "Attraction");
//			String likeability = rds.getValue("HYPOTHESIS", currentTest, "Likeability");
//			String reason = rds.getValue("HYPOTHESIS", currentTest, "Reason");
//			String probability = rds.getValue("HYPOTHESIS", currentTest, "Probability");
//			String probreason = rds.getValue("HYPOTHESIS", currentTest, "ProbReason");
//			String profile = rds.getValue("HYPOTHESIS", currentTest, "Profile");
//			inst.findOnePagerDeal(remoteDriver);
//			dealhome.editLikeability(remoteDriver, impact, financial, attraction, likeability, reason);
//			dealhome.editProbability(remoteDriver, probability, probreason);
//			dealhome.addContact(remoteDriver, profile);
//			dealhome.validateFolder(remoteDriver, projectName);
//			DealPipelineAllDeals tabs = new DealPipelineAllDeals(remoteDriver);
//			tabs.navigateTabs();
			hypPage.hypothesisDetails(remoteDriver);
			hypPage.newPost(remoteDriver);
			hypPage.newComment(remoteDriver);
			hypPage.editHypothesis(remoteDriver);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
