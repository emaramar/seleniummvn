package pages.dailyOverview;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.github.javafaker.Faker;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;

public class UnitProfilePage extends DailyOverviewPage {
	protected final static Logger logger = LogManager.getLogger(UnitProfilePage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public UnitProfilePage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info(" Unit Profile Page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoDailyOverviewPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createNotebtn.isEnabled()) {
			return true;
		} else
			return false;
	}

	/**
	 * Navigate to Unit Profile page
	 */
	//@FindBy(xpath = "//button[@id=\"daily-overview-add-note\"]")
	@FindBy(xpath = "//button[@id='daily-overview-add-note']")
	public static WebElement createNotebtn;

	@FindBy(xpath = "//*[@id='ss-unit-profile-search']")
	public static WebElement searchUnitBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfUnit;

	@FindBy(xpath = "//h1[contains(text(),'Unit 101')]")
	public static WebElement unitProfileHeader;

	public boolean searchUnit() {
		SeleniumWrapper.explicitWaitClickable(driver, searchUnitBox, 30);
		SeleniumWrapper.setInputFieldText(searchUnitBox, "101", driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfUnit, 50);
		if (choiceOfUnit.isDisplayed()) {
			SeleniumWrapper.clickElement(driver, choiceOfUnit, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean IsAtUnitProfilePage() {
		SeleniumWrapper.explicitWaitClickable(driver, unitProfileHeader, 50);
		String header = unitProfileHeader.getText();
		logger.info("Header is:" + header);
		if (header.equals("Unit 101"))
			return true;
		else
			return false;
	}

	/**
	 * Check direct user to Edit User page by clicking Edit pencil icon
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static List<WebElement> editIconList;

	@FindBy(xpath = "//*[@class='form_field_header']")
	public static List<WebElement> headerOfUserManagerPage;

	@FindBy(id = "send")
	public static WebElement confirmSaveUserBtn;

	public boolean clickEditIconOnResidentOwner() {
		SeleniumWrapper.explicitWaitClickable(driver, editIconList.get(0), 50);
		if (editIconList.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, editIconList.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean IsUserAtEditUserPage() {
		SeleniumWrapper.explicitWaitClickable(driver, headerOfUserManagerPage.get(0), 80);
		String header = headerOfUserManagerPage.get(0).getText();
		logger.info("header is:" + header);
		if (header.equalsIgnoreCase("Accont Information"))
			return true;
		else
			return false;
	}

	public boolean clickConfirmBtnOnSaveUserPage() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmSaveUserBtn, 30);
		if (confirmSaveUserBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, confirmSaveUserBtn);
			SeleniumWrapper.clickElement(driver, confirmSaveUserBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	/**
	 * Check go back to Daily OVerview menu
	 */
	@FindBy(xpath = "//*[@href='/daily_overview/']/span[2]")
	public static WebElement dailyOverviewMenu;

	public boolean goBackDailyOverviewMenu() {
		SeleniumWrapper.explicitWaitClickable(driver, dailyOverviewMenu, 30);
		if (dailyOverviewMenu.isDisplayed() && dailyOverviewMenu.isEnabled())
			return Function.clickElement(driver, dailyOverviewMenu);
		else
			return false;
	}

	/**
	 * Check edit resident owner's phone number
	 */
	@FindBy(name = "cell_phone")
	public static WebElement cellPhoneInputBox;

	@FindBy(xpath = "//*[@id='user-save-account-button']/span[2]")
	public static WebElement saveEditUserBtn;

	public boolean editPhoneNumber() {
		SeleniumWrapper.explicitWaitClickable(driver, cellPhoneInputBox, 30);
		logger.info("Before phone number:" + cellPhoneInputBox.getAttribute("value"));
		String newPhoneNum = "133 5678 555" + SeleniumWrapper.generateRandomInteger(10);
		if (SeleniumWrapper.clickElement(driver, cellPhoneInputBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.waitForDomToBeRendered(driver);
			SeleniumWrapper.setInputFieldText(cellPhoneInputBox, newPhoneNum, driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			logger.info("After phone number:" + cellPhoneInputBox.getAttribute("value"));
			return true;
		} else
			return false;
	}

	public boolean clickSaveEditUserBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveEditUserBtn, 30);
		if (saveEditUserBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveEditUserBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check click cancel button
	 */
	@FindBy(xpath = "//*[@id=\"user-create-cancel-button\"]/span[2]")
	public static WebElement cancelEditUserBtn;

	public boolean clickCancelUpdateAcctBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelEditUserBtn, 30);
		if (cancelEditUserBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, cancelEditUserBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check is success message present
	 */
	@FindBy(xpath = "//*[@id='success_container']/span[2]")
	public static WebElement confirmMsg;

	@FindBy(xpath = "//*[@id='unit_profile_entry_instructions']/div[1]/h2")
	public static WebElement entryInstructionSection;

	public boolean isSuccessMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmMsg, 30);
		String message = confirmMsg.getText();
		logger.info("message is:" + message);
		if (confirmMsg!=null&& !message.isEmpty())
			return true;
		else
			return false;
	}

	public boolean isSuccessMsgPresent1() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmMsg, 30);
		String message = confirmMsg.getText();
		logger.info("message is:" + message);
		if (message.equalsIgnoreCase("One or more required fields below have not been completed."))
			return true;
		else
			return false;
	}

	/**
	 * Check edit tenant' s email address
	 */
	@FindBy(name = "email")
	public static WebElement emailInputBox;

	public boolean clickEditIconOnTenant() {
		SeleniumWrapper.explicitWaitClickable(driver, editIconList.get(2), 80);
		if (editIconList.get(2).isEnabled())
			return SeleniumWrapper.clickElement(driver, editIconList.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean editEmailAddress() {
		SeleniumWrapper.explicitWaitClickable(driver, emailInputBox, 30);
		logger.info("Before email address:" + emailInputBox.getAttribute("value"));
		Faker faker = new Faker();
		String newEmail = faker.name().username() + "@gmail.com";
		if (SeleniumWrapper.clickElement(driver, emailInputBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.waitForDomToBeRendered(driver);
			SeleniumWrapper.setInputFieldText(emailInputBox, newEmail, driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			logger.info("After email address:" + emailInputBox.getAttribute("value"));
			return true;
		} else
			return false;
	}

	/**
	 * Check user PM log a visitor from Unit Profile
	 */
	@FindBy(xpath = "//*[contains(text(), 'LOG VISITOR')]")
	public static List<WebElement> logVisitorBtn;
	
	/*@FindBy(xpath = "//*[contains(text(), 'LOG VISITOR')]")
	public static WebElement logVisitorBtn;*/
	
	@FindBy(id = "note_entry_visitor_name")
	public static WebElement inputBoxOfVisitorName;

	@FindBy(xpath = "//*[@id='button-is_assign_visitor_parking']/span[1]")
	public static WebElement checkboxOfAssignVisitorsParking;

	@FindBy(xpath = "//*[@id='visitor_parking_form']/div[1]/div[2]/div/button/div")
	public static WebElement assignedSpot;

	@FindBy(xpath = "//*[@id='bs-select-4']/ul/li")
	public static List<WebElement> choiceOfSpot;

	@FindBy(id = "visitor_parking_plate")
	public static WebElement inputBoxOfCarPlate;

	@FindBy(xpath = "//*[@id='visitor_parking_form']/div[3]/div[2]/div/button")
	public static WebElement carMaker;

	@FindBy(xpath = "//*[@id='bs-select-5']/ul/li")
	public static List<WebElement> choiceOfCarMaker;

	@FindBy(xpath = "//div[contains(text(),'1 hour')]")
	public static WebElement expiresTimeSlot;

	@FindBy(xpath = "//*[@id='bs-select-6']/ul/li/a/span")
	public static List<WebElement> choiceOfExpiresTimeSlot;

	public boolean clickLogVisitorBtn() {
		return Function.hoverNclickElement(driver, logVisitorBtn.get(1));
	}

	public boolean inputVisitorName() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfVisitorName, 30);
		if (SeleniumWrapper.clickElement(driver, inputBoxOfVisitorName, Constants.CLICK_METHOD_ENUM.CLICK))
			return SeleniumWrapper.enterName(driver, inputBoxOfVisitorName);
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
	@FindBy(xpath = " //div[@id='bs-select-4']/ul/li/a")
	public static List<WebElement> choiceOfAssignedSpot;

	@FindBy(xpath = " //div[@class=\"bs-searchbox\"]/input")
	public static List<WebElement> searchCarSpot;

	public boolean assignedSpot() {
		Function.clickElement(driver, assignedSpot);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, choiceOfAssignedSpot.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Input car plate
	 */
	public boolean inputCarPlate() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfCarPlate, 30);
		if (Function.clickElement(driver, inputBoxOfCarPlate)) {
			String plateNum = SeleniumWrapper.generateRandomString(4);
			SeleniumWrapper.setInputFieldTextNoClear(inputBoxOfCarPlate, plateNum, driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Pick car Maker
	 */
	public boolean pickCarMaker() {
		Function.clickElement(driver, carMaker);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		int randomChoice = SeleniumWrapper.generateRandomInteger(choiceOfCarMaker.size() - 1);
		return Function.clickElement(driver, choiceOfCarMaker.get(randomChoice));
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
	 * Check click save button on Notes page
	 */
	@FindBy(xpath = "//button[contains(text(), 'Save')]")
	public static WebElement saveBtnOnNotesPage;

	public boolean clickSaveNoteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtnOnNotesPage, 30);
		if (saveBtnOnNotesPage.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, saveBtnOnNotesPage);
			SeleniumWrapper.clickElement(driver, saveBtnOnNotesPage, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check edit unit
	 */
	@FindBy(xpath = "//*[@class='icon-delete']")
	public static List<WebElement> deleteBtnOnUsersColumn;

	@FindBy(xpath = "//*[@id='create-unit-button']/span[2]")
	public static WebElement saveEditPageBtn;

	@FindBy(xpath = "//*[@id='additional_info_panel']/div[1]/div/a")
	public static WebElement editAdditionalDetailsIcon;

	public boolean clickEditIconOnAdditionalDetails() {
		SeleniumWrapper.explicitWaitClickable(driver, editAdditionalDetailsIcon, 50);
		if (editAdditionalDetailsIcon.isEnabled())
			return SeleniumWrapper.clickElement(driver, editAdditionalDetailsIcon, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean deleteParkingSpot() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtnOnUsersColumn.get(deleteBtnOnUsersColumn.size() - 2),
				50);
		if (deleteBtnOnUsersColumn.get(deleteBtnOnUsersColumn.size() - 2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteBtnOnUsersColumn.get(deleteBtnOnUsersColumn.size() - 2),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean clickSaveEditPageBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveEditPageBtn, 30);
		if (saveEditPageBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, saveEditPageBtn);
			SeleniumWrapper.clickElement(driver, saveEditPageBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.threadSleep(10000);
			return true;
		} else
			return false;
	}

	/**
	 * Check create new Entry Instruction
	 */
	@FindBy(xpath = "//*[@id='unit_profile_entry_instructions']/div[1]/div/div/a")
	public static WebElement createEntryInstructionsBtn;

	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[1]/div[1]/label")
	public static WebElement tagOfInstructionType;

	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> radioBtnList;

	@FindBy(name = "name")
	public static WebElement visitorBox;

	@FindBy(xpath = "//*[@id='save-instruction']/span[2]")
	public static WebElement saveEntryInstructionBtn;

	public boolean clickCreateBtnOnEntryInstructions() {
		SeleniumWrapper.explicitWaitClickable(driver, createEntryInstructionsBtn, 50);
		if (createEntryInstructionsBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createEntryInstructionsBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean selectUser() {
		return Function.hoverNclickElement(driver, radioBtnList.get(2));
	}

	public boolean inputVisitorNameForEntryInstruction() {
		Function.clickElement(driver, visitorBox);
		return SeleniumWrapper.enterName(driver, visitorBox);
	}

	public boolean clickSaveEntryInstructionBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveEntryInstructionBtn, 30);
		if (saveEntryInstructionBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, saveEntryInstructionBtn);
			SeleniumWrapper.clickElement(driver, saveEntryInstructionBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check edit Entry Instruction
	 */

	@FindBy(xpath = "//*[@id='unit_profile_entry_instructions']/div[2]/div/section[1]/div")
	public static List<WebElement> listOfEditEntryInstructionsBtn;

	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static List<WebElement> dateCalendarIcon;

	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> expirationDateRadioBtn;

	@FindBy(id = "expiration_date")
	public static WebElement expirationDateInputBox;

	public boolean scrollToEntryInstructionsSection() {
		SeleniumWrapper.explicitWaitClickable(driver, entryInstructionSection, 50);
		return SeleniumWrapper.scrollToElement(driver, entryInstructionSection);
	}

	public boolean clickEditEntryInstructionBtn() {
		SeleniumWrapper.hoverMouseOverElement(driver, listOfEditEntryInstructionsBtn.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, listOfEditEntryInstructionsBtn.get(0), 50);
		return SeleniumWrapper.clickElement(driver, listOfEditEntryInstructionsBtn.get(0),
				Constants.CLICK_METHOD_ENUM.CLICK);
	}

	/**
	 * Check Resident user modify expiration date on entry instruction
	 */
	public boolean modifyExpirationDate() {
		if (!expirationDateRadioBtn.get(1).isSelected())
			Function.hoverNclickElement(driver, expirationDateRadioBtn.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, dateCalendarIcon.get(1));
		Function.setDatefromCalendar(driver, 0);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("New select date :" + expirationDateInputBox.getAttribute("value"));
		return true;
	}

	/**
	 * Check create new Service Request
	 */
	@FindBy(xpath = "//*[@id='unit_profile_service_requests']/div[1]/div/div/a")
	public static WebElement createServiceRequestBtn;

	public boolean clickCreateBtnOnServiceRequest() {
		SeleniumWrapper.explicitWaitClickable(driver, createServiceRequestBtn, 50);
		if (createServiceRequestBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createServiceRequestBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * input subject
	 */
	@FindBy(xpath = "//input[@name ='subject']")
	public static WebElement subjectField;

	public boolean enterSubjectOfServiceRequest() {
		SeleniumWrapper.explicitWaitClickable(driver, subjectField, 30);
		if (SeleniumWrapper.clickElement(driver, subjectField, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldTextNoClear(subjectField, "No light in hallway", driver);
			logger.info("The subject is:" + subjectField.getAttribute("value"));
			return true;
		} else
			return false;
	}

	/**
	 * enter description
	 */
	@FindBy(xpath = "//div[contains(@class, 'fr-wrapper')]")
	public static WebElement textBox;

	@FindBy(xpath = "//div[contains(@class, 'fr-element fr-view')]")
	public static WebElement textBoxEditable;

	public boolean enterDescOfServiceRequest() {
		Function.hoverNclickElement(driver, textBox);
		Function.clickElement(driver, textBoxEditable);
		return SeleniumWrapper.setInputFieldTextNoClear(textBoxEditable, "request", driver);
	}

	/**
	 * Click submit Request button
	 */
	@FindBy(id = "save-request-button")
	public static WebElement submitServiceRequestBtn; // *[@id='save-request-button']

	public boolean clickSubmitServiceRequestBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, submitServiceRequestBtn, 30);
		if (submitServiceRequestBtn.isEnabled()) {
			Function.clickElement(driver, submitServiceRequestBtn);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check edit Service Request
	 */

	@FindBy(xpath = "//*[@id='unit_profile_service_requests']/div[2]/div/section[1]/div/a")
	public static List<WebElement> listOfEditServiceRequestBtn;

	@FindBy(xpath = "//*[@id=\"request_worklog_header_status\"]/div/div[1]/div/button")
	public static WebElement statusBox;

	@FindBy(xpath = " //*[@id=\"bs-select-1\"]/ul/li/a/span")
	public static List<WebElement> choiceOfStatus;

	@FindBy(xpath = "//button[contains(., 'Update Ticket')]")
	public static WebElement updateTicketBtn;

	public boolean clickEditServiceRequest() {
		SeleniumWrapper.hoverMouseOverElement(driver, listOfEditServiceRequestBtn.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, listOfEditServiceRequestBtn.get(0), 50);
		return SeleniumWrapper.clickElement(driver, listOfEditServiceRequestBtn.get(0),
				Constants.CLICK_METHOD_ENUM.CLICK);
	}

	public boolean updateStatusToResolved() {
		Function.clickElement(driver, statusBox);
		return Function.hoverNclickElement(driver, choiceOfStatus.get(2));
	}

	public boolean enterResolvedMsg() {
		SeleniumWrapper.setInputFieldText(textBoxEditable, "Resolved", driver);
		logger.info("The final message is: " + textBoxEditable.getText());
		return true;
	}

	public boolean clickUpdateTicketBtn() {
		Function.hoverNclickElement(driver, updateTicketBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check create new Delivery
	 */
	@FindBy(xpath = "//*[@id='create-container']/span[2]")
	public static WebElement createBtnOnDeliveries;

	@FindBy(id = "create-delivery")
	public static WebElement createDeliveryBtn;

	@FindBy(xpath = "//*[contains(@name, 'input-waybill')]")
	private static WebElement inputWaybillTextBox;

	@FindBy(xpath = "//*[@id='delivery-record-0']/li[3]/div/button")
	public static WebElement nameOrUnitNumberBoxForDelivery;

	@FindBy(xpath = "//*[contains(text(),'Ana  Kale - Tenant')]")
	public static List<WebElement> choiceOfName;

	@FindBy(xpath = "//*[contains(@name, 'input-sender')]")
	public static WebElement inputSenderBox;

	@FindBy(xpath = "//button[contains(.,'Create Delivery')]")
	public static WebElement createDeliveryButton;

	public boolean clickCreateNewDeliveryBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createBtnOnDeliveries, 50);
		if (createBtnOnDeliveries.isEnabled())
			return SeleniumWrapper.clickElement(driver, createBtnOnDeliveries, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean clickCreateDeliveryBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createDeliveryBtn, 50);
		if (createDeliveryBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createDeliveryBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean inputWayBill() {
		String waybill = "WB" + SeleniumWrapper.generateRandomString(3);
		SeleniumWrapper.explicitWaitClickable(driver, inputWaybillTextBox, 30);
		SeleniumWrapper.setInputFieldTextNoClear(inputWaybillTextBox, waybill, driver);
		logger.info("input waybill is:" + waybill);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean selectNameForDelivery() {
		Function.clickElement(driver, nameOrUnitNumberBoxForDelivery);
		return Function.clickElement(driver, choiceOfName.get(0));
	}

	public boolean inputSenderForDelivery() {
		SeleniumWrapper.explicitWaitClickable(driver, inputSenderBox, 30);
		if (inputSenderBox.isEnabled()) {
			Function.clickElement(driver, inputSenderBox);
			Faker faker = new Faker();
			String name = faker.name().firstName();
			SeleniumWrapper.setInputFieldText(inputSenderBox, name, driver);
			logger.info("sender is:" + inputSenderBox.getAttribute("value"));
			return true;
		} else
			return false;
	}

	public boolean clickCreateDeliveryButton() {
		SeleniumWrapper.explicitWaitClickable(driver, createDeliveryButton, 30);
		if (createDeliveryButton.isEnabled()) {
			Function.clickElement(driver, createDeliveryButton);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check create new Pickup
	 */
	@FindBy(id = "create-outbound-delivery")
	public static WebElement createPickupBtn;

	@FindBy(id = "input-item-desc")
	public static WebElement inputItemDescriptionBox;

	@FindBy(xpath = "//*[contains(text(),'Nothing selected')]")
	public static WebElement inputNameOrUnitNumberBox;

	@FindBy(id = "input-held-for")
	public static WebElement inputHeldForBox;

	@FindBy(xpath = "//button[contains(.,'Create & Print')]")
	public static WebElement createAndPrintButton;

	public boolean clickCreatePickupBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createPickupBtn, 50);
		if (createPickupBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createPickupBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean inputItemDescription() {
		String pickupItemDesc = "Fedex" + SeleniumWrapper.generateRandomString(3);
		SeleniumWrapper.explicitWaitClickable(driver, inputItemDescriptionBox, 30);
		if (inputItemDescriptionBox.isEnabled()) {
			SeleniumWrapper.clickElement(driver, inputItemDescriptionBox, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.setInputFieldText(inputItemDescriptionBox, pickupItemDesc, driver);
			logger.info("the input item is:" + pickupItemDesc);
			return true;
		} else
			return false;
	}

	/**
	 * Select Name/Unit Number box
	 */
	public boolean selectNameForPickup() {
		Function.clickElement(driver, inputNameOrUnitNumberBox);
		return Function.clickElement(driver, choiceOfName.get(1));
	}

	public boolean inputHeldFor() {
		SeleniumWrapper.explicitWaitClickable(driver, inputHeldForBox, 30);
		if (inputHeldForBox.isEnabled()) {
			SeleniumWrapper.clickElement(driver, inputHeldForBox, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.setInputFieldText(inputHeldForBox, "Kelvin Simon", driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean clickCreateAndPrintButton() {
		SeleniumWrapper.explicitWaitClickable(driver, createAndPrintButton, 30);
		if (createAndPrintButton.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createAndPrintButton, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check pickup Delivery
	 */
	@FindBy(xpath = "//*[@id='unit_profile_deliveries']/div[1]/h2")
	public static WebElement deliveriesSection;

	@FindBy(xpath = "//a[@class='simptip-position-top edit-notification']")
	public static List<WebElement> pickupBtn;

	@FindBy(id = "modal-save-button")
	public static WebElement saveButton;

	public boolean clickPickupBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, deliveriesSection, 50);
		SeleniumWrapper.scrollToElement(driver, deliveriesSection);
		SeleniumWrapper.hoverMouseOverElement(driver, pickupBtn.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, pickupBtn.get(0), 50);
		if (pickupBtn.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, pickupBtn.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean clickSaveSignatureNCloseButton() {
		SeleniumWrapper.explicitWaitClickable(driver, saveButton, 30);
		if (saveButton.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveButton, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check revoke Parking Pass
	 */
	@FindBy(xpath = "//*[@id='visitor_parking_container']/div[1]/h2")
	public static WebElement currentVisitorParkingPassesSection;

	@FindBy(xpath = "//a[@class='simptip-position-top revoke-pass']")
	public static List<WebElement> listOfRevokeBtn;

	@FindBy(xpath = "//*[@id='delete-action-confirm']")
	public static WebElement revokeBtn;

	public boolean revokeVisitorParkingPass() {
		SeleniumWrapper.explicitWaitClickable(driver, currentVisitorParkingPassesSection, 50);
		SeleniumWrapper.scrollToElement(driver, currentVisitorParkingPassesSection);
		SeleniumWrapper.hoverMouseOverElement(driver, listOfRevokeBtn.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, listOfRevokeBtn.get(0), 50);
		String parentWindow = driver.getWindowHandle();
		Function.clickElement(driver, listOfRevokeBtn.get(0));
		Function.switchToNewWindow(driver, parentWindow);
		SeleniumWrapper.explicitWaitClickable(driver, revokeBtn, 30);
		if (revokeBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, revokeBtn);
			SeleniumWrapper.clickElement(driver, revokeBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check create new pet
	 */
	@FindBy(xpath = "//*[@id='pets_panel']/div[1]/h2")
	public static WebElement petsSection;

	@FindBy(id = "create_pet")
	public static WebElement createPetBtn;

	//@FindBy(id = "user_search_box")
	@FindBy(id = "ss-unit-profile-search")
	public static WebElement searchUnit;

	@FindBy(id = "name")
	public static WebElement petNameBox;

	@FindBy(xpath = "//*[@id='save-pet']/span[2]")
	public static WebElement savePetBtn;

	public boolean clickCreatePetBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, petsSection, 50);
		SeleniumWrapper.scrollToElement(driver, petsSection);
		SeleniumWrapper.explicitWaitClickable(driver, createPetBtn, 50);
		if (createPetBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createPetBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean inputUnit() {
		Function.clickElement(driver, searchUnit);
		SeleniumWrapper.setInputFieldText(searchUnit, "101", driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfUnit, 30);
		if (choiceOfUnit.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, choiceOfUnit);
			SeleniumWrapper.clickElement(driver, choiceOfUnit, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean inputName() {
		SeleniumWrapper.explicitWaitClickable(driver, petNameBox, 30);
		if (SeleniumWrapper.clickElement(driver, petNameBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.enterFirstName(driver, petNameBox);
			logger.info("pet's name is:" + petNameBox.getAttribute("value"));
			return true;
		} else
			return false;
	}

	public boolean clickSaveNewpetBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, savePetBtn, 30);
		if (savePetBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, savePetBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check edit new pet
	 */
	@FindBy(xpath = "//*[@id='pets_panel']/div[2]/div/section/div[2]")
	public static List<WebElement> listOfEditPetBtn;

	@FindBy(xpath = "//*[@id='select_photo']")
	public static WebElement uploadPetPhotoBtn;

	@FindBy(xpath = "//*[@id='picker']/span[2]")
	public static WebElement selectFromMediaLibraryBtn;

	public boolean clickEditIconOnPets() {
		SeleniumWrapper.scrollToElement(driver, petsSection);
		SeleniumWrapper.hoverMouseOverElement(driver, listOfEditPetBtn.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, listOfEditPetBtn.get(0), 80);
		if (listOfEditPetBtn.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, listOfEditPetBtn.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean uploadPetPhoto() throws Exception {
		return Function.uploadFile(driver, uploadPetPhotoBtn, Constants.imgOfDogWalker);
	}

	/**
	 * Check create new key
	 */
	@FindBy(xpath = "//*[@id='keys_management_panel']/div[1]/h2")
	public static WebElement keySection;

	@FindBy(id = "create_key")
	public static WebElement createKeyBtn;

	@FindBy(id = "name")
	public static WebElement inputBoxOfName;

	@FindBy(id = "location")
	public static WebElement inputBoxOfLocation;

	@FindBy(xpath = "//*[contains(text(), 'Save')]")
	public static List<WebElement> saveKeyBtn;

	public boolean scrollToKeysSection() {
		SeleniumWrapper.explicitWaitClickable(driver, keySection, 50);
		return SeleniumWrapper.scrollToElement(driver, keySection);
	}

	public boolean clickCreateKeyBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createKeyBtn, 50);
		if (createKeyBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createKeyBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean inputLocation() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfLocation, 30);
		if (SeleniumWrapper.clickElement(driver, inputBoxOfLocation, Constants.CLICK_METHOD_ENUM.CLICK))
			return SeleniumWrapper.setInputFieldText(inputBoxOfLocation, "Front Desk", driver);
		else
			return false;
	}

	public boolean clickSaveNewKeyBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveKeyBtn.get(1), 50);
		if (saveKeyBtn.get(1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveKeyBtn.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check create sign key out note
	 */
	@FindBy(xpath = "//*[@class='button_icon icon-keys-sign-in']")
	public static List<WebElement> listOfsignKeyOutBtn;

	@FindBy(xpath = "//*[@id='button-is_associate_keys']/span[1]")
	public static WebElement radioBtnOfSignKeyOut;

	public boolean clickSignKeyOutBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfsignKeyOutBtn.get(listOfsignKeyOutBtn.size() - 1), 80);
		if (listOfsignKeyOutBtn.get(listOfsignKeyOutBtn.size() - 1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, listOfsignKeyOutBtn.get(listOfsignKeyOutBtn.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean selectSignKeyOutOption() {
		SeleniumWrapper.explicitWaitClickable(driver, radioBtnOfSignKeyOut, 30);
		if (radioBtnOfSignKeyOut == null) {
			return false;
		} else
			return SeleniumWrapper.clickElement(driver, radioBtnOfSignKeyOut, Constants.CLICK_METHOD_ENUM.CLICK);
	}

	public boolean clickSaveSignKeyOutBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveKeyBtn.get(2), 50);
		if (saveKeyBtn.get(2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveKeyBtn.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check report lost key
	 */
	@FindBy(xpath = "//*[@id='keys_management_panel']/div[2]/div/section/div[3]/div[2]/button/span[2]")
	public static List<WebElement> listOfInNOutKey;

	@FindBy(xpath = "//a[@id='key_report_lost']")
	public static List<WebElement> reportLostBtn;

	@FindBy(id = "send")
	public static WebElement confirmBtn;

	public boolean clickReportLostBtn() {
		Function.clickElement(driver, listOfInNOutKey.get(listOfInNOutKey.size() - 1));
		SeleniumWrapper.explicitWaitClickable(driver, reportLostBtn.get(reportLostBtn.size() - 1), 80);
		if (reportLostBtn.get(reportLostBtn.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, reportLostBtn.get(reportLostBtn.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean clickConfirmBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBtn, 30);
		if (confirmBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, confirmBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check remove key
	 */
	@FindBy(xpath = "//a[@id='key_revoke']")
	public static List<WebElement> removeKeyBtn;

	@FindBy(xpath = "//*[@id='keys_management_panel']/div[2]/div/section/div[3]/div[1]/button/span[2]")
	public static List<WebElement> listOfLostKey;

	public boolean clickRemoveKeyBtn() {
		Function.clickElement(driver, listOfInNOutKey.get(listOfInNOutKey.size() - 1));
		SeleniumWrapper.explicitWaitClickable(driver, removeKeyBtn.get(removeKeyBtn.size() - 1), 80);
		if (removeKeyBtn.get(removeKeyBtn.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, removeKeyBtn.get(removeKeyBtn.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check report found key
	 */
	@FindBy(xpath = "//*[@id='key_report_found']")
	public static List<WebElement> reportFoundBtn;

	public boolean clickReportFoundBtn() {
		Function.clickElement(driver, listOfLostKey.get(listOfLostKey.size() - 1));
		SeleniumWrapper.explicitWaitClickable(driver, reportFoundBtn.get(reportFoundBtn.size() - 1), 80);
		if (reportFoundBtn.get(reportFoundBtn.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, reportFoundBtn.get(reportFoundBtn.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check create new Vacancy Date
	 */
	@FindBy(xpath = "//*[@id='unit_profile_unit_vacancies']/div[1]/h2")
	public static WebElement vacancyDatesSection;

	@FindBy(id = "unit_profile_panel_create_unit_vacancy")
	public static WebElement createVacancyDateBtn;

	@FindBy(xpath = "//*[@id=\"datepicker_form_set_vacancy_dates_1\"]/span/span")
	public static WebElement calendarIcon_Start;

	@FindBy(xpath = "//*[@id=\"datepicker_form_set_vacancy_dates_2\"]/span/span")
	public static WebElement calendarIcon_End;

	@FindBy(id = "form_set_vacancy_dates_start")
	public static WebElement startDateBox;

	@FindBy(id = "form_set_vacancy_dates_end")
	public static WebElement endDateBox;

	public boolean scrollToVacancyDatesSection() {
		SeleniumWrapper.explicitWaitClickable(driver, vacancyDatesSection, 50);
		return SeleniumWrapper.scrollToElement(driver, vacancyDatesSection);
	}

	public boolean clickVacancyDatesBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createVacancyDateBtn, 50);
		if (createVacancyDateBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createVacancyDateBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
		return false;
	}

	public boolean selectStartDate() {
		Function.clickElement(driver, calendarIcon_Start);
		Function.setDatefromCalendar(driver, 0);
		SeleniumWrapper.explicitWaitClickable(driver, startDateBox, 30);
		logger.info("Start date is:" + startDateBox.getAttribute("value"));
		return true;
	}

	public boolean selectEndDate() {
		Function.clickElement(driver, calendarIcon_End);
		Function.setDatefromCalendar(driver, 0);
		SeleniumWrapper.explicitWaitClickable(driver, endDateBox, 30);
		logger.info("End date is:" + endDateBox.getAttribute("value"));
		return true;
	}

	public boolean clickAddVacancyDatesBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveButton, 30);
		if (saveButton.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveButton, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check view Vacancy Date
	 */
	@FindBy(xpath = "//*[@id='unit_profile_unit_vacancies']/div[2]/div/section[1]/div/a[1]")
	public static List<WebElement> viewIcon;

	@FindBy(xpath = "//*[@id='modal_unit_vacancy_view-footer-buttons']/button")
	public static WebElement cancelBtn;

	public boolean clickViewIconOnVacancyDate() {
		SeleniumWrapper.hoverMouseOverElement(driver, viewIcon.get(viewIcon.size() - 1));
		SeleniumWrapper.explicitWaitClickable(driver, viewIcon.get(viewIcon.size() - 1), 50);
		if (viewIcon.get(viewIcon.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, viewIcon.get(viewIcon.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 30);
		if (cancelBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, cancelBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check edit Vacancy Date
	 */
	@FindBy(xpath = "//*[@id='unit_profile_unit_vacancies']/div[2]/div/section[1]/div/a[2]")
	public static List<WebElement> editIcon;

	public boolean clickEditIconOnVacancyDate() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, editIcon.get(0), 50);
		if (editIcon.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, editIcon.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean modifyEndDate() {
		SeleniumWrapper.explicitWaitClickable(driver, endDateBox, 30);
		logger.info("Current end date is:" + endDateBox.getAttribute("value"));
		Function.clickElement(driver, calendarIcon_End);
		Function.setDatefromCalendar(driver, 10);
		SeleniumWrapper.explicitWaitClickable(driver, endDateBox, 30);
		logger.info("End date is:" + endDateBox.getAttribute("value"));
		return true;
	}

	/**
	 * Check create new Amenity Booking
	 */
	@FindBy(xpath = "//*[@id='unit_profile_amenity_booking']/div[1]/h2")
	public static WebElement amenityBookingSection;

	@FindBy(xpath = "//*[@id='unit_profile_amenity_booking']/div[1]/div/div/a")
	public static WebElement createAmenityBookingBtn;

	@FindBy(xpath = "//*[@id='create-amenity-booking']/span[2]")
	public static WebElement btnOfCreateAmenityBooking;

	@FindBy(id = "check-availability")
	public static List<WebElement> checkAvailabilityBtn;

	@FindBy(xpath = "//*[@id='payments_complete_go_back']/span[2]")
	public static WebElement viewCalendarBtn;
	
	@FindBy(xpath = "//button[contains(.,'Submit Request')]")
	public static WebElement submitRequestBtn;
	

	public boolean scrollToAmenityBookingSection() {
		SeleniumWrapper.explicitWaitClickable(driver, amenityBookingSection, 50);
		return SeleniumWrapper.scrollToElement(driver, amenityBookingSection);
	}

	public boolean clickCreateAmenityBookingBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createAmenityBookingBtn, 50);
		if (createAmenityBookingBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, createAmenityBookingBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	public boolean clickCheckAvailabilityBtn_GameRoom() {
		SeleniumWrapper.explicitWaitClickable(driver, checkAvailabilityBtn.get(2), 30);
		if (checkAvailabilityBtn.get(2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, checkAvailabilityBtn.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean clickBtnOfCreateAmenityBooking() {
		SeleniumWrapper.explicitWaitClickable(driver, btnOfCreateAmenityBooking, 30);
		if (btnOfCreateAmenityBooking.isEnabled()) {
			SeleniumWrapper.clickElement(driver, btnOfCreateAmenityBooking, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}
	
	public boolean setDate() {
		Function.clickElement(driver, dateCalendarIcon.get(0));
		return Function.setDatefromCalendar(driver, 0);
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
	 * Check edit Amenity Booking
	 */
	@FindBy(xpath = "//*[@id='unit_profile_amenity_booking']/div[2]/div/section/div/a")
	public static List<WebElement> editAmenityBookingBtn;

	@FindBy(xpath = "//*[@id='pets_panel']/div[2]/div/section/div[2]")
	public static List<WebElement> listOfEditAmenityBookingBtn;

	@FindBy(xpath = "//*[@id='cancel-booking']/span[2]")
	public static WebElement cancelNDeleteBtn;

	@FindBy(xpath = "//*[contains(text(),'Decline & Delete Booking')]")
	public static List<WebElement> declineNDeleteBtn;

	public boolean clickEditAmenityBookingBtn() {
		SeleniumWrapper.hoverMouseOverElement(driver, editAmenityBookingBtn.get(editAmenityBookingBtn.size() - 1));
		SeleniumWrapper.explicitWaitClickable(driver, editAmenityBookingBtn.get(editAmenityBookingBtn.size() - 1), 50);
		if (editAmenityBookingBtn.get(editAmenityBookingBtn.size() - 1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, editAmenityBookingBtn.get(editAmenityBookingBtn.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean clickCancelAndDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelNDeleteBtn, 30);
		if (cancelNDeleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, cancelNDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean clickDeclineAndDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, declineNDeleteBtn.get(2), 30);
		if (declineNDeleteBtn.get(2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, declineNDeleteBtn.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

}
