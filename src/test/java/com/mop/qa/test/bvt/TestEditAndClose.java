package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.EditDealHomePage;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestEditAndClose extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestEditAndClose.class.getName());
	InvestmentHome inst;
	 @Test
		public void editAndClose() throws Exception  {
		 try{ 
			 inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			 inst.launchApp(startURL, uname, pwd);
			 Thread.sleep(2000);
		  String projectName = rds.getValue("DATA", currentTest, "ProjectName"); 
		  String companyName = rds.getValue("DATA", currentTest, "CompanyName"); 
		  String sector = rds.getValue("DATA", currentTest, "Sector"); 
		  inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
			 EditDealHomePage editdeal = new EditDealHomePage(remoteDriver); 
			 String section = rds.getValue("DATA", currentTest, "EditSection");
			 editdeal.editSection(remoteDriver,section); 
			 inst.verifyNameAndTime(remoteDriver, projectName);
			 inst.editClose();
	  }catch(Exception e){
		  LOGGER.info(e); 
	  }
	} 

}
