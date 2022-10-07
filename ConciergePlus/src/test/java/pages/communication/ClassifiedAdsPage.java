package pages.communication;

import java.util.ArrayList;
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
import pages.BasePage;

public class ClassifiedAdsPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(ClassifiedAdsPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public ClassifiedAdsPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Classified Ads page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoClassifiedAdsPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createAdBtn.isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Check navigate to Classified Ad menu
	 */
	@FindBy(xpath = "//*[contains(.,'Communication')]/span[3]")
	public static WebElement communicationTab;

	@FindBy(xpath = "//a[@href='/classified_ads']/span[2]")
	public static WebElement classifiedAdsMenu;

	public boolean navigateToClassifiedAdsMenu() {
		Function.clickElement(driver, communicationTab);
		return Function.clickElement(driver, classifiedAdsMenu);
	}
	
	
	/**
	 * Check goto Homepage
	 */
	@FindBy(xpath = "//a[@href='/dashboard']/span[2]")
	public static WebElement homeMenu;
	
	@FindBy(xpath = "//h1[@class='theme-color-1']")
	public static List<WebElement> widgetHeader;

	public boolean gotoHomepage() {
		Function.clickElement(driver, homeMenu);
		SeleniumWrapper.explicitWaitClickable(driver, widgetHeader.get(2), 30);
		String header = widgetHeader.get(2).getText();
		logger.info("The header is:" + header);
		if (header.equals("Classified Ads"))
			return true;
		return false;
	}

	/**
	 * Test of is approved Ad present on Homepage
	 */
	@FindBy(xpath = "//*[@id='container_1']")
	public static List<WebElement> classifiedAdsOnHomepage;

	public boolean isApprovedAdPresentOnHomepage() {
		SeleniumWrapper.explicitWaitClickable(driver, classifiedAdsOnHomepage.get(0), 30);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < classifiedAdsOnHomepage.size(); i++) {
			list.add(classifiedAdsOnHomepage.get(i).getText());
		}
		logger.info("The classified ads are:" + list);
		if (list.contains("Used Honda Civi for sell"))
			return true;
		return false;
	}

	/**
	 * Check is approved ad present on User Resident's account
	 */
	@FindBy(xpath = "//a[@class='listview_tab']")
	public static WebElement listViewTab;

	@FindBy(xpath = "//*[@id='datatable_ads']/thead/tr/th[2]/span")
	public static WebElement columnHeaderTitle;

	public static final String tableRowsXpath_ThumbnailViewTab = "//*[@class='filename_t']";
	@FindBy(xpath = tableRowsXpath_ThumbnailViewTab)
	public static List<WebElement> tableRowsOfAds_ThumbnailViewTab;

	public boolean isApprovedAdPresentOnWebTable() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderTitle, 30);
		String keyword1 = vehicleForSell;
		String keyword2 = "Used Honda Civi for...";

		if (columnHeaderTitle.isDisplayed()) {
			Function.clickElement(driver, searchBox);
			SeleniumWrapper.setInputFieldText(searchBox, keyword1, driver);
			return TestResultValidator.isSearchedFromElementsList(tableRowsXpath, driver, keyword1);
		} else if (!columnHeaderTitle.isDisplayed()){
			Function.clickElement(driver, searchBox);
			SeleniumWrapper.setInputFieldText(searchBox, keyword2, driver);
		   return TestResultValidator.isSearchedFromElementsList(tableRowsXpath_ThumbnailViewTab, driver, keyword2);
		}
		return false;
	}
	
	
	/**
	 * Check resident user open ad
	 */
	public boolean openAd() {
		return Function.hoverNclickElement(driver, linkOfColumnTitle.get(0) );
	}


	/**
	 * Check is "Title" field and "Message" field are mandatory
	 */
	@FindBy(xpath = "//*[@id='ad-create-button']/span[2]")
	public static WebElement createAdBtn;

	@FindBy(xpath = " //*[@class ='btn dropdown-toggle btn-default']")
	public static WebElement categoryBox;

	@FindBy(xpath = "//*[@id='bs-select-1']/ul/li")
	public static List<WebElement> choiceOfCategory;

	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> listOfRadioBtn;

	@FindBy(id = "name")
	public static WebElement titleInputBox;

	@FindBy(id = "price")
	public static WebElement priceInputbox;

	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static WebElement calendarIcon;

	@FindBy(id = "description")
	public static WebElement messageInputBox;

	@FindBy(xpath = "//*[@id='attachments_select_file']/span[2]")
	public static WebElement uploadFilesBtn;

	@FindBy(xpath = "//*[@id='save-ad']/span[2]")
	public static WebElement saveAdBtn;

	@FindBy(xpath = "//*[@id='cancel-ad']/span[2]")
	public static WebElement cancelAdBtn;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static WebElement errorMsg;

	public boolean clickCreateAdBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createAdBtn, 30);
		if (createAdBtn.isDisplayed() && createAdBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createAdBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	String vehicleForSell = "Used Honda Civi for sell";

	public boolean enterTitleOfvehicleForSell() {
		if (Function.clickElement(driver, titleInputBox)) {
			SeleniumWrapper.setInputFieldText(titleInputBox, vehicleForSell, driver);
			logger.info("Title:" + titleInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean enterMessage() {
		if (Function.clickElement(driver, messageInputBox)) {
			SeleniumWrapper.setInputFieldText(messageInputBox, "This's an ad!", driver);
			logger.info("Message:" + messageInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean clickSaveAdBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveAdBtn, 30);
		if (saveAdBtn.isDisplayed() && saveAdBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveAdBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelAdBtn, 30);
		if (cancelAdBtn.isDisplayed() && cancelAdBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, cancelAdBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg, 30);
		String errorMessage = errorMsg.getText();
		logger.info("The error message is :" + errorMessage);
		if (errorMsg != null) {
			return true;
		}
		return false;
	}

	/**
	 * Check go back to Classified Ads tab
	 */
	@FindBy(xpath = "//a[@href='/classified_ads']")
	public static List<WebElement> classifiedAdsTab; 

	public boolean goBackToClassifiedAdsTab() {
		return Function.hoverNclickElement(driver, classifiedAdsTab.get(1));
	}

	/**
	 * Check create new ad
	 */
	@FindBy(xpath = "//*[@class='datepicker-days']/table/tbody/tr/td")
	public static List<WebElement> datePicker;
	 
	public boolean selectCategoryOfVehicle() {
		Function.clickElement(driver, categoryBox);
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfCategory.get(1), 30);
		if (choiceOfCategory.get(1).isDisplayed())
			return SeleniumWrapper.clickElement(driver, choiceOfCategory.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean enterPrice() {
		SeleniumWrapper.explicitWaitClickable(driver, priceInputbox, 30);
		if (SeleniumWrapper.clickElement(driver, priceInputbox, Constants.CLICK_METHOD_ENUM.CLICK))
			return SeleniumWrapper.setInputFieldText(priceInputbox, "5000", driver);
		return false;
	}

	public boolean setAdExpiryDate() {
		Function.clickElement(driver, calendarIcon);
		int randomChoice = SeleniumWrapper.generateRandomInteger(50);
		Function.hoverNclickElement(driver, datePicker.get(randomChoice));
		logger.info("Pick date of :" + expirationDateBox.getAttribute("value"));
		return true;
	}

	public boolean uploadFile() throws Exception {
		SeleniumWrapper.explicitWaitClickable(driver, uploadFilesBtn, 30);
		if (uploadFilesBtn.isEnabled())
			return Function.uploadFile(driver, uploadFilesBtn, Constants.imgOfCarKey);
		return false;
	}

	/**
	 * Check user Resident create new ad of "Buy & Sell Goods"
	 */
	public boolean selectTypeOfBuying() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfRadioBtn.get(0), 30);
		if (!listOfRadioBtn.get(0).isSelected())
			return SeleniumWrapper.clickElement(driver, listOfRadioBtn.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	String buyUsedStroller = "Look for used Stroller";

	public boolean enterTitleOfBuyUsedStroller() {
		if (Function.clickElement(driver, titleInputBox)) {
			SeleniumWrapper.setInputFieldText(titleInputBox, buyUsedStroller, driver);
			logger.info("Title:" + titleInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean selectPriceOfFree() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfRadioBtn.get(4), 30);
		if (!listOfRadioBtn.get(4).isSelected())
			return Function.hoverNclickElement(driver, listOfRadioBtn.get(4));
		return false;
	}
	
	//**********************************************************************************************************************

	/**
	 * Check PM verify the status of the ad created by user Resident
	 */
	@FindBy(xpath = "//*[@id='datatable_ads']/tbody/tr/td[2]")
	public static List<WebElement> columnTitle;

	public static final String tableRowsXpath = "//*[@id='datatable_ads']/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> tableRowsOfAds;

	public WebElement columnTitleOfBuyingAd() {
		for (int i = 0; i < tableRowsOfAds.size(); i++)
			if (tableRowsOfAds.get(i).getText().contains("Look for used Stroller"))
				return columnTitle.get(i);
		return null;
	}

	public boolean isStatusPendingApproval() {
		SeleniumWrapper.explicitWaitClickable(driver, columnTitleOfBuyingAd(), 50);
		String titleOfColumn = columnTitleOfBuyingAd().getText();
		logger.info("title and status of the Buying ad is:" + titleOfColumn);
		if (titleOfColumn.contains("PENDING APPROVAL"))
			return true;
		return false;
	}

	/**
	 * Check price of the ad (Look for used Stroller) created by user Resident
	 */
	@FindBy(xpath = "//*[@id='datatable_ads']/tbody/tr/td[2]/a")
	public static List<WebElement> linkOfColumnTitle;
	
	@FindBy(xpath = " //a[contains(text(),'Look for used Stroller')]")
	public static WebElement adOfBuying;

	@FindBy(xpath = "//*[@class='summary_title']")
	public static WebElement titleofAd;

	public boolean openNewAdOfBuying() {
		return Function.hoverNclickElement(driver, adOfBuying);
	}

	public boolean verifyTitleAndPriceOfNewAd_Buying() {
		SeleniumWrapper.explicitWaitClickable(driver, titleofAd, 30);
		String adOfBuying = titleofAd.getText();
		logger.info("title and price of the Buying ad is:" + adOfBuying);
		if (adOfBuying.contains("Free"))
			return true;
		return false;
	}

	/**
	 * Check price of the ad (Used Honda Civi for sell) created by user Resident
	 */
	@FindBy(xpath = " //a[contains(text(),'Used Honda Civi for sell')]")
	public static WebElement adOfSelling;

	public boolean openNewAdOfSelling() {
		return Function.hoverNclickElement(driver, adOfSelling);
	}

	public boolean verifyTitleAndPriceOfNewAd_Selling() {
		SeleniumWrapper.explicitWaitClickable(driver, titleofAd, 30);
		String adOfSelling = titleofAd.getText();
		logger.info("title and price of the Selling ad is:" + adOfSelling);
		if (adOfSelling.contains("$5,000"))
			return true;
		return false;
	}

	/**
	 * Check user PM approve new ad
	 */
	@FindBy(xpath = "//button[contains(., 'Approve Ad')]/span[2]")
	public static WebElement approveAdBtn;

	@FindBy(id = "confirm")
	public static WebElement confirmBtn;

	public boolean clickApproveAdBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, approveAdBtn, 30);
		if (approveAdBtn.isDisplayed() && approveAdBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, approveAdBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickConfirmBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBtn, 30);
		if (confirmBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, confirmBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return false;
	}

	/**
	 * Check user PM deny & delete new ad
	 */
	@FindBy(xpath = "//*[@id='decline-ad']/span[2]")
	public static WebElement denyNDeleteBtn;

	@FindBy(id = "modal_ad_decline_note")
	public static WebElement denyReasonInputBox;

	@FindBy(id = "modal-save-button")
	public static WebElement confirmDenyBDeleteBtn;

	public boolean clickDenyAndDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, denyNDeleteBtn, 30);
		if (denyNDeleteBtn.isDisplayed() && denyNDeleteBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, denyNDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean enterDenyMsg() {
		if (Function.clickElement(driver, denyReasonInputBox)) {
			SeleniumWrapper.setInputFieldText(denyReasonInputBox, "duplicate ad", driver);
			logger.info("message is:" + denyReasonInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean clickConfirmDenyNDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDenyBDeleteBtn, 30);
		if (confirmDenyBDeleteBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, confirmDenyBDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check user PM reply an ad
	 */
	@FindBy(xpath = "//*[@id='email_body']")
	public static WebElement replyInputBox;

	@FindBy(id = "email-ad")
	public static WebElement sendEmailBtn;

	public boolean enterReplyMsg() {
		SeleniumWrapper.explicitWaitClickable(driver, replyInputBox, 30);
		SeleniumWrapper.setInputFieldText(replyInputBox, "Still available", driver);
		logger.info("reply message:" + replyInputBox.getAttribute("value"));
		return true;
	}

	public boolean clickSendEmailBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, sendEmailBtn, 30);
		if (sendEmailBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, sendEmailBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check user PM create new ad of "house for rent"
	 */
	@FindBy(id = "ad_user_search")
	public static WebElement searchUnitBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement selectUnit;

	public boolean enterUnit() {
		return Function.enterNameOrUnit(driver, searchUnitBox, "108", selectUnit);
	}

	public boolean selectCategoryOfHousing() {
		Function.clickElement(driver, categoryBox);
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfCategory.get(2), 30);
		if (choiceOfCategory.get(2).isDisplayed())
			return SeleniumWrapper.clickElement(driver, choiceOfCategory.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	String houseForRent = "3 bedrooms condo for rent";

	public boolean enterTitleOfHouseForRent() {
		if (Function.clickElement(driver, titleInputBox)) {
			SeleniumWrapper.setInputFieldText(titleInputBox, houseForRent, driver);
			logger.info("Title:" + titleInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean selectTypeOfRenting() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfRadioBtn.get(2), 30);
		if (!listOfRadioBtn.get(2).isSelected())
			return SeleniumWrapper.clickElement(driver, listOfRadioBtn.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check user PM create new ad of "Hire nanny"
	 */
	public boolean selectCategoryOfServices() {
		Function.clickElement(driver, categoryBox);
		SeleniumWrapper.explicitWaitClickable(driver, choiceOfCategory.get(3), 30);
		if (choiceOfCategory.get(3).isDisplayed())
			return SeleniumWrapper.clickElement(driver, choiceOfCategory.get(3), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	String serviceForHire = "Hire nanny";

	public boolean enterTitleOfServiceForHire() {
		if (Function.clickElement(driver, titleInputBox)) {
			SeleniumWrapper.setInputFieldText(titleInputBox, serviceForHire, driver);
			logger.info("Title:" + titleInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean selectPriceOfPleaseContact() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfRadioBtn.get(5), 30);
		if (!listOfRadioBtn.get(5).isSelected())
			return Function.hoverNclickElement(driver, listOfRadioBtn.get(5));
		return false;
	}

	/**
	 * Check is the new created ad present on web table
	 */
	@FindBy(id = "search-input")
	public static WebElement searchBox;
	
	public boolean isNewCreatedAdPresentOnWebTable() {
		String keyword = "Hire nanny";
		return Function.search(driver, searchBox, keyword, tableRowsXpath);
	}

	/**
	 * Check display ads by category
	 */
	@FindBy(xpath = "//*[@id='classified-controls']/div[1]/div[2]/div/div/div/button")
	public static WebElement displayAllCategoriesBox;

	public boolean clickDisplayAllCateoriesBox() {
		return Function.clickElement(driver, displayAllCategoriesBox);
	}

	public boolean displayAdsByCategoryofBuyNSellGoods() {
		Function.hoverNclickElement(driver, choiceOfCategory.get(1));
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	public boolean displayAdsByCategoryofVehicles() {
		Function.hoverNclickElement(driver, choiceOfCategory.get(2));
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	public boolean displayAdsByCategoryofHousing() {
		Function.hoverNclickElement(driver, choiceOfCategory.get(3));
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	public boolean displayAdsByCategoryofServices() {
		Function.hoverNclickElement(driver, choiceOfCategory.get(4));
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

	/**
	 * Check search ad from List View tab
	 */
	public boolean searchAdFromListViewTab() {
		String keyword = "nanny";
		return Function.search(driver, searchBox, keyword, tableRowsXpath);
	}

	/**
	 * Check edit ad by clicking pencil icon from List View tab
	 */
	@FindBy(xpath = "//*[@id='datatable_ads']/tbody/tr/td[6]")
	public static List<WebElement> editIcon;

	public boolean clickPencilIconFromListViewTab() {
		return Function.hoverNclickElement(driver, editIcon.get(0));
	}

	public boolean modifyMessage() {
		SeleniumWrapper.explicitWaitClickable(driver, messageInputBox, 30);
		logger.info("Original Message:" + messageInputBox.getAttribute("value"));
		if (SeleniumWrapper.clickElement(driver, messageInputBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(messageInputBox, "This's an approved ad", driver);
			logger.info("Modify Message:" + messageInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	/**
	 * Check delete ad by clicking trash icon from List View tab
	 */
	@FindBy(xpath = "//*[@id='datatable_ads']/tbody/tr/td[7]")
	public static List<WebElement> deleteIcon;

	@FindBy(id = "delete-action-confirm")
	public static WebElement confirmDeleteBtn;
	
	public boolean deleteAdByClickingTrashIconFromListViewTab() {
		Function.hoverNclickElement(driver, deleteIcon.get(0));
		Function.hoverNclickElement(driver, confirmDeleteBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check user PM click any column to open ad and edit ad from List View tab
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static WebElement editBtn;

	@FindBy(id = "expiration_date")
	public static WebElement expirationDateBox;
	
	public WebElement adOfHousingForRent() {
		for (int i = 0; i < tableRowsOfAds.size(); i++)
			if (tableRowsOfAds.get(i).getText().contains("3 bedrooms condo for rent"))
				return linkOfColumnTitle.get(i);
		return null;
	}

	public boolean clickColumnToOpenAd() {
		SeleniumWrapper.explicitWaitClickable(driver, adOfHousingForRent(), 60);
		return SeleniumWrapper.clickElement(driver, adOfHousingForRent(), Constants.CLICK_METHOD_ENUM.CLICK);
	}

	public boolean clickEditBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, editBtn, 30);
		if (editBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, editBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean modifyAdExpiryDate() {
		SeleniumWrapper.explicitWaitClickable(driver, expirationDateBox, 30);
		String originExpiryDate = expirationDateBox.getAttribute("value");
		logger.info("Original expiry date:" + originExpiryDate);
		Function.clickElement(driver, calendarIcon);
		Function.setDatefromCalendar(driver, 3);
		String modifyExpiryDate = expirationDateBox.getAttribute("value");
		logger.info("Modify expiry date:" + modifyExpiryDate);
		if (!modifyExpiryDate.equals(originExpiryDate))
			return true;
		return false;
	}

	/**
	 * Check user PM click any column to open ad and delete ad from List View tab
	 */
	@FindBy(xpath = "//*[@class='icon-delete']")
	public static WebElement deleteBtn;

	public boolean clickDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtn, 30);
		if (deleteBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
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
	 * Check goto Thumbnails View tab
	 */
	@FindBy(xpath = "//*[@class='icon-thumbnails']")
	public static WebElement thumbnailsTab;

	public boolean gotoThumbnailsTab() {
		return Function.clickElement(driver, thumbnailsTab);
	}
	
	/**
	 * Check go back List View tab
	 */
	@FindBy(xpath = "//*[@class='icon-triple-dash']")
	public static WebElement listviewTab;

	public boolean goBackListviewTab() {
		return Function.clickElement(driver, listviewTab);
	}

	/**
	 * Check view ad from Thumbnails View tab
	 */
	@FindBy(xpath = "//div[@class='file_container']")
	public static List<WebElement> fileList;

	@FindBy(xpath = "//*[@class='icon-magnifier']")
	public static List<WebElement> viewIcon; // index 3

	public boolean hoverMouseOverAd() {
		SeleniumWrapper.explicitWaitClickable(driver, fileList.get(0), 30);
		SeleniumWrapper.hoverMouseOverElement(driver, fileList.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean clickViewBtnFromThumbnailsViewTab() {
		return Function.hoverNclickElement(driver, viewIcon.get(2));
	}

	public boolean verifyTitleAndPriceOfAd() {
		SeleniumWrapper.explicitWaitClickable(driver, titleofAd, 30);
		String text = titleofAd.getText();
		logger.info("title and price of the ad is:" + text);
		if (!text.isEmpty())
			return true;
		return false;
	}

	/**
	 * Check is ad title clickable from Thumbnails View tab
	 */
	@FindBy(xpath = "//*[@class='filename_t']")
	public static List<WebElement> adTitleList;

	public boolean isAdTitleClickableFromThumbnailsViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, adTitleList.get(0), 30);
		logger.info("Ad title is:" + adTitleList.get(0).getText());
		SeleniumWrapper.hoverMouseOverElement(driver, adTitleList.get(0));
		return SeleniumWrapper.clickElement(driver, adTitleList.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
	}

	/**
	 * Check edit ad from Thumbnails View tab
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static List<WebElement> editBtnList;
	
	public boolean clickEditBtnFromThumbnailsViewTab() {
		return Function.hoverNclickElement(driver, editBtnList.get(editBtnList.size()/2));
	}

	/**
	 * Check delete ad from Thumbnails View tab
	 */
	@FindBy(xpath = "//*[@class='icon-delete']")
	public static List<WebElement> deleteBtnList;
	
	public boolean deleteAdFromThumbnailsViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, fileList.get(0), 30);
		SeleniumWrapper.hoverMouseOverElement(driver, fileList.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, deleteBtnList.get(deleteBtnList.size()/2));
		Function.hoverNclickElement(driver, confirmDeleteBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	
}
