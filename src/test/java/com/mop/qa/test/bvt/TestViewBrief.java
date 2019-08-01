package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.pageobject.JRAddCandidate;
import com.mop.qa.pageobject.JRBriefAgency;
import com.mop.qa.testbase.TestBase;

public class TestViewBrief extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestViewBrief.class.getName());

	 @Test
		public void viewBrief() throws Exception  {
		 try{
			 InvestmentHome inst = new InvestmentHome(remoteDriver);
			 String startURL = rds.getValue("TALENTACQUISITION", currentTest, "URL");
			 String uname = rds.getValue("TALENTACQUISITION", currentTest, "UserName");
			 String pwd = rds.getValue("TALENTACQUISITION", currentTest, "Password");
			 inst.launchApp(startURL, uname, pwd);
			 Thread.sleep(2000);
			 JRAddCandidate add = new JRAddCandidate(remoteDriver);
			 String to = rds.getValue("TALENTACQUISITION", currentTest, "To");
			 String brief = rds.getValue("TALENTACQUISITION", currentTest, "Brief");
			 String position = rds.getValue("TALENTACQUISITION", currentTest, "Position");
			 String companyName = rds.getValue("TALENTACQUISITION", currentTest, "CompanyName");
			 String company = companyName.toUpperCase();
			 add.navigateLongList(remoteDriver, position, company);
			 JRBriefAgency agency = new JRBriefAgency(remoteDriver);
			 agency.addBrief(remoteDriver, to, brief);
			 agency.viewBrief(remoteDriver);
		 }catch(Exception e){
			 LOGGER.info(e);
		 }
	 }

}
