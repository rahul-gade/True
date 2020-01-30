package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestIMHome extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestIMHome.class.getName());

	@Test
	public void testIMHome() {
		try {
			System.out.println("Test -->  "+this.getClass().getSimpleName());
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			home.checkHomePage(remoteDriver);
			home.checkNewDealPopup();
			IM_DealPipeline dPipe = new IM_DealPipeline(remoteDriver);
			dPipe.checkDealPipeline(remoteDriver);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
