package com.mop.qa.InvestmentManagement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.testbase.TestBase;

public class TestHypothesisFull extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestHypothesisFull.class.getName());

	@Test
	public void testHypothesisFull() {
		try {
			String startURL = rds.getValue("INVMGMT", currentTest, "URL");
			String uname = rds.getValue("INVMGMT", currentTest, "UserName");
			String pwd = rds.getValue("INVMGMT", currentTest, "Password");
			IM_Home home = new IM_Home(remoteDriver);
			home.launchApp(remoteDriver, startURL, uname, pwd);
			String project = rds.getValue("INVMGMT", currentTest, "projectName");
			home.findLiveDeal(remoteDriver, project);
			IM_HypothesisTab dealHyp = new IM_HypothesisTab(remoteDriver);
			String category = rds.getValue("INVMGMT", currentTest, "hCategory");
			String title = rds.getValue("INVMGMT", currentTest, "hTitle");
			String desc = rds.getValue("INVMGMT", currentTest, "hDesc");
			dealHyp.createHypothesis(remoteDriver, category, title, desc);
			IM_HypothesisPage hyp = new IM_HypothesisPage(remoteDriver);
			hyp.validateHypothesis(remoteDriver, category, dealHyp.createdHypothesis);
			String postTitle = rds.getValue("INVMGMT", currentTest, "PostTitle");
			String filePath = rds.getValue("INVMGMT", currentTest, "filePath");
			String filePathX = rds.getValue("INVMGMT", currentTest, "filePathX");
			hyp.addPost(remoteDriver, postTitle, filePath, filePathX);
			hyp.editPost(remoteDriver, postTitle);
			hyp.deletePost(remoteDriver);
			String comment = rds.getValue("INVMGMT", currentTest, "Comment");
			String commentReply = rds.getValue("INVMGMT", currentTest, "CommentReply");
			hyp.addComment(remoteDriver, comment, commentReply);
			hyp.deleteComments(remoteDriver);
			hyp.editHypothesis(remoteDriver, dealHyp.createdHypothesis);
			hyp.deleteHypothesis(remoteDriver, dealHyp.createdHypothesis);
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}

}
