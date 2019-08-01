package com.mop.qa.test.bvt;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.pageobject.JRAboutThePosition;
import com.mop.qa.pageobject.JRBasicDetails;
import com.mop.qa.pageobject.JRBenchmark;
import com.mop.qa.pageobject.JRDesiredProfile;
import com.mop.qa.pageobject.JRNuancesAndChallenges;
import com.mop.qa.pageobject.JRTeamDetails;
import com.mop.qa.pageobject.ReplicateJR;
import com.mop.qa.pageobject.TalentAcquisitionHome;
import com.mop.qa.testbase.TestBase;

public class TestNewJR extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestNewJR.class.getName());
	@Test
	public void enterNewJRDetails() throws Exception  {
	 try{
		 InvestmentHome inst = new InvestmentHome(remoteDriver);
		 String startURL = rds.getValue("TALENTACQUISITION", currentTest, "URL");
		 String uname = rds.getValue("TALENTACQUISITION", currentTest, "UserName");
		 String pwd = rds.getValue("TALENTACQUISITION", currentTest, "Password");
		 inst.launchApp(startURL, uname, pwd);
		 Thread.sleep(2000);
		 TalentAcquisitionHome home = new TalentAcquisitionHome(remoteDriver);
		home.clickNewJr(remoteDriver);
		JRBasicDetails basic = new JRBasicDetails(remoteDriver);
		 String companyName = rds.getValue("TALENTACQUISITION", currentTest, "CompanyName");
		 String position = rds.getValue("TALENTACQUISITION", currentTest, "Position");
		 String location = rds.getValue("TALENTACQUISITION", currentTest, "Location");
		basic.enterBasicDetails(remoteDriver, companyName, position, location);
		basic.fillForm(remoteDriver);
		ReplicateJR jr = new ReplicateJR(remoteDriver);
		jr.skipToForm();
		JRAboutThePosition pos = new JRAboutThePosition(remoteDriver);
		pos.enterPositionDetails();
		JRTeamDetails team = new JRTeamDetails(remoteDriver);
		String reportingTo = rds.getValue("TALENTACQUISITION", currentTest, "ReportingTo");
		String reportee = rds.getValue("TALENTACQUISITION", currentTest, "Reportees");
		String stakeholders = rds.getValue("TALENTACQUISITION", currentTest, "StakeHolder");
		String[] reportees = reportee.split(",");
		team.enterTeamDetails(remoteDriver, reportingTo, reportees, stakeholders);
		JRDesiredProfile prof = new JRDesiredProfile(remoteDriver);
		String min = rds.getValue("TALENTACQUISITION", currentTest, "MinExp");
		String max = rds.getValue("TALENTACQUISITION", currentTest, "MaxExp");
		String competency = rds.getValue("TALENTACQUISITION", currentTest, "Competency");
		prof.enterProfileDetails(min, max, competency);
		JRBenchmark mark = new JRBenchmark(remoteDriver);
		String minctc = rds.getValue("TALENTACQUISITION", currentTest, "MinCTC");
		String maxctc = rds.getValue("TALENTACQUISITION", currentTest, "MaxCTC");
		String candidate = rds.getValue("TALENTACQUISITION", currentTest, "Candidate");
		String company = rds.getValue("TALENTACQUISITION", currentTest, "Company");
		String flow = rds.getValue("TALENTACQUISITION", currentTest, "Flow");
		String sectors = rds.getValue("TALENTACQUISITION", currentTest, "Sectors");
		String subsector = rds.getValue("TALENTACQUISITION", currentTest, "SubSectors");
		String choose = rds.getValue("TALENTACQUISITION", currentTest, "Choose");
		mark.enterBenchmarkDetails(remoteDriver, minctc, maxctc, candidate, company, sectors, subsector);
		mark.selectInterviewer(remoteDriver, candidate, choose);
		JRNuancesAndChallenges challenge = new JRNuancesAndChallenges(remoteDriver);
		challenge.enterNuancesandChallengesDetails();
		challenge.selectJRFlow(remoteDriver, flow);
	 }catch(Exception e){
		 LOGGER.info(e);
		 }
 }

}
