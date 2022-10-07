package pages.maintenance;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import com.utilities.TestResultValidator;
import config.Constants;
import pages.BasePage;

public class ServiceRequestsPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(ServiceRequestsPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public ServiceRequestsPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Service Requests page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoServiceRequestsPage().equals(null);
	}
	
	@Override
	public boolean isLoaded() {
		if(resolvedTab.isEnabled()) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check is user Resident logged in application
	 */
	public boolean isUserResidentLoggedInApplication() {
		return Function.isUserLoggedIn(driver, "Daniels Qu");
	}
	
	
	/**
	 * Navigate to Service Requests page
	 */
	@FindBy(xpath = "//*[contains(.,'Maintenance')]/span[2]")
	public static WebElement MaintenanceTab;
	
	@FindBy(xpath = "//a[@href ='/service_requests']/span")
	public static WebElement serviceRequestsPage;

	public boolean navigateToServiceRequestsPage() {
		Function.clickElement(driver, MaintenanceTab);
		return Function.clickElement(driver, serviceRequestsPage);
	}
	
	
	/**
	 * Check Resident user create new service request
	 */

	/**
	 * input subject
	 */
	@FindBy(xpath = "//input[@name ='subject']")
	public static WebElement subjectField;

	 String txtOfPowerOff ="Power off, please fix asap";
	public boolean enterSubjectOfNoPower() {
		SeleniumWrapper.explicitWaitClickable(driver, subjectField, 20);
		if (SeleniumWrapper.clickElement(driver, subjectField, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldTextNoClear(subjectField, txtOfPowerOff, driver);
			logger.info("The subject is:" + subjectField.getAttribute("value"));
		}
		return true;
	}
	
	 String txtOfWaterLeaking = "Water leaking from washroom, pls fix asap";
	public boolean enterSubjectOfWaterLeaking() {
		SeleniumWrapper.explicitWaitClickable(driver, subjectField, 20);
		if (SeleniumWrapper.clickElement(driver, subjectField, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldTextNoClear(subjectField, txtOfWaterLeaking, driver);
			logger.info("The subject is:" + subjectField.getAttribute("value"));
		}
		return true;
	}

	/**
	 * enter description
	 */
	public boolean clickTxtBox() {
		Function.hoverNclickElement(driver, textBox);
		return Function.hoverNclickElement(driver, textBoxEditable);
	}
	
	public boolean enterDescOfPowerOff() {
		return SeleniumWrapper.setInputFieldTextNoClear(textBoxEditable,txtOfPowerOff, driver);
	}
	
	public boolean enterDescOfWaterLeaking() {
		return SeleniumWrapper.setInputFieldTextNoClear(textBoxEditable,txtOfWaterLeaking, driver);
	}
	
	/**
	 * Check Resident user update and close new service request
	 */
	@FindBy(xpath = "//button[contains(.,'Update and Close Ticket')]")
	private static WebElement updateAndCloseTicketBtn ;

	@FindBy(id = "login-button")
	private static WebElement resolvedTabByResident;

	@FindBy(xpath = "//input[@name ='archieved_search-input']")
	public static WebElement searchBoxByResident;

	public static final String tableRowsXpath_Resident_Resolved = "//*[@id=\"datatable_archived\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath_Resident_Resolved)
	public static List<WebElement> serviceRequestsTableRowByResident_Resolved;

      public static String idOfNewRecord_Resident;
	public boolean openNewStatusRecordByResident() {
		Function.hoverNclickElement(driver, statusOfNewList.get(0));
		idOfNewRecord_Resident = refNum.get(1).getText();
		logger.info("The ID is:" + idOfNewRecord_Resident);   
		return true;
	}

	public boolean enterResolvedMsg() {
		SeleniumWrapper.setInputFieldText(textBoxEditable, "Resolved", driver);
		logger.info("The final message is: " + textBoxEditable.getText());
		return true;
	}

	public boolean clickUpdateAndCloseTicketBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, updateAndCloseTicketBtn, 20);
		if(updateAndCloseTicketBtn.isEnabled())
		return Function.clickElement(driver, updateAndCloseTicketBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}
	
	public boolean isMovedToResolvedTabAndUpdatedStatus_R() {
		String keyword = idOfNewRecord_Resident;
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBoxByResident, keyword, tableRowsXpath_Resident_Resolved);
	}


	/**
	 * Check Resident user cant see internal note on service request
	 */
	@FindBy(xpath = "//*[@class='content']")
	public static List<WebElement> workLogs;
	
	public static String IDOfResidentRecord;
	public static String internalMessage = "Send Mike";
	
	public boolean userResidentCantSeeInternalNote() {
		Function.clickElement(driver, statusOfWaitingForClientResponseList.get(0));
		List<WebElement> workLogs = driver.findElements(By.xpath("//*[@class='content']"));
		for (WebElement log : workLogs) {
			logger.info("The work log content are:" + log.getText());
			if (!log.getText().contains(internalMessage)) {
				logger.info("The work log is not contain internal note");
			} else {
				logger.info("The work log contains internal note");
			}
		}
		return true;
	}


	/**
	 * Check user Resident reply service request
	 */
	public static final String tableRowsXpath_Resident_Current = "//*[@id=\"datatable_requests\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath_Resident_Current)
	public static List<WebElement> serviceRequestsTableRowByResident_Current;

	@FindBy(xpath = "//*[@id=\"datatable_requests\"]/tbody/tr/td[2]")
	public static List<WebElement> columnID_Resident;

	@FindBy(xpath = "//*[@id=\"datatable_requests\"]/tbody/tr/td[5]")
	public static List<WebElement> columnStatus_Resident;
	
	@FindBy(xpath = "//div[contains(@class, 'fr-wrapper')]")
	public static WebElement textBox;

	@FindBy(xpath = "//div[contains(@class, 'fr-element fr-view')]")
	public static WebElement textBoxEditable;

	@FindBy(xpath = "//button[contains(., 'Update Ticket')]")
	public static WebElement updateTicketBtn;
	
   public static String idOfWaitingForYourResponseRecord;
	public boolean openWaitingForYourResponseRecordByResident() {
		Function.hoverNclickElement(driver, statusOfWaitingForClientResponseList.get(0));
		idOfWaitingForYourResponseRecord = refNum.get(1).getText();
		logger.info("The ID is:" + idOfWaitingForYourResponseRecord);
		return true;
	}
	
	public boolean msgByResidentUser() {
		SeleniumWrapper.setInputFieldText(textBoxEditable, "drop key", driver);
		logger.info("The final message is: " + textBoxEditable.getText());
		return true;
	}
	
	
//*****************************************************************************************************
	
	/**
	 * Go back to Service Requests tab
	 */
	@FindBy(xpath = "//a[contains(text(), 'Service Requests')]")
	public static WebElement serviceRequestTab;

	public boolean goBackToServiceRequestTab() {
		Function.hoverNclickElement(driver, serviceRequestTab);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	
	/**
	 * Check reply new service request in Internal Note from Current tab
	 */
	@FindBy(xpath = "//*[contains(text(),'Internal Note')]")
	public static WebElement internalNoteTab; 

	public boolean clickInternalNoteTab() {
		return Function.hoverNclickElement(driver, internalNoteTab);
	}

	public boolean enterInternalMsg() {
		return SeleniumWrapper.setInputFieldText(textBoxEditable, internalMessage, driver);
	}
	
	public boolean clickUpdateTicketBtn() {
		 Function.hoverNclickElement(driver, updateTicketBtn);
		 SeleniumWrapper.waitForDomToBeRendered(driver);
		 return true;
	}
	
	/**
	 * Check update new request to status of "Wait for client Response"  from Current tab
	 */
	@FindBy(xpath = "//*[@id=\"request_worklog_header_status\"]/div/div[1]/div/button")
	public static WebElement statusBox;

	@FindBy(xpath = " //*[@id=\"bs-select-1\"]/ul/li/a/span")
	public static List<WebElement> choiceOfStatus;
	
	public boolean updateStatusToWaitForClientResponse() {
		Function.clickElement(driver, statusBox);
		return Function.hoverNclickElement(driver, choiceOfStatus.get(1));	
	}
	
	public boolean enterMsgToClient() {
		return SeleniumWrapper.setInputFieldText(textBoxEditable, "DropKey", driver);
	}
	
	
	/**
	 * Check is new service request remove from Current tab
	 */
	public boolean isNewServiceRequestRemoveFromCurrentTab(){
		String keyword = idOfNewRecord;
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBox, keyword, tableRowsXpath_Current);
	}
	
	
	/**
	 * Check service request is remove from Current tab
	 */
	
	public boolean IsRemoveFromCurrentTab(){
		String keyword = idOfTicketUpdatedRecord; 
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBox, keyword, tableRowsXpath_Current);
	}
	
	
	/**
	 * Check go back Current tab
	 */
	@FindBy(xpath = "//a[@href='#tabs-1']")
	public static WebElement goBackCurrentTab;
	
	public boolean goBackCurrentTab() {
		Function.clickElement(driver, goBackCurrentTab);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}
	

	/**
	 * Check go to Waiting tab from Current tab
	 */
	@FindBy(xpath = "//a[@href='#tabs-2']")
	public static WebElement goToWaitingTab;

	public static final String tableRowsXpath_Waiting = "//*[@id=\"datatable_waiting\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath_Waiting)
	public static List<WebElement> serviceRequestsTableRowByWaitingTab;

	public boolean goToWaitingTab() {
		Function.clickElement(driver, goToWaitingTab);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check service request is moved to Waiting tab from Current tab
	 */
	@FindBy(xpath = "//input[@name ='waiting_search-input']")
	public static WebElement searchBox_WaitingTab;

	public boolean verifyServiceRequestMoveToWaitingTab() {
		String keyword = idOfNewRecord;
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBox_WaitingTab, keyword, tableRowsXpath_Waiting);
	}
	

	/**
	 * Check default user user Property Manager (PM) create service request
	 */
	@FindBy(xpath = "//a[contains(@href,'/service_requests/createRequest')]")
	public static List<WebElement> createServiceRequestBtn;

	@FindBy(xpath = "//button[contains(.,'Electrical')]")
	public static WebElement categoryByDefault;

	@FindBy(xpath = "//a[contains(.,'Plumbing')]")
	public static WebElement categoryOfPlumbing;

	@FindBy(xpath = "//a[contains(.,'General')]")
	public static WebElement categoryOfGeneral;

	@FindBy(xpath = "//button[contains(.,'Normal')]")
	public static WebElement priorityByDefault;

	@FindBy(xpath = "//a[contains(.,'High')]")
	public static WebElement priorityOfHigh;

	@FindBy(xpath = "//a[contains(.,'Low')]/span")
	public static WebElement priorityOfLow;

	@FindBy(xpath = "//input[@name ='subject']")
	public static List<WebElement> subjectBox; // get index 0

	@FindBy(xpath = "//input[@name ='user_search_box']")
	public static WebElement unitSearchBox;

	@FindBy(xpath = "//div[contains(@class,'tt-suggestion')]")
	public static List<WebElement> choiceOfUnits;

	@FindBy(xpath = "//*[@id=\"button-is_auth_unit_access\"]/span[1]")
	public static WebElement checkboxOfAllowAccesstoMyUnit;

	@FindBy(xpath = "//button[contains(.,'Submit Request')]")
	public static WebElement submitRequestBtn;

	/**
	 * Click create service request button
	 */
	public boolean clickCreateServiceRequestBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createServiceRequestBtn.get(0), 30);
		if(createServiceRequestBtn.get(0).isEnabled())
		return Function.clickElement(driver, createServiceRequestBtn.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Pick category of "Plumbing"
	 */
	public boolean pickCategoryOfPlumbing() {
		Function.clickElement(driver, categoryByDefault);
		return Function.clickElement(driver, categoryOfPlumbing);
	}

	/**
	 * Pick priority of "High"
	 */
	public boolean pickPriorityOfHigh() {
		Function.clickElement(driver, priorityByDefault);
		return Function.clickElement(driver, priorityOfHigh);
	}

	
	/**
	 * search and pick unit
	 */
	public boolean searchAndPickUnit() {
		Function.clickElement(driver,unitSearchBox);
		SeleniumWrapper.setInputFieldText(unitSearchBox, "101", driver);
		return Function.hoverNclickElement(driver, choiceOfUnits.get(0));
	}
	
	/**
	 * Check allow access to my unit for the purpose of resolving this issue
	 */
	public boolean checkAllowAccessToMyUnitBox() {
		return Function.hoverNclickElement(driver, checkboxOfAllowAccesstoMyUnit);
	}
	

	/**
	 * Close delivery editing page pop-up
	 */
	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public static List<WebElement> cancelBtn;

	public boolean closeEditPopup() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn.get(1), 50);
		return Function.hoverNclickElement(driver, cancelBtn.get(1));
	}

	/**
	 * Click submit Request button
	 */
	public boolean clickSubmitRequestBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, submitRequestBtn, 20);
		if(submitRequestBtn.isEnabled())
	   return Function.clickElement(driver, submitRequestBtn);
	   SeleniumWrapper.waitForDomToBeRendered(driver);
	   return false;
	}
	
	/**
	 * Check is success message present
	 */
	@FindBy(xpath = "//*[@id='success_container']/span[2]")
	public static WebElement confirmMsg;

	public boolean isSuccessMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmMsg, 20);
		String message = confirmMsg.getText();
		logger.info("message is:" + message);
		if (confirmMsg.isDisplayed()&& !message.isEmpty())
			return true;
		return false;
	}
	

	/**
	 * Check update new service request to "Working On It" from Current tab
	 */
	public boolean enterMsgOfWorkOnIt() {
		return SeleniumWrapper.setInputFieldText(textBoxEditable, "WorkOnIt", driver);
	}

	public boolean verifyStatusUpdatedFromNewToWorkongOnIt() {
		SeleniumWrapper.explicitWaitClickable(driver, searchBox, 30);
		if (SeleniumWrapper.clickElement(driver, searchBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldTextNoClear(searchBox, idOfNewRecord, driver);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			TestResultValidator.isFilterResultContainKeyword(tableRowsXpath_Current, driver, "WORKING ON IT");
		}
		return true;
	}


	/**
	 * Check service request is moved back to Current tab from Waiting tab after
	 * client update it
	 */
	public boolean isMovedBackFromWaitingTabToCurrentTab() {
		String Keyword = idOfWaitingForYourResponseRecord;
		return Function.search(driver, searchBox, Keyword, tableRowsXpath_Current);
	}
	
	
	/**
	 * Check resolve the service request
	 */
	@FindBy(xpath = " //*[@class='label status_code  status-ticket-updated']")
	public static List<WebElement> statusOfTicketUpdatedList;
	
	public static String idOfTicketUpdatedRecord;

	public boolean openTicketUpdatedStatusRecord() {
		Function.hoverNclickElement(driver, statusOfTicketUpdatedList.get(0));
		idOfTicketUpdatedRecord = refNum.get(1).getText();
		logger.info("The ID is:" + idOfTicketUpdatedRecord);
		return true;
	}
		
	public boolean updateStatusToResolved() {
		Function.clickElement(driver, statusBox);
		return Function.hoverNclickElement(driver, choiceOfStatus.get(2));
	}
	
	public boolean isMovedToResolvedTabAndUpdatedStatus_P() {
		String keyword = idOfTicketUpdatedRecord;
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBox_ResolvedTab, keyword, tableRowsXpath_Resolved);
	}
	

	/**
	 * Check New record is moved from Current tab to Resolved tab
	 * @throws Exception 
	 */
	public boolean  verifyServiceRequestMoveFromNewToResolvedTab() {
		String keyword = idOfNewRecord;
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBox_ResolvedTab, keyword, tableRowsXpath_Resolved);
	}

	
	/**
	 * Check go to Resolved tab from Current tab
	 */
	@FindBy(xpath = "//a[@href='#tabs-3']")
	public static WebElement resolvedTab;

	public static final String tableRowsXpath_Resolved = "//*[@id=\"datatable_archived_all\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath_Resolved)
	public static List<WebElement> serviceRequestsTableRowByResolvedTab;

	public boolean goToResolvedTab() {
		Function.hoverNclickElement(driver, resolvedTab);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return true;
	}

	/**
	 * Check service request is moved to resolved tab from Current tab
	 */
	@FindBy(xpath = "//input[@name ='archived_all_search-input']")
	public static WebElement searchBox_ResolvedTab;

	public boolean verifyServiceRequestMoveFromWaitingToResolvedTab(){
		String keyword = idOfRecordFromWaitingTab;
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBox_ResolvedTab, keyword,tableRowsXpath_Resolved);
	}
	

	/**
	 * Check go to Current tab
	 */
	@FindBy(xpath = "//a[@href='#tabs-1']")
	public static WebElement goToCurrentTab;

	public boolean goToCurrentTab() {
		Function.clickElement(driver, goToCurrentTab);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}
	

	/**
	 * Check search service request from Current tab
	 */
	@FindBy(xpath = "//input[@name ='new_search-input']")
	public static WebElement searchBox;

	public static final String tableRowsXpath_Current = "//*[@id=\"datatable_new\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath_Current)
	public static List<WebElement> serviceRequestsTableRowByCurrentTab;

	public boolean searchServiceRequestFromCurrentTab(){
		String searchKeyword = "locker";
		return Function.search(driver, searchBox, searchKeyword, tableRowsXpath_Current);
	}

	/**
	 * Check sort per column Subject from Current tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/thead/tr/th[2]")
	public static WebElement columnHeaderSubject_current;

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/tbody/tr/td[2]")
	public static List<WebElement> columnSubject_current;

	public boolean sortByColumnSubject_Current() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderSubject_current, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderSubject_current, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnSubject_current);
		return true;
	}

	/**
	 * Check sort per column ID from Current tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/thead/tr/th[3]")
	public static WebElement columnHeaderID_current;

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/tbody/tr/td[3]")
	public static List<WebElement> columnID_current;

	public boolean sortByColumnID_Current() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderID_current, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderID_current, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnID_current);
		return true;
	}

	/**
	 * Check sort per column Unit from Current tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/thead/tr/th[4]")
	public static WebElement columnHeaderUnit_current;

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/tbody/tr/td[4]")
	public static List<WebElement> columnUnit_current;

	public boolean sortByColumnUnit_Current() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderUnit_current, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderUnit_current, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnUnit_current);
		return true;
	}

	/**
	 * Check sort per column Category from Current tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/thead/tr/th[5]")
	public static WebElement columnHeaderCategory_current;

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/tbody/tr/td[5]")
	public static List<WebElement> columnCategory_current;

	public boolean sortByColumnCategory_Current() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderCategory_current, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderCategory_current, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnCategory_current);
		return true;
	}

	/**
	 * Check sort per column Status from Current tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/thead/tr/th[6]")
	public static WebElement columnHeaderStatus_current;

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/tbody/tr/td[6]")
	public static List<WebElement> columnStatus_current;

	public boolean sortByColumnStatus_Current() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderStatus_current, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderStatus_current, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnStatus_current);
		return true;
	}

	/**
	 * Check sort per column Last Update from Current tab
	 */
	@FindBy(xpath = "//*[@id=\"datatable_new\"]/thead/tr/th[7]")
	public static WebElement columnHeaderLastUpdate_current;

	@FindBy(xpath = "//*[@id=\"datatable_new\"]/tbody/tr/td[7]")
	public static List<WebElement> columnLastUpdate_current;

	public boolean sortByColumnLastUpdate_Current() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderLastUpdate_current, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderLastUpdate_current, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isDateElementsAscendingOrdered(columnLastUpdate_current);
		return true;
	}

	/**
	 * Check edit new service request from Current tab
	 */
	@FindBy(xpath = " //*[@class='label status_code  status-new']")
	public static List<WebElement> statusOfNewList;
	
	@FindBy(xpath = "//*[@class='summary_header_item_copy']")
	public static List<WebElement> refNum;   //get index 1
	
	public static String idOfNewRecord;
	public boolean openNewStatusRecord() {
		Function.hoverNclickElement(driver, statusOfNewList.get(0));
		idOfNewRecord = refNum.get(1).getText();
		logger.info("The ID is:" + idOfNewRecord);
		return true;
	}
	

	/**
	 * Check edit new service request from Current tab
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static WebElement editIcon;

	public boolean clickEditIcon() {
		return Function.hoverNclickElement(driver, editIcon);
	}

	public boolean modifySubject() {
		SeleniumWrapper.explicitWaitClickable(driver, subjectBox.get(0), 30);
		if (SeleniumWrapper.clickElement(driver, subjectBox.get(0), Constants.CLICK_METHOD_ENUM.CLICK)) {
			String originalSubject = subjectBox.get(0).getAttribute("value");
			logger.info("The original subject is:" + originalSubject);
			String newSubject = originalSubject + "-" + " Urgent";
			SeleniumWrapper.setInputFieldText(subjectBox.get(0), newSubject, driver);
			logger.info("The original subject is:" + subjectBox.get(0).getAttribute("value"));
		}
		return true;
	}

	/**
	 * Check delete new service request from Current Tab
	 */
	@FindBy(xpath = "//*[@id='delete-button']")
	public static WebElement deleteIcon;

	@FindBy(xpath = "//button[contains(.,'Confirm')]")
	public static WebElement confirmDeleteBtn;
	
	@FindBy(xpath = "//*[@class='label status_code  status-working']")
	public static List<WebElement> statusOfWorkingOnItList;
	
	public static String idOfWorkingOnItRecord;

	public boolean openWorkingOnItStatusRecord() {
		Function.hoverNclickElement(driver, statusOfWorkingOnItList.get(0));
		idOfWorkingOnItRecord = refNum.get(1).getText();
		logger.info("The ID is:" + idOfWorkingOnItRecord);
		return true;	
	}

	public boolean clickDeleteIcon() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteIcon,20);
		if(deleteIcon.isEnabled())
		 return SeleniumWrapper.clickElement(driver, deleteIcon, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	

	public boolean clickConfirmBtn() {
		 Function.hoverNclickElement(driver, confirmDeleteBtn);
		 SeleniumWrapper.waitForDomToBeRendered(driver);
		 return true;
	}
	

	/**
	 * Check search service request from Waiting tab
	 * 
	 * @throws Exception
	 */

	public boolean searchServiceRequestFromWaitingTab(){
		String searchKeyword = "locker";
		return Function.search(driver, searchBox_WaitingTab, searchKeyword, tableRowsXpath_Waiting);
	}

	/**
	 * Check sort per column Subject from Waiting tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/thead/tr/th[2]")
	public static WebElement columnHeaderSubject_Waiting;

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/tbody/tr/td[2]")
	public static List<WebElement> columnSubject_Waiting;

	public boolean sortByColumnSubject_Waiting() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderSubject_Waiting, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderSubject_Waiting, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnSubject_Waiting);
		return true;
	}

	/**
	 * Check sort per column ID from Waiting tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/thead/tr/th[3]")
	public static WebElement columnHeaderID_Waiting;

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/tbody/tr/td[3]")
	public static List<WebElement> columnID_Waiting;

	public boolean sortByColumnID_Waiting() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderID_Waiting, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderID_Waiting, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnID_Waiting);
		return true;
	}

	/**
	 * Check sort per column Unit from Waiting tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/thead/tr/th[4]")
	public static WebElement columnHeaderUnit_Waiting;

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/tbody/tr/td[4]")
	public static List<WebElement> columnUnit_Waiting;

	public boolean sortByColumnUnit_Waiting() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderUnit_Waiting, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderUnit_Waiting, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnUnit_Waiting);
		return true;
	}

	/**
	 * Check sort per column Category from Waiting tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/thead/tr/th[5]")
	public static WebElement columnHeaderCategory_Waiting;

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/tbody/tr/td[5]")
	public static List<WebElement> columnCategory_Waiting;

	public boolean sortByColumnCategory_Waiting() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderCategory_Waiting, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderCategory_Waiting, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnCategory_Waiting);
		return true;
	}

	/**
	 * Check sort per column Status from Waiting tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/thead/tr/th[6]")
	public static WebElement columnHeaderStatus_Waiting;

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/tbody/tr/td[6]")
	public static List<WebElement> columnStatus_Waiting;

	public boolean sortByColumnStatus_Waiting() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderStatus_Waiting, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderStatus_Waiting, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnStatus_Waiting);
		return true;
	}

	/**
	 * Check sort per column Last Update from Waiting tab
	 */
	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/thead/tr/th[7]")
	public static WebElement columnHeaderLastUpdate_Waiting;

	@FindBy(xpath = "//*[@id=\"datatable_waiting\"]/tbody/tr/td[7]")
	public static List<WebElement> columnLastUpdate_Waiting;

	public boolean sortByColumnLastUpdate_Waiting() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderLastUpdate_Waiting, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderLastUpdate_Waiting, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isDateElementsAscendingOrdered(columnLastUpdate_Waiting);
		return true;
	}

	/**
	 * Check update status on service request from Waiting tab
	 */
	@FindBy(xpath = " //*[@class='label status_code  status-waiting']")
	public static List<WebElement> statusOfWaitingForClientResponseList;
	
	public static String idOfRecordFromWaitingTab;
	public boolean openServiceRequestFromWaitingTab() {
		SeleniumWrapper.explicitWaitClickable(driver, statusOfWaitingForClientResponseList.get(0), 30);
		idOfRecordFromWaitingTab = columnID_Waiting.get(0).getText();
		logger.info("The Id of the service request is:" + idOfRecordFromWaitingTab);
		return SeleniumWrapper.clickElement(driver, statusOfWaitingForClientResponseList.get(0),Constants.CLICK_METHOD_ENUM.CLICK);
	}

	/**
	 * Check service request is remove from Waiting tab 
	 */
	public boolean IsRemoveFromWaitingTab(){
		String keyword = idOfRecordFromWaitingTab;
		logger.info("The search keyword is:" + keyword);
		return Function.search(driver, searchBox_WaitingTab, keyword, tableRowsXpath_Waiting);
	}

	/**
	 * Check search service request from Resolved tab
	 * 
	 * @throws Exception
	 */
	public boolean searchServiceRequestFromResolvedTab() throws Exception {
		String keyword = "Power";
		return Function.search(driver, searchBox_ResolvedTab, keyword, tableRowsXpath_Resolved);
	}

	/**
	 * Check sort per column Subject from Resolved tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/thead/tr/th[1]")
	public static WebElement columnHeaderSubject_Resolved;

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/tbody/tr/td[1]")
	public static List<WebElement> columnSubject_Resolved;

	public boolean sortByColumnSubject_Resolved() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderSubject_Resolved, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderSubject_Resolved, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnSubject_Resolved);
		return true;
	}

	/**
	 * Check sort per column ID from Resolved tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/thead/tr/th[2]")
	public static WebElement columnHeaderID_Resolved;

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/tbody/tr/td[2]")
	public static List<WebElement> columnID_Resolved;

	public boolean sortByColumnID_Resolved() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderID_Resolved, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderID_Resolved, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnID_Resolved);
		return true;
	}

	/**
	 * Check sort per column Unit from Resolved tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/thead/tr/th[3]")
	public static WebElement columnHeaderUnit_Resolved;

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/tbody/tr/td[3]")
	public static List<WebElement> columnUnit_Resolved;

	public boolean sortByColumnUnit_Resolved() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderUnit_Resolved, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderUnit_Resolved, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnUnit_Resolved);
		return true;
	}

	/**
	 * Check sort per column Category from Resolved tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/thead/tr/th[4]")
	public static WebElement columnHeaderCategory_Resolved;

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/tbody/tr/td[4]")
	public static List<WebElement> columnCategory_Resolved;

	public boolean sortByColumnCategory_Resolved() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderCategory_Resolved, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderCategory_Resolved, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnCategory_Resolved);
		return true;
	}

	/**
	 * Check sort per column Status from Resolved tab
	 */

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/thead/tr/th[5]")
	public static WebElement columnHeaderStatus_Resolved;

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/tbody/tr/td[5]")
	public static List<WebElement> columnStatus_Resolved;

	public boolean sortByColumnStatus_Resolved() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderStatus_Resolved, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderStatus_Resolved, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnStatus_Resolved);
		return true;
	}

	/**
	 * Check sort per column Last Update from Resolved tab
	 */
	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/thead/tr/th[6]")
	public static WebElement columnHeaderLastUpdate_Resolved;

	@FindBy(xpath = "//*[@id=\"datatable_archived_all\"]/tbody/tr/td[6]")
	public static List<WebElement> columnLastUpdate_Resolved;

	public boolean sortByColumnLastUpdate_Resolved() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderLastUpdate_Resolved, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderLastUpdate_Resolved, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isDateElementsAscendingOrdered(columnLastUpdate_Resolved);
		return true;
	}


	/**
	 * Check open service request from Resolved tab
	 */
	@FindBy(xpath = "//*[@id='datatable_archived_all']/tbody/tr/td[5]/a")
	public static List<WebElement> linkOfColumnStatus_Resolved;
	
	public boolean openServiceRequestFromResolvedTab() {
		return Function.hoverNclickElement(driver, linkOfColumnStatus_Resolved.get(0));
	}

}
