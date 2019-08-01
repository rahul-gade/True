package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.pageobject.JRBasicDetails;
import com.mop.qa.pageobject.ReplicateJR;
import com.mop.qa.pageobject.TalentAcquisitionHome;
import com.mop.qa.testbase.TestBase;

public class TestPreviewJrAndReplicate extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestPreviewJrAndReplicate.class.getName());

	 @Test
		public void previewAndReplicate() throws Exception  {
		 try{
			 InvestmentHome inst = new InvestmentHome(remoteDriver);
			 String startURL = rds.getValue("TALENTACQUISITION", currentTest, "URL");
			 String uname = rds.getValue("TALENTACQUISITION", currentTest, "UserName");
			 String pwd = rds.getValue("TALENTACQUISITION", currentTest, "Password");
			 inst.launchApp(startURL, uname, pwd);
			 Thread.sleep(5000);
			 TalentAcquisitionHome home = new TalentAcquisitionHome(remoteDriver);
				home.clickNewJr(remoteDriver);
				JRBasicDetails basic = new JRBasicDetails(remoteDriver);
				 String companyName = rds.getValue("TALENTACQUISITION", currentTest, "CompanyName");
				 String position = rds.getValue("TALENTACQUISITION", currentTest, "Position");
				 String location = rds.getValue("TALENTACQUISITION", currentTest, "Location");
				 String sector = rds.getValue("TALENTACQUISITION", currentTest, "Sector");
				basic.enterBasicDetails(remoteDriver, companyName, position, location);
				basic.fillForm(remoteDriver);
				ReplicateJR jr = new ReplicateJR(remoteDriver);
				jr.selectFilter(remoteDriver, position, sector, location, companyName);
				jr.prieviewJR(remoteDriver);
		 }catch(Exception e){
			 LOGGER.info(e);
		 }
	 }
}
