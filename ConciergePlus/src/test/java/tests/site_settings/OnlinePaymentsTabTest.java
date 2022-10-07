package tests.site_settings;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;

import pages.BasePage;
import pages.site__settings.OnlinePaymentsTab;
import pages.site__settings.SiteAdministrationPage;

public class OnlinePaymentsTabTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(OnlinePaymentsTabTest.class.getName());
	protected BasePage basePage = null;
	protected SiteAdministrationPage siteAdministrationPage = null;
	protected OnlinePaymentsTab onlinePaymentsTab = null;

	/**
	 * Test of navigating to Online Payments tab
	 */
	@Test(priority = 1)
	public void gotoOnlinePaymentsTab() {
		test = extent.startTest("Navigate to Online Payments tab");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		siteAdministrationPage = basePage.gotoSiteAdministrationPage();
		Assert.assertNotNull(siteAdministrationPage);
		onlinePaymentsTab = siteAdministrationPage.gotoOnlinePaymentsTab();
		Assert.assertNotNull(onlinePaymentsTab);
	}

	/**
	 * Test of verify transaction status for Party Room booking is "PAID"
	 */
	@Test(priority = 3)
	public void verifyTransactiontStatusOfPartyRoomBooking_Paid() {
		test = extent.startTest("Verify transaction status of Party Room booking is PAID");
		Assert.assertTrue(onlinePaymentsTab.verifyTransactionRecordOfPaid());
	}

	/**
	 * Test of verify transaction status for Guest Suite booking is "PENDING"
	 */
	@Test(priority = 5)
	public void verifyTransactiontStatusOfGuestSuiteBooking_Pending() {
		test = extent.startTest("Verify transaction status of Guest Suite booking is PENDING");
		if (onlinePaymentsTab.returnOnlinePaymentstab())
			Assert.assertTrue(onlinePaymentsTab.verifyTransactionRecordOfPending());
	}

	/**
	 * Test of verify transaction status for Guest Suite booking is "REFUNDED"
	 */
	@Test(priority = 7)
	public void verifyTransactiontStatusOfGuestSuiteBooking_Refunded() {
		test = extent.startTest("Verify transaction status of Guest Suite booking is REFUNDED");
		if (onlinePaymentsTab.returnOnlinePaymentstab())
			Assert.assertTrue(onlinePaymentsTab.verifyTransactionRecordOfRefunded());
	}

	/**
	 * Test of verify issue refund on "Paid" transaction
	 */
	@Test(priority = 9)
	public void verifyIssueRefund() {
		test = extent.startTest("Verify issue refund on 'Paid' transaction");
		if (onlinePaymentsTab.returnOnlinePaymentstab())
			Assert.assertTrue(onlinePaymentsTab.issueRefundOnPaidTransaction());
	}

	/**
	 * Test of view transactions by type of Amenity Booking
	 */
	@Test(priority = 11)
	public void verifyViewTransactionsByTypeAmenityBooking() {
		test = extent.startTest("Verify view transactions by type of 'Amenity Booking'");
		if (onlinePaymentsTab.returnOnlinePaymentstab())
			Assert.assertTrue(onlinePaymentsTab.viewTransactionByTypeAmenityBooking());
	}

	/**
	 * Test of view transactions by type of Estoppel Certificates
	 */
	@Test(priority = 13)
	public void verifyViewTransactionsByTypeEstoppelCertificates() {
		test = extent.startTest("Verify view transactions by type of 'Estoppel Certificates'");
		Assert.assertTrue(onlinePaymentsTab.viewTransactionByTypeEstoppelCertificate());
	}

	/**
	 * Test of sort per column Date Created
	 */
	@Test(priority = 15)
	public void verifySortPerColumnDateCreated() {
		test = extent.startTest("Verify sort per column Date Created");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(onlinePaymentsTab.sortByColumnDateCreated());
	}

	/**
	 * Test of sort per column Order ID
	 */
	@Test(priority = 17)
	public void verifySortPerColumnID() {
		test = extent.startTest("Verify sort per column Order ID");
		Assert.assertTrue(onlinePaymentsTab.sortByColumnOrderID());
	}

	/**
	 * Test of sort per column Status
	 */
	@Test(priority = 19)
	public void verifySortPerColumnStatus() {
		test = extent.startTest("Verify sort per column Status");
		Assert.assertTrue(onlinePaymentsTab.sortByColumnStatus());
	}

	/**
	 * Test of sort per column Type
	 */
	@Test(priority = 21)
	public void verifySortPerColumnType() {
		test = extent.startTest("Verify sort per column Type");
		Assert.assertTrue(onlinePaymentsTab.sortByColumnType());
	}

	/**
	 * Test of sort per column Unit
	 */
	@Test(priority = 23)
	public void verifySortPerColumnUnit() {
		test = extent.startTest("Verify sort per column Unit");
		Assert.assertTrue(onlinePaymentsTab.sortByColumnUnit());
	}

	/**
	 * Test of sort per column User
	 */
	@Test(priority = 25)
	public void verifySortPerColumnUser() {
		test = extent.startTest("Verify sort per column User");
		Assert.assertTrue(onlinePaymentsTab.sortByColumnUser());
	}

	/**
	 * Test of sort per column Total
	 */
	@Test(priority = 27)
	public void verifySortPerColumnTotal() {
		test = extent.startTest("Verify sort per column Total");
		Assert.assertTrue(onlinePaymentsTab.sortByColumTotal());
	}

	/**
	 * Test of search record
	 */
	@Test(priority = 29)
	public void verifySearchRecord() {
		test = extent.startTest("Verify search record");
		Assert.assertTrue(onlinePaymentsTab.searchRecord());
	}

}
