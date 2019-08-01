package com.mop.qa.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class JRAboutThePosition extends PageBase{
	public JRAboutThePosition(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	@FindBy(xpath = "(//a[@class='add-more-btn'])[1]")
	private WebElement libBackground;
	@FindBy(xpath = "(//div[@class='search-values']//li[@class='ng-star-inserted'])[1]")
	private WebElement select1;
	@FindBy(xpath = "(//div[@class='search-values']//li[@class='ng-star-inserted'])[3]")
	private WebElement select2;
	@FindBy(xpath = "//span[text()='ADD']")
	private WebElement btnAdd;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[2]")
	private WebElement libLibrary;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[3]")
	private WebElement libKpi;
	@FindBy(xpath = "(//a[@class='add-more-btn'])[4]")
	private WebElement libCareer;
	@FindBy(xpath = "(//span[text()='NEXT'])[2]")
	private WebElement btnNext;
	
	public void enterPositionDetails() throws Exception {	
		Thread.sleep(1000);
		click(libBackground, "Background Library");
		Thread.sleep(1000);
		click(select1, "Background 1st Selection");
	//	click(select2, "Background 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(libLibrary, "Library Library");
		Thread.sleep(1000);
		click(select1, "Library 1st Selection");
	//	click(select2, "Library 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(libKpi, "KPI Library");
		Thread.sleep(1000);
	//	click(select1, "KPI 1st Selection");
		click(select2, "KPI 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(libCareer, "Career Library");
		Thread.sleep(1000);
	//	click(select1, "Career 1st Selection");
		click(select2, "Career 2nd Selection");
		click(btnAdd, "ADD");
		Thread.sleep(1000);
		click(btnNext, "NEXT");
		Thread.sleep(1000);
	}
}
