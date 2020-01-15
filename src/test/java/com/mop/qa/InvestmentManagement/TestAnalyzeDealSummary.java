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
			summary.analyzePage(remoteDriver);
			
			String source = rds.getValue("INVMGMT", currentTest, "source");
			String rna = rds.getValue("INVMGMT", currentTest, "RNA");
			String portfolio = rds.getValue("INVMGMT", currentTest, "Portfolio");
			summary.analyzeSectorDetails(remoteDriver, source, rna, portfolio); 
			
			String marketCap = rds.getValue("INVMGMT", currentTest, "MarketCap");
			String HQ = rds.getValue("INVMGMT", currentTest, "HQ");
			String CEO = rds.getValue("INVMGMT", currentTest, "CEO");
			String services = rds.getValue("INVMGMT", currentTest, "Service");
			String holders = rds.getValue("INVMGMT", currentTest, "Sholders");
			String perces=rds.getValue("INVMGMT", currentTest, "Sperc");
			String geoFocus=rds.getValue("INVMGMT", currentTest, "GeoFocus");
			String india = rds.getValue("INVMGMT", currentTest, "India");
			String international = rds.getValue("INVMGMT", currentTest, "International");
			IM_DSum_CompFinThesis sumRem = new IM_DSum_CompFinThesis(remoteDriver);
			sumRem.analyzeCompanyInfo(remoteDriver, marketCap, HQ, CEO, services, holders, perces, geoFocus, india, international);
			
			sumRem.enterThesis(remoteDriver); //commented -- Hypothesis Inactive Check!
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
