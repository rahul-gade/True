package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.mop.qa.pageobject.DealHome;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.testbase.TestBase;

public class TestHomePage extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestHomePage.class.getName());

	 @Test
		public void verifyDealOwner() throws Exception  {
		 try{
			 InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			String dOwner = rds.getValue("DATA", currentTest, "DealOwner");
			//String	startURL = "http://10.223.127.71/#/Investment-Management";
			 inst.launchApp(startURL, uname, pwd);
			 DealHome home = new DealHome(remoteDriver);
			 home.verifyDealOwner(remoteDriver, dOwner);
		 }catch(Exception e){
			 LOGGER.info(e);
		 }
	 }
}
