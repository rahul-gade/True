package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.DealPipeline;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestDealPipelineSummary extends TestBase{
	private static final Logger LOGGER = Logger.getLogger(TestDealPipelineSummary.class.getName());
	
	@Test
	public void dealPipelineSummary() throws Exception {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			Thread.sleep(1000);
			
			DealPipeline dP = new DealPipeline(remoteDriver);
			dP.dealPipeline(remoteDriver);
			dP.stageTest(remoteDriver);
			dP.summaryBadge(remoteDriver);
			dP.summaryView(remoteDriver);
			dP.newOnePagers(remoteDriver);
			dP.dateRange(remoteDriver);
			dP.dealsProgressed(remoteDriver);
			
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
