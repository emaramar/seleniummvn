package pages.site__settings;

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

public class OnlinePaymentsTab extends SiteAdministrationPage {
	protected final static Logger logger = LogManager.getLogger(OnlinePaymentsTab.class.getName());

	public OnlinePaymentsTab(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Online Payments tab is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoOnlinePaymentsTab().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (allTransactionsBtn.isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Check transaction status of Party Room booking is "PAID"
	 */
	public static final String tableRowsXpath = ("//*[@id=\"datatable_payments_transactions\"]/tbody/tr");
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> transactionTableRow;

	@FindBy(xpath = "//*[contains(.,'Paid')]/a/span")
	public static List<WebElement> paidTransactionList;

	@FindBy(xpath = "//a[contains(.,'Issue Refund')]")
	public static WebElement issueRefundBtn;

	@FindBy(id = "payment_order_details_user_unit")
	public static WebElement unitOfBooking;

	@FindBy(xpath = "//div[@class='object_transactions_footer_row object_transactions_balance_row']/span[2]")
	public static WebElement amountOfBooking;
	
	@FindBy(xpath = "//*[@id='payment_order_details_description']")
	public static WebElement descriptionOfTransaction;
	
	@FindBy(xpath = "//*[@id='datatable_object_transactions']/tbody/tr/td[1]/a/span")
	public static List<WebElement> statusOfPayment;

	public boolean verifyTransactionRecordOfPaid() {
		SeleniumWrapper.explicitWaitClickable(driver,   paidTransactionList.get(0), 40);
		SeleniumWrapper.clickElement(driver,    paidTransactionList.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.explicitWaitClickable(driver, descriptionOfTransaction, 30);
		String description = descriptionOfTransaction.getText();
		logger.info(" Description of transaction:" + description);

		SeleniumWrapper.explicitWaitClickable(driver, statusOfPayment.get(0), 30);
		String paymentStatus = statusOfPayment.get(0).getText();
		logger.info("Payment status:" + paymentStatus);

		String totalBalance = amountOfBooking.getText();
		logger.info("Total balance:" + totalBalance);
		if (description.equalsIgnoreCase("Booking for Party Room") && paymentStatus.equalsIgnoreCase("Paid")
				&& (totalBalance.equalsIgnoreCase("$56.50")))
			return true;
		return false;
	}

	/**
	 * Check go back to Online Payments tab
	 */
	@FindBy(xpath = "//li[contains(.,'Online Payments')]")
	public static WebElement onlinePaymentsTab;

	public boolean returnOnlinePaymentstab() {
		return Function.clickElement(driver, onlinePaymentsTab);
	}

	/**
	 * Check transaction status of Guest Suite booking is "PENDING"
	 */
	@FindBy(xpath = "//*[contains(.,'Pending')]/a/span")
	public static List<WebElement> pendingTransactionList;

	public boolean verifyTransactionRecordOfPending() {
		SeleniumWrapper.explicitWaitClickable(driver,  pendingTransactionList.get(0), 40);
		SeleniumWrapper.clickElement(driver,   pendingTransactionList.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.explicitWaitClickable(driver, descriptionOfTransaction, 30);
		String description = descriptionOfTransaction.getText();
		logger.info(" Description of transaction:" + description);

		SeleniumWrapper.explicitWaitClickable(driver, statusOfPayment.get(0), 30);
		String paymentStatus = statusOfPayment.get(0).getText();
		logger.info("Payment status:" + paymentStatus);

		String totalBalance = amountOfBooking.getText();
		logger.info("Total balance:" + totalBalance);
		if (description.equalsIgnoreCase("Booking for Guest Suite") && paymentStatus.equalsIgnoreCase("Pending")
				&& (totalBalance.equalsIgnoreCase("$113.00")))
			return true;
		return false;
	}

	/**
	 * Check transaction status of Guest Suite booking is "REFUNDED"
	 */
	@FindBy(xpath = "//*[contains(.,'Refunded')]/a/span")
	public static List<WebElement> refundedTransactionList;

	public boolean verifyTransactionRecordOfRefunded() {
		SeleniumWrapper.explicitWaitClickable(driver, refundedTransactionList.get(0), 40);
		SeleniumWrapper.clickElement(driver,  refundedTransactionList.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.explicitWaitClickable(driver, descriptionOfTransaction, 30);
		String description = descriptionOfTransaction.getText();
		logger.info(" Description of transaction:" + description);
		
		List<String> paymentStatus = new ArrayList<>();
		for ( int i =0; i < statusOfPayment.size(); i++) {
			paymentStatus.add(statusOfPayment.get(i).getText());
			logger.info("Payment status:" + paymentStatus);
		}
		String totalBalance = amountOfBooking.getText();
		logger.info("Total balance:" + totalBalance);
		if (description.equalsIgnoreCase("Booking for Guest Suite") || description.equalsIgnoreCase("Booking for Party Room") && paymentStatus.contains("REFUNDED")
				&& totalBalance.equalsIgnoreCase("$0.00"))
			return true;
		return false;
	}

	/**
	 * Check issue refund function
	 */
	@FindBy(xpath = " //*[@class='btn-actual-checkbox']")
	public static List<WebElement> checkboxList; // index 4

	@FindBy(xpath = "//button[contains(.,'Process Refund')]")
	public static WebElement processRefundBtn;

	@FindBy(xpath = "//*[@class='payments_refunder_line payments_refunder_total_line']")
	public static WebElement totalRefundAmt;

	@FindBy(xpath = "//*[contains(.,'Order # ')]/li")
	public static List<WebElement> orderNum;

	public boolean issueRefundOnPaidTransaction() {
		Function.clickElement(driver, paidTransactionList.get(0));
		String orderNumber = orderNum.get(0).getText();
		logger.info("The order number is:" + orderNumber);
		Function.clickElement(driver, issueRefundBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, checkboxList.get(4));
		SeleniumWrapper.explicitWaitClickable(driver, totalRefundAmt, 20);
		logger.info("The total refund amount is:" + totalRefundAmt.getText());
		Function.clickElement(driver, processRefundBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check view transactions by type "Amenity Booking"
	 */
	@FindBy(xpath = "//button[contains(.,'All Transactions')]")
	public static WebElement allTransactionsBtn;

	@FindBy(xpath = "//*[@id=\"bs-select-1\"]/ul/li")
	public static List<WebElement> allTransactionsTypes;

	@FindBy(xpath = "//button[contains(.,'Amenity Booking')]")
	public static WebElement typeOfAmenityBooking;

	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions_paginate\"]/span/a")
	public static List<WebElement> numOfPages;

	@FindBy(xpath = "//a[@class='paginate_button next']")
	public static WebElement nextPageBtn;

	@FindBy(xpath = "//a[@class='paginate_button next disabled']")
	public static WebElement nextPageBtnDisabled;

	public boolean viewTransactionByTypeAmenityBooking() {
		Function.clickElement(driver, allTransactionsBtn);
		Function.clickElement(driver, allTransactionsTypes.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		TestResultValidator.isFilterResultContainKeyword(ColumnTypeXpath, driver, "Amenity Booking");
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return true;
	}

	/**
	 * Check view transactions by type "Estoppel Certificates"
	 */
	public boolean viewTransactionByTypeEstoppelCertificate() {
		Function.clickElement(driver, typeOfAmenityBooking);
		Function.clickElement(driver, allTransactionsTypes.get(2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		TestResultValidator.isFilterResultContainKeyword(ColumnTypeXpath, driver, "Estoppel Certificates");
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return true;
	}

	/**
	 * Check sort per column Date Created
	 */
	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/thead/tr/th[1]")
	public static WebElement columnHeaderDateCreated;

	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/tbody/tr/td[1]")
	public static List<WebElement> columnDateCreate;

	public boolean sortByColumnDateCreated() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderDateCreated, 20);
		if (SeleniumWrapper.clickElement(driver, columnHeaderDateCreated, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isDateElementsAscendingOrdered2(columnDateCreate);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check sort per column Order ID
	 */
	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/thead/tr/th[2]")
	public static WebElement columnHeaderOrderID;

	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/tbody/tr/td[2]")
	public static List<WebElement> columnOrderID;

	public boolean sortByColumnOrderID() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderOrderID, 20);
		if (SeleniumWrapper.clickElement(driver, columnHeaderOrderID, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnOrderID);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check sort per column Status
	 */
	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/thead/tr/th[3]")
	public static WebElement columnHeaderStatus;

	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/tbody/tr/td[3]")
	public static List<WebElement> columnStatus;

	public boolean sortByColumnStatus() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderStatus, 20);
		if (SeleniumWrapper.clickElement(driver, columnHeaderStatus, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnStatus);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check sort per column Type
	 */
	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/thead/tr/th[4]")
	public static WebElement columnHeaderType;

	public static final String ColumnTypeXpath = ("//*[@id=\"datatable_payments_transactions\"]/tbody/tr/td[4]");
	@FindBy(xpath = ColumnTypeXpath)
	public static List<WebElement> columnType;

	public boolean sortByColumnType() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderType, 20);
		if (SeleniumWrapper.clickElement(driver, columnHeaderType, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnType);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check sort per column Unit
	 */
	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/thead/tr/th[5]")
	public static WebElement columnHeaderOrderUnit;

	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/tbody/tr/td[5]")
	public static List<WebElement> columnUnit;

	public boolean sortByColumnUnit() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderOrderUnit, 20);
		if (SeleniumWrapper.clickElement(driver, columnHeaderOrderUnit, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isIntegerElementsAscendingOrdered(columnUnit);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check sort per column User
	 */
	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/thead/tr/th[6]")
	public static WebElement columnHeaderUser;

	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/tbody/tr/td[6]")
	public static List<WebElement> columnUser;

	public boolean sortByColumnUser() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderUser, 20);
		if (SeleniumWrapper.clickElement(driver, columnHeaderUser, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnUser);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check sort per column Total
	 */
	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/thead/tr/th[7]")
	public static WebElement columnHeaderTotal;

	@FindBy(xpath = "//*[@id=\"datatable_payments_transactions\"]/tbody/tr/td[7]")
	public static List<WebElement> columnTotal;

	public boolean sortByColumTotal() {
		SeleniumWrapper.explicitWaitClickable(driver, columnHeaderTotal, 30);
		if (SeleniumWrapper.clickElement(driver, columnHeaderTotal, Constants.CLICK_METHOD_ENUM.CLICK))
			return TestResultValidator.isStringElementsAscendingOrdered(columnTotal);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return false;
	}

	/**
	 * Check search function
	 */
	@FindBy(id = "online_payments_search-input")
	public static WebElement searchBox;

	public boolean searchRecord(){
		String keyword1 = "P";
		String keyword2 = "A";
		String keyword3 = "I";
		String keyword4 = "D";
		Function.clickElement(driver, searchBox);
		SeleniumWrapper.setInputFieldText(searchBox, keyword1, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldTextNoClear(searchBox, keyword1 + keyword2, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldText(searchBox, keyword1 + keyword2 + keyword3, driver);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		SeleniumWrapper.setInputFieldText(searchBox, keyword1 + keyword2 + keyword3 + keyword4, driver);
		SeleniumWrapper.waitForPageToBeLoaded(driver);
		return TestResultValidator.isSearchedFromElementsList(tableRowsXpath, driver, keyword1 + keyword2 + keyword3 + keyword4 );
	}
}
