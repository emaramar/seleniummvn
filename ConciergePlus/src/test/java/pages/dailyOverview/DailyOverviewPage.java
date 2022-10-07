package pages.dailyOverview;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import pages.BasePage;


public class DailyOverviewPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(DailyOverviewPage.class.getName());
	
	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public DailyOverviewPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Daily Overview page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoDailyOverviewPage().equals(null);
	}
	
	@Override
	public boolean isLoaded() {
		if(headerofImportantTasks.isDisplayed()) {
			return true;
		}
		return false;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//***************************************************************************************************

	/**
	 * Navigate to Important Task
	 */
	@FindBy(xpath = "//*[@id=\"daily-overview-tasks-container\"]/div[1]/h2")
	public static WebElement headerofImportantTasks;
	
	
	/**
	 * Check user Concierge view Visitor Parking Report from Daily Overview module
	 */
	@FindBy(xpath = "//div[@id='visitor_report']/a")
	public static List<WebElement> listOfViewBtn;

	
	public boolean verifyUserConciergeViewVisitorParkingReport() throws Exception {
		SeleniumWrapper.explicitWaitClickable(driver, listOfViewBtn.get(0), 20);
		String parentWindow = driver.getWindowHandle();
		Function.hoverNclickElement(driver, listOfViewBtn.get(0));
		Function.switchToNewWindow(driver, parentWindow);

		String reportTitle = headerOfReport.getText();
		logger.info("The report title is :" + reportTitle);
		Assert.assertEquals("Current Visitor Parking Passes", reportTitle);

		int countOfRow = 0;
		List<String> spot = new ArrayList<>();
		List<String> unit = new ArrayList<>();
		List<String> resident = new ArrayList<>();

		for (int i = 0; i < columnSpot.size(); i++) {
			spot.add(columnSpot.get(i).getText());
			logger.info("Spot is:" + spot);
		}

		for (int i = 0; i < columnUnit.size(); i++) {
			unit.add(columnUnit.get(i).getText());
			logger.info("Unit is:" + unit);
		}

		for (int i = 0; i < columnResident.size(); i++) {
			resident.add(columnResident.get(i).getText());
			logger.info("Resident is:" + resident);
		}

		countOfRow = columnSpot.size();
		logger.info("total count is: " + countOfRow);

		Function.closeNSwitchWindow(driver, parentWindow);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if(spot.contains("P001")
				&& unit.contains("101")
				&& resident.contains("Daniels Qu")) {
			return true;
		}
		return false;
	}

	
	
	
	
	
	
	/**
	 * Check Current Visitor Parking Passes report
	 */
	@FindBy(xpath = "//*[@id='header-reports']/div[2]/h2")
	public static WebElement headerOfReport;

	@FindBy(xpath = "//*[@id='datatable_reports_wrapper']/div[2]/div[1]/div/table/thead/tr/th")
	public static List<WebElement> listOfcolumnHeader;

	@FindBy(xpath = "//*[@id='datatable_reports']/tbody/tr[1]/td")
	public static List<WebElement> listOfcolumnBody;

	@FindBy(xpath = "//*[@id='datatable_reports']/tbody/tr/td[2]")
	public static List<WebElement> columnSpot;

	@FindBy(xpath = "//*[@id='datatable_reports']/tbody/tr/td[7]")
	public static List<WebElement> columnUnit;

	@FindBy(xpath = "//*[@id='datatable_reports']/tbody/tr/td[8]")
	public static List<WebElement> columnResident;

	public boolean userPMViewVisitorParkingReport() throws Exception {
		SeleniumWrapper.waitForDomToBeRendered(driver);
		String parentWindow = driver.getWindowHandle();
		Function.hoverNclickElement(driver, listOfViewBtn.get(0));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.switchToNewWindow(driver, parentWindow);
		String reportTitle = headerOfReport.getText();
		logger.info("The report title is :" + reportTitle);
		Assert.assertEquals("Current Visitor Parking Passes", reportTitle);

		/*
		 * 
		 * WebElement table = driver.findElement(By.xpath(
		 * "//*[@id='datatable_reports_wrapper']/div[2]/div[2]")); List<WebElement>
		 * rowsList = table.findElements(By.tagName("tr")); List<WebElement> columnsList
		 * = null; for (WebElement row : rowsList) { countOfRow = rowsList.size();
		 * logger.info("count of rows:" + countOfRow); // print 6, why ? columnsList =
		 * row.findElements(By.tagName("td")); for (WebElement column : columnsList) {
		 * logger.info(column.getText() + ", "); SeleniumWrapper.threadSleep(500); } }
		 */

		int countOfRow = 0;
		List<String> spot = new ArrayList<>();
		List<String> unit = new ArrayList<>();
		List<String> resident = new ArrayList<>();

		for (int i = 0; i < columnSpot.size(); i++) {
			spot.add(columnSpot.get(i).getText());
			logger.info("Spot is:" + spot);
		}

		for (int i = 0; i < columnUnit.size(); i++) {
			unit.add(columnUnit.get(i).getText());
			logger.info("Unit is:" + unit);
		}

		for (int i = 0; i < columnResident.size(); i++) {
			resident.add(columnResident.get(i).getText());
			logger.info("Resident is:" + resident);
		}

		countOfRow = columnSpot.size();
		logger.info("total count is: " + countOfRow);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Assert.assertTrue(( spot.contains("P001")
				&& unit.contains("101") && unit.contains("Front Desk")
				&& resident.contains("Daniels Qu") && resident.contains("tina bear")));
	  return Function.closeNSwitchWindow(driver, parentWindow);
	}
	
}
