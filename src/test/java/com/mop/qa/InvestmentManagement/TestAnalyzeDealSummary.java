package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestAnalyzeDealSummary extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestIMBasicDetails.class.getName());

	@Test
	public void analyzeDealSummary() {
		try {
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			String project = rds.getValue("INVMGMT", currentTest, "projectName");
			home.findLiveDeal(remoteDriver, project);
			IM_DealSummaryPage summary = new IM_DealSummaryPage(remoteDriver);
//			summary.analyzePage(remoteDriver);
			String source = rds.getValue("INVMGMT", currentTest, "source");
			String rna = rds.getValue("INVMGMT", currentTest, "RNA");
			String portfolio = rds.getValue("INVMGMT", currentTest, "Portfolio");
			summary.analyzeSectorDetails(remoteDriver, source, rna, portfolio);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
