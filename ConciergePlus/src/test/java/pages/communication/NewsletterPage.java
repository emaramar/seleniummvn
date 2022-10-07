package pages.communication;

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

public class NewsletterPage extends BasePage {
	protected final static Logger logger = LogManager.getLogger(NewsletterPage.class.getName());

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public NewsletterPage(WebDriver driver, String URL, String userName, String password) {
		super(driver, URL, userName, password);
		logger.info("Newsletter page is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		super.navigateTo();
		return !super.gotoNewsletterPage().equals(null);
	}

	@Override
	public boolean isLoaded() {
		if (createNewsletterBtn.isEnabled()) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check create new newsletter
	 */
	@FindBy(xpath = "//*[@id='newsletter-overview-add']/span[2]")
	public static WebElement createNewsletterBtn;	
	
	@FindBy(xpath = "//*[contains(text(),'Add group')]")
	public static List<WebElement> addGroupBtn;

	@FindBy(xpath = "//div[@class='bs-searchbox']/input")
	public static List<WebElement> searchBoxOfAddGroup; // get index 2

	@FindBy(xpath = "//*[contains(text(), 'Everyone')]")
	public static List<WebElement> choiceOfGroup_Everyone; // get index 5

	@FindBy(id = "period")
	public static WebElement titleInputBox;
	
	@FindBy(xpath = "//*[@class='fr-wrapper']")
	public static WebElement msgBox1;
	
	@FindBy(xpath = "//*[@class='fr-wrapper show-placeholder']")
	public static WebElement msgBox2;
	
	@FindBy(xpath = "//*[@class='fr-element fr-view']")
	public static WebElement ContentEditable;
	
	@FindBy(xpath = "//*[contains(text(), 'SELECT FROM MEDIA LIBRARY')]")
	public static WebElement selectFromMediaLibraryBtn;

	@FindBy(xpath = "//*[contains(text(),'Test Folder')]")
	public static WebElement selectFolder;

	@FindBy(xpath = "//*[@class ='list']/li/div")
	public static List<WebElement> listOfImages;
	
	@FindBy(xpath = "//*[contains(text(),'Save and Preview')]")
	public static WebElement saveNPreviewBtn;
	
	@FindBy(xpath = "//*[contains(text(), 'Cancel')]")
	public static WebElement cancelBtn;

	@FindBy(xpath = "//div[@id='error_container']/span[2]")
	public static List<WebElement> errorMsg;

	@FindBy(xpath = "//*[@class='summary_title']")
	public static List<WebElement> previewTitleOfNewsletter;

	public boolean clickCreateNewsletterBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, createNewsletterBtn, 20);
		if (createNewsletterBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, createNewsletterBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	
	public boolean clickAddGroupBtn() {
		return Function.clickElement(driver, addGroupBtn.get(1));
	}

	public boolean inputSearchGroupBox() {
		Function.clickElement(driver, searchBoxOfAddGroup.get(2));
		return SeleniumWrapper.setInputFieldText(searchBoxOfAddGroup.get(2), "Everyone", driver);

	}

	public boolean chooseGroup() {
		return Function.hoverNclickElement(driver, choiceOfGroup_Everyone.get(5));
	}

	String title = "Stay safe during COVID-19";
	public boolean enterTitle1() {
		if (Function.clickElement(driver, titleInputBox)) {
			SeleniumWrapper.setInputFieldText(titleInputBox, title, driver);
			logger.info("Title:" + titleInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}
	
	
	
	String title2 = "Install more lights on hallway";
	public boolean enterTitle2() {
		if (Function.clickElement(driver, titleInputBox)) {
			SeleniumWrapper.setInputFieldText(titleInputBox, title2, driver);
			logger.info("Title:" + titleInputBox.getAttribute("value"));
			return true;
		}
		return false;
	}

	public boolean clickTextBox1() {
		Function.hoverNclickElement(driver, msgBox1);
		return Function.hoverNclickElement(driver, ContentEditable);
	}
	
	public boolean clickTextBox2() {
		Function.hoverNclickElement(driver, msgBox2);
		return Function.hoverNclickElement(driver, ContentEditable);
	}

	
	String message = "newsletter";
	public boolean enterMessage() {
		SeleniumWrapper.setInputFieldText(ContentEditable, message, driver);
		logger.info("message is: " + ContentEditable.getText());
		return true;
	}
	

	public boolean clickSelectImageFromMediaLibraryBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, selectFromMediaLibraryBtn, 20);
		if(selectFromMediaLibraryBtn.isEnabled())
		return Function.clickElement(driver, selectFromMediaLibraryBtn);
		return false;
	}
	
	public boolean selectImage() {
		Function.hoverNclickElement(driver, selectFolder);
		Function.hoverNclickElement(driver, listOfImages.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	
	}

	public boolean clickSaveNPreviewBtn() {
		//SeleniumWrapper.scrollToElement(driver, saveNPreviewBtn);
		SeleniumWrapper.explicitWaitClickable(driver, saveNPreviewBtn, 20);
		if (saveNPreviewBtn.isDisplayed() && saveNPreviewBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveNPreviewBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}

	public boolean clickCancelBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, cancelBtn, 20);
		if (cancelBtn.isDisplayed() && cancelBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, cancelBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}

	public boolean isErrorMsgPresent() {
		SeleniumWrapper.explicitWaitClickable(driver, errorMsg.get(0), 20);
		String errorMessage = errorMsg.get(0).getText();
		logger.info("The error message is :" + errorMessage);
		if (errorMsg != null) {
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
		if (confirmMsg.isDisplayed()&& !message.isEmpty())
			return true;
		return false;
	}
	
	/**
	 * Check is new created newsletter present on preview page
	 */
	public boolean isPresentOnPreviewPage() {
		SeleniumWrapper.explicitWaitClickable(driver, previewTitleOfNewsletter.get(0), 30);
		String previewTitle = previewTitleOfNewsletter.get(0).getText();
		logger.info("The preview title is :" + previewTitle);
		if (previewTitle.equalsIgnoreCase(title))
			return true;
		return false;
	}
	
	
	/**
	 * Check view newsletter from View Tab
	 */
	@FindBy(xpath = "//div[contains(@class,'match')]/a/span[1]")
	public static List<WebElement> listOfNewsletterFromViewTab;
	
	@FindBy(xpath = "//*[@class='summary_body tinymce_tg']")
	public static  WebElement content;
	
	public boolean clickToOpenNewsletter() {
		SeleniumWrapper.explicitWaitClickable(driver, listOfNewsletterFromViewTab.get(0), 30);
		String titleOfRecord = listOfNewsletterFromViewTab.get(0).getText();
		logger.info("newsletter title is:" + titleOfRecord);
		return SeleniumWrapper.clickElement(driver, listOfNewsletterFromViewTab.get(0), Constants.CLICK_METHOD_ENUM.CLICK);
	}
	
	public boolean viewNewsletterInfo() {
		SeleniumWrapper.explicitWaitClickable(driver, previewTitleOfNewsletter.get(0), 30);
		String previewTitle = previewTitleOfNewsletter.get(0).getText();
		String previewContent = content.getText();
		logger.info("preview title:" + previewTitle);
		logger.info("preview content:" + previewContent);
		if(!previewTitle.isEmpty() &&(! previewContent.isEmpty()))
			return true;
		return false;
	}
	
	/**
	 * Check edit newsletter from View Tab
	 */
	@FindBy(id = "edit-admin-button")
	public static WebElement editIcon;
	
	@FindBy(xpath = "//*[contains(text(), 'Save')]")
	public static WebElement saveBtn;
	
	public boolean clickEditIcon() {
		SeleniumWrapper.explicitWaitClickable(driver, editIcon, 30);
		if(editIcon.isEnabled())
			return SeleniumWrapper.clickElement(driver, editIcon, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	
	public boolean editMessage() {
		String originMsg = msgBox1.getText();
		logger.info("original message:" + originMsg);
		String modifyMsg = "edit it";
		SeleniumWrapper.setInputFieldText(ContentEditable, modifyMsg, driver);
		logger.info("modify message:" +  msgBox1.getText());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	
	public boolean clickSaveBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, saveBtn, 20);
		SeleniumWrapper.scrollToElement(driver, saveBtn);
		if (saveBtn.isDisplayed() && saveBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, saveBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check delete newsletter from View Tab
	 */
	@FindBy(id = "delete-admin-button")
	public static WebElement deleteIcon;
	
	@FindBy(id = "delete-action-confirm")
	public static WebElement confirmBtn;
	
	public boolean clickDeleteIconFromViewTab() {
		SeleniumWrapper.explicitWaitClickable(driver, deleteIcon, 20);
		if (deleteIcon.isEnabled())
			return SeleniumWrapper.clickElement(driver, deleteIcon, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	
	public boolean clickConfirmBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmBtn, 20);
		if (confirmBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check search newsletter from View Tab
	 */
	public static final String tableRowsXpath ="//*[@id='newsletter-isotope-container']/div/a";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> newslettersTableRowsByViewTab;
	
	@FindBy(name = "search-all-newsletters-input")
	public static WebElement searchBox;
	
	public boolean searchNewsletter() {
		//String keyword = "safe";
		String keyword = "light";
		return Function.search(driver, searchBox, keyword, tableRowsXpath);
	}
	
	
	/**
	 * Check "View All Newsletters" from View Tab
	 */	
	@FindBy(xpath = "//a[contains(.,'View All Newsletters')]")
	public static WebElement viewAllNewslettersBtn;

	@FindBy(xpath = "//a[contains(text(),'List')]")
	public static WebElement listTab;

	public boolean clickViewAllNewsletters() {
		SeleniumWrapper.explicitWaitClickable(driver, viewAllNewslettersBtn, 30);
		if(viewAllNewslettersBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, viewAllNewslettersBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		return true;
		}
		return false;
	}

	public boolean isAtListTab() {
		SeleniumWrapper.explicitWaitClickable(driver, listTab, 30);
		logger.info(" The text is :" + listTab.getText());
		return listTab.getText().toLowerCase().contains("list");
	}
	
	/**
	 * Check goto List tab
	 */
	public boolean gotoListTab() {
		SeleniumWrapper.explicitWaitClickable(driver, listTab, 20);
		if(listTab.isEnabled())
		return SeleniumWrapper.clickElement(driver, listTab, Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	
	
	/**
	 * Check status of new newsletter
	 */

	public boolean isStatusDraft() {
		Function.clickElement(driver, draftNewsletters.get(0));
		SeleniumWrapper.explicitWaitClickable(driver, publishBtn, 20);
		SeleniumWrapper.explicitWaitClickable(driver, publishNSendEmailBtn, 20);
		if(publishBtn.isDisplayed() && publishNSendEmailBtn.isDisplayed())
			return true;
		return false;
	}
	
	
	/**
	 * Check publish draft newsletter
	 */
	@FindBy(xpath = "//*[contains(text(),'Draft')]")
	public static List<WebElement> draftNewsletters;
	
	@FindBy(xpath = "//*[@id='publish-button']/span[2]")
	public static WebElement publishBtn;
	
	public boolean openDraftNewsletter() {
		Function.clickElement(driver, draftNewsletters.get(0));
		SeleniumWrapper.waitForDomToBeRendered(driver);
		return true;
	}
	
	
	public boolean publicDraftNewsletter() {
		SeleniumWrapper.explicitWaitClickable(driver, publishBtn, 20);
		if (publishBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, publishBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	
	/**
	 * Check send newsletter by email
	 */
	@FindBy(xpath = "//*[@id='email-admin-button']/span[2]")
	public static WebElement sendNewsletterByEmailBtn;
	
	@FindBy(id = "confirm-semd-newsletter-by-email")
	public static WebElement sendBtn;
	
	public boolean clickSendNewsletterByEmail() {
		SeleniumWrapper.explicitWaitClickable(driver, sendNewsletterByEmailBtn, 20);
		if (sendNewsletterByEmailBtn.isDisplayed() && sendNewsletterByEmailBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, sendNewsletterByEmailBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	
	
	public boolean clickSendBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, sendBtn, 20);
		if (sendBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, sendBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	
	/**
	 * Check Publish & Send Email draft newsletter
	 */
	@FindBy(xpath = "//*[@id='publish-button-send']/span[2]")
	public static WebElement publishNSendEmailBtn;
	
	@FindBy(xpath = "//*[@id='publish-button-send']/span[2]")
	public static WebElement confirmPublishBtn;
	
	
	public boolean publicNSendEmailDraftNewsletter() {
		SeleniumWrapper.explicitWaitClickable(driver, publishNSendEmailBtn, 20);
		if (publishNSendEmailBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, publishNSendEmailBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check open newsletter by clicking newsletter title
	 */	
	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[2]/a")
	public static List<WebElement> columnPublished;
	

	public boolean openNewsletterByClickingTitle() {
		return Function.clickElement(driver, columnPublished.get(0));
		
	}
	
	/**
	 * Check  edit "Published" newsletter from List tab
	 */	
	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[5]/a")
	public static List<WebElement> editBtn;
	
	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[3]/a")
	public static List<WebElement> statusOfNewsletters;
	
	@FindBy(xpath = "//a[contains(text(),'Published')]")
	public static List<WebElement> publishedNewsletters;
	
	public WebElement editBtnOfPublishedNewsletter() {
		for(int i =0; i <statusOfNewsletters.size(); i++) 
			if (statusOfNewsletters.get(i).getText().contains("Published"))
			return editBtn.get(i);
		return null;
	}
	
	public boolean clickEditBtnOfPublishedNewsletterFromListTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, editBtnOfPublishedNewsletter());
		SeleniumWrapper.waitForDomToBeRendered(driver);
		if(editBtnOfPublishedNewsletter().isEnabled())
		return SeleniumWrapper.clickElement(driver, editBtnOfPublishedNewsletter(), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	
	
	/**
	 * Check  edit "Draft" newsletter from List tab
	 */
	@FindBy(xpath = "//*[@id='save-ann-button']/span[2]")
	public static WebElement publishDraftBtn;
	
	public WebElement editBtnOfDraftNewsletter() {
		for(int i =0; i < statusOfNewsletters.size(); i++) 
			if (statusOfNewsletters.get(i).getText().contains("Draft"))
			return editBtn.get(i);
		return null;
	}
	
	public boolean clickEditBtnOfDraftNewsletterFromListTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, editBtnOfDraftNewsletter());
		if(editBtnOfDraftNewsletter().isEnabled())
		return SeleniumWrapper.clickElement(driver, editBtnOfDraftNewsletter(), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	
	public boolean clickPublishDrafBtn() {
		//SeleniumWrapper.scrollToElement(driver,  publishDraftBtn);
		SeleniumWrapper.explicitWaitClickable(driver, publishDraftBtn, 20);
		if (publishDraftBtn.isDisplayed() && publishDraftBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, publishDraftBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check delete newsletter from List tab
	 */	
	@FindBy(xpath = "//*[@id='datatable_all']/tbody/tr/td[6]/a")
	public static List<WebElement> deleteBtn;
	
	@FindBy(id = "table-action-confirm")
	public static WebElement confirmDeleteBtn;
	

	public boolean clickDeleteBtnFromListTab() {
		SeleniumWrapper.hoverMouseOverElement(driver, deleteBtn.get(deleteBtn.size()-1));
		if(deleteBtn.get(deleteBtn.size()-1).isEnabled())
		return SeleniumWrapper.clickElement(driver, deleteBtn.get(deleteBtn.size()-1), Constants.CLICK_METHOD_ENUM.CLICK);
		return false;
	}
	
	public boolean clickConfirmDeleteBtn() {
		SeleniumWrapper.explicitWaitClickable(driver, confirmDeleteBtn, 20);
		if (confirmDeleteBtn.isEnabled()) {
			SeleniumWrapper.clickElement(driver, confirmDeleteBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check search newsletter from List tab
	 */	
	public static final String tableRowsXpath_ListTab ="//*[@id='datatable_all']/tbody/tr";
	@FindBy(xpath = tableRowsXpath)
	public static List<WebElement> newslettersTableRowsByListTab;
	
	@FindBy(id = "datatable_all_search")
	public static WebElement box;
	
	@FindBy(id = "datatable_all_search-input")
	public static WebElement searchBox_ListTab;
	
	public boolean searchNewsletterFromListTab() {
		String searchKeyword = "COVID-19";
		return Function.search(driver, searchBox_ListTab, searchKeyword, tableRowsXpath_ListTab);
	}
	
}
