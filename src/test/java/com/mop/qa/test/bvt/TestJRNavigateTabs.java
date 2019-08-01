package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.pageobject.JRNavigateTabs;
import com.mop.qa.testbase.TestBase;

public class TestJRNavigateTabs extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestJRNavigateTabs.class.getName());
	@Test
	public void navigateJRTabs() throws Exception  {
	 try{
		 InvestmentHome inst = new InvestmentHome(remoteDriver);
		 String startURL = rds.getValue("DATA", currentTest, "URL");
		 String uname = rds.getValue("DATA", currentTest, "UserName");
		 String pwd = rds.getValue("DATA", currentTest, "Password");
		 inst.launchApp(startURL, uname, pwd);
		 Thread.sleep(2000);
		 JRNavigateTabs tab = new JRNavigateTabs(remoteDriver);
		 tab.navigateTabs(remoteDriver);
	 }catch(Exception e){
		 LOGGER.info(e);
		 }
 }

}
