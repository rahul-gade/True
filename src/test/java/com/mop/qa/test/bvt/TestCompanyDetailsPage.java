package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.BasicDealDetails;
import com.mop.qa.pageobject.CompanyInformation;
import com.mop.qa.pageobject.EditDealHomePage;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestCompanyDetailsPage extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestCompanyDetailsPage.class.getName());
	@Test
	public void verifyCompanyDetails() throws Exception  {
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
		  inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
		  EditDealHomePage editdeal = new EditDealHomePage(remoteDriver); 
			 String section = rds.getValue("DATA", currentTest, "EditSection");
			 editdeal.editSection(remoteDriver,section); 
			 CompanyInformation companyinfo = new CompanyInformation(remoteDriver); 
			 companyinfo.verifyCompanyInfo(remoteDriver, companyName);
			 inst.verifyNameAndTime(remoteDriver, projectName);
	 }catch(Exception e){
		 LOGGER.info(e);
		 }
 }

}
