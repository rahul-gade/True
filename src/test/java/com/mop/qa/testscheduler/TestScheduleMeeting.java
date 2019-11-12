package com.mop.qa.testscheduler;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.SchedulerPages.SchedulerAttendees;
import com.mop.qa.SchedulerPages.SchedulerDateAndTime;
import com.mop.qa.SchedulerPages.SchedulerLocation;
import com.mop.qa.SchedulerPages.SchedulerMeetingTitle;
import com.mop.qa.SchedulerPages.SchedulerScheduleMeeting;
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
			String duration = rds.getValue("SCHEDULER", currentTest, "Duration");
			sDateTime.enterDateAndTime(remoteDriver, duration);

			SchedulerLocation sLoc = new SchedulerLocation(remoteDriver);
			String location = rds.getValue("SCHEDULER", currentTest, "Location");
			String bridgeNumber = rds.getValue("SCHEDULER", currentTest, "BridgeNumber");
			System.out.println(bridgeNumber);
			sLoc.enterLocation(remoteDriver, location, bridgeNumber);
			
			SchedulerScheduleMeeting sMeeting = new SchedulerScheduleMeeting(remoteDriver);
			String flexibility = rds.getValue("SCHEDULER", currentTest, "Flexibility");
			String agenda = rds.getValue("SCHEDULER", currentTest, "Agenda");
			sMeeting.checkMeetingDetails(remoteDriver, sDateTime.selectedDate, sDateTime.selectedTime, bridgeNumber, sLoc.selectedLocation);
			sMeeting.enterFlexibilityAgenda(remoteDriver, flexibility, agenda);
			
			
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
