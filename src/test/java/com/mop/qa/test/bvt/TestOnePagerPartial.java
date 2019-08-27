package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.CreateNewDocument;
import com.mop.qa.pageobject.CreatedDealPage;
import com.mop.qa.pageobject.InvestmentHome;
//import com.mop.qa.pageobject.OnePager;
import com.mop.qa.pageobject.OnePagerPartial;
import com.mop.qa.testbase.TestBase;

public class TestOnePagerPartial extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestOnePagerPartial.class.getName());

	@Test
	public void partialOnePager() throws Exception {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String projectName = rds.getValue("DATA", currentTest, "ProjectName");
			String companyName = rds.getValue("DATA", currentTest, "CompanyName");
			String sector = rds.getValue("DATA", currentTest, "Sector");
			String startURL = rds.getValue("DATA", currentTest, "URL");
			String uname = rds.getValue("DATA", currentTest, "UserName");
			String pwd = rds.getValue("DATA", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);
			Thread.sleep(1000);
			inst.findLiveDeal(remoteDriver, projectName, companyName, sector);
			OnePagerPartial pager = new OnePagerPartial(remoteDriver);
			CreatedDealPage cdp = new CreatedDealPage(remoteDriver);

			cdp.createNewDocument(remoteDriver);
			CreateNewDocument cnd = new CreateNewDocument(remoteDriver);
			String document = rds.getValue("HYPOTHESIS", currentTest, "DocumentName");
			cnd.createNewDocument(remoteDriver, document);

			String likeText = rds.getValue("HYPOTHESIS", currentTest, "CompetitionLike");
			String disLikeText = rds.getValue("HYPOTHESIS", currentTest, "CompetitionDisLike");
			pager.enterOnePagerTIC(remoteDriver, likeText, disLikeText);
			pager.enterOnePagerIndustry(remoteDriver, likeText, disLikeText);
			pager.closePager(remoteDriver);

			cdp.createNewDocument(remoteDriver);
			cnd.createNewDocument(remoteDriver, document);

			pager.clickNextBtn(remoteDriver);
			pager.industryPageTest(remoteDriver, likeText, disLikeText);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
