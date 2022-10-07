package pages.resources;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.SeleniumWrapper;
import config.Constants;
import pages.BasePage;

import com.utilities.Function;

public class AmenityBookingPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(AmenityBookingPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public AmenityBookingPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info(" Amenity Booking Page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoAmenityBookingPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (checkAvailabilityBtn.get(2).isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Navigate to Amenity Booking page
	 */
	@FindBy(xpath = "//*[contains(.,'Resources')]/span[2]")
	public static WebElement ResourcesTab;
	
	@FindBy(xpath = "//a[@href ='/amenity_booking']/span")
	public static WebElement amenityBookingMenu;

	public boolean navigateToAmenityBookingMenu() {
		Function.clickElement(driver, ResourcesTab);
		return Function.clickElement(driver, amenityBookingMenu);
	}

	/**
	 * Check resident user create Elevator booking
	 */
	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static WebElement dateCalendarIcon;

	@FindBy(id = "booking_date")
	public static WebElement dateInputBox;

	@FindBy(xpath = "//*[contains(text(),'next')]")
	public static WebElement next_Calendar;

	@FindBy(xpath = "//*[@title='9:00am - 11:00am']")
	public static WebElement timeSlotBox;

	@FindBy(xpath = "//a[contains(.,'11:00am - 1:00pm')]/span")
	public static WebElement pickTimeSlot;

	@FindBy(xpath = "//a[contains(.,'1:00pm - 3:00pm')]/span")
	public static WebElement pickAnotherTimeSlot;

	@FindBy(xpath = "//button[contains(.,'Book Amenity')]")
	public static WebElement bookAmenityBtn;

	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td")
	public static List<WebElement> datePicker;

	public boolean clickCheckAvailabilityBtn_Elevator() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(1), 50);
		if (checkAvailabilityBtn.get(1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkAvailabilityBtn.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean setDate() {
		Function.clickElement(driver, dateCalendarIcon);
		return Function.setDatefromCalendar(driver, 1);
		// return Function.hoverNclickElement(driver, datePicker.get(34));
	}

	public boolean setTime_Elevator() {
		Function.clickElement(driver, timeSlotBox);
		return Function.hoverNclickElement(driver, pickTimeSlot);
	}

	public boolean setAnotherTime_Elevator() {
		Function.clickElement(driver, timeSlotBox);
		return Function.hoverNclickElement(driver, pickAnotherTimeSlot);
	}

	public boolean clickBookAmenityBtn() {
		Function.clickElement(driver, bookAmenityBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	

	/**
	 * Check resident user create Guest Suite booking
	 */
	@FindBy(xpath = "//*[@class='date-field date-field-interval required']")
	public static List<WebElement> dateInputBoxList;

	@FindBy(xpath = "//*[@class='next']")
	public static List<WebElement> nextBtn;

	public boolean clickCheckAvailabilityBtn_GuestSuite() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(3), 50);
		if (checkAvailabilityBtn.get(1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkAvailabilityBtn.get(3), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean setDate_GuestSuite_ByResident() {
		Function.clickElement(driver, calendarIcon.get(0));
		Function.setDatefromCalendar(driver, 1);
		// Function.hoverNclickElement(driver, datePicker.get(32));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, calendarIcon.get(1));
		return Function.setDatefromCalendar(driver, 2);
		// return Function.hoverNclickElement(driver, datePicker.get(33));
	}

	public boolean setDate_GuestSuite_ByPM() {
		Function.clickElement(driver, calendarIcon.get(0));
		Function.setDatefromCalendar(driver, 3);
		// Function.hoverNclickElement(driver, datePicker.get(34));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, calendarIcon.get(1));
		return Function.setDatefromCalendar(driver, 4);
		// return Function.hoverNclickElement(driver, datePicker.get(35));
	}

	/**
	 * Click Review Booking button
	 */
	@FindBy(xpath = "//button[contains(.,'Review Booking')]")
	public static WebElement reviewBookingBtn;

	@FindBy(xpath = "//li[contains(.,'This booking conflicts with another booking or blackout')]")
	public static List<WebElement> errorMsg;

	public boolean clickReviewBookingBtn() {
		Function.clickElement(driver, reviewBookingBtn);
		return true;
	}

	public boolean IsDateConflicit() {
		if (!confirmBookingBtn.isDisplayed()) {
			SeleniumWrapper.explicitWaitClickable(driver, errorMsg.get(0), 30);
			String message = errorMsg.get(0).getText();
			logger.info("The error message is :" + message);
			Assert.assertFalse(
					message.toLowerCase().contains("This booking conflicts with another booking or blackout."));
		}
		return true;
	}

	/**
	 * Click Confirm Booking button
	 */
	@FindBy(xpath = "//button[contains(.,'Confirm Booking')]")
	public static WebElement confirmBookingBtn;

	public boolean clickConfirmBookingBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBookingBtn, 30);
		if (confirmBookingBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmBookingBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Verify booking is made successfully
	 */
	@FindBy(xpath = " //*[@class='payments_order_success_message']/span[2]")
	public static WebElement confirmMsg;

	public boolean IsBookingSuccess() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmMsg, 30);
		String message = confirmMsg.getText();
		logger.info("The text is:" + message);
		if (message.equalsIgnoreCase("Congratulations! Booking successful"))
			return true;
		else
			return false;
	}
	
	/**
	 * Check is success message present
	 */
	@FindBy(xpath = "//*[@id='success_container']/span[2]")
	public static WebElement confirmSuccessMsg;

	public boolean isSuccessMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmSuccessMsg, 30);
		String message = confirmSuccessMsg.getText();
		logger.info("message is:" + message);
		if (confirmSuccessMsg != null && message.matches("Amenity Booking has been created."))
			return true;
		else
			return false;
	}

	/**
	 * Check transaction record on Guest Suite booking is show on Resident user
	 * account
	 */
	@FindBy(xpath = "//*[@class='display_name']")
	public static WebElement currentLoginUser;

	@FindBy(xpath = "//a[@href = '/myaccount/']")
	public static WebElement myAccountBtn;

	@FindBy(xpath = "//a[@href = '#payments']")
	public static WebElement paymentsTab;

	@FindBy(xpath = "//*[@id='payment_order_details_user_unit']")
	public static WebElement unitOfTransaction;

	@FindBy(xpath = "//*[@id='datatable_payments_transactions']/tbody/tr/td[3]/a")
	public static List<WebElement> columnStatus;

	@FindBy(xpath = "//*[@id='datatable_object_transactions_wrapper']")
	public static WebElement transactionsTable;

	public boolean goToPaymentsTab() {
		Function.clickElement(driver, currentLoginUser);
		Function.clickElement(driver, myAccountBtn);
		return Function.hoverNclickElement(driver, paymentsTab);
	}

	public boolean verifypaymentTransaction() {
		Function.clickElement(driver, columnStatus.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, unitOfTransaction, 30);
		String unit = unitOfTransaction.getText();
		logger.info("The unit is:" + unit);
		SeleniumWrapper.explicitWaitClickable(driver, transactionsTable, 30);
		String record = transactionsTable.getText();
		logger.info("The transaction is:" + record);
		if (unit.contains("Daniels Qu - 101") && (record.toLowerCase().contains("pending"))
				&& (record.contains("$113.00")))
			return true;
		else
			return false;
	}

	/**
	 * Check PM create Conference Room booking
	 */

	@FindBy(xpath = "//*[contains(text(),'Check Availability')]")
	public static List<WebElement> checkAvailabilityBtn; // index 0

	@FindBy(id = "create-amenity-booking")
	public static WebElement createAmenityBookingBtn;

	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static List<WebElement> calendarIcon;

	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td")
	public static List<WebElement> dateList;

	@FindBy(xpath = "//input[@name ='freetime_start']")
	public static WebElement timerRange_From;

	@FindBy(xpath = "//input[@name ='freetime_end']")
	public static WebElement timerRange_To;

	@FindBy(name = "user_search_box")
	public static WebElement unitSearchBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfUnitBox;

	@FindBy(xpath = "//button[contains(.,'Submit Request')]")
	public static WebElement submitRequestBtn;

	public boolean clickCheckAvailabilityBtn_ConferenceRoom() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(0), 50);
		if (checkAvailabilityBtn.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkAvailabilityBtn.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean clickCreateAmenityBookingBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createAmenityBookingBtn, 30);
		if (createAmenityBookingBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createAmenityBookingBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean setTime_ConferenceRoom() {
		Function.clickElement(driver, timerRange_From);
		timerRange_From.clear();
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.setInputFieldText(timerRange_From, "1:00 PM", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, timerRange_To);
		timerRange_To.clear();
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return SeleniumWrapper.setInputFieldText(timerRange_To, "3:00 PM", driver);
	}

	public boolean inputUnit_1() {
		Function.clickElement(driver, unitSearchBox);
		SeleniumWrapper.setInputFieldText(unitSearchBox, "Susy Cai", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.clickElement(driver, choiceOfUnitBox);
	}

	public boolean inputUnit_2() {
		Function.clickElement(driver, unitSearchBox);
		SeleniumWrapper.setInputFieldText(unitSearchBox, "Kelly Q", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.clickElement(driver, choiceOfUnitBox);
	}

	public boolean inputUnit_3() {
		Function.clickElement(driver, unitSearchBox);
		SeleniumWrapper.setInputFieldText(unitSearchBox, "Kelvin Simon", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.clickElement(driver, choiceOfUnitBox);
	}

	public boolean clickSubmitRequestBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, submitRequestBtn, 30);
		if (submitRequestBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, submitRequestBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check edit Conference Room booking from Thumbnail View
	 */
	@FindBy(xpath = "//a[contains(text(), 'Guest Suite')]")
	public static WebElement guestSuiteTab;

	@FindBy(id = "calendar_tab")
	public static WebElement thumbnailViewTab;

	@FindBy(xpath = "//*[@class='fc-content']")
	public static List<WebElement> eventList;

	@FindBy(xpath = "//a[contains(.,'Edit Booking')]/span")
	public static List<WebElement> editIconList;

	public boolean clickGuestSuiteTab() {
		Function.hoverNclickElement(driver, guestSuiteTab);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean clickThumbnailViewTab() {
		return Function.hoverNclickElement(driver, thumbnailViewTab);
	}

	public boolean clickLastEvent() {
		return Function.hoverNclickElement(driver, eventList.get(eventList.size() - 1));
	}

	public boolean clickLastEventEditIcon() {
		Function.hoverNclickElement(driver, editIconList.get(editIconList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check "Show Canceled Bookings" for Conference Room booking
	 */
	@FindBy(xpath = "//*[@class='popover-title']")
	public static WebElement eventTitle;

	@FindBy(xpath = "//*[@class='popover-title']/span")
	public static List<WebElement> eventStatus;

	public boolean clickShowCanceledBookingsCheckbox() {
		SeleniumWrapper.explicitWaitClickable(driver, checkbox.get(0), 30);
		if (checkbox.get(0).isEnabled()) {
			return Function.hoverNclickElement(driver, checkbox.get(0));
		} else
			return false;
	}

	public boolean compareTotalCountOfBookingRecords() {
		int countOfList_Before = eventList.size();
		logger.info("The total count of bookings before is:" + countOfList_Before);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkbox.get(0));
		int countOfList_After = eventList.size();
		logger.info("The total count of bookings after is:" + countOfList_After);
		if (countOfList_After > countOfList_Before)
			return true;
		else
			return false;
	}

	public boolean verifyEventStatus() {
		Function.hoverNclickElement(driver, eventList.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, eventTitle, 30);
		String EventTitle = eventTitle.getText();
		logger.info("The event title is :" + EventTitle);
		// if (EventTitle.toLowerCase().contains("Canceled"))
		return true;
		// return false;
	}

	/**
	 * go back to Amenity Booking tab
	 */
	@FindBy(xpath = "//a[@href ='/amenity_booking']")
	public static List<WebElement> amenityBookingTab;

	public boolean goBackToAmenityBookingTab() {
		SeleniumWrapper.explicitWaitClickable(driver, amenityBookingTab.get(1), 30);
		if (amenityBookingTab.get(1).isEnabled())
			return SeleniumWrapper.clickElement(driver, amenityBookingTab.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check delete the Conference room booking from thumbnail View
	 */
	@FindBy(xpath = "//*[@class='fc-title']")
	public static WebElement conferenceRoomBookingRecord;

	@FindBy(name = "booking_notes")
	public static WebElement notesBox;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public static WebElement saveBtn;

	public boolean clickCalendarIconOnConferenceRoom() {
		Function.hoverNclickElement(driver, calendarIcon.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean addNote() {
		Function.hoverNclickElement(driver, notesBox);
		SeleniumWrapper.setInputFieldText(notesBox, "Confirmed booking", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("The note content is:" + notesBox.getAttribute("value"));
		return true;
	}

	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		if (saveBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check delete the booking from thumbnail View
	 */
	@FindBy(xpath = "//button[contains(.,'Cancel & Delete')]")
	public static WebElement cancelNDeleteBtn;

	public boolean clickCancelAndDeleteBtn() {
		return Function.clickElement(driver, cancelNDeleteBtn);
	}

	public boolean clickDeclineNDeleteBookingBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, declineNDeleteBookingBtn, 30);
		if (declineNDeleteBookingBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, declineNDeleteBookingBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;

	}

	/**
	 * Check edit new elevator booking from List view
	 */
	@FindBy(xpath = "//a[@id=\"listview_tab\"]")
	public static WebElement listViewTab;

	@FindBy(xpath = "//div[@class='bucket-event']")
	public static List<WebElement> elevatorBookingRecords;

	@FindBy(xpath = "//div[@class='bucket-event']")
	public static List<WebElement> bookingRecordList;

	@FindBy(xpath = "//a[contains(., 'Edit Booking')]")
	public static List<WebElement> editBookingIconList;

	@FindBy(xpath = "//*[@id='save-approve-booking']/span[2]")
	public static WebElement approveBtn;

	@FindBy(xpath = "//button[contains(.,'Save Changes')]/span[2]")
	public static WebElement saveChangesBtn;

	@FindBy(xpath = "//*[@aria-label='next']")
	public static WebElement nextMthArrowOnListView;

	@FindBy(xpath = "//button[@aria-label='prev']")
	public static WebElement prevMthArrowOnListView;

	@FindBy(xpath = "//*[@id=\"tab-bookings\"]/p/i")
	public static WebElement NoBookingRecordMsg;

	public boolean clickCalendarIconOnElevator() {
		Function.hoverNclickElement(driver, calendarIcon.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean clickListViewTab() {
		return Function.clickElement(driver, listViewTab);
	}

	/**
	 * Check click last booking from List view
	 */
	public boolean clickLastBookingRecord() {
		Function.clickElement(driver, nextMthArrowOnListView);
		List<WebElement> records;
		records = driver.findElements(By.xpath("//div[@class='bucket-event']"));
		if (records.isEmpty())
			SeleniumWrapper.explicitWaitClickable(driver, prevMthArrowOnListView, 30);
		SeleniumWrapper.clickElement(driver, prevMthArrowOnListView, Constants.CLICK_METHOD_ENUM.CLICK);
		Function.clickElement(driver, bookingRecordList.get(bookingRecordList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check edit existing booking from List view
	 */
	public boolean editBooking() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		Function.switchToNewWindow(driver, parentWindow);
		Function.clickElement(driver, notesBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.setInputFieldText(notesBox, "confirmed booking", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, saveBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

	/**
	 * Check edit new booking from List view
	 */
	public boolean editNewBooking() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		Function.switchToNewWindow(driver, parentWindow);
		Function.clickElement(driver, notesBox);
		SeleniumWrapper.setInputFieldText(notesBox, "confirmed booking", driver);
		Function.hoverNclickElement(driver, saveChangesBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

	/**
	 * Check Approve new elevator booking from List view
	 */
	public boolean approveNewElevatorBooking() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		Function.switchToNewWindow(driver, parentWindow);
		Function.hoverNclickElement(driver, approveBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

	/**
	 * Check Cancel& Delete approved elevator booking from List view
	 */
	@FindBy(xpath = "//button[contains(.,'Decline & Delete Booking')]")
	public static WebElement declineNDeleteBookingBtn;

	public boolean cancelNdeleteBooking() {
		Function.hoverNclickElement(driver, bookingRecordList.get(bookingRecordList.size() - 1));
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		Function.switchToNewWindow(driver, parentWindow);
		Function.hoverNclickElement(driver, cancelNDeleteBtn);
		Function.hoverNclickElement(driver, declineNDeleteBookingBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

	/**
	 * Check Decline & Delete new elevator booking from List view
	 */
	@FindBy(xpath = "//button[contains(.,'Decline & Delete')]/span[2]")
	public static WebElement declineNDeleteBtn;

	@FindBy(xpath = "//*[@name='modal_amenity_decline_note']")
	public static WebElement declineNoteBox;

	public boolean declineNdeleteNewElevatorBooking() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		Function.switchToNewWindow(driver, parentWindow);
		Function.hoverNclickElement(driver, declineNDeleteBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, declineNoteBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.setInputFieldText(declineNoteBox, "The elevator is out of work", driver);
		Function.hoverNclickElement(driver, declineNDeleteBookingBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

	/**
	 * Check Receive payment for Guest Suite booking from List view
	 */
	@FindBy(xpath = "//*[contains(text(),'Receive Payment')]")
	public static WebElement receivePaymentBtn;

	@FindBy(xpath = "//input[@id='form_process_payments_received_payments_notes']")
	public static WebElement checkNumberBox1;

	@FindBy(id = "payments_receive_details")
	public static WebElement checkNumberBox2;

	@FindBy(xpath = "//button[contains(., 'I confirm payment was received')]")
	public static WebElement confirmPaymentBtn;

	public boolean clickCalendarIconOnGuestSuite() {
		SeleniumWrapper.explicitWaitClickable(driver, calendarIcon.get(3), 50);
		if (calendarIcon.get(3).isEnabled()) {
			SeleniumWrapper.clickElement(driver, calendarIcon.get(3), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean clickReceivePayment() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.switchToNewWindow(driver, parentWindow);
		Function.clickElement(driver, receivePaymentBtn);
		if (checkNumberBox2.isEnabled()) {
			Function.clickElement(driver, checkNumberBox2);
			SeleniumWrapper.setInputFieldText(checkNumberBox2, "123", driver);
		} else {
			Function.clickElement(driver, checkNumberBox1);
			SeleniumWrapper.setInputFieldText(checkNumberBox1, "123", driver);
		}
		Function.hoverNclickElement(driver, confirmPaymentBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);

		Function.closeNSwitchWindow(driver, parentWindow);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * check issue refund on Guest Suite booking from List view
	 */
	@FindBy(xpath = "//a[contains(.,'Issue Refund')]")
	public static WebElement issueRefundBtn;

	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> checkbox;

	@FindBy(xpath = "//button[contains(.,'Process Refund')]")
	public static WebElement processRefundBtn;

	public boolean clickBookingRecord() {
		Function.hoverNclickElement(driver, nextMthArrowOnListView);
		List<WebElement> records;
		records = driver.findElements(By.xpath("//div[@class='bucket-event']"));
		if (records.isEmpty())
			Function.hoverNclickElement(driver, prevMthArrowOnListView);
		Function.hoverNclickElement(driver, bookingRecordList.get(bookingRecordList.size() - 2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean issueRefund_GuestSuite() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.switchToNewWindow(driver, parentWindow);
		Function.hoverNclickElement(driver, issueRefundBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkbox.get(3));
		Function.hoverNclickElement(driver, processRefundBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.closeNSwitchWindow(driver, parentWindow);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check delete Guest Suite booking from List View
	 */
	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> issueRefundRadioBtn; // index 1

	public boolean deleteBooking_GuestSuite() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		Function.switchToNewWindow(driver, parentWindow);
		Function.hoverNclickElement(driver, cancelNDeleteBtn);
		Function.hoverNclickElement(driver, declineNoteBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.setInputFieldText(declineNoteBox, "Proceed refund payment", driver);
		Function.hoverNclickElement(driver, declineNDeleteBookingBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

	/**
	 * Check PM create new Party Room booking
	 */
	@FindBy(xpath = "//input[@type='number']")
	public static List<WebElement> numOfGuestsBox; // index 0

	@FindBy(xpath = "//*[@id=\"steps-confirmation\"]/div/div/div[1]/div[4]/div/section[1]/div/div[2]/div/div[1]")
	public static WebElement payByCreditCardBtn;

	@FindBy(name = "form_process_payments_credit_card_name")
	public static WebElement cardHolderNameBox;

	@FindBy(name = "form_process_payments_credit_card_number")
	public static WebElement creditCardNumBtn;

	@FindBy(name = "form_process_payments_credit_card_exp")
	public static WebElement expireyBox;

	@FindBy(name = "form_process_payments_credit_card_cvc")
	public static WebElement cvcBox;

	@FindBy(name = "form_process_payments_credit_card_postal_code")
	public static WebElement zipyBox;

	public boolean clickCheckAvailabilityBtn_PartyRoom() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(6), 50);
		if (checkAvailabilityBtn.get(6).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkAvailabilityBtn.get(6), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean inputNumOfGuests() {
		Function.clickElement(driver, numOfGuestsBox.get(0));
		return SeleniumWrapper.setInputFieldText(numOfGuestsBox.get(0), "15", driver);
	}

	public boolean editReceivePayment_PartyRoom() {
		Function.clickElement(driver, checkbox.get(0));
		if (checkNumberBox2.isDisplayed()) {
			Function.clickElement(driver, checkNumberBox2);
			return SeleniumWrapper.setInputFieldText(checkNumberBox2, "125", driver);
		} else {
			Function.clickElement(driver, checkNumberBox1);
			return SeleniumWrapper.setInputFieldText(checkNumberBox1, "125", driver);
		}

	}

	/**
	 * Check select and input credit card information
	 */
	public boolean selectPayByCreditCard() {
		Function.hoverNclickElement(driver, payByCreditCardBtn);
		Function.clickElement(driver, cardHolderNameBox);
		SeleniumWrapper.setInputFieldText(cardHolderNameBox, "Kelly Q", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, creditCardNumBtn);
		SeleniumWrapper.setInputFieldText(creditCardNumBtn, "4242424242424242", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, expireyBox);
		SeleniumWrapper.setInputFieldText(expireyBox, "12/2022", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, cvcBox);
		SeleniumWrapper.setInputFieldText(cvcBox, "212", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, zipyBox);
		return SeleniumWrapper.setInputFieldText(zipyBox, "M2R2W9", driver);
	}

	/**
	 * Check click Party Room calendar icon
	 */
	public boolean clickCalendarIconOnPartyRoom() {
		Function.hoverNclickElement(driver, calendarIcon.get(5));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Test of delete and issue refund on Party Room booking from List View
	 */
	@FindBy(xpath = "//a[contains(.,'View Calendar')]")
	public static WebElement viewCalendarBtn;

	public boolean clickViewCalendarBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, viewCalendarBtn, 30);
		if (viewCalendarBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, viewCalendarBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean deleteBooking_PartyRoom() {
		String parentWindow = driver.getWindowHandle();
		Function.hoverNclickElement(driver, editBookingIconList.get(editBookingIconList.size() - 1));
		Function.switchToNewWindow(driver, parentWindow);
		Function.clickElement(driver, cancelNDeleteBtn);
		Function.clickElement(driver, declineNoteBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.setInputFieldText(declineNoteBox, "Proceed refund payment", driver);
		Function.clickElement(driver, issueRefundRadioBtn.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, checkbox.get(2));
		Function.clickElement(driver, declineNDeleteBookingBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

	/**
	 * Check go back to Party Room tab
	 */
	@FindBy(xpath = "//*[@id=\"calendar\"]/div[2]/div/table/tbody/tr/td/div/div/div/div[2]/table/tbody/tr[1]/td")
	public static List<WebElement> datesInCalendar;

	public boolean clickDateFromCalendar() {
		SeleniumWrapper.explicitWaitClickable(driver, datesInCalendar.get(datesInCalendar.size() / 2), 30);
		if (datesInCalendar.get(datesInCalendar.size() / 2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, datesInCalendar.get(datesInCalendar.size() / 2),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check create multiple simultaneous amentiy booking for North Lifestyle
	 * Service
	 */
	@FindBy(xpath = " //*[@class='icon-chevron-down']")
	public static List<WebElement> lifestyleServicesAmenity;

	public boolean clickNorthLifestyleServiceAmenity() {
		SeleniumWrapper.explicitWaitClickable(driver, lifestyleServicesAmenity.get(0), 30);
		if (lifestyleServicesAmenity.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, lifestyleServicesAmenity.get(0),
					Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}
	
	
	public boolean clickCheckAvailabilityBtn_NorthLifestyleService() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(4), 50);
		if (checkAvailabilityBtn.get(4).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkAvailabilityBtn.get(4), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}
	
	
	public boolean setDate_Multiple() {
		SeleniumWrapper.enterText(driver, dateCalendarIcon);
		Function.clickElement(driver, dateCalendarIcon);
		return Function.setDatefromCalendar(driver, 1);
		// return Function.hoverNclickElement(driver, datePicker.get(34));
	}
	
	
	/**
	 * Check multiple simultaneous booking title
	 */
	@FindBy(xpath = "//*[@class='fc-content']")
	public static List<WebElement> eventListTitle;
	
	public boolean verifyBookingTitle() {
		SeleniumWrapper.explicitWaitClickable(driver, eventListTitle.get(eventListTitle.size() - 1), 30);
		String title = eventListTitle.get(eventListTitle.size() - 1).getText();
		logger.info("Booking title is:" + title);
		if(title.contains("1/2"))
			return true;
		else 
			return false;
		}
		
	
	/**
	 * Check Multiple Bookings popup
	 */
	@FindBy(xpath = "//*[@id=\"newPerDayDetail-time\"]")
	public static WebElement typeOfAmenity;
	
	@FindBy(xpath = "//*[@id=\"newPerDayDetail-div-left\"]/div/h4[2]")
	public static WebElement countOfBooking;
	
	@FindBy(xpath = "//h2[@class='modal-title']")
	public static List<WebElement> multipleBookingsPopupTitle;
	
	@FindBy(xpath = "//*[contains(text(),'Cancel')]")
	public static List<WebElement> cancelBtn;
	
	public boolean getMultipleBookingsPopupTitle() {
		SeleniumWrapper.explicitWaitClickable(driver,multipleBookingsPopupTitle.get(0), 30);
		String title = multipleBookingsPopupTitle.get(0).getText();
		logger.info("Popup title is:" + title);
		
		SeleniumWrapper.explicitWaitClickable(driver, typeOfAmenity, 30);
		String type = typeOfAmenity.getText();
		logger.info("Type of amentiy is:" + type);
		
		SeleniumWrapper.explicitWaitClickable(driver, countOfBooking, 30);
		String count = countOfBooking.getText();
		logger.info("Count of booking is:" + count);
		
		
		if(title.contains("Multiple Bookings") && type.matches("All Day") && count.contains("1 out of 2 spots booked"))
			return true;
		else 
			return false;
		}

	
	
	
	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn.get(1), 30);
		if (cancelBtn.get(1).isDisplayed() && cancelBtn.get(1).isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, cancelBtn.get(1));
			SeleniumWrapper.clickElement(driver, cancelBtn.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		}
		return false;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


