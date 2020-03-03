package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestEndPlan extends TestBase{
	public static final Logger LOGGER = Logger.getLogger(TestEndPlan.class.getName());
	
	@Test
	public void testEndPlan() {
		try {
			System.out.println("Test -->  " + this.getClass().getSimpleName());
			String startURL = rds.getValue("BMGMT", currentTest, "URL");
			String uname = rds.getValue("BMGMT", currentTest, "UserName");
			String pwd = rds.getValue("BMGMT", currentTest, "Password");
			BM_Home home = new BM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);

			String stage = rds.getValue("BMGMT", currentTest, "Sector");
			String header = rds.getValue("BMGMT", currentTest, "Heading");
			String company = rds.getValue("BMGMT", currentTest, "Company");
			home.findProject(remoteDriver, stage, company, header);

			BM_Company_BizGoals goals = new BM_Company_BizGoals(remoteDriver);
			BM_PlanView plan = new BM_PlanView(remoteDriver);
			goals.leadHere(remoteDriver);

			String gTitles = rds.getValue("BMGMT", currentTest, "GoalTitle");
			goals.collectGoals(remoteDriver, gTitles);
			goals.openPlan(remoteDriver);
			String[] v = rds.getValue("BMGMT", currentTest, "Vertical").split(",");
			String vHDP = v[0], vSIA = v[1];
			plan.verifySideBar(remoteDriver, goals.HDP, vHDP);
			plan.endPlan(remoteDriver, goals.HDP);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
