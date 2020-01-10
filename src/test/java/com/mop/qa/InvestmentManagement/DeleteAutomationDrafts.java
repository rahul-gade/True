package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class DeleteAutomationDrafts extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(DeleteAutomationDrafts.class.getName());

	@Test
	public void deleteAutoDrafts() {
		try {
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			home.deleteAllAutoDrafts(remoteDriver);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
