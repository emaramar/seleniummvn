package pages.resources;

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

public class PetRegistryPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(PetRegistryPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param password login password
	 */

	public PetRegistryPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Pet Registry page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoPetRegistryPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (registerNewPetBtn.isEnabled()) {
			logger.info("Button name is:" + registerNewPetBtn.getText());
			return true;
		} else
			return false;
	}

	/**
	 * Check is "Unit" field mandatory
	 */
	@FindBy(xpath = "//*[@href='/pets/register']/span[2]")
	public static WebElement registerNewPetBtn;

	@FindBy(name = "user_search_box")
	public static WebElement unitSearchBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement selectChoice;

	@FindBy(id = "name")
	public static WebElement petNameBox;

	@FindBy(xpath = "//*[@id='save-pet']/span[2]")
	public static WebElement savePetBtn;

	@FindBy(xpath = "//*[@id='cancel-pet']/span[2]")
	public static WebElement cancelBtn;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsg; // index 5

	public boolean clickRegisterNewPetBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, registerNewPetBtn, 30);
		if (registerNewPetBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, registerNewPetBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean enterUnit() {
		Function.clickElement(driver, unitSearchBox);
		SeleniumWrapper.setInputFieldText(unitSearchBox, "Daniels", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, selectChoice);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String unit = unitSearchBox.getAttribute("value");
		logger.info("Unit is:" + unit);
		if (!unit.isEmpty())
			return true;
		else
			return false;
	}

	public boolean enterPetName() {
		Function.clickElement(driver, petNameBox);
		SeleniumWrapper.enterFirstName(driver, petNameBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String petName = petNameBox.getAttribute("value");
		logger.info("Pet name is:" + petName);
		if (!petName.isEmpty())
			return true;
		else
			return false;
	}

	public boolean clickSavePetBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, savePetBtn, 30);
		if (savePetBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, savePetBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg.get(0), 30);
		String errorMessage = errorMsg.get(0).getText();
		logger.info("The error message is :" + errorMessage);
		if (errorMsg.get(0) != null && errorMessage.matches("One or more required fields below have not been completed."))
			return true;
		else
			return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 30);
		if (cancelBtn.isEnabled()) {
			Function.clickElement(driver, cancelBtn);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
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
		if (confirmMsg.isDisplayed() && !message.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check select category
	 */
	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[3]/div[2]/div/button/div")
	public static WebElement categoryBox;

	@FindBy(xpath = "//div[@id='bs-select-1']/ul/li")
	public static List<WebElement> categoryDropdown;

	public boolean selectCategory() {
		SeleniumWrapper.explicitWaitClickable(driver, categoryBox, 30);
		if (SeleniumWrapper.clickElement(driver, categoryBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			Function.hoverNclickElement(driver, categoryDropdown.get(3));
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean isCategorySelected() {
		SeleniumWrapper.explicitWaitClickable(driver, categoryBox, 30);
		String category = categoryBox.getText();
		logger.info("Selected category is:" + category);
		if (!category.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check upload pet photo
	 */
	@FindBy(xpath = "//*[@id='select_photo']/span[2]")
	public static WebElement uploadPetPhotoBtn;

	@FindBy(xpath = "//*[@id='delete_photo']/span[2]")
	public static WebElement deletePetPhotoBtn;

	public boolean uploadPetPhoto() throws Exception {
		return Function.uploadFile(driver, uploadPetPhotoBtn, Constants.imgOfDogWalker);
	}

	public boolean isPetPhotoUploaded() {
		SeleniumWrapper.explicitWaitClickable(driver, deletePetPhotoBtn, 30);
		if ((deletePetPhotoBtn.isDisplayed() && deletePetPhotoBtn != null))
			return true;
		else
			return false;
	}

	/**
	 * Check upload file from media library
	 */
	@FindBy(xpath = "//*[@id='picker']/span[2]")
	public static WebElement selectFromMediaLibraryBtn;

	@FindBy(xpath = "//*[contains(text(),'Test Folder')]")
	public static WebElement selectFolder;

	@FindBy(xpath = "//*[@class ='list']/li/div")
	public static List<WebElement> listOfImages;

	@FindBy(xpath = "//*[@id='attachments_file_container']/li/div[1]")
	public static WebElement attachmentIcon;

	@FindBy(xpath = "//*[@id='pet_attachments_file_container']/li/div[6]/a[2]/span")
	public static WebElement deleteImageIcon;

	public boolean clickSelectImageFromMediaLibraryBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, selectFromMediaLibraryBtn, 30);
		if (selectFromMediaLibraryBtn.isEnabled())
			return Function.clickElement(driver, selectFromMediaLibraryBtn);
		else
			return false;
	}

	public boolean selectImage() {
		Function.hoverNclickElement(driver, selectFolder);
		Function.hoverNclickElement(driver, listOfImages.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if (attachmentIcon != null)
			return true;
		else
			return false;
	}

	public boolean isImageUploaded() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteImageIcon, 30);
		if ((deleteImageIcon.isDisplayed() && deleteImageIcon != null))
			return true;
		else
			return false;
	}

	/**
	 * Check enter breed
	 */
	@FindBy(xpath = "//*[@id='53_2_']")
	public static WebElement breedInputBox;

	public boolean enterBreed() {
		Function.clickElement(driver, breedInputBox);
		SeleniumWrapper.setInputFieldText(breedInputBox, "Staffordshire", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String breed = breedInputBox.getAttribute("value");
		logger.info("The breed is:" + breed);
		if (breed.matches("Staffordshire"))
			return true;
		else
			return false;
	}

	/**
	 * Check enter color/feature
	 */
	@FindBy(xpath = "//*[@id='53_4_']")
	public static WebElement colorNFeatureBox;

	public boolean enterColorAndFeature() {
		Function.clickElement(driver, colorNFeatureBox);
		SeleniumWrapper.enterText(driver, colorNFeatureBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String colorAndFeature = colorNFeatureBox.getAttribute("value");
		logger.info("The color/feature is:" + colorAndFeature);
		if (!colorAndFeature.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check set temperature
	 */
	@FindBy(xpath = "//*[@id='inner-main-body']/div/form/fieldset/div[8]/div[2]/div/button/div")
	public static WebElement temperatureBox;

	@FindBy(xpath = "//div[@id='bs-select-2']/ul/li")
	public static List<WebElement> temperatureDropdown;

	public boolean setTemperature() {
		SeleniumWrapper.explicitWaitClickable(driver, temperatureBox, 30);
		if (SeleniumWrapper.clickElement(driver, temperatureBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			Function.hoverNclickElement(driver, temperatureDropdown.get(1));
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else
			return false;
	}

	public boolean isTemperatureSet() {
		SeleniumWrapper.explicitWaitClickable(driver, temperatureBox, 30);
		String temperature = temperatureBox.getText();
		logger.info("Set temperature is:" + temperature);
		if (!temperature.isEmpty())
			return true;
		else
			return false;
	}

	public boolean setTemperature_1() {
		Function.clickElement(driver, temperatureBox);
		// int randomChoice = SeleniumWrapper.generateRandomInteger(4);
		// Function.hoverNclickElement(driver, temperatureDropdown.get(randomChoice));
		Function.hoverNclickElement(driver, temperatureDropdown.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		String temperature = temperatureBox.getText();
		logger.info("Set temperature is:" + temperature);
		if (!temperature.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check set pet's birthday
	 */
	@FindBy(xpath = "//*[@id='datepicker_53_3_']/span/span")
	public static WebElement calendarIcon;

	@FindBy(xpath = "//*[@class='prev']")
	public static List<WebElement> prevIcon;

	@FindBy(xpath = "//*[@id='53_3_']")
	public static WebElement petBirthdayBox;

	public boolean setPetBirthday() {
		Function.clickElement(driver, calendarIcon);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, prevIcon.get(0));
		Function.setDatefromCalendar(driver, 0);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String birthdayDate = petBirthdayBox.getAttribute("value");
		logger.info("Pet's birthday is:" + birthdayDate);
		if (!birthdayDate.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check enter Pet's Bio
	 */
	@FindBy(xpath = "//*[@id='53_5_']")
	public static WebElement petBio;

	public boolean enterPetBio() {
		Function.clickElement(driver, petBio);
		SeleniumWrapper.setInputFieldText(petBio, "Pet's Bio", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String bio = petBio.getAttribute("value");
		logger.info("Pet's bio is:" + bio);
		if (!bio.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Check enter Pet's food
	 */
	@FindBy(xpath = "//*[@id='53_51_']")
	public static WebElement petFood;

	public boolean enterPetFood() {
		Function.clickElement(driver, petFood);
		SeleniumWrapper.setInputFieldText(petFood, "healthy food", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String food = petFood.getAttribute("value");
		logger.info("Pet's food is:" + food);
		if (!food.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Test of enable option of "Do not show my pet on the registry"
	 */
	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> checkboxList;

	public boolean enableOptionOfDoNotShowMyPetOnTheRegistry() {
		SeleniumWrapper.explicitWaitClickable(driver, checkboxList.get(0), 30);
		if (checkboxList.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, checkboxList.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check search pet
	 */
	@FindBy(id = "pets_search-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath = "//*[@id='datatable_pets']/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> petsTableRow;

	public boolean searchPet() {
		String searchKeyword = "Dog";
		return Function.search(driver, searchBox, searchKeyword, tableRowsXpath);
	}

	/**
	 * Check user PM click pet name will direct to edit pet page
	 */
	@FindBy(xpath = "//*[@id='datatable_pets']/tbody/tr/td[3]/a")
	public static List<WebElement> petNameList;

	public boolean clickPetNameWillDirectToEditPage() {
		Function.clickElement(driver, petNameList.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.explicitWaitClickable(driver, savePetBtn, 30);
		if (savePetBtn != null)
			return true;
		else
			return false;
	}

	/**
	 * Check user PM edit pet from list view tab
	 */
	@FindBy(xpath = "//*[@id='datatable_pets']/tbody/tr/td[5]/a")
	public static List<WebElement> editIconList;

	public boolean clickEditIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIconList.get(0));
		Function.clickElement(driver, editIconList.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean changePetName() {
		Function.clickElement(driver, petNameBox);
		String originalName = petNameBox.getAttribute("value");
		logger.info("Pet's original name is:" + originalName);
		SeleniumWrapper.enterFirstName(driver, petNameBox);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String updateName = petNameBox.getAttribute("value");
		logger.info("Pet's update name is:" + updateName);
		if (!updateName.matches(originalName))
			return true;
		else
			return false;
	}

	/**
	 * Check user PM delete pet from list view tab
	 */
	@FindBy(xpath = "//*[@id='datatable_pets']/tbody/tr/td[6]/a")
	public static List<WebElement> deleteIconList;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean clickDeleteIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIconList.get(0));
		Function.clickElement(driver, deleteIconList.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	/**
	 * Check goto Thumbnail view tab
	 */
	@FindBy(xpath = "//*[@class='thumbnails_tab']/span")
	public static WebElement thumbnailViewTab;

	public boolean gotoThumbnailViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, thumbnailViewTab, 30);
		if (thumbnailViewTab.isEnabled()) {
			SeleniumWrapper.clickElement(driver, thumbnailViewTab, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	/**
	 * Check goto List view tab
	 */
	@FindBy(xpath = "//*[@class='listview_tab']/span")
	public static WebElement listViewTab;

	public boolean gotoListViewTab() {
		// return Function.clickElement(driver, listViewTab);
		SeleniumWrapper.explicitWaitClickable(driver, listViewTab, 30);
		if (listViewTab.isEnabled()) {
			SeleniumWrapper.clickElement(driver, listViewTab, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	/**
	 * Check user PM edit pet from thumbnail view tab
	 */
	@FindBy(xpath = "//*[@id='community_thumbnails']/ul/li")
	public static List<WebElement> petFiles;

	@FindBy(xpath = "//*[@class='thumbnail_pets_action thumbnail_pets_edit']/span")
	public static List<WebElement> editBtnList;

	public boolean hoverPetFile() {
		SeleniumWrapper.hoverMouseOverElement(driver, petFiles.get(petFiles.size() - 1));
		return true;
	}

	public boolean clickEditBtnFromThumbnailViewTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, editBtnList.get(editBtnList.size() - 1));
		SeleniumWrapper.explicitWaitClickable(driver, editBtnList.get(editBtnList.size() - 1), 30);
		if (editBtnList.get(editBtnList.size() - 1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, editBtnList.get(editBtnList.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check user PM delete pet from thumbnail view tab
	 */
	//@FindBy(xpath = "//*[@class='thumbnail_pets_action thumbnail_delete_community_pet thumbnail_pets_delete']/span")
	@FindBy(xpath = "//*[@data-id_pet='8']")
	public static List<WebElement> deleteBtnList;

	@FindBy(xpath = "//*[contains(text(),'Confirm')]")
	public static WebElement confirmDeleteBtnFromThumbnailViewTab;
	
	  //*[@id=table-action-confirm]

	public boolean clickDeleteBtnFromThumbnailViewTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteBtnList.get(deleteBtnList.size() - 1));
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtnList.get(deleteBtnList.size() - 1), 30);
		if (deleteBtnList.get(deleteBtnList.size() - 1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteBtnList.get(deleteBtnList.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean clickConfirmDeleteBtnFromThumbnailViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtnFromThumbnailViewTab, 30);
		SeleniumWrapper.hoverMouseOverElement(driver, confirmDeleteBtnFromThumbnailViewTab);
		if (confirmDeleteBtnFromThumbnailViewTab.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteBtnFromThumbnailViewTab,
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_5S(driver);
			return true;
		} else
			return false;
	}

	/**
	 * Check Resident user goto Pet Registry menu
	 */
	@FindBy(xpath = "//*[@href='/pets']/span[2]")
	public static WebElement petRegistryMenu;

	public boolean gotoPetRegistryMenu() {
		Function.clickElement(driver, ResourcesTab);
		SeleniumWrapper.explicitWaitClickable(driver, petRegistryMenu, 30);
		if (petRegistryMenu.isEnabled()) {
			SeleniumWrapper.clickElement(driver, petRegistryMenu, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check Resident user edit pet
	 */
	@FindBy(xpath = "//*[@id='tabs-1']/ul/li")
	public static List<WebElement> petFiles_ResidentUser;

	@FindBy(xpath = "//*[@class='thumbnail_pets_action thumbnail_pets_edit']/span")
	public static List<WebElement> editBtnList_ResidentUser;

	public boolean hoverPetFile_ResidentUser() {
		SeleniumWrapper.hoverMouseOverElement(driver, petFiles_ResidentUser.get(0));
		return true;
	}

	public boolean clickEditBtn_ResidentUser() {
		SeleniumWrapper.hoverMouseOverElement(driver, editBtnList_ResidentUser.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, editBtnList_ResidentUser.get(0), 30);
		if (editBtnList_ResidentUser.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, editBtnList_ResidentUser.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check Resident user delete pet
	 */
	@FindBy(xpath = "//*[@class='thumbnail_pets_action thumbnail_delete_my_pet thumbnail_pets_delete']/span")
	public static List<WebElement> deleteBtnList_ResidentUser;

	public boolean clickDeleteBtn_ResidentUser() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteBtnList_ResidentUser.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtnList_ResidentUser.get(0), 30);
		if (deleteBtnList_ResidentUser.get(0).isEnabled()) {
			SeleniumWrapper.clickElement(driver, deleteBtnList_ResidentUser.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	/**
	 * Check Resident user goto Pets at My Community tab
	 */
	@FindBy(xpath = "//*[@href='#tabs-2']")
	public static WebElement petsAtMyCommunityTab;

	public boolean gotoPetsAtMyCommunityTab() {
		SeleniumWrapper.explicitWaitClickable(driver, petsAtMyCommunityTab, 30);
		if (petsAtMyCommunityTab.isEnabled()) {
			SeleniumWrapper.clickElement(driver, petsAtMyCommunityTab, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} else
			return false;
	}

	public boolean viewPetsAtMyCommunity() {
		return TestResultValidator.isFilterResultListed(tableRowsXpath, driver);
	}

}
