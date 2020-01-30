package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestBMCompanyPage extends TestBase{
	private static final Logger LOGGER = Logger.getLogger(TestBMHome.class.getName());
	
	@Test
	public void testBMCompanyPage() {
		try {
			System.out.println("Test -->  " + this.getClass().getSimpleName());
			String startURL = rds.getValue("BMGMT", currentTest, "URL");
			String uname = rds.getValue("BMGMT", currentTest, "UserName");
			String pwd = rds.getValue("BMGMT", currentTest, "Password");
			BM_Home home = new BM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			
			String sector = rds.getValue("BMGMT", currentTest, "Sector");
			String company = rds.getValue("BMGMT", currentTest, "Company");
			String header = rds.getValue("BMGMT", currentTest, "Heading");
			home.findProject(remoteDriver, sector, company, header);
			
			BM_Company_Overview oview = new BM_Company_Overview(remoteDriver);
			oview.checkQuickActions(remoteDriver);
			oview.checkPopUps(remoteDriver);
			
//			navigate tabs
			BM_Company_VisMis vision = new BM_Company_VisMis(remoteDriver);
			BM_Company_BizGoals biz = new BM_Company_BizGoals(remoteDriver);
			BM_Company_ProjMgmt proj = new BM_Company_ProjMgmt(remoteDriver);
			vision.leadHere(remoteDriver);
			biz.leadHere(remoteDriver);
			proj.leadHere(remoteDriver);
			oview.leadHere(remoteDriver);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
