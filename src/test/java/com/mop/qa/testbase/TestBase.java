package com.mop.qa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.opencv.core.Core;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.mop.qa.Utilities.ExtentUtility;
import com.mop.qa.Utilities.ReadDataSheet;
import io.appium.java_client.AppiumDriver;

/**
 * This the Test Base class
 * 
 * @author SAT
 *
 */
public class TestBase {
	final static Logger LOGGER = Logger.getLogger(TestBase.class);
	public static long startTime;
	public static String startTimeUpdate;
	public static long endTime;
	public static long totalTime;
	public static String totalTimeTaken;
	public static String osType = System.getProperty("os.name");
	public String toolName;
	public String appType;
	public String remoteUrl;
	public AppiumDriver appiumDriver;
	public RemoteWebDriver remoteDriver;
	public String udid;
	public String appiumPort;
	public String currentTest;
	public String className;
	public ReadDataSheet rds = new ReadDataSheet();
	public String deviceName;
	public String startURL;

	/**
	 * @param ctx
	 */
	@BeforeSuite
	public void executeSuite(ITestContext ctx) {
		try {
			ExtentUtility.extent = ExtentUtility.getReporter();
			LOGGER.info("Before Suite");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param ist
	 */
	public void getSuiteName(ISuite ist) {

		try {
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("SuiteName" + ist.getName());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param ctx
	 */
	@BeforeTest
	public void startTestReport(ITestContext ctx ) {

		try {
			LOGGER.info("Before Test");
			if (getPropertyValue("Video_solution").equalsIgnoreCase("Yes"))
				System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			String browser = ctx.getCurrentXmlTest().getParameter("browser");
			
			toolName = ctx.getCurrentXmlTest().getParameter("toolName");
			String exeType = ctx.getCurrentXmlTest().getParameter("ExecutionType");
			String localityType = ctx.getCurrentXmlTest().getParameter("Locality");
			String platformName = "";
			remoteUrl = ctx.getCurrentXmlTest().getParameter("RemoteUrl");

			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("toolname is " + ctx.getCurrentXmlTest().getParameter("toolName"));
			}

			ExtentUtility.test = ExtentUtility.startTest(ctx.getCurrentXmlTest().getParameter("testname"));
			currentTest = ctx.getCurrentXmlTest().getParameter("testname");		
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug(currentTest + "is Running");
			}
			
			if (toolName.equalsIgnoreCase("Services")) {
			} else if (toolName.equalsIgnoreCase("Appium")) {
				deviceName = ctx.getCurrentXmlTest().getParameter("deviceName");
			//	appiumPort = getPort();
				appiumPort = "4723";
				appType = ctx.getCurrentXmlTest().getParameter("appType");
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("App type is " + ctx.getCurrentXmlTest().getParameter("appType"));
				}
				udid = ctx.getCurrentXmlTest().getParameter("udid");
				platformName = ctx.getCurrentXmlTest().getParameter("platformName");
				if ((platformName.equalsIgnoreCase("iOS")) && (appType.equalsIgnoreCase("Web"))) {
					startURL = rds.getValue("DATA", currentTest, "URL");
				}
				if (!localityType.equalsIgnoreCase("Cloud")) {
				//	startAppiumServer(udid, appiumPort,platformName);
				}
				Thread.sleep(5000);
				initiateDriver(localityType, appiumPort, browser, remoteUrl, platformName, toolName, appType,
						deviceName, startURL);

			} else {
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("browser is selenium" + ctx.getCurrentXmlTest().getParameter("browser"));
				}
				initiateDriver(localityType, appiumPort, browser, remoteUrl, platformName, toolName, appType,
						deviceName, startURL);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("test name is " + ctx.getCurrentXmlTest().getParameter("testname1"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @throws Exception
	 */
	@AfterTest
	public void afterTest() throws Exception {
		LOGGER.info("After Test");
		if (toolName.equalsIgnoreCase("Appium")) {
			if (appiumDriver != null) {
				Thread.sleep(5000);
				//appiumDriver.close();
			}
		} else if (toolName.equalsIgnoreCase("selenium")) {
			if (remoteDriver != null) {
				Thread.sleep(5000);
				remoteDriver.close();
			}
		}

		ExtentUtility.extent.endTest(ExtentUtility.getTest());

	}

	/**
	 * @throws Exception
	 */
	@AfterSuite
	public void finishExecution() throws Exception {
		
			

		LOGGER.info("After suite");
		try {
			if (toolName.equalsIgnoreCase("Appium")) {
				if (appiumDriver != null) {
					appiumDriver.quit();
				}
			} else {
				if (remoteDriver != null) {
					remoteDriver.quit();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			stopServer();

			/*File folder = new File("ReportGenerator\\"+ExtentUtility.reportFolder+"\\Screenshots");
			String file_Path="D:\\Image_Compress";
			String file_Path2="D:\\Image_Compress\\Image_2";
			takeSnapShot(remoteDriver,file_Path);*/

			ExtentUtility.getReporter().flush();

		}
	}
	
	
	/**
	 * Purpose of this method is to take SnapShot
	 * @param webdriver
	 * @param fileWithPath
	 * @throws Exception
	 */
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot =((TakesScreenshot)webdriver);

		//Call getScreenshotAs method to create image file

		File srcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination

		File destFile=new File(fileWithPath);

		//Copy file at destination

		FileUtils.copyFile(srcFile, destFile);

	}


	/**
	 * This method started AppiumServer
	 * @param udid
	 * @param port
	 * @param platformName
	 */
	public void startAppiumServer(String udid, String port,String platformName) {
		try {
			String chromePort = getPort();
			String wdaport = getPort();
			String bootstrapPort = getPort();
			String nodePath = getPropertyValue("nodePath");
			String appiumPath = getPropertyValue("appiumJSPath");
			CommandLine command = new CommandLine(nodePath);
			command.addArgument(appiumPath, false);
			command.addArgument("--address", false);
			command.addArgument("0.0.0.0");
			command.addArgument("--port", false);
			command.addArgument(port);
			command.addArgument("--session-override", false);
			command.addArgument("--bootstrap-port", false);
			command.addArgument(bootstrapPort);
			command.addArgument("--chromedriver-port", false);
			command.addArgument(chromePort);
			command.addArgument("--chromedriver-executable",false);
			command.addArgument("C:\\Users\\689082\\Downloads\\Project\\Unified_Automation_Framework_User_Doc_V2.0\\UAF\\UAF\\chromedriver.exe");
			if(platformName.equalsIgnoreCase("iOS"))
			{
				command.addArgument("--webdriveragent-port", false);
				command.addArgument(wdaport);
			}
			command.addArgument("--udid", false);
			command.addArgument(udid);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);

			executor.execute(command, resultHandler);
			Thread.sleep(5000);
			LOGGER.info("Appium server started.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This will check for free ports
	 * @return
	 * @throws Exception
	 */
	public String getPort() throws Exception {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		String port = Integer.toString(socket.getLocalPort());
		socket.close();
		return port;
	}

	/**
	 * This method initiates AppiumDriver
	 * @param localityType
	 * @param port
	 * @param browser
	 * @param remoteUrl
	 * @param platformName
	 * @param toolName
	 * @param appType
	 * @param deviceName
	 * @param startURL
	 */
	public void initiateDriver(String localityType, String port, String browser, String remoteUrl, String platformName,
			String toolName, String appType, String deviceName, String startURL) {
		try {

			if (toolName.equalsIgnoreCase("Appium")) {
				LOGGER.info("driver start");
				PageBase pagebaseclass = new PageBase(appiumDriver);
				appiumDriver = pagebaseclass.launchApp(udid, localityType, remoteUrl, toolName, appType, port,
						platformName, deviceName, startURL);
			}

			if (toolName.equalsIgnoreCase("selenium")) {
				PageBase pagebaseclass = new PageBase(remoteDriver);
				remoteDriver = pagebaseclass.launchSite(browser, localityType, remoteUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To stop the Servers
	 */
	public static void stopServer() {
		try {
			LOGGER.info("Stop server");
			String filePath = "";
			String filePath1 = "";
			if (System.getProperty("os.name").contains("Win")) {

				filePath = "taskkill /F /IM node.exe";
				Runtime.getRuntime().exec(filePath);
				filePath1 = "taskkill /F /IM chromedriver.exe";
				Runtime.getRuntime().exec(filePath1);
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
				Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			} else {

				Runtime.getRuntime().exec(new String[] { "bash", "-c", "killall node" });
				Runtime.getRuntime().exec(new String[] { "bash", "-c", "killall chrome" });
				Runtime.getRuntime().exec(new String[] { "bash", "-c", "killall safari" });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method gets Property value for a particular 'key'
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getPropertyValue(String key) throws IOException {
		String value = "";
		try {

			InputStream fileInputStream = Files.newInputStream(Paths.get("data.properties"));
			
			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	/**
	 * This method updates Property file with the given input
	 * @param updateTime
	 * @param startTime
	 */
	public static void updateProperty(String updateTime, String startTime) {
		try {

			InputStream in = Files.newInputStream(Paths.get("report.properties"));
			Properties props = new Properties();
			props.load(in);
			in.close();

			OutputStream out = Files.newOutputStream(Paths.get("report.properties"));
			props.setProperty("TOTAL_TIME", totalTimeTaken.toString());
			props.setProperty("RUN_STARTED", startTime.toString());
			props.store(out, null);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
