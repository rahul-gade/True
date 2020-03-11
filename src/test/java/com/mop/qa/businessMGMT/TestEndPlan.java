package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

import sun.awt.image.PixelConverter.Bgrx;

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

			BM_Company_BizGoals bG = new BM_Company_BizGoals(remoteDriver);
			BM_PlanView plan = new BM_PlanView(remoteDriver);
			bG.leadHere(remoteDriver);

			String gTitles = rds.getValue("BMGMT", currentTest, "GoalTitle");
			String pTitles = rds.getValue("BMGMT", currentTest, "PlanTitle");
			bG.collectGoals(remoteDriver, gTitles, pTitles);
			bG.openPlan(remoteDriver);
			String[] v = rds.getValue("BMGMT", currentTest, "Vertical").split(",");
			String vHDP1 = v[0], vHDP2 = v[1], vSIA = v[2];
			plan.verifySidebar(remoteDriver, bG.HDP_1, vHDP1, bG.A_P.get(0));
			plan.verifySidebar(remoteDriver, bG.HDP_2, vHDP2, bG.A_P.get(1));
			plan.endPlan(remoteDriver, bG.HDP_1, bG.HDP_2);
			
//			SIA View
			BM_PlanView_SIA planSIA = new BM_PlanView_SIA(remoteDriver);
			planSIA.testSIAPage(remoteDriver);
			planSIA.verifySIAPlans(remoteDriver, plan.ended, plan.unended, bG.SIA);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
