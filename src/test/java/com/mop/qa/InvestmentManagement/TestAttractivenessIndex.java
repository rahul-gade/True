package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestAttractivenessIndex extends TestBase{
	private static final Logger LOGGER = Logger.getLogger(TestAttractivenessIndex.class.getName());
	
	@Test
	public void testAttractivenessIndex() {
		try {
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			String project = rds.getValue("INVMGMT", currentTest, "projectName");
			home.findLiveDeal(remoteDriver, project);
			
			String dealSize = rds.getValue("INVMGMT", currentTest, "dealSize");
			String ruleSector = rds.getValue("INVMGMT", currentTest, "ruleSector");
			String ruleStake = rds.getValue("INVMGMT", currentTest, "ruleStake");
			String ebitda = rds.getValue("INVMGMT", currentTest, "EBITDA");
			String growth = rds.getValue("INVMGMT", currentTest, "growth");
			String scale = rds.getValue("INVMGMT", currentTest, "scale");
			String ROCE = rds.getValue("INVMGMT", currentTest, "ROCE");
			String margin = rds.getValue("INVMGMT", currentTest, "GrossMargin");
			IM_DSum_CompFinThesis dSum = new IM_DSum_CompFinThesis(remoteDriver);
			dSum.testAttractiveness(remoteDriver);
			dSum.editAttractiveness(remoteDriver, dealSize, ruleSector, ruleStake, ebitda, growth, scale, ROCE, margin);
			
		} catch (Exception e) {
			 LOGGER.info(e);
		}
	}

}
