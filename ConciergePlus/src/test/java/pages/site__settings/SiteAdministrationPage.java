package pages.site__settings;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.SeleniumWrapper;
import config.Constants;
import pages.BasePage;

public class SiteAdministrationPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(SiteAdministrationPage.class.getName());
	
	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public SiteAdministrationPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info(" Site Administration Page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoSiteAdministrationPage().equals(null);
	}
	
	@Override
	public boolean isLoaded() {
		if (communitySettingsTab.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	@FindBy(xpath = "//*[@id='community_settings_container']/span")
	public static WebElement communitySettingsTab;

	
	/**
	 * Navigate to Online Payments page
	 */
	@FindBy(xpath = "//a[@href ='/online_payments/']/div/span[2]")
	public static WebElement onlinePaymentsTab;
	
	public OnlinePaymentsTab gotoOnlinePaymentsTab() {
		SeleniumWrapper.explicitWaitClickable(driver,  onlinePaymentsTab, 30);
		if (SeleniumWrapper.clickElement(driver, onlinePaymentsTab, Constants.CLICK_METHOD_ENUM.CLICK))
			return new OnlinePaymentsTab(driver, URL, userName, password);
		return null;
	}
	

	/**
	 * Navigate to Parking page
	 */
	@FindBy(xpath = "//a[@href='/parking/']/div/span[2]")
	public static WebElement parkingPage;

	public ParkingPage gotoParkingPage() {
		SeleniumWrapper.explicitWaitClickable(driver, parkingPage, 30);
		if (SeleniumWrapper.clickElement(driver, parkingPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new ParkingPage(driver, URL, userName, password);
		return null;
	}


	/**
	 * Navigate to Visitor Parking page
	 */
	@FindBy(xpath = "//a[@href='/visitor_parking/']/div/span[2]")
	public static WebElement visitorParkingPage;

	public VisitorParkingPage gotoVisitorParkingPage() {
		SeleniumWrapper.explicitWaitClickable(driver, visitorParkingPage, 30);
		if (SeleniumWrapper.clickElement(driver, visitorParkingPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new VisitorParkingPage(driver, URL, userName, password);
		return null;
	}



	/**
	 * Navigate to Units page
	 */
	@FindBy(xpath = "//a[@href='/unit_manager/']/div/span[2]")
	public static WebElement unitsPage;

	public UnitsPage gotoUnitsPage() {
		SeleniumWrapper.explicitWaitClickable(driver,  unitsPage, 30);
		if (SeleniumWrapper.clickElement(driver, unitsPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new UnitsPage(driver, URL, userName, password);
		return null;
	}
	
	
	/**
	 * Navigate to Users page
	 */
	@FindBy(xpath = "//a[@href='/users/']/div/span[2]")
	public static WebElement usersPage;

	public UsersPage gotoUsersPage() {
		SeleniumWrapper.explicitWaitClickable(driver,  usersPage, 30);
		if (SeleniumWrapper.clickElement(driver, usersPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new UsersPage(driver, URL, userName, password);
		return null;
	}



}
