package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.CreateNewDocument;
import com.mop.qa.pageobject.CreatedDealPage;
import com.mop.qa.pageobject.ICNote;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestIcNote extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestIcNote.class.getName());
	@Test
	public void enterIndustryDetails() throws Exception  {
	 try{
		 InvestmentHome inst = new InvestmentHome(remoteDriver);
		 String projectName = rds.getValue("DATA", currentTest, "ProjectName");
		 String companyName = rds.getValue("DATA", currentTest, "CompanyName");
		 String sector = rds.getValue("DATA", currentTest, "Sector");
		 String startURL = rds.getValue("DATA", currentTest, "URL");
		 String uname = rds.getValue("DATA", currentTest, "UserName");
		 String pwd = rds.getValue("DATA", currentTest, "Password");
		 inst.launchApp(startURL, uname, pwd);
		 Thread.sleep(2000);
		 inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
		 CreatedDealPage cdp = new CreatedDealPage(remoteDriver);
		 cdp.createNewDocument(remoteDriver);
		 CreateNewDocument cnd = new CreateNewDocument(remoteDriver);
		 String document = rds.getValue("HYPOTHESIS", currentTest, "DocumentName");
		 cnd.createNewDocument(remoteDriver, document); 
		 ICNote note = new ICNote(remoteDriver);
		 String filePath = rds.getValue("DATA", currentTest, "FilePath");
		 note.createIcNote(filePath);
	 }catch(Exception e){
		 LOGGER.info(e);
		 }
 }
}
