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

			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			Thread.sleep(1000);
			inst.findOnePagerDeal(remoteDriver);
			
			//Hypothesis and Recent Activities Work!
			CreatedDealPage cdp = new CreatedDealPage(remoteDriver);
			HypothesesPage hypPage = new HypothesesPage(remoteDriver);
			cdp.testRecentActivities(remoteDriver);
			hypPage.createHypothesis(remoteDriver);
			cdp.testRecentActivities(remoteDriver);
			hypPage.hypothesisDetails(remoteDriver);
			cdp.testRecentActivities(remoteDriver);
			hypPage.newPost(remoteDriver);
			cdp.testRecentActivities(remoteDriver);
			hypPage.newComment(remoteDriver);
			cdp.testRecentActivities(remoteDriver);
			hypPage.editHypothesis(remoteDriver);
			cdp.testRecentActivities(remoteDriver);
			hypPage.editPost(remoteDriver);
			hypPage.deletePost(remoteDriver);
			hypPage.SUPverify(remoteDriver);
			hypPage.deleteAnyHyp(remoteDriver);
			cdp.testRecentActivities(remoteDriver);
			
			//Likeability-Probability & Folder Validations.
			String projectName = rds.getValue("DATA", currentTest, "ProjectName");
			String impact = rds.getValue("HYPOTHESIS", currentTest, "Impact");
			String financial = rds.getValue("HYPOTHESIS", currentTest, "Financial");
			String attraction = rds.getValue("HYPOTHESIS", currentTest, "Attraction");
			String likeability = rds.getValue("HYPOTHESIS", currentTest, "Likeability");
			String reason = rds.getValue("HYPOTHESIS", currentTest, "Reason");
			String probability = rds.getValue("HYPOTHESIS", currentTest, "Probability");
			String probreason = rds.getValue("HYPOTHESIS", currentTest, "ProbReason");
			String profile = rds.getValue("HYPOTHESIS", currentTest, "Profile");
			EditDealHomePage dealhome = new EditDealHomePage(remoteDriver);
			dealhome.editLikeability(remoteDriver, impact, financial, attraction, likeability, reason);
			dealhome.editProbability(remoteDriver, probability, probreason);
			dealhome.addContact(remoteDriver, profile);
			dealhome.validateFolder(remoteDriver, projectName);
//=====================================================================================================FURTHER PROCESS ON DETAILS PAGE...
			
//			String companyName = rds.getValue("DATA", currentTest, "CompanyName");
//			String sector = rds.getValue("DATA", currentTest, "Sector");
//			EditDealHomePage dealhome = new EditDealHomePage(remoteDriver);
//			inst.findOnePagerDeal(remoteDriver);
//			DealPipelineAllDeals tabs = new DealPipelineAllDeals(remoteDriver);
//			tabs.navigateTabs();
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
