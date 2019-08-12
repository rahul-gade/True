package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.BasicDealDetails;
import com.mop.qa.pageobject.EditDealHomePage;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestBasicDealPage extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestBasicDealPage.class.getName());
	@Test
	public void verifyDealDetails() throws Exception  {
	 try{
		 InvestmentHome inst = new InvestmentHome(remoteDriver);
		 String startURL = rds.getValue("DATA", currentTest, "URL");
		 String uname = rds.getValue("DATA", currentTest, "UserName");
		String pwd = rds.getValue("DATA", currentTest, "Password");
		 inst.launchApp(startURL, uname, pwd);
		  String projectName = rds.getValue("DATA", currentTest, "ProjectName"); 
		  String companyName = rds.getValue("DATA", currentTest, "CompanyName"); 
		  String sector = rds.getValue("DATA", currentTest, "Sector"); 
		  String navigate = rds.getValue("DATA", currentTest, "Navigate");
		  inst.findDeal(remoteDriver, projectName, companyName, sector);
		  EditDealHomePage editdeal = new EditDealHomePage(remoteDriver); 
			 String section = rds.getValue("DATA", currentTest, "EditSection");
			 editdeal.editSection(remoteDriver,section); 
			 inst.verifyNameAndTime(remoteDriver, projectName);
		 BasicDealDetails basic = new BasicDealDetails(remoteDriver);
		 basic.verifyDealDetails(remoteDriver, projectName, companyName, sector, navigate);
	 }catch(Exception e){
		 LOGGER.info(e);
		 }
 }
}
