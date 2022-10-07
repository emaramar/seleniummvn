package pages.resources;

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

public class ResidentsGuidePage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(ResidentsGuidePage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param password login password
	 */

	public ResidentsGuidePage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Residents'Guide page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoResidentsGuidePage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createGuideBtn.isEnabled()) {
			logger.info("Button name is:" + createGuideBtn.getText());
			return true;
		}
		return false;
	}

	/**
	 * Check is "Title" field mandatory
	 */
	@FindBy(xpath = "//*[@id='add-resident-guide']/span[2]")
	public static WebElement createGuideBtn;

	@FindBy(id = "title")
	public static WebElement titleInputBox;

	@FindBy(xpath = "//*[@class='fr-wrapper show-placeholder']")
	public static WebElement contentBox1;

	@FindBy(xpath = "//*[@class='fr-wrapper']")
	public static WebElement contentBox2;

	@FindBy(xpath = "//*[@class='fr-element fr-view']")
	public static WebElement ContentEditable;

	@FindBy(xpath = "//*[contains(text(), 'Save')]")
	public static WebElement saveBtn;

	@FindBy(xpath = "//button[contains(text(), 'Cancel')]")
	public static List<WebElement> cancelBtn;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsg; // index 5

	public boolean clickCreateGuideBtn() {
		return Function.clickElement(driver, createGuideBtn);
	}

	public boolean clickTitleBox() {
		return Function.clickElement(driver, titleInputBox);
	}

	public String title = "Rules and Regulations";

	public boolean enterTitle() {
		if (Function.clickElement(driver, titleInputBox)) {
			SeleniumWrapper.setInputFieldText(titleInputBox, title, driver);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	public boolean clickContentBox() {
		Function.clickElement(driver, contentBox1);
		return Function.clickElement(driver, ContentEditable);
	}

	public boolean enterContent() {
		String text = "Rules...";
		if (Function.clickElement(driver, ContentEditable)) {
			return SeleniumWrapper.setInputFieldText(ContentEditable, text, driver);
		}
		return false;
	}

	public boolean clickSaveBtn() {
		Function.clickElement(driver, saveBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg.get(5), 30);
		String errorMessage = errorMsg.get(5).getText();
		logger.info("The error message is :" + errorMessage);
		if(errorMsg != null)
			return true;
		else
		return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn.get(1), 30);
		if (cancelBtn.get(1) != null) {
			return Function.clickElement(driver, cancelBtn.get(1));
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
		if(confirmMsg.isDisplayed()&& !message.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check search resident guide
	 */
	@FindBy(id = "resident_guide_search-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath = ("//*[@id='datatable_residents']/tbody/tr");
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> residentGuidesTableRow;

	public boolean searchResidentGuide() {
		return Function.search(driver, searchBox, "Regulations", tableRowsXpath);
	}

	/**
	 * Check click to open resident guide
	 */
	@FindBy(xpath = "//*[@id='datatable_residents']/tbody/tr/td[2]/a")
	public static List<WebElement> columnName;

	public boolean clickToOpenResidentGuide() {
		return Function.clickElement(driver, columnName.get(columnName.size() - 1));
	}

	/**
	 * Check edit resident guide
	 */
	@FindBy(xpath = "//*[@id='modal-edit-button']/span[1]")
	public static WebElement editBtn;

	public boolean clickEditBtn() {
		return Function.clickElement(driver, editBtn);

	}

	public boolean modifyContent() {
		Function.clickElement(driver, contentBox2);
		logger.info("Original Text is:" + contentBox2.getText());
		String text = "content";
		if (Function.clickElement(driver, ContentEditable)) {
			SeleniumWrapper.setInputFieldText(ContentEditable, text, driver);
			logger.info("Modify Text is:" + contentBox2.getText());
		}
		return false;
	}

	/**
	 * Check edit resident guide from pencil icon
	 */
	@FindBy(xpath = "//*[@id='datatable_residents']/tbody/tr/td[5]")
	public static List<WebElement> editIcon;

	public WebElement recordToBeEdited() {
		for (int i = 0; i < residentGuidesTableRow.size(); i++)
			if (residentGuidesTableRow.get(i).getText().contains(title))
				return editIcon.get(i);
		return null;
	}

	public boolean clickEditIcon() {
		SeleniumWrapper.explicitWaitClickable(driver, editIcon.get(0), 30);
		SeleniumWrapper.hoverMouseOverElement(driver, editIcon.get(0));
		return Function.hoverNclickElement(driver, recordToBeEdited());
	}

	public boolean modifyTitle() {
		logger.info("Original title is:" + titleInputBox.getAttribute("value"));
		String text = "Rules and Regulations for All Residents";
		SeleniumWrapper.setInputFieldText(titleInputBox, text, driver);
		String modifyTitle = titleInputBox.getAttribute("value");
		logger.info("Modify title is:" + modifyTitle);
		if(modifyTitle.equals(text))
			return true;
		else
		return false;
	}

	/**
	 * Check delete resident guide from trash icon
	 */
	@FindBy(xpath = "//*[@id='datatable_residents']/tbody/tr/td[6]")
	public static List<WebElement> deleteIcon;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmDeleteBtn;

	
	public boolean deleteResidentGuide() {
		SeleniumWrapper.hoverMouseOverElement(driver,deleteIcon.get(deleteIcon.size()-1) );
		SeleniumWrapper.clickElement(driver, deleteIcon.get(deleteIcon.size()-1), Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 30);
		if(confirmDeleteBtn.isEnabled())
		return SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}

}
