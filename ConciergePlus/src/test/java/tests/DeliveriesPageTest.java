package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;

import pages.BasePage;
import pages.DeliveriesPage;
import pages.LoginPage;

public class DeliveriesPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(DeliveriesPageTest.class.getName());
	protected LoginPage loginPage = null;
	protected BasePage basePage = null;
	protected DeliveriesPage deliveriesPage = null;

	/**
	 * Test of navigating to DeliveriesPage
	 */
	@Test(priority = 1)
	public void gotoDeliveriesPage() {
		test = extent.startTest("Navigate to DeliveriesPage");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		deliveriesPage = basePage.gotoDeliveriesPage();
		Assert.assertNotNull(deliveriesPage);
	}

	/**
	 * Test Create a new delivery
	 */
	@Test(priority = 3)
	public void verifyCreateADelivery() {
		test = extent.startTest("Verify create a new delivery");
		if (deliveriesPage.clickCreateButtonToCreateDelivery())
			if (deliveriesPage.clickCreateDeliveriesButton())
				if (deliveriesPage.inputWayBill())
					if (deliveriesPage.inputNameOrUnitNumberForDelivery())
						if (deliveriesPage.selectChoiceForNameOrUnitNumberForDelivery())
							if (deliveriesPage.clickLocationForDelivery())
								if (deliveriesPage.chooseLocationForDelivery())
									if (deliveriesPage.clickTypeForDelivery())
										if (deliveriesPage.chooseTypeForDelivery())
											deliveriesPage.inputSenderForDelivery();
		if (deliveriesPage.clickAddDeliveryButton())
			deliveriesPage.clickDeleteIcon();
		deliveriesPage.clickCreateDeliveryButton();
		Assert.assertTrue(deliveriesPage.isSuccessMsgPresent());
	}

	/**
	 * Test create a pickup
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void testCreateAPickup() throws Exception {
		test = extent.startTest("Verify create a new pickup");
		SeleniumWrapper.refreshWebPage(driver);
		if (deliveriesPage.clickCreateButtonToCreatePickup())
			if (deliveriesPage.clickCreatePickupButton())
				if (deliveriesPage.inputItemDescription())
					if (deliveriesPage.inputNameOrUnitNumberForPickup())
						if (deliveriesPage.selectChoiceForNameOrUnitNumberForPickup())
							if (deliveriesPage.clickLocation())
								if (deliveriesPage.chooseLocation())
									deliveriesPage.inputHeldFor();
		deliveriesPage.clickCreateAndPrintButton();
		Assert.assertTrue(deliveriesPage.isSuccessMsgPresent());
	}

	/**
	 * Test Create multiple deliveries
	 */
	@Test(priority = 7)
	public void verifyCreateMultipleDeliveries() {
		test = extent.startTest("Verify create multiple new deliveries");
		if (deliveriesPage.clickCreateButtonToCreateDelivery())
			if (deliveriesPage.clickCreateDeliveriesButton())
				if (deliveriesPage.inputWayBill())
					if (deliveriesPage.inputNameOrUnitNumberForDelivery())
						if (deliveriesPage.selectChoiceForNameOrUnitNumberForDelivery())
							if (deliveriesPage.clickLocationForDelivery())
								if (deliveriesPage.chooseLocationForDelivery())
									if (deliveriesPage.clickTypeForDelivery())
										if (deliveriesPage.chooseTypeForDelivery())
											deliveriesPage.inputSenderForDelivery();
		if (deliveriesPage.clickAddDeliveryButton())
			if (deliveriesPage.inputAnotherWayBill())
				if (deliveriesPage.inputAnotherNameOrUnitNumberForDelivery())
					if (deliveriesPage.selectChoiceForNameOrUnitNumberForDelivery())
						if (deliveriesPage.inputAnotherSenderForDelivery())
							if (deliveriesPage.clickCreateDeliveryButton())
								Assert.assertTrue(deliveriesPage.isSuccessMsgPresent());
	}

	/**
	 * Verify sort per column ID (Current Tab)
	 */
	@Test(priority = 9)
	public void testSortColumnID() {
		test = extent.startTest("Verify sort per column ID");
		Assert.assertTrue(deliveriesPage.sortByColumnID());
	}

	/**
	 * Verify sort per column Received (Current Tab)
	 */
	@Test(priority = 11)
	public void testSortColumnReceived() {
		test = extent.startTest("Verify sort per column Received");
		Assert.assertTrue(deliveriesPage.sortByColumnReceived());
	}

	/**
	 * Verify sort per column Unit (Current Tab)
	 */
	@Test(priority = 13)
	public void testSortColumnUnit() {
		test = extent.startTest("Verify sort per column Unit");
		Assert.assertTrue(deliveriesPage.sortByColumnUnit());
	}

	/**
	 * Verify sort per column Location (Current Tab)
	 */
	@Test(priority = 15)
	public void testSortColumnLocation() {
		test = extent.startTest("Verify sort per column Location");
		Assert.assertTrue(deliveriesPage.sortByColumnLocation());
	}

	/**
	 * Verify sort per column Type (Current Tab)
	 */
	@Test(priority = 17)
	public void testSortColumnType() {
		test = extent.startTest("Verify sort per column Type");
		Assert.assertTrue(deliveriesPage.sortByColumnType());
	}

	/**
	 * Verify sort per column Recipient (Current Tab)
	 */
	@Test(priority = 19)
	public void testSortColumnRecipient() {
		test = extent.startTest("Verify sort per column Recipient");
		Assert.assertTrue(deliveriesPage.sortByColumnRecipient());
	}

	/**
	 * Verify sort per column Item (Current Tab)
	 */
	@Test(priority = 21)
	public void testSortColumnItem() {
		test = extent.startTest("Verify sort per column Item");
		Assert.assertTrue(deliveriesPage.sortByColumnItem());
	}

	/**
	 * Test Search function on received delivery/pickup (Current tab)
	 */
	@Test(priority = 23)
	public void verifySearchReceivedRecords() {
		test = extent.startTest("Verify Search function on received delivery/pickup ");
		Assert.assertTrue(deliveriesPage.searchDeliveryByKeyword());
	}

	/**
	 * Test View function on received delivery/pickup (Current Tab)
	 */
	@Test(priority = 25)
	public void testViewReceivedRecord() {
		test = extent.startTest("Verify View function on received delivery/pickup");
		SeleniumWrapper.refreshWebPage(driver);
		if (deliveriesPage.clickViewIcon())
			Assert.assertTrue(deliveriesPage.clickCancelButton());
	}

	/**
	 * test Edit function on received delivery/pickup (Current Tab)
	 */
	@Test(priority = 27)
	public void testEditReceivedRecord() {
		test = extent.startTest("Verify Edit function on received delivery/pickup");
		if (deliveriesPage.clickEditIcon())
			if (deliveriesPage.inputNotes())
				if (deliveriesPage.clickSaveEditCopyButton())
					Assert.assertTrue(deliveriesPage.isSuccessMsgPresent());
	}

	/**
	 * Test Pick Up function on received delivery/pickup (Current tab)
	 * 
	 * @throws Exception
	 */
	@Test(priority = 29)
	public void testPickUpReceivedRecord() throws Exception {
		test = extent.startTest("Verify Pick Up function on received delivery/pickup");
		if (deliveriesPage.clickPickUpIcon())
			if (deliveriesPage.clickSaveSignatureAndCloseButton())
				Assert.assertTrue(deliveriesPage.isSuccessMsgPresent());
	}

	/**
	 * Test the picked up record is moved from Current tab to Closed tab
	 */
	@Test(priority = 31)
	public void testIsPickedUpRecordMovedToClosedTab() {
		test = extent.startTest("Verify the picked up record is moved from CURRENT tab to CLOSED tab");
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (deliveriesPage.gotoClosedTab())
			Assert.assertTrue(deliveriesPage.searchPickUpItem());
	}

	/**
	 * Test delete function on received delivery/pickup (Current tab)
	 * 
	 * @throws Exception
	 */
	@Test(priority = 33)
	public void testDeleteReceivedRecord() throws Exception {
		test = extent.startTest("Verify delete function on received delivery/pickup");
		SeleniumWrapper.refreshWebPage(driver);
		if (deliveriesPage.clickDeleteIcon_Current())
			if (deliveriesPage.clickConfirmDeleteBtn())
				Assert.assertTrue(deliveriesPage.isSuccessMsgPresent());
	}

	/**
	 * Test Sorting on closed deliveries/pickups (Closed tab)
	 */

	/**
	 * Verify sort per column ID (Closed tab)
	 */
	@Test(priority = 35)
	public void testSortColumnID_Closed() {
		test = extent.startTest("Verify sort per column ID on Closed Tab");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.sortByColumnID_Closed())
				Assert.assertTrue(deliveriesPage.goBackCurrentTab());
	}

	/**
	 * Verify sort per column Received (Closed tab)
	 */
	@Test(priority = 37)
	public void testSortColumnReceived_Closed() {
		test = extent.startTest("Verify sort per column Received on Closed Tab");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.sortByColumnReceived_Closed())
				Assert.assertTrue(deliveriesPage.goBackCurrentTab());
	}

	/**
	 * Verify sort per column Unit (Closed tab)
	 */
	@Test(priority = 39)
	public void testSortColumnUnit_Closed() {
		test = extent.startTest("Verify sort per column Unit on Closed Tab");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.sortByColumnUnit_Closed())
				Assert.assertTrue(deliveriesPage.goBackCurrentTab());
	}

	/**
	 * Verify sort per column Location (Closed tab)
	 */
	@Test(priority = 41)
	public void testSortColumnLocation_Closed() {
		test = extent.startTest("Verify sort per column Location on Closed Tab");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.sortByColumnLocation_Closed())
				Assert.assertTrue(deliveriesPage.goBackCurrentTab());
	}

	/**
	 * Verify sort per column Type (Closed tab)
	 */
	@Test(priority = 43)
	public void testSortColumnType_Closed() {
		test = extent.startTest("Verify sort per column Type on Closed Tab");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.sortByColumnType_Closed())
				Assert.assertTrue(deliveriesPage.goBackCurrentTab());
	}

	/**
	 * Verify sort per column Recipient (Closed tab)
	 */
	@Test(priority = 45)
	public void testSortColumnRecipient_Closed() {
		test = extent.startTest("Verify sort per column Recipient on Closed Tab");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.sortByColumnRecipient_Closed())
				Assert.assertTrue(deliveriesPage.goBackCurrentTab());
	}

	/**
	 * Verify sort per column Item (Closed tab)
	 */
	@Test(priority = 47)
	public void testSortColumnItem_Closed() {
		test = extent.startTest("Verify sort per column Item on Closed Tab");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.sortByColumnItem_Closed())
				Assert.assertTrue(deliveriesPage.goBackCurrentTab());
	}

	/**
	 * Test View function on closed delivery/pickup (Closed Tab)
	 */
	@Test(priority = 49)
	public void testViewClosedRecord() {
		test = extent.startTest("Verify View function on closed delivery/pickup");
		if (deliveriesPage.gotoClosedTab())
			if (deliveriesPage.clickViewClosedRecordICon())
				Assert.assertTrue(deliveriesPage.clickCancelButtonOnClosedDelivery());
	}

	/**
	 * Test Search function on closed delivery/pickup (Closed tab)
	 */
	@Test(priority = 51)
	public void verifySearchClosedRecords() {
		test = extent.startTest("Verify Search function on closed delivery/pickup ");
		if (deliveriesPage.gotoClosedTab())
			Assert.assertTrue(deliveriesPage.searchClosedDelivery());
	}

	/**
	 * Test "Show Deleted Packages" function (Closed tab)
	 * 
	 * @throws Exception
	 */
	@Test(priority = 53)
	public void verifyShowDeletedPackages() throws Exception {
		test = extent.startTest("Verify 'Show Deleted Packages' function ");
		SeleniumWrapper.refreshWebPage(driver);
		if (deliveriesPage.gotoClosedTab())
			Assert.assertTrue(deliveriesPage.showDeletedPacksages());
	}
}
