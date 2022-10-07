package data.tests.communication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import pages.BasePage;
import pages.communication.NewsletterPage;

public class NewsletterTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(NewsletterTest.class.getName());
	protected BasePage basePage = null;
	protected NewsletterPage newsletterPage = null;

	/**
	 * Test of navigating to Newsletter Page
	 */
	@Test(priority = 1)
	public void gotoNewsletterPage() {
		test = extent.startTest("Navigate to Newsletter Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		newsletterPage = basePage.gotoNewsletterPage();
		Assert.assertNotNull(newsletterPage);
	}

	/**
	 * Test of create new newsletter
	 */
	@Test(priority = 3)
	public void verifyCreateNewNewsletter() {
		test = extent.startTest("Verify create new newsletter");
		if (newsletterPage.clickCreateNewsletterBtn())
			if (newsletterPage.clickAddGroupBtn())
				if (newsletterPage.chooseGroup())
					if (newsletterPage.enterTitle1())
						if (newsletterPage.clickTextBox2())
							if (newsletterPage.enterMessage())
								if (newsletterPage.clickSelectImageFromMediaLibraryBtn())
									if (newsletterPage.selectImage())
										if (newsletterPage.clickSaveNPreviewBtn())
											Assert.assertTrue(newsletterPage.isPresentOnPreviewPage());
	}

	/**
	 * Test of create another new newsletter preview page
	 */
	@Test(priority = 5)
	public void verifyCreateAnotherNewNewsletter() {
		test = extent.startTest("Verify create another new newsletter");
		if (newsletterPage.clickCreateNewsletterBtn())
			if (newsletterPage.clickAddGroupBtn())
				if (newsletterPage.chooseGroup())
					if (newsletterPage.enterTitle2())
						if (newsletterPage.clickTextBox2())
							if (newsletterPage.enterMessage())
								Assert.assertTrue(newsletterPage.clickSaveNPreviewBtn());
	}

}
