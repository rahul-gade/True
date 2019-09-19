package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.BasicDealDetails;
import com.mop.qa.pageobject.CompanyFinancials;
import com.mop.qa.pageobject.CompanyInformation;
import com.mop.qa.pageobject.CreatedDealPage;
import com.mop.qa.pageobject.DealHome;
import com.mop.qa.pageobject.IndustryInformation;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestDealPage extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestDealPage.class.getName());

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
			String sector = rds.getValue("DATA", currentTest, "Sector");
			String flow = rds.getValue("DATA", currentTest, "Flow");
			String filepath = rds.getValue("DATA", currentTest, "FilePath");
			String subsector = rds.getValue("DATA", currentTest, "SubSector");
			String dealsize = rds.getValue("DATA", currentTest, "DealSize");
			String stake = rds.getValue("DATA", currentTest, "Stake");
			String mandatory = rds.getValue("DATA", currentTest, "Mandatory");
			dHome.createDealHomePage(remoteDriver, projectName, companyName, sector, filepath, mandatory, subsector,
					dealsize, stake);
			Thread.sleep(1000);
			dHome.selectFlow(remoteDriver, flow);
			Thread.sleep(1000);
			BasicDealDetails dealDetails = new BasicDealDetails(remoteDriver);

			String source = rds.getValue("DATA", currentTest, "Source");

			String stakePercent = rds.getValue("DATA", currentTest, "StakePercent");
			Thread.sleep(1000);
			dealDetails.enterDealDetails(remoteDriver, companyName, companyName, subsector, source, dealsize, stake,
					stakePercent, mandatory);
			Thread.sleep(1000);
			CompanyInformation companyInfo = new CompanyInformation(remoteDriver);
			String cmpInfo = rds.getValue("DATA", currentTest, "CompanyInfoBrief");
			String leader = rds.getValue("DATA", currentTest, "CurrentLeaderhip");
			String shareHolder = rds.getValue("DATA", currentTest, "KeyShareHolders");
			String percentage = rds.getValue("DATA", currentTest, "SharePercentage");
			companyInfo.enterCompanyDetails(remoteDriver, cmpInfo, leader, shareHolder, percentage, mandatory);
			CompanyFinancials compFin = new CompanyFinancials(remoteDriver);
			compFin.enterCompanyDetails();
			IndustryInformation industryInfo = new IndustryInformation(remoteDriver);
			String indSize = rds.getValue("DATA", currentTest, "IndustrySize");
			String trgtMktShare = rds.getValue("DATA", currentTest, "TargetCompMarketShare");
			String last3year = rds.getValue("DATA", currentTest, "Last3Year");
			String last5year = rds.getValue("DATA", currentTest, "Last5Year");
			String pred3year = rds.getValue("DATA", currentTest, "Predicted3Year");
			String pred5year = rds.getValue("DATA", currentTest, "Predicted5Year");
			String competitorName = rds.getValue("DATA", currentTest, "CompetitorName");
			String percent = rds.getValue("DATA", currentTest, "IndSharePercentage");
			String flows = rds.getValue("DATA", currentTest, "Flows");
			industryInfo.enterIndustryDetails(remoteDriver, projectName, indSize, trgtMktShare, last3year, last5year,
					pred3year, pred5year, competitorName, percent, flows, mandatory);
			// ==========DEAL CREATED==========//
			CreatedDealPage dealPG = new CreatedDealPage(remoteDriver);
			dealPG.dropDown(remoteDriver);
			dealPG.passDeal(remoteDriver, "Sample Reason for Passing The Deal!");
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
