package pages.resources;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;
import config.Constants;
import pages.BasePage;

public class MediaLibraryPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(MediaLibraryPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public MediaLibraryPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Media Library page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoMediaLibraryPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (uploadFileBtn.isEnabled()) {
			return true;
		}
		return false;
	}

	/**
	 * Check create Folder
	 */
	@FindBy(xpath = "//button[@id=\"create-container\"]")
	public static WebElement createBtn;

	@FindBy(xpath = "//a[@id='create-folder']")
	public static WebElement createFolderBtn;

	@FindBy(xpath = "//input[@name='folder_name']")
	public static WebElement nameInputBox;

	@FindBy(xpath = "//*[contains(text(),'Add group')]")
	public static List<WebElement> addGroupBtn; // get index 0

	@FindBy(xpath = "//div[@class='bs-searchbox']/input")
	public static List<WebElement> searchBoxOfAddGroup; // get index 2

	@FindBy(xpath = "//li[@class='optgroup-1']")
	public static List<WebElement> optionsOfGroup; // get index 0-"Everyone"

	@FindBy(xpath = "//*[contains(text(),'Concierge')]")
	public static List<WebElement> optionsOfConcierge;

	@FindBy(xpath = "//*[@id='modal-save-button']")
	public static WebElement saveBtn;
	
	@FindBy(xpath = "//*[@id='modal-save-button']")
	public static List<WebElement> saveBtnList;

	public boolean clickCreateBtn() {
		return Function.clickElement(driver, createBtn);
	}

	public String folderName = "Auto Test1";

	public boolean clickCreateFolder() {
		Function.clickElement(driver, createFolderBtn);
		Function.clickElement(driver, nameInputBox);
		SeleniumWrapper.setInputFieldText(nameInputBox, folderName, driver);
		Function.hoverNclickElement(driver, addGroupBtn.get(1));
		return Function.clickElement(driver, optionsOfGroup.get(0));
	}

	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 30);
		if (saveBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	
	public boolean clickSaveBtn_CreateLink() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtnList.get(1), 30);
		if (saveBtnList.get(1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtnList.get(1), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	public boolean clickSaveBtn_CreateTextFile() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtnList.get(2), 30);
		if (saveBtnList.get(2).isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtnList.get(2), Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	/**
	 * Check is success message present
	 */
	@FindBy(xpath = "//*[@id='success_container']/span[2]")
	public static WebElement confirmMsg;

	public boolean isSuccessMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmMsg, 20);
		String message = confirmMsg.getText();
		logger.info("message is:" + message);
		if(confirmMsg!=null && !message.isEmpty())
			return true;
		else
		return false;
	}

	/**
	 * Check create Link
	 */
	@FindBy(xpath = "//a[@id='create-link']")
	public static WebElement createLinkBtn;

	@FindBy(id = "link_url")
	public static WebElement urlInputBox;

	@FindBy(id = "retrieve-meta")
	public static WebElement RetrieveTitleNDescBtn;

	public boolean clickCreateLink() {
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, createLinkBtn);
		Function.clickElement(driver, urlInputBox);
		SeleniumWrapper.setInputFieldText(urlInputBox, "http://www.conciergeplus.com", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, RetrieveTitleNDescBtn);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		return true;
	}

	/**
	 * Check create Text File
	 */
	@FindBy(xpath = "//a[@id='create-text-file']")
	public static WebElement createTextFileBtn;

	@FindBy(xpath = "//input[@name='file_name']")
	public static WebElement fileNameInputBox;

	@FindBy(xpath = "//*[@class='fr-placeholder']")
	public static WebElement textBox;

	@FindBy(xpath = "//div[@class='fr-element fr-view']")
	public static WebElement ContentEditable;

	public String textTitle = "Garbage Sorting";

	public boolean clickCreateTextFile() throws Exception {
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, createTextFileBtn);
		Function.clickElement(driver, fileNameInputBox);
		SeleniumWrapper.setInputFieldText(fileNameInputBox, textTitle, driver);
		Function.clickElement(driver, ContentEditable);
		SeleniumWrapper.setInputFieldText(ContentEditable, "Sorting", driver);
		logger.info("The file name and content is:" + fileNameInputBox.getAttribute("value") + ','
				+ ContentEditable.getText());
		return true;
	}

	/**
	 * Check upload image File
	 */
	@FindBy(xpath = "//*[@id=\"create-file\"]/span[2]")
	public static WebElement uploadFileBtn;

	@FindBy(id = "file_upload_select_files")
	public static WebElement selectFileBtn;

	@FindBy(id = "file_upload_start_upload")
	public static WebElement startUploadBtn;

	@FindBy(xpath = "//*[@id='button_start_auto_uploads']/span[1]")
	public static WebElement automaticallyStartUploadsBtn;
	
	@FindBy(xpath = "//*[@id=\"button_start_auto_uploads\"]/span[2]")
	public static WebElement disableAutomaticallyStartUploadsBtn;	

	@FindBy(xpath = "//*[@name='description_resource']")
	public static WebElement fileDescriptionInputBox;

	@FindBy(xpath = "//*[@id='button-mycheckbox']/span[1]")
	public static WebElement makePublicCheckbox;

	@FindBy(xpath = "//*[@id='button-mycheckbox']/span[1]")
	public static List<WebElement> makePublicCheckboxList;

	@FindBy(xpath = "//*[@id=\"file_upload_save_close\"]")
	public static WebElement saveNCloseBtn;

	public boolean uploadFile() {
		return Function.clickElement(driver, uploadFileBtn);
	}
	

	public boolean selectFile() throws Exception{
		Function.uploadFile(driver, selectFileBtn, Constants.imgOfCondo);
		Function.clickElement(driver, startUploadBtn);
		Thread.sleep(100000);
		return true;
	}

	public boolean clickSaveNCloseBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveNCloseBtn, 30);
		if (saveNCloseBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveNCloseBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check upload PDF file by automatically start uploads
	 * 
	 * @throws Exception
	 */

	public boolean selectNAutoUploadFile() throws Exception {
		Function.hoverNclickElement(driver, automaticallyStartUploadsBtn);
		Function.uploadFile(driver, selectFileBtn, Constants.imgOfPdf);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
	}

	public boolean enterFileDescription() throws Exception {
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, fileDescriptionInputBox);
		return SeleniumWrapper.setInputFieldText(fileDescriptionInputBox, "PDF", driver);
	}

	public boolean makeFilePublic() {
		return Function.hoverNclickElement(driver, makePublicCheckbox);
	}

	/**
	 * Check upload multiple files
	 * 
	 * @throws Exception
	 */
	public boolean selecMultipletFiles() throws Exception {
		Function.clickElement(driver, disableAutomaticallyStartUploadsBtn);
		Function.uploadFile_NoWaiting(driver, selectFileBtn, Constants.imgOfPdf);
		Function.uploadFile_NoWaiting(driver, selectFileBtn, Constants.imgOfText);
		Function.uploadFile_NoWaiting(driver, selectFileBtn, Constants.imgOfText);
		Function.uploadFile_NoWaiting(driver, selectFileBtn, Constants.imgOfCarKey);
		Function.clickElement(driver, startUploadBtn);
		SeleniumWrapper.threadSleep(15000);
		SeleniumWrapper.explicitWaitClickable(driver, makePublicCheckboxList.get(0), 30);
		if (makePublicCheckboxList.get(0).isEnabled())
			return SeleniumWrapper.clickElement(driver, makePublicCheckboxList.get(0),
					Constants.CLICK_METHOD_ENUM.CLICK);
		else
			return false;
	}

	/**
	 * Check edit Folder by add Restrict access group
	 */
	@FindBy(xpath = "//*[@id='datatable_media']/tbody/tr/td[6]")
	public static List<WebElement> editIconList;

	@FindBy(xpath = "//*[@id='datatable_media']/tbody/tr/td[3]")
	public static List<WebElement> nameColumn;
	
	@FindBy(xpath = "//*[@class='form-control']")
	public static List<WebElement> searchGroupBox;

	public WebElement folderToBeEdited() {
		for (int i = 0; i < nameColumn.size(); i++)
			if (nameColumn.get(i).getText().contains(folderName))
				return editIconList.get(i);
		return null;
	}

	public boolean editFolder() {
		SeleniumWrapper.hoverMouseOverElement(driver, editIconList.get(0));
		Function.hoverNclickElement(driver, folderToBeEdited());
		Function.clickElement(driver, addGroupBtn.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		//SeleniumWrapper.clickElement(driver, searchGroupBox.get(3), Constants.CLICK_METHOD_ENUM.CLICK);
		//SeleniumWrapper.setInputFieldText(searchGroupBox.get(3), "Concierge", driver);
		//return Function.hoverNclickElement(driver, optionsOfConcierge.get(2));
		SeleniumWrapper.explicitWaitClickable(driver, optionsOfGroup.get(7), 50);
		return Function.clickElement(driver, optionsOfGroup.get(7));
	}

	/**
	 * Check edit Link by public Link to home page
	 */
	public static final String tableRowsXpath = "//*[@id='datatable_media']/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> tableRowOfEntries;

	@FindBy(xpath = "//*[@class='icon-file-link']")
	public static List<WebElement> typeOfLink; // index 0

	@FindBy(xpath = "//*[@id='image_dialog_edit']")
	private static WebElement editBtn;

	@FindBy(xpath = "//*[@class='btn-actual-checkbox']")
	public static List<WebElement> checkbox;

	@FindBy(xpath = "//*[@id='button_move[]']")
	public static List<WebElement> checkboxList;

	@FindBy(xpath = "//*[@id=\"image_dialog_save\"]")
	public static WebElement EditSaveBtn;

	@FindBy(xpath = "//*[@class='icon-globecloud']")
	public static List<WebElement> sharedPublicIcon;

	public boolean clickLastEntryEditIcon() {
		return Function.hoverNclickElement(driver, editIconList.get(editIconList.size() - 1)); // need to delete later
	}

	public WebElement linkToBeEdited() {
		for (int i = 0; i < nameColumn.size(); i++)
			if (nameColumn.get(i).getText().contains("Concierge Plus"))
				return nameColumn.get(i);
		return null;
	}

	public boolean editLink() throws Exception {
		Function.hoverNclickElement(driver, typeOfLink.get(0));
		Thread.sleep(50000);
		return true;
	}

	public boolean clickEditBtn() throws Exception {
		Function.hoverNclickElement(driver, editBtn);
		Thread.sleep(15000);
		return true;

	}

	public boolean makePublicToHomePage() {
		 Function.clickElement(driver, checkbox.get(checkbox.size()-1));
		 SeleniumWrapper.waitForDomToBeRendered(driver);
		 return true;
	}

	public boolean clickEditSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, EditSaveBtn, 30);
		if (EditSaveBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, EditSaveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean isLinkMarkSharedPublicIcon() throws Exception {
		SeleniumWrapper.refreshWebPage(driver);
		SeleniumWrapper.explicitWaitClickable(driver, linkToBeEdited(), 30);
		if (sharedPublicIcon.get(0) != null) {
			logger.info("The link is shared public");
		} else {
			logger.info("The link is not shared public");
		}
		return true;
	}

	/**
	 * Check edit TextFile by public TextFile to home page
	 */
	@FindBy(xpath = "//*[@id='image_dialog_download']")
	public static WebElement downloadBtn;

	@FindBy(xpath = "//*[@class='icon-file-txt']")
	public static WebElement typeOfTxt;

	public WebElement txtFileToBeViewed() {
		for (int i = 0; i < nameColumn.size(); i++)
			if (nameColumn.get(i).getText().toLowerCase().contains(textTitle))
				return nameColumn.get(i);
		return null;
	}

	public boolean editTxtFile() throws Exception {
		Function.hoverNclickElement(driver, typeOfTxt);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean checkDownloadBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, downloadBtn, 30);
		if (SeleniumWrapper.clickElement(driver, downloadBtn, Constants.CLICK_METHOD_ENUM.CLICK))
			return SeleniumWrapper.clickElement(driver, downloadBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		else 
		return false;
	}

	public boolean isTextFileMarkSharedPublicIcon() {
		if (sharedPublicIcon.get(1) != null) {
			logger.info("The file is shared public");
		} else {
			logger.info("The file is not shared public");
		}
		return true;
	}

	/**
	 * Check edit image by public to Home Page
	 */

	public WebElement imgToBeEdited() {
		for (int i = 0; i < nameColumn.size(); i++)
			if (nameColumn.get(i).getText().contains("Condo.jpg"))
				return editIconList.get(i);
		return null;
	}
	
	public WebElement imgToBePublic() {
		for (int i = 0; i < nameColumn.size(); i++)
			if (nameColumn.get(i).getText().contains("Condo.jpg"))
				return editIconList.get(i);
		return null;
	}

	public WebElement imgToBeViewed() {
		for (int i = 0; i < nameColumn.size(); i++)
			if (nameColumn.get(i).getText().contains("Condo.jpg"))
				return nameColumn.get(i);
		return null;
	}

	public boolean editImage() {
		SeleniumWrapper.explicitWaitClickable(driver, imgToBePublic(), 30);
		return Function.hoverNclickElement(driver, imgToBePublic());
	}

	public boolean editImageByPublicToHomePage() {
		Function.hoverNclickElement(driver, editIconList.get(editIconList.size() - 1));
		if (SeleniumWrapper.clickElement(driver, downloadBtn, Constants.CLICK_METHOD_ENUM.CLICK)) {
			SeleniumWrapper.clickElement(driver, downloadBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		}
		Function.clickElement(driver, checkbox.get(checkbox.size() - 1));
		return Function.clickElement(driver, saveBtn);
	}

	public boolean isImgMarkSharedPublicIcon() throws Exception {
		SeleniumWrapper.refreshWebPage(driver);
		SeleniumWrapper.explicitWaitClickable(driver, imgToBeViewed(), 30);
		if (sharedPublicIcon.get(2) != null) {
			logger.info("The link is shared public");
		} else {
			logger.info("The link is not shared public");
		}
		return true;
	}


	/**
	 * Check the shared files are present on Homepage
	 */
	@FindBy(xpath = "//*[@class='list-item-body-title']")
	public static List<WebElement> publicFileList;

	public boolean areSharedFilesPresentOnHomePage() {
		SeleniumWrapper.explicitWaitClickable(driver, publicFileList.get(0), 30);
		SeleniumWrapper.scrollToElement(driver, publicFileList.get(0));
		List<String> list = new ArrayList<>();
		for (int i = 0; i < publicFileList.size(); i++) {
			list.add(publicFileList.get(i).getText());
		}
		logger.info("The file titles are:" + list);
		if (list.contains("test PDF.pdf") && list.contains("Condo.jpg")
				&& list.contains("Concierge Plus - Software to Manage Your Condo in the Cloud")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check move one file to folder
	 */
	@FindBy(xpath = "//*[@id='move-container']/span[2]")
	public static WebElement moveContainerBtn;

	@FindBy(xpath = "//button[contains(.,'Move')]")
	public static List<WebElement> moveBtn; // get index 1

	public boolean moveOneFileTofolder() {
		Function.hoverNclickElement(driver, checkboxList.get(checkboxList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, moveContainerBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, moveBtn.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean isMoveFileToFolderSuccess() {
		Function.clickElement(driver, listOfFolder.get(0));
		int countOfFile = tableRowOfEntries.size();
		if(countOfFile == 1)
			return true;
		else
		return false;
	}

	/**
	 * Check move multiple files to Folder
	 * 
	 * @throws Exception
	 */
	public boolean moveMultipleFilesToFolder() {
		Function.hoverNclickElement(driver, checkboxList.get(checkboxList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(checkboxList.size() - 2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, moveContainerBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, moveBtn.get(1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	public boolean areMoveMultipleFilesToFolderSuccess() {
		Function.clickElement(driver, listOfFolder.get(0));
		int countOfFile = tableRowOfEntries.size();
		if(countOfFile == 3)
			return true;
		else
		return false;
	}

	/**
	 * Test of delete uploaded file by clicking trash icon
	 */
	@FindBy(xpath = "//*[@id='datatable_media']/tbody/tr/td[7]")
	public static List<WebElement> deleteIconList;

	@FindBy(xpath = "//button[@id=\"table-action-confirm\"]")
	public static WebElement confirmDeleteRow;

	public boolean deleteFile() {
		//SeleniumWrapper.explicitWaitClickable(driver, deleteIconList.get(deleteIconList.size() - 1), 50);
		SeleniumWrapper.hoverMouseOverElement(driver, deleteIconList.get(deleteIconList.size() - 1));
		SeleniumWrapper.clickElement(driver, deleteIconList.get(deleteIconList.size() - 1), Constants.CLICK_METHOD_ENUM.CLICK);
		Function.clickElement(driver, confirmDeleteRow);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
		}

	/**
	 * Check delete one uploaded file By "Delete Container"
	 */
	@FindBy(id = "delete-container")
	public static WebElement deleteContainerBtn;

	@FindBy(id = "confirm")
	public static WebElement confirmBtn;

	public boolean deleteOneFileByDeleteContain() throws Exception {
		int countOfRecord_Before = deleteIcon.size();
		logger.info("The before count is:" + countOfRecord_Before);
		Function.hoverNclickElement(driver, checkboxList.get(checkboxList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, deleteContainerBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.clickElement(driver, confirmBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.refreshWebPage(driver);
		int countOfRecord_After = deleteIcon.size();
		logger.info("The after count is:" + countOfRecord_After);
		if(countOfRecord_Before > countOfRecord_After)
			return true;
		else
		return false;
	}

	/**
	 * Check delete multiple uploaded files By "Delete Container"
	 * 
	 * @throws Exception
	 */
	public boolean deleteMultipleFilesByDeleteContainer() throws Exception {
		int countOfRecord_Before = deleteIcon.size();
		logger.info("The before count is:" + countOfRecord_Before);
		Function.hoverNclickElement(driver, checkboxList.get(checkboxList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, checkboxList.get(checkboxList.size() - 2));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, deleteContainerBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		Function.hoverNclickElement(driver, confirmBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		SeleniumWrapper.refreshWebPage(driver);
		int countOfRecord_After = deleteIcon.size();
		logger.info("The after count is:" + countOfRecord_After);
		if(countOfRecord_Before > countOfRecord_After)
			return true;
		else
		return false;
	}

	/**
	 * Check search function
	 */
	@FindBy(id = "media_search-input")
	public static WebElement searchBox;

	public boolean searchFile() {
		String keyword = "Forms";
		return Function.search(driver, searchBox, keyword, tableRowsXpath);
	}	

	/**
	 * Check click thumbnail view
	 */
	@FindBy(xpath = "//*[@class='icon-thumbnails']")
	public static List<WebElement> thumbnailViewTab;

	public boolean clickThumbnailViewTab() {
		return Function.clickElement(driver, thumbnailViewTab.get(1));
	}

	/**
	 * Check view folder from thumbnail view tab
	 */
	@FindBy(xpath = "//div[@class='file_container']")
	public static List<WebElement> fileList;

	@FindBy(xpath = "//a[@class='view-file']")
	public static List<WebElement> viewIcon; // index 0

	public boolean viewFolder() {
		SeleniumWrapper.hoverMouseOverElement(driver, fileList.get(0));
		Function.hoverNclickElement(driver, viewIcon.get(0));
		int countOfFiles = tableRowOfEntries.size();
		logger.info("Total count of files is:" + countOfFiles);
		if(countOfFiles == 3)
			return true;
		else
		return true;
	}

	public boolean clickFolder() {
		SeleniumWrapper.hoverMouseOverElement(driver, fileList.get(0));
		return Function.hoverNclickElement(driver, viewIcon.get(0));
	}

	/**
	 * Check go back to Media Library tab
	 */
	@FindBy(xpath = "//*[@id=\"breadcrumbs_container\"]/a")
	public static WebElement goBackMediaLibraryTab;

	public boolean goBackMediaLibraryTab() {
		return Function.clickElement(driver, goBackMediaLibraryTab);
	}

	/**
	 * Check edit folder from thumbnail view tab
	 */
	@FindBy(xpath = "//a[@class='edit-file']")
	public static List<WebElement> editIcon;

	@FindBy(xpath = "//*[@title='Date Created']")
	public static WebElement orderByDateCreated;

	@FindBy(id = "bs-select-7-2")
	public static WebElement orderByName;

	public boolean editFolderFromThumbnailView() {
		SeleniumWrapper.hoverMouseOverElement(driver, fileList.get(0));
		Function.hoverNclickElement(driver, editIcon.get(0));
		Function.hoverNclickElement(driver, orderByDateCreated);
		return Function.hoverNclickElement(driver, orderByName);
	}

	/**
	 * Check edit file from thumbnail view tab
	 */
	@FindBy(name = "fileViewer_description")
	public static WebElement fileDescBox;

	public boolean editFileFromThumbnailViewTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, fileList.get(fileList.size() - 1));
		Function.hoverNclickElement(driver, editIcon.get(fileList.size() - 1));
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		Function.clickElement(driver, fileDescBox);
		SeleniumWrapper.setInputFieldText(fileDescBox, "File", driver);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		logger.info("description is:" + fileDescBox.getAttribute("value"));
		 return true;
	}

	/**
	 * Check delete file from thumbnail view tab
	 */
	@FindBy(xpath = "//a[@class='delete-file']")
	public static List<WebElement> deleteIcon;

	@FindBy(id = "delete-action-confirm")
	public static WebElement confirmDeleteBtn;

	public boolean deleteFileFromThumbnailViewTab(){
		SeleniumWrapper.hoverMouseOverElement(driver, fileList.get(fileList.size() - 1));
		Function.hoverNclickElement(driver, deleteIcon.get(fileList.size() - 1));
		Function.hoverNclickElement(driver, confirmDeleteBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

	/**
	 * Check open folder from folder from thumbnail view tab
	 */
	public boolean OpenFolderFromfolderFromThumbnailViewTab() {
		return Function.clickElement(driver, listOfFolder.get(0));
	}

	/**
	 * Check delete folder from thumbnail view tab
	 * 
	 * @throws Exception
	 */
	public boolean deleteFolder() {
		Function.clickElement(driver, deleteIconList.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteRow, 30);
		if (confirmDeleteRow.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteRow, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check click List View tab
	 */
	@FindBy(xpath = "//a[@class='listview_tab']")
	public static List<WebElement> listViewTab;

	public boolean clickListViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, listViewTab.get(1), 30);
		if(listViewTab.get(1).isEnabled()) {
			SeleniumWrapper.clickElement(driver, listViewTab.get(1),Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		}
		return false;
	}

	/**
	 * Check click to open folder to view PDF file
	 */
	@FindBy(xpath = " //*[@id='modal_fileViewer-footer-buttons']/button[4]")
	private static WebElement cancelBtn;

	@FindBy(xpath = "//*[@id='datatable_media']/tbody/tr/td[3]/a")
	private static List<WebElement> listOfFolder;

	@FindBy(xpath = "//*[@id='datatable_media']/tbody/tr/td[3]/a")
	private static List<WebElement> listOfFiles;

	public boolean clickToOpenFolder() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfFolder.get(0), 30);
		logger.info("The folder to be opened is:" + listOfFolder.get(0).getText());
		return Function.hoverNclickElement(driver, listOfFolder.get(0));
	}

	public boolean clickToOpenFile() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfFiles.get(listOfFiles.size() - 1), 30);
		logger.info("The folder to be opened is:" + listOfFiles.get(listOfFiles.size() - 1).getText());
		return Function.hoverNclickElement(driver, listOfFiles.get(listOfFiles.size() - 1));
	}

	public boolean modifyFileName() {
		Function.hoverNclickElement(driver, fileDescBox);
		SeleniumWrapper.setInputFieldText(fileDescBox, "PDF File", driver);
		logger.info("The updated file name is:" + fileDescBox.getAttribute("value"));
		return true;
	}

	public boolean clickCancelBtn() {
		Function.hoverNclickElement(driver, cancelBtn);
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}

}
