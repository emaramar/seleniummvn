package tests.site_settings;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.site__settings.ParkingPage;
import pages.site__settings.SiteAdministrationPage;

public class ParkingPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(ParkingPageTest.class.getName());
	protected BasePage basePage = null;
	protected SiteAdministrationPage siteAdministrationPage = null;
	protected ParkingPage parkingPage = null;

	/**
	 * Test of navigating to Parking Page
	 */
	@Test(priority = 1)
	public void gotoParkingPage() {
		test = extent.startTest("Navigate to Parking Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		Assert.assertNotNull(siteAdministrationPage);
		parkingPage = siteAdministrationPage.gotoParkingPage();
		Assert.assertNotNull(parkingPage);
	}

	/**
	 * Test of verify is Parking Spot field mandatory
	 */
	@Test(priority = 3)
	public void isParkingSpotFieldMandatory() {
		test = extent.startTest("Verify is Parking Spot field mandatory");
		if (parkingPage.clickCreateBtn())
			if (parkingPage.clickParkingSpotInputBox())
				if (parkingPage.clickSaveBtnOnCreateParkingSpotPage())
					if (parkingPage.isErrorMsgPresent())
					Assert.assertTrue(parkingPage.goBackParkingTab());
	}

	/**
	 * Test of verify create new Parking Spot for a unit
	 */
	@Test(priority = 5)
	public void createNewParkingSpot() {
		test = extent.startTest("Verify create new Parking Spot for a unit");
			if (parkingPage.clickCreateBtn())
				if (parkingPage.clickParkingSpotInputBox())
					if (parkingPage.enterParkingSpotInfo())
						if (parkingPage.enterUnit())
							if (parkingPage.enterLicensePlate())
								if (parkingPage.selectCarMaker())
									if (parkingPage.enterModel())
										if (parkingPage.selectColor())
											if (parkingPage.clickSaveBtnOnCreateParkingSpotPage())
											Assert.assertTrue(parkingPage.isSuccessMsgPresent());
	}

	/**
	 * Test of search new created parking spot
	 */
	@Test(priority = 7)
	public void searchNewCreatedParkingSpot() {
		test = extent.startTest("Verify search new created parking spot");
		Assert.assertTrue(parkingPage.searchParkingSpot());
	}

	/**
	 * Test of click to open parking spot
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void clickToOpenParkingSpot() throws Exception {
		test = extent.startTest("Verify click to open parking spot");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(parkingPage.clickToOpenParkingSpot());
	}

	/**
	 * Test of edit parking spot by changing car color
	 */
	@Test(priority = 11)
	public void editParkingSpotByChangeCarColor() {
		test = extent.startTest("Verify edit parking spot by change car color");
		if (parkingPage.editParkingSpot())
				if (parkingPage.updateCarColor())
					if (parkingPage.clickSaveBtnOnCreateParkingSpotPage())
						Assert.assertTrue(parkingPage.isSuccessMsgPresent());
	}
	
	/**
	 * Test of edit parking spot by editing rental info
	 */
	@Test(priority = 13)
	public void editParkingSpotByRemoveRentalInfo(){
		test = extent.startTest("Verify edit parking spot by editing rental info");
		if (parkingPage.clickParkingSpot())
			if (parkingPage.editRentalInfoOnParkingSpot())
				if (parkingPage.clickSaveBtnOnCreateParkingSpotPage())
					Assert.assertTrue(parkingPage.isSuccessMsgPresent());
	}
	

	/**
	 * Test of delete parking spot
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void deleteParkingSpot() throws Exception {
		test = extent.startTest("Verify delete new created parking spot");
		SeleniumWrapper.refreshWebPage(driver);
		if (parkingPage.deleteParkingSpot())
			Assert.assertTrue(parkingPage.isSuccessMsgPresent());
	}

	/**
	 * Test of edit parking spot from unit profile
	 * 
	 * @throws Exception
	 */
	@Test(priority = 17)
	public void editParkingSpotFromUnitProfile() throws Exception {
		test = extent.startTest("Verify edit parking spot from unit profile");
		if (parkingPage.searchUnit())
			if (parkingPage.clickParkingSpotFromUnitProfile())
				if (parkingPage.deleteParkingSpotFromUnitProfile())
						if (parkingPage.clickSaveBtn())
						Assert.assertTrue(parkingPage.isSuccessMsgPresent());
	}
	
	/**
	 * Test of verify create another Parking Spot for a unit
	 */
	@Test(priority = 19)
	public void createAnotherParkingSpot() {
		test = extent.startTest("Verify create another Parking Spot for a unit");
		basePage.gotoSiteAdministrationPage();
		siteAdministrationPage.gotoParkingPage();
			if (parkingPage.clickCreateBtn())
				if (parkingPage.clickParkingSpotInputBox())
					if (parkingPage.enterParkingSpotInfo())
						if (parkingPage.enterUnit())
							if (parkingPage.enterLicensePlate())
								if (parkingPage.selectCarMaker())
									if (parkingPage.enterModel())
										if (parkingPage.selectColor())
											if (parkingPage.clickSaveBtnOnCreateParkingSpotPage())
											Assert.assertTrue(parkingPage.isSuccessMsgPresent());
	}
	
	/**
	 * Test of verify create new Parking Spot for a unit
	 */
	@Test(priority = 21)
	public void createParkingSpot() {
		test = extent.startTest("Verify create new Parking Spot for a unit");
			if (parkingPage.clickCreateBtn())
				if (parkingPage.clickParkingSpotInputBox())
					if (parkingPage.enterParkingSpotInfo())
						if (parkingPage.enterUnit())
							if (parkingPage.enterLicensePlate())
								if (parkingPage.selectCarMaker())
									if (parkingPage.enterModel())
										if (parkingPage.selectColor())
											if (parkingPage.clickSaveBtnOnCreateParkingSpotPage())
											Assert.assertTrue(parkingPage.isSuccessMsgPresent());
	}

}
