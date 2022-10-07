package pages;

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

public class IncidentReportsPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(IncidentReportsPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public IncidentReportsPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Incident Reports Page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoIncidentReportsPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createIncidentReportBtn.isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Check create new incident report
	 */
	@FindBy(xpath = "//a[@href='/incident_reports/createReport']")
	private static WebElement createIncidentReportBtn;

	@FindBy(xpath = "//div[contains(text(), 'Game room')]")
	private static WebElement formType;

	@FindBy(xpath = "//*[contains(text(),'Visitor Parking Report')]")
	private static List<WebElement> choiceOfGym; // index 1

	@FindBy(id = "description")
	private static WebElement summaryBox;

	@FindBy(xpath = "//*[@class='icon-calendar']")
	public static List<WebElement> dateCalendarIcon; // get index 0

	@FindBy(xpath = "//*[@class='date-field required']")
	private static List<WebElement> dateInputBox;

	@FindBy(xpath = "//*[@class='search-box-search-user form-input tt-input']")
	private static List<WebElement> reportedByBox; // get index 0

	@FindBy(xpath = "//div[contains(@class, 'tt-suggestion')]")
	public static WebElement choiceOfReportedBy;

	@FindBy(id = "save-incident-report")
	public static WebElement createReportBtn;

	public boolean clickCreateReportBtn() {
		return Function.clickElement(driver, createIncidentReportBtn);
	}

	public boolean selectFormType() {
		Function.clickElement(driver, formType);
		return Function.hoverNclickElement(driver, choiceOfGym.get(1));
	}

	public boolean enterSummary() {
		String text = "Lost BMW car key in south parking lot";
		Function.clickElement(driver, summaryBox);
		return SeleniumWrapper.setInputFieldText(summaryBox, text, driver);
	}

	public boolean setDate() {
		Function.clickElement(driver, dateCalendarIcon.get(0));
		return Function.setDateByEnter(driver, -1, dateInputBox.get(0));
	}

	public boolean enterReportPerson() {
		Function.clickElement(driver, reportedByBox.get(0));
		SeleniumWrapper.setInputFieldText(reportedByBox.get(0), "Susy", driver);
		return Function.clickElement(driver, choiceOfReportedBy);
	}

	public boolean clickCreateIncidentReportBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createReportBtn, 30);
		if(createReportBtn.isEnabled())
		return Function.clickElement(driver, createReportBtn);
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
		return false;
	}

	/**
	 * check delete incident report
	 */
	@FindBy(xpath = "//*[@id='datatable_incident_reports']/tbody/tr/td[4]/a/span")
	public static List<WebElement> deleteIcon;

	@FindBy(id = "table-action-confirm")
	public static WebElement confirmBtn;

	public boolean deleteIncidentReport() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIcon.get(0));
		return Function.deleteRecord(driver, deleteIcon, 0, confirmBtn);
	}

	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBtn, 30);
		if (confirmBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}


	/**
	 * check update incident report
	 */
	@FindBy(xpath = "//div[contains(@class, 'fr-wrapper')]")
	public static WebElement textBox;

	@FindBy(xpath = "//div[contains(@class, 'fr-element fr-view')]")
	public static WebElement textBoxEditable;

	@FindBy(xpath = "//*[@id='attachments_create_select_file']/span[2]")
	public static WebElement uploadFileBtn;

	@FindBy(xpath = "//*[@id='save-incident-report-note']/span[2]")
	public static WebElement updateIncidentReportBtn;

	@FindBy(xpath = "//*[@id='datatable_incident_reports']/tbody/tr/td[3]/a")
	public static List<WebElement> columnSummaryToClick;

	public boolean openIncidentReport() {
		return Function.hoverNclickElement(driver, columnSummaryToClick.get(0));
	}

	public boolean uploadFile() throws Exception {
		// upload carkey imge
		 Function.uploadFile(driver, uploadFileBtn, Constants.imgOfCarKey);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return true;
	}

	public boolean enterMsg() {
		Function.hoverNclickElement(driver, textBox);
		Function.hoverNclickElement(driver, textBoxEditable);
		SeleniumWrapper.setInputFieldText(textBoxEditable, "photo", driver);
		logger.info("The msg is:" + textBoxEditable.getText());
		return true;
	}

	public boolean clickUpdateIncidentReportBtn() {
		return Function.hoverNclickElement(driver, updateIncidentReportBtn);
	}

	/**
	 * Go back Incident Report Tab
	 */
	@FindBy(xpath = "//a[@href='/incident_reports']")
	public static List<WebElement> incidentReportsTab;

	public boolean goBackIncidentReportTab() {
		return Function.hoverNclickElement(driver, incidentReportsTab.get(1));
	}

	/**
	 * Check search function
	 */
	@FindBy(id = "reports_search-input")
	public static WebElement searchBox;

	public static final String tableRowsXpath = "//*[@id='datatable_incident_reports']/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> tableRowRecords;

	public boolean searchReport() throws Exception {
		String keyword1 = "l"; //key = default keyword
		String keyword2 = "o";
		String keyword3 = "t";
		Function.clickElement(driver, searchBox);
		SeleniumWrapper.setInputFieldText(searchBox, keyword1, driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.setInputFieldTextNoClear(searchBox, keyword1 + keyword2, driver);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		SeleniumWrapper.setInputFieldText(searchBox, keyword1 + keyword2 + keyword3, driver);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return TestResultValidator.isSearchedFromElementsList(tableRowsXpath, driver, keyword1 + keyword2 + keyword3);
	}

	/**
	 * Check sort column ID
	 */
	@FindBy(xpath = "//*[@id='datatable_incident_reports']/thead/tr/th[1]")
	public static WebElement columnHeaderID;

	@FindBy(xpath = "//*[@id='datatable_incident_reports']/tbody/tr/td[1]")
	public static List<WebElement> columnID;

	public boolean sortByColumnID() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderID, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderID, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnID);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return true;
	}

	/**
	 * Check sort column Date Created
	 */
	@FindBy(xpath = "//*[@id='datatable_incident_reports']/thead/tr/th[2]")
	public static WebElement columnHeaderDateCreated;

	@FindBy(xpath = "//*[@id='datatable_incident_reports']/tbody/tr/td[2]")
	public static List<WebElement> columnDateCreated;

	public boolean sortByColumnDateCreated() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderDateCreated, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderDateCreated, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isDateElementsAscendingOrdered(columnDateCreated);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return true;
	}

	/**
	 * Check sort column Summary
	 */
	@FindBy(xpath = "//*[@id='datatable_incident_reports']/thead/tr/th[3]")
	public static WebElement columnHeaderSummary;

	@FindBy(xpath = "//*[@id='datatable_incident_reports']/tbody/tr/td[3]")
	public static List<WebElement> columnSummary;

	public boolean sortByColumnSummary() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderSummary, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderSummary, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnSummary);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check sort column Status
	 */
	@FindBy(xpath = "//*[@id='datatable_incident_reports']/thead/tr/th[4]")
	public static WebElement columnHeaderStatus;

	@FindBy(xpath = "//*[@id='datatable_incident_reports']/tbody/tr/td[4]")
	public static List<WebElement> columnStatus;

	public boolean sortByColumnStatus() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderStatus, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderStatus, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnStatus);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check "Show Deleted Incident Reports"
	 */
	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> showDeletedCheckbox;

	public boolean showDeletedIncidentReports() throws Exception {
		SeleniumWrapper.explicitWaitClickable(driver, showDeletedCheckbox.get(0), 30);
		if (SeleniumWrapper.clickElement(driver, showDeletedCheckbox.get(0), Constants.CLICK_METHOD_ENUM.CLICK))
			SeleniumWrapper.waitForDomToBeRendered(driver);
		return TestResultValidator.isSearchedFromElementsList(tableRowsXpath, driver, "DELETED");
	}

	public boolean clickShowDeletedCheckBox() throws Exception {
		Function.hoverNclickElement(driver, showDeletedCheckbox.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	/**
	 * Check restore deleted incident report
	 */
	@FindBy(xpath = "//*[@id='datatable_incident_reports']/tbody/tr/td[5]/a/span")
	public static List<WebElement> restoreIcon;

	public boolean restoreIncidentReport() {
		SeleniumWrapper.hoverMouseOverElement(driver, restoreIcon.get(0));
		Function.hoverNclickElement(driver, restoreIcon.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
}
