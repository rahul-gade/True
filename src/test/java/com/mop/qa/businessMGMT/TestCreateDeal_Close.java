package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.InvestmentManagement.IM_BasicDealDetails;
import com.mop.qa.InvestmentManagement.IM_DealSummaryPage;
import com.mop.qa.InvestmentManagement.IM_Home;
import com.mop.qa.testbase.TestBase;

public class TestCreateDeal_Close extends TestBase{
	private static final Logger LOGGER = Logger.getLogger(TestCreateDeal_Close.class.getName());
	
	@Test
	public void testCreateDealAndClose() {
		try {
			System.out.println("Test -->  "+this.getClass().getSimpleName());
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			home.startDealJourney();

			String company = rds.getValue("INVMGMT", currentTest, "companyName");
			String project = rds.getValue("INVMGMT", currentTest, "projectName");
			String industry = rds.getValue("INVMGMT", currentTest, "industry");
			String sector = rds.getValue("INVMGMT", currentTest, "sector");
			String subSector = rds.getValue("INVMGMT", currentTest, "subSector");
			String stake = rds.getValue("INVMGMT", currentTest, "stake");
			String dealSponsor = rds.getValue("INVMGMT", currentTest, "dealSponsor");
			IM_BasicDealDetails bDetails = new IM_BasicDealDetails(remoteDriver);
			String flow = rds.getValue("INVMGMT", currentTest, "Flow");
			bDetails.enterForBM(remoteDriver, company, project, industry, sector, subSector, stake, dealSponsor);
			bDetails.submitAndSelectFlow(remoteDriver, flow);
			IM_DealSummaryPage summary = new IM_DealSummaryPage(remoteDriver);
			summary.closeDeal(remoteDriver);
			String sectorB = rds.getValue("BMGMT", currentTest, "Sector");
			String header = rds.getValue("BMGMT", currentTest, "Heading");
			BM_Home bHome = new BM_Home(remoteDriver);
			bHome.openBM(remoteDriver, startURL);
			bHome.findProject(remoteDriver, sectorB, company, header);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
