package tests.resources;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.resources.ResidentsGuidePage;

public class ResidentsGuidePageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(ResidentsGuidePageTest.class.getName());
	protected BasePage basePage = null;
	protected ResidentsGuidePage residentsGuidePage = null;

	/**
	 * Test of navigating to Residents Guide Page
	 */
	@Test(priority = 1)
	public void gotoResidentsGuidePage() {
		test = extent.startTest("Navigate to Residents Guide Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		residentsGuidePage = basePage.gotoResidentsGuidePage();
		Assert.assertNotNull(residentsGuidePage);
		Assert.assertTrue(residentsGuidePage.isLoaded());
	}

	/**
	 * Test of verify is "Title" field mandatory
	 */
	@Test(priority = 3)
	public void isTitleFieldMandatory() {
		test = extent.startTest("Verify is 'Title' field mandatory");
		if (residentsGuidePage.clickCreateGuideBtn())
			if (residentsGuidePage.clickContentBox())
				if (residentsGuidePage.enterContent())
					if (residentsGuidePage.clickSaveBtn())
						Assert.assertTrue(residentsGuidePage.isErrorMsgPresent());
	}

	/**
	 * Test of verify is "Content" field mandatory
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void isContentFieldMandatory() throws Exception {
		test = extent.startTest("Verify is 'Content' field mandatory");
		SeleniumWrapper.refreshWebPage(driver);
		if (residentsGuidePage.clickCreateGuideBtn())
			if (residentsGuidePage.clickTitleBox())
				if (residentsGuidePage.enterTitle())
					if (residentsGuidePage.clickSaveBtn())
						if (residentsGuidePage.isErrorMsgPresent())
							Assert.assertTrue(residentsGuidePage.clickCancelBtn());
	}

	/**
	 * Test of create new resident guide
	 */
	@Test(priority = 7)
	public void createNewGuide() {
		test = extent.startTest("Verify create new resident guide");
		if (residentsGuidePage.clickCreateGuideBtn())
			if (residentsGuidePage.clickTitleBox())
				if (residentsGuidePage.enterTitle())
					if (residentsGuidePage.clickContentBox())
						if (residentsGuidePage.enterContent())
							if (residentsGuidePage.clickSaveBtn())
								Assert.assertTrue(residentsGuidePage.isSuccessMsgPresent());
	}

	/**
	 * Test of search resident guide
	 */
	@Test(priority = 9)
	public void searchNewCreatedResidentGuide() {
		test = extent.startTest("Verify search  resident guide");
		Assert.assertTrue(residentsGuidePage.searchResidentGuide());
	}

	/**
	 * Test of click to open resident guide
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void openResidentGuide() throws Exception {
		test = extent.startTest("Verify click to open resident guide");
		SeleniumWrapper.refreshWebPage(driver);
		if (residentsGuidePage.clickToOpenResidentGuide())
			Assert.assertTrue(residentsGuidePage.clickCancelBtn());
	}

	/**
	 * Test of edit resident guide
	 */
	@Test(priority = 13)
	public void editResidentGuide() {
		test = extent.startTest("Verify edit resident guide");
		if (residentsGuidePage.clickToOpenResidentGuide())
			if (residentsGuidePage.clickEditBtn())
				if (residentsGuidePage.modifyContent())
					Assert.assertTrue(residentsGuidePage.clickSaveBtn());
	}

	/**
	 * Test of edit resident guide from pencil icon
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void editResidentGuideFromPencilIcon() throws Exception {
		test = extent.startTest("Verify edit resident guide from pencil icon");
		SeleniumWrapper.refreshWebPage(driver);
		if (residentsGuidePage.clickEditIcon())
			if (residentsGuidePage.clickTitleBox())
				if (residentsGuidePage.modifyTitle())
					Assert.assertTrue(residentsGuidePage.clickSaveBtn());
	}

	/**
	 * Test of delete resident guide from trash icon
	 * 
	 * @throws Exception
	 */
	@Test(priority = 17)
	public void deleteResidentGuide() throws Exception {
		test = extent.startTest("Verify delete resident guide");
		SeleniumWrapper.refreshWebPage(driver);
		if (residentsGuidePage.deleteResidentGuide())
			Assert.assertTrue(residentsGuidePage.isSuccessMsgPresent());
	}

}
