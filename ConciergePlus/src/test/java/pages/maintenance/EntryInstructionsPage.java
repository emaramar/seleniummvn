package pages.maintenance;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;
import pages.BasePage;

public class EntryInstructionsPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(EntryInstructionsPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public EntryInstructionsPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Entry Instructions Page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoEntryInstructionsPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (completedTab.isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Navigate to Amenity Booking page
	 */
	@FindBy(xpath = "//*[contains(.,'Maintenance')]/span[2]")
	public static WebElement MaintenanceTab;
	
	@FindBy(xpath = "//a[@href ='/entry_instructions']/span")
	public static WebElement entryInstructionMenu;
	

	public boolean navigateToEntryInstructionMenu() {
		Function.clickElement(driver, MaintenanceTab);
		return Function.clickElement(driver, entryInstructionMenu);
	}

	/**
	 * Check resident user create new entry instruction
	 */
	@FindBy(xpath = " //a[@href='/entry_instructions/create/']")
	public static List<WebElement> createBtnEntryInstructionBtn;

	@FindBy(xpath = "//*[contains(text(), 'Caregiver')]")
	public static List<WebElement> typeofCaregiver; // get index 1

	@FindBy(xpath = "//a[@role='option']")
	public static List<WebElement> choicesOftype;

	@FindBy(xpath = "//*[@class='readonly_field']")
	public static WebElement unit;

	@FindBy(name = "name")
	public static WebElement visitorBox;

	@FindBy(id = "description")
	public static WebElement informationBox;

	@FindBy(xpath = "//*[@id='button-is_allow_keys']/span[1]")
	public static WebElement allowToUseKeyCheckbox;

	@FindBy(xpath = "//*[@class='date-field required']")
	public static WebElement dateStartInputBox;

	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> expirationDateRadioBtn;

	@FindBy(id = "expiration_date")
	public static WebElement expirationDateInputBox;

	@FindBy(xpath = "//*[@id='upload-photo']/span[2]")
	public static WebElement uploadVisitorPhotoBtn;

	@FindBy(xpath = "//*[@id=\"save-instruction\"]/span[2]")
	public static WebElement saveBtn;
	
	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static List<WebElement> calendarIcon;
	
	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td")
	public static List<WebElement> datePicker;

	public boolean clickCreateEntryInstructionBtn() {
		return Function.clickElement(driver, createBtnEntryInstructionBtn.get(0));
	}

	public boolean pickEntryInstructiontype_DogWalker() {
		Function.clickElement(driver, typeofCaregiver.get(1));
		return Function.clickElement(driver, choicesOftype.get(3));
	}

	public boolean checkUnitBox() {
		SeleniumWrapper.explicitWaitClickable(driver, unit, 30);
		String unitValue = unit.getText();
		logger.info("The unit  value is:" + unitValue);
		if(unitValue.equalsIgnoreCase("101"))
			return true;
		return false;
	}

	public boolean inputVisitorName() {
		Function.clickElement(driver, visitorBox);
		return SeleniumWrapper.enterTitle(driver, visitorBox);
	}

	public boolean inputVisitorName1() {
		Function.clickElement(driver, visitorBox);
		return SeleniumWrapper.enterTitle(driver, visitorBox);
	}

	public boolean inputAdditionalInformation() {
		Function.clickElement(driver, informationBox);
		return SeleniumWrapper.enterText(driver, informationBox);
	}

	public boolean checkAllowToUseMyKey() {
		return Function.clickElement(driver, allowToUseKeyCheckbox);
	}

	public boolean setStartDate() {
		return Function.setDateByEnter(driver, 0, dateStartInputBox);
		//Function.clickElement(driver, calendarIcon.get(0));
		//return Function.hoverNclickElement(driver, datePicker.get(34));
	}

	public boolean setExpirationDate() {
		Function.hoverNclickElement(driver, expirationDateRadioBtn.get(1));
		return Function.setDateByEnter(driver, 5, expirationDateInputBox);
	}

	public boolean uploadVisitorPhoto() throws Exception {
		// upload dogwalker photo
		Function.uploadFile(driver, uploadVisitorPhotoBtn, Constants.imgOfDogWalker);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return true;
	}

	public boolean clickSaveBtn() {
		Function.clickElement(driver, saveBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;

	}

	/**
	 * Check 'Visitor or Company' field is mandatory field
	 */
	@FindBy(xpath = "//*[@id='error_container']/span[2]")
	public static WebElement errorMsg;

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg, 30);
		String errorMessage = errorMsg.getText();
		logger.info("The error message is :" + errorMessage);
		if(errorMsg != null)
			return true;
		else
		return false;
	}

	/**
	 * Check create new Do Not Allow' Entry Instruction
	 */
	public boolean pickEntryInstructiontype_DoNotAllow() {
		Function.clickElement(driver, typeofCaregiver.get(1));
		return Function.clickElement(driver, choicesOftype.get(2));
	}

	/**
	 * Check create new Entry Instruction for HouseKeeper
	 */
	public boolean pickEntryInstructiontype_Housekeeper() {
		Function.clickElement(driver, typeofCaregiver.get(1));
		return Function.clickElement(driver, choicesOftype.get(4));
	}

	/**
	 * Check go back to Entry Instruction tab
	 */
	@FindBy(xpath = "//a[@href ='/entry_instructions']")
	public static List<WebElement> entryInstructionTab;

	public boolean goBackToEntryInstructionTab() {
		return Function.hoverNclickElement(driver, entryInstructionTab.get(1));
	}

	/**
	 * Check "Pending Approval" status is present on new entry instruction
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[3]/a")
	public static List<WebElement> visitorList;

	public boolean IsPendingApprovalStatusPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, tableRowByPendingApproval(), 30);
		String title = tableRowByPendingApproval().getText();
		logger.info("the title is:" + title);
		if(title.contains("PENDING APPROVAL"))
			return true;
		return true;
	}

	/**
	 * Check resident user view the new entry instruction record
	 */
	@FindBy(xpath = "//*[@id=\"cancel--instruction\"]/span[2]")
	public static WebElement cancelBtn;

	public WebElement tableRowByPendingApproval() {
		for (int i = 0; i < visitorList.size(); i++)
			if (visitorList.get(i).getText().toLowerCase().contains("pending approval"))
				return visitorList.get(i);
		return null;
	}

	public boolean viewEntryInstructionRecord() {
		SeleniumWrapper.explicitWaitClickable(driver, tableRowByPendingApproval(), 20);
		String visitor = tableRowByPendingApproval().getText();
		logger.info("the title is:" + visitor);
		return Function.hoverNclickElement(driver, tableRowByPendingApproval());
	}

	public boolean clickCancelBtn() {
		Function.clickElement(driver, cancelBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check Resident user edit entry instruction
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[6]")
	public static List<WebElement> editIcon;

	@FindBy(xpath = "//*[@id='delete-photo']/span[2]")
	public static WebElement deleteVisitorPhotoBtn;

	public boolean clickEditIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(0));
		return Function.clickElement(driver, editIcon.get(0));
	}

	public boolean editAdditionalInformation() {
		Function.clickElement(driver, informationBox);
		String originText = informationBox.getAttribute("value");
		String afterText = originText + "!";
		SeleniumWrapper.setInputFieldText(informationBox, afterText, driver);
		logger.info("The modify text is:" + informationBox.getAttribute("value"));
		return true;
	}

	public boolean deleteVisitorPhoto() {
		if (deleteVisitorPhotoBtn != null) {
			Function.clickElement(driver, deleteVisitorPhotoBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check Resident user modify expiration date on entry instruction
	 */
	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static List<WebElement> dateCalendarIcon;

	public boolean modifyExpirationDate() {
		if (!expirationDateRadioBtn.get(1).isSelected())
			Function.hoverNclickElement(driver, expirationDateRadioBtn.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, dateCalendarIcon.get(1));
		Function.setDatefromCalendar(driver, 1);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("New select date :" + expirationDateInputBox.getAttribute("value"));
		return true;
	}

	/**
	 * Check Resident user delete entry instruction
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[7]")
	public static List<WebElement> deleteIcon;

	@FindBy(id = "delete-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean deleteRecord() {
		Function.hoverNclickElement(driver, deleteIcon.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled()) 
			return SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
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
		if(confirmMsg.isDisplayed()&& !message.isEmpty())
				return true;
		return false;
	}

	/**
	 * Check user Property Manager Decline & Delete new entry instruction
	 */
	@FindBy(xpath = "//*[@id='decline-instruction']/span[2]")
	public static WebElement declineNDeleteBtn;

	@FindBy(id = "instruction_decline_note")
	public static WebElement reasonOfDecline;

	@FindBy(id = "modal-save-button")
	public static WebElement confirmDeclineNDeleteBtn;

	public boolean declineNDeleteNewEntryInstruction() {
		SeleniumWrapper.explicitWaitClickable(driver, declineNDeleteBtn, 30);
		if (declineNDeleteBtn != null) {
			Function.clickElement(driver, declineNDeleteBtn);
		}
		Function.clickElement(driver, reasonOfDecline);
		SeleniumWrapper.setInputFieldText(reasonOfDecline, "No longer needed", driver);
		logger.info("decline massage:" + reasonOfDecline.getAttribute("value"));
		Function.clickElement(driver, confirmDeclineNDeleteBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check user Property Manager Approve new entry instruction
	 */
	@FindBy(xpath = "//*[@id='approve-instruction']/span[2]")
	public static WebElement approveBtn;

	@FindBy(id = "confirm")
	public static WebElement confirmApproveBtn;

	public boolean approveNewEntryInstruction() {
		SeleniumWrapper.explicitWaitClickable(driver, approveBtn, 30);
		if (approveBtn != null) {
			Function.clickElement(driver, approveBtn);
			Function.clickElement(driver, confirmApproveBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check user Property Manager create new entry instruction
	 */
	@FindBy(id = "user_search_box")
	public static WebElement searchUnitBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement selectUnit;

	public boolean enterUnit() {
		return Function.enterNameOrUnit(driver, searchUnitBox, "108", selectUnit);
	}

	public boolean pickEntryInstructiontype_Contractor() {
		Function.clickElement(driver, typeofCaregiver.get(1));
		return Function.clickElement(driver, choicesOftype.get(1));
	}

	/**
	 * Test of PM search entry instruction from Active Tab
	 */
	@FindBy(name = "active_search-input")
	private static WebElement searchBox_ActiveTab;

	public static final String tableRowsXpath_ActiveTab = "//*[@id='datatable']/tbody/tr";
	@FindBy(xpath = tableRowsXpath_ActiveTab)
	public static List<WebElement> tableRowByActiveTab;

	public boolean searchEntryInstructiontypeFromActiveTab() {
		String keyword = "Allow";
		return Function.search(driver, searchBox_ActiveTab, keyword, tableRowsXpath_ActiveTab);
	}

	/**
	 * Check goto Completed Tab
	 */
	@FindBy(xpath = "//a[@href='#tabs-2']")
	private static WebElement completedTab;

	public boolean gotoCompletedTab() {
		return Function.clickElement(driver, completedTab);
	}

	/**
	 * Check resident user view the entry instruction record from Completed Tab
	 */
	@FindBy(xpath = "//*[@id='datatable_expired']/tbody/tr/td[3]/a")
	public static List<WebElement> titleOfVisitor;

	public boolean viewEntryInstructionRecordFromCompletedTab() {
		SeleniumWrapper.explicitWaitClickable(driver, titleOfVisitor.get(0), 30);
		String visitor = titleOfVisitor.get(0).getText();
		logger.info("the title is:" + visitor);
		return Function.hoverNclickElement(driver, titleOfVisitor.get(0));
	}

	/**
	 * Check Resident user edit entry instruction from Completed Tab
	 */
	@FindBy(xpath = "//*[@id=\"datatable_expired\"]/tbody/tr/td[6]/a/span")
	public static List<WebElement> editIcon_CompletedTab;

	public boolean editEntryInstruction() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon_CompletedTab.get(0));
		Function.clickElement(driver, editIcon_CompletedTab.get(0));
		Function.clickElement(driver, visitorBox);
		String originText = visitorBox.getAttribute("value");
		logger.info("Original visitor :" + originText);
		String afterText = originText + "-" + "Expired";
		SeleniumWrapper.setInputFieldText(visitorBox, afterText, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("The modify visitor :" + visitorBox.getAttribute("value"));
		return true;
	}

	/**
	 * Check Resident user delete entry instruction from Completed Tab
	 */
	@FindBy(xpath = "//*[@id='datatable_expired']/tbody/tr/td[7]/a/span")
	public static List<WebElement> deleteIcon_CompletedTab;

	public boolean deleteRecord_CompletedTab() throws Exception {
		int countOfRecord_Before = deleteIcon_CompletedTab.size();
		logger.info("The before count is:" + countOfRecord_Before);
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon_CompletedTab.get(0));
		Function.clickElement(driver, deleteIcon_CompletedTab.get(0));
		Function.hoverNclickElement(driver, confirmDeleteBtn);
		SeleniumWrapper.refreshWebPage(driver);
		Function.clickElement(driver, completedTab);
		int countOfRecord_After = deleteIcon_CompletedTab.size();
		logger.info("The after count is:" + countOfRecord_After);
		if(countOfRecord_Before > countOfRecord_After)
			return true;
		return false;
	}

	/**
	 * Test of PM search entry instruction from Completed Tab
	 */
	@FindBy(name = "expired_search-input")
	private static WebElement searchBox_CompletedTab;

	public static final String tableRowsXpath_CompletedTab = "//*[@id='datatable_expired']/tbody/tr";
	@FindBy(xpath = tableRowsXpath_CompletedTab)
	public static List<WebElement> tableRowByCompletedTab;

	public boolean searchEntryInstructiontypeFromCompletedTab() {
		String keyword = "Caregiver";
		return Function.search(driver, searchBox_CompletedTab, keyword, tableRowsXpath_CompletedTab);
	}

}
