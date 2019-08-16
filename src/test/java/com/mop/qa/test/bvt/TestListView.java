package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestListView extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestListView.class.getName());

	@Test
	public void testVerifyListView() throws Exception {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver); 
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			inst.verifyDraftsDeals(remoteDriver);
			inst.verifyListView(remoteDriver);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
