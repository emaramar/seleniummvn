package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;

public class TaskPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(TaskPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */

	public TaskPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Task page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoTaskPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createTaskBtn.isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Check is "Name" field mandatory
	 */
	@FindBy(xpath = "//*[@href='/tsk/create/']/span[2]")
	public static WebElement createTaskBtn;

	@FindBy(name = "user-search")
	public static WebElement userSearchBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement selectChoice;

	@FindBy(xpath = "//*[@id='save-tsk']/span[2]")
	public static WebElement saveTaskBtn;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsg; // index 0

	@FindBy(xpath = "//*[@id='cancel--tsk']/span[2]")
	public static WebElement cancelBtn;

	public boolean clickCreateTaskBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createTaskBtn, 30);
		if (createTaskBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createTaskBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean enterResponsibleUser() {
		Function.clickElement(driver, userSearchBox);
		SeleniumWrapper.setInputFieldText(userSearchBox, "Daniels", driver);
		SeleniumWrapper.clickElement(driver, selectChoice, Constants.CLICK_METHOD_ENUM.CLICK);
		String responsibleUser = userSearchBox.getAttribute("value");
		logger.info("Responsible User is:" + responsibleUser);
		if (!responsibleUser.isEmpty())
			return true;
		else
			return false;
	}

	public boolean clickSaveTaskBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveTaskBtn, 30);
		if (saveTaskBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveTaskBtn, Constants.CLICK_METHOD_ENUM.CLICK);
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
	 * Check enter task name
	 */
	@FindBy(name = "name")
	public static WebElement nameInputBox;

	String taskName_1 = "Replace all in-suite smoke detectors";
	String taskName_2 = "Renewal of medical cannibis prescription required";
	String taskName_3 = "Sliding door reparis";
	String taskName_4 = "Gym equipments maintenance";

	public boolean enterTaskName_1() {
		SeleniumWrapper.explicitWaitClickable(driver, nameInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, nameInputBox, Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(nameInputBox, taskName_1, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String taskName = nameInputBox.getAttribute("value");
		logger.info("Task name is: " + taskName);
		if (!taskName.isEmpty())
			return true;
		else
			return false;
	}

	public boolean enterTaskName_2() {
		SeleniumWrapper.explicitWaitClickable(driver, nameInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, nameInputBox, Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(nameInputBox, taskName_2, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String taskName = nameInputBox.getAttribute("value");
		logger.info("Task name is: " + taskName);
		if (!taskName.isEmpty())
			return true;
		else
			return false;
	}

	public boolean enterTaskName_3() {
		SeleniumWrapper.explicitWaitClickable(driver, nameInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, nameInputBox, Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(nameInputBox, taskName_3, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String taskName = nameInputBox.getAttribute("value");
		logger.info("Task name is: " + taskName);
		if (!taskName.isEmpty())
			return true;
		else
			return false;
	}

	public boolean enterTaskName_4() {
		SeleniumWrapper.explicitWaitClickable(driver, nameInputBox, 30);
		if (SeleniumWrapper.clickElement(driver, nameInputBox, Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(nameInputBox, taskName_4, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String taskName = nameInputBox.getAttribute("value");
		logger.info("Task name is: " + taskName);
		if (!taskName.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check enter task description
	 */
	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[2]/div[2]")
	public static WebElement descriptionBox;

	@FindBy(id = "description")
	public static List<WebElement> textAreaBox;

	public boolean enterTaskDesc() {
		Function.clickElement(driver, descriptionBox);
		SeleniumWrapper.explicitWaitClickable(driver, textAreaBox.get(0), 50);
		if (SeleniumWrapper.clickElement(driver, textAreaBox.get(0), Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(textAreaBox.get(0), "This is a new task.", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String taskDesc = textAreaBox.get(0).getAttribute("value");
		logger.info("Task description is: " + taskDesc);
		if (!taskDesc.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check enter task Priority
	 */
	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[3]/div[2]/div/button/div/div/div")
	public static WebElement priorityBox;

	@FindBy(xpath = "//*[@id='bs-select-1']/ul/li/a/span")
	public static List<WebElement> choicesFromDropdown;

	public boolean setTaskPriorityOfHigh() {
		Function.clickElement(driver, priorityBox);
		Function.hoverNclickElement(driver, choicesFromDropdown.get(0));
		String priority = priorityBox.getText();
		logger.info("Selected priority is:" + priority);
		if (priority.contains("High"))
			return true;
		else
			return false;
	}

	public boolean setTaskPriorityOfLow() {
		Function.clickElement(driver, priorityBox);
		Function.hoverNclickElement(driver, choicesFromDropdown.get(2));
		String priority = priorityBox.getText();
		logger.info("Selected priority is:" + priority);
		if (priority.contains("Low"))
			return true;
		else
			return false;
	}

	/**
	 * Check enter task Category
	 */
	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[4]/div[2]/div/button/div")
	public static WebElement categoryBox;

	@FindBy(xpath = "//*[@id='bs-select-2']/ul/li/a/span")
	public static List<WebElement> choicesOfCategory;

	public boolean setTaskCategory() {
		Function.clickElement(driver, categoryBox);
		Function.hoverNclickElement(driver, choicesOfCategory.get(2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String category = categoryBox.getText();
		logger.info("Selected category is:" + category);
		if (!category.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check assign due date on the task
	 */
	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> choicesOfAssignDueDate;

	@FindBy(xpath = "//*[@id='datepicker_dt_end']/span/span")
	public static WebElement calendarIcon;

	@FindBy(name = "dt_end_date")
	public static WebElement dateBox;

	@FindBy(name = "dt_end_time")
	public static WebElement timeBox;

	public boolean setDueDateOnTask() {
		Function.clickElement(driver, choicesOfAssignDueDate.get(0));
		Function.clickElement(driver, calendarIcon);
		Function.setDatefromCalendar(driver, 3);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.clickElement(driver, timeBox);
		SeleniumWrapper.setInputFieldText(timeBox, "12:00 PM", driver);
		String dueDate = dateBox.getAttribute("value");
		String dueTime = timeBox.getAttribute("value");
		logger.info("Due date and time is:" + dueDate + " " + "@" + dueTime);
		if (dueTime.contains("12:00 PM"))
			return true;
		else
			return false;
	}

	/**
	 * Check upload attachment on the task from media library
	 */
	@FindBy(xpath = "//*[@id=\"picker\"]/span[2]")
	public static WebElement selectFromMediaLibraryBtn;

	@FindBy(xpath = "//*[contains(text(),'Test Folder')]")
	public static WebElement selectFolder;

	@FindBy(xpath = "//*[@class ='list']/li/div")
	public static List<WebElement> listOfImages;

	@FindBy(xpath = "//*[@id='attachments_file_container']/li/div[1]")
	public static WebElement attachmentIcon;

	public boolean clickSelectImageFromMediaLibraryBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, selectFromMediaLibraryBtn, 20);
		if (selectFromMediaLibraryBtn.isEnabled())
			return Function.clickElement(driver, selectFromMediaLibraryBtn);
		else
			return false;
	}

	public boolean selectImage() {
		Function.hoverNclickElement(driver, selectFolder);
		Function.hoverNclickElement(driver, listOfImages.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if (attachmentIcon != null)
			return true;
		else
			return false;
	}

	/**
	 * Check upload attachment on the task by upload files
	 */
	@FindBy(xpath = "//*[@id='attachments_select_file']/span[2]")
	public static WebElement uploadFileBtn;

	public boolean uploadFile() throws Exception {
		// upload condo imge
		Function.uploadFile(driver, uploadFileBtn, Constants.imgOfCondo);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return true;
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
		if (confirmMsg.isDisplayed() && !message.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check view task details
	 */
	@FindBy(xpath = "//*[@id='datatable_open']/tbody/tr/td[2]/a")
	public static List<WebElement> openTaskNameList;
	
	@FindBy(xpath = "//*[@id='view-container-tsk']/div[1]/div[1]/div/div[1]/span[1]")
	public static WebElement taskID;

	@FindBy(xpath = "//*[@id='view-container-tsk']/div[1]/div[1]/div/div[1]/span[2]")
	public static WebElement taskIDValue;

	@FindBy(xpath = "//*[@id='view-container-tsk']/div[1]/div[1]/div/div[2]/span[2]")
	public static WebElement taskName;

	@FindBy(xpath = "//*[@id='request_worklog_buttons']/div[2]/a/span[2]")
	public static WebElement cancelBtnFromViewTaskPage;

	public boolean clickOpenTask() {
		return Function.clickElement(driver, openTaskNameList.get(0));
	}

	public boolean viewTaskDetails() {
		SeleniumWrapper.explicitWaitClickable(driver, taskID, 30);
		String id = taskID.getText();
		int id_Value= Integer.parseInt(taskIDValue.getText());
		logger.info("column:" + id + ":" + id_Value);
		SeleniumWrapper.explicitWaitClickable(driver, taskName, 30);
		String name = taskName.getText();
		logger.info("Task name:" + name);
		if (id.contains("Task ID"))
			return true;
		else
			return false;
	}

	public boolean clickCancelBtnFromViewTaskPage() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtnFromViewTaskPage, 30);
		if (cancelBtnFromViewTaskPage.isEnabled()) {
			SeleniumWrapper.clickElement(driver, cancelBtnFromViewTaskPage, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check edit task
	 */
	@FindBy(xpath = "//*[@id='datatable_open']/tbody/tr/td[8]")
	public static List<WebElement> editIcon;

	public boolean clickEditIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, editIcon.get(0), 30);
		if (editIcon.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, editIcon.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean modifyTaskDesc() {
		Function.clickElement(driver, descriptionBox);
		SeleniumWrapper.explicitWaitClickable(driver, textAreaBox.get(0), 30);
		logger.info("Original Task description is: " + textAreaBox.get(0).getAttribute("value"));

		if (SeleniumWrapper.clickElement(driver, textAreaBox.get(0), Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.setInputFieldText(textAreaBox.get(0),
					"This is a new task." + "  " + "Update:We need to work on it quicker!", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String taskDesc = textAreaBox.get(0).getAttribute("value");
		logger.info("Modify Task description is: " + taskDesc);
		if (taskDesc.contains("Update"))
			return true;
		else
			return false;
	}

	/**
	 * Check update task status to “IN PROGRESS”
	 */
	@FindBy(xpath = "//*[@id='request_worklog']/form/div[1]/div/div[1]/div/button/div")
	public static WebElement statusBox;

	@FindBy(xpath = "//div[contains(@class, 'fr-wrapper')]")
	public static WebElement commentBox;

	@FindBy(xpath = "//div[contains(@class, 'fr-element fr-view')]")
	public static WebElement commentBoxEditable;

	@FindBy(xpath = "//*[@id='save-work-log-button']/span[2]")
	public static WebElement saveBtnOnViewTaskPage;

	public boolean clickStatus() {
		Function.clickElement(driver, statusBox);
		logger.info("Current status:" + statusBox.getText());
		return true;
	}

	public boolean updateStatusToInProgress() {
		Function.clickElement(driver, choicesFromDropdown.get(2));
		String updatedStatus = statusBox.getText();
		logger.info("Current status:" + updatedStatus);
		if (updatedStatus.equalsIgnoreCase("In Progress"))
			return true;
		else
			return false;
	}

	public boolean addCommentOfInProgress() {
		Function.clickElement(driver, commentBox);
		Function.clickElement(driver, commentBoxEditable);
		SeleniumWrapper.setInputFieldText(commentBoxEditable, "Workingonit", driver);
		String comment = commentBoxEditable.getText();
		logger.info("Comment is:" + comment);
		if (!comment.isEmpty())
			return true;
		else
			return false;
	}

	public boolean clickSaveBtnOnViewTaskPage() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtnOnViewTaskPage, 30);
		if (saveBtnOnViewTaskPage.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtnOnViewTaskPage, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check update task status to “ON HOLD”
	 */
	public boolean updateStatusToOnHold() {
		Function.clickElement(driver, choicesFromDropdown.get(3));
		String updatedStatus = statusBox.getText();
		logger.info("Current status:" + updatedStatus);
		if (updatedStatus.equalsIgnoreCase("On Hold"))
			return true;
		else
			return false;
	}

	public boolean addCommentOfOnHold() {
		Function.clickElement(driver, commentBox);
		Function.clickElement(driver, commentBoxEditable);
		SeleniumWrapper.setInputFieldText(commentBoxEditable, "On Hold", driver);
		String comment = commentBoxEditable.getText();
		logger.info("Comment is:" + comment);
		if (!comment.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check update task status to “CANCELED”
	 */
	public boolean updateStatusToCanceled() {
		Function.clickElement(driver, choicesFromDropdown.get(5));
		String updatedStatus = statusBox.getText();
		logger.info("Current status:" + updatedStatus);
		if (updatedStatus.equalsIgnoreCase("Canceled"))
			return true;
		else
			return false;
	}

	public boolean addCommentOfCanceled() {
		Function.clickElement(driver, commentBox);
		Function.clickElement(driver, commentBoxEditable);
		SeleniumWrapper.setInputFieldText(commentBoxEditable, "Canceled", driver);
		String comment = commentBoxEditable.getText();
		logger.info("Comment is:" + comment);
		if (!comment.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check update task status to “DONE”
	 */
	public boolean updateStatusToDone() {
		Function.clickElement(driver, choicesFromDropdown.get(4));
		String updatedStatus = statusBox.getText();
		logger.info("Current status:" + updatedStatus);
		if (updatedStatus.equalsIgnoreCase("Done"))
			return true;
		else
			return false;
	}

	public boolean addCommentOfDone() {
		Function.clickElement(driver, commentBox);
		Function.clickElement(driver, commentBoxEditable);
		SeleniumWrapper.setInputFieldText(commentBoxEditable, "Done", driver);
		String comment = commentBoxEditable.getText();
		logger.info("Comment is:" + comment);
		if (!comment.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check delete task
	 */
	@FindBy(xpath = "//*[@id='datatable_open']/tbody/tr/td[9]")
	public static List<WebElement> deleteIcon;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean clickDeleteIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, deleteIcon.get(0), 30);
		if (deleteIcon.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteIcon.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
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
	 * Check goto CLOSED tab
	 */
	@FindBy(xpath = "//*[@href='#tabs-1']")
	public static WebElement closedTab;

	public boolean gotoClosedTab() {
		SeleniumWrapper.explicitWaitClickable(driver, closedTab, 30);
		if (closedTab.isEnabled()) {
			SeleniumWrapper.clickElement(driver, closedTab, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check goto OPEN tab
	 */
	@FindBy(xpath = "//*[@href='#tabs-0']")
	public static WebElement openTab;

	public boolean gotoOpenTab() {
		SeleniumWrapper.explicitWaitClickable(driver, openTab, 30);
		if (openTab.isEnabled()) {
			SeleniumWrapper.clickElement(driver, openTab, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check search task from CLOSED tab
	 */
	@FindBy(id = "datatable_closed_search-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath_Closed = "//*[@id='datatable_closed']/tbody/tr";
	@FindBy(xpath = tableRowsXpath_Closed)
	public static List<WebElement> tasksTableRowByClosedTab;

	public boolean searchTaskFromClosedTab() {
		String searchKeyword = "door";
		return Function.search(driver, searchBox, searchKeyword, tableRowsXpath_Closed);
	}

	/**
	 * Check search task from CLOSED tab
	 */
	//@FindBy(xpath = "//*[@id='datatable_closed']/tbody/tr/td[2]/a")
	@FindBy(xpath = "//*[@id='datatable_closed']/tbody/tr/td[2]/a[starts-with(@href, '/tsk/35')]")
	public static List<WebElement> closedTaskNameList;

	public boolean clickClosedTask() {
		return Function.clickElement(driver, closedTaskNameList.get(0));
	}

	/**
	 * Check view task from CLOSED tab
	 */
	@FindBy(xpath = "//*[@id='worklog_updates']/div/div[2]/dl/dt/span")
	public static List<WebElement> closedTaskStatus;

	@FindBy(xpath = "//*[@id='worklog_updates']/div/div[1]/span[2]")
	public static List<WebElement> lastUpdateTime;

	public boolean viewTaskLastStatus() {
		SeleniumWrapper.explicitWaitClickable(driver, closedTaskStatus.get(closedTaskStatus.size() - 1), 30);
		String status = closedTaskStatus.get(closedTaskStatus.size() - 1).getText();
		logger.info("Last status of the Task:" + status);
		if (status.contains("CANCELED") || status.contains("DELETE") || status.contains("DONE"))
			return true;
		else
			return false;
	}

	public boolean viewTaskLastUpdateTime() {
		SeleniumWrapper.explicitWaitClickable(driver, lastUpdateTime.get(lastUpdateTime.size() - 1), 30);
		String updateTime = lastUpdateTime.get(lastUpdateTime.size() - 1).getText();
		logger.info("Last update time is:" + updateTime);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		LocalDateTime now = LocalDateTime.now();
		String currDate = dtf.format(now);
		logger.info("Today is: " + currDate);

		if (updateTime.contains(currDate))
			return true;
		else
			return false;
	}

	/**
	 * Check Resident user goto Task menu
	 */
	@FindBy(xpath = "//*[@href='/tsk']/span[2]")
	public static WebElement taskMenu;

	public boolean gotoTaskMenu() {
		SeleniumWrapper.explicitWaitClickable(driver, taskMenu, 30);
		if (taskMenu.isEnabled()) {
			SeleniumWrapper.clickElement(driver, taskMenu, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check Resident user goto Task menu
	 */
	@FindBy(xpath = " //*[@class='task_reminder_create_link_copy']")
	public static WebElement remindMeAboutThisTaskBtn;

	@FindBy(xpath = "//*[@id='datepicker_reminder']/span/span")
	public static WebElement calendarIconOnCreateReminderPage;

	@FindBy(id = "reminder_date")
	public static WebElement remindMeDateBox;

	@FindBy(id = "reminder_time")
	public static WebElement remindMeTimeBox;

	@FindBy(id = "modal-save-button")
	public static WebElement saveBtnOnCreateReminderPage;

	public boolean clickRemindMeAboutThisTaskBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, remindMeAboutThisTaskBtn, 30);
		if (remindMeAboutThisTaskBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, remindMeAboutThisTaskBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean setRemindMeDateAndTime() {
		Function.clickElement(driver, calendarIconOnCreateReminderPage);
		Function.setDatefromCalendar(driver, 1);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.clickElement(driver, remindMeTimeBox);
		SeleniumWrapper.setInputFieldText(remindMeTimeBox, "12:00 PM", driver);
		String remindMeDate = remindMeDateBox.getAttribute("value");
		String remindMeTime = remindMeTimeBox.getAttribute("value");
		logger.info("Reminder me date and time:" + remindMeDate + " " + "@" + remindMeTime);
		if (remindMeTime.contains("12:00 PM"))
			return true;
		else
			return false;
	}

	public boolean clickSaveReminderBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtnOnCreateReminderPage, 30);
		if (saveBtnOnCreateReminderPage.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtnOnCreateReminderPage, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check is reminder set successfully
	 */
	@FindBy(xpath = "//*[@class='task_reminder_copy']")
	public static WebElement reminder;

	public boolean isReminderSetSuccessfully() {
		SeleniumWrapper.explicitWaitClickable(driver, reminder, 30);
		String reminderRecord = reminder.getText();
		logger.info("reminder copy:" + reminderRecord);
		if (reminder != null)
			return true;
		else
			return false;
	}

	/**
	 * Check Resident user delete reminder
	 */
	@FindBy(xpath = "//*[@class='task_reminder_delete']/span")
	public static WebElement deleteReminderIcon;

	@FindBy(id = "send")
	public static WebElement confirmDeleteReminderBtn;

	public boolean clickDeleteReminderIcon() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteReminderIcon, 30);
		if (deleteReminderIcon.isDisplayed() && deleteReminderIcon.isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteReminderIcon, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean clickConfirmDeleteReminderBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteReminderBtn, 30);
		if (confirmDeleteReminderBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteReminderBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}
}
