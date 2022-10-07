package tests.resources;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.templates.GuiTestCase;
import com.utilities.SeleniumWrapper;
import pages.BasePage;
import pages.resources.PetRegistryPage;

public class PetRegistryPageTest extends GuiTestCase {
	protected final static Logger logger = LogManager.getLogger(PetRegistryPageTest.class.getName());
	protected BasePage basePage = null;
	protected PetRegistryPage petRegistryPage = null;

	/**
	 * Test of navigating to Pet Registry Page
	 */
	@Test(priority = 1)
	public void gotoPetRegistryPage() {
		test = extent.startTest("Navigate to Pet Registry Page");
		basePage = new BasePage(driver, GuiTestCase.URL, GuiTestCase.userName, GuiTestCase.password);
		Assert.assertTrue(basePage.navigateTo());
		petRegistryPage = basePage.gotoPetRegistryPage();
		Assert.assertNotNull(petRegistryPage);
		Assert.assertTrue(petRegistryPage.isLoaded());
	}

	/**
	 * Test of verify is "Unit" field mandatory
	 */
	@Test(priority = 3)
	public void isUnitFieldMandatory() {
		test = extent.startTest("Verify is 'Unit' field mandatory");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.enterPetName())
				if (petRegistryPage.clickSavePetBtn())
					Assert.assertTrue(petRegistryPage.isErrorMsgPresent());
						if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of verify is "Pet Name" field mandatory
	 */
	@Test(priority = 5)
	public void isPetNameFieldMandatory() {
		test = extent.startTest("Verify is 'Pet Name' field mandatory");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.enterUnit())
				if (petRegistryPage.clickSavePetBtn())
					Assert.assertTrue(petRegistryPage.isErrorMsgPresent());
						if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of enter unit
	 */
	@Test(priority = 7)
	public void enterUnit() {
		test = extent.startTest("Verify enter unit");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.enterUnit());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of enter pet name
	 */
	@Test(priority = 9)
	public void enterPetName() {
		test = extent.startTest("Verify enter pet name");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.enterPetName());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of select category
	 */
	@Test(priority = 11)
	public void selectCategory() {
		test = extent.startTest("Verify select category");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.selectCategory())
				Assert.assertTrue(petRegistryPage.isCategorySelected());
					if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of upload pet photo
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void UploadPetPhoto() throws Exception {
		test = extent.startTest("Verify upload pet photo");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.uploadPetPhoto())
				Assert.assertTrue(petRegistryPage.isPetPhotoUploaded());
					if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of upload file from media library
	 */
	@Test(priority = 15)
	public void UploadFileFromMediaLibrary() {
		test = extent.startTest("Verify upload file from media library");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.clickSelectImageFromMediaLibraryBtn())
				if (petRegistryPage.selectImage())
					Assert.assertTrue(petRegistryPage.isImageUploaded());
						if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of enter breed
	 */
	@Test(priority = 17)
	public void enterBreed() {
		test = extent.startTest("Verify enter breed");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.enterBreed());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of enter color/feature
	 */
	@Test(priority = 19)
	public void enterColorAndFeature() {
		test = extent.startTest("Verify enter color/feature");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.enterColorAndFeature());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of set temperature
	 */
	@Test(priority = 21)
	public void setTemperature() {
		test = extent.startTest("Verify set temperature");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.setTemperature())
				Assert.assertTrue(petRegistryPage.isTemperatureSet());
					if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of set Pet’s birthday
	 */
	@Test(priority = 23)
	public void setPetBirthday() {
		test = extent.startTest("Verify set Pet’s birthday");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.setPetBirthday());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of enter Pet's Bio
	 */
	@Test(priority = 25)
	public void setPetsBio() {
		test = extent.startTest("Verify enter Pet's Bio");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.enterPetBio());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of enter Pet's food
	 */
	@Test(priority = 27)
	public void setPetsFood() {
		test = extent.startTest("Verify enter Pet's food");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.enterPetFood());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of enable option of "Do not show my pet on the registry"
	 */
	@Test(priority = 29)
	public void enableOptionOfDoNotShowMyPetOnTheRegistry() {
		test = extent.startTest("Verify enable option of 'Do not show my pet on the registry' ");
		if (petRegistryPage.clickRegisterNewPetBtn())
			Assert.assertTrue(petRegistryPage.enableOptionOfDoNotShowMyPetOnTheRegistry());
				if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of PM create new pet with “Do not show my pet on the registry”
	 * 
	 * @throws Exception
	 */
	@Test(priority = 31)
	public void userPMCreateNewPetWithoutShowPetOntheRegistry() throws Exception {
		test = extent.startTest("Verify PM create new pet with 'Do not show my pet on the registry' ");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.enterUnit())
				if (petRegistryPage.enterPetName())
					if (petRegistryPage.selectCategory())
						if (petRegistryPage.uploadPetPhoto())
							if (petRegistryPage.enterBreed())
								if (petRegistryPage.enterColorAndFeature())
									if (petRegistryPage.setTemperature())
										if (petRegistryPage.setPetBirthday())
											if (petRegistryPage.enterPetBio())
												if (petRegistryPage.enterPetFood())
													if (petRegistryPage.enableOptionOfDoNotShowMyPetOnTheRegistry())
														if (petRegistryPage.clickSavePetBtn())
															Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of PM create new pet with show pet on the registry
	 */
	@Test(priority = 33)
	public void userPMCreateNewPetWithShowPetOnTheRegistry() {
		test = extent.startTest("Verify PM create new pet with show pet on the registry ");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.enterUnit())
				if (petRegistryPage.enterPetName())
					if (petRegistryPage.selectCategory())
						if (petRegistryPage.clickSelectImageFromMediaLibraryBtn())
							if (petRegistryPage.selectImage())
								if (petRegistryPage.enterBreed())
									if (petRegistryPage.enterColorAndFeature())
										if (petRegistryPage.setTemperature())
											if (petRegistryPage.clickSavePetBtn())
												Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM search pet from List View
	 */
	@Test(priority = 35)
	public void userPMSearchPet() {
		test = extent.startTest("Verify user PM search pet from List View");
		Assert.assertTrue(petRegistryPage.searchPet());
	}

	/**
	 * Test of user PM click pet name will direct to edit pet page
	 */
	@Test(priority = 37)
	public void userPMClickPetNameWillDirectToEditPetPage() {
		test = extent.startTest("Verify user PM click pet name will direct to edit pet page");
		SeleniumWrapper.refreshWebPage(driver);
		Assert.assertTrue(petRegistryPage.clickPetNameWillDirectToEditPage());
			if(petRegistryPage.clickCancelBtn());
	}

	/**
	 * Test of user PM edit pet from List View tab
	 */
	@Test(priority = 39)
	public void userPMEditPetFromListViewTab() {
		test = extent.startTest("Verify user PM edit pet from List View tab");
		if (petRegistryPage.clickEditIcon())
			if (petRegistryPage.changePetName())
				if (petRegistryPage.clickSavePetBtn())
					Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM delete pet from List View tab
	 */
	@Test(priority = 41)
	public void userPMDeletePetFromListViewTab() {
		test = extent.startTest("Verify user PM delete pet from List View tab");
		if (petRegistryPage.clickDeleteIcon())
			if (petRegistryPage.clickConfirmDeleteBtn())
				Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM edit pet from Thumbnail View tab
	 */
	@Test(priority = 43)
	public void userPMEditPetFromThumbnailViewTab() {
		test = extent.startTest("Verify user PM edit pet from Thumbnail  View tab");
		if (petRegistryPage.gotoThumbnailViewTab())
			if (petRegistryPage.hoverPetFile())
				if (petRegistryPage.clickEditBtnFromThumbnailViewTab())
					if (petRegistryPage.changePetName())
						if (petRegistryPage.clickSavePetBtn())
							Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of user PM delete pet from Thumbnail View tab
	 */
	@Test(priority = 45)
	public void userPMDeletePetFromThumbnailViewTab() {
		test = extent.startTest("Verify user PM delete pet from Thumbnail  View tab");
		SeleniumWrapper.refreshWebPage(driver);
		if (petRegistryPage.gotoThumbnailViewTab())
			if (petRegistryPage.hoverPetFile())
				if (petRegistryPage.clickDeleteBtnFromThumbnailViewTab())
					Assert.assertTrue(petRegistryPage.clickConfirmDeleteBtnFromThumbnailViewTab());
						if(petRegistryPage.gotoListViewTab());
	}

	/**
	 * Test of Resident User goto Pet Registry menu
	 */
	@Test(priority = 47)
	public void residentUserGotoPetRegistry() {
		test = extent.startTest("Verify Resident User goto Pet Registry menu");
		petRegistryPage.logOutAsCurrentUser(driver);
		petRegistryPage.logInAsResidentUser(driver);
		Assert.assertTrue(petRegistryPage.gotoPetRegistryMenu());
	}

	/**
	 * Test of Resident User create new pet
	 * 
	 * @throws Exception
	 */
	@Test(priority = 49)
	public void residentUserCreateNewPet() throws Exception {
		test = extent.startTest("Verify Resident User create new pet");
		if (petRegistryPage.clickRegisterNewPetBtn())
			if (petRegistryPage.enterPetName())
				if (petRegistryPage.selectCategory())
					if (petRegistryPage.uploadPetPhoto())
						if (petRegistryPage.enterBreed())
							if (petRegistryPage.enterColorAndFeature())
								if (petRegistryPage.setTemperature())
									if (petRegistryPage.setPetBirthday())
										if (petRegistryPage.enterPetBio())
											if (petRegistryPage.enableOptionOfDoNotShowMyPetOnTheRegistry())
												if (petRegistryPage.clickSavePetBtn())
													Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of Resident User edit pet
	 */
	@Test(priority = 51)
	public void residentUserEditPet() {
		test = extent.startTest("Verify Resident User edit pet");
		if (petRegistryPage.hoverPetFile_ResidentUser())
			if (petRegistryPage.clickEditBtn_ResidentUser())
				if (petRegistryPage.changePetName())
					if (petRegistryPage.clickSavePetBtn())
						Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of Resident User delete pet
	 */
	@Test(priority = 53)
	public void residentUserDeletePet() {
		test = extent.startTest("Verify Resident User delete pet");
		if (petRegistryPage.hoverPetFile_ResidentUser())
			if (petRegistryPage.clickDeleteBtn_ResidentUser())
				if (petRegistryPage.clickConfirmDeleteBtnFromThumbnailViewTab())
					Assert.assertTrue(petRegistryPage.isSuccessMsgPresent());
	}

	/**
	 * Test of Resident User view pets at my community
	 */
	@Test(priority = 55)
	public void residentUserViewPetAtMyCommunityTab() {
		test = extent.startTest("Verify Resident user view pets at my community");
		if (petRegistryPage.gotoPetsAtMyCommunityTab())
			Assert.assertTrue(petRegistryPage.viewPetsAtMyCommunity());
	}

}
