package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestBizInsights extends TestBase{
	public static final Logger LOGGER = Logger.getLogger(TestBizInsights.class.getName());

	@Test
	public void testBizInsights() {
		try {
			System.out.println("Test -->  "+this.getClass().getSimpleName());
			String startURL = rds.getValue("BMGMT", currentTest, "URL");
			String uname = rds.getValue("BMGMT", currentTest, "UserName");
			String pwd = rds.getValue("BMGMT", currentTest, "Password");
			BM_Home home = new BM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			
			String stage = rds.getValue("BMGMT", currentTest, "Sector");
			String header = rds.getValue("BMGMT", currentTest, "Heading");
			String company = rds.getValue("BMGMT", currentTest, "Company");
			home.findProject(remoteDriver, stage, company, header);
			
			BM_Company_BizGoals goal = new BM_Company_BizGoals(remoteDriver);
			goal.leadHere(remoteDriver);
			goal.openBizInsights(remoteDriver);
			
			BM_Insights insight = new BM_Insights(remoteDriver);
			insight.testInsightPage(remoteDriver, company);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
