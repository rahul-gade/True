package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.Utilities.MPException;
import com.mop.qa.pageobject.BasicDealDetails;
import com.mop.qa.pageobject.DealHome;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestBlankCompCMIE extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestCreateDeal.class.getName());

	@Test
	public void testBlankFinTable() {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);

			DealHome dHome = new DealHome(remoteDriver);
			String projectName = rds.getValue("DATA", currentTest, "ProjectName");
			String companyName = rds.getValue("DATA", currentTest, "CompanyName");
			String indName = rds.getValue("DATA", currentTest, "IndustryName");
			String sector = rds.getValue("DATA", currentTest, "Sector");
			String flow = rds.getValue("DATA", currentTest, "Flow");
			String filepath = rds.getValue("DATA", currentTest, "FilePath");
			String subsector = rds.getValue("DATA", currentTest, "SubSector");
			String dealsize = rds.getValue("DATA", currentTest, "DealSize");
			String stake = rds.getValue("DATA", currentTest, "Stake");
			String mandatory = rds.getValue("DATA", currentTest, "Mandatory");
			dHome.createDealHomePage(remoteDriver, projectName, companyName, sector, filepath, mandatory, subsector,
					dealsize, stake, indName);
			Thread.sleep(1000);

			dHome.selectFlow(remoteDriver, flow);
			Thread.sleep(1000);
			
			BasicDealDetails dealDetails = new BasicDealDetails(remoteDriver);
			String source = rds.getValue("DATA", currentTest, "Source");
			String stakePercent = rds.getValue("DATA", currentTest, "StakePercent");
			Thread.sleep(1000);
//			dealDetails.enterDealDetails(remoteDriver, companyName, companyName, subsector, source, dealsize, stake,
//					stakePercent, mandatory);
			Thread.sleep(1000);
			
			dealDetails.checkCMIE(remoteDriver, mandatory, companyName); 
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
