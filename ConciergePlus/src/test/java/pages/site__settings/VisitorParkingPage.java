package pages.site__settings;

import java.io.File;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;

public class VisitorParkingPage extends SiteAdministrationPage {
	protected final static Logger logger = LogManager.getLogger(VisitorParkingPage.class.getName());

	public VisitorParkingPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Visitor Parking page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoParkingPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (blacklistWhitelistTab.isDisplayed()) {
			return true;
		}
		return false;
	}


	/**
	 * Check user Concierge Clock Out
	 */
	@FindBy(xpath = "//*[@class='display_name']")
	private static WebElement currentLoginUser;

	@FindBy(xpath = "//*[@id='modal-concierge-clockout-button']")
	private static WebElement clockOutBtn;

	/**
	 * Check log in application as resident user and go to Unit tab on his account
	 */
	@FindBy(xpath = "//a[@href = '/myaccount/']")
	public static WebElement myAccountBtn;

	@FindBy(xpath = "//a[@href='#tabs-un-76']")
	public static WebElement unit101Tab;

	@FindBy(xpath = "//*[@id='item-coll-0']/div")
	public static WebElement parkingSpotInfo;
	
	public boolean clickCurrentLoggedInUser() {
		return Function.hoverNclickElement(driver, currentLoginUser);
	}
	

	public boolean gotoMyAccount() {
		Function.hoverNclickElement(driver, currentLoginUser);
		Function.clickElement(driver, myAccountBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean clickUnit101Tab() {
		return Function.clickElement(driver, unit101Tab);
	}
	

	/**
	 * Check resident user view his parking spot info
	 */
	@FindBy(xpath = "//*[@class='icon-magnifier']")
	public static List<WebElement> viewIcon;

	@FindBy(xpath = "//*[@id='modal_profiler_parking_view-footer-buttons']/button")
	public static WebElement closeViewBtn;

	public boolean clickViewIcon() {
		SeleniumWrapper.explicitWaitClickable(driver,  viewIcon.get(0), 50);
		if(viewIcon.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver,  viewIcon.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickCloseBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, closeViewBtn, 30);
		if (closeViewBtn.isDisplayed() && closeViewBtn.isEnabled()) {
			Function.clickElement(driver, closeViewBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check resident user edit his parking spot info
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static List<WebElement> editIcon;

	@FindBy(name = "vehicle_model")
	public static WebElement ModelInputBox;

	@FindBy(xpath = "//button[contains(text(), 'Save')]")
	public static WebElement saveBtn;

	public boolean clickEditIcon() {
		SeleniumWrapper.explicitWaitClickable(driver, editIcon.get(0), 50);
		if (editIcon.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, editIcon.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	public boolean modifyCarModel() {
		SeleniumWrapper.explicitWaitClickable(driver, ModelInputBox, 50);
		logger.info("The origin model is:" + ModelInputBox.getAttribute("value"));
		SeleniumWrapper.clickElement(driver, ModelInputBox, Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.setInputFieldText(ModelInputBox, "Vosvagen", driver);
		String modifyModel = ModelInputBox.getAttribute("value");
		logger.info("The modify model is:" + modifyModel);
			return true;
	
	}

	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		if (saveBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, saveBtn);
			SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
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
	 * Check goto Home menu
	 */
	@FindBy(xpath = " //*[@class='icon-home']")
	public static WebElement homeMenu;

	public boolean gotoHomeMenu() {
		Function.clickElement(driver, homeMenu);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check resident user create visitor parking Pass
	 */
	@FindBy(xpath = " //*[@id='request-parking-pass-button']/span[2]")
	public static WebElement createParkingPassBtn;

	@FindBy(name = "name")
	public static WebElement visitorNameBox;

	@FindBy(id = "plate")
	public static WebElement plateBox;

	@FindBy(xpath = "//*[@class='bs-caret']")
	public static List<WebElement> dropdownArrow;

	@FindBy(xpath = "//*[@id='bs-select-1']/ul/li")
	public static List<WebElement> makeList;

	@FindBy(xpath = "//*[@id='bs-select-2']/ul/li")
	public static List<WebElement> expiresList;

	@FindBy(xpath = " //*[@class='parking-ticket']")
	public static List<WebElement> existingParkingPasses;

	@FindBy(xpath = "//button[contains(text(), 'Create Parking Pass')]")
	public static WebElement saveCreateParkingPassBtn;

	@FindBy(xpath = "//*[@id='modal-cancel-button']")
	public static WebElement cancelBtn;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static WebElement errorMsg; // index 1
	
	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsgList; // index 1

	@FindBy(css = "cr-button.cancel-button")
	public static WebElement cancelPrintBtn;

	public boolean clickCreateParkingPassBtn() {
		String parentWindow = driver.getWindowHandle();
		Function.hoverNclickElement(driver, createParkingPassBtn);
		return Function.switchToNewWindow(driver, parentWindow);
	}
	
	public boolean clickCreateParkingPassBtn1() {
		return Function.hoverNclickElement(driver, createParkingPassBtn);
	}
	
	public boolean clickVisitorNameInputBox() {
		Function.clickElement(driver, visitorNameBox);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean enterVisitorName() {
		SeleniumWrapper.enterName(driver, visitorNameBox);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean clickPlateBox() {
		 Function.clickElement(driver, plateBox);
		 SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		 return true;
	}

	public boolean enterPlate() {
		String plateNum = SeleniumWrapper.generateRandomString(6).toUpperCase();
		SeleniumWrapper.setInputFieldText(plateBox, plateNum, driver);
		logger.info("Plate is:" + plateBox.getAttribute("value"));
		return true;
	}

	public boolean selectCarMaker_Resident() {
		Function.clickElement(driver, dropdownArrow.get(0));
		int randomChoice = SeleniumWrapper.generateRandomInteger(makeList.size() - 1);
		if (Function.hoverNclickElement(driver, makeList.get(randomChoice)))
			return true;
		return false;
	}

	public boolean selectExpiresSlot_Resident() {
		Function.clickElement(driver, dropdownArrow.get(1));
		int randomChoice = SeleniumWrapper.generateRandomInteger(expiresList.size() - 1);
		if (Function.hoverNclickElement(driver, expiresList.get(randomChoice)))
			return true;
		return false;
	}

	public boolean clickSaveCreateParkingPassBtn() throws Exception {
		SeleniumWrapper.explicitWaitClickable(driver, saveCreateParkingPassBtn, 50);
		if (saveCreateParkingPassBtn.isEnabled()) {
			Function.clickElement(driver, saveCreateParkingPassBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean closePrintWindow() throws Exception {
		File file = new File(Constants.closePrintWindow);
		Runtime.getRuntime().exec(file.getAbsolutePath());
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 30);
		if (cancelBtn.isDisplayed() && cancelBtn.isEnabled()) {
			Function.clickElement(driver, cancelBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

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
	 * Check user Concierge can Clock In
	 */
	@FindBy(xpath = "//*[@id=\"modal-concierge-clockin-button\"]")
	public static WebElement clockInBtn;

	public boolean conciergeUserClockIn() {
		if (clockInBtn != null) {
			Function.hoverNclickElement(driver, clockInBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check user Concierge goto Unit Profile
	 */
	@FindBy(xpath = "//*[@id='ss-unit-profile-search']")
	public static WebElement searchUnitBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfUnit;

	@FindBy(xpath = "//*[@class='unit_profile_header_title_container']")
	public static WebElement unitProfileHeader;

	public boolean searchUnit() {
		Function.clickElement(driver, searchUnitBox);
		SeleniumWrapper.setInputFieldText(searchUnitBox, "101", driver);
		Function.clickElement(driver, choiceOfUnit);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean isAtUnitProfile() {
		SeleniumWrapper.explicitWaitClickable(driver, unitProfileHeader, 30);
		String header = unitProfileHeader.getText();
		logger.info("Header is:" + header);
		if(header.equals("Unit 101"))
			return true;
		else
		return false;
	}

	/**
	 * Check user Concierge create Visitor Parking Pass
	 */
	@FindBy(xpath = "//*[@id=\"modal_requestParkingPass-content\"]/form/fieldset/div[4]/div[2]/div/button")
	public static WebElement makeBox;

	@FindBy(xpath = "//*[@id='bs-select-7']/ul/li")
	public static List<WebElement> makeList_Concierge;

	@FindBy(xpath = "//*[@id=\"modal_requestParkingPass-content\"]/form/fieldset/div[5]/div/div/div[2]/div/button")
	public static WebElement expiresBox;

	@FindBy(xpath = "//*[@id='bs-select-8']/ul/li")
	public static List<WebElement> expiresList_Concierge;

	public boolean selectCarMaker_Concierge() {
		Function.clickElement(driver, makeBox);
		int randomChoice = SeleniumWrapper.generateRandomInteger(makeList_Concierge.size() - 1);
		if (Function.hoverNclickElement(driver, makeList_Concierge.get(randomChoice)))
			return true;
		return false;
	}

	public boolean selectExpiresSlot_Concierge() {
		Function.clickElement(driver, expiresBox);
		int randomChoice = SeleniumWrapper.generateRandomInteger(expiresList_Concierge.size() - 1);
		if (Function.hoverNclickElement(driver, expiresList_Concierge.get(randomChoice)))
			return true;
		return false;
	}

	/**
	 * Check user Concierge revoke Visitor Parking Pass
	 */
	@FindBy(xpath = "//*[@id=\"visitor_parking_container\"]/div[1]/h2")
	public static WebElement currentVisitorParkingPassesSection;

	@FindBy(xpath = "//a[@class='simptip-position-top revoke-pass']")
	public static List<WebElement> listOfRevokeBtn;

	@FindBy(xpath = "//*[@id='delete-action-confirm']")
	public static WebElement revokeBtn;

	public boolean revokeVisitorParkingPass() {
		SeleniumWrapper.explicitWaitClickable(driver, currentVisitorParkingPassesSection, 30);
		SeleniumWrapper.scrollToElement(driver, currentVisitorParkingPassesSection);
		SeleniumWrapper.hoverMouseOverElement(driver, listOfRevokeBtn.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, listOfRevokeBtn.get(0), 30);
		Function.hoverNclickElement(driver, listOfRevokeBtn.get(0));
		Function.hoverNclickElement(driver, revokeBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check user Concierge Clock Out
	 */
	public boolean userConciergeclockOut() {
		SeleniumWrapper.explicitWaitClickable(driver, clockOutBtn, 30);
		if (clockOutBtn.isDisplayed() && clockOutBtn.isEnabled())
			return Function.clickElement(driver, clockOutBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check goto Visitor Parking Tab
	 */
	@FindBy(xpath = "//a[@href ='/site_settings/']/span")
	public static List<WebElement> siteAdministrationMenu;

	@FindBy(xpath = "//a[@href='/visitor_parking/']/div/span[2]")
	public static WebElement visitorParkingMenu;

	public boolean gotoVisitorParkingTab() {
		Function.clickElement(driver, siteAdministrationMenu.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.explicitWaitClickable(driver, visitorParkingMenu, 30);
		SeleniumWrapper.scrollToElement(driver, visitorParkingMenu);
		Function.clickElement(driver, visitorParkingMenu);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	
	/**
	 * Check user PM log a visitor from Unit Profile
	 */
	@FindBy(xpath = "//*[contains(text(), 'LOG VISITOR')]")
	public static List<WebElement> logVisitorBtn;
	
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

	public boolean clickLogVisitorBtn(){
		return Function.hoverNclickElement(driver, logVisitorBtn.get(1));
	}
	
	public boolean inputVisitorName() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfVisitorName, 30);
		if(SeleniumWrapper.clickElement(driver, inputBoxOfVisitorName, Constants.CLICK_METHOD_ENUM.CLICK))
		return SeleniumWrapper.enterName(driver, inputBoxOfVisitorName);
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
		Function.hoverNclickElement(driver,choiceOfAssignedSpot.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	
	/**
	 * Input car plate
	 */
	public boolean inputCarPlate() {
		SeleniumWrapper.explicitWaitClickable(driver, inputBoxOfCarPlate, 30);
		if(Function.clickElement(driver, inputBoxOfCarPlate)) {
		String plateNum = SeleniumWrapper.generateRandomString(4);
		SeleniumWrapper.setInputFieldTextNoClear(inputBoxOfCarPlate, plateNum, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
		}
		return false;
	}

	/**
	 * Pick car Maker
	 */
	public boolean pickCarMaker() {
		Function.clickElement(driver, carMaker);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		int randomChoice = SeleniumWrapper.generateRandomInteger(choiceOfCarMaker.size()-1);
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
	 * Check goto Blacklist/Whitelist tab
	 */
	@FindBy(xpath = "//a[contains(text(),'Blacklist/Whitelist')]")
	
	public static WebElement blacklistWhitelistTab;

	public boolean gotoBlacklistAndWhitelistTab() {
		return Function.clickElement(driver, blacklistWhitelistTab);
	}

	/**
	 * Check user PM create Blacklist and Whitelist
	 */
	@FindBy(xpath = "//*[@id='create-access-list']/span[2]")
	public static WebElement createBtn;

	@FindBy(name = "license_plate")
	public static WebElement licensePlateBox;

	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[2]/div[2]/div/button")
	public static WebElement accessListBox;

	@FindBy(id = "bs-select-1-0")
	public static WebElement accessListOfBlacklist;

	@FindBy(id = "bs-select-1-1")
	public static WebElement accessListOfWhitelist;

	@FindBy(xpath = "//*[@id='save_access']/span[2]")
	public static WebElement saveAccessBtn;

	@FindBy(xpath = "//*[@id='cancel']/span[2]")
	public static WebElement cancelAccessBtn;

	@FindBy(xpath = "//*[@id='error_container']/span[2]")
	public static List<WebElement> errorMessage; // index 0

	public boolean clickCreateBtn() {
		return Function.clickElement(driver, createBtn);
	}

	public boolean licensePlateBox() {
		return Function.clickElement(driver, licensePlateBox);
	}

	String licensePlateNum;

	public boolean enterLicensePlate() {
		String licensePlate = SeleniumWrapper.generateRandomString(8).toUpperCase();
		SeleniumWrapper.explicitWaitClickable(driver, licensePlateBox, 30);
		if (Function.clickElement(driver, licensePlateBox))
			SeleniumWrapper.setInputFieldText(licensePlateBox, licensePlate, driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		licensePlateNum = licensePlateBox.getAttribute("value");
		logger.info("The license plate number is:" + licensePlateNum);
		return true;
	}

	public boolean selectWhitelist() {
		Function.clickElement(driver, accessListBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, accessListOfWhitelist);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		String text = accessListBox.getText();
		logger.info("The access is:" + text);
		return true;
	}

	public boolean clickSaveAccessBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveAccessBtn, 30);
		if (saveAccessBtn.isEnabled())
			Function.hoverNclickElement(driver, saveAccessBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if(errorMsgList.get(0).getText().isEmpty())
			return true;
		else
		return false;
	}

	public boolean clickCancelAccessBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelAccessBtn, 30);
		if (cancelAccessBtn.isEnabled()) {
		SeleniumWrapper.clickElement(driver, cancelAccessBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean isErrorMsgOfAccessPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsgList.get(0), 30);
		String errorMessage = errorMsgList.get(0).getText();
		logger.info("The error message is :" + errorMessage);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Assert.assertFalse(errorMessage.isEmpty());
		return true;
	}
	
	/**
	 * Check go back to Visitor Parking tab
	 */
	@FindBy(xpath = "//a[@href='/visitor_parking']")
	public static WebElement visitorParkingTab;
	
	public boolean goBackToVisitorParkingTab() {
		return Function.hoverNclickElement(driver, visitorParkingTab);
	}

	/**
	 * Check search new created license plate
	 */
	@FindBy(id = "access_list_search-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath = ("//*[@id='datatable_access_lists']/tbody/tr");
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> accessListTableRow;

	public boolean searchNewCreatedLicensePlate() {
		return Function.search(driver, searchBox, licensePlateNum, tableRowsXpath);
	}

	/**
	 * Check view access list
	 */
	@FindBy(xpath = "//*[@id='datatable_access_lists']/tbody/tr/td[1]/a")
	public static List<WebElement> columnLicensePlate;

	public boolean viewAccessList() {
		SeleniumWrapper.explicitWaitClickable(driver, columnLicensePlate.get(0), 30);
		String plate = columnLicensePlate.get(0).getText();
		logger.info("the license plate is:" + plate);
		Function.hoverNclickElement(driver, columnLicensePlate.get(0));
		String text = licensePlateBox.getAttribute("value");
		logger.info("the license plate is:" + text);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if(plate.equalsIgnoreCase(text))
			return true;
		else
		return false;
	}

	/**
	 * Check edit access list
	 */
	@FindBy(xpath = "//*[@id='datatable_access_lists']/tbody/tr/td[3]")
	public static List<WebElement> editBtn;

	public WebElement pickBlacklist() {
		for (int i = 0; i < accessListTableRow.size(); i++)
			if (accessListTableRow.get(i).getText().contains("Blacklist"))
				return editBtn.get(i);
		return null;
	}

	public boolean clickEditBtn() {
		SeleniumWrapper.hoverMouseOverElement(driver, editBtn.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, pickBlacklist(), 30);
		Function.hoverNclickElement(driver, pickBlacklist());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check delete access list
	 */
	@FindBy(xpath = "//*[@id='datatable_access_lists']/tbody/tr/td[4]")
	public static List<WebElement> deleteBtn;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmBtn;

	public boolean deleteAccessList() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteBtn.get(0));
		Function.deleteRecord(driver, deleteBtn, 0, confirmBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check search access list
	 */

	public boolean searchAccessList() {
		return Function.search(driver, searchBox, "Blacklist", tableRowsXpath);
	}

}
