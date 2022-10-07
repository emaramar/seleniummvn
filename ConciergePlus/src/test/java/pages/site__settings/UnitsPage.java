package pages.site__settings;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;

public class UnitsPage extends SiteAdministrationPage {
	protected final static Logger logger = LogManager.getLogger(UnitsPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */

	public UnitsPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Units page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoUnitsPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (ownerHeaderColumn.isDisplayed())
			return true;
		return false;
	}

	/**
	 * Check user can click Unit column to view Unit detail
	 */
	@FindBy(xpath = "//*[@id='datatable']/thead/tr/th[2]/span")
	public static WebElement ownerHeaderColumn;

	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[1]/a")
	public static List<WebElement> unitColumn;

	@FindBy(xpath = "//*[@id='create-unit-button']/span[2]")
	public static WebElement saveEditPageBtn;

	@FindBy(xpath = "//*[@id='save-cancel-log']/span[2]")
	public static WebElement cancelBtn;

	public boolean clickColumnUnitToViewUnitInfo() {
		SeleniumWrapper.explicitWaitClickable(driver, unitColumn.get(17), 50);
		SeleniumWrapper.scrollToElement(driver, unitColumn.get(17));
		if (unitColumn.get(17).isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, unitColumn.get(17));
			return SeleniumWrapper.clickElement(driver, unitColumn.get(17), Constants.CLICK_METHOD_ENUM.CLICK);
		}
		return false;
	}

	public boolean isEditUnitPageOpened() {
		SeleniumWrapper.explicitWaitClickable(driver, saveEditPageBtn, 30);
		if(saveEditPageBtn.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 30);
		if (cancelBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, cancelBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check go back to Unit Manager tab
	 */
	@FindBy(xpath = " //a[@href='/unit_manager']")
	public static WebElement unitManagerTab;

	public boolean goBackUnitManagerTab() {
		SeleniumWrapper.explicitWaitClickable(driver, unitManagerTab, 30);
		if (unitManagerTab.isEnabled())
			return SeleniumWrapper.clickElement(driver, unitManagerTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check user can click Owner column to view Unit detail
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[2]/a")
	public static List<WebElement> ownerColumn;

	public boolean clickColumnOwnerToViewUnitInfo() {
		SeleniumWrapper.scrollToElement(driver, ownerColumn.get(17));
		SeleniumWrapper.explicitWaitClickable(driver, ownerColumn.get(17), 50);
		if (ownerColumn.get(17).isEnabled())
			return SeleniumWrapper.clickElement(driver, ownerColumn.get(17), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check user can click Tenant column to view Unit detail
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[3]/a")
	public static List<WebElement> tenantColumn;

	public boolean clickColumnTenantToViewUnitInfo() {
		SeleniumWrapper.scrollToElement(driver, tenantColumn.get(17));
		SeleniumWrapper.explicitWaitClickable(driver, tenantColumn.get(17), 50);
		if (tenantColumn.get(17).isEnabled())
			return SeleniumWrapper.clickElement(driver, tenantColumn.get(17), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check user can click Other column to view Unit detail
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[4]/a")
	public static List<WebElement> otherColumn;

	public boolean clickColumnOtherToViewUnitInfo() {
		SeleniumWrapper.scrollToElement(driver, otherColumn.get(16));
		SeleniumWrapper.explicitWaitClickable(driver, otherColumn.get(16), 50);
		if (otherColumn.get(16).isEnabled())
			return SeleniumWrapper.clickElement(driver, otherColumn.get(17), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check edit unit 209 by deleting unit's all information
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[5]")
	public static List<WebElement> editIcon;

	@FindBy(xpath = "//*[@class='icon-delete']")
	public static List<WebElement> deleteBtnListOnUsersColumn;
	
	@FindBy(xpath = "//*[@class='icon-delete']")
	public static WebElement deleteBtnOnUsersColumn;

	@FindBy(xpath = "//*[@id='delete-action-confirm']")
	public static WebElement confirmDeleteBtn;

	@FindBy(xpath = "//*[@id='table-action-confirm']")
	public static WebElement confirmBtn;

	public boolean clickEditUnit209() {
		SeleniumWrapper.explicitWaitClickable(driver, editIcon.get(17), 100);
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(17));
		return SeleniumWrapper.clickElement(driver, editIcon.get(17), Constants.CLICK_METHOD_ENUM.CLICK);
	}


	public boolean deleteUser() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtnListOnUsersColumn.get(0),50);
		if (deleteBtnListOnUsersColumn.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteBtnListOnUsersColumn.get(0),Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, confirmDeleteBtn);
			SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			return true;
		}
		return false;
	}
	
	
	

	public boolean clickConfirmRemoveAttachmentBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBtn, 50);
		if (confirmBtn.isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, confirmBtn);
			SeleniumWrapper.clickElement(driver, confirmBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check delete Parking spot, bicycle spot and locker spot
	 */
	public boolean deleteParkingAndBicycleAndLocerSpot() {
		Function.clickElement(driver, deleteBtnListOnUsersColumn.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.clickElement(driver, deleteBtnListOnUsersColumn.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.clickElement(driver, deleteBtnOnUsersColumn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}
	
	
	
	/**
	 * Check delete image
	 */
	public boolean deleteImage() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtnListOnUsersColumn.get(deleteBtnListOnUsersColumn.size()-1),50);
		if (deleteBtnListOnUsersColumn.get(deleteBtnListOnUsersColumn.size()-1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteBtnListOnUsersColumn.get(deleteBtnListOnUsersColumn.size()-1),Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			return true;
		}
		return false;
	}
	

	/**
	 * Check click save Edit Unit button
	 */
	public boolean clickSaveEditPageBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveEditPageBtn, 30);
		if (saveEditPageBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveEditPageBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
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
	 * Check edit unit by adding 3 users
	 */
	@FindBy(id = "user-search")
	public static WebElement searchUserBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement selectChoice;

	public boolean clickSearchUserBox() {
		return Function.clickElement(driver, searchUserBox);
	}

	public boolean enterUser1() {
		SeleniumWrapper.setInputFieldText(searchUserBox, "Curtis", driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean enterUser2() {
		 SeleniumWrapper.setInputFieldText(searchUserBox, "Jose", driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean enterUser3() {
		 SeleniumWrapper.setInputFieldText(searchUserBox, "Richard", driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean selectChoice() {
		SeleniumWrapper.explicitWaitClickable(driver, selectChoice, 30);
		if (selectChoice.isEnabled()) {
			SeleniumWrapper.clickElement(driver, selectChoice, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check edit unit by editing user to Tenant and set move-out date
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static List<WebElement> editBtnOnUsersColumn;

	@FindBy(xpath = "//*[@title='Resident Owner']")
	public static WebElement registrationTypeBox;

	@FindBy(xpath = "//*[@id='bs-select-7']/ul/li/a/span")
	public static List<WebElement> choicesOfRegistrationType;

	@FindBy(xpath = "//*[@id='datepicker_moving_in_date1']/span/span")
	public static WebElement calendarIcon;

	@FindBy(xpath = "//*[contains(text(),'Close')]")
	public static List<WebElement> closeUserTypeBtn;
	
	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td")
	public static List<WebElement> datePicker;

	public boolean editUserToTenant() {
		Function.clickElement(driver, editBtnOnUsersColumn.get(1));
		SeleniumWrapper.explicitWaitClickable(driver, registrationTypeBox, 30);
		if (SeleniumWrapper.clickElement(driver, registrationTypeBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.explicitWaitClickable(driver, choicesOfRegistrationType.get(2), 50);
			SeleniumWrapper.clickElement(driver, choicesOfRegistrationType.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean setTenantMoveOutDate() {
		Function.hoverNclickElement(driver, calendarIcon);
		return Function.setDatefromCalendar(driver, 5);
		//return Function.hoverNclickElement(driver, datePicker.get(35));
	}

	/**
	 * Check click Close button
	 */
	public boolean clickCloseBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, closeUserTypeBtn.get(1), 30);
		if (closeUserTypeBtn.get(1).isEnabled()) {
			SeleniumWrapper.clickElement(driver,closeUserTypeBtn.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check is move-in date set?
	 */
	@FindBy(xpath = "//*[@class='pill-box-description']")
	public static List<WebElement> userDesc;

	public boolean clickIsMoveInDateSet() {
		SeleniumWrapper.explicitWaitClickable(driver, userDesc.get(1), 30);
		String moveInDate = userDesc.get(1).getText();
		logger.info("tenant move-in date:" + moveInDate);
		if(moveInDate.contains("Tenant access expires on"))
			return true;
		else
		return false;
	}

	/**
	 * Check edit unit by editing user to Resident Roommate
	 */
	public boolean editUserToResidentRoommate() {
		Function.clickElement(driver, editBtnOnUsersColumn.get(2));
		SeleniumWrapper.explicitWaitClickable(driver, registrationTypeBox, 30);
		if (SeleniumWrapper.clickElement(driver, registrationTypeBox, Constants.CLICK_METHOD_ENUM.CLICK))
			return Function.hoverNclickElement(driver, choicesOfRegistrationType.get(4));
		return false;
	}

	/**
	 * Check edit Unit by adding Parking spot
	 */
	@FindBy(xpath = "//*[@id='parking-search']")
	public static WebElement searchParkingSpotBox;
	
	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfSpots;

	public boolean addParkingSpotOnUnit() {
		Function.clickElement(driver, searchParkingSpotBox);
		SeleniumWrapper.setInputFieldText(searchParkingSpotBox, "P", driver);
		return Function.hoverNclickElement(driver, choiceOfSpots);
	}

	/**
	 * Check edit Unit by adding Bicycle Spot
	 */
	@FindBy(id = "bikes-search")
	public static WebElement bikeSearchBox;

	public boolean addBicycleSpotOnUnit() {
		SeleniumWrapper.explicitWaitClickable(driver, bikeSearchBox, 30);
		if (SeleniumWrapper.clickElement(driver, bikeSearchBox, Constants.CLICK_METHOD_ENUM.CLICK))
			return SeleniumWrapper.setInputFieldText(bikeSearchBox, "B 018", driver);
		return false;
	}

	/**
	 * Check edit Unit by adding Locker
	 */
	@FindBy(id = "lockers-search")
	public static WebElement lockerSearchBox;

	public boolean addLockerOnUnit() {
		SeleniumWrapper.explicitWaitClickable(driver, lockerSearchBox, 30);
		if (SeleniumWrapper.clickElement(driver, lockerSearchBox, Constants.CLICK_METHOD_ENUM.CLICK))
			return SeleniumWrapper.setInputFieldText(lockerSearchBox, "L 005", driver);
		return false;
	}

	/**
	 * Check edit Unit by uploading image
	 */
	@FindBy(xpath = "//*[@id='attachments_select_file']/span[2]")
	public static WebElement attachedFilesBtn;

	public boolean uploadImageOnUnit() throws Exception {
		//Function.clickElement(driver, attachedFilesBtn);
		 Function.uploadFile(driver,attachedFilesBtn, Constants.imgOfDogWalker);
		//File file = new File(Constants.imgOfDogWalker);
		//Runtime.getRuntime().exec(file.getAbsolutePath());
		SeleniumWrapper.threadSleep(70000);
		return true;
	}


	/**
	 * check search unit function
	 */
	@FindBy(id = "unit_search-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath = "//*[@id='datatable']/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> tableRowsOfUnit;

	public boolean searchUnit() {
		String keyword = "105";
		return Function.search(driver, searchBox, keyword, tableRowsXpath);
	}
	
	/**
	 * Check edit icon is clickable
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[5]/a")
	public static List<WebElement> editUnitIcon;
	
	@FindBy(xpath = "//*[@name='title_label']")
	public static WebElement unitNumber;
	
	public boolean clickEditUnitIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, editUnitIcon.get(editUnitIcon.size()-1));
		SeleniumWrapper.explicitWaitClickable(driver, editUnitIcon.get(editUnitIcon.size()-1), 50);
		if(editUnitIcon.get(editUnitIcon.size()-1).isEnabled())
		return SeleniumWrapper.clickElement(driver,editUnitIcon.get(editUnitIcon.size()-1), Constants.CLICK_METHOD_ENUM.CLICK);
		else
		return false;
	}
	
	public boolean isAtEditUnitPage() {
		SeleniumWrapper.explicitWaitClickable(driver, unitNumber, 30);
		String unit = unitNumber.getText();
		logger.info("Unit Title is:" + unit);
		if(unit.contains("Mangament Office"))
			return true;
		else
		return false;
	}
	
}
