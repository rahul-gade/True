package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestBMHome extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestBMHome.class.getName());

	@Test
	public void testBMHome() {
		try {
			System.out.println("Test -->  " + this.getClass().getSimpleName());
			String startURL = rds.getValue("BMGMT", currentTest, "URL");
			String uname = rds.getValue("BMGMT", currentTest, "UserName");
			String pwd = rds.getValue("BMGMT", currentTest, "Password");
			BM_Home home = new BM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);

			String sector = rds.getValue("BMGMT", currentTest, "Sector");
			String company = rds.getValue("BMGMT", currentTest, "Company");
			String header = rds.getValue("BMGMT", currentTest, "Heading");
			home.testBMHome(remoteDriver, sector, company, header);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
