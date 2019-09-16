package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.DealHome;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestDeleteDeal extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestCardsData.class.getName());

	@Test
	public void deleteDeal() throws Exception {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			String projectName = rds.getValue("DATA", currentTest, "ProjectName");
			String companyName = rds.getValue("DATA", currentTest, "CompanyName");
			String sector = rds.getValue("DATA", currentTest, "Sector");
			
			/*
			 * The commented section creates a deal, then deletes it. 
			 * If run without it, it'll look for the deal created by TestRequestIB - Test.
			 */
//			DealHome dHome = new DealHome(remoteDriver);
//			String flow = rds.getValue("DATA", currentTest, "Flow");
//			String filepath = rds.getValue("DATA", currentTest, "FilePath");
//			String mandatory = rds.getValue("DATA", currentTest, "Mandatory");
//			dHome.createDealHomePage(remoteDriver, projectName, companyName, sector, filepath, mandatory);
//			Thread.sleep(1000);
//			
//			dHome.flowForDelete(remoteDriver, flow);
//			Thread.sleep(1000);

			inst.deleteDeal(remoteDriver, projectName, companyName, sector);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
