package tests.maintenance;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.maintenance.ServiceRequestsPage;

public class ServiceRequestsPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(ServiceRequestsPageTest.class.getName());
	protected BasePage basePage = null;
	protected ServiceRequestsPage serviceRequestsPage = null;

	/**
	 * Test of navigating to Service Requests page
	 */
	@Test(priority = 1)
	public void gotoServiceRequestsPage() {
		test = extent.startTest("Navigate to Service Requests page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		serviceRequestsPage = basePage.gotoServiceRequestsPage();
		Assert.assertNotNull(serviceRequestsPage);
	}

	/**
	 * Test user Resident log in application
	 */
	@Test(priority = 3)
	public void testUserResidentLogInApplication() {
		test = extent.startTest("Test user Resident log in application");
		if (serviceRequestsPage.logOutAsCurrentUser(driver))
			if (serviceRequestsPage.logInAsResidentUser(driver))
				if (serviceRequestsPage.isUserResidentLoggedInApplication())
					Assert.assertTrue(serviceRequestsPage.navigateToServiceRequestsPage());
	}

	/**
	 * Test user Resident cant see internal note on service request
	 */
	@Test(priority = 5)
	public void testUserResidentCantSeeInternalNote() {
		test = extent.startTest("Test user Resident cant see internal note");
		if (serviceRequestsPage.userResidentCantSeeInternalNote())
			Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test user Resident reply service request
	 */
	@Test(priority = 7)
	public void testUserResidentReplyServiceRequest() {
		test = extent.startTest("Test user Resident reply service request");
		if (serviceRequestsPage.openWaitingForYourResponseRecordByResident())
			if (serviceRequestsPage.clickTxtBox())
				if (serviceRequestsPage.msgByResidentUser())
					if (serviceRequestsPage.clickUpdateTicketBtn())
						Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Check Resident user create new service request of "Power off"
	 */
	@Test(priority = 9)
	public void testResidentUserCreateServiceRequestOfPowerOff() {
		test = extent.startTest("Test Resident user create a service request of 'Power Off'");
		if (serviceRequestsPage.clickCreateServiceRequestBtn())
			if (serviceRequestsPage.enterSubjectOfNoPower())
				if (serviceRequestsPage.checkAllowAccessToMyUnitBox())
					if (serviceRequestsPage.clickTxtBox())
						if (serviceRequestsPage.enterDescOfPowerOff())
							if (serviceRequestsPage.clickSubmitRequestBtn())
								if (serviceRequestsPage.isSuccessMsgPresent())
									Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Check Resident user create new service request of "Water Leaking"
	 */
	@Test(priority = 11)
	public void testResidentUserCreateServiceRequestOfWaterLeaking() {
		test = extent.startTest("Test Resident user create a service request of 'Water leaking'");
		if (serviceRequestsPage.clickCreateServiceRequestBtn())
			if (serviceRequestsPage.pickCategoryOfPlumbing())
				if (serviceRequestsPage.enterSubjectOfWaterLeaking())
					if (serviceRequestsPage.checkAllowAccessToMyUnitBox())
						if (serviceRequestsPage.clickTxtBox())
							if (serviceRequestsPage.enterDescOfWaterLeaking())
								if (serviceRequestsPage.clickSubmitRequestBtn())
									if (serviceRequestsPage.isSuccessMsgPresent())
										Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test Resident user close service request
	 */
	@Test(priority = 13)
	public void testResidentUserCloseServiceRequest() throws Exception {
		test = extent.startTest("Test Resident user close service request");
		if (serviceRequestsPage.openNewStatusRecordByResident())
			if (serviceRequestsPage.clickTxtBox())
				if (serviceRequestsPage.enterResolvedMsg())
					if (serviceRequestsPage.clickUpdateAndCloseTicketBtn())
						if (serviceRequestsPage.goBackToServiceRequestTab())
							if (serviceRequestsPage.goToResolvedTab())
								Assert.assertTrue(serviceRequestsPage.isMovedToResolvedTabAndUpdatedStatus_R());
	}

//***********************************************************************************************************************
	/**
	 * Test default user PM log in application and navigate to Service Request
	 * module
	 */
	@Test(priority = 15)
	public void userPMLogInAndNavigateToServiceRequestsPage() {
		test = extent.startTest("Default user PM log in application and navigate to Service Request page");
		if (serviceRequestsPage.logOutAsCurrentUser(driver))
			basePage.navigateTo();
		serviceRequestsPage = basePage.gotoServiceRequestsPage();
		Assert.assertNotNull(serviceRequestsPage);
	}

	/**
	 * Test user PM reply new service request by internal note from Current tab
	 */
	@Test(priority = 17)
	public void testReplyNewServiceRequestInternallyFromCurrentTab() {
		test = extent.startTest("Test reply new service request by internal note from Current tab");
		if (serviceRequestsPage.openNewStatusRecord())
			if (serviceRequestsPage.clickInternalNoteTab())
				if (serviceRequestsPage.clickTxtBox())
					if (serviceRequestsPage.enterInternalMsg())
						if (serviceRequestsPage.clickUpdateTicketBtn())
							Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test user PM update new service request status to "Waiting for Client
	 * Response" from Current tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void testUpdateStatusToWaitForClientResponseFromCurrentTab() throws Exception {
		test = extent.startTest("Test update status of 'New' to 'Waiting for Client Response' from Current tab");
		if (serviceRequestsPage.openNewStatusRecord())
			if (serviceRequestsPage.updateStatusToWaitForClientResponse())
				if (serviceRequestsPage.clickTxtBox())
					if (serviceRequestsPage.enterMsgToClient())
						if (serviceRequestsPage.clickUpdateTicketBtn())
							if (serviceRequestsPage.goBackToServiceRequestTab())
								if (serviceRequestsPage.isNewServiceRequestRemoveFromCurrentTab())
									SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.verifyServiceRequestMoveToWaitingTab())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test default user Property Manager(PM) create a service request
	 */
	@Test(priority = 21)
	public void testCreateServiceRequest() {
		test = extent.startTest("Test create a service request");
		if (serviceRequestsPage.clickCreateServiceRequestBtn())
			if (serviceRequestsPage.pickCategoryOfPlumbing())
				if (serviceRequestsPage.pickPriorityOfHigh())
					if (serviceRequestsPage.enterSubjectOfWaterLeaking())
						if (serviceRequestsPage.searchAndPickUnit())
							if (serviceRequestsPage.checkAllowAccessToMyUnitBox())
								if (serviceRequestsPage.enterDescOfWaterLeaking())
									if (serviceRequestsPage.closeEditPopup())
										if (serviceRequestsPage.clickSubmitRequestBtn())
											if (serviceRequestsPage.isSuccessMsgPresent())
												Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test default user Property Manager(PM) create another service request
	 */
	@Test(priority = 23)
	public void testCreateAnotherServiceRequest() {
		test = extent.startTest("Test create another service request");
		if (serviceRequestsPage.clickCreateServiceRequestBtn())
			if (serviceRequestsPage.enterSubjectOfNoPower())
				if (serviceRequestsPage.searchAndPickUnit())
					if (serviceRequestsPage.enterDescOfPowerOff())
						if (serviceRequestsPage.closeEditPopup())
							if (serviceRequestsPage.clickSubmitRequestBtn())
								if (serviceRequestsPage.isSuccessMsgPresent())
									Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test update new service request status to "Working On it" from Current tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 25)
	public void testUpdateStatusToWorkingOnItFromCurrentTab() throws Exception {
		test = extent.startTest("Test update new service request status to 'Working On it' from Current tab");
		if (serviceRequestsPage.openNewStatusRecord())
			if (serviceRequestsPage.clickTxtBox())
				if (serviceRequestsPage.enterMsgOfWorkOnIt())
					if (serviceRequestsPage.clickUpdateTicketBtn())
						if (serviceRequestsPage.goBackToServiceRequestTab())
							Assert.assertTrue(serviceRequestsPage.verifyStatusUpdatedFromNewToWorkongOnIt());
	}

	/**
	 * Test service request is moved back to Current tab from Waiting tab after
	 * client update it
	 */
	@Test(priority = 27)
	public void isMovedBackFromWaitingTabToCurrentTab() {
		test = extent.startTest("Test service request is moved back to Current tab from Waiting tab");
		Assert.assertTrue(serviceRequestsPage.isMovedBackFromWaitingTabToCurrentTab());
	}

	/**
	 * Test add internal note on "Working On It" status record from Current Tab
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 29)
	public void addInternalNoteOnWaitingForItRecordFromCurrentTab() throws InterruptedException {
		test = extent.startTest("Test add internal note on 'Working On It' status record");
		SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.openWorkingOnItStatusRecord())
			if (serviceRequestsPage.clickInternalNoteTab())
				if (serviceRequestsPage.clickTxtBox())
					if (serviceRequestsPage.enterInternalMsg())
						if (serviceRequestsPage.clickUpdateTicketBtn())
							Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test update status of "Working On It" To "Wait For Client Response' from
	 * Current Tab
	 */
	@Test(priority = 31)
	public void updateStatusOfWaitingForItToWaitForClientResponseFromCurrentTab() {
		test = extent.startTest("Test update status of 'Working On It' To 'Wait For Client Response' from Current Tab");
		if (serviceRequestsPage.openWorkingOnItStatusRecord())
			if (serviceRequestsPage.updateStatusToWaitForClientResponse())
				if (serviceRequestsPage.clickTxtBox())
					if (serviceRequestsPage.enterMsgToClient())
						if (serviceRequestsPage.clickUpdateTicketBtn())
							Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test resolve new service request from Current tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 33)
	public void testUpdateStatusToResolvedFromCurrentTab() throws Exception {
		test = extent.startTest("Test update service request status to 'Resolved' from Current tab");
		if (serviceRequestsPage.openTicketUpdatedStatusRecord())
			if (serviceRequestsPage.updateStatusToResolved())
				if (serviceRequestsPage.clickTxtBox())
					if (serviceRequestsPage.enterResolvedMsg())
						if (serviceRequestsPage.clickUpdateTicketBtn())
							if (serviceRequestsPage.goBackToServiceRequestTab())
								if (serviceRequestsPage.IsRemoveFromCurrentTab())
									SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.isMovedToResolvedTabAndUpdatedStatus_P())
				Assert.assertTrue(serviceRequestsPage.goToCurrentTab());
	}

	/**
	 * Test of search a service request from Current tab
	 */
	@Test(priority = 35)
	public void testSearchServiceRequestFromCurrentTab() {
		test = extent.startTest("Test search a service request from Current tab");
		Assert.assertTrue(serviceRequestsPage.searchServiceRequestFromCurrentTab());
	}

	/**
	 * Test of sort per column Subject from Current tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 37)
	public void testSortPerColumnSubjectFromCurrentTab() throws Exception {
		test = extent.startTest("Test sort per column Subject from Current tab");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(serviceRequestsPage.sortByColumnSubject_Current());
	}

	/**
	 * Test of sort per column ID from Current tab
	 */
	@Test(priority = 39)
	public void testSortPerColumnIDFromCurrentTab() {
		test = extent.startTest("Test sort per column ID from Current tab");
		Assert.assertTrue(serviceRequestsPage.sortByColumnID_Current());
	}

	/**
	 * Test of sort per column Unit from Current tab
	 */
	@Test(priority = 41)
	public void testSortPerColumnUnitFromCurrentTab() {
		test = extent.startTest("Test sort per column Unit from Current tab");
		Assert.assertTrue(serviceRequestsPage.sortByColumnUnit_Current());
	}

	/**
	 * Test of sort per column Category from Current tab
	 */
	@Test(priority = 43)
	public void testSortPerColumnCategoryFromCurrentTab() {
		test = extent.startTest("Test sort per column Category from Current tab");
		Assert.assertTrue(serviceRequestsPage.sortByColumnCategory_Current());
	}

	/**
	 * Test of sort per column Status from Current tab
	 */
	@Test(priority = 45)
	public void testSortPerColumnStatusFromCurrentTab() {
		test = extent.startTest("Test sort per column Status from Current tab");
		Assert.assertTrue(serviceRequestsPage.sortByColumnStatus_Current());
	}

	/**
	 * Test of sort per column Last Update from Current tab
	 */
	@Test(priority = 47)
	public void testSortPerColumnLastUpdateFromCurrentTab() {
		test = extent.startTest("Test sort per column Last Update from Current tab");
		Assert.assertTrue(serviceRequestsPage.sortByColumnLastUpdate_Current());
	}

	/**
	 * Test of edit service request from Current tab
	 */
	@Test(priority = 49)
	public void testEditServiceRequestFromCurrentTab() {
		test = extent.startTest("Test edit a service request from Current tab");
		if (serviceRequestsPage.openNewStatusRecord())
			if (serviceRequestsPage.clickEditIcon())
				if (serviceRequestsPage.modifySubject())
					Assert.assertTrue(serviceRequestsPage.clickSubmitRequestBtn());
	}

	/**
	 * Test of delete service request from Current tab
	 */
	@Test(priority = 51)
	public void testDeleteServiceRequestFromCurrentTab() {
		test = extent.startTest("Test delete a service request from Current tab");
		if (serviceRequestsPage.goBackToServiceRequestTab())
			if (serviceRequestsPage.openNewStatusRecord())
				if (serviceRequestsPage.clickDeleteIcon())
					Assert.assertTrue(serviceRequestsPage.clickConfirmBtn());
	}

	/**
	 * Test of search a service request from Waiting tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 53)
	public void testSearchServiceRequestFromWaitingTab() throws Exception {
		test = extent.startTest("Test search a service request from Waiting tab");
		if (serviceRequestsPage.goToWaitingTab())
			Assert.assertTrue(serviceRequestsPage.searchServiceRequestFromWaitingTab());
	}

	/**
	 * Test of sort per column Subject from Waiting tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 55)
	public void testSortPerColumnSubjectFromWaitingTab() throws Exception {
		test = extent.startTest("Test sort per column Subject from Waiting tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.sortByColumnSubject_Waiting())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column ID from Waiting tab
	 */
	@Test(priority = 57)
	public void testSortPerColumnIDFromWaitingTab() {
		test = extent.startTest("Test sort per column ID from Waiting tab");
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.sortByColumnID_Waiting())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Unit from Waiting tab
	 */
	@Test(priority = 59)
	public void testSortPerColumnUnitFromWaitingTab() {
		test = extent.startTest("Test sort per column Unit from Waiting tab");
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.sortByColumnUnit_Waiting())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Category from Waiting tab
	 */
	@Test(priority = 61)
	public void testSortPerColumnCategoryFromWaitingTab() {
		test = extent.startTest("Test sort per column Category from Waiting tab");
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.sortByColumnCategory_Waiting())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Status from Waiting tab
	 */
	@Test(priority = 63)
	public void testSortPerColumnStatusFromWaitingTab() {
		test = extent.startTest("Test sort per column Status from Waiting tab");
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.sortByColumnStatus_Waiting())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Last Update from Waiting tab
	 */
	@Test(priority = 65)
	public void testSortPerColumnLastUpdateFromWaitingTab() {
		test = extent.startTest("Test sort per column Last Update from Waiting tab");
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.sortByColumnLastUpdate_Waiting())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test reply internal note on service request from Waiting tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 67)
	public void testReplyInternalNoteOnServiceRequestFromWaitingTab() throws Exception {
		test = extent.startTest("Test reply internal note on service request from Waiting tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.openServiceRequestFromWaitingTab())
				if (serviceRequestsPage.clickInternalNoteTab())
					if (serviceRequestsPage.clickTxtBox())
						if (serviceRequestsPage.enterInternalMsg())
							if (serviceRequestsPage.clickUpdateTicketBtn())
								Assert.assertTrue(serviceRequestsPage.goBackToServiceRequestTab());
	}

	/**
	 * Test update status on service request from Waiting tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 69)
	public void testUpdateStatusOnServiceRequestFromWaitingTab() throws Exception {
		test = extent.startTest("Test update status on service request from Waiting tab");
		if (serviceRequestsPage.goToWaitingTab())
			if (serviceRequestsPage.openServiceRequestFromWaitingTab())
				if (serviceRequestsPage.updateStatusToResolved())
					if (serviceRequestsPage.enterResolvedMsg())
						if (serviceRequestsPage.clickUpdateTicketBtn())
							if (serviceRequestsPage.goBackToServiceRequestTab())
								if (serviceRequestsPage.goToWaitingTab())
									if (serviceRequestsPage.IsRemoveFromWaitingTab())
										SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.goToResolvedTab())
			Assert.assertTrue(serviceRequestsPage.verifyServiceRequestMoveFromWaitingToResolvedTab());
	}

	/**
	 * Test of search a service request from Resolved tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 71)
	public void testSearchServiceRequestFromResolvedTab() throws Exception {
		test = extent.startTest("Test search a service request from Resolved tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.goToResolvedTab())
			Assert.assertTrue(serviceRequestsPage.searchServiceRequestFromResolvedTab());
	}

	/**
	 * Test of sort per column Subject from Resolved tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 73)
	public void testSortPerColumnSubjectFromResolvedTab() throws Exception {
		test = extent.startTest("Test sort per column Subject from Resolved tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.sortByColumnSubject_Resolved())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column ID from Resolved tab
	 */
	@Test(priority = 75)
	public void testSortPerColumnIDFromResolvedTab() {
		test = extent.startTest("Test sort per column ID from Resolved tab");
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.sortByColumnID_Resolved())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Unit from Resolved tab
	 */
	@Test(priority = 77)
	public void testSortPerColumnUnitFromResolvedTab() {
		test = extent.startTest("Test sort per column Unit from Resolved tab");
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.sortByColumnUnit_Resolved())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Category from Resolved tab
	 */
	@Test(priority = 79)
	public void testSortPerColumnCategoryFromResolvedTab() {
		test = extent.startTest("Test sort per column Category from Resolved tab");
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.sortByColumnCategory_Resolved())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Status from Resolved tab
	 */
	@Test(priority = 81)
	public void testSortPerColumnStatusFromResolvedTab() {
		test = extent.startTest("Test sort per column Status from Resolved tab");
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.sortByColumnStatus_Resolved())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of sort per column Last Update from Resolved tab
	 */
	@Test(priority = 83)
	public void testSortPerColumnLastUpdateFromResolvedTab() {
		test = extent.startTest("Test sort per column Last Update from Resolved tab");
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.sortByColumnLastUpdate_Resolved())
				Assert.assertTrue(serviceRequestsPage.goBackCurrentTab());
	}

	/**
	 * Test of delete service request from Resolved tab
	 */
	@Test(priority = 85)
	public void testDeleteServiceRequestFromResolvedTab() throws Exception {
		test = extent.startTest("Test delete a service request from Resolved tab");
		if (serviceRequestsPage.goToResolvedTab())
			if (serviceRequestsPage.openServiceRequestFromResolvedTab())
				if (serviceRequestsPage.clickDeleteIcon())
					Assert.assertTrue(serviceRequestsPage.clickConfirmBtn());
	}

}
