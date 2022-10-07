package tests.site_settings;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.site__settings.SiteAdministrationPage;
import pages.site__settings.UsersPage;

public class UsersPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(UsersPageTest.class.getName());
	protected BasePage basePage = null;
	protected SiteAdministrationPage siteAdministrationPage = null;
	protected UsersPage usersPage = null;

	/**
	 * Test of navigating to Users Page
	 */
	@Test(priority = 1)
	public void gotoUsersPage() {
		test = extent.startTest("Navigate to Users Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		basePage.navigateTo();
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		usersPage = siteAdministrationPage.gotoUsersPage();
		Assert.assertNotNull(usersPage);
		Assert.assertNotNull(usersPage.isLoaded());
	}

	/**
	 * Test of auto-generate password
	 */
	@Test(priority = 3)
	public void testAutoGeneratePassword() {
		test = extent.startTest("Verify auto-generate password");
		if (usersPage.clickCreateUserBtn())
			if (usersPage.clickAutoGeneratePasswordBtn())
				Assert.assertTrue(usersPage.isPasswordGenerated());
					if(usersPage.clickCancelBtn());
	}

	/**
	 * Test of select country flag and enter number on Cell field
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void testSelectCountryFlagNEnterNumberOnCellField() {
		test = extent.startTest("Verify select country flag and enter number on Cell field");
		if (usersPage.clickCreateUserBtn())
			Assert.assertTrue(usersPage.selectCountryFlagNEnterCellNum());
				if(usersPage.clickCancelBtn());
	}

	/**
	 * Test of change user's registration type and set move-in date
	 * 
	 */
	@Test(priority = 7)
	public void testChangeUserRegistrationTypeNSetMoveInDate() {
		test = extent.startTest("Verify change user's registration type and set move-in date");
		if (usersPage.clickCreateUserBtn())
			if (usersPage.enterSuiteNum())
				if (usersPage.clickEditIconOnUser())
					if (usersPage.editUserToTenant())
						if (usersPage.setTenantMoveInDate())
							if (usersPage.clickSaveBtn())
								Assert.assertTrue (usersPage.checkIsMoveInDateSet());
									if(usersPage.clickCancelBtn());
	}

	/**
	 * Test of add user on User Group
	 */
	@Test(priority = 9)
	public void testAddUserOnUserGroup() {
		test = extent.startTest("Verify add user on User Group");
		if (usersPage.clickCreateUserBtn())
			Assert.assertTrue(usersPage.enterUserGroup());
				if(usersPage.clickCancelBtn());
	}
	
	
	/**
	 * Test of disable email notifications
	 */
	@Test(priority = 11)
	public void testDisableEmailNotifications() {
		test = extent.startTest("Verify disable email notifications");
		if (usersPage.clickCreateUserBtn())
			Assert.assertTrue(usersPage.removeEmailNotificationSetting());
				if(usersPage.clickCancelBtn());
	}
	
	

	/**
	 * Test of enable notification for SMS and phone call
	 */
	@Test(priority = 13)
	public void testEnableSMSAndPhoneCallNotifications() {
		test = extent.startTest("Verify enable notification for SMS and phone call");
		if (usersPage.clickCreateUserBtn())
			if (usersPage.enterCellNumber())
				if (usersPage.setEmailAndSMSAndPhoneNotification())
					Assert.assertTrue(usersPage.verifyIsPhoneNotificationSet());
						if(usersPage.clickCancelBtn());
	}

	/**
	 * Test of create a new user with type of “Website User”
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void testCreateWebsiteUser() throws Exception {
		test = extent.startTest("Verify create a new user with type of 'Website User'");
		if (usersPage.clickCreateUserBtn())
			if (usersPage.enterFirstName())
				if (usersPage.enterLastName_WebsiteUser())
					if (usersPage.enterEmail())
						if (usersPage.enterPassword())
							if (usersPage.enterHomePhone())
								if (usersPage.enterCellNumber())
									if (usersPage.enterSuiteNum())
										if (usersPage.enterUserGroup())
											if (usersPage.attachedFile())
												if (usersPage.clickconfirmCreateUserBtn())
													Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of "Re-send Welcome Email" with reset password by auto-generate password
	 */
	@Test(priority = 17)
	public void testResendWelcomeEmailWithResetPasswordByAutoGeneration() {
		test = extent.startTest("Verify 'Re-send Welcome Email' with reset password by auto-generate password ");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.clickResendWelcomeEmailBtn())
				if (usersPage.clickResetPasswordCheckbox())
					if (usersPage.isAutoGeneratePasswordOptionIsSelected())
						if (usersPage.clickSaveBtn())
							Assert.assertTrue(usersPage.isSuccessMsgPresent());
								if(usersPage.clickCancelBtn());
	}

	/**
	 * Test of "Re-send Welcome Email" with reset password
	 */
	@Test(priority = 19)
	public void testResendWelcomeEmailWithResetPassword() {
		test = extent.startTest("Verify 'Re-send Welcome Email' with reset password ");
		if (usersPage.clickWebsiteUser())
			if (usersPage.clickResendWelcomeEmailBtn())
				if (usersPage.clickResetPasswordCheckbox())
					if (usersPage.upcheckOptionAutoGeneratePassword())
						if (usersPage.setNewPasswordOnResendWelcomeEmail())
							if (usersPage.clickSaveBtn())
								Assert.assertTrue (usersPage.isSuccessMsgPresent());
									if(usersPage.clickCancelBtn());
	}
	

	/**
	 * Test of "Change Password" function
	 */
	@Test(priority = 21)
	public void testChangePassword() {
		test = extent.startTest("Verify 'Change Password' function ");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.clickChangePasswordBtn())
				if (usersPage.changePassword())
					if (usersPage.clickSaveBtnOnEditAccountPage())
						//if (usersPage.clickConfirmBtnToSaveUser())
							Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}
	
	

	/**
	 * Test of set user move-out date
	 */
	@Test(priority = 23)
	public void testSetUserMoveOutDate() {
		test = extent.startTest("Verify set user move-out date");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.clickEditIconOnUser())
				if (usersPage.setUserMoveOutDate())
					if (usersPage.clickSaveBtn())
						if (usersPage.checkIsMoveOutDateSet())
							if (usersPage.clickSaveBtnOnEditAccountPage())
									Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of remove setting of E-mail notification on existing user
	 */
	@Test(priority = 25)
	public void testRemoveSettingOfEmailNotificationOnExistingUser() {
		test = extent.startTest("Verify remove setting of E-mail notification on existing user");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.removeEmailNotificationOnExistingUser())
				if (usersPage.clickSaveBtnOnEditAccountPage())
					if (usersPage.clickSendBtn())
						Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of enable muting settings on existing user
	 */
	@Test(priority = 27)
	public void testEnableMutingSettingsOnExistingUser() {
		test = extent.startTest("Verify enable muting settings on existing user");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.enableMutingSettingsOnExistingUser())
				if (usersPage.clickSaveBtnOnEditAccountPage())
					if (usersPage.clickSendBtn())
						Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of enable E-mail, SMS and Phone Notification on existing user
	 * 
	 * @throws Exception
	 */
	@Test(priority = 29)
	public void testEnableNotificationsOnEmailAndSMSAndPhone() throws Exception {
		test = extent.startTest("Verify enable E-mail, SMS and Phone Notification on existing user");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.enableNotificationsOfEmailAndSMSAndPhoneOnExistingUser())
				if (usersPage.verifyIsPhoneNotificationSet())
					if (usersPage.clickSaveBtnOnEditAccountPage())
						Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}
	

	/**
	 * Test of search a user
	 */
	@Test(priority = 31)
	public void testSearchAUser() {
		test = extent.startTest("Verify search a user");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		Assert.assertTrue(usersPage.searchUser());
	}

	/**
	 * Test of user’s default email client is open when clicking “Send Email” icon
	 * 
	 * @throws Exception
	 */
	@Test(priority = 33)
	public void testClickSendEmailIcon() throws Exception {
		test = extent.startTest("Verify click 'Send Email' icon will open user's default email client");
		SeleniumWrapper.refreshWebPage(driver);
		//if (usersPage.clickSendEmailIcon())
			//if (usersPage.closeEmailClientWindow());
				Assert.assertTrue(usersPage.clickSendEmailIcon());
			
	}

	/**
	 * Test of edit a user
	 * 
	 * @throws Exception
	 */
	@Test(priority = 35)
	public void testEditUser() throws Exception {
		test = extent.startTest("Verify edit a user");
		if (usersPage.ClickEditIcon())
			if (usersPage.attachedFile())
				if (usersPage.clickSaveBtnOnEditAccountPage())
					Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of deactivate a user
	 */
	@Test(priority = 37)
	public void testDeactivateUser() {
		test = extent.startTest("Verify deactivate a user");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.ClickDeactivateIcon())
			if (usersPage.enableSendDeactivationNoticOption())
				if (usersPage.clickConfirmBtn())
					Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of "Show inactive users" function
	 */
	@Test(priority = 39)
	public void testShowInactiveUsers() {
		test = extent.startTest("Verify 'Show inactive users' function");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.enableOptionOfShowInactiveUsers())
			if (usersPage.areInactiveUsersShowing());
		Assert.assertTrue(usersPage.disableOptionOfShowInactiveUsers());
	}

	/**
	 * Test of activate inactive user
	 */
	@Test(priority = 41)
	public void testActivateInactiveUser() {
		test = extent.startTest("Verify activate inactive user");
		if (usersPage.enableOptionOfShowInactiveUsers())
			if (usersPage.ClickActivateIcon())
				if (usersPage.clickConfirmBtn())
					if (usersPage.isSuccessMsgPresent())
						Assert.assertTrue(usersPage.disableOptionOfShowInactiveUsers());
	}

	/**
	 * Test of change “Website” user to “Profile Only” user
	 */
	@Test(priority = 43)
	public void testChangeWebsiteUserToProfileOnlyUser() {
		test = extent.startTest("Verify change 'Website' user to 'Profile Only' user");
		SeleniumWrapper.refreshWebPage(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.changeWebsiteUserToProfileOnlyUser())
				if (usersPage.clickSaveBtnOnEditAccountPage())
					Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of create new “Profile Only” user with email
	 * 
	 * @throws Exception
	 */
	@Test(priority = 45)
	public void testCreateProfileOnlyUser() throws Exception {
		test = extent.startTest("Verify create new  'Profile Only' user with email");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickCreateUserBtn())
			if (usersPage.selectUserTypeOfProfileOnly())
				if (usersPage.enterFirstName())
					if (usersPage.enterLastName_ProfileOnlyUser())
						if (usersPage.enterEmail())
							if (usersPage.enterSuiteNum())
								if (usersPage.attachedFile())
									if (usersPage.selectNotSendWelcomeEmail())
										if (usersPage.clickconfirmCreateUserBtn())
											Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of merge Account With Existing User and change user to "Website User"
	 */
	@Test(priority = 47)
	public void testMergeAccountWithExistingUserAndChangeUserToWebsiteUser() {
		test = extent.startTest("Verify merge Account With Existing User and change user to 'Website User' ");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.clickSelectAccountBtn())
				if (usersPage.searchAccountForMerge())
					if (usersPage.mergeFirstName())
						if (usersPage.mergeLastName())
							if (usersPage.mergeEmail())
								if (usersPage.clickMergeAccountsBtn())
									if (usersPage.isSuccessMsgPresent())
										if (usersPage.selectUserTypeOfWebsiteUser())
											if (usersPage.clickSaveBtnOnEditAccountPage())
												Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of delete user by clicking trash icon
	 */
	@Test(priority = 49)
	public void testDeleteUserByClickingTrashIcon() {
		test = extent.startTest("Verify delete user by clicking trash icon");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.ClickDeleteIcon())
			if (usersPage.clickConfirmBtn())
				Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of create another new “Profile Only” user with email
	 * 
	 * @throws Exception
	 */
	@Test(priority = 51)
	public void testCreateAnotherProfileOnlyUser() throws Exception {
		test = extent.startTest("Verify create another new  'Profile Only' user with email");
		if (usersPage.clickCreateUserBtn())
			if (usersPage.selectUserTypeOfProfileOnly())
				if (usersPage.enterFirstName())
					if (usersPage.enterLastName_ProfileOnlyUser())
						if (usersPage.enterEmail())
							if (usersPage.enterSuiteNum())
								if (usersPage.attachedFile())
									if (usersPage.selectNotSendWelcomeEmail())
										Assert.assertTrue(usersPage.clickconfirmCreateUserBtn());
	}

	/**
	 * Test of create a new user with type of “Website User”
	 * 
	 * @throws Exception
	 */
	@Test(priority = 53)
	public void testCreateAnotherWebsiteUser() throws Exception {
		test = extent.startTest("Verify create another new user with type of 'Website User'");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickCreateUserBtn())
			if (usersPage.enterFirstName())
				if (usersPage.enterLastName_WebsiteUser())
					if (usersPage.enterEmail())
						if (usersPage.enterFixPassword())
							if (usersPage.enterHomePhone())
								if (usersPage.enterCellNumber())
									if (usersPage.enterSuiteNum())
										if (usersPage.enterUserGroup())
											if (usersPage.attachedFile())
												if (usersPage.clickconfirmCreateUserBtn())
													Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of change password for "Profile Only" user
	 */
	@Test(priority = 55)
	public void testChangePasswordForProfileOnlyUser() {
		test = extent.startTest("Verify change password for \"Profile Only\" user");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickProfileOnlyUser())
			if (usersPage.getProfileOnlyUserEmail())
				if (usersPage.clickChangePasswordBtn())
					if (usersPage.changeToFixedPassword())
						if (usersPage.clickSaveBtnOnEditAccountPage())
							Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of get "Website User" email address
	 */
	@Test(priority = 57)
	public void testGetWebsiteUserEmailAddress() {
		test = extent.startTest("Verify get \"Website User\" email address");
		SeleniumWrapper.refreshWebPage(driver);
		if (usersPage.clickWebsiteUser())
			if (usersPage.getWebsiteUserEmail())
				Assert.assertTrue(usersPage.clickCancelBtn());
	}

	/**
	 * Test of "Website User" can log in application
	 */
	@Test(priority = 59)
	public void testWebsiteUserCanLogInApp() {
		test = extent.startTest("Verify  \"Website User\" can log in application");
		usersPage.logOutAsCurrentUser(driver);
		if (usersPage.loginByWebsiteUser())
			if (usersPage.clickClockInBtn())
				if (usersPage.clickCloseBtnOnSetNotificationPreference())
					if (usersPage.isWebsiteUserLoggedInSuccessful())
						if (usersPage.logOutAsCurrentUser(driver))
							if (usersPage.clickClockOutBtn())
								basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName,
										GuiTestCase.password);
		basePage.navigateTo();
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		usersPage = siteAdministrationPage.gotoUsersPage();
		// Assert.assertTrue(usersPage.clickClockOutBtn());
	}

	/**
	 * Test of "Profile Only" user cant log in application
	 */
	@Test(priority = 61)
	public void testProfileOnlyUserCantLogInApp() {
		test = extent.startTest("Verify 'Profile Only' user cant log in application");
		usersPage.logOutAsCurrentUser(driver);
		if (usersPage.loginByProfileOnlyUser())
			if (usersPage.isProfileOnlyUserLoggedInSuccessful())
				basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		basePage.navigateTo();
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		usersPage = siteAdministrationPage.gotoUsersPage();
		// Assert.assertTrue(usersPage.isProfileOnlyUserLoggedInSuccessful());
	}

	/**
	 * Test of delete "Profile Only" user from "Edit User" page
	 */
	@Test(priority = 63)
	public void testDeleteUserFromEditUserPage() {
		test = extent.startTest("Verify delete 'Profile Only' user from 'Edit User' page");
		if (usersPage.clickProfileOnlyUser())
			if (usersPage.clickDeleteUserBtn())
				if (usersPage.clickConfirmDeleteBtn())
					Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

	/**
	 * Test of delete "Website User" from "Edit User" page
	 */
	@Test(priority = 65)
	public void testDeleteUser() {
		test = extent.startTest("Verify delete 'Website User' from 'Edit User' page");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (usersPage.clickToBeDeletedWebsiteUser())
			if (usersPage.clickDeleteUserBtn())
				if (usersPage.clickConfirmDeleteBtn())
					Assert.assertTrue(usersPage.isSuccessMsgPresent());
	}

}
