package tests.resources;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;

import pages.BasePage;
import pages.resources.AmenityBookingPage;

public class AmenityBookingPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(AmenityBookingPageTest.class.getName());
	protected BasePage basePage = null;
	protected AmenityBookingPage amenityBookingPage = null;

	/**
	 * Test of navigating to Amenity Booking Page
	 */
	@Test(priority = 1)
	public void gotoAmenityBookingPage() {
		test = extent.startTest("Navigate to Amenity Booking Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		amenityBookingPage = basePage.gotoAmenityBookingPage();
		Assert.assertNotNull(amenityBookingPage);
	}

	/**
	 * Test of resident go to Amenity Booking menu
	 */
	@Test(priority = 3)
	public void verifyResidentUserGotoAmenityBookingMenu() {
		test = extent.startTest("Verify Resident go to Amenity Booking menu");
		if (amenityBookingPage.logOutAsCurrentUser(driver))
			basePage.logInAsResidentUser(driver);
		Assert.assertTrue(amenityBookingPage.navigateToAmenityBookingMenu());
	}

	/**
	 * Test of resident user create Elevator booking
	 */
	@Test(priority = 5)
	public void verifyResidentUserCreateElevatorBooking() {
		test = extent.startTest("Verify Resident user create Elevator booking");
		if (amenityBookingPage.clickCheckAvailabilityBtn_Elevator())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.setDate())
					//if (amenityBookingPage.setTime_Elevator())
					Assert.assertTrue (amenityBookingPage.clickSubmitRequestBtn());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of resident user create another Elevator booking
	 */
	@Test(priority = 7)
	public void verifyResidentUserCreateAnotherElevatorBook() {
		test = extent.startTest("Verify Resident user create another Elevator booking");
		if (amenityBookingPage.clickCheckAvailabilityBtn_Elevator())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.setDate())
					//if (amenityBookingPage.setAnotherTime_Elevator())
					Assert.assertTrue (amenityBookingPage.clickSubmitRequestBtn());
							if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of resident user create Guest Suite booking with payment is pending
	 */
	@Test(priority = 9)
	public void verifyResidentUserCreateGuestSuiteBooking() {
		test = extent.startTest("Verify resident user create Guest Suite booking");
		if (amenityBookingPage.clickCheckAvailabilityBtn_GuestSuite())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.setDate_GuestSuite_ByResident())
					if (amenityBookingPage.clickReviewBookingBtn())
						if (amenityBookingPage.clickConfirmBookingBtn())
							Assert.assertTrue(amenityBookingPage.IsBookingSuccess());
	}

	/**
	 * Test of transaction record on Guest Suite booking is show on Resident user
	 * account
	 */
	@Test(priority = 11)
	public void verifyTransactionRecordOfGuestSuiteBookingOnResidentUserAcct() {
		test = extent.startTest("Verify transaction record on Guest Suite booking is show on Resident user account");
		if (amenityBookingPage.goToPaymentsTab())
			Assert.assertTrue(amenityBookingPage.verifypaymentTransaction());
	}

	// ************************************************************************************

	/**
	 * Test default user PM log in application and navigate to Amenity Booking menu
	 */
	@Test(priority = 13)
	public void defaultUserLogInAndNavigateToAmenityBookingMenu() {
		test = extent.startTest("Default user PM log in application and navigate to Amenity Booking menu");
		if (amenityBookingPage.logOutAsCurrentUser(driver))
			basePage.navigateTo();
		basePage.gotoAmenityBookingPage();
	}
	

	/**
	 * Test of PM cant create new Guest Suite booking with conflict time slot
	 */
	@Test(priority = 15)
	public void verifyCantCreateNewGuestSuiteBookingWithSameTime() {
		test = extent.startTest("Verify cant create new Guest Suite booking with conflict time slot");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.setDate_GuestSuite_ByResident())
					if (amenityBookingPage.inputUnit_3())
						if (amenityBookingPage.clickReviewBookingBtn())
							Assert.assertTrue(amenityBookingPage.IsDateConflicit());
								if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of PM create new Guest Suite booking from List view
	 */
	@Test(priority = 17)
	public void verifyPMCreateNewGuestSuiteBooking() {
		test = extent.startTest("Verify create new Guest Suite booking");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.setDate_GuestSuite_ByPM())
					if (amenityBookingPage.inputUnit_3())
						if (amenityBookingPage.clickReviewBookingBtn())
							if (amenityBookingPage.clickConfirmBookingBtn())
								Assert.assertTrue(amenityBookingPage.IsBookingSuccess());
									if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of Receive payment for Guest Suite booking from List view
	 */
	@Test(priority = 19)
	public void verifyReceivePaymentForGuestSuiteBooking() {
		test = extent.startTest("Verify Receive payment for Guest Suite booking from List view");
		SeleniumWrapper.threadSleep(20000);
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.clickReceivePayment());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of PM create new Party Room booking with payment by cheque
	 */
	@Test(priority = 21)
	public void verifyCreateNewPartyRoomBookingWithPaymentByCheck() {
		test = extent.startTest("Verify PM create new Party Room booking with payment by cheque");
		if (amenityBookingPage.clickCheckAvailabilityBtn_PartyRoom())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.inputUnit_1())
					if (amenityBookingPage.inputNumOfGuests())
						if (amenityBookingPage.clickReviewBookingBtn())
							if (amenityBookingPage.editReceivePayment_PartyRoom())
								Assert.assertTrue(amenityBookingPage.clickConfirmBookingBtn());
									if (amenityBookingPage.clickViewCalendarBtn())
										if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of PM create new Party Room booking with payment by credit card
	 */
	@Test(priority = 23)
	public void verifyCreateNewPartyRoomBookingWithPaymentByCreditCard() {
		test = extent.startTest("Verify PM create new Party Room booking with payment by credit card");
		if (amenityBookingPage.clickCheckAvailabilityBtn_PartyRoom())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.inputUnit_2())
					if (amenityBookingPage.inputNumOfGuests())
						if (amenityBookingPage.clickReviewBookingBtn())
							if (amenityBookingPage.selectPayByCreditCard())
								Assert.assertTrue(amenityBookingPage.clickConfirmBookingBtn());
									if (amenityBookingPage.clickViewCalendarBtn())
										if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of user PM create Conference Room booking
	 * 
	 */
	@Test(priority = 25)
	public void verifyCreateConferenceRoomBooking() {
		test = extent.startTest("Verify create Conference Room booking by PM");
		if (amenityBookingPage.clickCheckAvailabilityBtn_ConferenceRoom())
			if (amenityBookingPage.clickCreateAmenityBookingBtn())
				if (amenityBookingPage.setDate())
					if (amenityBookingPage.setTime_ConferenceRoom())
						if (amenityBookingPage.inputUnit_1())
							Assert.assertTrue(amenityBookingPage.clickSubmitRequestBtn());
								if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of edit Conference Room booking from thumbnail view calendar
	 */
	@Test(priority = 27)
	public void verifyEditConferenceRoomBookingFromThumbnailView() {
		test = extent.startTest("Verify edit Conference Room booking from thumbnail view calendar");
		if (amenityBookingPage.clickCheckAvailabilityBtn_ConferenceRoom())
			if (amenityBookingPage.clickLastEvent())
				if (amenityBookingPage.clickLastEventEditIcon())
					if (amenityBookingPage.addNote())
						Assert.assertTrue(amenityBookingPage.clickSaveBtn());
							if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of delete Conference Room booking from List view
	 */
	@Test(priority = 29)
	public void verifyDeleteConferenceRoomBooking() {
		test = extent.startTest("Verify delete Conference Room booking from List view");
		if (amenityBookingPage.clickCheckAvailabilityBtn_ConferenceRoom())
			if (amenityBookingPage.clickListViewTab())
				Assert.assertTrue(amenityBookingPage.cancelNdeleteBooking());
					if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of “show Canceled bookings” on Conference Room booking from Thumbnail
	 * view
	 */
	@Test(priority = 31)
	public void verifyShowCanceledBookings_ConferenceRoomBooking() {
		test = extent.startTest("Verify 'show Canceled bookings' on Conference Room booking");
		if (amenityBookingPage.clickCheckAvailabilityBtn_ConferenceRoom())
			if (amenityBookingPage.compareTotalCountOfBookingRecords())
				Assert.assertTrue(amenityBookingPage.verifyEventStatus());
					if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of Decline & Delete new elevator booking from List view
	 */
	@Test(priority = 33)
	public void verifyDeclineNDeleteNewElevatorBooking() {
		test = extent.startTest("Verify Decline & Delete new elevator booking from List view");
		if (amenityBookingPage.clickCalendarIconOnElevator())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.declineNdeleteNewElevatorBooking());
						if(amenityBookingPage.goBackToAmenityBookingTab());

	}

	/**
	 * Test of edit new elevator booking from List view
	 */
	@Test(priority = 35)
	public void verifyEditNewElevatorBooking() {
		test = extent.startTest("Verify edit new elevator booking from List view");
		if (amenityBookingPage.clickCalendarIconOnElevator())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.editNewBooking());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of Approve new elevator booking from List view
	 */
	@Test(priority = 37)
	public void verifyApproveNewElevatorBooking() {
		test = extent.startTest("Verify Approve new elevator booking from List view");
		if (amenityBookingPage.clickCalendarIconOnElevator())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.approveNewElevatorBooking());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of edit Elevator booking from Thumbnail View
	 */
	@Test(priority = 39)
	public void verifyEditElevatorBookingFromThumbnailView() {
		test = extent.startTest("Verify edit Elevator booking from Thumbnail View");
		if (amenityBookingPage.clickCalendarIconOnElevator())
			if (amenityBookingPage.clickLastEvent())
				if (amenityBookingPage.clickLastEventEditIcon())
					if (amenityBookingPage.addNote())
						Assert.assertTrue(amenityBookingPage.clickSaveBtn());
							if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of Cancel & Delete approved elevator booking from List view
	 */
	@Test(priority = 41)
	public void verifyCancelNDeleteApprovedElevatorBooking() {
		test = extent.startTest("Verify Cancel & Delete  approved elevator booking from List view");
		if (amenityBookingPage.clickCalendarIconOnElevator())
			if (amenityBookingPage.clickListViewTab())
				Assert.assertTrue(amenityBookingPage.cancelNdeleteBooking());
					if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of "Show Canceled Bookings" for Elevator booking
	 */
	@Test(priority = 43)
	public void verifyShowCanceledBookings_ElevatorBooking() {
		test = extent.startTest("Verify 'Show Canceled Bookings' for Elevator booking");
		if (amenityBookingPage.clickCalendarIconOnElevator())
			if (amenityBookingPage.compareTotalCountOfBookingRecords())
				Assert.assertTrue(amenityBookingPage.verifyEventStatus());
					if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of issue Refund on Guest Suite booking from List view
	 * 
	 * @throws Exception
	 */
	@Test(priority = 45)
	public void verifyIssueRefundOnGuestSuiteBooking() throws Exception {
		test = extent.startTest("Verify issue Refund on Guest Suite booking from List view");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.issueRefund_GuestSuite());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of Edit Guest Suite booking from List view
	 */
	@Test(priority = 47)
	public void verifyEditGuestSuiteBooking() {
		test = extent.startTest("Verify edit Guest Suite booking from List view");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.editBooking());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of edit Guest Suite booking from Thumbnail View
	 */
	@Test(priority = 49)
	public void verifyEditGuestSuiteBookingFromThumbnailView() {
		test = extent.startTest("Verify edit Guest Suite booking from Thumbnail View");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickLastEvent())
				if (amenityBookingPage.clickLastEventEditIcon())
					if (amenityBookingPage.addNote())
						Assert.assertTrue(amenityBookingPage.clickSaveBtn());
							if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of verify delete Guest Suite booking from List View
	 */
	@Test(priority = 51)
	public void verifyDeleteGuestSuiteBooking() {
		test = extent.startTest("Verify delete Guest Suite booking from List View");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.deleteBooking_GuestSuite());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of "Show Canceled Bookings" for Guest Suite booking
	 */
	@Test(priority = 53)
	public void verifyShowCanceledBookings_GuestSuiteBooking() {
		test = extent.startTest("Verify 'Show Canceled Bookings' for Guest Suite booking");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.compareTotalCountOfBookingRecords())
				Assert.assertTrue(amenityBookingPage.verifyEventStatus());
					if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of delete Guest Suite booking from Thumbnail View
	 */
	@Test(priority = 55)
	public void verifyDeleteGuestSuiteBookingFromThumbnailView() {
		test = extent.startTest("Verify delete Guest Suite booking from Thumbnail View");
		if (amenityBookingPage.clickCalendarIconOnGuestSuite())
			if (amenityBookingPage.clickLastEvent())
				if (amenityBookingPage.clickLastEventEditIcon())
					if (amenityBookingPage.clickCancelAndDeleteBtn())
						Assert.assertTrue(amenityBookingPage.clickDeclineNDeleteBookingBtn());
							if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of edit Party Room booking from Thumbnail View
	 */
	@Test(priority = 57)
	public void verifyEditPartyRoomBookingFromThumbnailView() {
		test = extent.startTest("Verify edit Party Room booking from Thumbnail View");
		if (amenityBookingPage.clickCalendarIconOnPartyRoom())
			if (amenityBookingPage.clickLastEvent())
				if (amenityBookingPage.clickLastEventEditIcon())
					if (amenityBookingPage.addNote())
						Assert.assertTrue(amenityBookingPage.clickSaveBtn());
							if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of delete and issue refund on Party Room booking from List View
	 */
	@Test(priority = 59)
	public void verifyDeleteNIssueRefundOnPartyRoomBooking() {
		if (amenityBookingPage.clickCalendarIconOnPartyRoom())
			if (amenityBookingPage.clickListViewTab())
				if (amenityBookingPage.clickLastBookingRecord())
					Assert.assertTrue(amenityBookingPage.deleteBooking_PartyRoom());
						if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of "Show Canceled Bookings" for Party Room booking
	 */
	@Test(priority = 61)
	public void verifyShowCanceledBookings_PartyRoomBooking() {
		test = extent.startTest("Verify 'Show Canceled Bookings' for Party Room booking");
		if (amenityBookingPage.clickCalendarIconOnPartyRoom())
			if (amenityBookingPage.compareTotalCountOfBookingRecords())
				Assert.assertTrue(amenityBookingPage.verifyEventStatus());
					if(amenityBookingPage.goBackToAmenityBookingTab());
	}

	/**
	 * Test of delete Party Room booking from Thumbnail View
	 */
	@Test(priority = 63)
	public void verifyDeletePartyRoomBookingFromThumbnailView() {
		test = extent.startTest("Verify delete Party Room booking from Thumbnail View");
		if (amenityBookingPage.clickCalendarIconOnPartyRoom())
			if (amenityBookingPage.clickLastEvent())
				if (amenityBookingPage.clickLastEventEditIcon())
					if (amenityBookingPage.clickCancelAndDeleteBtn())
						Assert.assertTrue(amenityBookingPage.clickDeclineNDeleteBookingBtn());
	}
	
	/**
	 * Test of PM create new booking on North Lifestyle Service
	 */
	//@Test(priority = 65)
	public void verifyPMCreateBookingsOnNorthLifestyleService() {
		test = extent.startTest("Verify create new booking on North Lifestyle Service");
		if (amenityBookingPage.clickNorthLifestyleServiceAmenity())
			if (amenityBookingPage.clickCheckAvailabilityBtn_NorthLifestyleService())
				if (amenityBookingPage.clickCreateAmenityBookingBtn())
					if (amenityBookingPage.inputUnit_1())
						if (amenityBookingPage.addNote())
							if (amenityBookingPage.clickBookAmenityBtn())
								Assert.assertTrue(amenityBookingPage.isSuccessMsgPresent());
		if (amenityBookingPage.goBackToAmenityBookingTab());
	}
	
	/**
	 * Test of verify multiple simultaneous booking title
	 */
	//@Test(priority = 67)
	public void verifyMultipleSimultaneousBookingTitle() {
		test = extent.startTest("Verify verify multiple simultaneous booking title");
		if (amenityBookingPage.clickNorthLifestyleServiceAmenity())
			if (amenityBookingPage.clickCheckAvailabilityBtn_NorthLifestyleService())
				Assert.assertTrue(amenityBookingPage.verifyBookingTitle());
		if (amenityBookingPage.goBackToAmenityBookingTab());
	}

}
