package tests.resources;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;

import pages.BasePage;
import pages.resources.CommunitySchedulePage;

public class CommunitySchedulePageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(CommunitySchedulePageTest.class.getName());
	protected BasePage basePage = null;
	protected CommunitySchedulePage communitySchedulePage = null;
	

	/**
	 * Test of navigating to Community Schedule Page
	 */
	@Test(priority = 1)
	public void gotoCommunitySchedulePage() {
		test = extent.startTest("Navigate to Community Schedule Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		communitySchedulePage = basePage.gotoCommunitySchedulePage();
		Assert.assertNotNull(communitySchedulePage);
	}

	/**
	 * Test of verify is “Name” field mandatory
	 */
	@Test(priority = 3)
	public void isNameFieldMandatory() {
		test = extent.startTest("Verify is 'Name' field mandatory");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.clickSaveEvent())
				if (communitySchedulePage.isErrorMsgPresent())
					Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of verify enter event name
	 */
	@Test(priority = 5)
	public void VerifyEnterEventName() {
		test = extent.startTest("Verify enter event name");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.enterEventName_1())
				Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of verify set event date
	 */
	@Test(priority = 7)
	public void VerirySetEventDate() {
		test = extent.startTest("Verify set event date");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventDate())
				Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of verify set event type of "Multi Day"
	 */
	@Test(priority = 9)
	public void VerirySetEventType() {
		test = extent.startTest("Verify set event type of 'Multi Day'");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventTypeOfMultiDay())
				Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of verify set start and end date of Multi Day event
	 */
	@Test(priority = 11)
	public void VerifySetMultiDayEventDates() {
		test = extent.startTest("Verify set start and end date of  Multi Day event");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventTypeOfMultiDay())
				if (communitySchedulePage.setMultiDayEventDate())
					Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of set time base event
	 */
	@Test(priority = 13)
	public void VerifySetTimeBaseEvent() {
		test = extent.startTest("Verify set time base event");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.uncheckAllDayEvent())
				if (communitySchedulePage.setEventStartTime())
					if (communitySchedulePage.setEventEndTime())
						Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of set event repeat Weekly
	 */
	@Test(priority = 15)
	public void VerifySetEventRepeatWeekly() {
		test = extent.startTest("Verify set event repeat Weekly");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventRepeatWeekly())
				if (communitySchedulePage.selectMultiRepeatDays())
					Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of set event repeat Monthly
	 */
	@Test(priority = 17)
	public void VerifySetEventRepeatMonthly() {
		test = extent.startTest("Verify set event repeat Monthly");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventRepeatMonthly())
				if (communitySchedulePage.setRepeatMonthlyBy2Months())
					Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of set event repeat Yearly
	 */
	@Test(priority = 19)
	public void VerifySetEventRepeatYearly() {
		test = extent.startTest("Verify set event repeat Yearly");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventRepeatYearly())
				if (communitySchedulePage.setRepeatYearlyBy2Years())
					Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of set event "Repeat Until" date
	 */
	@Test(priority = 21)
	public void VerifySetEventRepeatUntilDate() {
		test = extent.startTest("Verify set event 'Repeat Until' date");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventRepeatWeekly())
				if (communitySchedulePage.setEventRepeatUntilDate())
					Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of event tool tip is showing
	 */
	@Test(priority = 23)
	public void VerifyIsEventToolTipShowing() {
		test = extent.startTest("Verify is event tool tip showing");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.setEventRepeatWeekly())
				if (communitySchedulePage.selectMultiRepeatDays())
					if (communitySchedulePage.setEventRepeatUntilDate())
						if (communitySchedulePage.isEventToolTipShowing())
							Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of set which group can view event
	 */
	@Test(priority = 25)
	public void VerifySetWhichGroupCanViewEvent() {
		test = extent.startTest("Verify which group can view event");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.deleteResidentGroup())
				if (communitySchedulePage.setWhoCanViewEvent())
					Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of create single day event
	 */
	@Test(priority = 27)
	public void VerifyCreateSingleDateEvent() {
		test = extent.startTest("Verify create single date event");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.enterEventName_1())
				if (communitySchedulePage.setEventDate())
					if (communitySchedulePage.setEventRepeatWeekly())
						if (communitySchedulePage.selectSingleRepeatDay())
							if (communitySchedulePage.setEventRepeatUntilDate())
								if (communitySchedulePage.enterDescription())
									if (communitySchedulePage.clickSaveEvent())
										Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}

	/**
	 * Test of create multi day event
	 */
	@Test(priority = 29)
	public void VerifyCreateMultiDayEvent() {
		test = extent.startTest("Verify create multi day event");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.enterEventName_2())
				if (communitySchedulePage.setEventTypeOfMultiDay())
					if (communitySchedulePage.setMultiDayEventDate())
						if (communitySchedulePage.enterDescription())
							if (communitySchedulePage.deleteResidentGroup())
								if (communitySchedulePage.setWhoCanViewEvent())
									if (communitySchedulePage.clickSaveEvent())
										Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}

	/**
	 * Test of create time base event
	 */
	@Test(priority = 31)
	public void VerifyCreateTimeBaseEvent() {
		test = extent.startTest("Verify create time base event");
		if (communitySchedulePage.clickCreateEventBtn())
			if (communitySchedulePage.enterEventName_3())
				if (communitySchedulePage.setEventDate())
					if (communitySchedulePage.uncheckAllDayEvent())
						if (communitySchedulePage.setEventStartTime())
							if (communitySchedulePage.setEventEndTime())
								if (communitySchedulePage.setEventRepeatMonthly())
									if (communitySchedulePage.enterDescription())
										if (communitySchedulePage.clickSaveEvent())
											Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}

	/**
	 * Test of edit event from Thumbnail View tab
	 */
	@Test(priority = 33)
	public void VerifyEditEventFromThumbnailViewTab() {
		test = extent.startTest("Verify edit event from Thumbnail View tab");
		if (communitySchedulePage.clickLastEvent())
			if (communitySchedulePage.clickEditEventBtn())
				if (communitySchedulePage.editDescription())
					if (communitySchedulePage.clickSaveEvent())
						Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}

	/**
	 * Test of delete event from Thumbnail View tab
	 */
	@Test(priority = 35)
	public void VerifyDeleteEventFromThumbnailViewTab() {
		test = extent.startTest("Verify delete event from Thumbnail View tab");
		if (communitySchedulePage.clickLastEvent())
			if (communitySchedulePage.deleteEvent())
				Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}

	/**
	 * Test of click any date from calendar will direct user to create event page
	 */
	@Test(priority = 37)
	public void VerifyClickDateWillDirectUserToCreateEventPage() {
		test = extent.startTest("Verify click any date from calendar will direct user to create event page");
		SeleniumWrapper.refreshWebPage(driver);
		if (communitySchedulePage.clickDateFromCalendar())
			if (communitySchedulePage.isAtCreateEVentPage())
				Assert.assertTrue(communitySchedulePage.clickCancelBtn());
	}

	/**
	 * Test of edit event from List View tab
	 */
	@Test(priority = 39)
	public void VerifyEditEventFromListViewTab() {
		test = extent.startTest("Verify edit event from List View tab");
		if (communitySchedulePage.gotoListViewTab())
			if (communitySchedulePage.clickEventFromListViewTab())
				if (communitySchedulePage.clickEditEventBtnFromListViewTab())
					if (communitySchedulePage.editDescription())
						if (communitySchedulePage.clickSaveEvent())
							Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}

	/**
	 * Test of delete event from List View tab
	 */
	@Test(priority = 41)
	public void VerifyDeleteEventFromListViewTab() {
		test = extent.startTest("Verify delete event from List View tab");
		if (communitySchedulePage.gotoListViewTab())
			if (communitySchedulePage.clickEventFromListViewTab())
				if (communitySchedulePage.clickEditEventBtnFromListViewTab())
					if (communitySchedulePage.clickDeleteEventBtnFromEditEventPage())
						if (communitySchedulePage.clickConfirmDeleteBtn())
							Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}
	
	
	/**
	 * Test of delete another event from List View tab
	 */
	@Test(priority = 43)
	public void VerifyDeleteAnotherEventFromListViewTab() {
		test = extent.startTest("Verify delete another event from List View tab");
		if (communitySchedulePage.gotoListViewTab())
			if (communitySchedulePage.clickEventFromListViewTab())
				if (communitySchedulePage.clickEditEventBtnFromListViewTab())
					if (communitySchedulePage.clickDeleteEventBtnFromEditEventPage())
						if (communitySchedulePage.clickConfirmDeleteBtn())
							Assert.assertTrue(communitySchedulePage.isSuccessMsgPresent());
	}
	
	/**
	 * Test of click “Create Amenity Booking” button will direct user to Amenity
	 * Booking module
	 */
	@Test(priority = 45)
	public void VerifyClickCreateAmenityBookingBtnWillDirectUserToAmenityBookingModule() {
		test = extent
				.startTest("Verify click'Create Amenity Booking' button will direct user to Amenity Booking module");
		if (communitySchedulePage.gotoAmenitiesTab())
			if (communitySchedulePage.clickCreateAmenityBookingBtn())
				if (communitySchedulePage.isAtAmenityBookingModule())
					basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		communitySchedulePage = basePage.goBackCommunitySchedulePage();
		Assert.assertNotNull(communitySchedulePage);
	}

	/**
	 * Test of create new amenity booking
	 */
	@Test(priority = 47)
	public void VerifyCreateAmenityBooking() {
		test = extent.startTest("Verify create new amenity booking");
		if (communitySchedulePage.gotoAmenitiesTab())
			if (communitySchedulePage.clickCreateAmenityBookingBtn())
				if (communitySchedulePage.clickCheckAvailabilityBtn())
					if (communitySchedulePage.clickBtnOfCreateAmenityBooking())
						if (communitySchedulePage.setDate())
							if (communitySchedulePage.inputUnit())
										if (communitySchedulePage.clickSubmitRequestBtn())
											basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName,
													GuiTestCase.password);
		communitySchedulePage = basePage.goBackCommunitySchedulePage();
		Assert.assertNotNull(communitySchedulePage);
	}

	/**
	 * Test of edit amenity booking from thumbnail tab
	 */
	@Test(priority = 49)
	public void VerifyEditAmenityBookingFromThumbnailTab() {
		test = extent.startTest("Verify edit amenity booking from thumbnail tab");
		if (communitySchedulePage.gotoAmenitiesTab())
			if (communitySchedulePage.clickBooking())
				if (communitySchedulePage.clickEditBookingBtn())
					if (communitySchedulePage.addNote())
						if (communitySchedulePage.clickSaveBtn())
							if (communitySchedulePage.isSuccessMsgPresent())
								basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName,
										GuiTestCase.password);
		communitySchedulePage = basePage.goBackCommunitySchedulePage();
		Assert.assertNotNull(communitySchedulePage);
	}

	/**
	 * Test of edit amenity booking from List View tab
	 */
	@Test(priority = 51)
	public void VerifyEditAmenityBookingFromListViewTab() {
		test = extent.startTest("Verify edit amenity booking from List View tab");
		if (communitySchedulePage.gotoAmenitiesTab())
			if (communitySchedulePage.gotoListViewTab())
				if (communitySchedulePage.clickBookingFromListViewTab())
					if (communitySchedulePage.editBookingFromListViewTab())
						if (communitySchedulePage.gotoThumbnailTab())
							Assert.assertTrue(communitySchedulePage.gotoEventsTab());

	}

	/**
	 * Test of delete amenity booking
	 */
	@Test(priority = 53)
	public void VerifyDeleteAmenityBooking() {
		test = extent.startTest("Verify edit amenity booking");
		if (communitySchedulePage.gotoAmenitiesTab())
			if (communitySchedulePage.clickCreateAmenityBookingBtn())
				if (communitySchedulePage.clickCheckAvailabilityBtn())
					if (communitySchedulePage.editAmenityBooking())
						if (communitySchedulePage.deleteAmenityBooking())
							if (communitySchedulePage.isSuccessMsgPresent())
								basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName,
										GuiTestCase.password);
		communitySchedulePage = basePage.goBackCommunitySchedulePage();
		Assert.assertNotNull(communitySchedulePage);

	}

	/**
	 * Test of “Show Canceled Bookings” function
	 */
	@Test(priority = 55)
	public void VerifyShowCanceledBookingFunction() {
		test = extent.startTest("Verify'Show Canceled Booking' function");
		if (communitySchedulePage.gotoAmenitiesTab())
			if (communitySchedulePage.enableShowCanceledBookingsOption())
				if (communitySchedulePage.verifyBookingStatus())
					if (communitySchedulePage.closeToolTip())
						if (communitySchedulePage.disableShowCanceledBookingsOption())
							Assert.assertTrue(communitySchedulePage.gotoEventsTab());

	}

}
