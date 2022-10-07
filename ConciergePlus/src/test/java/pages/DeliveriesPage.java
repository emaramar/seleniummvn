package pages;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.github.javafaker.Faker;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import com.utilities.TestResultValidator;
import config.Constants;

public class DeliveriesPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(DeliveriesPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public DeliveriesPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Deliveries page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoDeliveriesPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (closedTab.isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Check Create a new delivery
	 */
	public static String waybill = "WB" + SeleniumWrapper.generateRandomString(3);
	public static String waybill_M = "WBM" + SeleniumWrapper.generateRandomString(4);

	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/div[1]/div[1]/div/div")
	public static WebElement createButton;

	@FindBy(id = "create-delivery")
	public static List<WebElement> createDeliveriesButton;

	@FindBy(xpath = "//*[contains(@name, 'input-waybill')]")
	private static WebElement inputWaybillTextBox;

	@FindBy(xpath = "//*[contains(@name, 'input-waybill')]")
	public static List<WebElement> inputWaybillTextBoxList;

	@FindBy(name = "input-recipient[]")
	public static WebElement inputNameOrUnitNumberBoxForDelivery;

	@FindBy(name = "input-recipient[]")
	public static List<WebElement> inputNameOrUnitNumberBoxListForDelivery;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfNameOrUnitNumberForDelivery;

	@FindBy(xpath = "//div[contains(text(),'Nothing selected')]")
	public static WebElement locationForDelivery;

	@FindBy(id = "bs-select-15-1")
	public static WebElement choiceOfLocationForDelivery;

	@FindBy(xpath = "//*[@title = 'Amazon']/div")
	public static WebElement typeDropdown;//

	@FindBy(xpath = "//div[@id='bs-select-16']/ul/li")
	public static List<WebElement> choiceOfTypeDropdown;

	@FindBy(xpath = "//*[contains(@name, 'input-sender')]")
	public static WebElement inputSenderBox;

	@FindBy(xpath = " //*[@class='inputModal inputModalSender']")
	public static List<WebElement> inputSenderBoxList;

	@FindBy(xpath = "//*[@id=\"add-delivery\"]/span[1]")
	public static WebElement addDeliveryButton;

	@FindBy(xpath = "//*[contains(@id, 'icon-delete')]/span")
	public static List<WebElement> deleteIcon;

	@FindBy(xpath = "//button[contains(.,'Create Delivery')]")
	public static WebElement createDeliveryButton;

	/**
	 * Click Create button
	 */
	public boolean clickCreateButtonToCreateDelivery() {
		return Function.clickElement(driver, createButton);
	}

	/**
	 * Click Create Deliveries button
	 */
	public boolean clickCreateDeliveriesButton() {
		return Function.clickElement(driver, createDeliveriesButton.get(0));
	}

	/**
	 * Input Waybill
	 */
	public boolean inputWayBill() {
		SeleniumWrapper.explicitWaitClickable(driver, inputWaybillTextBox, 30);
		if (SeleniumWrapper.clickElement(driver, inputWaybillTextBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			 SeleniumWrapper.setInputFieldTextNoClear(inputWaybillTextBox, waybill, driver);
		logger.info("input waybill is:" + waybill);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
		}
		return false;
	}

	/**
	 * Input Name/Unit Name
	 */
	public boolean inputNameOrUnitNumberForDelivery() {
		SeleniumWrapper.explicitWaitClickable(driver, inputNameOrUnitNumberBoxForDelivery, 30);
		if (SeleniumWrapper.clickElement(driver, inputNameOrUnitNumberBoxForDelivery, Constants.CLICK_METHOD_ENUM.CLICK))
			return SeleniumWrapper.setInputFieldText(inputNameOrUnitNumberBoxForDelivery, "Susy Cai", driver);
		return false;
	}

	/**
	 * Select Name/Unit Name from dropdown list
	 */
	public boolean selectChoiceForNameOrUnitNumberForDelivery() {
		return Function.clickElement(driver, choiceOfNameOrUnitNumberForDelivery);
	}

	/**
	 * Click Location
	 */
	public boolean clickLocationForDelivery() {
		return Function.clickElement(driver, locationForDelivery);
	}

	/**
	 * Select Location from dropdown list
	 */
	public boolean chooseLocationForDelivery() {
		return Function.hoverNclickElement(driver, choiceOfLocationForDelivery);
	}

	/**
	 * Click Type
	 */
	public boolean clickTypeForDelivery() {
		return Function.clickElement(driver, typeDropdown);
	}

	/**
	 * Select Type from dropdown list
	 */
	public boolean chooseTypeForDelivery() {
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfTypeDropdown.get(0), 30);
		int randomChoiceOfType = SeleniumWrapper.generateRandomInteger(choiceOfTypeDropdown.size() - 1);
		Function.hoverNclickElement(driver, choiceOfTypeDropdown.get(randomChoiceOfType));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Input Sender
	 */
	public boolean inputSenderForDelivery() {
		SeleniumWrapper.explicitWaitClickable(driver, inputSenderBox, 30);
		if(inputSenderBox.isEnabled()) {
		Function.clickElement(driver, inputSenderBox);
		Faker faker = new Faker();
	    String name = faker.name().firstName();
		SeleniumWrapper.setInputFieldText(inputSenderBox, name, driver);
		logger.info("sender is:" + inputSenderBox.getAttribute("value"));
		return true;
		}
		return false;
	}


	/**
	 * Click Add Delivery button
	 */
	public boolean clickAddDeliveryButton() {
		SeleniumWrapper.explicitWaitClickable(driver, addDeliveryButton, 30);
		if (addDeliveryButton.isEnabled()) {
			Function.clickElement(driver, addDeliveryButton);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	/**
	 * Click Delete icon
	 */
	public boolean clickDeleteIcon() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteIcon.get(1), 30);
		if (deleteIcon.get(1).isEnabled()) {
			Function.clickElement(driver, deleteIcon.get(1));
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	/**
	 * Click Create Delivery button
	 */
	public boolean clickCreateDeliveryButton() {
		SeleniumWrapper.explicitWaitClickable(driver, createDeliveryButton, 30);
		if (createDeliveryButton.isEnabled()) {
			Function.clickElement(driver, createDeliveryButton);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
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
		if (confirmMsg.isDisplayed()&& !message.isEmpty())
			return true;
		return false;
	}
	
	
	/**
	 * Create multiple new Deliveries
	 */
	/**
	 * Input another Waybill
	 */
	public boolean inputAnotherWayBill() {
		SeleniumWrapper.explicitWaitClickable(driver, inputWaybillTextBoxList.get(1), 30);
		if (Function.clickElement(driver, inputWaybillTextBoxList.get(1)))
			SeleniumWrapper.setInputFieldText(inputWaybillTextBoxList.get(1), waybill_M, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("input waybill is:" + waybill_M);
		return true;
	}

	/**
	 * Input Another Name/Unit Name
	 */
	public boolean inputAnotherNameOrUnitNumberForDelivery() {
		Function.clickElement(driver, inputNameOrUnitNumberBoxListForDelivery.get(1));
		return SeleniumWrapper.setInputFieldText(inputNameOrUnitNumberBoxListForDelivery.get(1), "Lily", driver);
	}
	
	/**
	 * Input Another Sender
	 */
	public boolean inputAnotherSenderForDelivery() {
		SeleniumWrapper.explicitWaitClickable(driver, inputSenderBoxList.get(1), 30);
		if (inputSenderBoxList.get(1).isEnabled()) {
			Function.clickElement(driver, inputSenderBoxList.get(1));
			Faker faker = new Faker();
			String name = faker.name().firstName();
			SeleniumWrapper.setInputFieldText(inputSenderBoxList.get(1), name, driver);
			logger.info("sender is:" + inputSenderBoxList.get(1).getAttribute("value"));
			return true;
		}
		return false;
	}

	

	/**
	 * Check Create a Pickup
	 */
	public static String pickupItemDesc = "DHL" + SeleniumWrapper.generateRandomString(3);

	@FindBy(xpath = "//*[@id=\"create-outbound-delivery\"]")
	public static List<WebElement> createPickupButton;

	@FindBy(id = "input-item-desc")
	public static WebElement inputItemDescriptionBox;

	@FindBy(id = "input-recipient")
	public static WebElement inputNameOrUnitNumberBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static List<WebElement> choiceOfNameOrUnitNumber;

	@FindBy(xpath = "//*[@class=\"location\"]/div/button")
	public static WebElement locationForPickup;

	@FindBy(xpath = "//*[@id=\"bs-select-9-1\"]")
	public static WebElement choiceOfLocationForPickup;

	@FindBy(id = "input-held-for")
	public static WebElement inputHeldForBox;

	@FindBy(xpath = "//button[contains(.,'Create & Print')]")
	public static WebElement createAndPrintButton;

	/**
	 * Click Create button
	 */
	public boolean clickCreateButtonToCreatePickup() {
		return Function.clickElement(driver, createButton);
	}

	/**
	 * Click Create Pickup button
	 */
	public boolean clickCreatePickupButton() {
		return Function.clickElement(driver, createPickupButton.get(0));
	}

	
	/**
	 * Input Item Description
	 */
	public boolean inputItemDescription() {
		SeleniumWrapper.explicitWaitClickable(driver, inputItemDescriptionBox, 30);
		if (inputItemDescriptionBox.isEnabled()) {
			SeleniumWrapper.clickElement(driver, inputItemDescriptionBox, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.setInputFieldText(inputItemDescriptionBox, pickupItemDesc, driver);
			logger.info("the input item is:" + pickupItemDesc);
			return true;
		}
		return false;
	}
	

	/**
	 * Input Name/Unit Number box
	 */
	public boolean inputNameOrUnitNumberForPickup() {
		Function.clickElement(driver, inputNameOrUnitNumberBox);
		return SeleniumWrapper.setInputFieldText(inputNameOrUnitNumberBox, "Kelvin", driver);
	}

	/**
	 * Select Name from dropdown
	 */
	public boolean selectChoiceForNameOrUnitNumberForPickup() {
		return Function.hoverNclickElement(driver, choiceOfNameOrUnitNumber.get(0));
	}

	/**
	 * Click Location box
	 */
	public boolean clickLocation() {
		return Function.clickElement(driver, locationForPickup);
	}

	/**
	 * Select Location from dropdown
	 */
	public boolean chooseLocation() {
		return Function.hoverNclickElement(driver, choiceOfLocationForPickup);
	}

	/**
	 * Input Held For
	 */
	public boolean inputHeldFor() {
		SeleniumWrapper.explicitWaitClickable(driver, inputHeldForBox, 30);
		if (inputHeldForBox.isEnabled()) {
			SeleniumWrapper.clickElement(driver, inputHeldForBox, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.setInputFieldText(inputHeldForBox, "Kelvin Simon", driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	/**
	 * Click Create & Print button
	 */
	public boolean clickCreateAndPrintButton() {
		SeleniumWrapper.explicitWaitClickable(driver, createAndPrintButton, 30);
		if (createAndPrintButton.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createAndPrintButton, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Close creation page pop-up
	 */
	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public static List<WebElement> cancelBtn;

	public boolean closePopup() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn.get(1), 30);
		return Function.hoverNclickElement(driver, cancelBtn.get(1));
	}

	/**
	 * Check Sorting function on received deliveries/pickups (Current Tab)
	 */

	/**
	 * Check sort per column ID (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/thead/tr/th[1]")
	public static WebElement columnHeaderID;

	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[1]")
	public static List<WebElement> columnID;

	public boolean sortByColumnID() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderID, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderID, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnID);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check sort per column Received (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/thead/tr/th[2]")
	public static WebElement columnHeaderReceived;

	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[2]")
	public static List<WebElement> columnReceived;

	public boolean sortByColumnReceived() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderReceived, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderReceived, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isDateElementsAscendingOrdered(columnReceived);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check sort per column Unit (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/thead/tr/th[3]")
	public static WebElement columnHeaderUnit;

	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[3]")
	public static List<WebElement> columnUnit;

	public boolean sortByColumnUnit() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderUnit, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderUnit, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnUnit);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check sort per column Location (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/thead/tr/th[4]")
	public static WebElement columnHeaderLocation;

	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[4]")
	public static List<WebElement> columnLocation;

	public boolean sortByColumnLocation() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderLocation, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderLocation, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnLocation);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check sort per column Type (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/thead/tr/th[5]")
	public static WebElement columnHeaderType;

	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[5]")
	public static List<WebElement> columnType;

	public boolean sortByColumnType() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderType, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderType, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnType);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check sort per column Recipient (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/thead/tr/th[6]")
	public static WebElement columnHeaderRecipient;

	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[6]")
	public static List<WebElement> columnRecipient;

	public boolean sortByColumnRecipient() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderRecipient, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderRecipient, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnRecipient);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check sort per column Item (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/thead/tr/th[7]")
	public static WebElement columnHeaderItem;

	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[7]")
	public static List<WebElement> columnItem;

	public boolean sortByColumnItem() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderItem, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderItem, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnItem);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check Search function on received delivery/pickup (Current Tab)
	 */
	@FindBy(xpath = "//*[@id=\"open_search-input\"]")
	public static WebElement inputSearchBoxText;

	public static final String tableRowsXpath = "//*[@id=\"datatable_packages\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> tableCurrentReceivedRecords;

	public boolean searchDeliveryByKeyword() {
		String searchKeyword = "DHL";
		return Function.search(driver, inputSearchBoxText, searchKeyword, tableRowsXpath);
	}

	/**
	 * Check View function on received delivery/pickup (Current tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[8]")
	public static List<WebElement> viewIconList;

	@FindBy(xpath = "//*[@id=\"modal_Signature-content-wrapper\"]")
	public static WebElement viewPopup;

	public boolean clickViewIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, viewIconList.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, viewIconList.get(0), 30);
		Function.hoverNclickElement(driver, viewIconList.get(0));
		String itemTobeViewed = columnItem.get(0).getText();
		logger.info("The item to be viewed is : " + itemTobeViewed);
		return true;
	}

	public boolean clickCancelButton() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn.get(2), 30);
		if (cancelBtn.get(2).isEnabled())
			return Function.clickElement(driver, cancelBtn.get(2));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check Edit function on received delivery/pickup (Current tab)
	 */
	@FindBy(xpath = "//*[@id=\"datatable_packages\"]/tbody/tr/td[9]")
	public static List<WebElement> editIconList;

	@FindBy(id = "modal_editDelivery-content-wrapper")
	public static WebElement editPopup;

	@FindBy(id = "edit-notes")
	public static WebElement notesTextBox;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public static WebElement saveEditCopyButton;

	public boolean clickEditIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIconList.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, editIconList.get(0), 30);
		Function.hoverNclickElement(driver, editIconList.get(0));
		String itemTobeEdited = columnItem.get(0).getText();
		logger.info("The item to be edited is : " + itemTobeEdited);
		return true;
	}

	public boolean inputNotes() {
		SeleniumWrapper.explicitWaitClickable(driver, notesTextBox, 30);
		if (Function.clickElement(driver, notesTextBox)) {
			logger.info("Original text :" + notesTextBox.getAttribute("value"));
			SeleniumWrapper.waitForDomToBeRendered(driver);
			String desc = "add text";
			SeleniumWrapper.setInputFieldTextNoClear(notesTextBox, desc, driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			logger.info("Modified text : " + notesTextBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean clickSaveEditCopyButton() {
		Function.clickElement(driver, saveEditCopyButton);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check Pick Up function on received delivery/pickup (Current Tab)
	 */
	@FindBy(xpath = "//*[@id='datatable_packages']/tbody/tr/td[10]")
	public static List<WebElement> pickUpIconList;

	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static WebElement selectedCheckboxList; // from index 4

	@FindBy(id = "modal-save-button")
	public static WebElement saveSignatureAndCloseButton;

	@FindBy(id = "closed_search-input")
	public static WebElement inputSearchBoxText_Closed;

	public static final String tableRowsXpath_Closed = "//*[@id=\"datatable_closed_packages\"]/tbody/tr";
	@FindBy(xpath = tableRowsXpath_Closed)
	public static List<WebElement> table_ClosedRecords;

	String itemToDoPickUp;
	String searchId;

	public boolean clickPickUpIcon() {
		int lastEntryIndex = pickUpIconList.size() - 1;
		SeleniumWrapper.explicitWaitClickable(driver, pickUpIconList.get(lastEntryIndex), 30);
		Function.hoverNclickElement(driver, pickUpIconList.get(lastEntryIndex));
		searchId = columnID.get(lastEntryIndex).getText();
		logger.info("The last record is:" + searchId);
		return true;
	}

	public boolean clickSaveSignatureAndCloseButton() {
		SeleniumWrapper.explicitWaitClickable(driver, saveSignatureAndCloseButton, 30);
		if (saveSignatureAndCloseButton.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveSignatureAndCloseButton, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check delete function on received delivery/pickup (Current Tab)
	 */
	@FindBy(xpath = "//*[@id='datatable_packages']/tbody/tr/td[11]")
	public static List<WebElement> deleteIconList;

	@FindBy(xpath = "//*[@id='modal-confirm']/div/div")
	public static WebElement deletePopup;

	@FindBy(xpath = "//button[contains(.,'Confirm')]")
	public static WebElement ConfirmBtn;

	
	public boolean clickDeleteIcon_Current() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIconList.get(deleteIconList.size()-1));
		SeleniumWrapper.explicitWaitClickable(driver, deleteIconList.get(deleteIconList.size()-1), 30);
		if (deleteIconList.get(deleteIconList.size()-1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteIconList.get(deleteIconList.size()-1), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, ConfirmBtn, 30);
		if (ConfirmBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, ConfirmBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	
	

	/**
	 * Check goto Closed tab
	 */
	@FindBy(xpath = "//a[contains(text(),'Closed')]")
	public static WebElement closedTab;

	public boolean gotoClosedTab() {
		Function.clickElement(driver, closedTab);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}
	
	/**
	 * Check go back Current tab
	 */
	@FindBy(xpath = "//a[contains(text(),'Current')]")
	public static WebElement currentTab;

	public boolean goBackCurrentTab() {
		Function.clickElement(driver,currentTab);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Enter picked up record in search box and check picked up record is moved from
	 * Current tab to Closed tab
	 * 
	 * @throws Exception
	 */
	public boolean searchPickUpItem() {
		return Function.search(driver, inputSearchBoxText_Closed, searchId, tableRowsXpath_Closed);
	}

	/**
	 * check sorting function on closed deliveries/pickups (Closed Tab)
	 */

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/thead/tr/th[1]")
	public static WebElement columnHeaderID_Closed;

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/tbody/tr/td[1]")
	public static List<WebElement> columnID_Closed;

	/**
	 * Check sort per column ID (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean sortByColumnID_Closed() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderID_Closed, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderID_Closed, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnID_Closed);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return false;
	}

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/thead/tr/th[2]")
	public static WebElement columnHeaderReceived_Closed;

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/tbody/tr/td[2]")

	public static List<WebElement> columnReceived_Closed;

	/**
	 * Check sort per column Received (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean sortByColumnReceived_Closed() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderReceived_Closed, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderReceived_Closed, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isDateElementsAscendingOrdered(columnReceived_Closed);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return false;
	}

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/thead/tr/th[3]")
	public static WebElement columnHeaderUnit_Closed;

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/tbody/tr/td[3]")
	public static List<WebElement> columnUnit_Closed;

	/**
	 * Check sort per column Unit (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean sortByColumnUnit_Closed() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderUnit_Closed, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderUnit_Closed, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnUnit_Closed);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return false;
	}

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/thead/tr/th[4]")
	public static WebElement columnHeaderLocation_Closed;

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/tbody/tr/td[4]")
	public static List<WebElement> columnLocation_Closed;

	/**
	 * Check sort per column Location (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean sortByColumnLocation_Closed() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderLocation_Closed, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderLocation_Closed, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnLocation_Closed);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return false;
	}

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/thead/tr/th[5]")
	public static WebElement columnHeaderType_Closed;

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/tbody/tr/td[5]")
	public static List<WebElement> columnType_Closed;

	/**
	 * Check sort per column Type (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean sortByColumnType_Closed() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderType_Closed, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderType_Closed, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnType_Closed);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return false;
	}

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/thead/tr/th[6]")
	public static WebElement columnHeaderRecipient_Closed;

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/tbody/tr/td[6]")
	public static List<WebElement> columnRecipient_Closed;

	/**
	 * Check sort per column Recipient (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean sortByColumnRecipient_Closed() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderRecipient_Closed, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderRecipient_Closed, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnRecipient_Closed);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return false;
	}

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/thead/tr/th[7]")
	public static WebElement columnHeaderItem_Closed;

	@FindBy(xpath = "//*[@id=\"datatable_closed_packages\"]/tbody/tr/td[7]")
	public static List<WebElement> columnItem_Closed;

	/**
	 * Check sort per column Item (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean sortByColumnItem_Closed() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderItem_Closed, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderItem_Closed, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnItem_Closed);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return false;
	}

	/**
	 * Check view function on closed delivery/pickup (Closed tab)
	 */
	@FindBy(xpath = "//*[@id='datatable_closed_packages']/tbody/tr/td[8]/a")
	public static List<WebElement> viewIconList_Closed;

	@FindBy(id = "modal_Signature-content-wrapper")
	public static WebElement viewPopup_Closed;

	@FindBy(xpath = "//*[@id='modal_Signature-footer-buttons']/button[1]")
	public static WebElement cancelButton_Closed;

	public boolean clickViewClosedRecordICon() {
		SeleniumWrapper.hoverMouseOverElement(driver, viewIconList_Closed.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, viewIconList_Closed.get(0), 20);
		Function.hoverNclickElement(driver, viewIconList_Closed.get(0));
		String itemTobeViewed_Closed = columnItem_Closed.get(0).getText();
		logger.info("The item to be viewed is : " + itemTobeViewed_Closed);
		return true;
	}

	public boolean clickCancelButtonOnClosedDelivery() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelButton_Closed, 30);
		if (cancelButton_Closed.isEnabled())
			return Function.clickElement(driver, cancelButton_Closed);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check Search closed Records (Closed Tab)
	 * 
	 * @throws Exception
	 */
	public boolean searchClosedDelivery() {
		String searchByPickup = "105";
		return Function.search(driver, inputSearchBoxText_Closed, searchByPickup, tableRowsXpath_Closed);
	}

	/**
	 * Check "Show Deleted Packages" function (Closed tab)
	 */
	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> showDeletedPackagesCheckbox;

	public boolean showDeletedPacksages() {
		SeleniumWrapper.explicitWaitClickable(driver, showDeletedPackagesCheckbox.get(0), 30);
		if (SeleniumWrapper.clickElement(driver, showDeletedPackagesCheckbox.get(0), Constants.CLICK_METHOD_ENUM.CLICK)) {
		TestResultValidator.isFilterResultContainKeyword(tableRowsXpath_Closed, driver, "DELETED");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
		}
		return false;
	}

}
