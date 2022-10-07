package pages.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import com.utilities.TestResultValidator;
import config.Constants;
import pages.BasePage;

public class AnnouncementsPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(AnnouncementsPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param password login password
	 */
	public AnnouncementsPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info(" Announcements Page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoAnnouncementsPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (listTab.isEnabled())
			return true;
		return false;
	}

	/**
	 * Check create announcements
	 */

	/**
	 * Check create announcements with adding the event in Community Schedule
	 * Publish the announcement NOW
	 */
	@FindBy(xpath = "//*[contains(text(),'Create Announcement')]")
	public static List<WebElement> createAnnouncementBtn;

	@FindBy(xpath = "//*[contains(text(),'Add group')]")
	public static List<WebElement> addGroupBtn;

	@FindBy(xpath = "//div[@class='bs-searchbox']/input")
	public static List<WebElement> searchBoxOfAddGroup; // get index 2

	@FindBy(xpath = "//*[contains(text(), 'Owners')]")
	public static List<WebElement> choiceOfGroup_Owners; // get index 19

	@FindBy(xpath = "//*[@class= 'btn-actual-checkbox']")
	public static List<WebElement> checkboxOfCreateAnEventInTheCommunitySchedule;

	@FindBy(xpath = "//button[contains(., 'Save Draft')]")
	public static WebElement saveDraftBtn;
	
	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td")
	public static List<WebElement> datePicker;

	public boolean clickCreateAnnouncementBtn() {
		Function.clickElement(driver, createAnnouncementBtn.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean clickAddGroupBtn() {
		return Function.clickElement(driver, addGroupBtn.get(1));
	}

	public boolean inputSearchGroupBox() {
		Function.clickElement(driver, searchBoxOfAddGroup.get(2));
		return SeleniumWrapper.setInputFieldText(searchBoxOfAddGroup.get(2), "Owners", driver);

	}

	public boolean chooseGroup() {
		return Function.hoverNclickElement(driver, choiceOfGroup_Owners.get(19));
	}

	public static String randomSuject = SeleniumWrapper.generateRandomSubjectForAnnouncement();
	public static int randomIndex = SeleniumWrapper.generateRandomInteger(100);
	public static String subject = randomSuject + "#" + randomIndex;
	public static String msgContent = "message";

	public boolean inputSubjectOfAnnouncement() {
		Function.hoverNclickElement(driver, inputSubjectBox.get(0));
		SeleniumWrapper.setInputFieldText(inputSubjectBox.get(0), subject, driver);
		logger.info("The subject is: " + subject);
		return true;
	}

	public boolean clickTextBox1() {
		Function.hoverNclickElement(driver, msgBox1);
		return Function.hoverNclickElement(driver, ContentEditable);
	}

	public boolean clickTextBox2() {
		Function.hoverNclickElement(driver, msgBox2);
		return Function.hoverNclickElement(driver, ContentEditable);
	}

	public boolean enterMessage() {
		SeleniumWrapper.setInputFieldText(ContentEditable, msgContent, driver);
		logger.info("The message is: " + ContentEditable.getText());
		return true;
	}

	public boolean clickCheckboxCreateEventInTheCommunitySchedule() {
		return Function.hoverNclickElement(driver, checkboxOfCreateAnEventInTheCommunitySchedule.get(0));
	}

	public boolean pickEventDate() {
		Function.clickElement(driver, calendarIcon.get(0));
		return Function.setDatefromCalendar(driver, 5);
		//return Function.hoverNclickElement(driver, datePicker.get(34));
	}

	/**
	 * Check create announcements with attach photo Save the announcement as DRAFT
	 */
	@FindBy(xpath = "//*[@class='icon-plus']")
	public static List<WebElement> selectImageBtn;

	@FindBy(xpath = "//*[contains(text(),'Test Folder')]")
	public static WebElement selectFolder;

	@FindBy(xpath = "//*[@class ='list']/li/div")
	public static List<WebElement> listOfImages;

	@FindBy(xpath = "//*[@class='attachments_item_container']")
	public static List<WebElement> attachmentFromPreview;

	@FindBy(xpath = "//button[contains(., 'Close')]")
	public static List<WebElement> closeBtn;

	public boolean clickSelectImageBtn() {
		Function.clickElement(driver, selectImageBtn.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean selectFolder() {
		return Function.hoverNclickElement(driver, selectFolder);
	}

	public boolean selectImage() {
		Function.hoverNclickElement(driver, listOfImages.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean previewAnnouncement() {
		Function.clickElement(driver, previewBtn);
		if (SeleniumWrapper.clickElement(driver, attachmentFromPreview.get(1), Constants.CLICK_METHOD_ENUM.CLICK)) {
			String nameOfImg = attachmentFromPreview.get(1).getText();
			logger.info("The name of attached image is : " + nameOfImg);
			Assert.assertTrue(!nameOfImg.isEmpty());
		}
		return true;
	}

	public boolean clickCloseBtn() {
		Function.hoverNclickElement(driver, closeBtn.get(1));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean clickSaveDraftBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveDraftBtn, 30);
		if (saveDraftBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveDraftBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check create emergency announcement
	 */

	/**
	 * Go to EMERGENCY tab
	 */
	@FindBy(xpath = "//a[contains(text(),'Emergency')]")
	public static WebElement emergencyTab;

	@FindBy(xpath = "//*[contains(text(),'Tenants_New')]")
	public static List<WebElement> choiceOfGroup_Tenant_New; // get index 16

	@FindBy(name = "content_emergency")
	public static WebElement messageBox;

	@FindBy(xpath = "//button[contains(., 'Publish & Send Notifications')]")
	public static WebElement publishAndSendNotificationsBtn;
	
	@FindBy(xpath = "//*[@id=\"groups_selector_form_element_groups_emergency\"]/div[1]/div/div/div[2]/div[2]/div[2]/a/span")
	public static List<WebElement> deleteIconFromCreatePage;

	@FindBy(id = "modal-save-button")
	public static WebElement sendBtn;
	
	@FindBy(id = "send")
	public static WebElement sendBtnOnEmailUrgentAnnouncement;

	public boolean goToEmergencyTab() {
		return Function.clickElement(driver, emergencyTab);
	}
	
	public boolean deleteGroupOfResidents() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteIconFromCreatePage.get(0), 30);
		if(deleteIconFromCreatePage.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteIconFromCreatePage.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	

	public boolean clickBtnOfAddGroup() {
		return Function.clickElement(driver, addGroupBtn.get(4));
	}

	public boolean searchTenantNew() {
		Function.clickElement(driver, searchBoxOfAddGroup.get(4));
		return SeleniumWrapper.setInputFieldText(searchBoxOfAddGroup.get(4), "Tenants_New", driver);

	}

	public boolean chooseTenantNewGroup() {
		return Function.hoverNclickElement(driver, choiceOfGroup_Tenant_New.get(13));
	}

	public boolean inputMsgContent() {
		String message = "Power off";
		Function.hoverNclickElement(driver, messageBox);
		if (SeleniumWrapper.clickElement(driver, messageBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(messageBox, message, driver);
			logger.info("The message content is:" + messageBox.getAttribute("value"));
		}
		return true;

	}

	public boolean clickPublishAndSendNotificationBtn() {
		Function.hoverNclickElement(driver, publishAndSendNotificationsBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean clickSendBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, sendBtn, 30);
		if (sendBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, sendBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			return true;
		}
		return false;
	}
	
	
	public boolean clickSendBtnOnUrgencyAnnouncement() {
		SeleniumWrapper.explicitWaitClickable(driver,  sendBtnOnEmailUrgentAnnouncement, 30);
		if ( sendBtnOnEmailUrgentAnnouncement.isEnabled()) {
			SeleniumWrapper.clickElement(driver,  sendBtnOnEmailUrgentAnnouncement, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			return true;
		}
		return false;
	}

	// ***************************************************************************************************

	/**
	 * Check search announcement from View tab
	 */
	@FindBy(id = "search-all-announcements-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath = "//*[@id ='announcements-isotope-container']/div/a ";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> annoncementsTableRowByViewTab;

	public boolean searchAnnouncementFromViewTab() {
		String searchKeyword = "Gym";
		return Function.search(driver, searchBox, searchKeyword, tableRowsXpath);
	}

	/**
	 * Get and verify announcement by status from VIEW tab
	 */

	@FindBy(xpath = "//div[contains(@class,'match')]/a")
	public static List<WebElement> allLinkElements;
	// div[contains(@class,'match')]/a   // with "Not yet published"
	// div[contains(@class,'match')]/a/span // without "Not yet published"

	@FindBy(xpath = "//div[contains(@class,'match')]/span")
	public static List<WebElement> allStatusElements;

	public WebElement annoncementsTableRowByStatusPublished() {
		for (int i = 0; i < allStatusElements.size(); i++)
			if (allStatusElements.get(i).getText().toLowerCase().contains("published"))
				return allLinkElements.get(i);
		return null;
	}

	public WebElement annoncementsTableRowByStatusDraft() {
		for (int i = 0; i < allStatusElements.size(); i++)
			if (allStatusElements.get(i).getText().toLowerCase().contains("draft"))
				return allLinkElements.get(i);
		return null;
	}

	public WebElement annoncementsTableRowByStatusScheduled() {
		for (int i = 0; i < allStatusElements.size(); i++)
			if (allStatusElements.get(i).getText().toLowerCase().contains("scheduled"))
				return allLinkElements.get(i);
		return null;
	}

	public WebElement annoncementsTableRowByStatusUrgent() {
		for (int i = 0; i < allStatusElements.size(); i++)
			if (allStatusElements.get(i).getText().toLowerCase().contains("urgent"))
				return allLinkElements.get(i);
		return null;
	}

	/**
	 * Check view "PUBLISHED" announcement
	 */
	public boolean viewPublishedAnnouncement() {
		SeleniumWrapper.hoverMouseOverElement(driver, annoncementsTableRowByStatusPublished());
		SeleniumWrapper.explicitWaitClickable(driver, annoncementsTableRowByStatusPublished(), 50);
		Function.hoverNclickElement(driver, annoncementsTableRowByStatusPublished());
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		logger.info("The 'Published' announcement is:" + annoncementsTableRowByStatusPublished().getText());
		return true;
	}

	/**
	 * Check view "DRAFT" announcement
	 */

	public boolean viewDraftAnnouncement() {
		SeleniumWrapper.hoverMouseOverElement(driver, annoncementsTableRowByStatusDraft());
		SeleniumWrapper.explicitWaitClickable(driver, annoncementsTableRowByStatusDraft(), 50);
		Function.hoverNclickElement(driver, annoncementsTableRowByStatusDraft());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("The 'Draft' announcement is:" + annoncementsTableRowByStatusDraft().getText());
		return true;
	}

	/**
	 * Check view "SCHEDULED" announcement
	 */
	public boolean viewScheduledAnnouncement() {
		SeleniumWrapper.hoverMouseOverElement(driver, annoncementsTableRowByStatusScheduled());
		SeleniumWrapper.explicitWaitClickable(driver, annoncementsTableRowByStatusScheduled(), 50);
		Function.hoverNclickElement(driver, annoncementsTableRowByStatusScheduled());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("The 'Scheduled' announcement is:" + annoncementsTableRowByStatusScheduled().getText());
		return true;
	}

	/**
	 * Check view "URGENT" announcement
	 */
	public boolean viewUrgentAnnouncement() {
		SeleniumWrapper.explicitWaitClickable(driver,  annoncementsTableRowByStatusUrgent(), 50);
		Function.hoverNclickElement(driver, annoncementsTableRowByStatusUrgent());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("The 'Urgent' announcement is:" + annoncementsTableRowByStatusUrgent().getText());
		return true;	
	}

	/**
	 * Pick "PUBLISHED" status announcement
	 */
	@FindBy(xpath = "//div[contains(@class,'match')]/a")
	public static List<WebElement> allLinkElementsWithoutStatus;

	public WebElement pickPublishedAnnouncement() {
		for (int i = 0; i < allStatusElements.size(); i++)
			if (allStatusElements.get(i).getText().toLowerCase().contains("published"))
				return allLinkElementsWithoutStatus.get(i);
		return null;
	}

	public static String title;

	public boolean openPublishedAnnouncement() {
		SeleniumWrapper.explicitWaitClickable(driver, pickPublishedAnnouncement(), 50);
		Function.hoverNclickElement(driver, pickPublishedAnnouncement());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		title = pickPublishedAnnouncement().getText();
		logger.info("The 'Published' announcement to be viewed is:" + title);
		return true;
	}

	/**
	 * Check duplicate a "PUBLISHED announcement
	 */
	@FindBy(id = "duplicate-button")
	public static WebElement duplicateBtn;

	@FindBy(xpath = "//input[@name='subject']")
	public static List<WebElement> inputSubjectBox; // get index 0

	public boolean clickDuplicateBtn() {
		Function.clickElement(driver, duplicateBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public static String newSubject;
	public static String originalSubj;
	public static String content;

	public boolean modifySubject() {
		SeleniumWrapper.explicitWaitClickable(driver, inputSubjectBox.get(0), 20);
		if (Function.hoverNclickElement(driver, inputSubjectBox.get(0))) {
			String originalSubj = inputSubjectBox.get(0).getAttribute("value");
			logger.info("The original subject is: " + originalSubj);
			String str = originalSubj;
			int startIndex = str.indexOf(")");
			content = str.substring(startIndex + 1);
			String newSubj = content;
			SeleniumWrapper.setInputFieldText(inputSubjectBox.get(0), newSubj, driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			logger.info("The modified subject is:" + inputSubjectBox.get(0).getAttribute("value"));
		}
		return true;
	}

	/**
	 * Check delete a Published announcement
	 */
	@FindBy(id = "delete-admin-button")
	public static WebElement deleteBtn;

	@FindBy(xpath = "//button[contains(.,'Confirm')]")
	public static WebElement confirmDeleteBtn;

	/**
	 * Click delete icon to delete announcement
	 * 
	 * @throws Exception
	 */
	public boolean clickDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtn, 30);
		if (deleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	

	/**
	 * Click Confirm button to delete announcement
	 */
	public boolean clickConfirmBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check who received the announcement by clicking "Who Received This?"button
	 */
	@FindBy(xpath = "//a[contains(., 'Who Received This?')]")
	public static WebElement btnOfWhoReceivedThis;
	// a[@href ='/reports/executeDeliveryReport/4/10']

	@FindBy(xpath = "//*[@id=\"header-reports\"]/div[2]/h2")
	public static WebElement deliveryReport;

	@FindBy(xpath = "//*[@id=\"datatable_reports_wrapper\"]/div[2]/div[1]/div/table/thead/tr/th[3]")
	public static WebElement columnHeaderName;

	@FindBy(xpath = "//*[@id='datatable_reports']/tbody/tr/td[3]")
	public static List<WebElement> columnName;

	/**
	 * Check who received the announcement by clicking "Who Received This?"button
	 */

	public boolean verifyWhoReceivedAnnouncement() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, btnOfWhoReceivedThis);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.switchToNewWindow(driver, parentWindow);
		Function.clickElement(driver, deliveryReport);

		String reportTitle = deliveryReport.getText();
		logger.info("The actual report title is :" + reportTitle);
		logger.info("The original title is :" + title);

		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderName, 20);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if (SeleniumWrapper.clickElement(driver, columnHeaderName, Constants.CLICK_METHOD_ENUM.CLICK)) {

			List<String> nameList = new ArrayList<>();
			try {
				for (int i = 0; i < columnName.size(); i++)
					nameList.add(columnName.get(i).getText());
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
			logger.info("The recipients are: " + nameList.stream().distinct().collect(Collectors.toList()));
		}
		return Function.closeNSwitchWindow(driver, parentWindow);
	}

// ****************************************************************************//

	/**
	 * Pick "DRAFT" status announcement
	 */
	public WebElement pickdDraftAnnouncement() {
		for (int i = 0; i < allStatusElements.size(); i++)
			if (allStatusElements.get(i).getText().toLowerCase().contains("draft"))
				return allLinkElementsWithoutStatus.get(i);
		return null;
	}

	public boolean openDraftAnnouncement() {
		SeleniumWrapper.hoverMouseOverElement(driver, allLinkElementsWithoutStatus.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, pickdDraftAnnouncement(), 20);
		Function.hoverNclickElement(driver, pickdDraftAnnouncement());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		title = pickdDraftAnnouncement().getText();
		logger.info("The 'Draft' announcement to be viewed is:" + title);
		return true;
	}

	/**
	 * click preview button to preview the Draft announcement
	 */
	@FindBy(xpath = "//button[contains(.,'Preview')]")
	public static WebElement previewBtn;

	@FindBy(xpath = "//*[@id=\"view_announcement_preview\"]/div[1]/span")
	public static WebElement titleOfAnnouncement;

	@FindBy(xpath = "//*[@id=\"announcements_preview-content\"]/ul/li[2]/a")
	public static WebElement previewByEmail;

	@FindBy(xpath = "//*[@id=\"email-preview\"]/table/tbody/tr/td/table[1]/tbody/tr[2]/td[2]/a/img")
	public static WebElement logoImgInEmailView;

	@FindBy(xpath = "//button[contains(.,'Close')]")
	public static WebElement closePreviewBtn;

	public boolean clickPreviewBtn() {
		Function.clickElement(driver, previewBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Get Draft announcement's title
	 */
	public boolean getDraftAnnouncementTitle() {
		SeleniumWrapper.explicitWaitClickable(driver, titleOfAnnouncement, 20);
		if (SeleniumWrapper.clickElement(driver, titleOfAnnouncement, Constants.CLICK_METHOD_ENUM.CLICK)) {
			String actualTitle = titleOfAnnouncement.getText();
			logger.info("The actual title of announcemenet is :" + actualTitle);
			logger.info("The original title of announcemenet is :" + title);
		}
		return true;
	}

	/**
	 * Click to preview announcement by Email
	 */
	public boolean isLogoImageDisplayed() {
		Function.clickElement(driver, previewByEmail);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if (SeleniumWrapper.clickElement(driver, previewByEmail, Constants.CLICK_METHOD_ENUM.CLICK)) {
			Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					logoImgInEmailView);
			if (!ImagePresent) {
				logger.info("Image is not displayed");
			} else {
				logger.info("Image is displayed");
			}
		}
		return true;
	}

	/**
	 * Click close preview announcement
	 * 
	 * @throws Exception
	 */
	public boolean closePreview() {
		Function.clickElement(driver, closePreviewBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check edit the DRAFT announcement
	 */

	@FindBy(id = "edit-button")
	public static WebElement editBtn;

	@FindBy(xpath = "//*[@class='fr-wrapper']")
	public static WebElement msgBox1;

	@FindBy(xpath = "//*[@class='fr-wrapper show-placeholder']")
	public static WebElement msgBox2;

	@FindBy(xpath = "//*[@class='fr-element fr-view']")
	public static WebElement ContentEditable;

	/**
	 * Click edit button to edit DRAFT announcement
	 */
	public boolean clickEditBtn() {
		return Function.clickElement(driver, editBtn);
	}

	/**
	 * Click Save button
	 * 
	 * @throws Exception
	 */
	public boolean clickSaveDraftBtnToSaveDraft() {
		Function.clickElement(driver, sendBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check publish DRAFT announcement LATER
	 */
	@FindBy(xpath = "//button[contains(.,'Publish Announcement')]")
	public static WebElement publishAnnouncementBtn;

	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> publishTypeList;

	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static List<WebElement> calendarIcon;

	@FindBy(xpath = "//*[@class='date-field required']")
	public static List<WebElement> dateInputBox;

	@FindBy(xpath = "//*[@class='next']")
	public static List<WebElement> nextBtn;

	@FindBy(xpath = "//button[contains(.,'Schedule Announcement')]")
	public static WebElement scheduledAnnouncementBtn;

	/**
	 * Click "Publish Announcement" button
	 * 
	 * @throws Exception
	 */
	public boolean clickPublishAnnouncementBtn() {
		Function.hoverNclickElement(driver, publishAnnouncementBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Click Publish "Later" radio button
	 */
	public boolean clickPublishLaterBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, publishTypeList.get(3), 50);
		if (publishTypeList.get(3).isEnabled())
			return Function.hoverNclickElement(driver, publishTypeList.get(3));
		return false;
	}

	/**
	 * Pick publish date from Calendar
	 */
	public boolean pickPublishDate() {
		Function.clickElement(driver, calendarIcon.get(6));
		return Function.setDatefromCalendar(driver, 5);
		//return Function.hoverNclickElement(driver, datePicker.get(33));
	}
	
	/**
	 * check disable Send Notification
	 */
	@FindBy(xpath = "//*[@id=\"button-send-notifications\"]/span[2]")
	public static WebElement checkboxOfSendNotification;

	public boolean disableSendNotification() {
		SeleniumWrapper.explicitWaitClickable(driver, checkboxOfSendNotification, 30);
		if (checkboxOfSendNotification.isEnabled())
			return SeleniumWrapper.clickElement(driver, checkboxOfSendNotification, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Click "Schedule Announcement" button
	 * 
	 * @throws Exception
	 */
	public boolean clickScheduledAnnouncementBtn() {
		Function.hoverNclickElement(driver, scheduledAnnouncementBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check publish DRAFT announcement NOW
	 */

	/**
	 * Click "Send" button to publish announcement
	 * 
	 * @throws Exception
	 */
	public boolean clickSendBtnToPublishAnnouncement() {
		SeleniumWrapper.explicitWaitClickable(driver, sendBtn, 30);
		if (sendBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, sendBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Search published announcement and verify status is changed from DRAFT to
	 * PUBLISHED
	 */
	public boolean searchAndVerifyPublishedAnnouncement() {
		logger.info("The title of draft announcemenet to be published is :" + title);
		String st = title;
		String[] s = st.split("Not yet published");
		String s1 = s[0];
		String titleWithoutStatus = s1;
		logger.info("The title without status is :" + titleWithoutStatus);

		String searchKeyword = titleWithoutStatus;
		Function.clickElement(driver, searchBox);
		List<String> allRecord = new ArrayList<>();
		List<String> matchedRecord = new ArrayList<>();
		try {
			for (int i = 0; i < annoncementsTableRowByViewTab.size(); i++)
				allRecord.add(annoncementsTableRowByViewTab.get(i).getText());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		for (int i = 0; i < allRecord.size(); i++) {
			if (allRecord.get(i).contains(searchKeyword)) {
				matchedRecord.add(allRecord.get(i));
			}
		}
		logger.info("Matched result is: " + matchedRecord);

		if (!matchedRecord.contains("Not yet published")) {
			return true;
		}
		return false;
	}

// ************************************************************************************************
	/**
	 * Pick "SCHEDULED" status announcement
	 */
	public WebElement pickdScheduledAnnouncement() {
		for (int i = 0; i < allStatusElements.size(); i++)
			if (allStatusElements.get(i).getText().toLowerCase().contains("scheduled"))
				return allLinkElementsWithoutStatus.get(i);
		return null;
	}

	public boolean openScheduledAnnouncement() {
		SeleniumWrapper.hoverMouseOverElement(driver, pickdScheduledAnnouncement());
		SeleniumWrapper.explicitWaitClickable(driver, pickdScheduledAnnouncement(), 20);
		Function.hoverNclickElement(driver, pickdScheduledAnnouncement());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		title = pickdScheduledAnnouncement().getText();
		logger.info("The 'Scheduled' announcement to be viewed is:" + title);
		return true;
	}

	/**
	 * click preview button, get Scheduled announcement's title
	 */
	public boolean getAnnouncementTitle() {
		SeleniumWrapper.explicitWaitClickable(driver, titleOfAnnouncement, 20);
		if (SeleniumWrapper.clickElement(driver, titleOfAnnouncement, Constants.CLICK_METHOD_ENUM.CLICK)) {
			String actualTitle = titleOfAnnouncement.getText();
			logger.info("The actual title of announcemenet is : " + actualTitle);
			logger.info("The original title of announcemenet is : " + title);
			Assert.assertTrue(!actualTitle.contains("Not yet published"));
		}
		return true;
	}

	/**
	 * Check edit announcement: change status from Scheduled to Draft
	 */
	@FindBy(xpath = "//button[contains(.,'Edit Publish Date')]")
	public static WebElement editPublishDateBtn;

	@FindBy(xpath = "//button[contains(.,'Cancel & Move to Draft')]")
	public static WebElement cancelAndMoveTODraftBtn;

	/**
	 * Click "Edit Publish Date" button
	 */
	public boolean clickEditPublishDateBtn() {
		return Function.clickElement(driver, editPublishDateBtn);
	}

	/**
	 * Click "Cancel & Move to Draft" button
	 * 
	 * @throws Exception
	 */
	public boolean clickCancelAndMoveToDraftBtn() {
		Function.clickElement(driver, cancelAndMoveTODraftBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check publish Scheduled announcement LATER
	 */
	@FindBy(xpath = "//button[contains(.,'Save Changes')]")
	public static WebElement saveChangesBtn;

	/**
	 * Click "Save Change" button
	 * 
	 * @throws Exception
	 */
	public boolean clickSaveChangesBtn() {
		Function.clickElement(driver, saveChangesBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check publish Scheduled announcement NOW
	 */

	/**
	 * Click Publish "NOW" radio button
	 * 
	 * @throws Exception
	 */
	public boolean clickPublishNowBtn() {
		Function.hoverNclickElement(driver, publishTypeList.get(2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

//*************************************************************************************************//	

	/**
	 * Check view all announcements
	 */
	@FindBy(xpath = "//a[contains(.,'View All Announcements')]")
	public static WebElement viewAllAnnouncementsBtn;

	@FindBy(xpath = "//a[contains(text(),'List')]")
	public static WebElement listTab;

	public boolean clickViewAllAnnouncements() {
		SeleniumWrapper.explicitWaitClickable(driver, viewAllAnnouncementsBtn, 50);
		if (viewAllAnnouncementsBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, viewAllAnnouncementsBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			return true;
		}
		return false;
	}
	

	public boolean isAtListTab() {
		SeleniumWrapper.explicitWaitClickable(driver, listTab, 30);
		logger.info(" The text is :" + listTab.getText());
		return listTab.getText().toLowerCase().contains("list");
	}

//*****************************************************

	/**
	 * Check go to LIST tab
	 */
	public boolean goToListTab() {
		Function.clickElement(driver, listTab);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}
	
	/**
	 * Check go back VIEW tab
	 */
	@FindBy(xpath = "//a[contains(text(),'View')]")
	public static WebElement viewTab;
	
	public boolean goBackViewTab() {
		Function.clickElement(driver, viewTab);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check search announcement from List tab
	 */
	@FindBy(name = "datatable_all_search-input")
	public static WebElement searchField;

	public static final String tableRowsXpathOfListTab = "//*[@id='datatable_all']/tbody/tr";
	@FindBy(xpath = tableRowsXpathOfListTab)
	public static List<WebElement> tableRowOfAnnouncements;

	public boolean searchAnnouncementFromListTab() {
		String keyword1 = "y";
		String keyword2 = "a";
		String keyword3 = "r";
		String keyword4 = "d";
		Function.clickElement(driver, searchField);
		SeleniumWrapper.setInputFieldText(searchField, keyword1, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldText(searchField, keyword1 + keyword2, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldText(searchField, keyword1 + keyword2 + keyword3, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldText(searchField,  keyword1 + keyword2 + keyword3 + keyword4, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return TestResultValidator.isSearchedFromElementsList(tableRowsXpathOfListTab, driver,
				keyword1 + keyword2 + keyword3 + keyword4);
	}

	/**
	 * Check sorting function on announcements table
	 */

	/**
	 * Check sort per column STATUS
	 */
	//@FindBy(xpath = "//*[@id=\"datatable_all\"]/thead/tr/th[1]")
	@FindBy(xpath = "//*[@id='datatable_all']/thead/tr/th[1]")
	public static WebElement columnHeaderStatus;

	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[1]")
	public static List<WebElement> columnStatus;

	public boolean sortByColumnStatus() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderStatus, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderStatus, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.waitForPageToBeLoaded(driver);
			TestResultValidator.isStringElementsAscendingOrdered(columnStatus);
			SeleniumWrapper.waitForPageToBeLoaded(driver);
			return true;
		}
		return false;
	}
	
	

	/**
	 * Check sort per column PUBLISHED
	 */
	@FindBy(xpath = "//*[@id=\"datatable_all\"]/thead/tr/th[2]")
	public static WebElement columnHeaderPublished;

	@FindBy(xpath = "//*[@id=\"datatable_all\"]/tbody/tr/td[2]")
	public static List<WebElement> columnPublished;

	public boolean sortByColumnPublished() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderPublished, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderPublished, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.waitForPageToBeLoaded(driver);
			TestResultValidator.isDateElementsAscendingOrdered(columnPublished);
			SeleniumWrapper.waitForPageToBeLoaded(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check sort per column SUBJECT
	 */
	@FindBy(xpath = "//*[@id='datatable_all']/thead/tr/th[3]")
	public static WebElement columnHeaderSubject;

	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[3]")
	public static List<WebElement> columnSubject;

	public boolean sortByColumnSubject() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderSubject, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderSubject, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.waitForPageToBeLoaded(driver);
			TestResultValidator.isStringElementsAscendingOrdered(columnSubject);
			SeleniumWrapper.waitForPageToBeLoaded(driver);
			return true;
		}
		return false;
	}

//**************************************************************************************************	
	/**
	 * Check create draft announcement(add event in the Community Schedule) from
	 * List Tab
	 */

	public boolean clickCreateAnnouncementBtnFromListTab() {
		Function.clickElement(driver, createAnnouncementBtn.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check Who Received the Urgent Announcements'notification from List tab
	 */
	@FindBy(xpath = "//*[@class='summary_title']")
	public static List<WebElement> subjectTitle;

	public WebElement pickdUrgentStatus() {
		for (int i = 0; i < columnStatus.size(); i++)
			if (columnStatus.get(i).getText().toLowerCase().contains("urgent"))
				return columnStatus.get(i);
		return null;
	}

	public boolean openUrgentAnnouncement() {
		SeleniumWrapper.explicitWaitClickable(driver, pickdUrgentStatus(), 80);
		SeleniumWrapper.clickElement(driver, pickdUrgentStatus(), Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String title = subjectTitle.get(0).getText();
		logger.info("The subject of 'Urgent' announcement is:" + title);
		if (title.contains("Urgent Announcement"))
			return true;
		return false;
	}

	public boolean verifyWhoReceivedNotification() {
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, btnOfWhoReceivedThis);
		Function.switchToNewWindow(driver, parentWindow);
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderName, 30);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if (SeleniumWrapper.clickElement(driver, columnHeaderName, Constants.CLICK_METHOD_ENUM.CLICK)) {

			List<String> nameList = new ArrayList<>();
			for (int i = 0; i < columnName.size(); i++)
				nameList.add(columnName.get(i).getText());
			List<String> distinctRecipient = nameList.stream().distinct().collect(Collectors.toList());
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			logger.info("The recipients are: " + distinctRecipient);
			Assert.assertTrue(distinctRecipient.contains("Daniels Qu"));
		}
		Function.closeNSwitchWindow(driver, parentWindow);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check duplicate an announcement
	 */
	@FindBy(xpath = "//*[@id=\"datatable_all\"]/tbody/tr/td[5]/a")
	public static List<WebElement> duplicateIcon;

	public boolean clickDuplicateIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, duplicateIcon.get(0));
		Function.hoverNclickElement(driver, duplicateIcon.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check edit an announcement (pick draft announcement to do edition)
	 */
	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[6]")
	public static List<WebElement> editIcon;

	@FindBy(xpath = "//*[@id=\"inner-main-body\"]/div/form/fieldset/div[4]/div[2]/div/div[3]/div")
	public static WebElement msgEditable;

	public WebElement pickDraftAnnouncement() {
		for (int i = 0; i < columnStatus.size(); i++)
			if (columnStatus.get(i).getText().toLowerCase().contains("draft"))
				return editIcon.get(i);
		return null;
	}

	public boolean clickEditIconOnDraftAnnouncement() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, pickDraftAnnouncement(), 20);
		Function.hoverNclickElement(driver, pickDraftAnnouncement());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean editMessage() {
		SeleniumWrapper.setInputFieldText(msgEditable, msgContent, driver);
		logger.info("The final message is: " + msgEditable.getText());
		return true;
	}

	/**
	 * Check "Published" status announcement from List Tab
	 */
	public WebElement pickPublishedRecord() {
		for (int i = 0; i < columnStatus.size(); i++)
			if (columnStatus.get(i).getText().toLowerCase().contains("published"))
				return deleteIcon.get(i);
		return null;
	}

	public boolean clickDeleteIconOnPublishedAnnouncement() {
		SeleniumWrapper.explicitWaitClickable(driver, pickPublishedRecord(), 50);
		SeleniumWrapper.hoverMouseOverElement(driver, pickPublishedRecord());
		return SeleniumWrapper.clickElement(driver, pickPublishedRecord(), Constants.CLICK_METHOD_ENUM.CLICK);
	}

	public WebElement pickPublishedStatus() {
		for (int i = 0; i < columnStatus.size(); i++)
			if (columnStatus.get(i).getText().toLowerCase().contains("published"))
				return columnStatus.get(i);
		return null;
	}

	public boolean clickPublishedAnnouncementFromListTab() {
		SeleniumWrapper.explicitWaitClickable(driver, pickPublishedStatus(), 30);
		Function.hoverNclickElement(driver, pickPublishedStatus());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check "Urgent" status announcement from List Tab
	 */
	public WebElement pickUrgentAnnouncement() {
		for (int i = 0; i < columnStatus.size(); i++)
			if (columnStatus.get(i).getText().toLowerCase().contains("urgent"))
				return deleteIcon.get(i);
		return null;
	}

	public boolean clickDeleteIconOnUrgentAnnouncement() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, pickUrgentAnnouncement(), 50);
		Function.hoverNclickElement(driver, pickUrgentAnnouncement());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check delete an announcement
	 */
	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[7]")
	public static List<WebElement> deleteIcon;

	public boolean clickDeleteIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon.get(0));
		int recordToBeDeleted = deleteIcon.size() - 1;
		SeleniumWrapper.explicitWaitClickable(driver, deleteIcon.get(recordToBeDeleted), 20);
		Function.hoverNclickElement(driver, deleteIcon.get(recordToBeDeleted));
		String itemToBeDeleted = columnSubject.get(recordToBeDeleted).getText() + ","
				+ columnStatus.get(recordToBeDeleted).getText();
		logger.info("The item to be deleted is : " + itemToBeDeleted);

		String currWindow = driver.getWindowHandle();
		logger.info("The current window name is:" + currWindow);

		driver.switchTo().window(currWindow);
		Function.clickElement(driver, confirmDeleteBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
}
