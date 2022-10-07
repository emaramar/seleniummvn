package tests.maintenance;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.maintenance.EntryInstructionsPage;

public class EntryInstructionsPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(EntryInstructionsPageTest.class.getName());
	protected BasePage basePage = null;
	protected EntryInstructionsPage entryInstructionsPage = null;

	/**
	 * Test of navigating to Entry Instructions Page
	 */
	@Test(priority = 1)
	public void gotoEntryInstructionsPage() {
		test = extent.startTest("Navigate to Entry Instructions Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		entryInstructionsPage = basePage.gotoEntryInstructionsPage();
		Assert.assertNotNull(entryInstructionsPage);
	}

	/**
	 * Test of resident go to Entry Instructions menu
	 */
	@Test(priority = 3)
	public void verifyResidentUserGotoEntryInstructionsMenu() {
		test = extent.startTest("Verify Resident go to Entry Instructions menu");
		if (entryInstructionsPage.logOutAsCurrentUser(driver))
			basePage.logInAsResidentUser(driver);
		Assert.assertTrue(entryInstructionsPage.navigateToEntryInstructionMenu());
	}

	/**
	 * Test of resident user cant create new entry instruction for other unit
	 */
	@Test(priority = 5)
	public void verifyResidentUserCantCreateNewEntryInstructionForOtherUnit() {
		test = extent.startTest("Verify resident user cant create new entry instruction for other unit ");
		if (entryInstructionsPage.clickCreateEntryInstructionBtn())
			if (entryInstructionsPage.checkUnitBox())
				Assert.assertTrue(entryInstructionsPage.goBackToEntryInstructionTab());
	}

	/**
	 * Test of 'Visitor or Company' field is mandatory field
	 */
	@Test(priority = 7)
	public void verifyMandatoryFieldOfVisitorOrCompany() {
		test = extent.startTest("Verify 'Visitor or Company' field is mandatory field");
		if (entryInstructionsPage.clickCreateEntryInstructionBtn())
			if (entryInstructionsPage.clickSaveBtn())
				if (entryInstructionsPage.isErrorMsgPresent())
					Assert.assertTrue(entryInstructionsPage.goBackToEntryInstructionTab());
	}

	/**
	 * Test of resident user create new entry instruction for Dog walker
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void verifyResidentUserCreateNewEntryInstruction_dogWalker() throws Exception {
		test = extent.startTest("Verify resident user create new entry instruction");
		if (entryInstructionsPage.clickCreateEntryInstructionBtn())
			if (entryInstructionsPage.pickEntryInstructiontype_DogWalker())
				if (entryInstructionsPage.inputVisitorName())
					if (entryInstructionsPage.inputAdditionalInformation())
						if (entryInstructionsPage.checkAllowToUseMyKey())
							if (entryInstructionsPage.setStartDate())
								if (entryInstructionsPage.setExpirationDate())
									if (entryInstructionsPage.uploadVisitorPhoto())
										if (entryInstructionsPage.clickSaveBtn())
											Assert.assertTrue(entryInstructionsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of verify 'Pending Approval' status is present on new entry instruction
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void verifyIsPendingApprovalStatusPresent() throws Exception {
		test = extent.startTest("Verify 'Pending Approval' status is present on new entry instruction");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(entryInstructionsPage.IsPendingApprovalStatusPresent());
	}

	/**
	 * Test of resident user view the new entry instruction record
	 */
	@Test(priority = 13)
	public void verifyResidentUserViewNewEntryInstructionRecord() {
		test = extent.startTest("Verify resident user view the new entry instruction record ");
		if (entryInstructionsPage.viewEntryInstructionRecord())
			Assert.assertTrue(entryInstructionsPage.clickCancelBtn());
	}

	/**
	 * Test of resident user delete uploaded visitor photo
	 */
	@Test(priority = 15)
	public void verifyResidentUserDeleteVisitorPhoto() {
		test = extent.startTest("Verify resident user delete uploaded visitor photo");
		if (entryInstructionsPage.viewEntryInstructionRecord())
			if (entryInstructionsPage.deleteVisitorPhoto())
				Assert.assertTrue(entryInstructionsPage.clickSaveBtn());
	}

	/**
	 * Test of resident user edit entry instruction
	 */
	@Test(priority = 17)
	public void verifyResidentUserEditEntryInstruction() {
		test = extent.startTest("Verify resident user edit entry instruction");
		if (entryInstructionsPage.clickEditIcon())
			if (entryInstructionsPage.editAdditionalInformation())
				Assert.assertTrue(entryInstructionsPage.clickSaveBtn());
	}

	/**
	 * Test of resident user delete entry instruction
	 */
	@Test(priority = 19)
	public void verifyResidentUserDeleteEntryInstruction() {
		test = extent.startTest("Verify resident user delete new entry instruction");
		Assert.assertTrue(entryInstructionsPage.deleteRecord());
	}

	/**
	 * Test of resident create new 'Do Not Allow' entry instruction
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void verifyResidentUserCreateEntryInstruction_DoNotAllow() throws Exception {
		test = extent.startTest("Verify resident create new 'Do Not Allow' entry instruction");
		SeleniumWrapper.refreshWebPage(driver);
		if (entryInstructionsPage.clickCreateEntryInstructionBtn())
			if (entryInstructionsPage.pickEntryInstructiontype_DoNotAllow())
				if (entryInstructionsPage.inputVisitorName())
					if (entryInstructionsPage.setStartDate())
						if (entryInstructionsPage.clickSaveBtn())
							Assert.assertTrue(entryInstructionsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of resident create new entry instruction for Housekeeper
	 */
	@Test(priority = 23)
	public void verifyResidentUserCreateNewEntryInstruction_Housekeeper() {
		test = extent.startTest("Verify resident create another new entry instruction for Housekeeper");
		if (entryInstructionsPage.clickCreateEntryInstructionBtn())
			if (entryInstructionsPage.pickEntryInstructiontype_Housekeeper())
				if (entryInstructionsPage.inputVisitorName())
					if (entryInstructionsPage.checkAllowToUseMyKey())
						if (entryInstructionsPage.setStartDate())
							if (entryInstructionsPage.setExpirationDate())
								if (entryInstructionsPage.clickSaveBtn())
									Assert.assertTrue(entryInstructionsPage.isSuccessMsgPresent());
	}

	/**
	 * Test default user PM log in application and navigate to Entry Instruction
	 * menu
	 */
	@Test(priority = 25)
	public void defaultUserLogInAndNavigateToEntryInstructionMenu() {
		test = extent.startTest("Default user PM log in application and navigate to Entry Instruction menu");
		if (entryInstructionsPage.logOutAsCurrentUser(driver))
			basePage.navigateTo();
		entryInstructionsPage.gotoEntryInstructionsPage();
		Assert.assertNotNull(entryInstructionsPage);
	}

	/**
	 * Test of PM search entry instruction from Active Tab
	 */
	@Test(priority = 27)
	public void verifySearchEntryInstructionFromActiveTab() {
		test = extent.startTest("Verify search entry instruction from Active Tab");
		Assert.assertTrue(entryInstructionsPage.searchEntryInstructiontypeFromActiveTab());
	}

	/**
	 * Test of PM view the entry instruction record from Active Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 29)
	public void verifyViewEntryInstructionRecord() throws Exception {
		test = extent.startTest("Verify view entry instruction record from Active Tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (entryInstructionsPage.viewEntryInstructionRecord())
			Assert.assertTrue(entryInstructionsPage.clickCancelBtn());
	}

	/**
	 * Test of user Property Manager Decline & Delete new entry instruction
	 */
	@Test(priority = 31)
	public void verifyPMDeclineNDeleteNewEntryInstruction() {
		test = extent.startTest("Verify user Property Manager Decline & Delete new entry instruction");
		if (entryInstructionsPage.viewEntryInstructionRecord())
			Assert.assertTrue(entryInstructionsPage.declineNDeleteNewEntryInstruction());
	}

	/**
	 * Test of user Property Manager approve new entry instruction
	 * 
	 * @throws Exception
	 */
	@Test(priority = 33)
	public void verifyPMApproveNewEntryInstruction() throws Exception {
		test = extent.startTest("Verify user Property Manager approve new entry instruction");
		SeleniumWrapper.refreshWebPage(driver);
		if (entryInstructionsPage.viewEntryInstructionRecord())
			if (entryInstructionsPage.approveNewEntryInstruction())
				Assert.assertTrue(entryInstructionsPage.goBackToEntryInstructionTab());
	}

	/**
	 * Test of Property Manager cant create new entry instruction without fill
	 * mandatory filed "Unit"
	 */
	@Test(priority = 35)
	public void verifyCantCreateNewEntryInstructionWithoutFillMandatoryFieldUnit() {
		test = extent.startTest("Verify cant create new entry instruction without fill mandatory filed 'Unit'");
		if (entryInstructionsPage.clickCreateEntryInstructionBtn())
			if (entryInstructionsPage.pickEntryInstructiontype_Contractor())
				if (entryInstructionsPage.inputVisitorName())
					if (entryInstructionsPage.inputAdditionalInformation())
						if (entryInstructionsPage.setStartDate())
							if (entryInstructionsPage.setExpirationDate())
								if (entryInstructionsPage.clickSaveBtn())
									if (entryInstructionsPage.isErrorMsgPresent())
										Assert.assertTrue(entryInstructionsPage.goBackToEntryInstructionTab());
	}

	/**
	 * Test of Property Manager(PM) create new entry instruction for Contractor
	 */
	@Test(priority = 37)
	public void verifyCreateNewEntryInstruction_Contractor() {
		test = extent.startTest("Verify Property Manager create new entry instruction for Contractor");
		if (entryInstructionsPage.clickCreateEntryInstructionBtn())
			if (entryInstructionsPage.pickEntryInstructiontype_Contractor())
				if (entryInstructionsPage.enterUnit())
					if (entryInstructionsPage.inputVisitorName())
						if (entryInstructionsPage.inputAdditionalInformation())
							if (entryInstructionsPage.setStartDate())
								if (entryInstructionsPage.setExpirationDate())
									if (entryInstructionsPage.clickSaveBtn())
										Assert.assertTrue(entryInstructionsPage.isSuccessMsgPresent());
	}

	/**
	 * Test of PM edit entry instruction by uploading photo from Active Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 39)
	public void verifyEditRecordByUploadingVisitorPhotoFromActiveTab() throws Exception {
		test = extent.startTest("Verify edit record by uploading visitor photo from Active Tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (entryInstructionsPage.clickEditIcon())
			if (entryInstructionsPage.uploadVisitorPhoto())
				Assert.assertTrue(entryInstructionsPage.clickSaveBtn());
	}
	
	/**
	 * Test of PM delete entry instruction from Active Tab
	 */
	@Test(priority = 41)
	public void verifyDeleteEntryInstructionFromActiveTab() {
		test = extent.startTest("Verify delete new entry instruction from Active Tab");
		Assert.assertTrue(entryInstructionsPage.deleteRecord());
	}
	

	/**
	 * Test of PM edit entry instruction by modifying expire date from Active Tab
	 */
	@Test(priority = 43)
	public void verifyEditRecordByModifyExpireDateFromActiveTab() {
		test = extent.startTest("Verify edit record by modifying expire date from Active Tab");
		if (entryInstructionsPage.clickEditIcon())
			if (entryInstructionsPage.modifyExpirationDate())
				Assert.assertTrue(entryInstructionsPage.clickSaveBtn());
	}


	/**
	 * Test of PM view entry instruction record from Completed Tab
	 */
	@Test(priority = 45)
	public void verifyViewEntryInstructionRecordFromCompletedTab() {
		test = extent.startTest("Verify view entry instruction record from Completed Tab ");
		if (entryInstructionsPage.gotoCompletedTab())
			if (entryInstructionsPage.viewEntryInstructionRecordFromCompletedTab())
				Assert.assertTrue(entryInstructionsPage.clickCancelBtn());
	}

	/**
	 * Test of PM edit entry instruction from Completed Tab
	 */
	@Test(priority = 47)
	public void verifyEditEntryInstructionFromCompletedTab() {
		test = extent.startTest("Verify edit entry instruction from Completed Tab");
		if (entryInstructionsPage.gotoCompletedTab())
			if (entryInstructionsPage.editEntryInstruction())
				Assert.assertTrue(entryInstructionsPage.clickSaveBtn());
	}

	/**
	 * Test of PM delete entry instruction from Completed Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 49)
	public void verifyDeleteEntryInstructionFromCompletedTab() throws Exception {
		test = extent.startTest("Verify delete entry instruction from Completed Tab");
		if (entryInstructionsPage.gotoCompletedTab())
			Assert.assertTrue(entryInstructionsPage.deleteRecord_CompletedTab());
	}

	/**
	 * Test of PM search entry instruction from Completed Tab
	 */
	@Test(priority = 51)
	public void verifySearchEntryInstructionFromCompletedTab() {
		test = extent.startTest("Verify search entry instruction from Completed Tab");
		if (entryInstructionsPage.gotoCompletedTab())
			Assert.assertTrue(entryInstructionsPage.searchEntryInstructiontypeFromCompletedTab());
	}
}
