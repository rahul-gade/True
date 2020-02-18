package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

/*
 * This Class is no longer used
 * As All the features in this class are tested back in 
 * TestAddGoalSIA.java 
 * 
 * This is done to prevent redundancy and save time.
 * 
 * ALSO - THE CLASS IS REMOVED FROM testrunner AND datasheet
 */
public class TestGoalDetails extends TestBase {
	public static final Logger LOGGER = Logger.getLogger(TestGoalDetails.class.getName());

	@Test
	public void testGoalDetails() {
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
			String goalTitle = rds.getValue("BMGMT", currentTest, "GoalTitle");
			String type = rds.getValue("BMGMT", currentTest, "Type");
			String criticality = rds.getValue("BMGMT", currentTest, "Criticality");
			String critCode = rds.getValue("BMGMT", currentTest, "CritCode");
			goals.openGoalDetails(remoteDriver, goalTitle);
			BM_GoalDetails details = new BM_GoalDetails(remoteDriver);
			details.testDetails(remoteDriver, goalTitle, type, criticality, critCode);
			
			goals.openGoalDetails(remoteDriver, goalTitle);
			details.archiveGoal(remoteDriver);
			goals.checkArchive(remoteDriver, goalTitle);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
