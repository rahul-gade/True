package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.BasicDealDetails;
import com.mop.qa.pageobject.CompanyInformation;
import com.mop.qa.pageobject.EditDealHomePage;
import com.mop.qa.pageobject.IndustryInformation;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestEdit extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestEdit.class.getName());
	InvestmentHome inst;
	 @Test (priority = 1)
		public void launch() throws Exception  {
		 try{ 
			 inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			 inst.launchApp(startURL, uname, pwd);
			 Thread.sleep(2000);
		 }catch(Exception e){
			 LOGGER.info(e);
		 }
	 }
	 
	 @Test (priority = 2)
	  public void findDeal() throws Exception { 
	   try{ 
		  String projectName = rds.getValue("DATA", currentTest, "ProjectName"); 
		  String companyName = rds.getValue("DATA", currentTest, "CompanyName"); 
		  String sector = rds.getValue("DATA", currentTest, "Sector"); 
		//  inst.findDeal(remoteDriver, projectName, companyName, sector);
		  inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
		  Thread.sleep(1000);
	   }catch(Exception e){ 
		  LOGGER.info(e); 
	   } 
	 }
	  
	  @Test (priority = 3)
	  public void editSection() throws Exception { 
		  try{
			 EditDealHomePage editdeal = new EditDealHomePage(remoteDriver); 
			 String section = rds.getValue("DATA", currentTest, "EditSection");
			 String projectName = rds.getValue("DATA", currentTest, "ProjectName");
			 editdeal.editSection(remoteDriver,section); 
			 inst.verifyNameAndTime(remoteDriver, projectName);
	  }catch(Exception e){
		  LOGGER.info(e); 
	  }
	} 

	  @Test (priority = 4)
	  public void editBasicDetails() throws Exception { 
		  try{	
			  BasicDealDetails  basicdeal = new BasicDealDetails(remoteDriver); 
			  String editProjectName = rds.getValue("DATA", currentTest, "EditProjectName"); 
			  String editCompanyName = rds.getValue("DATA", currentTest, "EditCompanyName"); 
			  String editSector = rds.getValue("DATA", currentTest, "EditSector"); 
			  String trueNorthName = rds.getValue("DATA", currentTest, "TrueNorthName"); 
			  String subSector = rds.getValue("DATA", currentTest, "SubSector"); 
			  String source = rds.getValue("DATA", currentTest, "Source"); 
			  String dealSize = rds.getValue("DATA", currentTest, "DealSize");
			  String stake = rds.getValue("DATA", currentTest, "Stake"); 
			  String otherSector = rds.getValue("DATA", currentTest, "OtherSector");
			  String section = rds.getValue("DATA", currentTest, "EditSection");
			  basicdeal.editBasicDealDetails(remoteDriver, editProjectName,	editCompanyName, trueNorthName, editSector, subSector, otherSector, source, dealSize, stake, section); 
		  }catch(Exception e) { 
				  LOGGER.info(e); 
		  }
	}
	  
	  @Test (priority = 5)
	  public void editCompanyInfo() throws Exception { 
		 try{
			  CompanyInformation companyinfo = new CompanyInformation(remoteDriver); 
			//  String editProjectName = rds.getValue("DATA", currentTest, "EditProjectName");
			  String editcmpBackground = rds.getValue("DATA", currentTest, "EditCompanyBackground"); 
			  String editcmpInfo = rds.getValue("DATA", currentTest, "EditCompanyInfoBrief"); 
			  String editleader = rds.getValue("DATA", currentTest, "EditCurrentLeaderhip"); 
			  String editshareHolder = rds.getValue("DATA", currentTest, "EditShareHolders");
			  String editpercentage = rds.getValue("DATA", currentTest,"EditSharePercentage");
			  String section = rds.getValue("DATA", currentTest, "EditSection");
			  companyinfo.editCompanyDetails(remoteDriver, editcmpBackground, editcmpInfo, editleader, editshareHolder, editpercentage, section);
		  }catch(Exception e){
			  LOGGER.info(e);
		  } 
	}
	  
	  @Test (priority = 6)
	  public void editIndustryInfo() throws Exception { 
		  try{
			  IndustryInformation industryinfo = new IndustryInformation(remoteDriver);
			  String projectName = rds.getValue("DATA", currentTest,"ProjectName");
			  String editProjectName = rds.getValue("DATA", currentTest,"EditProjectName"); 
			  String indSize = rds.getValue("DATA", currentTest,"EditIndustrySize"); 
			  String trgtMktShare = rds.getValue("DATA", currentTest,"EditTargetCompMarketShare");
			  String last3year = rds.getValue("DATA", currentTest, "EditLast3Year"); 
			  String last5year = rds.getValue("DATA", currentTest, "EditLast5Year"); 
			  String pred3year = rds.getValue("DATA", currentTest, "EditPredicted3Year"); 
			  String pred5year = rds.getValue("DATA", currentTest, "EditPredicted5Year"); 
			  String competitorName = rds.getValue("DATA", currentTest, "EditCompetitorName"); 
			  String percent = rds.getValue("DATA", currentTest, "EditIndSharePercentage");
			  String section = rds.getValue("DATA", currentTest, "EditSection"); 
			  String navigate = rds.getValue("DATA", currentTest, "Navigate");
			  industryinfo.editIndustryDetails(remoteDriver, projectName, editProjectName, indSize,trgtMktShare, last3year, last5year, pred3year, pred5year, competitorName, percent, section, navigate);
		 }catch(Exception e){ 
			  LOGGER.info(e); 
		 } 
	}
	 
}
