package com.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.utilities.TestResultValidator;
import config.Constants;

public class Function {
	protected final static Logger logger = LogManager.getLogger(Function.class.getName());
	
	
	/**
	 * Wait for elmenet clickable, then hover mouse to element and click the element
	 * @param element web element to check
	 * @return true, if web element is clickable; otherwise false
	 */
	public static boolean clickElement(WebDriver driver, WebElement element) {
		try {
			SeleniumWrapper.explicitWaitClickable(driver, element, 30);
			return SeleniumWrapper.clickElement(driver, element, Constants.CLICK_METHOD_ENUM.CLICK);
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	/**
	 * Wait for elmenet clickable, then hover mouse to element and click the element
	 * @param element web element to check
	 * @return true, if web element is clickable; otherwise false
	 */
	public static boolean hoverNclickElement(WebDriver driver, WebElement element) {
		try {
			SeleniumWrapper.explicitWaitClickable(driver, element, 30);
			SeleniumWrapper.hoverMouseOverElement(driver, element);
			return SeleniumWrapper.clickElement(driver, element, Constants.CLICK_METHOD_ENUM.CLICK);
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	
	
	/**
	 * Check is user logged in application successfully
	 * @param String name is logged in user's name
	 * @return true if user name is equals to actual logged in user's display name, else return false;
	 */
	@FindBy(xpath = "//*[@class='display_name']")
	public static WebElement currentLoginUser;
	
	public static boolean isUserLoggedIn(WebDriver driver, String name) {
		WebElement currentLoginUser = driver.findElement(By.xpath("//*[@class='display_name']"));
		try {
			String user = currentLoginUser.getText();
			logger.info("Current logged in user is :" + user );
			if(user.equalsIgnoreCase(name))
			return true;
			else
				return false;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	
	
	/**
	 * Get column  from the table row record
	 * @param element is the table row record with specific condition 
	 * @return the column text , otherwise false
	 */
	public static boolean getColumn(WebDriver driver, WebElement element,int num) {
		try {
		SeleniumWrapper.explicitWaitClickable(driver, element, 20);
			List<WebElement> columns = element.findElements(By.tagName("td"));

			String cell = columns.get(num).getText();
			logger.info("The cell is:" + cell);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
	}
	}
	
	
	/**
	 * Search function
	 * 
	 * @param WebElement searchBox 
	 * @param String search keyword 
	 * @param String xpath
	 * @return true, if return search result; otherwise false
	 */
	public static boolean search(WebDriver driver, WebElement searchBox, String keyword, String xpath) {
		try {
			Function.clickElement(driver, searchBox);
			SeleniumWrapper.setInputFieldText(searchBox, keyword, driver);
			Thread.sleep(80000);
			return TestResultValidator.isSearchedFromElementsList(xpath, driver, keyword);
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	
	/**
	 * Switch to childWindow
	 * @param element parentWindow
	 * @return true, if switch to childWindow; otherwise false
	 */
	public static boolean switchToNewWindow (WebDriver driver, String parentWindow) {
		try {
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
			String childWindow = iterator.next();
				if (!parentWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
					logger.info("Child window is:" + childWindow);
				}
			}
			return true;	
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	
	/**
	 * Close child window and switch to parent window
	 * @param element web element to check
	 * @return true, if close current window and switch to parent window; otherwise false
	 */
	public static boolean closeNSwitchWindow(WebDriver driver, String window) {
		try {
			driver.close();
			driver.switchTo().window(window);
			return true;	
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	
	/**
	 * Verify is logo image display 
	 * @param element web element to check
	 * @return true, if close popup; otherwise false
	 */
	public static boolean isLogoImageDisplayed(WebDriver driver, WebElement element1, WebElement element2) {
		try {
			SeleniumWrapper.explicitWaitClickable(driver, element1, 20);
			SeleniumWrapper.hoverMouseOverElement(driver, element1);
			SeleniumWrapper.clickElement(driver, element1, Constants.CLICK_METHOD_ENUM.CLICK);
			if (SeleniumWrapper.clickElement(driver, element1, Constants.CLICK_METHOD_ENUM.CLICK)) {
				Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
						"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
						element2);
				if (!ImagePresent) {
					logger.info("Image is not displayed");
				} else {
					logger.info("Image is displayed");
				}
			}
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	/**
	 * Check set date from Calendar by enter the date/month/year value
	 * @param int num , setDayIndex = today date index on calendar + number of date to be added on
	 * @param WebElement dateInputBox is used to enter the new date
	 * @return
	 */
	public static boolean setDateByEnter(WebDriver driver, int num, WebElement dateInputBox) {
		try {
			String pattern = "MM/dd/yyyy";
			DateFormat df = new SimpleDateFormat(pattern);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			//int dayOfMth = cal.get(Calendar.DAY_OF_MONTH);
			Date today = Calendar.getInstance().getTime();
			//String todayAsString = df.format(today);
			cal.setTime(today);
			cal.add(Calendar.DATE, num);
			Date nextDay = cal.getTime();
			String nextDayAsString = df.format(nextDay);
			 logger.info(nextDayAsString);
			 
			 SeleniumWrapper.explicitWaitClickable(driver, dateInputBox, 20);
			 SeleniumWrapper.clickElement(driver, dateInputBox, Constants.CLICK_METHOD_ENUM.CLICK);
			 return SeleniumWrapper.setInputFieldText(dateInputBox, nextDayAsString, driver);
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
		}
		return false;	
	}
	

	
	/**
	 * Check set date from Calendar by click calendar icon
	 * 
	 * @param int num , setDayIndex = today date index on calendar + number of date
	 *            to be added on
	 * @return apply to Announcements , Amenity Booking and Units module
	 */
	public static boolean setDatefromCalendar(WebDriver driver, int num) {
		try {
			List<WebElement> datepicker = driver.findElements(By.xpath("//*[@class='datepicker-days']/table/tbody/tr/td"));
			List<Integer> matchDate = new ArrayList<Integer>();

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int day = cal.get(Calendar.DAY_OF_MONTH);
			String todayDay = Integer.toString(day);
			logger.info("Today is:" + day);

			for (int i = 0; i < datepicker.size(); i++) {
				if (datepicker.get(i).getText().equals(todayDay)) {   
					matchDate.add(i);    // since calendar has two todayDay, represent different index,  add the two index in the list

				}
			}
			int setDayIndex = matchDate.get(0) + num;   //default to use the first index
			if (matchDate.size() > 1 && day > 15) {     // condition to verify when to use the second index
				setDayIndex = matchDate.get(1) + num;
			}
			logger.info("New index is:" + setDayIndex);

			Function.hoverNclickElement(driver, datepicker.get(setDayIndex));
			SeleniumWrapper.waitForDomToBeRendered(driver);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
	

	/**
	 * Set time by dropdown list from Calendar 
	 */

	public static boolean setTimeFromdropdown(WebDriver driver, WebElement timeSlotsBox, WebElement choiceFromDropdown) {
		try {		
		SeleniumWrapper.explicitWaitClickable(driver, timeSlotsBox, 20);
		SeleniumWrapper.clickElement(driver, timeSlotsBox, Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.hoverMouseOverElement(driver,choiceFromDropdown);
		SeleniumWrapper.clickElement(driver, choiceFromDropdown, Constants.CLICK_METHOD_ENUM.CLICK);
		return true;
		}
		catch(Exception e) {
			logger.warn("Exception is: ", e);
		}
		return false;
	}
	
	
	
	/**
	 * Set time by input from Calendar
	 */
	public static boolean setTimeFromInput(WebDriver driver, WebElement timeInputBox, String time) {
		try {
			SeleniumWrapper.explicitWaitClickable(driver, timeInputBox, 20);
			SeleniumWrapper.clickElement(driver,timeInputBox, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.setInputFieldText(timeInputBox, time, driver);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
		}
		return false;
	}
	
	
	/**
	 * Enter unit Or Name function
	 * @param element searchUnitBox, for enter unit or name on searchUnitBox 
	 * @param element selectChoice, for select name or unit from dropdown list 
	 * @return true, if name or unit has been selected; otherwise false
	 */
	public static boolean enterNameOrUnit(WebDriver driver, WebElement inputBox, String unit, WebElement selectChoice) {
		try {
			SeleniumWrapper.explicitWaitClickable(driver, inputBox, 30);
			SeleniumWrapper.clickElement(driver, inputBox, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.setInputFieldText(inputBox, unit, driver);
			SeleniumWrapper.explicitWaitClickable(driver, selectChoice, 30);
			SeleniumWrapper.clickElement(driver, selectChoice, Constants.CLICK_METHOD_ENUM.CLICK);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}
	
		
	
	/**
	 * Log in application as resident user
	 * @param usernameBox, to enter username
	 * @param passwordBox, to enter password
	 * @param loginBtn
	 */
	public static boolean logInAsUser(WebDriver driver, String user, String password, String name) {
		WebElement usernameBox = driver.findElement(By.xpath(".//*[@id='username']"));
		WebElement passwordBox = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		WebElement currentLoginUser = driver.findElement(By.xpath("//*[@class='display_name']"));
		try {
		SeleniumWrapper.explicitWaitClickable(driver, usernameBox, 20);
		SeleniumWrapper.setInputFieldText(usernameBox, user, driver);

		SeleniumWrapper.explicitWaitClickable(driver, passwordBox, 20);
		SeleniumWrapper.setInputFieldText(passwordBox, password, driver);

		SeleniumWrapper.explicitWaitClickable(driver, loginBtn, 20);
		SeleniumWrapper.clickElement(driver, loginBtn, Constants.CLICK_METHOD_ENUM.CLICK);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		
		SeleniumWrapper.explicitWaitClickable(driver, currentLoginUser, 20);
		if(currentLoginUser.getText().equalsIgnoreCase(name))
			return true;
		else
			return false;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
	}
	}	
	
	/**
	 * Log in application as resident user
	 * 
	 * @param usernameBox, to enter username
	 * @param passwordBox, to enter password
	 * @param loginBtn
	 */
	public static boolean logInAsNewUser(WebDriver driver, String user, String password) {
		WebElement usernameBox = driver.findElement(By.xpath(".//*[@id='username']"));
		WebElement passwordBox = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		try {
			SeleniumWrapper.explicitWaitClickable(driver, usernameBox, 30);
			SeleniumWrapper.setInputFieldText(usernameBox, user, driver);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);

			SeleniumWrapper.explicitWaitClickable(driver, passwordBox, 30);
			SeleniumWrapper.setInputFieldText(passwordBox, password, driver);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);

			SeleniumWrapper.explicitWaitClickable(driver, loginBtn, 30);
			SeleniumWrapper.clickElement(driver, loginBtn, Constants.CLICK_METHOD_ENUM.CLICK);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}

	/**
	 * Click upload file button and Upload file and image
	 * WebElement elemet is the upload file button
	 * String filepath is the image and file path
	 */
	public static boolean uploadFile(WebDriver driver, WebElement uploadBtn, String filePath)throws Exception {
		try {
				Function.clickElement(driver, uploadBtn);
				File file = new File(filePath);
				Runtime.getRuntime().exec(file.getAbsolutePath());
				Thread.sleep(20000);
		return true;
	} catch (Exception e) {
		logger.warn("Exception is: ", e);
		return false;
	}	
		}
	
	
	/**
	 * Click upload file button and Upload file and image
	 * WebElement elemet is the upload file button
	 * String filepath is the image and file path
	 */
	public static boolean uploadFile_NoWaiting(WebDriver driver, WebElement uploadBtn, String filePath)throws Exception {
		try {
				Function.clickElement(driver, uploadBtn);
				File file = new File(filePath);
				Runtime.getRuntime().exec(file.getAbsolutePath());
				Thread.sleep(5000);
		return true;
	} catch (Exception e) {
		logger.warn("Exception is: ", e);
		return false;
	}	
		}
	
	/**
	 * Check delete record from web table
	 * List WebElement deleteIcon
	 * WebElement confirmBtn
	 * (this method is not work when the records on web table more than 1 page, currently apply this method on Deliveries module, Entry Instruction 
	 * Incident Reports, Media Library ,Parking, Residents Guide)
	 */
	public static boolean deleteRecord(WebDriver driver, List<WebElement> deleteIcon, int index, WebElement conifirmBtn) {
		try {
			int countOfRecord_Before = deleteIcon.size();
			logger.info("The before count is:" + countOfRecord_Before);
			Function.hoverNclickElement(driver, deleteIcon.get(index));
			Function.hoverNclickElement(driver, conifirmBtn);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		    //SeleniumWrapper.refreshWebPage(driver);
			int countOfRecord_After = deleteIcon.size();
			logger.info("The after count is:" + countOfRecord_After);
			if (countOfRecord_Before > countOfRecord_After) {
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}	
	
	
	
/**
 * Check delete specific record from web table
 * List WebElement deleteIcon
 * WebElement confirmBtn
 * (this method is not work when the records on web table more than 1 page, currently apply this method on Deliveries module, Entry Instruction 
 * Incident Reports, Media Library ,Parking, Residents Guide)
 */
public static boolean deleteSpecificRecord(WebDriver driver, List<WebElement> deleteIcon, WebElement record, WebElement conifirmBtn) {
	try {
		int countOfRecord_Before = deleteIcon.size();
		logger.info("The before count is:" + countOfRecord_Before);
		Function.hoverNclickElement(driver, record);
		Function.hoverNclickElement(driver, conifirmBtn);
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		int countOfRecord_After = deleteIcon.size();
		logger.info("The after count is:" + countOfRecord_After);
		if (countOfRecord_Before > countOfRecord_After) {
			return true;
		}
		return false;
	} catch (Exception e) {
		logger.warn("Exception is: ", e);
		return false;
	}
}	

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

