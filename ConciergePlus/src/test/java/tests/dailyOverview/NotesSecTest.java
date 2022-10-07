package tests.dailyOverview;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.dailyOverview.NotesSec;

public class NotesSecTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(NotesSecTest.class.getName());
	protected BasePage basePage = null;
	protected NotesSec notesSec = null;

	/**
	 * Test of navigating to Notes section
	 */
	@Test(priority = 1)
	public void gotoNotesSection() {
		test = extent.startTest("Navigate to Notes section");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		notesSec = basePage.gotoNotesSec();
		Assert.assertNotNull(notesSec);
	}

	/**
	 * Test of create a Visitor Note
	 */
	@Test(priority = 3)
	public void testCreateVisitorNote() {
		test = extent.startTest("Test create a Visitor note");
		if (notesSec.clickCreateNoteBtn())
			if (notesSec.inputVisitorName())
				if (notesSec.inputDescriptionOfVisitorNote())
					if (notesSec.clickSaveBtn())
						Assert.assertTrue(notesSec.isSuccessMsgPresent());
	}

	/**
	 * Test of create a Shift Note with "Sign Key Out"
	 * 
	 */
	@Test(priority = 5)
	public void testCreateShiftNoteWithSignKeyOut() {
		test = extent.startTest("Test create a Shift note with Sign Key Out");
		if (notesSec.clickCreateNoteBtn())
			if (notesSec.clickType())
				if (notesSec.pickShiftNoteFromTypeDropdown())
					if (notesSec.inputDescriptionOfShiftNote())
						if (notesSec.checkSignKeyOutBox())
							if (notesSec.clickAndPickRelatedKeys())
								if (notesSec.clickSaveBtn())
									Assert.assertTrue(notesSec.isSuccessMsgPresent());
	}

	/**
	 * Test of create a "Pre-Authorized Visitor" note with Allow Access
	 */
	@Test(priority = 7)
	public void testCreatePreAuthorizedVisitorNote_AllowAccess() {
		test = extent.startTest("Test create 'Pre-Authorized Visitor'note with Allow Access");
		if (notesSec.clickCreateNoteBtn())
			if (notesSec.clickType())
				if (notesSec.pickPreAuthorizedVisitor())
					if (notesSec.pickRelatedUnit_PreAuthorizedVisitor1())
						if (notesSec.verifyDisplayOfPreauthorizedEntryInstruction())
		if (notesSec.enterDescOfAllowEntry())
			Assert.assertTrue (notesSec.clickSaveBtn());
	}

	/**
	 * Test of create a "Pre-Authorized Visitor" note with Not Allow Access (this
	 * function is not working currently)
	 */
	@Test(priority = 9)
	public void testCreatePreAuthorizedVisitorNote_NotAllowAccess() {
		test = extent.startTest("Test create 'Pre-Authorized Visitor'note with Not Allow Access");
		if (notesSec.clickCreateNoteBtn())
			if (notesSec.clickType())
				if (notesSec.pickPreAuthorizedVisitor())
					if (notesSec.pickRelatedUnit_PreAuthorizedVisitor2())
						SeleniumWrapper.waitForDomToBeRendered(driver);
		if (notesSec.enterDescriptionOfNotAllowEntry())
			Assert.assertTrue(notesSec.clickSaveBtn_NotAllowAccessNote());
				if(notesSec.clickCancelBtn());
	}

	/**
	 * create note from "View All Notes" section Test of create a Visitor Note with
	 * "Assign Visitor's Parking"
	 */
	@Test(priority = 11)
	public void testCreateVisitorNoteWithAssignVisitorsParking() throws Exception {
		test = extent.startTest("Test create a Visitor note with Assign Visitor's Parking");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			if (notesSec.clickCreateNoteBtnFromViewAllNotesPage())
				if (notesSec.pickRelatedUnit_AssignParkingSpot())
					if (notesSec.inputVisitorNameForVisitorNote())
						if (notesSec.checkAssignVisitorsParkingBox())
							if (notesSec.clickAssignedSpot())
								if (notesSec.searchAndPickCarSpot())
									if (notesSec.inputCarPlate())
										if (notesSec.pickCarMaker())
											if (notesSec.pickExpiresTimeSlot())
												if (notesSec.clickSaveBtn())
													Assert.assertTrue(notesSec.isSuccessMsgPresent());
	}

	/**
	 * create note from "View All Notes" section Test of create a Key Out Note
	 */
	@Test(priority = 13)
	public void testCreateKeyOutNote() {
		test = extent.startTest("Test create a Key Out note");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			if (notesSec.clickCreateNoteBtnFromViewAllNotesPage())
				if (notesSec.clickType())
					if (notesSec.pickKeyOutFromTypeDropdown())
						if (notesSec.inputVisitorNameForKeyOutNote())
							if (notesSec.inputDescriptionOfKeyOutNote())
								if (notesSec.clickSaveBtn())
									Assert.assertTrue(notesSec.isSuccessMsgPresent());
	}

	/**
	 * Test view notes by type of "View Incident Reports"
	 */
	@Test(priority = 15)
	public void testViewIncidentReportsNotes() {
		test = extent.startTest("Test view incident reports notes");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			Assert.assertTrue(notesSec.viewNotesByTypeOfViewIncidentReports());
	}

	/**
	 * Test view Keys In/Keys Out notes
	 */
	@Test(priority = 17)
	public void testViewKeysInNKeysOutNotes() {
		test = extent.startTest("Test view Keys In/Keys Out notes");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			Assert.assertTrue(notesSec.viewNotesByTypeOfViewKeysInKeyOut());
	}

	/**
	 * Test view notes by type of "View Notes"
	 */
	@Test(priority = 19)
	public void testViewShiftNotes() {
		test = extent.startTest("Test view shift notes");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			Assert.assertTrue(notesSec.viewNotesByTypeOfViewNotes());
	}

	/**
	 * Test view notes by type of "View Visitor Logs"
	 */
	@Test(priority = 21)
	public void testViewVisitorLogsNotes() {
		test = extent.startTest("Test view visitor logs notes");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			Assert.assertTrue(notesSec.viewNotesByTypeOfVisitorLogs());
	}

	/**
	 * Test view notes by creator "Tina Lau"
	 */
	@Test(priority = 23)
	public void testViewNotesCreatedByTinaLau() {
		test = extent.startTest("Test view notes created by Tina Lau");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			Assert.assertTrue(notesSec.viewNotesByCreatorOfTinaLau());
	}

	/**
	 * Test of view note details by click "VIEW DETAILS"
	 */
	@Test(priority = 25)
	public void testViewNoteDetails() {
		test = extent.startTest("Test view note details by click 'VIEW DETAILS'");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			if (notesSec.clickViewDetailsBtn())
				Assert.assertTrue(notesSec.closeNoteDetailsPage());
	}

	/**
	 * Test of edit note by click "Edit" icon
	 */
	@Test(priority = 27)
	public void testEditNote() {
		test = extent.startTest("Test edite note");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			if (notesSec.clickEditNoteIcon())
				if (notesSec.editNote())
					Assert.assertTrue(notesSec.clickSaveNoteBtn());
	}

	/**
	 * Test of delete Visitor Note
	 */
	@Test(priority = 29)
	public void testDeleteVisitorNote() {
		test = extent.startTest("Test delete 'Visitor' note");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			if (notesSec.clickDeleteIconOnVisitorNote())
				Assert.assertTrue(notesSec.clickConfirmBtn());
	}

	/**
	 * Test of delete Key Out note
	 */
	@Test(priority = 31)
	public void testDeleteKeyOutNote() {
		test = extent.startTest("Test delete 'Key Out' note");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			if (notesSec.clickDeleteIconOnKeyOutNote())
				Assert.assertTrue(notesSec.clickConfirmBtn());
	}

	/**
	 * Test of view all notes for specific date
	 */
	@Test(priority = 33)
	public void testViewAllNotesForSpecificDate() {
		test = extent.startTest("Test view all notes for specific date");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			if (notesSec.clickCalendarIcon())
				Assert.assertTrue(notesSec.pickDateFromCalendar());
	}

	/**
	 * Test search note
	 */
	@Test(priority = 35)
	public void testSearchNote() {
		test = extent.startTest("Test search note");
		basePage.gotoNotesSec();
		if (notesSec.clickViewAllNotesBtn())
			Assert.assertTrue(notesSec.searchNote());
	}

}
