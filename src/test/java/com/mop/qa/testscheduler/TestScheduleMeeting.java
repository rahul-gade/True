package com.mop.qa.testscheduler;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.SchedulerPages.SchedulerAttendees;
import com.mop.qa.SchedulerPages.SchedulerDateAndTime;
import com.mop.qa.SchedulerPages.SchedulerLocation;
import com.mop.qa.SchedulerPages.SchedulerMeetingTitle;
import com.mop.qa.testbase.TestBase;

public class TestScheduleMeeting extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestScheduleMeeting.class.getName());

	@Test
	public void scheduleMeeting() {
		try {
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("SCHEDULER", currentTest, "URL");
			String uname = rds.getValue("SCHEDULER", currentTest, "UserName");
			String pwd = rds.getValue("SCHEDULER", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);

			SchedulerMeetingTitle sTitle = new SchedulerMeetingTitle(remoteDriver);
			String meetingTitle = rds.getValue("SCHEDULER", currentTest, "MeetingTitle");
			String projectName = rds.getValue("SCHEDULER", currentTest, "ProjectName");
			sTitle.enterTitleandProject(remoteDriver, meetingTitle, projectName);

			SchedulerAttendees sAttend = new SchedulerAttendees(remoteDriver);
			String attend = rds.getValue("SCHEDULER", currentTest, "Attendee");
			String addAttend = rds.getValue("SCHEDULER", currentTest, "AdditionalAttendees");
			sAttend.enterAttendees(remoteDriver, attend, addAttend);

			SchedulerDateAndTime sDateTime = new SchedulerDateAndTime(remoteDriver);
			String date = rds.getValue("SCHEDULER", currentTest, "Date");
			String duration = rds.getValue("SCHEDULER", currentTest, "Duration");
			String timeSlot = rds.getValue("SCHEDULER", currentTest, "Time Slot");
			sDateTime.enterDateAndTime(remoteDriver, date, duration, timeSlot);
//
//			SchedulerLocation sLoc = new SchedulerLocation(remoteDriver);
//			String Location;
//			String bridgeNumber;

			
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
