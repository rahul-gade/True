package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.pageobject.JRAddCandidate;
import com.mop.qa.testbase.TestBase;

public class TestAddCandidate extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestAddCandidate.class.getName());

	 @Test
		public void addCandidate() throws Exception  {
		 try{
			 InvestmentHome inst = new InvestmentHome(remoteDriver);
			 String startURL = rds.getValue("TALENTACQUISITION", currentTest, "URL");
			 String uname = rds.getValue("TALENTACQUISITION", currentTest, "UserName");
			 String pwd = rds.getValue("TALENTACQUISITION", currentTest, "Password");
			 inst.launchApp(startURL, uname, pwd);
			 Thread.sleep(1000);
			 JRAddCandidate add = new JRAddCandidate(remoteDriver);
			 String position = rds.getValue("TALENTACQUISITION", currentTest, "Position");
			 String companyName = rds.getValue("TALENTACQUISITION", currentTest, "CompanyName");
			 String company = companyName.toUpperCase();
			 String name = rds.getValue("TALENTACQUISITION", currentTest, "Name");
			 String email = rds.getValue("TALENTACQUISITION", currentTest, "Email");
			 String contact = rds.getValue("TALENTACQUISITION", currentTest, "Contact");
			 add.navigateLongList(remoteDriver, position, company);
			 add.addCandidate(remoteDriver);
			 String company1 = rds.getValue("TALENTACQUISITION", currentTest, "Company");
			 add.enterCandidateDetails(remoteDriver, name, company1, position, email, contact);
			 String remark = rds.getValue("TALENTACQUISITION", currentTest, "Remarks");
			 add.shortlistCandidate(remoteDriver, remark);
			 add.addToProspect(remoteDriver, remark);
		 }catch(Exception e){
			 LOGGER.info(e);
		 }
	 }
}
