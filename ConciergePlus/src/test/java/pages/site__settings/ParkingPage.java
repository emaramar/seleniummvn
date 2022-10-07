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

public class ParkingPage extends SiteAdministrationPage {
	protected final static Logger logger = LogManager.getLogger(ParkingPage.class.getName());
	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public ParkingPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Parking page is now ready.");
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
		if (columnHeaderSpot.isDisplayed()) {
			return true;
		}
		return false;
	}

	/**
	 * Check is Parking Spot field mandatory
	 */
	@FindBy(xpath = "//*[@id='datatable_spots']/thead/tr/th[1]/span")
	public static WebElement columnHeaderSpot;

	@FindBy(xpath = " //a[@href='/parking/create']/span[2]")
	public static WebElement createBtn;

	@FindBy(name = "spot_description")
	public static WebElement parkingSpotInputBox;

	@FindBy(xpath = "//*[@id='edit-save-spot']")
	public static WebElement saveBtnOnCreateParkingSpotPage;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsg; // index 0

	public boolean clickCreateBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createBtn, 30);
		if(createBtn.isEnabled())
		return SeleniumWrapper.clickElement(driver, createBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickParkingSpotInputBox() {
		Function.clickElement(driver, parkingSpotInputBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean clickSaveBtnOnCreateParkingSpotPage() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtnOnCreateParkingSpotPage, 30);
		if (saveBtnOnCreateParkingSpotPage.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtnOnCreateParkingSpotPage, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg.get(0), 30);
		String errorMessage = errorMsg.get(0).getText();
		logger.info("The error message is :" + errorMessage);
		if(errorMsg != null)
			return true;
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
		if(confirmMsg.isDisplayed()&& !message.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check go back to Parking tab
	 */
	@FindBy(xpath = "//a[@href='/parking']")
	public static List<WebElement> parkingTab;

	public boolean goBackParkingTab() {
		return Function.clickElement(driver, parkingTab.get(0));
	}

	/**
	 * Check create parking spot
	 */
	@FindBy(id = "related_unit")
	public static WebElement unitSearchBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfUnit;

	@FindBy(id = "vehicle_license_plate")
	public static WebElement licensePlateInputBox;

	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[5]/div[2]/div/button")
	public static WebElement makeBtn;

	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[5]/div[2]/div/div/div[1]/input")
	public static WebElement makeSearchBox;

	@FindBy(xpath = "//*[@id='bs-select-2-1']")
	public static WebElement makeOfAcura;

	@FindBy(xpath = "//*[@id='bs-select-2']/ul/li")
	public static List<WebElement> makeList;

	@FindBy(id = "vehicle_model")
	public static WebElement modelInputBox;

	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[7]/div[2]/div/button")
	public static WebElement colorBox;

	@FindBy(xpath = "//*[@id='bs-select-3']/ul/li")
	public static List<WebElement> colorList;

	//String spot = "P01" + SeleniumWrapper.generateRandomNumericString(1);
	String spot = "P01" + SeleniumWrapper.generateRandomInteger(50);
	

	public boolean enterParkingSpotInfo() {
		return SeleniumWrapper.setInputFieldText(parkingSpotInputBox, spot, driver);
	}

	public boolean enterUnit() {
		return Function.enterNameOrUnit(driver, unitSearchBox, "101", choiceOfUnit);
	}

	String plate = SeleniumWrapper.generateRandomString(6);
	
	public boolean enterLicensePlate() {
		Function.clickElement(driver, licensePlateInputBox);
		return SeleniumWrapper.setInputFieldText(licensePlateInputBox, plate.toUpperCase(), driver);
	}

	public boolean enterModel() {
		Function.clickElement(driver, modelInputBox);
		return SeleniumWrapper.setInputFieldText(modelInputBox, "X5", driver);
	}

	public boolean selectCarMaker() {
		Function.clickElement(driver, makeBtn);
		int randomChoice = SeleniumWrapper.generateRandomInteger(makeList.size() - 1);
		return Function.clickElement(driver, makeList.get(randomChoice));
	}

	public boolean selectColor() {
		Function.clickElement(driver, colorBox);
		int randomChoice = SeleniumWrapper.generateRandomInteger(colorList.size() - 1);
		return Function.clickElement(driver, colorList.get(randomChoice));
	}

	/**
	 * Check search new created parking spot
	 */
	@FindBy(id = "spots_search-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath = ("//*[@id='datatable_spots']/tbody/tr");
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> parkingSpotTableRow;

	public boolean searchParkingSpot() {
		String keyword = "Visitor Parking";
		return Function.search(driver, searchBox, keyword, tableRowsXpath);
	}

	/**
	 * Check click to open parking spot
	 */
	@FindBy(xpath = "//*[@id='datatable_spots']/tbody/tr/td[1]/a")
	public static List<WebElement> columnSpot;

	@FindBy(xpath = "//*[@id='edit-cancel-spot']/span[2]")
	public static WebElement cancelBtn;

	public boolean clickToOpenParkingSpot() throws Exception {
		Function.clickElement(driver, columnSpot.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if (cancelBtn != null) {
			Function.clickElement(driver, cancelBtn);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check edit parking spot
	 */
	@FindBy(xpath = "//*[@id='datatable_spots']/tbody/tr/td[9]")
	public static List<WebElement> editIcon;

	String originColor;
	String updateColor;

	public boolean editParkingSpot() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(0));
		Function.hoverNclickElement(driver, editIcon.get(0));
		originColor = colorBox.getText();
		logger.info("Current color is :" + originColor);
		return true;
	}

	/**
	 * Check update car color
	 */
	public boolean updateCarColor() {
		Function.clickElement(driver, colorBox);
		int randomIndex = SeleniumWrapper.generateRandomInteger(10);
		Function.hoverNclickElement(driver, colorList.get(randomIndex));
		updateColor = colorBox.getText();
		logger.info("New color is :" + updateColor);
		return true;
	}

	/**
	 * Check add rental info on parking spot
	 */
	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> radioBtnOfRentParkingSpot;

	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/form/fieldset/div[9]/div[2]/input")
	public static WebElement unitToRent;

	public boolean clickParkingSpot() {
		Function.clickElement(driver, editIcon.get(0) );
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean editRentalInfoOnParkingSpot() {
		if (radioBtnOfRentParkingSpot.get(0).isDisplayed()) {
			Function.hoverNclickElement(driver, radioBtnOfRentParkingSpot.get(0));
			SeleniumWrapper.setInputFieldText(unitToRent, "103", driver);
			logger.info("Unit to be rented is:" + unitToRent.getAttribute("value"));
			return true;
		}
		return false;
	}

	/**
	 * Check delete new created parking spot
	 */
	@FindBy(xpath = "//*[@id='datatable_spots']/tbody/tr/td[10]")
	public static List<WebElement> deleteIcon;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean deleteParkingSpot() {
		Function.hoverNclickElement(driver, deleteIcon.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 20);
		if (confirmDeleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check edit parking spot from unit profile
	 */
	@FindBy(xpath = "//*[@id='ss-unit-profile-search']")
	public static WebElement searchUnitBox;

	@FindBy(xpath = "//*[@id=\"additional_info_panel\"]/div[1]/div/a/span")
	public static WebElement parkingSpot;
	
	@FindBy(xpath = " //*[@class='icon-delete']")
	public static List<WebElement> deleteIconList;
	
	@FindBy(xpath = " //*[@id='create-unit-button']/span[2]")
	public static WebElement saveBtn;

	public boolean searchUnit() throws Exception {
		Function.clickElement(driver, searchUnitBox);
		SeleniumWrapper.setInputFieldText(searchUnitBox, "101", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.clickElement(driver, choiceOfUnit);
	}

	String originCarMaker;
	String updateCarMaker;

	public boolean clickParkingSpotFromUnitProfile() throws Exception {
		SeleniumWrapper.explicitWaitClickable(driver, parkingSpot, 30);
		SeleniumWrapper.scrollToElement(driver, parkingSpot);
		if (parkingSpot.isEnabled()) {
			SeleniumWrapper.clickElement(driver, parkingSpot, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	

	public boolean deleteParkingSpotFromUnitProfile() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteIconList.get(deleteIconList.size()-2), 50);
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIconList.get(deleteIconList.size()-2));
		if (deleteIconList.get(deleteIconList.size()-2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteIconList.get(deleteIconList.size()-2),
					Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		}
		return false;
	}
	
	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		if (saveBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	

}
