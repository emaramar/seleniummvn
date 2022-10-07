package tests.site_settings;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.site__settings.SiteAdministrationPage;
import pages.site__settings.UnitsPage;

public class UnitsPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(UnitsPageTest.class.getName());
	protected BasePage basePage = null;
	protected SiteAdministrationPage siteAdministrationPage = null;
	protected UnitsPage unitsPage = null;

	/**
	 * Test of navigating to Units Page
	 */
	@Test(priority = 1)
	public void gotoUnitsPage() {
		test = extent.startTest("Navigate to Units Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		basePage.navigateTo();
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		unitsPage = siteAdministrationPage.gotoUnitsPage();
		Assert.assertNotNull(unitsPage);
		Assert.assertNotNull(unitsPage.isLoaded());
	}

	/**
	 * Test of verify user can click column Unit to view Unit detail
	 */
	@Test(priority = 3)
	public void testUserClickColumnUnitToViewUnitDetail() {
		test = extent.startTest("Verify user can click column Unit to view Unit detail");
		if (unitsPage.clickColumnUnitToViewUnitInfo())
			Assert.assertTrue(unitsPage.isEditUnitPageOpened());
				if(unitsPage.clickCancelBtn());
	}

	/**
	 * Test of verify user can click column Owner to view Unit detail
	 */
	@Test(priority = 5)
	public void testUserClickColumnOwnerToViewUnitDetail() {
		test = extent.startTest("Verify user can click column Owner  to view Unit detail");
		if (unitsPage.clickColumnOwnerToViewUnitInfo())
			Assert.assertTrue (unitsPage.isEditUnitPageOpened());
				if(unitsPage.goBackUnitManagerTab());
	}

	/**
	 * Test of verify user can click Tenant column to view Unit detail
	 */
	@Test(priority = 7)
	public void testUserClickColumnTenantToViewUnitDetail() {
		test = extent.startTest("Verify user can click Tenant column to view Unit detail");
		if (unitsPage.clickColumnTenantToViewUnitInfo())
			Assert.assertTrue(unitsPage.isEditUnitPageOpened());
				if(unitsPage.goBackUnitManagerTab());
	}

	/**
	 * Test of verify user can click column Other to view Unit detail
	 */
	@Test(priority = 9)
	public void testUserClickColumnOtherToViewUnitDetail() {
		test = extent.startTest("Verify user can click column Other to view Unit detail");
		if (unitsPage.clickColumnOtherToViewUnitInfo())
			Assert.assertTrue(unitsPage.isEditUnitPageOpened());
				if(unitsPage.goBackUnitManagerTab());
	}



	/**
	 * Test of verify edit Unit by deleting image
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void testEditUnitByDeleteImage() throws Exception {
		test = extent.startTest("Verify edit Unit by deleting image");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.deleteImage())
				if (unitsPage.clickConfirmRemoveAttachmentBtn())
					if (unitsPage.clickSaveEditPageBtn())
						Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}
	
	
	/**
	 * Test of verify edit unit by deleting users
	 */
	@Test(priority = 13)
	public void testEditUnitByDeleteUsers() {
		test = extent.startTest("Verify edit unit by deleting users");
		if (unitsPage.clickEditUnit209())
			if (unitsPage.deleteUser())
				if (unitsPage.clickConfirmDeleteBtn())
					if (unitsPage.deleteUser())
						if (unitsPage.clickConfirmDeleteBtn())
							if (unitsPage.deleteUser())
								if (unitsPage.clickConfirmDeleteBtn())
									if (unitsPage.clickSaveEditPageBtn())
										Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify edit Unit by deleting Parking, Bicycle and Locker spot
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void testEditUnitByDeleteParkingNBicycleNLockerSpot() throws Exception {
		test = extent.startTest("Verify edit Unit by deleting Parking, Bicycle and Locker spot");
		SeleniumWrapper.refreshWebPage(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.deleteParkingAndBicycleAndLocerSpot())
				if (unitsPage.clickSaveEditPageBtn())
					Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}
	

	/**
	 * Test of verify edit unit by adding 3 users
	 */
	@Test(priority = 17)
	public void testEditUnitByAddingUsers() {
		test = extent.startTest("Verify edit unit by adding 3 users");
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		unitsPage = siteAdministrationPage.gotoUnitsPage();
		if (unitsPage.clickEditUnit209())
			if (unitsPage.clickSearchUserBox())
				if (unitsPage.enterUser1())
					if (unitsPage.selectChoice())
						if (unitsPage.enterUser2())
							if (unitsPage.selectChoice())
								if (unitsPage.enterUser3())
									if (unitsPage.selectChoice())
										if (unitsPage.clickSaveEditPageBtn())
											Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify edit user to Tenant and set move-out date
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void testEditUserToTenantNSetMoveOutDate() throws Exception {
		test = extent.startTest("Verify edit user to Tenant and set move-out date");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.editUserToTenant())
				if (unitsPage.setTenantMoveOutDate())
					if (unitsPage.clickCloseBtn())
						if (unitsPage.clickIsMoveInDateSet())
							if (unitsPage.clickSaveEditPageBtn())
								Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify edit user to Resident Roommate
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void testEditUserRegistrationTypeToResidentRoommate() throws Exception {
		test = extent.startTest("Verify edit user to Resident Roommate");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.editUserToResidentRoommate())
				if (unitsPage.clickCloseBtn())
					if (unitsPage.clickSaveEditPageBtn())
						Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify edit Unit by adding Parking spot
	 * 
	 * @throws Exception
	 */
	@Test(priority = 23)
	public void testEditUnitByAddParkingSpot() throws Exception {
		test = extent.startTest("Verify edit Unit by adding Parking spot");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.addParkingSpotOnUnit())
				if (unitsPage.clickSaveEditPageBtn())
					Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify edit Unit by adding Bicycle Spot
	 * 
	 * @throws Exception
	 */
	@Test(priority = 25)
	public void testEditUnitByAddBicycleSpot() throws Exception {
		test = extent.startTest("Verifyedit Unit by adding Bicycle Spot");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.addBicycleSpotOnUnit())
				if (unitsPage.selectChoice())
					if (unitsPage.clickSaveEditPageBtn())
						Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify edit Unit by adding Locker
	 * 
	 * @throws Exception
	 */
	@Test(priority = 27)
	public void testEditUnitByAddLocker() throws Exception {
		test = extent.startTest("Verify edit Unit by adding Locker");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.addLockerOnUnit())
				if (unitsPage.selectChoice())
					if (unitsPage.clickSaveEditPageBtn())
						Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify edit Unit by uploading image
	 * 
	 * @throws Exception
	 */
	@Test(priority = 29)
	public void testEditUnitByUploadImage() throws Exception {
		test = extent.startTest("Verify edit Unit by uploading image");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnit209())
			if (unitsPage.uploadImageOnUnit())
				if (unitsPage.clickSaveEditPageBtn())
					Assert.assertTrue(unitsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of edit icon is responding
	 * 
	 * @throws Exception
	 */
	@Test(priority = 31)
	public void testIsEditIconResponding() {
		test = extent.startTest("Verify is edit iconresponding");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (unitsPage.clickEditUnitIcon())
			Assert.assertTrue(unitsPage.isAtEditUnitPage());
				if(unitsPage.clickCancelBtn());
	}

	/**
	 * Test of verify search unit function
	 */
	@Test(priority = 33)
	public void testSearchUnit() {
		test = extent.startTest("Verify search unit ");
		Assert.assertTrue(unitsPage.searchUnit());
	}

}
