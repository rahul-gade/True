package com.mop.qa.InvestmentManagement;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import com.mop.qa.testbase.PageBase;

public class IM_SubmitSystemRules extends PageBase{

	public IM_SubmitSystemRules(RemoteWebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.deal-weight strong")
	WebElement dealWeight;	
	@FindBy(css = "div.section-header")
	List<WebElement> sections;	
	@FindBy(css = "a.link")
	WebElement goToDealPage;
	@FindBy(css = "span.dd-title")
	WebElement dealHeader;
	
	public void collapseFieldsAndSubmit() throws Exception{
		if(dealWeight.isDisplayed()) {
			assertTrue("Deal Weight is displayed as: "+dealWeight.getText());
			for(WebElement section : sections) {
				click(section, "Section Header");
				Thread.sleep(100);
				if(!section.getAttribute("class").contains("active"))
					assertTrue("Section is collapsed");
				else
					assertFalse("Section is not collapsed");
			}	
			for(WebElement section : sections) {
				click(section, "Section Header");
				Thread.sleep(100);
				if(section.getAttribute("class").contains("active"))
					assertTrue("Section is expanded");
				else
					assertFalse("Section is not expanded");
			}		
			click(goToDealPage, "Go To Deal Page");
			Thread.sleep(2000);
			if(dealHeader.isDisplayed())
				assertTrue("Landed on deal details Page, with project name: "+dealHeader.getText().trim());
			else
				assertFalse("Deal Details Page did not Open.");
		} else 
			assertFalse("Deal Weight is not shown.");

	}
}
