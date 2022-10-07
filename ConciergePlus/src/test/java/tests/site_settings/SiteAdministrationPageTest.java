package tests.site_settings;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.templates.GuiTestCase;

import pages.BasePage;
import pages.site__settings.SiteAdministrationPage;


public class SiteAdministrationPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(SiteAdministrationPage.class.getName());
	protected BasePage basePage = null;
	protected SiteAdministrationPage siteAdministrationPage = null;

	/**
	 * Test of navigating to Site Administration Page
	 */
	@Test(priority = 1)
	public void gotoSiteAdministrationPage() {
		test = extent.startTest("Navigate to Site Administration Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		basePage.navigateTo();
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		Assert.assertNotNull(siteAdministrationPage);
		Assert.assertNotNull(siteAdministrationPage.isLoaded());
	}
}
