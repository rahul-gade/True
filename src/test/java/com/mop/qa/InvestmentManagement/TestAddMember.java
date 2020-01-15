package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestAddMember extends TestBase{
	private static final Logger LOGGER = Logger.getLogger(TestAddMember.class.getName());

	@Test
	public void testAddMember() {
		try {
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			String project = rds.getValue("INVMGMT", currentTest, "projectName");
			home.findLiveDeal(remoteDriver, project);
			IM_AddMember addM = new IM_AddMember(remoteDriver);
			String member = rds.getValue("INVMGMT", currentTest, "AddMember");
			addM.addMember(remoteDriver, member);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
