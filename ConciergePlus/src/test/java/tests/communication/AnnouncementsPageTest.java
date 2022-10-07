package tests.communication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;

import pages.BasePage;
import pages.communication.AnnouncementsPage;

public class AnnouncementsPageTest extends GuiTestCase {

	protected final static Logger logger = LogManager.getLogger(AnnouncementsPageTest.class.getName());
	protected BasePage basePage = null;
	protected AnnouncementsPage announcementsPage = null;

	/**
	 * Test of navigating to Announcement Page
	 */
	@Test(priority = 1)
	public void gotoAnnouncementsPage() {
		test = extent.startTest("Navigate to Announcements Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		announcementsPage = basePage.gotoAnnouncementsPage();
		Assert.assertNotNull(announcementsPage);
	}

	/**
	 * Test create and publish an announcement( add event in the Community Schedule)
	 */
	@Test(priority = 3)
	public void verifyCreateAndPublishAnouncement() {
		test = extent
				.startTest("Verify create & publish an announcement with adding the event in Commnunity Schedule ");
		if (announcementsPage.clickCreateAnnouncementBtn())
			if (announcementsPage.clickAddGroupBtn())
				if (announcementsPage.inputSearchGroupBox())
					if (announcementsPage.chooseGroup())
						if (announcementsPage.inputSubjectOfAnnouncement())
							if (announcementsPage.clickTextBox2())
								if (announcementsPage.enterMessage())
									if (announcementsPage.clickCheckboxCreateEventInTheCommunitySchedule())
										if (announcementsPage.pickEventDate())
											if (announcementsPage.clickPublishAnnouncementBtn())
												Assert.assertTrue(
														announcementsPage.clickSendBtnToPublishAnnouncement());
	}

	/**
	 * Test create a Scheduled announcement
	 */
	@Test(priority = 5)
	public void verifyCreateScheduledAnouncement() {
		test = extent.startTest("Verify create a Scheduled announcement");
		if (announcementsPage.clickCreateAnnouncementBtn())
			if (announcementsPage.clickAddGroupBtn())
				if (announcementsPage.inputSearchGroupBox())
					if (announcementsPage.chooseGroup())
						if (announcementsPage.inputSubjectOfAnnouncement())
							if (announcementsPage.clickTextBox2())
								if (announcementsPage.enterMessage())
									if (announcementsPage.clickPublishAnnouncementBtn())
										if (announcementsPage.clickPublishLaterBtn())
												if (announcementsPage.pickPublishDate())
													Assert.assertTrue(announcementsPage.clickSendBtnToPublishAnnouncement());
	}

	/**
	 * Test create a Draft announcement with uploading file/image
	 */
	@Test(priority = 7)
	public void verifyCreateDraftAnouncementWithFileUploaded() {
		test = extent.startTest("Verify create a draft announcement with uploading file and image file");
		if (announcementsPage.clickCreateAnnouncementBtn())
			if (announcementsPage.clickAddGroupBtn())
				if (announcementsPage.inputSearchGroupBox())
					if (announcementsPage.chooseGroup())
						if (announcementsPage.inputSubjectOfAnnouncement())
							if (announcementsPage.clickTextBox2())
								if (announcementsPage.enterMessage())
									if (announcementsPage.clickSelectImageBtn())
										if (announcementsPage.selectFolder())
											if (announcementsPage.selectImage())
												if (announcementsPage.previewAnnouncement())
													if (announcementsPage.clickCloseBtn())
														Assert.assertTrue(announcementsPage.clickSaveDraftBtn());
	}

	/**
	 * Test create emergency announcement
	 */
	@Test(priority = 9)
	public void verifyCreateEmergentAnnouncement() {
		test = extent.startTest("Verify create emergent announcement");
		if (announcementsPage.clickCreateAnnouncementBtn())
			if (announcementsPage.goToEmergencyTab())
				if (announcementsPage.deleteGroupOfResidents())
					if (announcementsPage.clickBtnOfAddGroup())
						if (announcementsPage.searchTenantNew())
							if (announcementsPage.chooseTenantNewGroup())
								if (announcementsPage.inputMsgContent())
									if (announcementsPage.clickPublishAndSendNotificationBtn())
										Assert.assertTrue(announcementsPage.clickSendBtnOnUrgencyAnnouncement());
	}

	/**
	 * Test search an announcement from VIEW tab
	 */
	@Test(priority = 11)
	public void verifySearchAnnouncementFromViewTab() {
		test = extent.startTest("Verify search an announcement from VIEW tab");
		Assert.assertTrue(announcementsPage.searchAnnouncementFromViewTab());
	}

	/**
	 * Test view a "PUBLISHED" status announcement
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void verifyViewPublishedAnnouncement() throws Exception {
		test = extent.startTest("Verify view a 'Published' status announcement");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(announcementsPage.viewPublishedAnnouncement());
	}

	/**
	 * Test view a "DRAFT" status announcement
	 */
	@Test(priority = 15)
	public void verifyViewDraftAnnouncement() {
		test = extent.startTest("Verify view a 'Draft' status announcement");
		Assert.assertTrue(announcementsPage.viewDraftAnnouncement());
	}

	/**
	 * Test view a "SCHEDULED" status announcement
	 */
	@Test(priority = 17)
	public void verifyViewScheduledAnnouncement() {
		test = extent.startTest("Verify view a 'Scheduled' status announcement");
		Assert.assertTrue(announcementsPage.viewScheduledAnnouncement());
	}

	/**
	 * Test view a "URGENT" status announcement
	 */
	@Test(priority = 19)
	public void verifyViewUrgentAnnouncement() {
		test = extent.startTest("Verify view a 'Urgent' status announcement");
		Assert.assertTrue(announcementsPage.viewUrgentAnnouncement());
	}

	/**
	 * Test duplicate a "PUBLISHED" announcement
	 */
	@Test(priority = 21)
	public void verifyDuplicatePublishedAnnouncement() {
		test = extent.startTest("Verify duplicate a 'Published' announcement");
		if (announcementsPage.openPublishedAnnouncement())
			if (announcementsPage.clickDuplicateBtn())
				if (announcementsPage.modifySubject())
					if (announcementsPage.clickPublishAnnouncementBtn())
						Assert.assertTrue(announcementsPage.clickSendBtnToPublishAnnouncement());

	}

	/**
	 * Test delete a "Published" announcement
	 */
	@Test(priority = 23)
	public void verifyDeletePublishedAnnouncement() {
		test = extent.startTest("Verify delete a 'Published' announcement");
		if (announcementsPage.openPublishedAnnouncement())
			if (announcementsPage.clickDeleteBtn())
				Assert.assertTrue(announcementsPage.clickConfirmBtn());
	}

	/**
	 * Test who received the "Published" announcement
	 */
	@Test(priority = 25)
	public void verifyWhoReceivedPublishedAnnouncement() {
		test = extent.startTest("Verify who received 'PUBLISHED' announcement");
		if (announcementsPage.openPublishedAnnouncement())
			Assert.assertTrue(announcementsPage.verifyWhoReceivedAnnouncement());
	}

	/**
	 * Test preview DRAFT announcement
	 */
	@Test(priority = 27)
	public void verifyPreviewDraftAnnouncement() {
		test = extent.startTest("Verify preview 'DRAFT'announcement");
		if (announcementsPage.openDraftAnnouncement())
			if (announcementsPage.clickPreviewBtn())
				if (announcementsPage.getDraftAnnouncementTitle())
					if (announcementsPage.isLogoImageDisplayed())
						Assert.assertTrue(announcementsPage.closePreview());
	}

	/**
	 * Test publish DRAFT announcement LATER
	 */
	@Test(priority = 31)
	public void verifyPublishDraftAnnouncementLater() {
		test = extent.startTest("Verify publish 'DRAFT'announcement LATER");
		if (announcementsPage.openDraftAnnouncement())
			if (announcementsPage.clickPublishAnnouncementBtn())
				if (announcementsPage.clickPublishLaterBtn())
					Assert.assertTrue(announcementsPage.clickSendBtnToPublishAnnouncement());
	}

	/**
	 * Test publish DRAFT announcement NOW
	 */
	@Test(priority = 37)
	public void verifyPublishDraftAnnouncementNow() {
		test = extent.startTest("Verify publish 'DRAFT'announcement NOW");
		if (announcementsPage.openDraftAnnouncement())
			if (announcementsPage.clickPublishAnnouncementBtn())
				if (announcementsPage.clickSendBtnToPublishAnnouncement())
					Assert.assertTrue(announcementsPage.searchAndVerifyPublishedAnnouncement());
	}

	/**
	 * Test preview "Scheduled" announcement
	 */
	@Test(priority = 35)
	public void verifyPreviewScheduledAnnouncement() {
		test = extent.startTest("Verify preview a'SCHEDULED'announcement");
		if (announcementsPage.openScheduledAnnouncement())
			if (announcementsPage.clickPreviewBtn())
				if (announcementsPage.getAnnouncementTitle())
					if (announcementsPage.isLogoImageDisplayed())
						Assert.assertTrue(announcementsPage.closePreview());
	}

	/**
	 * Test edit Scheduled announcement by changing status from Scheduled to Draft
	 */
	@Test(priority = 33)
	public void verifyEditScheduledAnnouncementByChangeStatusToDraft() {
		test = extent.startTest("Verify edit 'SCHEDULED'announcement by change status to 'DRAFT'");
		if (announcementsPage.openScheduledAnnouncement())
			if (announcementsPage.clickEditPublishDateBtn())
				Assert.assertTrue(announcementsPage.clickCancelAndMoveToDraftBtn());
	}

	/**
	 * Check edit Scheduled announcement by publish the announcement LATER
	 */
	@Test(priority = 39)
	public void verifyEditSchduledAnnouncementByPublishLater() {
		test = extent.startTest("Verify edit 'SCHEDULED'announcement by publish announcement LATER");
		if (announcementsPage.openScheduledAnnouncement())
			if (announcementsPage.clickEditPublishDateBtn())
				if (announcementsPage.pickPublishDate())
					Assert.assertTrue(announcementsPage.clickSaveChangesBtn());
	}

	/**
	 * Check edit Scheduled announcement by publish the announcement NOW
	 */
	@Test(priority = 41)
	public void verifyEditSchduledAnnouncementByPublishNow() {
		test = extent.startTest("Verify edit 'SCHEDULED'announcement by publish announcement NOW");
		if (announcementsPage.openScheduledAnnouncement())
			if (announcementsPage.clickEditPublishDateBtn())
				if (announcementsPage.clickPublishNowBtn())
					Assert.assertTrue(announcementsPage.clickSendBtnToPublishAnnouncement());
	}

	/**
	 * Test view all announcements
	 */
	@Test(priority = 43)
	public void verifyClickViewAllAnnouncementsBtn() {
		test = extent.startTest("Verify click 'View All announcements' button");
		if (announcementsPage.clickViewAllAnnouncements())
			Assert.assertTrue(announcementsPage.isAtListTab());
	}

	/**
	 * Test search an announcement from LIST tab
	 */
	@Test(priority = 45)
	public void verifySearchAnnouncementFromListTab() {
		test = extent.startTest("Verify search an announcement from LIST tab");
		if (announcementsPage.goToListTab())
			Assert.assertTrue(announcementsPage.searchAnnouncementFromListTab());
	}

	/**
	 * Verify sort per column Item STATUS
	 * 
	 * @throws Exception
	 */
	@Test(priority = 47)
	public void testSortColumnStatus() throws Exception {
		test = extent.startTest("Verify sort per column Status");
		SeleniumWrapper.refreshWebPage(driver);
		if (announcementsPage.goToListTab())
			if (announcementsPage.sortByColumnStatus())
				Assert.assertTrue(announcementsPage.goBackViewTab());
	}

	/**
	 * Verify sort per column Item PUBLISHED
	 * 
	 * @throws Exception
	 */
	@Test(priority = 49)
	public void testSortColumnPublished() {
		test = extent.startTest("Verify sort per column Published");
		if (announcementsPage.goToListTab())
			if (announcementsPage.sortByColumnPublished())
				Assert.assertTrue(announcementsPage.goBackViewTab());
	}

	/**
	 * Verify sort per column Item SUBJECT
	 * 
	 * @throws Exception
	 */
	@Test(priority = 51)
	public void testSortColumnSubject() {
		test = extent.startTest("Verify sort per column Subject");
		if (announcementsPage.goToListTab())
			if (announcementsPage.sortByColumnSubject())
				Assert.assertTrue(announcementsPage.goBackViewTab());
	}

	/**
	 * Test create draft announcement(add event in the Community Schedule)
	 */
	@Test(priority = 53)
	public void verifyCreateDraftAnouncementFromListTab() {
		test = extent.startTest("Verify create Draft announcement from LIST tab ");
		if (announcementsPage.goToListTab())
			if (announcementsPage.clickCreateAnnouncementBtnFromListTab())
				if (announcementsPage.clickAddGroupBtn())
					if (announcementsPage.inputSearchGroupBox())
						if (announcementsPage.chooseGroup())
							if (announcementsPage.inputSubjectOfAnnouncement())
								if (announcementsPage.enterMessage())
									if (announcementsPage.clickCheckboxCreateEventInTheCommunitySchedule())
										if (announcementsPage.pickEventDate())
											Assert.assertTrue(announcementsPage.clickSaveDraftBtn());
	}
	

	/**
	 * Test create emergency announcement from LIST tab
	 */
	@Test(priority = 55)
	public void verifyCreateEmergentAnnouncementFromListTab() {
		test = extent.startTest("Verify create emergent announcement from LIST tab");
		if (announcementsPage.goToListTab())
			if (announcementsPage.clickCreateAnnouncementBtnFromListTab())
				if (announcementsPage.goToEmergencyTab())
					if (announcementsPage.deleteGroupOfResidents())
						if (announcementsPage.clickBtnOfAddGroup())
							if (announcementsPage.searchTenantNew())
								if (announcementsPage.chooseTenantNewGroup())
									if (announcementsPage.inputMsgContent())
										if (announcementsPage.clickPublishAndSendNotificationBtn())
											Assert.assertTrue(announcementsPage.clickSendBtnOnUrgencyAnnouncement());
	}
	

	/**
	 * Test who received the urgent announcement's notification from List Tab
	 */
	@Test(priority = 59)
	public void verifyWhoReceivedNotificationFromListTab() {
		test = extent.startTest("Verify Who received Urgent announcement's notification");
		if (announcementsPage.goToListTab())
			if (announcementsPage.openUrgentAnnouncement())
				Assert.assertTrue(announcementsPage.verifyWhoReceivedNotification());
	}

	/**
	 * Verify duplicate an announcement from LIST tab
	 */
	@Test(priority = 57)
	public void testDuplicateAnnouncementFromListTab() {
		test = extent.startTest("Verify duplicate an announcement from LIST tab");
		if (announcementsPage.goToListTab())
			if (announcementsPage.clickDuplicateIcon())
				if (announcementsPage.modifySubject())
					if (announcementsPage.clickPublishAnnouncementBtn())
						Assert.assertTrue(announcementsPage.clickSendBtnToPublishAnnouncement());
	}

	/**
	 * Verify edit an announcement from LIST tab
	 */
	@Test(priority = 61)
	public void testEditAnnouncementFromListTab() {
		test = extent.startTest("Verify edit an announcement from LIST tab");
		if (announcementsPage.goToListTab())
			if (announcementsPage.clickEditIconOnDraftAnnouncement())
				if (announcementsPage.clickTextBox1())
					if (announcementsPage.editMessage())
						if (announcementsPage.clickPublishAnnouncementBtn())
							if (announcementsPage.clickSendBtnToPublishAnnouncement())
								Assert.assertTrue(announcementsPage.goBackViewTab());
	}

	/**
	 * Verify delete"Published" announcement from LIST tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 63)
	public void testDeletePublishedAnnouncementFromListTab() throws Exception {
		test = extent.startTest("Verify delete 'Published' announcement from LIST tab");
		if (announcementsPage.goToListTab())
			if (announcementsPage.clickDeleteIconOnPublishedAnnouncement())
				if (announcementsPage.clickConfirmBtn())
					Assert.assertTrue(announcementsPage.goBackViewTab());
	}

	/**
	 * Verify delete "Urgent" announcement from LIST tab
	 */
	@Test(priority = 65)
	public void testDeleteUrgentAnnouncementFromListTab() {
		test = extent.startTest("Verify delete 'Urgent' announcement from LIST tab");
		if (announcementsPage.goToListTab())
			if (announcementsPage.clickDeleteIconOnUrgentAnnouncement())
				if (announcementsPage.clickConfirmBtn())
					Assert.assertTrue(announcementsPage.goBackViewTab());
	}

	/**
	 * Verify delete "Published" announcement through View Tab
	 */
	@Test(priority = 67)
	public void testDeletePublishedAnnouncementthroughViewTab() {
		test = extent.startTest("Verify delete 'Published' announcement through View Tab");
		if (announcementsPage.goToListTab())
			if (announcementsPage.clickPublishedAnnouncementFromListTab())
				if (announcementsPage.clickDeleteBtn())
					Assert.assertTrue(announcementsPage.clickConfirmBtn());
	}

}
