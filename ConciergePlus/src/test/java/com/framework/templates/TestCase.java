package com.framework.templates;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.framework.helpers.ExtentReportHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import config.Constants;
import io.restassured.RestAssured;

public class TestCase {
	protected final static Logger logger = LogManager.getLogger(TestCase.class.getName());

	protected static WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected static String API_TEST_BASE_URL = "";

	/**
	 * Prepare per BeforeSuite annotation.
	 */
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		DOMConfigurator.configure(Constants.RESOURCE_FOLDER + "log4j-config.xml");
		logger.info(Constants.RESOURCE_FOLDER + "log4j-config.xml");
		logger.info(logger.getAllAppenders());
		logger.info("-----------------------Beginning of suite----------------------");
	}

	/**
	 * Prepare per BeforeTest annotation.
	 * 
	 * @param Browser    type used by GUI test
	 */
	@Parameters({ "browser", "URL" })
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String browser, String baseURI) {
		logger.info("-----------------------Beginning of test-----------------------");
		API_TEST_BASE_URL = baseURI;
		RestAssured.baseURI = baseURI;
		logger.info("API test base URL: " + baseURI);
		extent = ExtentReportHelper.getExtentReporter(browser);
	}

	/**
	 * Cleanup per AfterTest annotation.
	 */
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		logger.info("----------------------Ending of test--------------------------");
	}

	/**
	 * Cleanup per AfterTest annotation.
	 */
	@AfterSuite(alwaysRun = true)
	protected void afterSuite() {
		extent.flush();
		extent.close();
		logger.info("***** Extent report ready to use! *****");
		logger.info("----------------------Ending of suite-------------------------");
	}
}