package pages.dailyOverview;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class NotesSec extends DailyOverviewPage {
	protected final static Logger logger = LogManager.getLogger(NotesSec.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */

	public NotesSec(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Notes section is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoNotesSec().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createNotebtn.isEnabled())
			return true;
		else
			return false;
	}

	/**
	 * Check create a Visitor Note
	 */
	@FindBy(xpath = "//button[@id=\"daily-overview-add-note\"]")
	public static WebElement createNotebtn;

	@FindBy(id = "note_entry_visitor_name")
	public static WebElement inputBoxOfVisitorName;

	@FindBy(id = "description")
	public static WebElement inputBoxOfDescription;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public static WebElement saveBtn;

	/**
	 * click "Create Note" button
	 */
	public boolean clickCreateNoteBtn() {
		Function.hoverNclickElement(driver, createNotebtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean closePopup() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		SeleniumWrapper.implicitWait_Constant(driver);
		return true;
	}

	/**
	 * input "Visitor Name"
	 */
	public boolean inputVisitorName() {
		Function.clickElement(driver, inputBoxOfVisitorName);
		return SeleniumWrapper.enterName(driver, inputBoxOfVisitorName);
	}

	/**
	 * Input Description of Visitor Note
	 */
	public boolean inputDescriptionOfVisitorNote() {
		Function.clickElement(driver, inputBoxOfDescription);
		return SeleniumWrapper.setInputFieldText(inputBoxOfDescription,
				"Vincent needs the key to the maintenance room for water resource to clean-up lobby.", driver);
	}

	/**
	 * Click Save button
	 */
	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		if (saveBtn.isDisplayed() && saveBtn.isEnabled()) {
			 SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			 return true;
		}
		else
		return false;
	}

	/**
	 * Click save button for "Not Allow Access Note"
	 */
	@FindBy(xpath = "//*[@id=\"save_title_content\"]/div[6]/div[2]/div[2]/div[1]/button/div/div/div")
	public static WebElement displayNoAllowEntryInstruction;
	
	public boolean clickSaveBtn_NotAllowAccessNote() {
		SeleniumWrapper.explicitWaitClickable(driver, displayNoAllowEntryInstruction, 30);
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		String copy = displayNoAllowEntryInstruction.getText();
		logger.info("Copy is: " + copy);
		if (copy.contains("Do Not Allow)")&& !saveBtn.isEnabled())
			return true;
		else
		return false;
	}
	
	
	/**
	 * Click cancel button
	 */
	//@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	@FindBy(xpath = "//button[@id='modal-cancel-button']")
	//public static List<WebElement> cancelBtn;
	public static WebElement cancelBtn;
	
	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 30);
		//SeleniumWrapper.explicitWaitClickable(driver, cancelBtn);
		if (cancelBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, cancelBtn, Constants.CLICK_METHOD_ENUM.CLICK);
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
		if (confirmMsg!=null && !message.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check create a Shift note with "Sign Key Out"
	 */
	@FindBy(xpath = "//*[@id='save_title_content']/div[3]/div[2]/div")
	public static WebElement typeDropdown;

	@FindBy(id = "bs-select-1-4")
	public static WebElement typeOfShiftNote;

	@FindBy(id = "bs-select-1-14")
	public static WebElement typeOfPreAuthorizedVisitor;

	@FindBy(xpath = "//*[@id=\"button-is_associate_keys\"]/span[1]")
	public static WebElement checkboxOfSignKeyOut;

	@FindBy(name = "related_keys")
	public static WebElement relatedKeys;

	// @FindBy(xpath = "//strong[@class='tt-highlight']")
	@FindBy(xpath = "//strong[contains(@class, 'tt-highlight')]")
	public static List<WebElement> pickRelatedKeysFromDropdown;

	/**
	 * click type
	 */
	public boolean clickType() {
		return Function.clickElement(driver, typeDropdown);
	}

	/**
	 * pick "Shift Note" from dropdown
	 */
	public boolean pickShiftNoteFromTypeDropdown() {
		return Function.clickElement(driver, typeOfShiftNote);
	}

	/**
	 * pick "Shift Note" from dropdown
	 */
	public boolean pickPreAuthorizedVisitor() {
		return Function.clickElement(driver, typeOfPreAuthorizedVisitor);
	}

	/**
	 * Input Description of Shift Note
	 */
	public boolean inputDescriptionOfShiftNote() {
		Function.clickElement(driver, inputBoxOfDescription);
		return SeleniumWrapper.setInputFieldTextNoClear(inputBoxOfDescription,
				"Wilson Damon took electricity room key for fixing electrical issue.", driver);
	}

	/**
	 * Check checkbox of "Sign Key Out"
	 */
	public boolean checkSignKeyOutBox() {
		return Function.clickElement(driver, checkboxOfSignKeyOut);
	}

	/**
	 * Click and pick "Related Keys"
	 */
	public boolean clickAndPickRelatedKeys() {
		Function.clickElement(driver, relatedKeys);
		SeleniumWrapper.setInputFieldTextNoClear(relatedKeys, "101", driver);
		return Function.clickElement(driver, pickRelatedKeysFromDropdown.get(0));
	}

	/**
	 * Check create notes through 'View All Notes' section Check create a Key Out
	 * Note
	 */
	@FindBy(xpath = "//a[@href='/notes']/span")
	public static WebElement viewAllNotesbtn;

	@FindBy(id = "add-note")
	//@FindBy(xpath ="//a[@id='add-note']")
	public static WebElement createNoteBtnFromViewAllNotesPage;

	@FindBy(xpath = "//*[@id=\"bs-select-2-3\"]/span")
	public static WebElement typeOfKeyOutNote;

	/**
	 * click "Create Note" button from View All Notes page
	 */
	public boolean clickCreateNoteBtnFromViewAllNotesPage() {
		Function.clickElement(driver, createNoteBtnFromViewAllNotesPage);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * pick "Key Out" from dropdown
	 */
	public boolean pickKeyOutFromTypeDropdown() {
		return Function.clickElement(driver, typeOfKeyOutNote);
	}

	/**
	 * input "Visitor Name"
	 */
	public boolean inputVisitorNameForKeyOutNote() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfVisitorName, 30);
		if (Function.clickElement(driver, inputBoxOfVisitorName))
			return SeleniumWrapper.setInputFieldTextNoClear(inputBoxOfVisitorName, "Vincent Mayer", driver);
		else
		return false;
	}

	/**
	 * Input Description of Shift Note
	 */
	public boolean inputDescriptionOfKeyOutNote() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfDescription, 30);
		if (Function.clickElement(driver, inputBoxOfDescription))
			return SeleniumWrapper.setInputFieldTextNoClear(inputBoxOfDescription,
					"Vincent needs the key to the maintenance room for water resource to clean-up lobby.", driver);
		else
		return false;
	}

	/**
	 * Check create notes through 'View All Notes' section Test of create a Visitor
	 * Note with Assign Visitor's Parking //from "View All Notes" tab
	 */
	@FindBy(name = "related_units")
	public static WebElement relatedUnit;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static List<WebElement> choiceOfRelatedUnitList;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfRelatedUnit;

	@FindBy(xpath = "//*[@id=\"button-is_assign_visitor_parking\"]/span[1]")
	public static WebElement checkboxOfAssignVisitorsParking;

	@FindBy(xpath = "//*[@id=\"visitor_parking_form\"]/div[1]/div[2]/div/button/div/div/div")
	public static WebElement assignedSpot;

	@FindBy(xpath = "//div[@id='bs-select-4']/ul/li/a")
	//@FindBy(xpath = " //div[@id='bs-select-4']/ul/li/a[@id='bs-select-4-0']")
	public static List<WebElement> choiceOfAssignedSpot;

	@FindBy(xpath = "//div[@class=\"bs-searchbox\"]/input")
	public static List<WebElement> searchCarSpot;

	@FindBy(id = "visitor_parking_plate")
	public static WebElement inputBoxOfCarPlate;

	@FindBy(xpath = "//*[@id=\"visitor_parking_form\"]/div[3]/div[2]/div/button")
	public static WebElement carMaker;

	@FindBy(xpath = "//*[contains(text(),'Kia')]")
	public static List<WebElement> choiceOfCarMaker_Kia;

	@FindBy(xpath = "//div[contains(text(),'1 hour')]")
	public static WebElement expiresTimeSlot;

	@FindBy(xpath = "//*[@id='bs-select-6']/ul/li/a/span")
	public static List<WebElement> choiceOfExpiresTimeSlot;

	/**
	 * Pick related Unit(s)
	 */
	public boolean pickRelatedUnit_AssignParkingSpot() {
		Function.clickElement(driver, relatedUnit);
		SeleniumWrapper.setInputFieldTextNoClear(relatedUnit, "101", driver);
		return Function.clickElement(driver, choiceOfRelatedUnitList.get(0));
	}

	/**
	 * Pick related Unit(s)
	 */
	public boolean pickRelatedUnit_PreAuthorizedVisitor1() {
		Function.clickElement(driver, relatedUnit);
		SeleniumWrapper.setInputFieldTextNoClear(relatedUnit, "201", driver);
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfRelatedUnit, 30);
		if(choiceOfRelatedUnit.isDisplayed())
			return SeleniumWrapper.clickElement(driver, choiceOfRelatedUnit, Constants.CLICK_METHOD_ENUM.CLICK);
		else 
			return false;
	}
	
	/**
	 * verify entry instruction is displaying
	 */
	@FindBy(xpath = "//*[@id='note_entry_entry_instruction_key_status_yes']")
	public static WebElement displayOfEntryInstruction;
	
	public boolean verifyDisplayOfPreauthorizedEntryInstruction() {
		SeleniumWrapper.explicitWaitClickable(driver, displayOfEntryInstruction, 30);
		String copy = displayOfEntryInstruction.getText();
		logger.info("Copy is:" +  copy);
		if(displayOfEntryInstruction==null && !copy.contains("This visitor is authorized to use my key."))
			return false;
		else 
			return true;
	}
	

	/**
	 * Pick related Unit(s)
	 */
	public boolean pickRelatedUnit_PreAuthorizedVisitor2() {
		Function.clickElement(driver, relatedUnit);
		SeleniumWrapper.setInputFieldTextNoClear(relatedUnit, "108", driver);
		return Function.clickElement(driver, choiceOfRelatedUnit);
	}

	/**
	 * Input description of allow entry for Pre-Authorized Visitor Note
	 */
	public boolean enterDescOfAllowEntry() {
		Function.clickElement(driver, descBox);
		return SeleniumWrapper.setInputFieldText(descBox, "Allow entry", driver);

	}

	/**
	 * Input description of not allow entry for Pre-Authorized Visitor Note
	 */
	public boolean enterDescriptionOfNotAllowEntry() {
		SeleniumWrapper.explicitWaitClickable(driver, descBox, 30);
		if (Function.clickElement(driver, descBox)) {
		 SeleniumWrapper.setInputFieldText(descBox, "Not allow entry", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
		}
		else
		return false;

	}

	/**
	 * input "Visitor Name"
	 */
	public boolean inputVisitorNameForVisitorNote() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfVisitorName, 30);
		if (Function.clickElement(driver, inputBoxOfVisitorName))
			return SeleniumWrapper.setInputFieldTextNoClear(inputBoxOfVisitorName, "Rita Klyer", driver);
		else
		return false;
	}

	/**
	 * Check checkbox of "Assign Visitor's Parking"
	 */
	public boolean checkAssignVisitorsParkingBox() {
		return Function.clickElement(driver, checkboxOfAssignVisitorsParking);
	}

	/**
	 * Click "Assigned Spot"
	 */
	public boolean clickAssignedSpot() {
		Function.clickElement(driver, assignedSpot);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Search and pick "Assigned Spot"
	 */
	public boolean searchAndPickCarSpot() {
		Function.hoverNclickElement(driver, choiceOfAssignedSpot.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Input car plate
	 */
	public boolean inputCarPlate() {
		Function.clickElement(driver, inputBoxOfCarPlate);
		String plateNum = SeleniumWrapper.generateRandomString(6);
		SeleniumWrapper.setInputFieldTextNoClear(inputBoxOfCarPlate, plateNum, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Pick car Maker
	 */
	public boolean pickCarMaker() {
		Function.clickElement(driver, carMaker);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, searchCarSpot.get(1));
		SeleniumWrapper.setInputFieldText(searchCarSpot.get(1), "Kia", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.hoverNclickElement(driver, choiceOfCarMaker_Kia.get(1));
	}

	/**
	 * Pick Expires time slot
	 */
	public boolean pickExpiresTimeSlot() {
		Function.clickElement(driver, expiresTimeSlot);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		int randomExpiredSlot = SeleniumWrapper.generateRandomInteger(choiceOfExpiresTimeSlot.size() - 1);
		return Function.hoverNclickElement(driver, choiceOfExpiresTimeSlot.get(randomExpiredSlot));
	}

	/**
	 * Check view notes by note types and creator
	 */
	public static final String tableRowsXpath = "//*[@id=\"datatable_all_notes\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> notesTableRows;

	@FindBy(xpath = "//*[@id=\"datatable_all_notes\"]/tbody/tr/td[1]")
	public static List<WebElement> noteTypeColumn;

	@FindBy(xpath = "//*[@id=\"datatable-controls\"]/div[2]/div[1]/button")
	public static WebElement viewNotesBox;

	@FindBy(xpath = "//*[contains(text(),'View All Notes')]")
	public static List<WebElement> linkOfViewAllNotes;

	@FindBy(xpath = "//*[contains(text(), 'View Incident Report')]")
	public static List<WebElement> linkOfViewIncidentReports;

	@FindBy(xpath = "//*[contains(text(), 'View Keys In/Keys Out')]")
	public static List<WebElement> linkOfViewKeysInAndKeysOut;

	@FindBy(xpath = "//*[contains(text(), 'View Notes')]")
	public static List<WebElement> linkOfViewNotes;

	@FindBy(xpath = "//*[contains(text(), 'View Visitor Logs')]")
	public static List<WebElement> linkOfViewVisitorLogs;

	@FindBy(xpath = "//*[contains(text(), 'Tina Lau')]")
	public static List<WebElement> linkOfViewNotesCreatedByTinaLau;
	
	@FindBy(xpath = "//*[contains(text(),'NOTE CREATOR')]")
	public static WebElement noteCreatorSection;
	
	/**
	 * Check view notes by type of "View Incident Reports"
	 */
	public boolean viewNotesByTypeOfViewIncidentReports() {
		Function.clickElement(driver, viewNotesBox);
		Function.clickElement(driver, linkOfViewIncidentReports.get(1));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return TestResultValidator.isFilterResultListed1(tableRowsXpath, driver);
	}

	/**
	 * Check view notes by type of "View Keys In/Keys Out"
	 */
	public boolean viewNotesByTypeOfViewKeysInKeyOut() {
		Function.clickElement(driver, viewNotesBox);
		Function.clickElement(driver, linkOfViewKeysInAndKeysOut.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	/**
	 * Check view notes by type of "View Notes"
	 */
	public boolean viewNotesByTypeOfViewNotes() {
		Function.clickElement(driver, viewNotesBox);
		Function.clickElement(driver, linkOfViewNotes.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	/**
	 * Check view notes by type of "View Visitor Logs"
	 */
	public boolean viewNotesByTypeOfVisitorLogs() {
		Function.clickElement(driver, viewNotesBox);
		Function.clickElement(driver, linkOfViewVisitorLogs.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	/**
	 * Check view notes by creator of"Tina Lau"
	 */
	public boolean viewNotesByCreatorOfTinaLau() {
		Function.clickElement(driver, viewNotesBox);
		SeleniumWrapper.scrollToElement(driver, noteCreatorSection);
		SeleniumWrapper.clickElement(driver, linkOfViewNotesCreatedByTinaLau.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	/**
	 * Check view note details
	 */
	@FindBy(xpath = "//a[@class='view_note']")
	public static List<WebElement> viewDetailsBtn;

	@FindBy(xpath = "//button[contains(text(),'Close')]")
	public static WebElement closeBtn;

	public boolean clickViewDetailsBtn() {
		Function.hoverNclickElement(driver, viewDetailsBtn.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean closeNoteDetailsPage() {
		Function.clickElement(driver, closeBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check edit note
	 */
	@FindBy(xpath = "//*[@id=\"datatable_all_notes\"]/tbody/tr/td[5]/a")
	public static List<WebElement> editIcon;

	@FindBy(id = "description")
	public static WebElement descBox;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public static WebElement saveNoteBtn;

	public boolean clickEditNoteIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(2));
		Function.hoverNclickElement(driver, editIcon.get(2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean editNote() {
		Function.clickElement(driver, descBox);
		String text = descBox.getAttribute("value");
		logger.info("Original text: " + text);
		String modifiedText = text + " + test add note";
		SeleniumWrapper.setInputFieldText(descBox, modifiedText, driver);
		logger.info("Modified text: " + descBox.getAttribute("value"));
		return true;
	}

	public boolean clickSaveNoteBtn() {
		Function.clickElement(driver, saveNoteBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * check delete Visitor note
	 */
	@FindBy(id = "table-action-confirm")
	public static WebElement confirmBtn;

	@FindBy(xpath = "//*[@id=\"datatable_all_notes\"]/tbody/tr/td[6]/a")
	public static List<WebElement> deleteIcon;

	@FindBy(xpath = "//*[@id='datatable_all_notes']/tbody/tr/td[1]/span[2]")
	public static List<WebElement> noteType;

	public WebElement pickVisitorNote() {
		for (int i = 0; i < noteType.size(); i++)
			if (noteType.get(i).getText().toLowerCase().contains("visitor"))
				return deleteIcon.get(i);
		return null;
	}

	public boolean clickDeleteIconOnVisitorNote() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon.get(2));
		SeleniumWrapper.hoverMouseOverElement(driver, pickVisitorNote());
		Function.clickElement(driver, pickVisitorNote());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean clickConfirmBtn() {
		Function.hoverNclickElement(driver, confirmBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * check delete 'Key Out' note
	 */
	public WebElement pickKeyOutNote() {
		for (int i = 0; i < noteType.size(); i++)
			if (noteType.get(i).getText().contains("KEY OUT"))
				return deleteIcon.get(i);
		return null;
	}

	public boolean clickDeleteIconOnKeyOutNote() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon.get(2));
		SeleniumWrapper.hoverMouseOverElement(driver, pickKeyOutNote());
		Function.clickElement(driver, pickKeyOutNote());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check View All Notes
	 */
	@FindBy(id = "calendar-datepicker-view-all")
	public static WebElement calendarIcon;

	@FindBy(xpath = "//*[@class='prev']")
	public static WebElement previousYearMthArrow;

	@FindBy(xpath = "//*[@class='next']")
	public static List<WebElement> nextYearMthArrow;

	/**
	 * Click "View All Notes" button
	 */
	public boolean clickViewAllNotesBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, viewAllNotesbtn, 30);
		Function.hoverNclickElement(driver, viewAllNotesbtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Click calendar icon
	 */
	public boolean clickCalendarIcon() {
		Function.clickElement(driver, calendarIcon);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Pick date Dec. 9, 2019 for view notes
	 */
	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td[3]")
	public static List<WebElement> datepicker;

	public boolean pickDateFromCalendar() {
		Function.hoverNclickElement(driver, datepicker.get(0));
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	/**
	 * Check search function
	 */
	@FindBy(name = "all_search-input")
	public static WebElement searchBox;

	/**
	 * Check search notes by keyword" shift note"
	 */
	public boolean searchNote() {
		String searchKeyword = "Key";
		String searchKeyword1 = " O";
		String searchKeyword2 = "u";
		String searchKeyword3 = "t";
		Function.clickElement(driver, searchBox);
		SeleniumWrapper.setInputFieldText(searchBox, searchKeyword, driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.setInputFieldText(searchBox, searchKeyword + searchKeyword1, driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.setInputFieldText(searchBox, searchKeyword + searchKeyword1 + searchKeyword2, driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.setInputFieldText(searchBox, searchKeyword + searchKeyword1 + searchKeyword2 + searchKeyword3,
				driver);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return TestResultValidator.isSearchedFromElementsList(tableRowsXpath, driver,
				searchKeyword + searchKeyword1 + searchKeyword2 + searchKeyword3);
	}

}
