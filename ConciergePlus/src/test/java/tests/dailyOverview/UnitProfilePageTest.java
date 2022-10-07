package tests.dailyOverview;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.dailyOverview.UnitProfilePage;

public class UnitProfilePageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(UnitProfilePageTest.class.getName());
	protected BasePage basePage = null;
	protected UnitProfilePage unitProfilePage = null;

	/**
	 * Test of navigating to Unit Profile Page
	 */
	@Test(priority = 1)
	public void gotoUnitProfilePage() {
		test = extent.startTest("Navigate to Unit Profile Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		unitProfilePage = basePage.gotoUnitProfilePage();
		Assert.assertTrue(unitProfilePage.isLoaded());
	}

	/**
	 * Test goto Unit Profile page
	 */
	@Test(priority = 3)
	public void verifyGotoUnitProfilePage() {
		test = extent.startTest("Verify goto Unit Profile page ");
		if (unitProfilePage.searchUnit())
			Assert.assertTrue(unitProfilePage.IsAtUnitProfilePage());
				if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of clicking edit pencil icon will direct user to edit user page
	 */
	@Test(priority = 5)
	public void verifyDirectUserToEditUserPageByClickingEditIcon() {
		test = extent.startTest("Verify clicking edit pencil icon will direct user to edit user page ");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickEditIconOnResidentOwner())
				if (unitProfilePage.IsUserAtEditUserPage())
					Assert.assertTrue(unitProfilePage.clickCancelUpdateAcctBtn());
						if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of edit resident owner’s phone number
	 */
	@Test(priority = 7)
	public void verifyEditResidentOwnerPhoneNumber() {
		test = extent.startTest("Verify edit resident owner’s phone number");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickEditIconOnResidentOwner())
				if (unitProfilePage.editPhoneNumber())
					if (unitProfilePage.clickSaveEditUserBtn())
						Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
							if(unitProfilePage.goBackDailyOverviewMenu());
	}
	

	/**
	 * Test of edit tenant' s email address
	 */
	@Test(priority = 9)
	public void verifyEditTenantEmailAddress() {
		test = extent.startTest("Verify edit tenant's email address");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickEditIconOnTenant())
				if (unitProfilePage.editEmailAddress())
					if (unitProfilePage.clickSaveEditUserBtn())
						if (unitProfilePage.clickConfirmBtnOnSaveUserPage())
							Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
								if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of user PM log a visitor
	 */
	@Test(priority = 11)
	public void verifyUserPMLogVisitorFromUnitProfile() {
		test = extent.startTest("Verify user PM log a visitor");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickLogVisitorBtn())
				if (unitProfilePage.inputVisitorName())
					if (unitProfilePage.checkAssignVisitorsParkingBox())
						if (unitProfilePage.assignedSpot())
							if (unitProfilePage.inputCarPlate())
								if (unitProfilePage.pickCarMaker())
									if (unitProfilePage.pickExpiresTimeSlot())
										if (unitProfilePage.clickSaveNoteBtn())
											Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
												if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of edit unit
	 */
	@Test(priority = 13)
	public void verifyEditUnit() {
		test = extent.startTest("Verify edit unit");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickEditIconOnAdditionalDetails())
				if (unitProfilePage.deleteParkingSpot())
					Assert.assertTrue(unitProfilePage.clickSaveEditPageBtn());
						if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of create new Entry Instructions
	 */
	@Test(priority = 15)
	public void verifyCreateNewEntryInstructions() {
		test = extent.startTest("Verify create new Entry Instructions");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToEntryInstructionsSection())
				if (unitProfilePage.clickCreateBtnOnEntryInstructions())
					if (unitProfilePage.selectUser())
						if (unitProfilePage.inputVisitorNameForEntryInstruction())
							if (unitProfilePage.clickSaveEntryInstructionBtn())
								Assert.assertTrue (unitProfilePage.isSuccessMsgPresent());
									if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of edit Entry Instructions
	 */
	@Test(priority = 17)
	public void verifyEditEntryInstructions() {
		test = extent.startTest("Verify edit Entry Instructions");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToEntryInstructionsSection())
				if (unitProfilePage.clickEditEntryInstructionBtn())
					if (unitProfilePage.modifyExpirationDate())
						if (unitProfilePage.clickSaveEntryInstructionBtn())
							Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
								if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of create new Service Request
	 */
	@Test(priority = 19)
	public void verifyCreateNewServiceRequest() {
		test = extent.startTest("Verify create new Service Request");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickCreateBtnOnServiceRequest())
				if (unitProfilePage.enterSubjectOfServiceRequest())
					if (unitProfilePage.selectUser())
						if (unitProfilePage.enterDescOfServiceRequest())
							if (unitProfilePage.clickSubmitServiceRequestBtn())
								Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
									if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of edit Service Request
	 */
	@Test(priority = 21)
	public void verifyEditServiceRequest() {
		test = extent.startTest("Verify edit Service Request");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickEditServiceRequest())
				if (unitProfilePage.updateStatusToResolved())
					if (unitProfilePage.enterResolvedMsg())
						if (unitProfilePage.clickUpdateTicketBtn())
							Assert.assertTrue (unitProfilePage.isSuccessMsgPresent());
								if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of create new Delivery
	 */
	@Test(priority = 23)
	public void verifyCreateNewDelivery() {
		test = extent.startTest("Verify create new Delivery");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickCreateNewDeliveryBtn())
				if (unitProfilePage.clickCreateDeliveryBtn())
					if (unitProfilePage.inputWayBill())
						if (unitProfilePage.selectNameForDelivery())
							if (unitProfilePage.inputSenderForDelivery())
								if (unitProfilePage.clickCreateDeliveryButton())
									Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
										if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of create new Pickup
	 */
	@Test(priority = 25)
	public void verifyCreateNewPickup() {
		test = extent.startTest("Verify create new Pickup");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickCreateNewDeliveryBtn())
				if (unitProfilePage.clickCreatePickupBtn())
					if (unitProfilePage.inputItemDescription())
						if (unitProfilePage.selectNameForPickup())
							if (unitProfilePage.inputHeldFor())
								if (unitProfilePage.clickCreateAndPrintButton())
									Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
										if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of pickup Delivery
	 */
	@Test(priority = 27)
	public void verifyPickupDelivery() {
		test = extent.startTest("Verify pickup delivery");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickPickupBtn())
				if (unitProfilePage.clickSaveSignatureNCloseButton())
					Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
						if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of revoke Parking Pass
	 */
	@Test(priority = 29)
	public void verifyRevokeParkingPass() {
		test = extent.startTest("Verify revoke Parking Pass");
		if (unitProfilePage.searchUnit())
			Assert.assertTrue(unitProfilePage.revokeVisitorParkingPass());
				if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of create new pet
	 */
	@Test(priority = 31)
	public void verifyCreateNewPet() {
		test = extent.startTest("Verify create new pet");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickCreatePetBtn())
				if (unitProfilePage.inputUnit())
					if (unitProfilePage.inputName())
						if (unitProfilePage.clickSaveNewpetBtn())
							Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
								if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of edit pet
	 * 
	 * @throws Exception
	 */
	@Test(priority = 33)
	public void verifyEditPet() throws Exception {
		test = extent.startTest("Verify edit pet");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.clickEditIconOnPets())
				if (unitProfilePage.uploadPetPhoto())
					if (unitProfilePage.clickSaveNewpetBtn())
						Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
							if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of create new Amenity Booking
	 */
	@Test(priority = 35)
	public void verifyCreateNewAmenityBooking() {
		test = extent.startTest("Verify create new amenity Booking");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToAmenityBookingSection())
				if (unitProfilePage.clickCreateAmenityBookingBtn())
					if (unitProfilePage.clickCheckAvailabilityBtn_GameRoom())
						if (unitProfilePage.clickBtnOfCreateAmenityBooking())
							if (unitProfilePage.setDate())
								if (unitProfilePage.inputUnit())
									Assert.assertTrue(unitProfilePage.clickSubmitRequestBtn());
		if (unitProfilePage.goBackDailyOverviewMenu())
			;
	}

	/**
	 * Test of edit Amenity Booking
	 */
	@Test(priority = 37)
	public void verifyEditAmenityBooking() {
		test = extent.startTest("Verify edit amenity booking");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToAmenityBookingSection())
				if (unitProfilePage.clickEditAmenityBookingBtn())
					if (unitProfilePage.clickCancelAndDeleteBtn())
						Assert.assertTrue(unitProfilePage.clickDeclineAndDeleteBtn());
							if(unitProfilePage.goBackDailyOverviewMenu());
	}
	
	
	/**
	 * Test of create new key
	 */
	@Test(priority = 39)
	public void verifyCreateNewKey() {
		test = extent.startTest("Verify create new key");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToKeysSection())
				if (unitProfilePage.clickCreateKeyBtn())
					if (unitProfilePage.inputName())
						if (unitProfilePage.inputLocation())
							if (unitProfilePage.clickSaveNewKeyBtn())
								Assert.assertTrue (unitProfilePage.isSuccessMsgPresent());
									if(unitProfilePage.goBackDailyOverviewMenu());
	}



	/**
	 * Test of create sign key out note
	 */
	@Test(priority = 41)
	public void verifyCreateSignKeyOutNote() {
		test = extent.startTest("Verify create sign key out note");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToKeysSection())
				if (unitProfilePage.clickSignKeyOutBtn())
					if (unitProfilePage.inputVisitorName())
						if (unitProfilePage.selectSignKeyOutOption())
							if (unitProfilePage.clickSaveSignKeyOutBtn())
								Assert.assertTrue (unitProfilePage.isSuccessMsgPresent());
									if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of report lost key
	 */
	@Test(priority = 43)
	public void verifyReportLostKey() {
		test = extent.startTest("Verify report lost key");
		SeleniumWrapper.refreshWebPage(driver);
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		unitProfilePage = basePage.gotoUnitProfilePage();
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToKeysSection())
				if (unitProfilePage.clickReportLostBtn())
					if (unitProfilePage.clickConfirmBtn())
						Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
							if(unitProfilePage.goBackDailyOverviewMenu());
	}
	
	/**
	 * Test of report found key
	 */
	@Test(priority = 45)
	public void verifyReportFoundKey() {
		test = extent.startTest("Verify report found key");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToKeysSection())
				if (unitProfilePage.clickReportFoundBtn())
					if (unitProfilePage.clickConfirmBtn())
						Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
							if(unitProfilePage.goBackDailyOverviewMenu());
	}
	

	/**
	 * Test of remove key
	 */
	@Test(priority = 47)
	public void verifyRemoveKey() {
		test = extent.startTest("Verify remove key");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToKeysSection())
				if (unitProfilePage.clickRemoveKeyBtn())
					if (unitProfilePage.clickConfirmBtn())
						Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
							if(unitProfilePage.goBackDailyOverviewMenu());
	}

	

	/**
	 * Test of create new Vacancy Date
	 */
	@Test(priority = 49)
	public void verifyCreateNewVacancyDate() {
		test = extent.startTest("Verify create new Vacancy Date");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToVacancyDatesSection())
				if (unitProfilePage.clickVacancyDatesBtn())
					if (unitProfilePage.selectStartDate())
						if (unitProfilePage.selectEndDate())
							if (unitProfilePage.clickAddVacancyDatesBtn())
								Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
									if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of view Vacancy Date
	 */
	@Test(priority = 51)
	public void verifyViewVacancyDate() {
		test = extent.startTest("Verify view Vacancy Dates");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToVacancyDatesSection())
				if (unitProfilePage.clickViewIconOnVacancyDate())
					Assert.assertTrue (unitProfilePage.clickCancelBtn());
							if(unitProfilePage.goBackDailyOverviewMenu());
	}

	/**
	 * Test of edit Vacancy Date
	 */
	@Test(priority = 53)
	public void verifyEditVacancyDate() {
		test = extent.startTest("Verify edit Vacancy Dates");
		if (unitProfilePage.searchUnit())
			if (unitProfilePage.scrollToVacancyDatesSection())
				if (unitProfilePage.clickEditIconOnVacancyDate())
					if (unitProfilePage.modifyEndDate())
						if (unitProfilePage.clickAddVacancyDatesBtn())
							Assert.assertTrue(unitProfilePage.isSuccessMsgPresent());
	}
}
