package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class JRDesiredProfile extends PageBase{
	public JRDesiredProfile(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "(//a[@class='add-more-btn'])[5]")
	private WebElement libMustEducational;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[6]")
	private WebElement libDesiredEducational;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[7]")
	private WebElement libCertifications;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[8]")
	private WebElement libFunctionalExpertise;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[9]")
	private WebElement libSkills;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[10]")
	private WebElement libCompetency;
	@FindBy(xpath = "(//div[@class='search-values']//li[@class='ng-star-inserted'])[1]//label")
	private WebElement select1;
	@FindBy(xpath = "(//div[@class='search-values']//li[@class='ng-star-inserted'])[2]")
	private WebElement select2;
	@FindBy(xpath = "//span[text()='ADD']")
	private WebElement btnAdd;
	@FindBy(xpath = "//ul[@class='im-bullet-list']")
	private WebElement inputCompetency;
	@FindBy(xpath = "//span[text()='Min']")
	private WebElement labelMin;
	@FindBy(xpath = "//span[text()='Min']/ancestor::div[1]/input")
	private WebElement inputMin;
	@FindBy(xpath = "(//div[@class=\"mat-select-arrow\"])[2]")
	private WebElement minYears;
	@FindBy(xpath = "//span[text()='Max']")
	private WebElement labelMax;
	@FindBy(xpath = "//span[text()='Max']/ancestor::div[1]/input")
	private WebElement inputMax;
	@FindBy(xpath = "(//div[@class=\"mat-select-arrow\"])[3]")
	private WebElement maxYears;
	@FindBy(xpath = "//span[text()='years']")
	private WebElement select;
	@FindBy(xpath = "(//span[text()='years'])[2]")
	private WebElement selectmax;
	@FindBy(xpath = "(//span[text()='NEXT'])[4]")
	private WebElement btnNext;
	
	public void enterProfileDetails(String min, String max, String competency) throws Exception {	
		Thread.sleep(1000);
		click(libMustEducational, "Must Have Educational Qualification Library");
		Thread.sleep(1000);
	//	click(select1, "Must Have Educational Qualification 1st Selection");
		click(select2, "Must Have Educational Qualification 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(libDesiredEducational, "Library Library");
		Thread.sleep(1000);
		click(select1, "Desired Educational Qualification 1st Selection");
	//	click(select2, "Desired Educational Qualification 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(libCertifications, "Certifications Library");
		Thread.sleep(1000);
		click(select1, "Certifications 1st Selection");
	//	click(select2, "Certifications 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(libFunctionalExpertise, "Functional Expertise Library");
		Thread.sleep(1000);
	//	click(select1, "Functional Expertise 1st Selection");
		click(select2, "Functional Expertise 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(libSkills, "Skills Library");
		Thread.sleep(1000);
		click(select1, "Skills 1st Selection");
	//	click(select2, "Skills 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(inputMin, "Minimum");
		enterText(inputMin, min, "Minimum");
		Thread.sleep(1000);
		click(minYears, "Click Min Dropdown");
		click(select, "Select Min Dropdown");
		Thread.sleep(1000);
		click(inputMax, "Maximum");
		enterText(inputMax, max, "Maximum");
		Thread.sleep(1000);
		click(maxYears, "Click max Dropdown");
		click(selectmax, "Select Max Dropdown");
		Thread.sleep(1000);
		click(libCompetency, "Library Competency");
		Thread.sleep(1000);
		click(select1, "Competency Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(inputCompetency, "Input Competency");
		//enterText(inputCompetency, competency, "Input Competency");
		 for (int i=0;i<competency.length();i++) {
			 char c = competency.charAt(i);
			 String s = new StringBuilder().append(c).toString();
			 inputCompetency.sendKeys(s);
			 Thread.sleep(10);
		}
		click(btnNext, "NEXT");
		Thread.sleep(1000);
	}
}
