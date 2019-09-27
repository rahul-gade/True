package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.BasicDealDetails;
import com.mop.qa.pageobject.CompanyFinancials;
import com.mop.qa.pageobject.CompanyInformation;
import com.mop.qa.pageobject.DealHome;
import com.mop.qa.pageobject.IndustryInformation;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.pageobject.RequestIB;
import com.mop.qa.testbase.TestBase;

public class TestRequestIB extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestRequestIB.class.getName());

	@Test
	public void requestInfoByEmail() throws Exception {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			DealHome dHome = new DealHome(remoteDriver);
			String projectName = rds.getValue("DATA", currentTest, "ProjectName");
			String companyName = rds.getValue("DATA", currentTest, "CompanyName");
			String indName = rds.getValue("DATA", currentTest, "IndustryName");
			String sector = rds.getValue("DATA", currentTest, "Sector");
			String flow = rds.getValue("DATA", currentTest, "Flow");
			String filepath = rds.getValue("DATA", currentTest, "FilePath");
			String mandatory = rds.getValue("DATA", currentTest, "Mandatory");
			String subsector = rds.getValue("DATA", currentTest, "SubSector");
			String dealsize = rds.getValue("DATA", currentTest, "DealSize");
			String stake = rds.getValue("DATA", currentTest, "Stake");
			dHome.createDealHomePage(remoteDriver, projectName, companyName, sector, filepath, mandatory, subsector, dealsize, stake, indName);
			Thread.sleep(2000);
			dHome.selectFlow(remoteDriver, flow);
			Thread.sleep(2000);
			RequestIB reqIb = new RequestIB(remoteDriver);
			String recepients = rds.getValue("DATA", currentTest, "Recepients");
			reqIb.requestInformation(recepients);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}

}
