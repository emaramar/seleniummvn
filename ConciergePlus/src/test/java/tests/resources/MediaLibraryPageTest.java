package tests.resources;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.resources.MediaLibraryPage;

public class MediaLibraryPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(MediaLibraryPageTest.class.getName());
	protected BasePage basePage = null;
	protected MediaLibraryPage mediaLibraryPage = null;

	/**
	 * Test of navigating to Media Library Page
	 */
	@Test(priority = 1)
	public void gotoMediaLibraryPage() {
		test = extent.startTest("Navigate to Media Library Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		mediaLibraryPage = basePage.gotoMediaLibraryPage();
		Assert.assertNotNull(mediaLibraryPage);
	}

	/**
	 * Test of create Folder
	 */
	@Test(priority = 3)
	public void testCreateFolder() {
		test = extent.startTest("Verify create Folder");
		if (mediaLibraryPage.clickCreateBtn())
			if (mediaLibraryPage.clickCreateFolder())
				if (mediaLibraryPage.clickSaveBtn())
					Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of create Link
	 */
	@Test(priority = 5)
	public void testCreateLink() {
		test = extent.startTest("Verify create Link");
		if (mediaLibraryPage.clickCreateBtn())
			if (mediaLibraryPage.clickCreateLink())
				if (mediaLibraryPage.clickSaveBtn_CreateLink())
					Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());

	}

	/**
	 * Test of create TextFile
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(priority = 7)
	public void testCreateTextFile() throws Exception {
		test = extent.startTest("Verify create TextFile");
		if (mediaLibraryPage.clickCreateBtn())
			if (mediaLibraryPage.clickCreateTextFile())
				if (mediaLibraryPage.clickSaveBtn_CreateTextFile())
					Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of upload image file
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void testUploadFileOfImg() throws Exception {
		test = extent.startTest("Verify upload image file");
		if (mediaLibraryPage.uploadFile())
			if (mediaLibraryPage.selectFile())
				Assert.assertTrue(mediaLibraryPage.clickSaveNCloseBtn());
	}

	/**
	 * Test of upload PDF file by automatically start uploads
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void testAutoUploadFile() throws Exception {
		test = extent.startTest("Verify upload multiple files by automatically start uploads");
		if (mediaLibraryPage.uploadFile())
			if (mediaLibraryPage.selectNAutoUploadFile())
				if (mediaLibraryPage.enterFileDescription())
					if (mediaLibraryPage.makeFilePublic())
						Assert.assertTrue(mediaLibraryPage.clickSaveNCloseBtn());
	}

	/**
	 * Test of upload multiple files
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void testUploadMultipleFiles() throws Exception {
		test = extent.startTest("Verify upload multiple files by automatically start uploads");
		if (mediaLibraryPage.uploadFile())
			if (mediaLibraryPage.selecMultipletFiles())
				Assert.assertTrue(mediaLibraryPage.clickSaveNCloseBtn());
	}

	/**
	 * Test of edit Folder by add Restrict access group
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void testEditFolderByAddRestrictAccessGroup() throws Exception {
		test = extent.startTest("Verify edit Folder by add Restrict access group");
		if (mediaLibraryPage.editFolder())
			if (mediaLibraryPage.clickSaveBtn())
				Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of edit Link by public Link to home page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 17)
	public void testEditLinkByPublicLinkToHomePage() throws Exception {
		test = extent.startTest("Verify edit Link by public Link to home page");
		if (mediaLibraryPage.editLink())
			if (mediaLibraryPage.clickEditBtn())
				if (mediaLibraryPage.makePublicToHomePage())
					if (mediaLibraryPage.clickEditSaveBtn())
						if (mediaLibraryPage.isSuccessMsgPresent())
							Assert.assertTrue(mediaLibraryPage.isLinkMarkSharedPublicIcon());
	}

	/**
	 * Test of edit TextFile
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void testEditTextFile() throws Exception {
		test = extent.startTest("Verify edit TextFile");
		if (mediaLibraryPage.editTxtFile())
			if (mediaLibraryPage.checkDownloadBtn())
				if (mediaLibraryPage.clickEditBtn())
					if (mediaLibraryPage.checkDownloadBtn())
						if (mediaLibraryPage.makePublicToHomePage())
							if (mediaLibraryPage.clickEditSaveBtn())
								if (mediaLibraryPage.isSuccessMsgPresent())
									Assert.assertTrue(mediaLibraryPage.isTextFileMarkSharedPublicIcon());
	}

	/**
	 * Test of edit image by public to Home Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void testEditImageByPublicToHomePage() throws Exception {
		test = extent.startTest("Verify edit image by public to Home page");
		if (mediaLibraryPage.editImage())
			if (mediaLibraryPage.checkDownloadBtn())
				if (mediaLibraryPage.makePublicToHomePage())
					if (mediaLibraryPage.clickEditSaveBtn())
						if (mediaLibraryPage.isSuccessMsgPresent())
							Assert.assertTrue(mediaLibraryPage.isImgMarkSharedPublicIcon());
	}

	/**
	 * Test of public files are present on Homepage
	 */
	@Test(priority = 23)
	public void testArePublicFilesPresentOnHomepage() {
		test = extent.startTest("Verify are public PDF files present on Homepage");
		if (mediaLibraryPage.logOutAsCurrentUser(driver))
			Assert.assertTrue(mediaLibraryPage.areSharedFilesPresentOnHomePage());
	}

	/**
	 * Test of move one file to Folder
	 */
	@Test(priority = 25)
	public void testMoveOneToFolder() {
		test = extent.startTest("Verify move one file to Folder");
		basePage.navigateTo();
		mediaLibraryPage = basePage.gotoMediaLibraryPage();
		if (mediaLibraryPage.moveOneFileTofolder())
			Assert.assertTrue (mediaLibraryPage.isMoveFileToFolderSuccess());
				if(mediaLibraryPage.goBackMediaLibraryTab());
	}

	/**
	 * Test move multiple files together(Link and Text file) to folder
	 */
	@Test(priority = 27)
	public void testMoveMultipleFilesToFolder() {
		test = extent.startTest("Verify move multiple files together to folder");
		if (mediaLibraryPage.moveMultipleFilesToFolder())
			Assert.assertTrue (mediaLibraryPage.areMoveMultipleFilesToFolderSuccess());
				if(mediaLibraryPage.goBackMediaLibraryTab());
	}

	/**
	 * Test of delete file by clicking trash icon
	 */
	@Test(priority = 29)
	public void testDeleteFileByClickingTrashIcon() {
		test = extent.startTest("Verify delete file by clicking trash icon");
		if (mediaLibraryPage.deleteFile())
			Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of delete one uploaded file by Delete Container
	 * 
	 * @throws Exception
	 */
	@Test(priority = 31)
	public void testDeleteOneFile() throws Exception {
		test = extent.startTest("Verify delete one uploaded file");
		Assert.assertTrue(mediaLibraryPage.deleteOneFileByDeleteContain());
	}

	/**
	 * Test of delete multiple uploaded file by Delete Container
	 */
	@Test(priority = 33)
	public void testDeleteMultipleFiles() throws Exception {
		test = extent.startTest("Verify delete multiple uploaded files");
		Assert.assertTrue(mediaLibraryPage.deleteMultipleFilesByDeleteContainer());
	}

	/**
	 * Test search function
	 */
	@Test(priority = 35)
	public void testSearchFunction() {
		test = extent.startTest("Verify search function");
		Assert.assertTrue(mediaLibraryPage.searchFile());
	}

	/**
	 * Test view folder from Thumbnail View
	 */
	@Test(priority = 37)
	public void testViewFolderFromThumbnailView() throws Exception {
		test = extent.startTest("Verify view folder from Thumbnail View");
		SeleniumWrapper.refreshWebPage(driver);
		if (mediaLibraryPage.clickThumbnailViewTab())
			Assert.assertTrue(mediaLibraryPage.viewFolder());
				if(mediaLibraryPage.goBackMediaLibraryTab());
	}

	/**
	 * Test edit Folder from Thumbnail View
	 */
	@Test(priority = 39)
	public void testEditFolderFromThumbnailView() {
		test = extent.startTest("Verify  edit Folder from Thumbnail View");
		if (mediaLibraryPage.clickThumbnailViewTab())
			if (mediaLibraryPage.editFolderFromThumbnailView())
				if (mediaLibraryPage.clickSaveBtn())
					Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
						if(mediaLibraryPage.clickListViewTab());
	}

	/**
	 * Test create a folder in Folder from Thumbnail View
	 */
	@Test(priority = 41)
	public void testCreateFolderFromThumbnailView() {
		test = extent.startTest("Verify create a folder in Folder from Thumbnail View");
		if (mediaLibraryPage.clickThumbnailViewTab())
			if (mediaLibraryPage.clickFolder())
				if (mediaLibraryPage.clickCreateBtn())
					if (mediaLibraryPage.clickCreateFolder())
						if (mediaLibraryPage.clickSaveBtn())
							Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
								if(mediaLibraryPage.goBackMediaLibraryTab());
	}

	/**
	 * Test upload PDF file in Folder from Thumbnail View
	 * 
	 * @throws Exception
	 */
	@Test(priority = 43)
	public void testUploadFileInFolderFromThumbnailView() throws Exception {
		test = extent.startTest("Verify upload file in Folder from Thumbnail View");
		if (mediaLibraryPage.clickThumbnailViewTab())
			if (mediaLibraryPage.clickFolder())
				if (mediaLibraryPage.OpenFolderFromfolderFromThumbnailViewTab())
					if (mediaLibraryPage.uploadFile())
						if (mediaLibraryPage.selectNAutoUploadFile())
							if (mediaLibraryPage.makeFilePublic())
								Assert.assertTrue(mediaLibraryPage.clickSaveNCloseBtn());
									if(mediaLibraryPage.goBackMediaLibraryTab());
	}

	/**
	 * Test of delete file in folder from thumbnail View
	 */
	@Test(priority = 45)
	public void testDeleteFileInFolder() {
		test = extent.startTest("Verify delete file in folder from thumbnail View");
		if (mediaLibraryPage.clickThumbnailViewTab())
			if (mediaLibraryPage.clickFolder())
				if (mediaLibraryPage.OpenFolderFromfolderFromThumbnailViewTab())
					if (mediaLibraryPage.deleteFile())
						Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
							if(mediaLibraryPage.goBackMediaLibraryTab());
	}

	/**
	 * Test edit file from Thumbnail View
	 */
	@Test(priority = 47)
	public void testEditFileFromThumbnailView() {
		test = extent.startTest("Verify edit file from Thumbnail View");
		if (mediaLibraryPage.clickThumbnailViewTab())
			if (mediaLibraryPage.editFileFromThumbnailViewTab())
				if (mediaLibraryPage.clickEditSaveBtn())
					Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (mediaLibraryPage.clickListViewTab())
			;
	}

	/**
	 * Test delete file from Thumbnail View
	 */
	@Test(priority = 49)
	public void testDeleteFileFromThumbnailView() {
		test = extent.startTest("Verify delete file from Thumbnail View");
		if (mediaLibraryPage.clickThumbnailViewTab())
			if (mediaLibraryPage.deleteFileFromThumbnailViewTab())
				Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (mediaLibraryPage.clickListViewTab())
			;
	}

	/**
	 * Test delete folder from Thumbnail View
	 */
	@Test(priority = 51)
	public void testDeleteFolderFromThumbnailView() {
		test = extent.startTest("Verify delete folder from Thumbnail View");
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if (mediaLibraryPage.deleteFolder())
			Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
	}

	/**
	 * Test view and download PDF file from folder
	 */
	@Test(priority = 53)
	public void testViewAndDownloadPDFFileFromFolder() {
		test = extent.startTest("Verify view and download PDF file from folder ");
		if (mediaLibraryPage.clickToOpenFolder())
			if (mediaLibraryPage.clickToOpenFile())
				if (mediaLibraryPage.checkDownloadBtn())
					Assert.assertTrue(mediaLibraryPage.clickCancelBtn());
						if(mediaLibraryPage.goBackMediaLibraryTab());
	}

	/**
	 * Test view and edit PDF file from folder
	 * 
	 * @throws Exception
	 */
	@Test(priority = 55)
	public void testViewAndEditPDFFileFromFolder() throws Exception {
		test = extent.startTest("Verify view and edit PDF file from folder ");
		if (mediaLibraryPage.clickToOpenFolder())
			if (mediaLibraryPage.clickToOpenFile())
				if (mediaLibraryPage.clickEditBtn())
					if (mediaLibraryPage.modifyFileName())
						if (mediaLibraryPage.clickEditSaveBtn())
							Assert.assertTrue(mediaLibraryPage.isSuccessMsgPresent());
	}

}
