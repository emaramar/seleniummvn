package tests.communication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.communication.NewsletterPage;

public class NewsletterPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(NewsletterPageTest.class.getName());
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
	 * Test of the "Message" field is mandatory
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void verifyIsMessageFieldMandatory() {
		test = extent.startTest("Verify the 'Message' field is mandatory");
		if (newsletterPage.clickCreateNewsletterBtn())
			if (newsletterPage.clickSaveNPreviewBtn())
				if (newsletterPage.isErrorMsgPresent())
					Assert.assertTrue(newsletterPage.clickCancelBtn());
	}

	/**
	 * Test of create new newsletter and verify new created newsletter is present on
	 * preview page
	 */
	@Test(priority = 5)
	public void verifyCreateNewNewsletterNVerifyPresenceOnPreviewPage() {
		test = extent.startTest("Verify create newsletter and verify newsletter is present on preview page");
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
	 * Test of create new newsletter with uploading image
	 */
	@Test(priority = 7)
	public void verifyCreateNewNewsletterWithImg() {
		test = extent.startTest("Verify create new newsletter with image");
		if (newsletterPage.clickCreateNewsletterBtn())
			if (newsletterPage.clickAddGroupBtn())
				if (newsletterPage.chooseGroup())
					if (newsletterPage.enterTitle2())
						if (newsletterPage.clickTextBox2())
							if (newsletterPage.enterMessage())
								if (newsletterPage.clickSelectImageFromMediaLibraryBtn())
									if (newsletterPage.selectImage())
										Assert.assertTrue(newsletterPage.clickSaveNPreviewBtn());
	}

	/**
	 * Test of create new newsletter without image
	 */
	@Test(priority = 9)
	public void verifyCreateNewNewsletterWithoutImg() {
		test = extent.startTest("Verify create new newsletter without image");
		if (newsletterPage.clickCreateNewsletterBtn())
			if (newsletterPage.clickAddGroupBtn())
				if (newsletterPage.chooseGroup())
					if (newsletterPage.enterTitle2())
						if (newsletterPage.clickTextBox2())
							if (newsletterPage.enterMessage())
								Assert.assertTrue(newsletterPage.clickSaveNPreviewBtn());
	}

	/**
	 * Test of view newsletter from View Tab
	 */
	@Test(priority = 11)
	public void verifyViewNewsletterFromViewTab() {
		test = extent.startTest("Verify view newsletter from View Tab");
		if (newsletterPage.clickToOpenNewsletter())
			Assert.assertTrue(newsletterPage.viewNewsletterInfo());
	}

	/**
	 * Test of edit newsletter from View Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void verifyEditNewsletterFromViewTab() throws Exception {
		SeleniumWrapper.refreshWebPage(driver);
		test = extent.startTest("Verify edit newsletter from View Tab");
		if (newsletterPage.clickToOpenNewsletter())
			if (newsletterPage.clickEditIcon())
				if (newsletterPage.clickTextBox1())
					if (newsletterPage.editMessage())
						Assert.assertTrue(newsletterPage.clickSaveBtn());
	}

	/**
	 * Test of delete newsletter from View Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void verifyDeleteNewsletterFromViewTab() throws Exception {
		test = extent.startTest("Verify delete newsletter from View Tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (newsletterPage.clickToOpenNewsletter())
			if (newsletterPage.clickDeleteIconFromViewTab())
				Assert.assertTrue(newsletterPage.clickConfirmBtn());
	}
	

	/**
	 * Test of search newsletter from View Tab
	 */
	@Test(priority = 17)
	public void verifySearchNewsletterFromViewTab() {
		test = extent.startTest("Verify search newsletter from View Tab");
		Assert.assertTrue(newsletterPage.searchNewsletter());
	}

	/**
	 * Test of "View All Newsletters" from View Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void verifyViewAllNewslettersFromViewTab() throws Exception {
		test = extent.startTest("Verify 'View All Newsletters' from View Tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (newsletterPage.clickViewAllNewsletters())
			Assert.assertTrue(newsletterPage.isAtListTab());
	}

	/**
	 * Test of verify status of the new newsletter
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void verifyStatusOfNewNewsletter() throws Exception {
		test = extent.startTest("Verify status of the new newsletter");
		SeleniumWrapper.refreshWebPage(driver);
		if (newsletterPage.gotoListTab())
			Assert.assertTrue(newsletterPage.isStatusDraft());
	}

	/**
	 * Test of publish newsletter
	 * 
	 * @throws Exception
	 */
	@Test(priority = 23)
	public void verifyPublishDraftNewsletter() throws Exception {
		test = extent.startTest("Verify publish newsletter");
		SeleniumWrapper.refreshWebPage(driver);
		if (newsletterPage.gotoListTab())
			if (newsletterPage.openDraftNewsletter())
				if (newsletterPage.viewNewsletterInfo())
					if (newsletterPage.publicDraftNewsletter())
						Assert.assertTrue(newsletterPage.isSuccessMsgPresent());
	}

	/**
	 * Test of send newsletter by email
	 */
	@Test(priority = 25)
	public void verifySendNewsletterByEmail() {
		test = extent.startTest("Verify send newsletter by email");
		if (newsletterPage.clickSendNewsletterByEmail())
			if (newsletterPage.clickSendBtn())
				Assert.assertTrue(newsletterPage.isSuccessMsgPresent());
	}

	/**
	 * Test of Publish & Send Email Draft newsletter
	 */
	@Test(priority = 27)
	public void verifyPublishNSendEmailDraftNewsletter() {
		test = extent.startTest("Verify Publish & Send Email draft newsletter");
		if (newsletterPage.gotoListTab())
			if (newsletterPage.openDraftNewsletter())
				if (newsletterPage.viewNewsletterInfo())
					if (newsletterPage.publicNSendEmailDraftNewsletter())
						Assert.assertTrue(newsletterPage.clickConfirmBtn());
	}

	/**
	 * Test of open newsletter by clicking newsletter title
	 */
	@Test(priority = 29)
	public void verifyOpenNewsletterByClickingNewsletterTitle() {
		test = extent.startTest("Verify open newsletter by clicking newsletter title");
		if (newsletterPage.gotoListTab())
			if (newsletterPage.openNewsletterByClickingTitle())
				Assert.assertTrue(newsletterPage.viewNewsletterInfo());
	}

	/**
	 * Test of edit "Published" status newsletter from List Tab
	 */
	@Test(priority = 31)
	public void verifyEditPublishedNewsletterFromListTab() {
		test = extent.startTest("Verify edit 'Published' newsletter from List Tab");
		if (newsletterPage.gotoListTab())
			if (newsletterPage.clickEditBtnOfPublishedNewsletterFromListTab())
				if (newsletterPage.clickTextBox1())
					if (newsletterPage.editMessage())
						Assert.assertTrue(newsletterPage.clickSaveBtn());
	}

	/**
	 * Test of edit "Draft" status newsletter from List Tab
	 */
	@Test(priority = 33)
	public void verifyEditDraftNewsletterFromListTab() {
		test = extent.startTest("Verify edit 'Draft' newsletter from List Tab");
		if (newsletterPage.gotoListTab())
			if (newsletterPage.clickEditBtnOfDraftNewsletterFromListTab())
				if (newsletterPage.clickTextBox1())
					if (newsletterPage.editMessage())
						Assert.assertTrue(newsletterPage.clickSaveNPreviewBtn());
	}

	/**
	 * Test of edit and publish "Draft" status newsletter from List Tab
	 */
	@Test(priority = 35)
	public void verifyEditNPublishDraftNewsletterFromListTab() {
		test = extent.startTest("Verify edit and publish 'Draft' newsletter from List Tab");
		if (newsletterPage.gotoListTab())
			if (newsletterPage.clickEditBtnOfDraftNewsletterFromListTab())
				if (newsletterPage.clickTextBox1())
					if (newsletterPage.editMessage())
						Assert.assertTrue(newsletterPage.clickPublishDrafBtn());
	}

	/**
	 * Test of delete newsletter from List Tab
	 */
	@Test(priority = 37)
	public void verifyDeleteNewsletterFromListTab() {
		test = extent.startTest("Verify delete newsletter from List Tab");
		if (newsletterPage.gotoListTab())
			if (newsletterPage.clickDeleteBtnFromListTab())
				Assert.assertTrue(newsletterPage.clickConfirmDeleteBtn());
	}

	/**
	 * Test of search newsletter from List Tab
	 */
	@Test(priority = 39)
	public void verifySearchNewsletterFromListTab() {
		test = extent.startTest("Verify search newsletter from List Tab");
		if (newsletterPage.gotoListTab())
			Assert.assertTrue(newsletterPage.searchNewsletterFromListTab());
	}

}
