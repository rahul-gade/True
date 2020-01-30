package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestDealFolder extends TestBase{	
	private static final Logger LOGGER = Logger.getLogger(TestHypothesisFull.class.getName());

	@Test
	public void testDealFolder() {
		try {
			System.out.println("Test -->  "+this.getClass().getSimpleName());
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			String project = rds.getValue("INVMGMT", currentTest, "projectName");
			home.findLiveDeal(remoteDriver, project);
			
			IM_DealSummaryPage summary = new IM_DealSummaryPage(remoteDriver);
			String filePath = rds.getValue("INVMGMT", currentTest, "filePath");
			String newFile = rds.getValue("INVMGMT", currentTest, "filePathX");
			summary.validateFolders(remoteDriver, filePath);
			summary.createFolder(remoteDriver);
			summary.uploadFile(remoteDriver, newFile);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
