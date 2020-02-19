package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestAddGoalIDEA extends TestBase{
	public static final Logger LOGGER = Logger.getLogger(TestAddGoalIDEA.class.getName());

	@Test
	public void testAddGoalIdea() {
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
			
			BM_Company_BizGoals goals = new BM_Company_BizGoals(remoteDriver);
			goals.leadHere(remoteDriver); //probably add a page check - on BM_Company_TEST??
			goals.initiateNewGoal(remoteDriver);
			String goalTitle = rds.getValue("BMGMT", currentTest, "GoalTitle");
			String vertical = rds.getValue("BMGMT", currentTest, "Vertical");
			String type = rds.getValue("BMGMT", currentTest, "Type");
			String criticality = rds.getValue("BMGMT", currentTest, "Criticality");
			String critCode = rds.getValue("BMGMT", currentTest, "CritCode");
			
			BM_NewGoal goal = new BM_NewGoal(remoteDriver);
			goal.fillbasicData(remoteDriver, goalTitle, vertical, type, criticality, critCode);
			goals.submitAndVerifyGoal(remoteDriver, type, goal.goal);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
