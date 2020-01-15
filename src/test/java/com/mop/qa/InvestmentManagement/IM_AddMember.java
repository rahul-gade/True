package com.mop.qa.InvestmentManagement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_AddMember extends PageBase {

	public IM_AddMember(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "a[resourceuri=IM_ADD_MEMBER_CTA]")
	WebElement addBtn;
	@FindBy(css = "app-add-memeber")
	WebElement addMemberPopup;
	@FindBy(css = "div.header")
	WebElement popUpHeader;
	@FindBy(css = "a.close-btn")
	WebElement closeBtn;
	@FindBy(css = "input[formcontrolname=teamMemberPB]")
	WebElement searchBar;

	String options = "//span[text()='NAME']";
	@FindBy(css = "div.member-list li")
	List<WebElement> memberList;
	@FindBy(css = "div.member-list li:last-of-type")
	WebElement lastMember;

	@FindBy(css = "a.champion-link")
	WebElement makeChampion;
	@FindBy(css = "a.delete-btn")
	WebElement deleteMember;

	public void addMember(RemoteWebDriver driver, String member) throws Exception {
		Thread.sleep(100);
		click(addBtn, "ADD");
		Thread.sleep(200);
		if (addMemberPopup.isDisplayed()) {
			assertTrue("Add Member Popup is displayed");
			assertTrue("Pop-Up header is displayed as: " + popUpHeader.getText());
			int members = memberList.size();
			click(searchBar, "Search member");
			enterText(searchBar, member, "SearchBar");
			Thread.sleep(250);
			click(options.replace("NAME", member), "Member Option");
			Thread.sleep(250);
			if (memberList.size() > members) {
				assertTrue("New Member Added to list");
				assertTrue("Correct Mamber Added",
						lastMember.findElement(By.cssSelector("p.name")).getText().contains(member));
			} else
				assertFalse("New Member Not added");
			click(makeChampion, "Make Champion");
			if (lastMember.findElements(By.cssSelector("span.deal-champion")).size() > 0)
				assertTrue("Member Successfully made champion");
			else
				assertTrue("Making Champion failed");
			click(makeChampion, "Make Champion");
			if (lastMember.findElements(By.cssSelector("span.deal-champion")).size() == 0)
				assertTrue("Member successfully reverted from champion");
			else
				assertTrue("Reverting from Champion failed");
			click(deleteMember, "Delete Member");
			Thread.sleep(250);
			if (memberList.size() == members)
				assertTrue("Member deleted successFully");
			else
				assertFalse("Deleting Member Failed.");
			click(closeBtn, "Close");
			Thread.sleep(250);
			assertTrue("Landed back to deal details page",
					driver.findElements(By.cssSelector("app-add-memeber")).size() == 0);
			Thread.sleep(250);
		} else
			assertFalse("Add Member Pop-up is not displayed");

	}
}
