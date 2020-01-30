package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestIMBasicDetails extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestIMBasicDetails.class.getName());

	@Test
	public void createBasicDeal() {
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
			String filePath = rds.getValue("INVMGMT", currentTest, "filePath");
			String dealSponsor = rds.getValue("INVMGMT", currentTest, "dealSponsor");
			String dealOwner = rds.getValue("INVMGMT", currentTest, "dealOwner");
			String teamMembers = rds.getValue("INVMGMT", currentTest, "teamMembers");
			String flow = rds.getValue("INVMGMT", currentTest, "Flow");
			IM_BasicDealDetails bDetails = new IM_BasicDealDetails(remoteDriver);
			bDetails.enterBasicDetails(remoteDriver, company, project, industry, sector, subSector, stake, filePath,
					dealSponsor, dealOwner, teamMembers);
			bDetails.submitAndSelectFlow(remoteDriver, flow);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
