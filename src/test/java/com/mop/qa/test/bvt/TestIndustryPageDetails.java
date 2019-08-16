package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.EditDealHomePage;
import com.mop.qa.pageobject.IndustryInformation;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestIndustryPageDetails extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestIndustryPageDetails.class.getName());
	@Test
	public void verifyIndustryDetails() throws Exception  {
	 try{
		 InvestmentHome inst = new InvestmentHome(remoteDriver);
		 String projectName = rds.getValue("DATA", currentTest, "ProjectName");
		 String companyName = rds.getValue("DATA", currentTest, "CompanyName");
		 String sector = rds.getValue("DATA", currentTest, "Sector");
		 String startURL = rds.getValue("DATA", currentTest, "URL");
		 String uname = rds.getValue("DATA", currentTest, "UserName");
		 String pwd = rds.getValue("DATA", currentTest, "Password");
		 inst.launchApp(startURL, uname, pwd);
		 Thread.sleep(1000);
		 inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
		 EditDealHomePage editdeal = new EditDealHomePage(remoteDriver); 
		 String section = rds.getValue("DATA", currentTest, "EditSection");
		 editdeal.editSection(remoteDriver,section); 
		 IndustryInformation info = new IndustryInformation(remoteDriver);
		 info.verifyIndustryDetailsPage(remoteDriver);
	   }catch(Exception e){
		 LOGGER.info(e);
	   }
	}
}
