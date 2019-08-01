package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestDeleteDeal extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestCardsData.class.getName());
	@Test
	public void deleteDeal() throws Exception  {
	 try{
		 InvestmentHome inst = new InvestmentHome(remoteDriver);
		 String startURL = rds.getValue("DATA", currentTest, "URL");
		 String uname = rds.getValue("DATA", currentTest, "UserName");
		String pwd = rds.getValue("DATA", currentTest, "Password");
		 String projectName = rds.getValue("DATA", currentTest, "ProjectName"); 
		  String companyName = rds.getValue("DATA", currentTest, "CompanyName"); 
		  String sector = rds.getValue("DATA", currentTest, "Sector"); 
		 inst.launchApp(startURL, uname, pwd);
		 inst.deleteDeal(remoteDriver, projectName, companyName, sector);
	 }catch(Exception e){
		 LOGGER.info(e);
		 }
	}
}
