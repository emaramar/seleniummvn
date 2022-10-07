package tests.site_settings;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.site__settings.SiteAdministrationPage;
import pages.site__settings.VisitorParkingPage;

public class VisitorParkingPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(VisitorParkingPageTest.class.getName());
	protected BasePage basePage = null;
	protected SiteAdministrationPage siteAdministrationPage = null;
	protected VisitorParkingPage visitorParkingPage = null;

	/**
	 * Test of navigating to Visitor Parking Page
	 */
	@Test(priority = 1)
	public void gotoVisitorParkingPage() {
		test = extent.startTest("Navigate to Visitor Parking Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		Assert.assertNotNull(siteAdministrationPage);
		visitorParkingPage = siteAdministrationPage.gotoVisitorParkingPage();
		Assert.assertNotNull(visitorParkingPage);
	}

	/**
	 * Test of user Resident log in application
	 */
	@Test(priority = 3)
	public void verifyUserResidentLogInApplication() {
		test = extent.startTest("Verify user Resident go to Unit tab");
		if (visitorParkingPage.logOutAsCurrentUser(driver))
			basePage.logInAsResidentUser(driver);
	}

	/**
	 * Test of user Resident can view his Parking spot information
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void verifyUserResidentCanViewParkingSpotInfo() throws Exception {
		test = extent.startTest("Verify user Resident can view his Parking spot information");
		if (visitorParkingPage.gotoMyAccount())
			if (visitorParkingPage.clickUnit101Tab())
				if (visitorParkingPage.clickViewIcon())
					Assert.assertTrue(visitorParkingPage.clickCloseBtn());
	}

	/**
	 * Test of user Resident edit his Parking spot information
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void verifyUserResidentEditParkingSpotInfo() throws Exception {
		test = extent.startTest("Verify  user Resident edit his Parking spot information");
		if (visitorParkingPage.gotoMyAccount())
			if (visitorParkingPage.clickUnit101Tab())
				if (visitorParkingPage.clickEditIcon())
					if (visitorParkingPage.modifyCarModel())
						if (visitorParkingPage.clickSaveBtn())
							Assert.assertTrue(visitorParkingPage.gotoHomeMenu());
	}

	/**
	 * Test of is "Visitor Name" field mandatory
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void verifyIsVisitorNameFieldMandatory() throws Exception {
		test = extent.startTest("Verify is 'Visitor Name' field mandatory");
		if (visitorParkingPage.clickCreateParkingPassBtn())
			if (visitorParkingPage.clickPlateBox())
				if (visitorParkingPage.enterPlate())
					if (visitorParkingPage.clickSaveCreateParkingPassBtn())
						if (visitorParkingPage.isErrorMsgPresent())
							SeleniumWrapper.waitForDomToBeRendered(driver);
		Assert.assertTrue(visitorParkingPage.clickCancelBtn());
	}

	/**
	 * Test of is "Plate" field mandatory
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void verifyIsPlateFieldMandatory() throws Exception {
		test = extent.startTest("Verify is 'Plate' field mandatory");
		SeleniumWrapper.refreshWebPage(driver);
		if (visitorParkingPage.clickCreateParkingPassBtn())
			if (visitorParkingPage.clickVisitorNameInputBox())
				if (visitorParkingPage.enterVisitorName())
					if (visitorParkingPage.clickSaveCreateParkingPassBtn())
						if (visitorParkingPage.isErrorMsgPresent())
							SeleniumWrapper.waitForDomToBeRendered(driver);
		Assert.assertTrue(visitorParkingPage.clickCancelBtn());
	}

	/**
	 * Test of user Resident create new parking pass
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void verifyUserResidentCreateNewParkingPass() throws Exception {
		test = extent.startTest("Verify user Resident create new parking pass");
		SeleniumWrapper.refreshWebPage(driver);
		if (visitorParkingPage.clickCreateParkingPassBtn())
			if (visitorParkingPage.clickVisitorNameInputBox())
				if (visitorParkingPage.enterVisitorName())
					if (visitorParkingPage.clickPlateBox())
						if (visitorParkingPage.enterPlate())
							if (visitorParkingPage.selectCarMaker_Resident())
								if (visitorParkingPage.selectExpiresSlot_Resident())
									if (visitorParkingPage.clickSaveCreateParkingPassBtn())
										if (visitorParkingPage.closePrintWindow())
											SeleniumWrapper.refreshWebPage(driver);
	}

	/**
	 * Test of user Concierge Clock in application
	 */
	@Test(priority = 15)
	public void verifyUserConciergeClockInApplication() {
		test = extent.startTest("Verify Concierge user Clock in application");
		if (visitorParkingPage.logOutAsCurrentUser(driver))
			basePage.logInAsConciergeUser(driver);
		Assert.assertTrue(visitorParkingPage.conciergeUserClockIn());
	}

	/**
	 * Test of user Concierge create new parking pass
	 * 
	 * @throws Exception
	 */
	@Test(priority = 17)
	public void verifyUserConciergeCreateParkingPass() throws Exception {
		test = extent.startTest("Verify user Concierge create new parking pass");
		if (visitorParkingPage.gotoHomeMenu())
			if (visitorParkingPage.clickCreateParkingPassBtn1())
				if (visitorParkingPage.clickVisitorNameInputBox())
					if (visitorParkingPage.enterVisitorName())
						if (visitorParkingPage.clickPlateBox())
							if (visitorParkingPage.enterPlate())
								if (visitorParkingPage.selectCarMaker_Concierge())
									if (visitorParkingPage.selectExpiresSlot_Concierge())
										if (visitorParkingPage.clickSaveCreateParkingPassBtn())
											if (visitorParkingPage.closePrintWindow())
												SeleniumWrapper.refreshWebPage(driver);
	}

	/**
	 * Test of user Concierge revoke visitor parking pass from Unit Profile
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void verifyUserConciergeRevokeVisitorParkingPass() throws Exception {
		test = extent.startTest("Verify user Concierge revoke visitor parking pass");
		if (visitorParkingPage.searchUnit())
			if (visitorParkingPage.isAtUnitProfile())
				Assert.assertTrue(visitorParkingPage.revokeVisitorParkingPass());
	}

	/**
	 * Test of user Concierge Clock Out
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void verifyUserConciergeClockOut() {
		test = extent.startTest("Verify user Concierge Clock Out");
		if (visitorParkingPage.logOutAsCurrentUser(driver))
			Assert.assertTrue(visitorParkingPage.userConciergeclockOut());
	}

	/**
	 * Test of user PM log a visitor from Unit Profile
	 * 
	 * @throws Exception
	 */
	@Test(priority = 23)
	public void verifyUserPMLogVisitorFromUnitProfile() {
		test = extent.startTest("Verify user PM log a visitor from Unit Profile");
		basePage.navigateTo();
		if (visitorParkingPage.searchUnit())
			if (visitorParkingPage.isAtUnitProfile())
				if (visitorParkingPage.clickLogVisitorBtn())
					if (visitorParkingPage.inputVisitorName())
						if (visitorParkingPage.checkAssignVisitorsParkingBox())
							if (visitorParkingPage.assignedSpot())
								if (visitorParkingPage.inputCarPlate())
									if (visitorParkingPage.pickCarMaker())
										if (visitorParkingPage.pickExpiresTimeSlot())
											Assert.assertTrue(visitorParkingPage.clickSaveBtn());
	}


	/**
	 * Test of is "License Plate" field mandatory
	 * 
	 * @throws Exception
	 */
	@Test(priority = 25)
	public void verifyIsLicensePlateFieldMandatory() {
		test = extent.startTest("Verify is 'License Plate' field mandatory");
		basePage.gotoSiteAdministrationPage();
		visitorParkingPage.gotoVisitorParkingTab();
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			if (visitorParkingPage.clickCreateBtn())
				if (visitorParkingPage.selectWhitelist())
					if (visitorParkingPage.clickSaveAccessBtn())
						if (visitorParkingPage.isErrorMsgOfAccessPresent())
							SeleniumWrapper.waitForDomToBeRendered(driver);
		Assert.assertTrue(visitorParkingPage.clickCancelAccessBtn());
	}

	/**
	 * Test of user PM can create Blacklist
	 * 
	 * @throws Exception
	 */
	@Test(priority = 27)
	public void verifyUserPMCreateBlacklist() {
		test = extent.startTest("Verify user PM can create Blacklist");
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			if (visitorParkingPage.clickCreateBtn())
				if (visitorParkingPage.licensePlateBox())
					if (visitorParkingPage.enterLicensePlate())
						Assert.assertTrue(visitorParkingPage.clickSaveAccessBtn());
	}

	/**
	 * Test of search new created license plate
	 */
	@Test(priority = 29)
	public void searchNewCreatedLicensePlate() {
		test = extent.startTest("Verify search new created license plate");
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			Assert.assertTrue(visitorParkingPage.searchNewCreatedLicensePlate());
	}

	/**
	 * Test of user PM can create Whitelist
	 * 
	 * @throws Exception
	 */
	@Test(priority = 31)
	public void verifyUserPMCreateWhitelist() throws Exception {
		test = extent.startTest("Verify user PM can create Whitelist");
		SeleniumWrapper.refreshWebPage(driver);
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			if (visitorParkingPage.clickCreateBtn())
				if (visitorParkingPage.licensePlateBox())
					if (visitorParkingPage.enterLicensePlate())
						if (visitorParkingPage.selectWhitelist())
							Assert.assertTrue(visitorParkingPage.clickSaveAccessBtn());
	}

	/**
	 * Test of user PM view access list
	 */
	@Test(priority = 33)
	public void viewAccessList() {
		test = extent.startTest("Verify user PM view access list");
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			if (visitorParkingPage.viewAccessList())
				Assert.assertTrue(visitorParkingPage.clickCancelAccessBtn());
	}

	/**
	 * Test of user PM edit access list
	 * 
	 * @throws Exception
	 */
	@Test(priority = 35)
	public void editAccessList() {
		test = extent.startTest("Verify user PM edit access list");
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			if (visitorParkingPage.clickEditBtn())
				if (visitorParkingPage.selectWhitelist())
					Assert.assertTrue(visitorParkingPage.clickSaveAccessBtn());
	}

	/**
	 * Test of user PM delete access list
	 * 
	 * @throws Exception
	 */
	@Test(priority = 37)
	public void deleteAccessList() {
		test = extent.startTest("Verify user PM delete access list");
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		visitorParkingPage = siteAdministrationPage.gotoVisitorParkingPage();
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			Assert.assertTrue(visitorParkingPage.deleteAccessList());
	}

	/**
	 * Test of user PM delete another access list
	 * 
	 * @throws Exception
	 */
	@Test(priority = 39)
	public void deleteAnotherAccessList() {
		test = extent.startTest("Verify user PM delete another access list");
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			Assert.assertTrue(visitorParkingPage.deleteAccessList());
	}

	/**
	 * Test of search access list
	 */
	@Test(priority = 41)
	public void searchAccessList() {
		test = extent.startTest("Verify search access list");
		if (visitorParkingPage.gotoBlacklistAndWhitelistTab())
			Assert.assertTrue(visitorParkingPage.searchAccessList());
	}

}
