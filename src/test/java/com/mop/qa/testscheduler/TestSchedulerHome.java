package com.mop.qa.testscheduler;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.SchedulerPages.SchedulerHome;
import com.mop.qa.testbase.TestBase;

public class TestSchedulerHome extends TestBase{
	private static final Logger LOGGER = Logger.getLogger(TestSchedulerHome.class.getName());
	
	@Test
	public void testSchedulerHome() {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("SCHEDULER", currentTest, "URL");
			String uname = rds.getValue("SCHEDULER", currentTest, "UserName");
			String pwd = rds.getValue("SCHEDULER", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			
			SchedulerHome sHome = new SchedulerHome(remoteDriver);
			sHome.testHomePage(remoteDriver); 
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
