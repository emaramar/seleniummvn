package pages.site__settings;

import java.io.File;
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

public class UsersPage extends SiteAdministrationPage {
	protected final static Logger logger = LogManager.getLogger(UsersPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */

	public UsersPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Users page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoUsersPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createUserBtn.isDisplayed()) {
			logger.info("tag name:" + createUserBtn.getText());
			return true;
		}
		return false;
	}

	/**
	 * Check auto-generate password
	 */
	@FindBy(xpath = "//*[@href='/users/create/']/span[2]")
	public static WebElement createUserBtn;

	@FindBy(id = "auto-generate-password")
	public static WebElement autoGeneratePasswordBtn;

	@FindBy(id = "password1")
	public static WebElement passwordInputBox;

	@FindBy(xpath = "//*[@id='user-create-cancel-button']/span[2]")
	public static WebElement cancelBtn;

	public boolean clickCreateUserBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createUserBtn, 30);
		if (createUserBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createUserBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	public boolean clickAutoGeneratePasswordBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, autoGeneratePasswordBtn, 30);
		if (autoGeneratePasswordBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, autoGeneratePasswordBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean isPasswordGenerated() {
		SeleniumWrapper.explicitWaitClickable(driver, passwordInputBox, 30);
		String password = passwordInputBox.getAttribute("value");
		logger.info("password is:" + password);
		if(!password.isEmpty())
			return true;
		else
		return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 30);
		if (cancelBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, cancelBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check select country and enter number on Cell field
	 */
	@FindBy(xpath = "//*[@class='selected-flag']")
	public static List<WebElement> selectCountryFlagField;

	@FindBy(xpath = "//*[contains(text(),'American Samoa')]")
	public static List<WebElement> countryOfAmericanSamoa;

	@FindBy(xpath = "//*[contains(text(),'Canada')]")
	public static List<WebElement> countryOfCanada;

	public boolean selectCountryFlag() {
		Function.clickElement(driver, selectCountryFlagField.get(1));
		Function.hoverNclickElement(driver, countryOfAmericanSamoa.get(1));
		logger.info("Country code is:" + cellInputBox.getAttribute("value"));
		if (cellInputBox.getAttribute("value").equalsIgnoreCase("+1684"))
			return true;
		return false;
	}

	public boolean selectCountryFlagNEnterCellNum() {
		Function.clickElement(driver, selectCountryFlagField.get(1));
		Function.hoverNclickElement(driver, countryOfAmericanSamoa.get(1));
		String countryCode = cellInputBox.getAttribute("value");
		logger.info("Country code is:" + countryCode);
		SeleniumWrapper.explicitWaitClickable(driver, cellInputBox, 20);
		SeleniumWrapper.setInputFieldTextNoClear(cellInputBox, countryCode + "-" + "633-4116", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("Cell number is:" + cellInputBox.getAttribute("value"));
		if(cellInputBox.getAttribute("value").contains("+1684-633-4116"))
			return true;
		else
		return false;
	}

	/**
	 * Check change user's registration type
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static WebElement editIconOnUnitAccess;

	@FindBy(xpath = "//*[@title='Resident Owner']")
	public static WebElement registrationTypeBox;

	@FindBy(xpath = "//*[@id='bs-select-10']/ul/li/a/span")
	public static List<WebElement> choicesOfRegistrationType;// index 2

	@FindBy(xpath = "//*[@id=\"datepicker_moving_in_date1\"]/span/span")
	public static WebElement calendarIcon;

	@FindBy(xpath = "//*[@id='modal-save-button']")
	public static WebElement saveBtn;

	public boolean clickEditIconOnUser() {
		SeleniumWrapper.explicitWaitClickable(driver, editIconOnUnitAccess, 80);
		if (editIconOnUnitAccess.isEnabled())
			return SeleniumWrapper.clickElement(driver, editIconOnUnitAccess, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean editUserToTenant() {
		SeleniumWrapper.explicitWaitClickable(driver, registrationTypeBox, 30);
		if (SeleniumWrapper.clickElement(driver, registrationTypeBox, Constants.CLICK_METHOD_ENUM.CLICK))
			return Function.hoverNclickElement(driver, choicesOfRegistrationType.get(2));
		return false;
	}

	public boolean setTenantMoveInDate() {
		SeleniumWrapper.explicitWaitClickable(driver, calendarIcon, 50);
		if (SeleniumWrapper.clickElement(driver, calendarIcon, Constants.CLICK_METHOD_ENUM.CLICK)) {
			Function.setDatefromCalendar(driver, 2);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	

	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		if (saveBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check is move-in date set?
	 */
	@FindBy(xpath = "//*[@class='pill-box-description']")
	public static WebElement userDesc;

	public boolean checkIsMoveInDateSet() {
		SeleniumWrapper.explicitWaitClickable(driver, userDesc, 20);
		String moveInDate = userDesc.getText();
		logger.info("tenant move-in date:" + moveInDate);
		if(moveInDate.contains("Tenant access starts on"))
			return true;
		else
		return false;
	}


	/**
	 * Check create a new user with type of “Website User”
	 */
	@FindBy(id = "first_name")
	public static WebElement firstNameInputBox;

	@FindBy(id = "last_name")
	public static WebElement lastNameInputBox;

	@FindBy(id = "email")
	public static WebElement emailInputbox;

	@FindBy(id = "home_phone")
	public static WebElement homePhoneInputBox;

	@FindBy(id = "cell_phone")
	public static WebElement cellInputBox;

	@FindBy(id = "user-search")
	public static WebElement suiteSearchBox;

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement selectChoice;

	@FindBy(xpath = "//*[@class='pill-box-description']")
	public static WebElement inputSuite;

	@FindBy(xpath = "//*[@class='token-input-input-token-workflow']")
	public static WebElement userGroups;

	@FindBy(xpath = "//*[@class='token-input-token-workflow']")
	public static WebElement inputGroups;

	@FindBy(xpath = "//*[@class='token-input-dropdown-item-workflow']")
	public static List<WebElement> choiceOfGroups;// select index 0
	////*[@class='token-input-dropdown-item2-workflow'

	@FindBy(xpath = "//*[@id='attachments_select_file']/span[2]")
	public static WebElement attachedFilesBtn;

	@FindBy(xpath = "//*[@id='user-create-account-button']/span[2]")
	public static WebElement confirmCreateUserBtn;

	public static String firstName;

	public boolean enterFirstName() {
		Function.clickElement(driver, firstNameInputBox);
		Faker faker = new Faker();
		firstName = faker.name().firstName();
		SeleniumWrapper.setInputFieldText(firstNameInputBox, firstName, driver);
		logger.info("first name is:" + firstNameInputBox.getAttribute("value"));
		if(!firstNameInputBox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	public boolean enterLastName_WebsiteUser() {
		Function.clickElement(driver, lastNameInputBox);
		SeleniumWrapper.setInputFieldText(lastNameInputBox, "Wraf", driver);
		logger.info("last name is:" + lastNameInputBox.getAttribute("value"));
		if(!lastNameInputBox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	public boolean enterLastName_ProfileOnlyUser() {
		Function.clickElement(driver, lastNameInputBox);
		SeleniumWrapper.setInputFieldText(lastNameInputBox, "Zoo", driver);
		logger.info("last name is:" + lastNameInputBox.getAttribute("value"));
		if(!lastNameInputBox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	public static String emailAddress;

	public boolean enterEmail() {
		emailAddress = firstName + "@gmail.com";
		Function.clickElement(driver, emailInputbox);
		SeleniumWrapper.setInputFieldText(emailInputbox, emailAddress, driver);
		logger.info("email is:" + emailInputbox.getAttribute("value"));
		if(!emailInputbox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	public static String password = SeleniumWrapper.generateRandomString(8);

	public boolean enterPassword() {
		Function.clickElement(driver, passwordInputBox);
		SeleniumWrapper.setInputFieldText(passwordInputBox, password, driver);
		logger.info("password is:" + passwordInputBox.getAttribute("value"));
		if(!passwordInputBox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	public boolean enterFixPassword() {
		Function.clickElement(driver, passwordInputBox);
		SeleniumWrapper.setInputFieldText(passwordInputBox, "12345678", driver);
		logger.info("password is:" + passwordInputBox.getAttribute("value"));
		if(!passwordInputBox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	String phoneNum = "905-456-5689";

	public boolean enterHomePhone() {
		Function.clickElement(driver, homePhoneInputBox);
		SeleniumWrapper.setInputFieldText(homePhoneInputBox, phoneNum, driver);
		logger.info("Home phone is:" + homePhoneInputBox.getAttribute("value"));
		if(!homePhoneInputBox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	public boolean enterCellNumber() {
		Function.clickElement(driver, selectCountryFlagField.get(1));
		Function.hoverNclickElement(driver, countryOfCanada.get(3));
		String countryCode = cellInputBox.getAttribute("value");
		SeleniumWrapper.explicitWaitClickable(driver, cellInputBox, 30);
		SeleniumWrapper.setInputFieldTextNoClear(cellInputBox, countryCode + phoneNum, driver);
		SeleniumWrapper.clickElement(driver, suiteSearchBox, Constants.CLICK_METHOD_ENUM.CLICK);
		// Function.clickElement(driver, cellInputBox);
		// SeleniumWrapper.setInputFieldText(cellInputBox, phoneNum, driver);
		logger.info("Cell number is:" + cellInputBox.getAttribute("value"));
		if(!cellInputBox.getAttribute("value").isEmpty())
			return true;
		else
		return false;
	}

	public boolean enterSuiteNum() {
		Function.clickElement(driver, suiteSearchBox);
		SeleniumWrapper.setInputFieldText(suiteSearchBox, "103", driver);
		SeleniumWrapper.clickElement(driver, selectChoice, Constants.CLICK_METHOD_ENUM.CLICK);
		logger.info("Suite number is:" + inputSuite.getText());
		if(!inputSuite.getText().isEmpty())
			return true;
		else
		return false;
	}

	public boolean enterUserGroup() {
		Function.clickElement(driver, userGroups);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.clickElement(driver, choiceOfGroups.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		logger.info("User Group is:" + inputGroups.getText());
		if(!inputGroups.getText().isEmpty())
			return true;
		else
		return false;
	}

	public boolean attachedFile() throws Exception {
		return Function.uploadFile(driver, attachedFilesBtn, Constants.imgOfPhoto);
	}

	public boolean clickconfirmCreateUserBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmCreateUserBtn, 30);
		if (confirmCreateUserBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmCreateUserBtn, Constants.CLICK_METHOD_ENUM.CLICK);
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
		if(confirmMsg.isDisplayed() && !message.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check create a new user with type of “Profile Only”
	 */
	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> usertypeRadioBtn;

	@FindBy(xpath = "//*[@id='button-welcome-email']/span[2]")
	public static WebElement sendWelcomeEmailCheckbox;

	public boolean selectUserTypeOfProfileOnly() {
		SeleniumWrapper.explicitWaitClickable(driver, usertypeRadioBtn.get(1), 30);
		if (usertypeRadioBtn.get(1).isEnabled())
			return SeleniumWrapper.clickElement(driver, usertypeRadioBtn.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean selectNotSendWelcomeEmail() {
		SeleniumWrapper.explicitWaitClickable(driver, sendWelcomeEmailCheckbox, 30);
		if (sendWelcomeEmailCheckbox.isEnabled())
			return SeleniumWrapper.clickElement(driver, sendWelcomeEmailCheckbox, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check "Re-send Welcome Email" by auto-generate password
	 */
	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[7]/a/span")
	public static List<WebElement> editBtn;

	@FindBy(xpath = "//a[contains(text(),'Wraf')]")
	public static List<WebElement> userToBeEditted;

	@FindBy(xpath = "//*[contains(text(),'Re-send Welcome Email')]")
	public static WebElement resendWelcomeEmailBtn;

	@FindBy(xpath = "//*[@id='button-reset-password']/span[1]")
	public static WebElement resetPasswordCheckbox;

	@FindBy(xpath = "//*[@id='button-generate-password']/span[3]")
	public static WebElement optionOfAutoGenerateStrongPassword;

	@FindBy(xpath = "//*[@id='button-generate-password']/span[2]")
	public static WebElement checkboxOfOptionAutoGenerateStrongPassword;

	public boolean clickWebsiteUser() {
		SeleniumWrapper.explicitWaitClickable(driver, userToBeEditted.get(userToBeEditted.size()-1), 60);
		if (userToBeEditted.get(userToBeEditted.size()-1).isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, userToBeEditted.get(userToBeEditted.size()-1));
			SeleniumWrapper.clickElement(driver, userToBeEditted.get(userToBeEditted.size()-1),
					Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		}
		return false;
	}
	

	public boolean clickResendWelcomeEmailBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, resendWelcomeEmailBtn, 30);
		if (resendWelcomeEmailBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, resendWelcomeEmailBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickResetPasswordCheckbox() {
		SeleniumWrapper.explicitWaitClickable(driver, resetPasswordCheckbox, 30);
		if (resetPasswordCheckbox.isEnabled())
			return SeleniumWrapper.clickElement(driver, resetPasswordCheckbox, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean isAutoGeneratePasswordOptionIsSelected() {
		SeleniumWrapper.explicitWaitClickable(driver, optionOfAutoGenerateStrongPassword, 30);
		SeleniumWrapper.explicitWaitClickable(driver, checkboxOfOptionAutoGenerateStrongPassword, 30);
		logger.info("text is:" + optionOfAutoGenerateStrongPassword.getText());
		if(optionOfAutoGenerateStrongPassword.isDisplayed() && checkboxOfOptionAutoGenerateStrongPassword.isEnabled())
			return true;
		else
		return false;
	}

	/**
	 * Check "Re-send Welcome Email" with reset password
	 */
	@FindBy(xpath = "//*[@id='new_password_2']")
	public static WebElement typeNewPasswordBox_2;

	@FindBy(xpath = "//*[@id='retype_password_2']")
	public static WebElement retypeNewPasswordBox_2;

	public boolean upcheckOptionAutoGeneratePassword() {
		return Function.clickElement(driver, checkboxOfOptionAutoGenerateStrongPassword);
	}

	public boolean setNewPasswordOnResendWelcomeEmail() {
		Function.clickElement(driver, typeNewPasswordBox_2);
		SeleniumWrapper.setInputFieldText(typeNewPasswordBox_2, password, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, retypeNewPasswordBox_2);
		SeleniumWrapper.setInputFieldText(retypeNewPasswordBox_2, password, driver);
		String newPassword = retypeNewPasswordBox_2.getAttribute("value");
		logger.info("New password is:" + newPassword);
		if(!newPassword.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check"Change Password" function
	 */
	@FindBy(xpath = "//*[contains(text(),'Change Password')]")
	public static WebElement changePasswordBtn;

	@FindBy(xpath = "//*[@id='new_password_1']")
	public static WebElement typeNewPasswordBox_1;

	@FindBy(xpath = "//*[@id='retype_password_1']")
	public static WebElement retypeNewPasswordBox_1;

	@FindBy(xpath = "//*[@id='user-save-account-button']/span[2]")
	public static WebElement saveAccountBtn;
	
	@FindBy(xpath = "//*[@id='send']")
	public static WebElement confirmBtnToSaveUser;

	public boolean clickChangePasswordBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, changePasswordBtn, 30);
		if (changePasswordBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, changePasswordBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean changePassword() {
		Function.clickElement(driver, typeNewPasswordBox_1);
		SeleniumWrapper.setInputFieldText(typeNewPasswordBox_1, password, driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, retypeNewPasswordBox_1);
		SeleniumWrapper.setInputFieldText(retypeNewPasswordBox_1, password, driver);
		String newPassword = retypeNewPasswordBox_1.getAttribute("value");
		logger.info("New password is:" + newPassword);
		if(!newPassword.isEmpty())
			return true;
		else
		return false;
	}

	public boolean clickSaveBtnOnEditAccountPage() {
		SeleniumWrapper.explicitWaitClickable(driver, saveAccountBtn, 30);
		if (saveAccountBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveAccountBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	public boolean clickConfirmBtnToSaveUser() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBtnToSaveUser, 30);
		if (confirmBtnToSaveUser.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmBtnToSaveUser, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	

	/**
	 * Check set move-out date
	 */

	public boolean setUserMoveOutDate() {
		Function.hoverNclickElement(driver, calendarIcon);
		return Function.setDatefromCalendar(driver, 7);
	}

	/**
	 * Check is move-out date set?
	 */
	public boolean checkIsMoveOutDateSet() {
		SeleniumWrapper.explicitWaitClickable(driver, userDesc, 30);
		String moveOutDate = userDesc.getText();
		logger.info("User move-out date:" + moveOutDate);
		if(moveOutDate.contains("access expires on"))
			return true;
		else
		return false;
	}

	/**
	 * Check remove setting of E-mail Notification
	 */
	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> checkboxList;

	@FindBy(xpath = "//*[@class='btn-selected icon-checkmark']")
	public static List<WebElement> selectedCheckboxList;

	@FindBy(id = "send")
	public static WebElement sendBtn;

	public boolean removeEmailNotificationSetting() {
		Function.clickElement(driver, selectedCheckboxList.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
	    Function.clickElement(driver, selectedCheckboxList.get(3));
	    SeleniumWrapper.waitForDomToBeRendered(driver);
	    Function.clickElement(driver, selectedCheckboxList.get(6));
		SeleniumWrapper.waitForDomToBeRendered(driver);
	    Function.clickElement(driver, selectedCheckboxList.get(9));
	    SeleniumWrapper.waitForDomToBeRendered(driver);
	    return true;
	}

	public boolean clickSendBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, sendBtn, 30);
		if (sendBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, sendBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check enable muting settings on existing user
	 */

	public boolean enableMutingSettingsOnExistingUser() {
		Function.clickElement(driver, checkboxList.get(11));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return Function.clickElement(driver, checkboxList.get(12));
	}
		
	

	/**
	 * Check setting of E-mail, SMS and Phone Notification
	 */
	@FindBy(xpath = "//*[@class='filter-option-inner-inner']")
	public static List<WebElement> phoneCallBox;

	public boolean setEmailAndSMSAndPhoneNotification_copy() {
		SeleniumWrapper.explicitWaitClickable(driver,  checkboxList.get(0), 30);
		int i =1;
		do {
			Function.clickElement(driver, checkboxList.get(i));
			i++;
		} while (i< checkboxList.size());
		return true;
	}
	
	public boolean setEmailAndSMSAndPhoneNotification() {
		Function.hoverNclickElement(driver, checkboxList.get(2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(4));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(5));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(7));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(8));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(10));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(11));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	
	

	public boolean verifyIsPhoneNotificationSet() {
		SeleniumWrapper.explicitWaitClickable(driver, phoneCallBox.get(0), 30);
		String phoneNum = phoneCallBox.get(0).getText();
		logger.info("Phone number is:" + phoneNum);
		if(!phoneNum.isEmpty())
			return true;
		else
		return false;
	}
	
	
	/**
	 * Check remove setting of E-mail notification on existing user
	 */
	public boolean removeEmailNotificationOnExistingUser() {
		Function.clickElement(driver, selectedCheckboxList.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
	    Function.clickElement(driver, selectedCheckboxList.get(2));
	    SeleniumWrapper.waitForDomToBeRendered(driver);
	    Function.clickElement(driver, selectedCheckboxList.get(5));
		SeleniumWrapper.waitForDomToBeRendered(driver);
	    Function.clickElement(driver, selectedCheckboxList.get(8));
	    SeleniumWrapper.waitForDomToBeRendered(driver);
	    return true;
	}
	

	/**
	 * Check enable notifications for Email, SMS and phone call on existing user
	 * @throws Exception 
	 */
	public boolean enableNotificationsOfEmailAndSMSAndPhoneOnExistingUser_copy() throws Exception {
		Function.hoverNclickElement(driver, checkboxList.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(1));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(2));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(3));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(4));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(5));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(6));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(7));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(8));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(9));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.hoverNclickElement(driver, checkboxList.get(10));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	
	public boolean enableNotificationsOfEmailAndSMSAndPhoneOnExistingUser() throws Exception {
		SeleniumWrapper.explicitWaitClickable(driver,  checkboxList.get(0), 30);
		int i =0;
		do {
			Function.clickElement(driver, checkboxList.get(i));
			i++;
		} while (i<=11);
		return true;
	}
	

	/**
	 * Check search a user
	 */
	@FindBy(id = "all_user_search-input")
	public static WebElement inputSearchBoxText;

	public static final String tableRowsXpath = "//*[@id='datatable']/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> tableRowOfUsers;

	public boolean searchUser() {
		String keyword1 = "L";
		String keyword2 = "a";
		String keyword3 = "u";
		Function.clickElement(driver, inputSearchBoxText);
		SeleniumWrapper.setInputFieldText(inputSearchBoxText, keyword1, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldText(inputSearchBoxText, keyword1 + keyword2, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldText(inputSearchBoxText, keyword1 + keyword2 + keyword3, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return TestResultValidator.isSearchedFromElementsList(tableRowsXpath, driver,
				keyword1 + keyword2 + keyword3);
	}

	/**
	 * Check user’s default email client is open when clicking “Send Email” icon
	 */
	@FindBy(xpath = "//*[@class='icon-envelope']")
	public static List<WebElement> sendEmailIcon;

	public boolean clickSendEmailIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, sendEmailIcon.get(sendEmailIcon.size() - 1));
		if (sendEmailIcon.get(sendEmailIcon.size() - 1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, sendEmailIcon.get(sendEmailIcon.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else
			return false;
	}

	public boolean closeEmailClientWindow() throws Exception {
		File file = new File(Constants.closeEmailClientWindow);
		Runtime.getRuntime().exec(file.getAbsolutePath());
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check deactivate user
	 */
	@FindBy(xpath = "//*[@class='icon-minus-circled']")
	public static List<WebElement> deactivateIcon;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmBtn;

	public boolean ClickDeactivateIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, deactivateIcon.get(deactivateIcon.size() - 1));
		if (deactivateIcon.get(deactivateIcon.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, deactivateIcon.get(deactivateIcon.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean enableSendDeactivationNoticOption() {
		return Function.hoverNclickElement(driver, checkboxList.get(5));
	}

	public boolean clickConfirmBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBtn, 30);
		if (confirmBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, confirmBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check edit user
	 */
	@FindBy(xpath = "//*[@class='icon-edit']")
	public static List<WebElement> editIcon;

	@FindBy(id = "delete-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean ClickEditIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(editIcon.size() - 1));
		if (editIcon.get(editIcon.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, editIcon.get(editIcon.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if (confirmDeleteBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check "Show inactive users" function
	 */
	@FindBy(xpath = "//*[@id='button-show_inactive_users']/span[2]")
	public static WebElement checkboxOfShowInactiveUsers;

	public boolean enableOptionOfShowInactiveUsers() {
		SeleniumWrapper.explicitWaitClickable(driver, checkboxList.get(0), 30);
		if (checkboxList.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, checkboxList.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean areInactiveUsersShowing() {
		String status = "INACTIVE";
		return TestResultValidator.isFilterResultContainKeyword(tableRowsXpath, driver, status);

	}

	public boolean disableOptionOfShowInactiveUsers() {
		SeleniumWrapper.explicitWaitClickable(driver, checkboxOfShowInactiveUsers, 30);
		if (checkboxOfShowInactiveUsers.isEnabled())
			return SeleniumWrapper.clickElement(driver, checkboxOfShowInactiveUsers, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check activate inactive user
	 */
	@FindBy(xpath = "//*[@class='icon-check-circled']")
	public static List<WebElement> activateIcon;

	public boolean ClickActivateIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, activateIcon.get(activateIcon.size() - 1));
		if (activateIcon.get(activateIcon.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, activateIcon.get(activateIcon.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check change “Website” user to “Profile Only” user
	 */
	@FindBy(xpath = "//*[@class='btn-actual-radio']")
	public static List<WebElement> radioBtnList;

	public boolean changeWebsiteUserToProfileOnlyUser() {
		SeleniumWrapper.explicitWaitClickable(driver, radioBtnList.get(1), 30);
		if (radioBtnList.get(1).isEnabled())
			return SeleniumWrapper.clickElement(driver, radioBtnList.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check merge Account With Existing User and change user to "Website User"
	 */
	@FindBy(xpath = "//*[contains(text(),'Select Account')]")
	public static WebElement selectAccountBtn;

	@FindBy(id = "user-search-merge")
	public static WebElement searchAccountBox;

	@FindBy(id = "button-merge")
	public static WebElement mergeAccountsBtn;

	@FindBy(name = "first_name_merged")
	public static List<WebElement> mergeFirstName;

	@FindBy(name = "last_name_merged")
	public static List<WebElement> mergeLastName;

	@FindBy(name = "email_merged")
	public static List<WebElement> mergeEmail;

	public boolean clickSelectAccountBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, selectAccountBtn, 80);
		if (selectAccountBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, selectAccountBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean searchAccountForMerge() {
		SeleniumWrapper.explicitWaitClickable(driver, searchAccountBox, 30);
		if (SeleniumWrapper.clickElement(driver, searchAccountBox, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.setInputFieldText(searchAccountBox, "Zoo", driver);
			Function.clickElement(driver, selectChoice);
			return true;
		}
		return false;
	}

	public boolean mergeFirstName() {
		SeleniumWrapper.explicitWaitClickable(driver, mergeFirstName.get(0), 30);
		if (mergeFirstName.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, mergeFirstName.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean mergeLastName() {
		SeleniumWrapper.explicitWaitClickable(driver, mergeLastName.get(1), 30);
		if (mergeLastName.get(1).isEnabled())
			return SeleniumWrapper.clickElement(driver, mergeLastName.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean mergeEmail() {
		SeleniumWrapper.explicitWaitClickable(driver, mergeEmail.get(1), 30);
		if (mergeEmail.get(1).isEnabled())
			return SeleniumWrapper.clickElement(driver, mergeEmail.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickMergeAccountsBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, mergeAccountsBtn, 30);
		if (mergeAccountsBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, mergeAccountsBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean selectUserTypeOfWebsiteUser() {
		SeleniumWrapper.explicitWaitClickable(driver, usertypeRadioBtn.get(0), 30);
		if (usertypeRadioBtn.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, usertypeRadioBtn.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check delete user by clicking trash icon
	 */
	@FindBy(xpath = "//*[@class='icon-delete']")
	public static List<WebElement> deleteIcon;

	public boolean ClickDeleteIcon() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon.get(deleteIcon.size() - 1));
		if (deleteIcon.get(deleteIcon.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteIcon.get(deleteIcon.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check password for "Profile Only" user
	 */
	@FindBy(xpath = "//*[contains(text(),'Zoo')]")
	public static List<WebElement> userOfZoo;

	public boolean clickProfileOnlyUser() {
		SeleniumWrapper.explicitWaitClickable(driver, userOfZoo.get(userOfZoo.size() - 1), 100);
		if (userOfZoo.get(userOfZoo.size() - 1).isEnabled())
			return SeleniumWrapper.clickElement(driver, userOfZoo.get(userOfZoo.size() - 1),
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check get "Profile Only" user email address
	 */

	public static String profileOnlyUserEmail;

	public boolean getProfileOnlyUserEmail() {
		SeleniumWrapper.explicitWaitClickable(driver, emailInputbox, 30);
		profileOnlyUserEmail = emailInputbox.getAttribute("value");
		logger.info("Profile Only user email :" + profileOnlyUserEmail);
		if(!profileOnlyUserEmail.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check change password on "Profile Only" user
	 */
	@FindBy(xpath = "//*[@id='edit-password-trigger']/span[2]")
	public static WebElement changePasswordBtnOnEditUserPage;

	public boolean changeToFixedPassword() {
		Function.clickElement(driver, typeNewPasswordBox_1);
		SeleniumWrapper.setInputFieldText(typeNewPasswordBox_1, "12345678", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, retypeNewPasswordBox_1);
		SeleniumWrapper.setInputFieldText(retypeNewPasswordBox_1, "12345678", driver);
		String newPassword = retypeNewPasswordBox_1.getAttribute("value");
		logger.info("New password is:" + newPassword);
		if(!newPassword.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check get "Website user" email address
	 */

	public static String websiteUserEmail;

	public boolean getWebsiteUserEmail() {
		SeleniumWrapper.explicitWaitClickable(driver, emailInputbox, 30);
		websiteUserEmail = emailInputbox.getAttribute("value");
		logger.info("Website user email :" + websiteUserEmail);
		if(!websiteUserEmail.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check "Profile Only" user cant log in application
	 */
	@FindBy(xpath = "/html/body/div[2]/div/div[2]/div[2]/span[2]")
	public static WebElement loginErrorMsg;

	public boolean loginByProfileOnlyUser() {
		return Function.logInAsNewUser(driver, profileOnlyUserEmail, "12345678");
	}

	public boolean isProfileOnlyUserLoggedInSuccessful() {
		SeleniumWrapper.explicitWaitClickable(driver, loginErrorMsg, 30);
		String ErrorMessage = loginErrorMsg.getText();
		logger.info("message is:" + ErrorMessage);
		if(ErrorMessage.contains("Website access has been removed for this account"))
			return true;
		else
		return false;
	}

	/**
	 * Check "Website Use" can log in application
	 */
	@FindBy(xpath = "//*[@class='display_name']")
	public static WebElement loggedInUserName;

	@FindBy(id = "modal-concierge-clockin-button")
	public static WebElement clockInBtn;

	@FindBy(xpath = "//button[@class='close']")
	public static WebElement closeBtnOnSetNotificationPreference;

	@FindBy(id = "modal-concierge-clockout-button")
	public static WebElement clockOutBtn;

	public boolean loginByWebsiteUser() {
		return Function.logInAsNewUser(driver, websiteUserEmail, "12345678");
	}

	public boolean isWebsiteUserLoggedInSuccessful() {
		SeleniumWrapper.explicitWaitClickable(driver, loggedInUserName, 30);
		String userName = loggedInUserName.getText();
		logger.info("User is:" + userName);
		if(!userName.isEmpty())
			return true;
		else
		return false;
	}

	public boolean clickClockInBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, clockInBtn, 30);
		if (clockInBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, clockInBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickCloseBtnOnSetNotificationPreference() {
		SeleniumWrapper.explicitWaitClickable(driver, closeBtnOnSetNotificationPreference, 30);
		if (closeBtnOnSetNotificationPreference.isEnabled())
			return SeleniumWrapper.clickElement(driver, closeBtnOnSetNotificationPreference,
					Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	public boolean clickClockOutBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, clockOutBtn, 30);
		if (clockOutBtn.isEnabled())
			return SeleniumWrapper.clickElement(driver, clockOutBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check delete user from "Edit User" page
	 */
	@FindBy(xpath = "//*[@id='user-delete-account-button']/span[2]")
	public static WebElement deleteBtnOnEditUserPage;

	public boolean clickDeleteUserBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteBtnOnEditUserPage, 30);
		if (deleteBtnOnEditUserPage.isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteBtnOnEditUserPage, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

	/**
	 * Check delete "Website User" from "Edit User" page
	 */

	public boolean clickToBeDeletedWebsiteUser() {
		SeleniumWrapper.explicitWaitClickable(driver, userToBeEditted.get(0), 100);
		if (userToBeEditted.get(0).isEnabled()) {
			SeleniumWrapper.hoverMouseOverElement(driver, userToBeEditted.get(0));
			SeleniumWrapper.clickElement(driver, userToBeEditted.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		}
		return false;
	}
	
}
