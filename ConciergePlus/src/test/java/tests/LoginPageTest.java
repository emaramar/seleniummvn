package tests;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.templates.GuiTestCase;

import pages.LoginPage;

public class LoginPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(LoginPageTest.class.getName());
	protected LoginPage loginPage = null;

	/**
	 * Test of navigating to LoginPage
	 */
	@Test(priority = 1)
	public void gotoLoginPage() {
		test = extent.startTest("Navigate to LoginPage");
		loginPage = new LoginPage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(loginPage.navigateTo());
		Assert.assertTrue(loginPage.isLoaded());
	}
	
	/**
	 * Test of navigating to LoginPage
	 */
	@Test(priority = 3)
	public void loginApp() {
		test = extent.startTest("Verify login application");
		Assert.assertTrue(loginPage.login(userName, password));
	}
	
	/**
	 * Test of navigating to LoginPage
	 */
	@Test(priority = 5)
	public void logoutApp() {
		test = extent.startTest("Verify logout application");
		Assert.assertTrue(loginPage.logout());
	}
}
