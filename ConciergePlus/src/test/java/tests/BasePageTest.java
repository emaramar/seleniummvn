package tests;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import pages.BasePage;
import pages.LoginPage;

public class BasePageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(BasePageTest.class.getName());
	protected LoginPage loginPage = null;
	protected BasePage basePage = null;

	/**
	 * Test of navigating to BasePage
	 */
	@Test(priority = 1)
	public void gotoBasePage() {
		test = extent.startTest("Navigate to HomePage");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
	}

	/**
	 * Test of navigate to DailyOverview page
	 */
	@Test(priority = 3)
	public void testIsATDailyOverviewPage() {
		test = extent.startTest("Navigate to Daily Overview Page by default after logging in");
		Assert.assertTrue(basePage.isAtDailyOverviewPage());
	}

	 @Test(priority =5)
	public void verifyBasePageCompanyLogoDisplay() {
		test = extent.startTest("Verify basepage company logo display");
		Assert.assertTrue(basePage.isCompanyLogoImageDisplay());
	}
}
