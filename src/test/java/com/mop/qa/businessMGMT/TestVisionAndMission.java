package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestVisionAndMission extends TestBase{
	public static final Logger LOGGER = Logger.getLogger(TestVisionAndMission.class.getName());
	
	@Test
	public void testVisionAndMission() {
		try {
			System.out.println("Test -->  "+this.getClass().getSimpleName());
			String startURL = rds.getValue("BMGMT", currentTest, "URL");
			String uname = rds.getValue("BMGMT", currentTest, "UserName");
			String pwd = rds.getValue("BMGMT", currentTest, "Password");
			BM_Home home = new BM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			
			String stage = rds.getValue("BMGMT", currentTest, "Sector");
			String header = rds.getValue("BMGMT", currentTest, "Heading");
			String company = rds.getValue("BMGMT", currentTest, "Company");
			home.findProject(remoteDriver, stage, company, header);
			BM_Company_VisMis vision = new BM_Company_VisMis(remoteDriver);
			vision.leadHere(remoteDriver);
			if(vision.addVisionMission(remoteDriver))
				vision.editVisionMission(remoteDriver);
			else
				vision.editVisionMission(remoteDriver);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
