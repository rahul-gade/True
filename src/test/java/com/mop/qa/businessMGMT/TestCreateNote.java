package com.mop.qa.businessMGMT;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.NoteTaker.NT_Home;
import com.mop.qa.testbase.TestBase;

public class TestCreateNote extends TestBase{
	public static final Logger LOGGER = Logger.getLogger(TestVisionAndMission.class.getName());
	
	@Test
	public void testCreateNote() {
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
			
			BM_Notes BNote = new BM_Notes(remoteDriver);
			BNote.leadHere(remoteDriver);
			BNote.openNewNote(remoteDriver);
			BNote.createNote(remoteDriver, "TAB");
			BNote.openNewNoteQA(remoteDriver);
			BNote.createNote(remoteDriver, "QA");
			
			NT_Home nHome = new NT_Home(remoteDriver);
			String URL = rds.getValue("BMGMT", currentTest, "NTURL");
			nHome.openNoteTaker(remoteDriver, URL);
			nHome.findBMNotes(remoteDriver, BNote.tabTitle, BNote.qANTitle);
			nHome.deleteNote(remoteDriver, BNote.tabTitle);
			home.openBM(remoteDriver, startURL);
			home.findProject(remoteDriver, stage, company, header);
			BNote.leadHere(remoteDriver);
			BNote.confirmDelete(remoteDriver, BNote.tabTitle);
			BNote.deleteNotes(remoteDriver);
			nHome.openNoteTaker(remoteDriver, URL);
			nHome.confirmDelete(remoteDriver, BNote.qANTitle);
//			TODO check integration by visiting Note Take actually!
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
