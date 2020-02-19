package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestAddGoalSIA extends TestBase {
	public static final Logger LOGGER = Logger.getLogger(TestAddGoalSIA.class.getName());

	@Test
	public void testAddGoalIdea() {
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
			goals.leadHere(remoteDriver);
			goals.initiateNewGoal(remoteDriver);
			String goalTitle = rds.getValue("BMGMT", currentTest, "GoalTitle");
			String vertical = rds.getValue("BMGMT", currentTest, "Vertical");
			String type = rds.getValue("BMGMT", currentTest, "Type");
			String criticality = rds.getValue("BMGMT", currentTest, "Criticality");
			String critCode = rds.getValue("BMGMT", currentTest, "CritCode");

			BM_NewGoal goal = new BM_NewGoal(remoteDriver);
			goal.fillbasicData(remoteDriver, goalTitle, vertical, type, criticality, critCode);
			goal.testAdditionalFields(remoteDriver, type);
			String tn_spoc = rds.getValue("BMGMT", currentTest, "TN_SPOC");
			String pc_spoc = rds.getValue("BMGMT", currentTest, "PC_SPOC");
			goal.enterAdditionalFields(remoteDriver, tn_spoc, pc_spoc);
			goal.submitAndVerify(remoteDriver, type, goal.goal);

			BM_AddActionPlan plan = new BM_AddActionPlan(remoteDriver);
			String planTitle = rds.getValue("BMGMT", currentTest, "PlanTitle");
			String MSTitle = rds.getValue("BMGMT", currentTest, "MileStone");
			String metricTitle = rds.getValue("BMGMT", currentTest, "Metric");
			String metricUnit = rds.getValue("BMGMT", currentTest, "Unit");
			plan.addNewPlan(remoteDriver, type, goal.dateStart, goal.dateEnd, planTitle, pc_spoc, MSTitle, metricTitle,
					metricUnit);
			goals.submitAndVerifyGoal(remoteDriver, type, goal.goal);
			BM_GoalDetails details = new BM_GoalDetails(remoteDriver);
			
//			goal details and archiving features - DID NOT check for every goal type!
			goals.openGoalDetails(remoteDriver, goal.goal);
			details.testDetails(remoteDriver, goal.goal, type, criticality, critCode);
			goals.openGoalDetails(remoteDriver, goal.goal);
			details.archiveGoal(remoteDriver);
			goals.checkArchive(remoteDriver, goal.goal);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
