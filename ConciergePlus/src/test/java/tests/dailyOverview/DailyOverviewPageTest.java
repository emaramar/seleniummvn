package tests.dailyOverview;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import pages.BasePage;
import pages.LoginPage;
import pages.dailyOverview.DailyOverviewPage;

public class DailyOverviewPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(DailyOverviewPageTest.class.getName());
	protected BasePage basePage = null;
	protected DailyOverviewPage dailyOverviewPage = null;

	/**
	 * Test of navigating to Daily Overview Page
	 */
	@Test(priority = 1)
	public void gotoDailyOverviewPage() {
		test = extent.startTest("Navigate to Daily Overview Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		basePage.navigateTo();
		dailyOverviewPage = basePage.gotoDailyOverviewPage();
		Assert.assertNotNull(dailyOverviewPage);
		Assert.assertNotNull(dailyOverviewPage.isLoaded());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Test of user PM view Visitor Parking Report from Daily Overview module
	 * 
	 * @throws Exception
	 */
	//@Test(priority = 33)
	public void verifyUserPMViewVisitorParkingPassesReport() throws Exception {
		test = extent.startTest("Verify user PM view Visitor Parking Report from Daily Overview module");
		    basePage.navigateTo();
		Assert.assertTrue(dailyOverviewPage.userPMViewVisitorParkingReport() );
	}
}
