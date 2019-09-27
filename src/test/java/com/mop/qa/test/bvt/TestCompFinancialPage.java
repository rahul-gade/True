package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.BasicDealDetails;
import com.mop.qa.pageobject.CompanyFinancials;
import com.mop.qa.pageobject.CompanyInformation;
import com.mop.qa.pageobject.DealHome;
import com.mop.qa.pageobject.InvestmentHome;

import com.mop.qa.testbase.TestBase;

public class TestCompFinancialPage extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestCompFinancialPage.class.getName());

	@Test
	public void createDeal() throws Exception {
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
			String mandatory = rds.getValue("DATA", currentTest, "Mandatory");
			String subsector = rds.getValue("DATA", currentTest, "SubSector");
			String dealsize = rds.getValue("DATA", currentTest, "DealSize");
			String stake = rds.getValue("DATA", currentTest, "Stake");
			dHome.createDealHomePage(remoteDriver, projectName, companyName, sector, filepath, mandatory, subsector, dealsize, stake, indName);
			Thread.sleep(1000);
			dHome.selectFlow(remoteDriver, flow);
			Thread.sleep(1000);
			BasicDealDetails dealDetails = new BasicDealDetails(remoteDriver);
//			String subsector = rds.getValue("DATA", currentTest, "SubSector");
			String source = rds.getValue("DATA", currentTest, "Source");
//			String dealsize = rds.getValue("DATA", currentTest, "DealSize");
//			String stake = rds.getValue("DATA", currentTest, "Stake");
			String stakePercent = rds.getValue("DATA", currentTest, "StakePercent");
			// dealDetails.verifyDealDetails(projectName, companyName, sector);
			Thread.sleep(1000);
			dealDetails.enterDealDetails(remoteDriver, companyName, companyName, subsector, source, dealsize, stake, stakePercent, mandatory);
			Thread.sleep(1000);
			CompanyInformation companyInfo = new CompanyInformation(remoteDriver);
			String cmpInfo = rds.getValue("DATA", currentTest, "CompanyInfoBrief");
			String leader = rds.getValue("DATA", currentTest, "CurrentLeaderhip");
			String shareHolder = rds.getValue("DATA", currentTest, "KeyShareHolders");
			String percentage = rds.getValue("DATA", currentTest, "SharePercentage");
			companyInfo.enterCompanyDetails(remoteDriver, cmpInfo, leader, shareHolder, percentage, mandatory);
			CompanyFinancials compFin = new CompanyFinancials(remoteDriver);
//			compFin.checkPageTitle(remoteDriver);
//			compFin.checkDealTitle(remoteDriver, projectName);
//			compFin.checkLastSaveTime(remoteDriver);
			compFin.checkNavButtons(remoteDriver);
			compFin.tableData(remoteDriver);
//			compFin.enterCompanyDetails();
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
