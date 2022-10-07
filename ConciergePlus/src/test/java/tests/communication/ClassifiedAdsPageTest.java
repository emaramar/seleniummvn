package tests.communication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.communication.ClassifiedAdsPage;

public class ClassifiedAdsPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(ClassifiedAdsPageTest.class.getName());
	protected BasePage basePage = null;
	protected ClassifiedAdsPage classifiedAdsPage = null;

	/**
	 * Test of navigating to Classified Ads Page
	 */
	@Test(priority = 1)
	public void gotoClassifiedAdsPage() {
		test = extent.startTest("Navigate to Classified Ads Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		classifiedAdsPage = basePage.gotoClassifiedAdsPage();
		Assert.assertNotNull(classifiedAdsPage);
	}

	/**
	 * Test of user Resident log in application and navigate to Classified Ads menu
	 */
	@Test(priority = 3)
	public void verifyUserResidentLoginAppAndNavigateToMenu() {
		test = extent.startTest("Verify user Resident log in application and navigate to Classified Ads menu");
		if (classifiedAdsPage.logOutAsCurrentUser(driver))
			basePage.logInAsResidentUser(driver);
		Assert.assertTrue(classifiedAdsPage.navigateToClassifiedAdsMenu());
	}
	
	
	/**
	 * Test of the approved Ad is present on Homepage from user Resident's account
	 */
	@Test(priority = 5)
	public void verifyIsApproveAdPresentOnHomepage() {
		test = extent.startTest("Verify the approved Ad is present on Homepage");
		if (classifiedAdsPage.gotoHomepage())
		Assert.assertTrue(classifiedAdsPage.isApprovedAdPresentOnHomepage());
	}
	
	/**
	 * Test of the approved Ad is present on web table from user Resident's account
	 */
	@Test(priority = 7)
	public void verifyIsApproveAdPresentOnWebTable() {
		test = extent.startTest("Verify the approved Ad is present on web table");
		if (classifiedAdsPage.navigateToClassifiedAdsMenu())
			Assert.assertTrue(classifiedAdsPage.isApprovedAdPresentOnWebTable());
	}
	
	/**
	 * Test of user Resident reply an ad
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void verifyUserResidentReplyAd() throws Exception {
		test = extent.startTest("Verify user Resident reply an ad");
		SeleniumWrapper.refreshWebPage(driver);
		if (classifiedAdsPage.openAd())
			if (classifiedAdsPage.enterReplyMsg())
				Assert.assertTrue(classifiedAdsPage.clickSendEmailBtn());
	}

	/**
	 * Test of user Resident delete ad
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void verifyUserResidentDeleteAd() throws Exception {
		test = extent.startTest("Verify user Resident delete ad");
		SeleniumWrapper.refreshWebPage(driver);
		if (classifiedAdsPage.openNewAdOfSelling())
			if (classifiedAdsPage.clickDeleteBtn())
				if (classifiedAdsPage.clickConfirmDeleteBtn())
					Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}
	
	

	/**
	 * Test of the "Title" field is mandatory
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void verifyIsTitleFieldMandatory() {
		test = extent.startTest("Verify the 'Title' field is mandatory");
		if (classifiedAdsPage.clickCreateAdBtn())
			if (classifiedAdsPage.enterMessage())
				if (classifiedAdsPage.clickSaveAdBtn())
					if (classifiedAdsPage.isErrorMsgPresent())
					Assert.assertTrue(classifiedAdsPage.clickCancelBtn());
	}

	/**
	 * Test of the "Message" field is mandatory
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void verifyIsMessageFieldMandatory() {
		test = extent.startTest("Verify the 'Message' field is mandatory");
			if (classifiedAdsPage.clickCreateAdBtn())
				if (classifiedAdsPage.enterTitleOfvehicleForSell())
					if (classifiedAdsPage.clickSaveAdBtn())
						if (classifiedAdsPage.isErrorMsgPresent())
						Assert.assertTrue(classifiedAdsPage.clickCancelBtn());
	}

	/**
	 * Test of user Resident create new ad of "Used vehicle for sell"
	 * @throws Exception 
	 */
	@Test(priority = 17)
	public void verifyUserResidentCreateAdofVehicleForSell() throws Exception {
		test = extent.startTest("Verify user Resident create new  ad of 'Used vehicle for sell'");
		if (classifiedAdsPage.clickCreateAdBtn())
			if (classifiedAdsPage.selectCategoryOfVehicle())
				if (classifiedAdsPage.enterTitleOfvehicleForSell())
					if (classifiedAdsPage.enterPrice())
						if (classifiedAdsPage.setAdExpiryDate())
							if (classifiedAdsPage.enterMessage())
								if (classifiedAdsPage.uploadFile())
									if (classifiedAdsPage.clickSaveAdBtn())
										Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user Resident create new ad of "Look for used Stroller"
	 */
	@Test(priority = 19)
	public void verifyUserResidentCreateAdofBuyStroller() {
		test = extent.startTest("Verify user Resident create new ad of 'Look for used Stroller'");
		if (classifiedAdsPage.clickCreateAdBtn())
			if (classifiedAdsPage.selectTypeOfBuying())
				if (classifiedAdsPage.enterTitleOfBuyUsedStroller())
					if (classifiedAdsPage.selectPriceOfFree())
						if (classifiedAdsPage.enterMessage())
							if (classifiedAdsPage.clickSaveAdBtn())
								Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM log in application and navigate to Classified Ads menu
	 */
	@Test(priority = 21)
	public void verifyUserPMLogInAppAndNavigateToClassifiedAdsMenu() {
		test = extent.startTest("Verify user PM log in application and navigate to Classified Ads menu");
		if (classifiedAdsPage.logOutAsCurrentUser(driver))
			basePage.navigateTo();
		classifiedAdsPage = basePage.gotoClassifiedAdsPage();
		Assert.assertNotNull(classifiedAdsPage);
	}

	/**
	 * Test of verify status of the new ad created by user Resident
	 */
	@Test(priority = 23)
	public void verifyStatusOfNewAd() {
		test = extent.startTest("Verify status of the new ad created by user Resident");
		Assert.assertTrue(classifiedAdsPage.isStatusPendingApproval());
	}

	/**
	 * Test of verify title and price of the new ad (Look for used Stroller) created
	 * by user Resident
	 */
	@Test(priority = 25)
	public void verifyTitleAndPriceOfNewAd_Buying() {
		test = extent.startTest("Verify title and price of the new ad (Buying) created by user Resident");
		if (classifiedAdsPage.openNewAdOfBuying())
			if (classifiedAdsPage.verifyTitleAndPriceOfNewAd_Buying())
			Assert.assertTrue(classifiedAdsPage.goBackToClassifiedAdsTab());
	}

	/**
	 * Test of verify title and price of the new ad (Used Honda Civi for sell)
	 * created by user Resident
	 */
	@Test(priority = 27)
	public void verifyTitleAndPriceOfNewAd_Selling() {
		test = extent.startTest("Verify title and price of the new ad (Selling) created by user Resident");
			if (classifiedAdsPage.openNewAdOfSelling())
				if (classifiedAdsPage.verifyTitleAndPriceOfNewAd_Selling())
				Assert.assertTrue(classifiedAdsPage.goBackToClassifiedAdsTab());
	}
	
	/**
	 * Test of verify user PM deny & delete new ad
	 */
	@Test(priority = 29)
	public void verifyUserPMDenyAndDeleteNewAd() {
		test = extent.startTest("Verify user PM deny & delete new ad");
			if (classifiedAdsPage.openNewAdOfBuying())
				if (classifiedAdsPage.clickDenyAndDeleteBtn())
					if (classifiedAdsPage.enterDenyMsg())
						Assert.assertTrue(classifiedAdsPage.clickConfirmDenyNDeleteBtn());
	}

	/**
	 * Test of verify user PM approve new ad
	 */
	@Test(priority = 31)
	public void verifyUserPMApproveNewAd() {
		test = extent.startTest("Verify user PM approve new ad");
			if (classifiedAdsPage.openNewAdOfSelling())
				if (classifiedAdsPage.clickApproveAdBtn())
					if (classifiedAdsPage.clickConfirmBtn())
					Assert.assertTrue(classifiedAdsPage.goBackToClassifiedAdsTab());
	}


	/**
	 * Test of the "Your Reply" field is mandatory
	 */
	@Test(priority = 33)
	public void verifyisYourReplyFieldMandatory() {
		test = extent.startTest("Verify the 'Your Reply' field is mandatory");
			if (classifiedAdsPage.openNewAdOfSelling())
				if (classifiedAdsPage.clickSendEmailBtn())
					if (classifiedAdsPage.isErrorMsgPresent())
					Assert.assertTrue(classifiedAdsPage.goBackToClassifiedAdsTab());
	}

	/**
	 * Test of user PM reply an ad
	 */
	@Test(priority = 35)
	public void verifyUserPMReplyAd() {
		test = extent.startTest("Verify user PM reply an ad");
			if (classifiedAdsPage.openNewAdOfSelling())
				if (classifiedAdsPage.enterReplyMsg())
					Assert.assertTrue(classifiedAdsPage.clickSendEmailBtn());
	}

	/**
	 * Test of the "Unit" field is mandatory
	 */
	@Test(priority = 37)
	public void verifyIsUnitFieldMandatory() {
		test = extent.startTest("Verify the 'Unit' field is mandatory");
		if (classifiedAdsPage.clickCreateAdBtn())
			if (classifiedAdsPage.enterTitleOfHouseForRent())
				if (classifiedAdsPage.enterMessage())
					if (classifiedAdsPage.clickSaveAdBtn())
						if (classifiedAdsPage.isErrorMsgPresent())
						Assert.assertTrue(classifiedAdsPage.clickCancelBtn());
	}

	/**
	 * Test of user PM create new ad of "house for rent"
	 * 
	 * @throws Exception
	 */
	@Test(priority = 39)
	public void verifyUserPMCreateAdofHouseForRent() throws Exception {
		test = extent.startTest("Verify user PM create new  ad of 'house for rent'");
		if (classifiedAdsPage.clickCreateAdBtn())
			if (classifiedAdsPage.enterUnit())
				if (classifiedAdsPage.selectCategoryOfHousing())
					if (classifiedAdsPage.selectTypeOfRenting())
						if (classifiedAdsPage.enterTitleOfHouseForRent())
							if (classifiedAdsPage.enterPrice())
								if (classifiedAdsPage.setAdExpiryDate())
									if (classifiedAdsPage.enterMessage())
										if (classifiedAdsPage.uploadFile())
											if (classifiedAdsPage.clickSaveAdBtn())
												Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM create new ad of "hire nanny"
	 */
	@Test(priority = 41)
	public void verifyUserPMCreateAdofHireNanny() {
		test = extent.startTest("Verify user PM create new ad of 'hire nanny'");
		if (classifiedAdsPage.clickCreateAdBtn())
			if (classifiedAdsPage.enterUnit())
				if (classifiedAdsPage.selectCategoryOfServices())
					if (classifiedAdsPage.enterTitleOfServiceForHire())
						if (classifiedAdsPage.selectPriceOfPleaseContact())
							if (classifiedAdsPage.enterMessage())
								if (classifiedAdsPage.clickSaveAdBtn())
									Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}
	
	/**
	 * Test of user PM create new ad of ""Look for used Stroller""
	 */
	@Test(priority = 43)
	public void verifyUserPMCreateAdofBuyUsedStroller() {
		test = extent.startTest("Verify user PM create new ad of 'Look for used Stroller'");
		if (classifiedAdsPage.clickCreateAdBtn())
			if (classifiedAdsPage.enterUnit())
				if (classifiedAdsPage.selectTypeOfBuying())
					if (classifiedAdsPage.enterTitleOfBuyUsedStroller())
						if (classifiedAdsPage.selectPriceOfFree())
							if (classifiedAdsPage.enterMessage())
								if (classifiedAdsPage.clickSaveAdBtn())
									Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());

	}
	
	
	/**
	 * Test of new created Ad is present on web table
	 * @throws Exception 
	 */
	@Test(priority = 45)
	public void verifyIsNewCreateAdPresentOnWebTable() throws Exception {
		test = extent.startTest("Verify new created Ad is present on web table");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(classifiedAdsPage.isNewCreatedAdPresentOnWebTable());
	}

	/**
	 * Test of display ads by category of "Buy & Sell Goods"
	 * 
	 * @throws Exception
	 */
	@Test(priority = 47)
	public void verifyDisplayAdsByCategoryOfBuyNSellGoods() throws Exception {
		test = extent.startTest("Verify display ads by category of 'Buy & Sell Goods'");
		SeleniumWrapper.refreshWebPage(driver);
		if (classifiedAdsPage.clickDisplayAllCateoriesBox())
			Assert.assertTrue(classifiedAdsPage.displayAdsByCategoryofBuyNSellGoods());
	}

	/**
	 * Test of display ads by category of "Cars, Bikes & Other Vehicles"
	 */
	@Test(priority = 49)
	public void verifyDisplayAdsByCategoryOfVehicles() {
		test = extent.startTest("Verify display ads by category of 'Cars, Bikes & Other Vehicles'");
		if (classifiedAdsPage.clickDisplayAllCateoriesBox())
			Assert.assertTrue(classifiedAdsPage.displayAdsByCategoryofVehicles());
	}

	/**
	 * Test of display ads by category of "Housing for Rent & Sale"
	 */
	@Test(priority = 51)
	public void verifyDisplayAdsByCategoryOfHousingForRentNSale() {
		test = extent.startTest("Verify display ads by category of 'Housing for Rent & Sale'");
		if (classifiedAdsPage.clickDisplayAllCateoriesBox())
			Assert.assertTrue(classifiedAdsPage.displayAdsByCategoryofHousing());
	}

	/**
	 * Test of display ads by category of "Services for Hire"
	 */
	@Test(priority = 53)
	public void verifyDisplayAdsByCategoryOfServicesForHire() {
		test = extent.startTest("Verify display ads by category of 'Services for Hire'");
		if (classifiedAdsPage.clickDisplayAllCateoriesBox())
			Assert.assertTrue(classifiedAdsPage.displayAdsByCategoryofServices());
	}

	/**
	 * Test of user PM search ad from List View tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 55)
	public void verifySearchAdFromListViewTab() throws Exception {
		test = extent.startTest("Verify user PM search ad from List View tab");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(classifiedAdsPage.searchAdFromListViewTab());
	}

	/**
	 * Test of user PM edit ad by clicking pencil icon from List View
	 * 
	 * @throws Exception
	 */
	@Test(priority = 57)
	public void verifyEditAdByClickingPencilIconFromListView() throws Exception {
		test = extent.startTest("Verify edit ad by clicking pencil icon from List View");
		SeleniumWrapper.refreshWebPage(driver);
		if (classifiedAdsPage.clickPencilIconFromListViewTab())
			if (classifiedAdsPage.modifyMessage())
				Assert.assertTrue(classifiedAdsPage.clickSaveAdBtn());
	}

	/**
	 * Test of user PM delete ad by clicking trash icon from List View
	 * @throws Exception 
	 */
	@Test(priority = 59)
	public void verifyDeleteAdByClickingTrashIconFromListView() throws Exception {
		test = extent.startTest("Verify delete ad by clicking trash icon from List View");
		SeleniumWrapper.refreshWebPage(driver);
		if (classifiedAdsPage.deleteAdByClickingTrashIconFromListViewTab())
			Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM click any column to open ad and edit ad from List View tab
	 */
	@Test(priority = 61)
	public void verifyClickColumnToOpenAdAndEditAdFromListViewTab() {
		test = extent.startTest("Verify user PM click any column to open ad and edit ad from List View tab");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (classifiedAdsPage.clickColumnToOpenAd())
			if (classifiedAdsPage.clickEditBtn())
				if (classifiedAdsPage.modifyAdExpiryDate())
					if (classifiedAdsPage.clickSaveAdBtn())
						Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM click any column to open ad and delete ad from List View tab
	 */
	@Test(priority = 63)
	public void verifyClickColumnToOpenAdAndDeleteAdFromListViewTab() {
		test = extent.startTest("Verify user PM click any column to open ad and delete ad from List View tab");
		if (classifiedAdsPage.clickColumnToOpenAd())
			if (classifiedAdsPage.clickDeleteBtn())
				if (classifiedAdsPage.clickConfirmDeleteBtn())
					Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM view ad from Thumbnails View tab
	 */
	@Test(priority = 65)
	public void verifyViewAdFromThumbnailsViewTab() {
		test = extent.startTest("Verify user PM view ad from Thumbnails tab");
		if (classifiedAdsPage.gotoThumbnailsTab())
			if (classifiedAdsPage.hoverMouseOverAd())
				if (classifiedAdsPage.clickViewBtnFromThumbnailsViewTab())
					if (classifiedAdsPage.verifyTitleAndPriceOfAd())
						if (classifiedAdsPage.goBackToClassifiedAdsTab())
							Assert.assertTrue(classifiedAdsPage.goBackListviewTab());
	}

	/**
	 * Test of click ad title to view ad detail from thumbnails View tab
	 */
	@Test(priority = 67)
	public void verifyIsAdTitleClickableFromThumbnailsViewTab() {
		test = extent.startTest("Verify click ad title to view ad detail from thumbnails View tab");
		if (classifiedAdsPage.gotoThumbnailsTab())
			if (classifiedAdsPage.isAdTitleClickableFromThumbnailsViewTab())
				if (classifiedAdsPage.verifyTitleAndPriceOfAd())
					if (classifiedAdsPage.goBackToClassifiedAdsTab())
						Assert.assertTrue(classifiedAdsPage.goBackListviewTab());
	}

	/**
	 * Test of user PM edit ad from Thumbnails View tab
	 */
	@Test(priority = 69)
	public void verifyEditAdFromThumbnailsViewTab() {
		test = extent.startTest("Verify user PM edit ad from Thumbnails tab");
		if (classifiedAdsPage.gotoThumbnailsTab())
			if (classifiedAdsPage.hoverMouseOverAd())
				if (classifiedAdsPage.clickEditBtnFromThumbnailsViewTab())
					if (classifiedAdsPage.modifyMessage())
						if (classifiedAdsPage.clickSaveAdBtn())
							if (classifiedAdsPage.isSuccessMsgPresent())
							Assert.assertTrue(classifiedAdsPage.goBackListviewTab());
	}

	/**
	 * Test of user PM delete ad from Thumbnails View tab
	 */
	@Test(priority = 71)
	public void verifydeleteAdFromThumbnailsViewTab() {
		test = extent.startTest("Verify user PM delete ad from Thumbnails tab");
		if (classifiedAdsPage.gotoThumbnailsTab())
			if (classifiedAdsPage.deleteAdFromThumbnailsViewTab())
				Assert.assertTrue(classifiedAdsPage.isSuccessMsgPresent());
	}
	
}
