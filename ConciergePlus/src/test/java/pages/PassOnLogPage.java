package pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import com.utilities.TestResultValidator;
import config.Constants;

public class PassOnLogPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(PassOnLogPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */

	public PassOnLogPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info(" Pass On Log Page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoPassOnLogPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createEntryBtn.isEnabled())
			return true;
		return false;
	}

	/**
	 * Navigate to Pass On Log menu
	 */
	@FindBy(xpath = "//*[@id='create-passlog']/span[2]")
	public static WebElement createEntryBtn;

	@FindBy(xpath = "//*[@class='icon-pol']")
	public static WebElement passOnLogMenuIcon;

	public boolean navigateToPassOnLogMenu() {
		SeleniumWrapper.explicitWaitClickable(driver, passOnLogMenuIcon, 30);
		if (passOnLogMenuIcon.isEnabled())
			return SeleniumWrapper.clickElement(driver, passOnLogMenuIcon, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check is "Subject" field Mandatory
	 */
	@FindBy(xpath ="//*[contains(text(), 'Save')]")
	public static WebElement saveBtn;

	public boolean clickCreateEntryBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createEntryBtn, 30);
		if (createEntryBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createEntryBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		if (saveBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, saveBtn);
			SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check error message present
	 */
	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsg; // index 4

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg.get(4), 30);
		String errorMessage = errorMsg.get(4).getText();
		logger.info("The error message is :" + errorMessage);
		if(errorMessage.contains("One or more required fields below have not been completed."))
			return true;
		return false;
	}

	/**
	 * Check cancel button
	 */
	@FindBy(id = "modal-cancel-button")
	public static List<WebElement> cancelBtn;

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn.get(0), 30);
		if (cancelBtn.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, cancelBtn.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check setting of who will receive the new Pass On Log notification
	 */
	@FindBy(xpath = "//*[@class='btn-selected icon-checkmark']")
	public static List<WebElement> checkedBtnOfWhoWillReceiveThis;

	public boolean setWhoWillReceiveNotification() {
		int i = checkedBtnOfWhoWillReceiveThis.size()/2 + 2; // when i=5, uncheck from 1st user
		do {
			Function.clickElement(driver, checkedBtnOfWhoWillReceiveThis.get(i));
			i++;
		} while (i <= checkedBtnOfWhoWillReceiveThis.size() - 1); // -2 :keep last user uncheck
		return true;
	}

	/**
	 * Check user Concierge create new Pass On Log
	 */
	@FindBy(name = "subject")
	public static WebElement subjectInputBox;

	@FindBy(name = "details")
	public static WebElement detailInputBox;

	@FindBy(xpath = "//*[@id='datepicker_date_expiration']/span/span")
	public static WebElement showThroughDatePicker;

	@FindBy(name = "date_expiration")
	public static WebElement showThroughDate;

	String subject_1 = "Fedex for #201";
	String subject_2 = "USPS Pickup In Mail Closet";
	String subject_3 = "Power off at gym at 8pm-9pm";

	public boolean enterSubject_1() {
		SeleniumWrapper.explicitWaitClickable(driver, subjectInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, subjectInputBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(subjectInputBox, subject_1, driver);
			logger.info("subject is:" + subject_1);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean enterSubject_2() {
		SeleniumWrapper.explicitWaitClickable(driver, subjectInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, subjectInputBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(subjectInputBox, subject_2, driver);
			logger.info("subject is:" + subject_2);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean enterSubject_3() {
		SeleniumWrapper.explicitWaitClickable(driver, subjectInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, subjectInputBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(subjectInputBox, subject_3, driver);
			logger.info("subject is:" + subject_3);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean enterDetail() {
		SeleniumWrapper.explicitWaitClickable(driver, detailInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, detailInputBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(detailInputBox, "pass on log", driver);
			logger.info("Detail is:" + detailInputBox.getAttribute("value"));
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean enterShowThroughDate() {
		Function.clickElement(driver, showThroughDatePicker);
		Function.setDatefromCalendar(driver, 4);
		logger.info("Show through date is:" + showThroughDate.getAttribute("value"));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
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
		if(confirmMsg.isDisplayed() && !message.isEmpty())
			return true;
		return false;
	}

	// ************************************************************************************

	/**
	 * Check mark as read on notification if notification is present
	 */
	@FindBy(xpath = "//*[@id=\"modal_passonlogs_view-content-wrapper\"]")
	public static WebElement popup;

	public boolean clickMarkAsReadOnNotification() {
		/*
		 * SeleniumWrapper.explicitWaitClickable(driver, markAsReadBtn, 50); if
		 * (markAsReadBtn.isEnabled()) { SeleniumWrapper.clickElement(driver,
		 * passOnLogMenuIcon, Constants.CLICK_METHOD_ENUM.CLICK);
		 * SeleniumWrapper.waitForDomToBeRendered_5S(driver); return true; } return
		 * false;
		 */
		return true;
	}

	public boolean clickShowMeThisLaterOnNotification() {
		SeleniumWrapper.explicitWaitClickable(driver, showMeThisLaterBtn, 50);
		if (showMeThisLaterBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, showMeThisLaterBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	// ************************************************************************************

	/**
	 * Check status of new pass on log
	 */
	public static final String tableRowsXpath_All = "//*[@id='datatable_active']/tbody/tr";
	@FindBy(xpath = tableRowsXpath_All)
	public static List<WebElement> tableRowOfPassOnLogs;

	public boolean statusOfNewPassOnLogs() {
		SeleniumWrapper.explicitWaitClickable(driver, tableRowOfPassOnLogs.get(0), 50);
		String record = tableRowOfPassOnLogs.get(0).getText();
		logger.info("record is:" + record);
		if(record.contains("READ") && record.contains("USPS Pickup In Mail Closet"))
			return true;
		return false;
	}

	/**
	 * Check search active pass on log
	 */
	@FindBy(id = "search_active-input")
	public static WebElement searchInputBox_active;

	public boolean searchActivePassOnLog() {
		String searchKeyword = "Fedex";
		return Function.search(driver, searchInputBox_active, searchKeyword, tableRowsXpath_All);
	}

	/**
	 * Check who has read the pass on log
	 */
	@FindBy(xpath = "//*[@id='datatable_active']/tbody/tr/td[7]")
	public static List<WebElement> viewDetailIcon_Active;

	@FindBy(xpath = "//*[@id=\"users_multiple_options_option_container\"]/ul/li")
	public static List<WebElement> whoHasReadPassOnLog;

	@FindBy(xpath = "//*[contains(text(),'Show me this later')]")
	public static WebElement showMeThisLaterBtn;

	public boolean clickViewDetailIconFromAllTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, viewDetailIcon_Active.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, viewDetailIcon_Active.get(0), 30);
		if (viewDetailIcon_Active.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, viewDetailIcon_Active.get(0),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean verifyWhoHasReadPassOnLog() {
		SeleniumWrapper.explicitWaitClickable(driver, whoHasReadPassOnLog.get(0), 30);
		List<String> usersOnNotification = new ArrayList<>();
		for (int i = 0; i < whoHasReadPassOnLog.size(); i++)
			usersOnNotification.add(whoHasReadPassOnLog.get(i).getText());
		logger.info("user and status: " + usersOnNotification);
		if(usersOnNotification.contains("READ Joy Shaw"))
			return true;
		else
		return false;
	}

	public boolean clickShowMeThisLaterBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, showMeThisLaterBtn, 30);
		if (showMeThisLaterBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, showMeThisLaterBtn);
			SeleniumWrapper.clickElement(driver, showMeThisLaterBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		}
		return false;
	}

	/**
	 * Check mark as read on the unread pass on log
	 */
	@FindBy(xpath = "//*[contains(text(),'Mark as Read')]")
	public static WebElement markAsReadBtn;

	public boolean clickMarkAsReadBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, markAsReadBtn, 50);
		if (markAsReadBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, markAsReadBtn);
			SeleniumWrapper.clickElement(driver, markAsReadBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check goto UNREAD tab and verify status of unread pass on log
	 */
	@FindBy(xpath = "//*[@id='calendar_events_tabs']/a[2]/span")
	public static WebElement unreadTab;

	@FindBy(id = "modal-cancel-button")
	public static WebElement closeBtn;

	@FindBy(xpath = "//*[@id='modal_passonlogs_viewLabel']/span")
	public static WebElement status;

	public boolean goToUnreadTab() {
		SeleniumWrapper.explicitWaitClickable(driver, unreadTab, 30);
		if (unreadTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, unreadTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean viewUnreadPassOnLog() {
		SeleniumWrapper.explicitWaitClickable(driver, unreadTab, 30);
		if (unreadTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, unreadTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean viewStatus() {
		SeleniumWrapper.explicitWaitClickable(driver, status, 30);
		String statusOfEntry = status.getText();
		logger.info("Entry status:" + statusOfEntry);
		if(statusOfEntry.equalsIgnoreCase("UNREAD"))
			return true;
		else
		return false;
	}

	public boolean clickCloseBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, closeBtn, 30);
		if (closeBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, closeBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check goto READ tab and verify status of read pass on log
	 */
	@FindBy(xpath = "//*[@id='calendar_events_tabs']/a[3]/span")
	public static WebElement readTab;

	public boolean goToReadTab() {
		SeleniumWrapper.explicitWaitClickable(driver, readTab, 30);
		if (readTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, readTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check delete pass on log from READ tab
	 */
	@FindBy(xpath = "//*[@id='datatable_active']/tbody/tr/td[8]")
	public static List<WebElement> deleteIcon_Active;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean clickDeleteIconOnActiveEntry() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon_Active.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, deleteIcon_Active.get(0), 30);
		if (deleteIcon_Active.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteIcon_Active.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check goto All tab and delete pass on log
	 * 
	 */
	@FindBy(xpath = "//*[@id='calendar_events_tabs']/a[1]/span")
	public static WebElement allTab;

	public boolean goToAllTab() {
		SeleniumWrapper.explicitWaitClickable(driver, allTab, 30);
		if (allTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, allTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check show pass on logs for certain period
	 */
	@FindBy(xpath = "//*[@id=\"datepicker_date_filter_pol_1\"]/span/span")
	public static WebElement datePicker_Start;

	@FindBy(name = "date_filter_pol_start")
	public static WebElement startDateField;

	@FindBy(xpath = "//*[@id=\"datepicker_date_filter_pol_2\"]/span/span")
	public static WebElement datePicker_End;

	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td")
	public static List<WebElement> datePicker;

	@FindBy(name = "date_filter_pol_end")
	public static WebElement endDateField;

	public boolean setStartDate() {
		SeleniumWrapper.explicitWaitClickable(driver, datePicker_Start, 30);
		if (SeleniumWrapper.clickElement(driver, datePicker_Start, Constants.CLICK_METHOD_ENUM.CLICK)) {
			Function.clickElement(driver, datePicker.get(0));
			logger.info("Start date is:" + startDateField.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean setEndDate() {
		SeleniumWrapper.explicitWaitClickable(driver, datePicker_End, 30);
		if (SeleniumWrapper.clickElement(driver, datePicker_End, Constants.CLICK_METHOD_ENUM.CLICK)) {
			Function.setDatefromCalendar(driver, 0);
			logger.info("End date is:" + endDateField.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean viewFilterResults() {
		return TestResultValidator.isFilterResultListed(tableRowsXpath_All, driver);
	}

	/**
	 * Check go to MY ENTRIES tab and view my pass on log
	 */
	@FindBy(xpath = "//*[@href='#my-logs']")
	public static WebElement myEntriesTab;

	public static final String tableRowsXpath_MyEntries = "//*[@id='datatable_my']/tbody/tr";
	@FindBy(xpath = tableRowsXpath_MyEntries)
	public static List<WebElement> tableRowOfMyPassOnLogs;

	@FindBy(xpath = " //*[@id='datatable_my']/tbody/tr/td[5]")
	public static List<WebElement> viewDetailIcon_MyEntries;

	public boolean goToMyEntriesTab() {
		SeleniumWrapper.explicitWaitClickable(driver, myEntriesTab, 30);
		if (myEntriesTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, myEntriesTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean viewMyEntries() {
		return TestResultValidator.isFilterResultContainKeyword(tableRowsXpath_MyEntries, driver, subject_3);
	}

	public boolean clickViewDetailIconFromMyEntriesTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, viewDetailIcon_MyEntries.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, viewDetailIcon_MyEntries.get(0), 30);
		if (viewDetailIcon_MyEntries.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, viewDetailIcon_MyEntries.get(0),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check go to ALL ENTRIES tab
	 */
	@FindBy(xpath = "//*[@href='#all-logs']")
	public static WebElement allEntriesTab;

	public boolean goToAllEntriesTab() {
		SeleniumWrapper.explicitWaitClickable(driver, allEntriesTab, 30);
		if (allEntriesTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, allEntriesTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check go to EXPIRED ENTRIES tab
	 */
	@FindBy(xpath = "//*[@href='#expired']")
	public static WebElement expiredEntriesTab;

	public static final String tableRowsXpath_ExpiredEntries = "//*[@id='datatable_expired']/tbody/tr";
	@FindBy(xpath = tableRowsXpath_ExpiredEntries)
	public static List<WebElement> tableRowOfExpiredPassOnLogs;

	public boolean goToExpiredEntriesTab() {
		SeleniumWrapper.explicitWaitClickable(driver, expiredEntriesTab, 30);
		if (expiredEntriesTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, expiredEntriesTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean viewExpiredEntries() {
		return TestResultValidator.isFilterResultContainKeyword(tableRowsXpath_ExpiredEntries, driver, subject_3);
	}

	/**
	 * Check view detail of expired entry
	 */
	@FindBy(xpath = " //*[@id='datatable_expired']/tbody/tr/td[7]")
	public static List<WebElement> viewDetailIcon_ExpiredEntries;

	@FindBy(xpath = "//*[@id='show_until']/div[2]")
	public static WebElement showThroughDateOfExpiredEntry;

	public boolean clickViewDetailIconFromExpiredEntriesTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, viewDetailIcon_ExpiredEntries.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, viewDetailIcon_ExpiredEntries.get(0), 30);
		if (viewDetailIcon_ExpiredEntries.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, viewDetailIcon_ExpiredEntries.get(0),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean viewShowThroughDateOnExpiredEntry() {
		SeleniumWrapper.explicitWaitClickable(driver, showThroughDateOfExpiredEntry, 30);
		String date = showThroughDateOfExpiredEntry.getText();
		logger.info("Show through date is :" + date);
		return true;
	}

	/**
	 * Check delete expired entry
	 */
	@FindBy(xpath = "//*[@id='datatable_expired']/tbody/tr/td[8]")
	public static List<WebElement> deleteIcon_Expired;

	public boolean clickDeleteIconOnExpiredEntry() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon_Expired.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, deleteIcon_Expired.get(0), 30);
		if (deleteIcon_Expired.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteIcon_Expired.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

}
