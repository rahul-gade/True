package com.mop.qa.pageobject;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;	
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mop.qa.testbase.PageBase;

public class FileUpload extends PageBase{
	public FileUpload(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	} 
	
	public void fileUpload(String filePath) throws AWTException, InterruptedException {
		StringSelection strSel = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(strSel,null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);	
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
