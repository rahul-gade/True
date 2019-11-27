package com.mop.qa.testscheduler;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.InvestmentHome;
import com.mop.qa.SchedulerPages.SchedulerAttendees;
import com.mop.qa.SchedulerPages.SchedulerDateAndTime;
import com.mop.qa.SchedulerPages.SchedulerHome;
import com.mop.qa.SchedulerPages.SchedulerLocation;
import com.mop.qa.SchedulerPages.SchedulerMeetingTitle;
import com.mop.qa.SchedulerPages.SchedulerScheduleMeeting;
import com.mop.qa.Utilities.MPException;
import com.mop.qa.testbase.TestBase;

public class TestScheduleMeeting extends TestBase {
	private static final Logger LOGGER = Logger.getLogger(TestScheduleMeeting.class.getName());

	@Test
	public void scheduleMeeting() {
		try {
			String scheduleType = rds.getValue("SCHEDULER", currentTest, "ScheduleType");
			InvestmentHome inst = new InvestmentHome(remoteDriver);
			String startURL = rds.getValue("SCHEDULER", currentTest, "URL");
			String uname = rds.getValue("SCHEDULER", currentTest, "UserName");
			String pwd = rds.getValue("SCHEDULER", currentTest, "Password");
			inst.launchApp(startURL, uname, pwd);

			// COLLECTING DATA AND CREATING OBJECTS
			// Scheduler HomePage
			SchedulerHome sHome = new SchedulerHome(remoteDriver);
			// Title Page Details
			SchedulerMeetingTitle sTitle = new SchedulerMeetingTitle(remoteDriver);
			String meetingTitle = rds.getValue("SCHEDULER", currentTest, "MeetingTitle");
			String projectName = rds.getValue("SCHEDULER", currentTest, "ProjectName");
			// Attendees Page Details
			SchedulerAttendees sAttend = new SchedulerAttendees(remoteDriver);
			String attend = rds.getValue("SCHEDULER", currentTest, "Attendee");
			String addAttend = rds.getValue("SCHEDULER", currentTest, "AdditionalAttendees");
			// Date Time Page Object
			SchedulerDateAndTime sDateTime = new SchedulerDateAndTime(remoteDriver);
			// Location Page
			SchedulerLocation sLoc = new SchedulerLocation(remoteDriver);
			String location = rds.getValue("SCHEDULER", currentTest, "Location");
			String bridgeNumber = rds.getValue("SCHEDULER", currentTest, "BridgeNumber");
			// Schedule Page
			SchedulerScheduleMeeting sMeeting = new SchedulerScheduleMeeting(remoteDriver);
			String agenda = rds.getValue("SCHEDULER", currentTest, "Agenda");
			String flexibility = rds.getValue("SCHEDULER", currentTest, "Flexibility");

			switch (scheduleType) {
			case "Full Journey":
				sTitle.enterTitleandProject(remoteDriver, meetingTitle, projectName);
				sAttend.enterAttendees(remoteDriver, attend, addAttend);
				String duration = rds.getValue("SCHEDULER", currentTest, "Duration");
				sDateTime.selectTimeSlot(remoteDriver, duration);
				sLoc.enterLocation(remoteDriver, location, bridgeNumber);
				sMeeting.checkMeetingDetails(remoteDriver, sDateTime.selectedDate, sDateTime.selectedTime, bridgeNumber,
						sLoc.selectedLocation);
				sMeeting.enterFlexibilityAgenda(remoteDriver, flexibility, agenda);
				sMeeting.scheduleMeeting(remoteDriver);
				sHome.testCreatedMeeting(remoteDriver, sTitle.m_Title);

			case "Customize":
				sTitle.enterTitleandProject(remoteDriver, meetingTitle, projectName);
				sAttend.enterAttendees(remoteDriver, attend, addAttend);
				String startTime = rds.getValue("SCHEDULER", currentTest, "BeginTime");
				String endTime = rds.getValue("SCHEDULER", currentTest, "EndTime");
				sDateTime.enterCustomeDateTime(remoteDriver, startTime, endTime);
				sLoc.enterLocation(remoteDriver, location, bridgeNumber);
				sMeeting.checkMeetingDetails(remoteDriver, sDateTime.selectedDate, sDateTime.selectedTime, bridgeNumber,
						sLoc.selectedLocation);
				sMeeting.enterFlexibilityAgenda(remoteDriver, flexibility, agenda);
				sMeeting.scheduleMeeting(remoteDriver);
				sHome.testCreatedMeeting(remoteDriver, sTitle.m_Title);

			case "From Slot":
				sHome.findSlot(remoteDriver);
			}
		} catch (Exception e) {
			LOGGER.info(e);
		}
	}
}
