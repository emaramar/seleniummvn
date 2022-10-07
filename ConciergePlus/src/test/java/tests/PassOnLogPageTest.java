package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.PassOnLogPage;

public class PassOnLogPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(PassOnLogPageTest.class.getName());
	protected BasePage basePage = null;
	protected PassOnLogPage passOnLogPage = null;

	/**
	 * Test of navigating to Pass On Log Page
	 */
	@Test(priority = 1)
	public void gotoPassOnLogPage() {
		test = extent.startTest("Navigate to Pass On Log Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		passOnLogPage = basePage.gotoPassOnLogPage();
		Assert.assertNotNull(passOnLogPage);
	}

	/**
	 * Test of user Concierge go to Pass On Log menu
	 */
	@Test(priority = 3)
	public void verifyUserConciergeGoToPassOnLogMenu() {
		test = extent.startTest("Verify user Concierge go to Pass On Log menu");
		if (passOnLogPage.logOutAsCurrentUser(driver))
			basePage.logInAsConciergeUser(driver);
		basePage.userConciergeClockIn(driver);
		Assert.assertTrue(passOnLogPage.navigateToPassOnLogMenu());
	}

	/**
	 * Test of is "Subject" field mandatory
	 */
	@Test(priority = 5)
	public void verifyIsSubjectFieldMandatory() {
		test = extent.startTest("Verify is 'Subject'field mandatory");
		if (passOnLogPage.clickCreateEntryBtn())
			if (passOnLogPage.clickSaveBtn())
				if (passOnLogPage.isErrorMsgPresent())
					Assert.assertTrue(passOnLogPage.clickCancelBtn());
	}

	/**
	 * Test of set who will receive the new Pass On Log notification
	 */
	@Test(priority = 7)
	public void verifySettingOfWhoWillReceiveNotification() {
		test = extent.startTest("Verify setting of who will receive the new Pass On Log notification");
		if (passOnLogPage.clickCreateEntryBtn())
			if (passOnLogPage.setWhoWillReceiveNotification())
				Assert.assertTrue(passOnLogPage.clickCancelBtn());
	}

	/**
	 * Test of user Concierge create new Pass On Log
	 */
	@Test(priority = 9)
	public void verifyUserConciergeCreateNewPassOnLog() {
		test = extent.startTest("Verify user Concierge create new Pass On Log");
		if (passOnLogPage.clickCreateEntryBtn())
			if (passOnLogPage.enterSubject_1())
				if (passOnLogPage.enterDetail())
					if (passOnLogPage.enterShowThroughDate())
						if (passOnLogPage.setWhoWillReceiveNotification())
							Assert.assertTrue(passOnLogPage.clickSaveBtn());
	}

	/**
	 * Test of user Concierge create another new Pass On Log
	 */
	@Test(priority = 11)
	public void verifyUserConciergeCreateAnotherNewPassOnLog() {
		test = extent.startTest("Verify user Concierge create another new Pass On Log");
		if (passOnLogPage.clickCreateEntryBtn())
			if (passOnLogPage.enterSubject_2())
				if (passOnLogPage.enterDetail())
					if (passOnLogPage.setWhoWillReceiveNotification())
						if (passOnLogPage.clickSaveBtn())
							if (passOnLogPage.logOutAsCurrentUser(driver))
								if (passOnLogPage.userConciergeClockOut(driver))
									basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName,
											GuiTestCase.password);
		basePage.navigateTo();
		passOnLogPage = basePage.gotoPassOnLogPage();
		Assert.assertNotNull(passOnLogPage);
	}

	/**
	 * Test of check status of new pass on log
	 */
	@Test(priority = 13)
	public void verifyStatusOfNewPassOnLog() {
		test = extent.startTest("Verify status of new pass on log");
		Assert.assertTrue(passOnLogPage.statusOfNewPassOnLogs());
	}

	/**
	 * Test of search active pass on log
	 */
	@Test(priority = 15)
	public void verifySearchActivePassOnLog() {
		test = extent.startTest("Verify search active pass on log");
		Assert.assertTrue(passOnLogPage.searchActivePassOnLog());
	}

	/**
	 * Test of who has read this pass on log from All tab
	 */
	@Test(priority = 17)
	public void verifyWhoHasReadThisPassOnLogFromALLTab() {
		test = extent.startTest("Verify who has read this pass on log from All tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (passOnLogPage.clickViewDetailIconFromAllTab())
			if (passOnLogPage.verifyWhoHasReadPassOnLog())
				Assert.assertTrue(passOnLogPage.clickShowMeThisLaterBtn());
	}

	/**
	 * Test of mark as read on pass on log from ALL tab
	 */
	@Test(priority = 19)
	public void verifyMarkAsReadOnPassOnLogFromALLTab() {
		test = extent.startTest("Verify mark as read on the unread pass on log from ALL tab");
		if (passOnLogPage.clickViewDetailIconFromAllTab())
			Assert.assertTrue(passOnLogPage.clickMarkAsReadBtn());
	}

	/**
	 * Test of view unread status pass on log from UNREAD tab
	 */
	@Test(priority = 21)
	public void verifyViewUnreadStatusPassOnLogFromUNREADTab() {
		test = extent.startTest("Verify view status of unread pass on log from UNREAD tab");
		if (passOnLogPage.goToUnreadTab())
			if (passOnLogPage.clickViewDetailIconFromAllTab())
				if (passOnLogPage.viewStatus())
					if (passOnLogPage.clickCloseBtn())
						Assert.assertTrue(passOnLogPage.goToAllTab());
	}

	/**
	 * Test of mark as read on pass on log from UNREAD tab
	 */
	@Test(priority = 23)
	public void verifyMarkAsReadOnPassOnLogFromUNREADTab() {
		test = extent.startTest("Verify mark as read on unread pass on log from UNREAD tab");
		if (passOnLogPage.goToUnreadTab())
			if (passOnLogPage.clickViewDetailIconFromAllTab())
				if (passOnLogPage.clickMarkAsReadBtn())
					Assert.assertTrue(passOnLogPage.goToAllTab());
	}

	/**
	 * Test of view and mark as read on pass on log from READ tab
	 */
	@Test(priority = 25)
	public void verifyViewAndMarkAsReadOnPassOnLogFromREADTab() {
		test = extent.startTest("Verify view and mark as read on pass on log from READ tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (passOnLogPage.goToReadTab())
			if (passOnLogPage.clickViewDetailIconFromAllTab())
				if (passOnLogPage.clickMarkAsReadBtn())
					Assert.assertTrue(passOnLogPage.goToAllTab());
	}

	/**
	 * Test of delete pass on log from READ tab
	 */
	@Test(priority = 27)
	public void verifyDeletePassOnLogFromREADTab() {
		test = extent.startTest("Verify delete pass on log from READ tab");
		if (passOnLogPage.goToReadTab())
			if (passOnLogPage.clickDeleteIconOnActiveEntry())
				if (passOnLogPage.clickConfirmDeleteBtn())
					Assert.assertTrue(passOnLogPage.goToAllTab());
	}

	/**
	 * Test of user PM create a new pass on log
	 */
	@Test(priority = 29)
	public void verifyUserPMCreateNewPassOnLlog() {
		test = extent.startTest("Verify user PM create a new pass on log");
		if (passOnLogPage.clickCreateEntryBtn())
			if (passOnLogPage.enterSubject_3())
				if (passOnLogPage.enterDetail())
					if (passOnLogPage.enterShowThroughDate())
						if (passOnLogPage.setWhoWillReceiveNotification())
							Assert.assertTrue(passOnLogPage.clickSaveBtn());
	}

	/**
	 * Test of show pass on logs for certain period
	 */
	@Test(priority = 31)
	public void verifyShowPassOnLlogsForCertainPeriod() {
		test = extent.startTest("Verify show pass on logs for certain period ");
		if (passOnLogPage.setStartDate())
			if (passOnLogPage.setEndDate())
				Assert.assertTrue(passOnLogPage.viewFilterResults());
	}

	/**
	 * Test of go to MY ENTRIES tab and view my entries
	 */
	@Test(priority = 33)
	public void verifyGoToMyEntriesTabAndViewMyEntries() {
		test = extent.startTest("Verify go to MY ENTRIES tab and view my entries");
		SeleniumWrapper.refreshWebPage(driver);
		if (passOnLogPage.goToMyEntriesTab())
			if (passOnLogPage.viewMyEntries())
				Assert.assertTrue(passOnLogPage.goToAllEntriesTab());
	}

	/**
	 * Test of view detail of my pass on log
	 */
	@Test(priority = 35)
	public void verifyViewDetailOfMyPassOnLog() {
		test = extent.startTest("Verify view detail of my pass on log");
		if (passOnLogPage.goToMyEntriesTab())
			if (passOnLogPage.clickViewDetailIconFromMyEntriesTab())
				if (passOnLogPage.clickMarkAsReadBtn())
					Assert.assertTrue(passOnLogPage.goToAllEntriesTab());
	}

	/**
	 * Test of go to EXPIRED ENTRIES tab and view expired entries
	 */
	@Test(priority = 37)
	public void verifyGoToExpiredEntriesTabAndViewExpiredEntries() {
		test = extent.startTest("Verify go to EXPIRED ENTRIES tab and view expired entries");
		if (passOnLogPage.goToExpiredEntriesTab())
			if (passOnLogPage.viewExpiredEntries())
				Assert.assertTrue(passOnLogPage.goToAllEntriesTab());
	}

	/**
	 * Test of view detail of expired entry
	 */
	@Test(priority = 39)
	public void verifyViewDetailOfExpiredEntry() {
		test = extent.startTest("Verify view detail of expired entry");
		if (passOnLogPage.goToExpiredEntriesTab())
			if (passOnLogPage.clickViewDetailIconFromExpiredEntriesTab())
				if (passOnLogPage.viewShowThroughDateOnExpiredEntry())
					if (passOnLogPage.clickCloseBtn())
						Assert.assertTrue(passOnLogPage.goToAllEntriesTab());
	}

	/**
	 * Test of delete expired entry
	 */
	@Test(priority = 41)
	public void verifyDeleteExpiredEntry() {
		test = extent.startTest("Verify delete expired entry");
		if (passOnLogPage.goToExpiredEntriesTab())
			if (passOnLogPage.clickDeleteIconOnExpiredEntry())
				if (passOnLogPage.clickConfirmDeleteBtn())
					Assert.assertTrue(passOnLogPage.goToAllEntriesTab());
	}

	/**
	 * Test of delete entry from ALL ENTRIES tab
	 */
	@Test(priority = 43)
	public void verifyDeleteActiveEntryFromALLENTRIESTab() {
		test = extent.startTest("Verify delete entry from ALL ENTRIES tab");
		if (passOnLogPage.clickDeleteIconOnActiveEntry())
			Assert.assertTrue(passOnLogPage.clickConfirmDeleteBtn());
	}

}
