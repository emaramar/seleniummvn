package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.TaskPage;

public class TaskPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(TaskPageTest.class.getName());
	protected BasePage basePage = null;
	protected TaskPage taskPage = null;

	/**
	 * Test of navigating to Task Page
	 */
	@Test(priority = 1)
	public void gotoTaskPage() {
		test = extent.startTest("Navigate to Task Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		taskPage = basePage.gotoTaskPage();
		Assert.assertNotNull(taskPage);
	}

	/**
	 * Test of verify is “Name” field mandatory
	 */
	@Test(priority = 3)
	public void isNameFieldMandatory() {
		test = extent.startTest("Verify is 'Name' field mandatory");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterResponsibleUser())
				if (taskPage.clickSaveTaskBtn())
					if (taskPage.isErrorMsgPresent())
						Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify is “Responsible User” field mandatory
	 */
	@Test(priority = 5)
	public void isResponsibleUserFieldMandatory() {
		test = extent.startTest("Verify is 'Responsible User' field mandatory");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterTaskName_1())
				if (taskPage.clickSaveTaskBtn())
					if (taskPage.isErrorMsgPresent())
						Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify enter task name
	 */
	@Test(priority = 7)
	public void enterTaskName() {
		test = extent.startTest("Verify enter task name");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterTaskName_1())
				Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify task description
	 */
	@Test(priority = 9)
	public void enterTaskDescription() {
		test = extent.startTest("Verify enter task description");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterTaskDesc())
				Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify set task priority
	 */
	@Test(priority = 11)
	public void setTaskPriority() {
		test = extent.startTest("Verify set task priority");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.setTaskPriorityOfHigh())
				Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify set task category
	 */
	@Test(priority = 13)
	public void setTaskCategory() {
		test = extent.startTest("Verify set task category");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.setTaskCategory())
				Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify set task responsible user
	 */
	@Test(priority = 15)
	public void setTaskResponsibleUser() {
		test = extent.startTest("Verify set task responsible user");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterResponsibleUser())
				Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify assign due date on the task
	 */
	@Test(priority = 17)
	public void assignDueDateOnTask() {
		test = extent.startTest("Verify assign due date on the task");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.setDueDateOnTask())
				Assert.assertTrue(taskPage.clickCancelBtn());
	}

	/**
	 * Test of verify user PM create a new task with Due Date
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void userPMCreateTaskWithDueDate() throws Exception {
		test = extent.startTest("Verify user PM create a new task with Due Date");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterTaskName_2())
				if (taskPage.enterTaskDesc())
					if (taskPage.setTaskCategory())
						if (taskPage.enterResponsibleUser())
							if (taskPage.setDueDateOnTask())
								if (taskPage.uploadFile())
									if (taskPage.clickSaveTaskBtn())
										Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify user PM create a new task without Due Date
	 */
	@Test(priority = 21)
	public void userPMCreateTaskWithoutDueDate() {
		test = extent.startTest("Verify user PM create a new task w/o Due Date");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterTaskName_4())
				if (taskPage.enterTaskDesc())
					if (taskPage.enterResponsibleUser())
						if (taskPage.clickSaveTaskBtn())
							Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify user PM create a new task with high priority
	 */
	@Test(priority = 23)
	public void userPMCreateTaskWithHighPriority() {
		test = extent.startTest("Verify user PM create a new task with high priority");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterTaskName_3())
				if (taskPage.enterTaskDesc())
					if (taskPage.setTaskPriorityOfHigh())
						if (taskPage.setTaskCategory())
							if (taskPage.enterResponsibleUser())
								if (taskPage.setDueDateOnTask())
									if (taskPage.clickSaveTaskBtn())
										Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify user PM create a new task with attachment
	 */
	@Test(priority = 25)
	public void userPMCreateTaskWithAttachment() {
		test = extent.startTest("Verify user PM create a new task with attachment");
		if (taskPage.clickCreateTaskBtn())
			if (taskPage.enterTaskName_1())
				if (taskPage.enterTaskDesc())
					if (taskPage.setTaskPriorityOfLow())
						if (taskPage.setTaskCategory())
							if (taskPage.enterResponsibleUser())
								if (taskPage.clickSelectImageFromMediaLibraryBtn())
									if (taskPage.selectImage())
										if (taskPage.clickSaveTaskBtn())
											Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify view task details
	 */
	@Test(priority = 27)
	public void viewTaskDetails() {
		test = extent.startTest("Verify view task details");
		if (taskPage.clickOpenTask())
			if (taskPage.viewTaskDetails())
				Assert.assertTrue(taskPage.clickCancelBtnFromViewTaskPage());
	}

	/**
	 * Test of verify edit task
	 */
	@Test(priority = 29)
	public void editTask() {
		test = extent.startTest("Verify edit task");
		if (taskPage.clickEditIcon())
			if (taskPage.modifyTaskDesc())
				if (taskPage.clickSaveTaskBtn())
					Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of update task status to “IN PROGRESS”
	 */
	@Test(priority = 31)
	public void updateTaskStatusToInProgress() {
		test = extent.startTest("Verify update task status to 'IN PROGRESS'");
		if (taskPage.clickOpenTask())
			if (taskPage.clickStatus())
				if (taskPage.updateStatusToInProgress())
					if (taskPage.addCommentOfInProgress())
						if (taskPage.clickSaveBtnOnViewTaskPage())
							Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of update task status to “ON HOLD ”
	 */
	@Test(priority = 33)
	public void updateTaskStatusToOnHold() {
		test = extent.startTest("Verify update task status to 'ON HOLD'");
		if (taskPage.clickOpenTask())
			if (taskPage.clickStatus())
				if (taskPage.updateStatusToOnHold())
					if (taskPage.addCommentOfOnHold())
						if (taskPage.clickSaveBtnOnViewTaskPage())
							Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of update task status to “CANCELED ”
	 */
	@Test(priority = 35)
	public void updateTaskStatusToCanceled() {
		test = extent.startTest("Verify update task status to 'CANCELED'");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (taskPage.clickOpenTask())
			if (taskPage.clickStatus())
				if (taskPage.updateStatusToCanceled())
					if (taskPage.addCommentOfCanceled())
						if (taskPage.clickSaveBtnOnViewTaskPage())
							Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of update task status to “DONE ”
	 */
	@Test(priority = 37)
	public void updateTaskStatusToDone() {
		test = extent.startTest("Verify update task status to 'DONE'");
		if (taskPage.clickOpenTask())
			if (taskPage.clickStatus())
				if (taskPage.updateStatusToDone())
					if (taskPage.addCommentOfDone())
						if (taskPage.clickSaveBtnOnViewTaskPage())
							Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of delete task
	 */
	@Test(priority = 39)
	public void deleteTask() {
		test = extent.startTest("Verify delete task");
		if (taskPage.clickDeleteIcon())
			if (taskPage.clickConfirmDeleteBtn())
				Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

	/**
	 * Test of search task from CLOSED tab
	 */
	@Test(priority = 41)
	public void searchTaskFromClosedTab() {
		test = extent.startTest("Verify search task from CLOSED tab");
		if (taskPage.gotoClosedTab())
			if (taskPage.searchTaskFromClosedTab())
				Assert.assertTrue(taskPage.gotoOpenTab());
	}

	/**
	 * Test of verify view task details from CLOSED tab
	 */
	@Test(priority = 43)
	public void viewTaskDetailsFromClosedTab() {
		test = extent.startTest("Verify view task details from CLOSED tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (taskPage.gotoClosedTab())
			if (taskPage.clickClosedTask())
				if (taskPage.viewTaskDetails())
					if (taskPage.viewTaskLastStatus())
						Assert.assertTrue(taskPage.clickCancelBtnFromViewTaskPage());
	}

	/**
	 * Test of verify view task last update time from CLOSED tab
	 */
	@Test(priority = 45)
	public void viewTaskLastUpdateTimeFromClosedTab() {
		test = extent.startTest("Verify view task last update time from CLOSED tab");
		if (taskPage.gotoClosedTab())
			if (taskPage.clickClosedTask())
				if (taskPage.viewTaskLastUpdateTime())
					Assert.assertTrue(taskPage.clickCancelBtnFromViewTaskPage());
	}

	/**
	 * Test of verify Resident user log in application and goto Task menu
	 */
	@Test(priority = 47)
	public void residentUserGotoTaskMenu() {
		test = extent.startTest("Verify Resident user log in application and goto Task menu");
		taskPage.logOutAsCurrentUser(driver);
		taskPage.logInAsResidentUser(driver);
		Assert.assertTrue(taskPage.gotoTaskMenu());
	}

	/**
	 * Test of Resident user view task details
	 */
	@Test(priority = 49)
	public void residentUserViewTaskDetails() {
		test = extent.startTest("Verify Resident user view task details");
		if (taskPage.clickOpenTask())
			if (taskPage.viewTaskDetails())
				Assert.assertTrue(taskPage.clickCancelBtnFromViewTaskPage());
	}

	/**
	 * Test of verify Resident user set reminder for the task
	 */
	@Test(priority = 51)
	public void residentUserSetReminderForTheTask() {
		test = extent.startTest("Verify Resident user set reminder for the task");
		if (taskPage.clickOpenTask())
			if (taskPage.clickRemindMeAboutThisTaskBtn())
				if (taskPage.setRemindMeDateAndTime())
					if (taskPage.clickSaveReminderBtn())
						if (taskPage.isReminderSetSuccessfully())
							Assert.assertTrue(taskPage.clickCancelBtnFromViewTaskPage());
	}
	
	
	/**
	 * Test of verify Resident user can delete reminder for the task
	 */
	@Test(priority = 53)
	public void residentUserDeleteReminderForTheTask() {
		test = extent.startTest("Verify Resident user can delete reminder for the task");
		if (taskPage.clickOpenTask())
			if (taskPage.clickDeleteReminderIcon())
				if (taskPage.clickConfirmDeleteReminderBtn())
					Assert.assertTrue(taskPage.clickCancelBtnFromViewTaskPage());
	}
	

	/**
	 * Test of Resident User update task status to “DONE ”
	 */
	@Test(priority = 55)
	public void residentUserUpdateTaskStatusToDone() {
		test = extent.startTest("Verify Resident user update task status to 'DONE'");
		if (taskPage.clickOpenTask())
			if (taskPage.clickStatus())
				if (taskPage.updateStatusToDone())
					if (taskPage.addCommentOfDone())
						if (taskPage.clickSaveBtnOnViewTaskPage())
							Assert.assertTrue(taskPage.isSuccessMsgPresent());
	}

}
