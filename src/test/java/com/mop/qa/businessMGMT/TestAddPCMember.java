package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestAddPCMember extends TestBase{
	public static final Logger LOGGER = Logger.getLogger(TestAddPCMember.class.getName());
	
	@Test
	public void testAddPCMember() {
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
			
			BM_AddMember member = new BM_AddMember(remoteDriver);
			member.openPopUp(remoteDriver);
			
			String pName = rds.getValue("BMGMT", currentTest, "ProfileName");
			String location = rds.getValue("BMGMT", currentTest, "Location");
			String designation = rds.getValue("BMGMT", currentTest, "Designation");
			String contact = rds.getValue("BMGMT", currentTest, "Contact");
			String mail = rds.getValue("BMGMT", currentTest, "Mail");
			member.addMember(remoteDriver, pName);
			member.addProfile(remoteDriver, location, designation, contact, mail);
//			TODO - PENDING SUBMIT AND FURTHER PROCESSING - Application erroro so far
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
