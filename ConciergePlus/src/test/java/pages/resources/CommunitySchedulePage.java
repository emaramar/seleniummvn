package pages.resources;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;
import pages.BasePage;

public class CommunitySchedulePage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(CommunitySchedulePage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */

	public CommunitySchedulePage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Community Schedule page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoCommunitySchedulePage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createEventBtn.isEnabled()) {
			return true;
		} else
			return false;
	}

	/**
	 * Check is "Name" field mandatory
	 */
	@FindBy(xpath = "//*[@id='create-event-button']/span[2]")
	public static WebElement createEventBtn;

	@FindBy(xpath = "//*[@id='daily-overview-create-event-button']/span[2]")
	public static WebElement createEventBtnOnCreateEventPage;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsg; // index 0

	@FindBy(xpath = "//*[@id='daily-overview-create-event-cancel']/span[2]")
	public static WebElement cancelBtn;

	public boolean clickCreateEventBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createEventBtn, 30);
		if (createEventBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createEventBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean clickSaveEvent() {
		SeleniumWrapper.explicitWaitClickable(driver, createEventBtnOnCreateEventPage, 30);
		if (createEventBtnOnCreateEventPage.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createEventBtnOnCreateEventPage, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg.get(0), 30);
		String errorMessage = errorMsg.get(0).getText();
		logger.info("The error message is :" + errorMessage);
		if (errorMsg != null)
			return true;
		else
			return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 30);
		if (cancelBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, cancelBtn);
			SeleniumWrapper.clickElement(driver, cancelBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check enter event name
	 */
	@FindBy(name = "name")
	public static WebElement eventNameInputBox;

	String eventName_1 = "Pave parking lot floor";
	String eventName_2 = "Decorate lobby";
	String eventName_3 = "Paint entry door";

	public boolean enterEventName_1() {
		SeleniumWrapper.explicitWaitClickable(driver, eventNameInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, eventNameInputBox, Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(eventNameInputBox, eventName_1, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String event = eventNameInputBox.getAttribute("value");
		logger.info("Event name is: " + event);
		if (!event.isEmpty())
			return true;
		else
			return false;
	}

	public boolean enterEventName_2() {
		SeleniumWrapper.explicitWaitClickable(driver, eventNameInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, eventNameInputBox, Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(eventNameInputBox, eventName_2, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String event = eventNameInputBox.getAttribute("value");
		logger.info("Event name is: " + event);
		if (!event.isEmpty())
			return true;
		else
			return false;
	}

	public boolean enterEventName_3() {
		SeleniumWrapper.explicitWaitClickable(driver, eventNameInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, eventNameInputBox, Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(eventNameInputBox, eventName_3, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String event = eventNameInputBox.getAttribute("value");
		logger.info("Event name is: " + event);
		if (!event.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check set event date
	 */
	@FindBy(xpath = "//*[@id='datepicker_date']/span/span")
	public static WebElement calendarIcon;

	@FindBy(id = "date")
	public static WebElement DateBox;

	public boolean setEventDate() {
		Function.clickElement(driver, calendarIcon);
		Function.setDatefromCalendar(driver, 1);
		logger.info("Event date is:" + DateBox.getAttribute("value"));
		return true;
	}

	/**
	 * Check set event type of "Multi Day"
	 */
	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> radioBtnList;

	@FindBy(xpath = "//*[@id='datepicker_date_2']/span/span")
	public static WebElement calendarIcon_End;

	public boolean setEventTypeOfMultiDay() {
		Function.hoverNclickElement(driver, radioBtnList.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if (calendarIcon_End != null)
			return true;
		else
			return false;
	}

	/**
	 * Check set start and end date of Multi Day event
	 */
	@FindBy(id = "date_start")
	public static WebElement startDateBox;

	@FindBy(id = "date_end")
	public static WebElement endDateBox;

	@FindBy(xpath = "//*[@id='datepicker_date_1']/span/span")
	public static WebElement calendarIcon_Start;

	public boolean setMultiDayEventDate() {
		Function.hoverNclickElement(driver, calendarIcon_Start);
		Function.setDatefromCalendar(driver, 1);
		String startDate = startDateBox.getAttribute("value");
		logger.info("Event Start date is:" + startDate);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, calendarIcon_End);
		Function.setDatefromCalendar(driver, 2);
		String endDate = endDateBox.getAttribute("value");
		logger.info("Event end date is:" + endDate);
		if (!startDate.matches(endDate))
			return true;
		else
			return false;
	}

	/**
	 * Check select time base event
	 */
	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> checkboxList;

	@FindBy(xpath = "//*[@id=\"all_day_container\"]/div/label/span[2]")
	public static WebElement checkboxOfAllDayEvent;

	@FindBy(name = "time_start")
	public static WebElement startTime;

	@FindBy(name = "time_end")
	public static WebElement endTime;

	public boolean uncheckAllDayEvent() {
		SeleniumWrapper.explicitWaitClickable(driver, checkboxOfAllDayEvent, 30);
		if (checkboxOfAllDayEvent.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, checkboxOfAllDayEvent);
			SeleniumWrapper.clickElement(driver, checkboxOfAllDayEvent, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	public boolean setEventStartTime() {
		SeleniumWrapper.explicitWaitClickable(driver, startTime, 30);
		if (SeleniumWrapper.clickElement(driver, startTime, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(startTime, "11:00 AM", driver);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			logger.info("Start time is:" + startTime.getAttribute("value"));
			return true;
		} else
			return false;
	}

	public boolean setEventEndTime() {
		SeleniumWrapper.explicitWaitClickable(driver, endTime, 30);
		if (SeleniumWrapper.clickElement(driver, endTime, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(endTime, "2:00 PM", driver);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			logger.info("End time is:" + endTime.getAttribute("value"));
			return true;
		} else
			return false;
	}

	/**
	 * Check set event repeat by Weekly
	 */
	@FindBy(xpath = "//*[@id='form-secondary']/fieldset/div[8]/div[2]/div/button/div/div/div")
	public static WebElement repeatEvery1Week;

	@FindBy(xpath = "//*[@id='form-secondary']/fieldset/div[8]/div[1]/label")
	public static List<WebElement> week;

	public boolean setEventRepeatWeekly() {
		Function.hoverNclickElement(driver, radioBtnList.get(3));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.explicitWaitClickable(driver, repeatEvery1Week, 30);
		String text = repeatEvery1Week.getText();
		logger.info("Repeat type is:" + text);
		if (text.contains("1 Week"))
			return true;
		else
			return false;
	}

	public boolean selectMultiRepeatDays() {
		Function.clickElement(driver, checkboxList.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, checkboxList.get(4));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean selectSingleRepeatDay() {
		Function.clickElement(driver, checkboxList.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check set event repeat by Monthly
	 */
	@FindBy(xpath = "//*[@id='form-secondary']/fieldset/div[10]/div[2]/div/button")
	public static WebElement repeatEvery1Month;

	@FindBy(xpath = "//*[@id='bs-select-3-1']/span")
	public static WebElement repeatEvery2Months;

	public boolean setEventRepeatMonthly() {
		SeleniumWrapper.explicitWaitClickable(driver, radioBtnList.get(4), 30);
		if (radioBtnList.get(4).isEnabled()) {
			SeleniumWrapper.clickElement(driver, radioBtnList.get(4), Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	public boolean setRepeatMonthlyBy2Months() {
		Function.clickElement(driver, repeatEvery1Month);
		Function.hoverNclickElement(driver, repeatEvery2Months);
		String text = repeatEvery1Month.getText();
		logger.info("Repeat every" + text);
		if (text.contains("2 Months"))
			return true;
		else
			return false;
	}

	/**
	 * Check set event repeat by Yearly
	 */
	@FindBy(xpath = "//*[@id='form-secondary']/fieldset/div[11]/div[2]/div/button")
	public static WebElement repeatEvery1Year;

	@FindBy(xpath = "//*[@id='bs-select-4-1']/span")
	public static WebElement repeatEvery2Years;

	public boolean setEventRepeatYearly() {
		SeleniumWrapper.explicitWaitClickable(driver, radioBtnList.get(5), 30);
		if (radioBtnList.get(5).isEnabled()) {
			SeleniumWrapper.clickElement(driver, radioBtnList.get(5), Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	public boolean setRepeatYearlyBy2Years() {
		Function.clickElement(driver, repeatEvery1Year);
		Function.hoverNclickElement(driver, repeatEvery2Years);
		String text = repeatEvery1Year.getText();
		logger.info("Repeat every" + text);
		if (text.contains("2 Years"))
			return true;
		else
			return false;
	}

	/**
	 * Check set event "Repeat Until" date
	 */
	@FindBy(xpath = "//*[@id='datepicker_repeat_until_date']/span/span")
	public static WebElement calendarIconOnRepeatUntilDate;

	@FindBy(xpath = "/html/body/div[12]/div[1]/table/thead/tr[1]/th[3]")
	public static WebElement nextArrow;

	@FindBy(name = "repeat_until_date")
	public static WebElement repeatUntilDateBox;

	public boolean setEventRepeatUntilDate() {
		Function.clickElement(driver, calendarIconOnRepeatUntilDate);
		String currentRepeatUntilDate = repeatUntilDateBox.getAttribute("value");
		logger.info("Current 'Repeat Until' date:" + currentRepeatUntilDate);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, nextArrow);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.setDatefromCalendar(driver, 0);
		String newRepeatUnitlDate = repeatUntilDateBox.getAttribute("value");
		logger.info("New 'Repeat Until' date:" + newRepeatUnitlDate);
		if (newRepeatUnitlDate != currentRepeatUntilDate)
			return true;
		else
			return false;
	}

	/**
	 * Check is event tool tip showing
	 */
	@FindBy(xpath = "//*[@id='form-secondary']/fieldset/div[13]/div[2]/div/span[2]")
	public static WebElement toolTipText;

	public boolean isEventToolTipShowing() {
		SeleniumWrapper.explicitWaitClickable(driver, toolTipText, 30);
		String toolTip = toolTipText.getText();
		logger.info("Tool tip is:" + toolTip);
		if (!toolTip.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check set which group can view event
	 */
	@FindBy(xpath = "//*[@id=\"groups_selector_form_element_groups\"]/div[1]/div/div/div[2]/div[2]/div[2]/a/span")
	public static WebElement deleteIconOnResidents;

	@FindBy(xpath = "//*[@id=\"groups_selector_form_element_groups\"]/div[1]/div/div/div[3]/div[1]/div/button/div/div/div/span")
	public static WebElement addGroupBtn;

	@FindBy(xpath = "//*[@id=\"bs-select-15-3\"]/span")
	public static WebElement groupOfEveryone;

	@FindBy(xpath = "//*[@id=\"groups_selector_form_element_groups\"]/div[1]/div/div/div[3]/div[1]/div/button/div/div/div")
	public static WebElement visibleByGroup_1;

	@FindBy(xpath = "//*[@id=\"groups_selector_form_element_groups\"]/div[1]/div/div/div[2]/div[1]/div/button/div/div/div")
	public static WebElement visibleByGroup_2;

	public boolean deleteResidentGroup() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteIconOnResidents, 30);
		if (deleteIconOnResidents.isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteIconOnResidents, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean setWhoCanViewEvent() {
		Function.clickElement(driver, addGroupBtn);
		Function.hoverNclickElement(driver, groupOfEveryone);
		SeleniumWrapper.explicitWaitClickable(driver, visibleByGroup_1, 30);
		String group1 = visibleByGroup_1.getText();
		logger.info("Group 1 is:" + group1);

		SeleniumWrapper.explicitWaitClickable(driver, visibleByGroup_2, 30);
		String group2 = visibleByGroup_2.getText();
		logger.info("Group 2 is:" + group2);
		if (group1.equalsIgnoreCase("Everyone") && group2.equalsIgnoreCase("Concierge"))
			return true;
		else
			return false;
	}

	/**
	 * Check create single date event
	 */
	@FindBy(xpath = "//*[@id=\"form-secondary\"]/fieldset/div[14]/div[2]")
	public static WebElement descBox;

	@FindBy(id = "description")
	public static List<WebElement> textAreaBox;

	public boolean enterDescription() {
		Function.clickElement(driver, descBox);
		Function.clickElement(driver, textAreaBox.get(0));
		SeleniumWrapper.setInputFieldText(textAreaBox.get(0), "Community Event", driver);
		String desc = textAreaBox.get(0).getAttribute("value");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		logger.info("Event description:" + desc);
		if (desc.contains("Community Event"))
			return true;
		else
			return false;
	}

	/**
	 * Check is success message present
	 */
	@FindBy(xpath = "//*[@id='success_container']/span[2]")
	public static WebElement confirmMsg;

	public boolean isSuccessMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmMsg, 30);
		String message = confirmMsg.getText();
		logger.info("message is:" + message);
		if (confirmMsg.isDisplayed() && !message.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check edit event from Thumbnail View
	 */
	@FindBy(xpath = "//*[@class='fc-content']")
	public static List<WebElement> eventListFromThumbnailViewTab;

	@FindBy(xpath = "//a[contains(.,'Edit Event')]/span[2]")
	public static List<WebElement> EditEventBtnFromThumbnailViewTab;

	@FindBy(id = "edit")
	public static WebElement editBtnOnEditRecurringEventPopup;
	
	@FindBy(xpath = "//*[@class='fc-icon fc-icon-right-single-arrow']")
	public static WebElement nextMonthArrow;
	
	@FindBy(xpath = "//*[@class='popover-title']")
	public static WebElement eventTitle;
	

	public boolean clickLastEvent() {
		//Function.clickElement(driver, nextMonthArrow);
		return Function.clickElement(driver, eventListFromThumbnailViewTab.get(eventListFromThumbnailViewTab.size()-1));
		
	}
	

	public boolean clickEditEventBtn() {
		String title = eventTitle.getText();
		if(title.contains("Paint entry door")) {
			Function.clickElement(driver, EditEventBtnFromThumbnailViewTab.get(EditEventBtnFromThumbnailViewTab.size() - 1));
			Function.hoverNclickElement(driver, radioBtnList.get(2));
			Function.clickElement(driver, editBtnOnEditRecurringEventPopup);
		}
		else {
		    Function.clickElement(driver, EditEventBtnFromThumbnailViewTab.get(EditEventBtnFromThumbnailViewTab.size() - 1));
		}
		return true;
	}
			

	public boolean editDescription() {
		SeleniumWrapper.explicitWaitClickable(driver, textAreaBox.get(0), 30);
		logger.info("Original description:" + textAreaBox.get(0).getText());
		Function.clickElement(driver, descBox);
		Function.clickElement(driver, textAreaBox.get(0));
		SeleniumWrapper.setInputFieldText(textAreaBox.get(0), "Edit Community Event", driver);
		String desc = textAreaBox.get(0).getAttribute("value");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		logger.info("Final description:" + desc);
		if (desc.contains("Edit Community Event"))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Check delete event from Thumbnail View
	 */
	@FindBy(xpath = "//a[contains(.,'Delete Event')]/span[2]")
	public static List<WebElement> DeleteEventBtnFromThumbnailViewTab;
	
	@FindBy(xpath = "//*[@id='edit']")
	public static WebElement deleteBtnOnRecurringEvent;
	
	@FindBy(xpath = "//button[@id=\"delete-action-confirm\"]")
	public static WebElement confirmDeleteEventBtn;
	
	

	public boolean deleteEvent() {
		String title = eventTitle.getText();
		if(title.contains("Paint entry door")) {
			Function.clickElement(driver, DeleteEventBtnFromThumbnailViewTab.get(DeleteEventBtnFromThumbnailViewTab.size() - 1));
			Function.hoverNclickElement(driver, radioBtnList.get(2));
			 Function.clickElement(driver, deleteBtnOnRecurringEvent); 
		}
		else  {
			Function.clickElement(driver, DeleteEventBtnFromThumbnailViewTab.get(DeleteEventBtnFromThumbnailViewTab.size() - 1));
			Function.hoverNclickElement(driver, confirmDeleteEventBtn);
		}
		return true;
	}
	

	/**
	 * Check click any date from calendar will direct user to create event page
	 */
	@FindBy(xpath = "//*[@class='fc-day-grid fc-unselectable']/div/div/table/tbody/tr/td[1]")
	public static List<WebElement> datesInCalendar;

	public boolean clickDateFromCalendar() {
		return Function.clickElement(driver, datesInCalendar.get(1));
	}

	public boolean isAtCreateEVentPage() {
		SeleniumWrapper.explicitWaitClickable(driver, createEventBtnOnCreateEventPage, 30);
		if (createEventBtnOnCreateEventPage != null)
			return true;
		else
			return false;
	}

	/**
	 * Check gotoList View tab
	 */
	@FindBy(xpath = "//a[@id='listview_tab']/span")
	public static WebElement listviewTab;

	public boolean gotoListViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, listviewTab, 30);
		if (listviewTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, listviewTab, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check goto Thumbnail tab
	 */
	@FindBy(id = "calendar_tab")
	public static WebElement thumbnailTab;

	public boolean gotoThumbnailTab() {
		SeleniumWrapper.explicitWaitClickable(driver, thumbnailTab, 30);
		if (thumbnailTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, thumbnailTab, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check edit event from List View tab
	 */
	@FindBy(xpath = "//*[@class='bucket-event']")
	public static List<WebElement> recordFromListViewTab;

	@FindBy(xpath = "//*[contains(text(),'Edit Event')]")
	public static List<WebElement> editEventBtnFromListViewTab;
	
	@FindBy(xpath = "//*[@aria-label='next']")
	public static WebElement nextMthArrowOnListView;

	@FindBy(xpath = "//button[@aria-label='prev']")
	public static WebElement prevMthArrowOnListView;

	public boolean clickEventFromListViewTab() {
		Function.hoverNclickElement(driver, nextMthArrowOnListView);
		List<WebElement> records;
		records = driver.findElements(By.xpath("//div[@class='bucket-event']"));
		if (records.isEmpty())
			Function.hoverNclickElement(driver, prevMthArrowOnListView);
		return Function.clickElement(driver,  recordFromListViewTab.get(recordFromListViewTab.size() - 1));
	}

	public boolean clickEditEventBtnFromListViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver,
				editEventBtnFromListViewTab.get(editEventBtnFromListViewTab.size() - 1), 50);
		if (editEventBtnFromListViewTab.get(editEventBtnFromListViewTab.size() - 1).isEnabled()) {
			SeleniumWrapper.clickElement(driver,
					editEventBtnFromListViewTab.get(editEventBtnFromListViewTab.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check delete event from List View tab
	 */
	@FindBy(xpath = "//*[@id='form-delete-event-button']/span[2]")
	public static WebElement deleteEventBtn;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean clickDeleteEventBtnFromEditEventPage() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteEventBtn, 30);
		if (deleteEventBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteEventBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check goto Amenities tab
	 */
	@FindBy(xpath = "//*[@id='calendar_events_tabs']/a[2]")
	public static WebElement amenitiesTab;

	@FindBy(id = "create-amenity-button")
	public static WebElement createAmenityBookingBtn;

	public boolean gotoAmenitiesTab() {
		Function.clickElement(driver, amenitiesTab);
		SeleniumWrapper.explicitWaitClickable(driver, createAmenityBookingBtn, 30);
		if (createAmenityBookingBtn != null)
			return true;
		else
			return false;
	}
		

	/**
	 * Check goto Events tab
	 */
	@FindBy(xpath = "//*[@id='calendar_events_tabs']/a[1]")
	public static WebElement eventsTab;

	public boolean gotoEventsTab() {
		SeleniumWrapper.explicitWaitClickable(driver, eventsTab, 30);
		if (eventsTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, eventsTab, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check click “Create Amenity Booking” button will direct user to Amenity
	 * Booking module
	 */
	@FindBy(xpath = "//*[@id=\"check-availability\"]/span[2]")
	public static List<WebElement> checkAvailabilityBtn;

	public boolean clickCreateAmenityBookingBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createAmenityBookingBtn, 30);
		if (createAmenityBookingBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createAmenityBookingBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean isAtAmenityBookingModule() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(2), 30);
		logger.info("Button name is:" + checkAvailabilityBtn.get(2).getText());
		if (checkAvailabilityBtn.get(2) != null)
			return true;
		else
			return false;
	}

	/**
	 * Check click any date from calendar will direct user to create event page
	 */
	@FindBy(xpath = "//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div/div/div[5]/div[1]/table/tbody/tr/td")
	public static List<WebElement> datesInCalendarOnAmenitiesTab;

	public boolean clickDateFromCalendarOnAmenitiesTab() {
		SeleniumWrapper.explicitWaitClickable(driver, datesInCalendarOnAmenitiesTab.get(3), 50);
		if (datesInCalendarOnAmenitiesTab.get(3).isEnabled()) {
			SeleniumWrapper.clickElement(driver, datesInCalendarOnAmenitiesTab.get(3),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check create amenity booking
	 */
	@FindBy(id = "create-amenity-booking")
	public static List<WebElement> btnOfCreateAmenityBooking;

	@FindBy(id = "user_search_box")
	public static WebElement searchUnit;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfUnit;

	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static WebElement dateCalendarIcon;
	
	@FindBy(xpath = "//button[contains(.,'Submit Request')]")
	public static WebElement submitRequestBtn;

	public boolean clickCheckAvailabilityBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(2), 30);
		if (checkAvailabilityBtn.get(2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkAvailabilityBtn.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean clickBtnOfCreateAmenityBooking() {
		SeleniumWrapper.explicitWaitClickable(driver, btnOfCreateAmenityBooking.get(0), 30);
		if (btnOfCreateAmenityBooking.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, btnOfCreateAmenityBooking.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean setDate() {
		Function.clickElement(driver, dateCalendarIcon);
		return Function.setDatefromCalendar(driver, 0);
	}

	public boolean inputUnit() {
		Function.clickElement(driver, searchUnit);
		SeleniumWrapper.setInputFieldText(searchUnit, "101", driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfUnit, 50);
		if (choiceOfUnit.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, choiceOfUnit);
			SeleniumWrapper.clickElement(driver, choiceOfUnit, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
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
	 * Check edit amenity booking from thumbnail tab
	 */
	@FindBy(xpath = "//a[contains(.,'Edit Booking')]/span[2]")
	public static List<WebElement> EditBookingBtnFromThumbnailViewTab;

	@FindBy(name = "booking_notes")
	public static WebElement notesBox;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public static WebElement saveBtn;
	
	public boolean clickBooking() {
		return Function.clickElement(driver, eventListFromThumbnailViewTab.get(0));
	}

	public boolean clickEditBookingBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, EditBookingBtnFromThumbnailViewTab.get(0), 30);
		if (EditBookingBtnFromThumbnailViewTab.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, EditBookingBtnFromThumbnailViewTab.get(0),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean addNote() {
		Function.hoverNclickElement(driver, notesBox);
		SeleniumWrapper.setInputFieldText(notesBox, "Confirmed booking", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("The note content is:" + notesBox.getAttribute("value"));
		return true;
	}

	public boolean clickSaveBtn() {
		Function.hoverNclickElement(driver, saveBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check edit amenity booking from list view tab
	 */
	@FindBy(xpath = "//*[contains(text(),'Edit Booking')]")
	public static List<WebElement> editBookingBtnFromListViewTab;

	@FindBy(xpath = "//button[contains(.,'Cancel & Delete')]")
	public static WebElement cancelNDeleteBtn;

	@FindBy(xpath = "//*[@id=\"modal_amenity_decline_note\"]")
	public static WebElement cancelNoteBox;

	@FindBy(xpath = "//button[contains(.,'Decline & Delete Booking')]")
	public static WebElement declineNDeleteBookingBtn;

	public boolean clickBookingFromListViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, recordFromListViewTab.get(0), 50);
		if (recordFromListViewTab.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, recordFromListViewTab.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean editBookingFromListViewTab() {
		String parentWindow = driver.getWindowHandle();
		Function.hoverNclickElement(driver, editBookingBtnFromListViewTab.get(0));
		Function.switchToNewWindow(driver, parentWindow);
		Function.hoverNclickElement(driver, cancelNDeleteBtn);
		Function.clickElement(driver, cancelNoteBox);
		SeleniumWrapper.setInputFieldText(cancelNoteBox, "User request to cancel the booking", driver);
		logger.info("Notes for cancellation:" + cancelNoteBox.getAttribute("value"));
		Function.hoverNclickElement(driver, declineNDeleteBookingBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.closeNSwitchWindow(driver, parentWindow);
	}
	
	
	/**
	 * Check delete amenity booking from Amenity Booking module
	 */
	
	public boolean editAmenityBooking() {
		Function.clickElement(driver, eventListFromThumbnailViewTab.get(eventListFromThumbnailViewTab.size()-1));
	    SeleniumWrapper.explicitWaitClickable(driver, EditBookingBtnFromThumbnailViewTab.get(EditBookingBtnFromThumbnailViewTab.size()-1), 30);
		if (EditBookingBtnFromThumbnailViewTab.get((EditBookingBtnFromThumbnailViewTab.size()-1)).isEnabled()) {
			SeleniumWrapper.clickElement(driver, EditBookingBtnFromThumbnailViewTab.get((EditBookingBtnFromThumbnailViewTab.size()-1)),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}
	
	public boolean deleteAmenityBooking() {
		Function.hoverNclickElement(driver, cancelNDeleteBtn);
		Function.clickElement(driver, cancelNoteBox);
		SeleniumWrapper.setInputFieldText(cancelNoteBox, "User request to cancel the booking", driver);
		logger.info("Notes for cancellation:" + cancelNoteBox.getAttribute("value"));
		SeleniumWrapper.explicitWaitClickable(driver,declineNDeleteBookingBtn, 30);
		if (declineNDeleteBookingBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, declineNDeleteBookingBtn,Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}
	
	

	/**
	 * Check "Show Canceled Bookings" function
	 */
	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> checkbox;

	@FindBy(xpath = "//*[@class='fc-title']")
	public static List<WebElement> bookingList;

	@FindBy(xpath = "//*[@class='popover-title']")
	public static WebElement bookingTitle;

	@FindBy(xpath = "//*[@class='popover-title']/span[3]")
	public static WebElement closeTooltip;

	@FindBy(xpath = "//*[@class='btn-selected icon-checkmark']")
	public static List<WebElement> checkedCheckbox;

	public boolean enableShowCanceledBookingsOption() {
		SeleniumWrapper.explicitWaitClickable(driver, checkbox.get(0), 30);
		if (checkbox.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkbox.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean verifyBookingStatus() {
		Function.hoverNclickElement(driver, bookingList.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, bookingTitle, 30);
		String bookingHeader = bookingTitle.getText();
		logger.info("The booking title is :" + bookingHeader);
		if (bookingHeader.contains("Canceled"))
			return true;
		else
			return false;
	}

	public boolean closeToolTip() {
		SeleniumWrapper.explicitWaitClickable(driver, closeTooltip, 30);
		if (closeTooltip.isEnabled())
			return SeleniumWrapper.clickElement(driver, closeTooltip, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean disableShowCanceledBookingsOption() {
		SeleniumWrapper.explicitWaitClickable(driver, checkedCheckbox.get(0), 30);
		if (checkedCheckbox.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkedCheckbox.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	

}
