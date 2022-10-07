package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.IncidentReportsPage;

public class IncidentReportsPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(IncidentReportsPageTest.class.getName());
	protected BasePage basePage = null;
	protected IncidentReportsPage incidentReportsPage = null;

	/**
	 * Test of navigating to Incident Reports Page
	 */
	@Test(priority = 1)
	public void gotoIncidentReportsPagePage() {
		test = extent.startTest("Navigate to Incident Reports Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		incidentReportsPage = basePage.gotoIncidentReportsPage();
		Assert.assertNotNull(incidentReportsPage);
	}

	/**
	 * Test of create new incident report
	 */
	@Test(priority = 3)
	public void verifyCreateNewIncidentReport() {
		test = extent.startTest("Verify create new incident report");
		if (incidentReportsPage.clickCreateReportBtn())
			if (incidentReportsPage.selectFormType())
				if (incidentReportsPage.enterSummary())
					if (incidentReportsPage.setDate())
						if (incidentReportsPage.enterReportPerson())
							if (incidentReportsPage.clickCreateIncidentReportBtn())
							Assert.assertTrue(incidentReportsPage.isSuccessMsgPresent());
	}

	/**
	 * check update incident report
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void verifyUpdateIncidentReport() throws Exception {
		test = extent.startTest("Verify update incident report");
		SeleniumWrapper.refreshWebPage(driver);
		if (incidentReportsPage.openIncidentReport())
			if (incidentReportsPage.enterMsg())
				if (incidentReportsPage.uploadFile())
					Assert.assertTrue(incidentReportsPage.clickUpdateIncidentReportBtn());
	}

	/**
	 * Test of delete incident report
	 */
	@Test(priority = 9)
	public void verifyDeleteIncidentReport() {
		test = extent.startTest("Verify delete incident report");
		if (incidentReportsPage.goBackIncidentReportTab())
			if (incidentReportsPage.deleteIncidentReport())
			Assert.assertTrue(incidentReportsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of search incident report
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void verifySearchIncidentReport() throws Exception {
		test = extent.startTest("Verify search incident report");
		Assert.assertTrue(incidentReportsPage.searchReport());
	}

	/**
	 * Test of sort column ID
	 */
	@Test(priority = 11)
	public void verifySortColumnID() throws Exception {
		test = extent.startTest("Verify sort column ID");
		SeleniumWrapper.refreshWebPage(driver);
		if (incidentReportsPage.goBackIncidentReportTab())
			Assert.assertTrue(incidentReportsPage.sortByColumnID());
	}

	/**
	 * Test of sort column Date Created
	 */
	@Test(priority = 13)
	public void verifySortColumnDateCreated() {
		test = extent.startTest("Verify sort column Date Created");
		Assert.assertTrue(incidentReportsPage.sortByColumnDateCreated());
	}

	/**
	 * Test of sort column Summary
	 */
	@Test(priority = 15)
	public void verifySortColumnSummary() {
		test = extent.startTest("Verify sort column Summary");
		Assert.assertTrue(incidentReportsPage.sortByColumnSummary());
	}

	/**
	 * Test of "Show Deleted Incident Reports"
	 * 
	 * @throws Exception
	 */
	@Test(priority = 17)
	public void verifyShowDeletedIncidentReports() throws Exception {
		test = extent.startTest("Verify 'Show Deleted Incident Reports' ");
		Assert.assertTrue(incidentReportsPage.showDeletedIncidentReports());
	}

	/**
	 * Test of sort column Status
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void verifySortColumnStatus() throws Exception {
		test = extent.startTest("Verify sort column Status");
		SeleniumWrapper.refreshWebPage(driver);
		if (incidentReportsPage.clickShowDeletedCheckBox())
			Assert.assertTrue(incidentReportsPage.sortByColumnStatus());
	}

	/**
	 * Test of restore deleted incident report
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void verifyRestoreDeletedIncidentReport() throws Exception {
		test = extent.startTest("Verify restore deleted incident report");
		SeleniumWrapper.refreshWebPage(driver);
		if (incidentReportsPage.clickShowDeletedCheckBox())
			if (incidentReportsPage.restoreIncidentReport())
				Assert.assertTrue(incidentReportsPage.clickConfirmDeleteBtn());
	}

	/**
	 * Test of delete another incident report
	 */
	@Test(priority = 23)
	public void verifyDeleteAnotherIncidentReport() throws Exception {
		test = extent.startTest("Verify delete another incident report");
		if (incidentReportsPage.goBackIncidentReportTab())
			if (incidentReportsPage.deleteIncidentReport())
			Assert.assertTrue(incidentReportsPage.isSuccessMsgPresent());
	}

}
