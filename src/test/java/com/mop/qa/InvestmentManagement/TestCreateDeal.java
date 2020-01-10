package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestCreateDeal extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestIMBasicDetails.class.getName());

	@Test
	public void createDeal() {
		try {
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
//			home.openDraft(remoteDriver);
			if (flow.equals("Open Draft")) {
				String dealSize = rds.getValue("INVMGMT", currentTest, "dealSize");
				String ruleSector = rds.getValue("INVMGMT", currentTest, "ruleSector");
				String ruleStake = rds.getValue("INVMGMT", currentTest, "ruleStake");
				String ebitda = rds.getValue("INVMGMT", currentTest, "EBITDA");
				IM_SystemRules sysRule1 = new IM_SystemRules(remoteDriver);
				sysRule1.enterDetails(remoteDriver, dealSize, ruleSector, ruleStake, ebitda);
				
				String growth = rds.getValue("INVMGMT", currentTest, "growth");
				String scale = rds.getValue("INVMGMT", currentTest, "scale");
				IM_SystemRules_2 sysRule2 = new IM_SystemRules_2(remoteDriver);
				sysRule2.enterDetails(remoteDriver, growth, scale);
				
				String ROCE = rds.getValue("INVMGMT", currentTest, "ROCE");
				String margin = rds.getValue("INVMGMT", currentTest, "GrossMargin");
				IM_SystemRules_3 sysRule3 = new IM_SystemRules_3(remoteDriver);
				sysRule3.enterDetails(remoteDriver, ROCE, margin);
				
				IM_SubmitSystemRules submit = new IM_SubmitSystemRules(remoteDriver);
				submit.collapseFieldsAndSubmit();
			}
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}

}
