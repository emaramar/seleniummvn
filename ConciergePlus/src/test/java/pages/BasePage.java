package pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;
import pages.LoginPage;
import pages.communication.AnnouncementsPage;
import pages.communication.ClassifiedAdsPage;
import pages.communication.NewsletterPage;
import pages.dailyOverview.DailyOverviewPage;
import pages.dailyOverview.NotesSec;
import pages.dailyOverview.UnitProfilePage;
import pages.maintenance.EntryInstructionsPage;
import pages.maintenance.ServiceRequestsPage;
import pages.resources.*;
import pages.site__settings.SiteAdministrationPage;

/**
*BasePage is considered as super class of modules
*Logged in user
*/
public class BasePage extends LoginPage {
	protected final static Logger logger = LogManager.getLogger(BasePage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public BasePage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("BasePage is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return super.login(super.userName, super.password);
	}
	
	@Override
	public boolean isLoaded() {
		if(companyLogo.isEnabled()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check basepage company log present
	 */
	
	@FindBy(xpath = "//*[@id='cp-main-logo']")
	public static WebElement companyLogo;
	
	public boolean isCompanyLogoImageDisplay() {
		SeleniumWrapper.explicitWaitClickable(driver, companyLogo, 30);
	    Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", companyLogo);
	        if (!ImagePresent)
	        {
	          logger.info("Image not displayed.");
	             return false;
	        }
	        else
	        {
	           logger.info("Image displayed.");
	            return true;
	        }
	}
	
	
	/**
	 * Log out as current user
	 */
	public boolean logOutAsCurrentUser(WebDriver driver) {
		WebElement currentLoginUser = driver.findElement(By.xpath("//*[@class='display_name']"));
		WebElement logoutBtn = driver.findElement(By.xpath("//a[@class='concierge_logout']"));
		try {
			Function.clickElement(driver, currentLoginUser);
			Function.clickElement(driver, logoutBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	/**
	 * Log in as Resident user "Daniels Qu"
	 */
	public  boolean logInAsResidentUser(WebDriver driver) {
		WebElement usernameBox = driver.findElement(By.xpath(".//*[@id='username']"));
		WebElement passwordBox = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		try {
			Function.clickElement(driver, usernameBox);
			SeleniumWrapper.setInputFieldText(usernameBox, "danielsq9@outlook.com", driver);

			Function.clickElement(driver, passwordBox);
			SeleniumWrapper.setInputFieldText(passwordBox, "12345678", driver);

			Function.clickElement(driver, loginBtn);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}	
	
	/**
	 * Log in as Concierge user "tina bear"
	 */
	public boolean logInAsConciergeUser(WebDriver driver) {
		WebElement usernameBox = driver.findElement(By.xpath(".//*[@id='username']"));
		WebElement passwordBox = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		try {
			Function.clickElement(driver, usernameBox);
			SeleniumWrapper.setInputFieldText(usernameBox, "joyshaw9@outlook.com", driver);

			Function.clickElement(driver, passwordBox);
			SeleniumWrapper.setInputFieldText(passwordBox, "12345678", driver);

			Function.clickElement(driver, loginBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);

			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	/**
	 * Check user Concierge "tina bear" clock in
	 */
	public boolean userConciergeClockIn(WebDriver driver) {
		WebElement clockInBtn = driver.findElement(By.xpath("//*[@id='modal-concierge-clockin-button']"));
		try {
			Function.hoverNclickElement(driver, clockInBtn);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	
	/**
	 * Check user Concierge "tina bear" clock out
	 */
	public boolean userConciergeClockOut(WebDriver driver) {
		WebElement clockOutBtn = driver.findElement(By.xpath("//*[@id='modal-concierge-clockout-button']"));
		try {
			Function.hoverNclickElement(driver, clockOutBtn);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	
	
		
	/**
	 * Check user is at DailyOverview Page
	 */
	@FindBy(xpath = "//h1[contains(text(),'Daily Overview')]")
	public static WebElement dailyOverviewHeader;

	public boolean isAtDailyOverviewPage() {
		SeleniumWrapper.explicitWaitClickable(driver, dailyOverviewHeader, 30);
		return dailyOverviewHeader.getText().contains("Daily Overview");
	}
	
	
	/**
	 * Navigate to Deliveries page
	 */
	
	@FindBy(xpath = "//a[@href ='/deliveries']/span[2]")
	public static WebElement deliveriesPage;

	
	public DeliveriesPage gotoDeliveriesPage() {
		SeleniumWrapper.explicitWaitClickable(driver, deliveriesPage, 30);
		if (SeleniumWrapper.clickElement(driver, deliveriesPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new DeliveriesPage(driver, URL, userName, password );
		return null;
	}
	
	
	/**
	 * Navigate to Daily Overview page
	 */
	
	@FindBy(xpath = "//a[@href ='/daily_overview/']/span[2]")
	public static WebElement dailyOverviewPage;

	
	public DailyOverviewPage gotoDailyOverviewPage() {
		SeleniumWrapper.explicitWaitClickable(driver, dailyOverviewPage, 30);
		if (SeleniumWrapper.clickElement(driver, dailyOverviewPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new DailyOverviewPage(driver, URL, userName, password );
		return null;
	}
	
	
	/**
	 * Navigate to Notes from Daily Overview page
	 */
	public NotesSec gotoNotesSec() {
		SeleniumWrapper.explicitWaitClickable(driver, dailyOverviewPage, 30);
		if (SeleniumWrapper.clickElement(driver, dailyOverviewPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new NotesSec(driver, URL, userName, password );
		return null;
	}
	
	/**
	 * Navigate to Unit Profile from Daily Overview page
	 */
	public UnitProfilePage gotoUnitProfilePage() {
		SeleniumWrapper.explicitWaitClickable(driver, dailyOverviewPage, 30);
		if (SeleniumWrapper.clickElement(driver, dailyOverviewPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new UnitProfilePage(driver, URL, userName, password );
		return null;
	}
	
	/**
	 * Navigate to Announcements page
	 */
	
	@FindBy(xpath = "//a[@href ='/announcements']/span[2]")
	public static WebElement announcementsPage;

	
	public AnnouncementsPage gotoAnnouncementsPage() {
		Function.clickElement(driver, communicationTab);
		SeleniumWrapper.explicitWaitClickable(driver,  announcementsPage, 30);
		if (SeleniumWrapper.clickElement(driver,  announcementsPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new AnnouncementsPage(driver, URL, userName, password );
		return null;
	}
	
	
	
	/**
	 * Navigate to Service Requests page
	 */
	@FindBy(xpath = "//*[contains(.,'Maintenance')]/span[2]")
	public static WebElement MaintenanceTab;
	
	@FindBy(xpath = "//a[@href ='/service_requests']/span[2]")
	public static WebElement serviceRequestsPage;

	public ServiceRequestsPage gotoServiceRequestsPage() {
		Function.clickElement(driver, MaintenanceTab);
		SeleniumWrapper.explicitWaitClickable(driver, serviceRequestsPage, 30);
		if (SeleniumWrapper.clickElement(driver, serviceRequestsPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new ServiceRequestsPage(driver, URL, userName, password);
		return null;
	}

	/**
	 * Navigate to Amenity Booking page
	 */
	@FindBy(xpath = "//a[@href ='/amenity_booking']/span[2]")
	public static WebElement amenityBookingPage;
	
	public AmenityBookingPage gotoAmenityBookingPage(){
		Function.clickElement(driver, ResourcesTab);
		SeleniumWrapper.explicitWaitClickable(driver, amenityBookingPage, 30);
		if (SeleniumWrapper.clickElement(driver, amenityBookingPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new AmenityBookingPage(driver, URL, userName, password);
		return null;
	}	
	
	/**
	 * Navigate to Site Administration page
	 */
	@FindBy(xpath = "//a[@href ='/site_settings/']/span[2]")
	public static WebElement siteAdministrationPage;
	
	public SiteAdministrationPage gotoSiteAdministrationPage(){
		SeleniumWrapper.explicitWaitClickable(driver, siteAdministrationPage, 50);
		if (SeleniumWrapper.clickElement(driver, siteAdministrationPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new SiteAdministrationPage(driver, URL, userName, password);
		return null;
	}

	/**
	 * Navigate to Media Library page
	 */
	@FindBy(xpath = "//*[contains(.,'Resources')]/span[2]")
	public static WebElement ResourcesTab;
	
	@FindBy(xpath = "//a[@href ='/media_library']/span")
	public static WebElement mediaLibraryPage;
		
	public MediaLibraryPage gotoMediaLibraryPage(){
		Function.clickElement(driver, ResourcesTab);
		SeleniumWrapper.explicitWaitClickable(driver, mediaLibraryPage, 30);
		if (SeleniumWrapper.clickElement(driver, mediaLibraryPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new MediaLibraryPage(driver, URL, userName, password);
		return null;
	}
	
	
	/**
	 * Navigate to Incident Reports page
	 */
	@FindBy(xpath = "//a[@href='/incident_reports']/span[2]")
	public static WebElement incidentReportsPage;
	
		
	public IncidentReportsPage gotoIncidentReportsPage(){
		SeleniumWrapper.explicitWaitClickable(driver, incidentReportsPage, 30);
		if (SeleniumWrapper.clickElement(driver, incidentReportsPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new IncidentReportsPage(driver, URL, userName, password);
		return null;
	}
	

	/**
	 * Navigate to Entry Instruction page
	 */
	@FindBy(xpath = "//a[@href='/entry_instructions']/span[2]")
	public static WebElement entryInstructionsPage;
	
		
	public EntryInstructionsPage gotoEntryInstructionsPage(){
		Function.clickElement(driver, MaintenanceTab);
		SeleniumWrapper.explicitWaitClickable(driver, entryInstructionsPage, 30);
		if (SeleniumWrapper.clickElement(driver, entryInstructionsPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new EntryInstructionsPage(driver, URL, userName, password);
		return null;
	}
	
	/**
	 * Navigate to Residents Guide page
	 */
	@FindBy(xpath = "//a[@href='/resident_guide']/span[2]")
	public static WebElement residentsGuidePage;

	public ResidentsGuidePage gotoResidentsGuidePage() {
		Function.clickElement(driver, ResourcesTab);
		SeleniumWrapper.explicitWaitClickable(driver, residentsGuidePage, 30);
		if (SeleniumWrapper.clickElement(driver, residentsGuidePage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new ResidentsGuidePage(driver, URL, userName, password);
		return null;
	}
	
	/**
	 * Navigate to Classified Ads page
	 */
	@FindBy(xpath = "//*[contains(.,'Communication')]/span[2]")
	public static WebElement communicationTab;
	
	@FindBy(xpath = "//a[@href='/classified_ads']/span[2]")
	public static WebElement classifiedAdsPage;

	public ClassifiedAdsPage gotoClassifiedAdsPage() {
		Function.clickElement(driver, communicationTab);
		SeleniumWrapper.explicitWaitClickable(driver, classifiedAdsPage, 30);
		if (SeleniumWrapper.clickElement(driver, classifiedAdsPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new ClassifiedAdsPage(driver, URL, userName, password);
		return null;
	}
	
	
	/**
	 * Navigate to Newsletter page
	 */
	@FindBy(xpath = "//a[@href='/newsletter']/span[2]")
	public static WebElement newsletterPage;

	public NewsletterPage gotoNewsletterPage() {
		Function.clickElement(driver, communicationTab);
		SeleniumWrapper.explicitWaitClickable(driver, newsletterPage, 30);
		if (SeleniumWrapper.clickElement(driver, newsletterPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new NewsletterPage(driver, URL, userName, password);
		return null;
	}
	
	/**
	 * Navigate to Pass On Log page
	 */
	@FindBy(xpath = "//a[@href='/pol']/span[2]")
	public static WebElement passOnLogPage;

	public PassOnLogPage gotoPassOnLogPage() {
		SeleniumWrapper.explicitWaitClickable(driver, passOnLogPage, 30);
		if (SeleniumWrapper.clickElement(driver, passOnLogPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new PassOnLogPage(driver, URL, userName, password);
		return null;
	}

	
	
	/**
	 * Navigate to Community Schedule page
	 */
	@FindBy(xpath = "//*[@href='/community_schedule']/span[2]")
	public static WebElement communitySchedulePage;

	public CommunitySchedulePage gotoCommunitySchedulePage() {
		Function.clickElement(driver, ResourcesTab);
		SeleniumWrapper.explicitWaitClickable(driver, communitySchedulePage, 30);
		if (SeleniumWrapper.clickElement(driver, communitySchedulePage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new CommunitySchedulePage(driver, URL, userName, password);
		return null;
	}
	
	
	public CommunitySchedulePage goBackCommunitySchedulePage() {
		SeleniumWrapper.explicitWaitClickable(driver, communitySchedulePage, 30);
		if (SeleniumWrapper.clickElement(driver, communitySchedulePage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new CommunitySchedulePage(driver, URL, userName, password);
		return null;
	}
	
	
	/**
	 * Navigate to Task page
	 */
	@FindBy(xpath = "//*[@href='/tsk']/span[2]")
	public static WebElement taskPage;

	public TaskPage gotoTaskPage() {
		SeleniumWrapper.explicitWaitClickable(driver, taskPage, 30);
		if (SeleniumWrapper.clickElement(driver, taskPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new TaskPage(driver, URL, userName, password);
		return null;
	}
	
	

	/**
	 * Navigate to Pet Registry Page
	 */
	@FindBy(xpath = "//*[@href='/pets']/span[2]")
	public static WebElement petRegistryPage;

	public PetRegistryPage gotoPetRegistryPage() {
		Function.clickElement(driver, ResourcesTab);
		SeleniumWrapper.explicitWaitClickable(driver, petRegistryPage, 30);
		if (SeleniumWrapper.clickElement(driver, petRegistryPage, Constants.CLICK_METHOD_ENUM.CLICK))
			return new PetRegistryPage(driver, URL, userName, password);
		return null;
	}


	
}