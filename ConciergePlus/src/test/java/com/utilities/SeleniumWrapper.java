package com.utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;
import config.Constants;


public class SeleniumWrapper {
	protected final static Logger logger = LogManager.getLogger(SeleniumWrapper.class.getName());

	/**
	 * Check if a web element is displayed or not
	 * 
	 * @param element web element to check
	 * @return true, if web element is displayed; otherwise false
	 */
	public static boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}

	/**
	 * Implicitly wait
	 * 
	 * @param driver web browser driver
	 */
	public static void implicitWait_Constant(WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(Constants.EXPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
		}
	}
	
	
	/**
	 * Thread sleep
	 * 
	 * Do not recommend this way, be carefully on calling this method
	 * 
	 * @param driver web browser driver
	 */
	public static void threadSleep(int num) {
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			logger.warn("Exception is: ", e);
		}
	}
	

	/**
	 * Explicitly wait for a web element to be ready
	 * 
	 * @param driver   web browser driver
	 * @param element  web element to scroll to
	 * @param waitTime time to wait
	 */
	public static void explicitWaitClickable(WebDriver driver, WebElement element, int waitTime) {
		try {
			(new WebDriverWait(driver, waitTime)).until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
		}
	}

	/**
	 * Waits for DOM to be rendered
	 * 
	 * @param driver WebDriver
	 */
	public static void waitForDomToBeRendered(WebDriver driver) {
		try {
			Thread.sleep(Constants.PAGE_RENDER_TIME);  // wait for 1 seconds
		} catch (InterruptedException e) {
			logger.info("Falied to wait for DOM to be rendered");
			logger.info("Exception is: " + e);
		}
	}
	
	
	/**
	 * Waits for DOM to be rendered
	 * 
	 * @param driver WebDriver
	 */
	public static void waitForDomToBeRendered_3S(WebDriver driver) {
		try {
			Thread.sleep(Constants.PAGE_RENDER_TIME_3S);  // wait for 3 seconds
		} catch (InterruptedException e) {
			logger.info("Falied to wait for DOM to be rendered");
			logger.info("Exception is: " + e);
		}
	}
	
	
	/**
	 * Waits for DOM to be rendered
	 * 
	 * @param driver WebDriver
	 */
	public static void waitForDomToBeRendered_5S(WebDriver driver) {
		try {
			Thread.sleep(Constants.PAGE_RENDER_TIME_5S);  // wait for 5 seconds
		} catch (InterruptedException e) {
			logger.info("Falied to wait for DOM to be rendered");
			logger.info("Exception is: " + e);
		}
	}
	

	/**
	 * Waits for the progress bar to disappear ensuring the page has loaded
	 * 
	 * @param driver WebDriver
	 */
	public static void waitForPageToBeLoaded(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.PAGE_LOAD_TIME);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/svg")));
		} catch (Exception e) {
			logger.debug("Exception is: " + e);
			return;
		}
		WebDriverWait waitForInvisibility = new WebDriverWait(driver, Constants.PAGE_LOAD_TIME);
		waitForInvisibility.ignoring(org.openqa.selenium.NoSuchElementException.class);
		waitForInvisibility.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div/svg")));
	}

	/**
	 * Scroll to the location of a specific web element
	 * 
	 * @param driver  web browser driver
	 * @param element web element to scroll to
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		int yScrollPosition = element.getLocation().getY();
		try {
			executor.executeScript("window.scroll(0, " + yScrollPosition + ");");
			return true;
		} catch (Exception e) {
			try {
				new Actions(driver).moveToElement(element).perform();
				return true;
			} catch (Exception e1) {
				logger.error("Exception is: ", e1);
				return false;
			}
		}
	}

	/**
	 * Hover mouse over element
	 * 
	 * @param driver  web browser driver
	 * @param element web element to hover over
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean hoverMouseOverElement(WebDriver driver, WebElement element) {
		try {
			new Actions(driver).moveToElement(element).perform();
			return true;
		} catch (Exception e) {
			logger.warn("Exception is: ", e);
			return false;
		}
	}

	/**
	 * Click a specific web element, please call explicitly wait first
	 * 
	 * @param driver      web browser driver
	 * @param element     web element to scroll to
	 * @param clickMethod action to perform
	 * @param waitTime    time to wait
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean clickElement(WebDriver driver, WebElement element, Constants.CLICK_METHOD_ENUM clickMethod) {
		if (!(element.isDisplayed() && element.isEnabled())) {
			waitForPageToBeLoaded(driver);
		}
		switch (clickMethod) {
		case CLICK:
			element.click();
			SeleniumWrapper.waitForDomToBeRendered(driver);
			logger.info("element.click(), called.");
			return true;
		case SENDENTER:
			element.sendKeys(Keys.ENTER);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			logger.info("element.sendKeys(Keys.ENTER), called.");
			return true;
		case SENDRETURN:
			element.sendKeys(Keys.RETURN);
			SeleniumWrapper.waitForDomToBeRendered(driver);
			logger.info("element.sendKeys(Keys.RETURN), called.");
			return true;
		case SUBMIT:
			element.submit();
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			logger.info("element.submit(), called.");
			return true;
		case RUNJS:
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			logger.info("((JavascriptExecutor) driver).executeScript(\"arguments[0].click();\", element), called.");
			return true;
		default:
			return false;
		}
	}


	/**
	 * Set text of an input field
	 * 
	 * @param inputField The field to set the text for
	 * @param textToSet  The text to set
	 * @param driver     The WebDriver instance
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean setInputFieldText(WebElement inputField, String textToSet, WebDriver driver) {
		SeleniumWrapper.explicitWaitClickable(driver, inputField, Constants.EXPLICIT_WAIT_TIME);
		try {
			new Actions(driver).moveToElement(inputField).perform();
			inputField.clear();
			inputField.sendKeys(textToSet);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
			return false;
		}
	}

	/**
	 * Set text of an input field without using built-in clear() method since
	 * sometimes it does not work
	 * 
	 * @param inputField The field to set the text for
	 * @param textToSet  The text to set
	 * @param driver     The WebDriver instance
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean setInputFieldTextNoClear(WebElement inputField, String textToSet, WebDriver driver) {
		SeleniumWrapper.explicitWaitClickable(driver, inputField, Constants.EXPLICIT_WAIT_TIME);
		try {
			new Actions(driver).moveToElement(inputField).perform();
			inputField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			inputField.sendKeys(textToSet);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
			return false;
		}
	}
	
	
	
	/**
	 * Enter name on name box
	 * @param inputField, is the filed to enter name
	 * Use Java Faker to generate random name
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean enterName(WebDriver driver, WebElement inputField) {
		SeleniumWrapper.explicitWaitClickable(driver, inputField, Constants.EXPLICIT_WAIT_TIME);
		try {
			Faker faker = new Faker();
		    String name = faker.name().fullName();
		    logger.info("enter name is:" + name);	
			new Actions(driver).moveToElement(inputField).perform();
			inputField.clear();
			inputField.sendKeys(name);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
			return false;
		}
	}
	
	
	/**
	 * Enter name on name box
	 * @param inputField, is the filed to enter name
	 * Use Java Faker to generate random name
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean enterFirstName(WebDriver driver, WebElement inputField) {
		SeleniumWrapper.explicitWaitClickable(driver, inputField, Constants.EXPLICIT_WAIT_TIME);
		try {
			Faker faker = new Faker();
		    String name = faker.name().firstName();
		    logger.info("enter name is:" + name);	
			new Actions(driver).moveToElement(inputField).perform();
			inputField.clear();
			inputField.sendKeys(name);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
			return false;
		}
	}
	
	/**
	 * Enter name on name box
	 * @param inputField, is the filed to enter name
	 * Use Java Faker to generate random name
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean enterTitle(WebDriver driver, WebElement inputField) {
		SeleniumWrapper.explicitWaitClickable(driver, inputField, Constants.EXPLICIT_WAIT_TIME);
		try {
			Faker faker = new Faker();
		    String text = faker.job().title();
		    logger.info("enter name is:" + text);	
			new Actions(driver).moveToElement(inputField).perform();
			inputField.clear();
			inputField.sendKeys(text);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
			return false;
		}
	}
	
	
	/**
	 * Enter name on name box
	 * @param inputField, is the filed to enter name
	 * Use Java Faker to generate random name
	 * @return true, if everything successful; otherwise false
	 */
	public static boolean enterText(WebDriver driver, WebElement inputField) {
		SeleniumWrapper.explicitWaitClickable(driver, inputField, Constants.EXPLICIT_WAIT_TIME);
		try {
			Faker faker = new Faker();
		   String text = faker.color().name();
		    logger.info("enter text is:" + text);	
			new Actions(driver).moveToElement(inputField).perform();
			inputField.clear();
			inputField.sendKeys(text);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: " + e);
			return false;
		}
	}
	
	
	/**
	 * Refresh web page
	 * 
	 * @param driver WebDriver
	 */
	public static void refreshWebPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	
	/**
	 * another way to generate random string public static String
	 * generateRandomString(int length) { Random random = new Random(); String
	 * suffix = Long.toString(Math.abs(random.nextLong()), length); //return
	 * suffix.substring(0, length); return suffix.substring(0,length); }
	 */
	public static String generateRandomSubjectForAnnouncement() {

		List<String> list = new ArrayList<>();
		list.add("The water is shut down this Thursday");
		list.add("We collect electronic waste this Tuesday");
		list.add("Gym is out of service for maintenance");
		list.add("Repair lobby floor this Wednesday");
		list.add("Yard sales is held every Saturday");
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

	/**
	 * Generate random integer
	 */
	public static int generateRandomInteger(int modal) {
		return (int) ((new Random().nextInt(30) + 1) % modal);
	}

	
	/**
	 * Generate random string mixing with character and number
	*/
    static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";
	public static String generateRandomString(int length) {
	StringBuilder builder = new StringBuilder();
	while (length-- != 0) {
	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
}
	
	/**
	 * Generate random numeric string
	*/
    static final String NUMERIC_STRING = "123456789";
	public static String generateRandomNumericString(int length) {
	StringBuilder builder = new StringBuilder();
	while (length-- != 0) {
	int character = (int)(Math.random()*NUMERIC_STRING.length());
	builder.append(NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
	}
	
	/**
	 * another way to generate random string
	public static String generateRandomString(int length) {
		Random random = new Random();
		String suffix = Long.toString(Math.abs(random.nextLong()), length);
		//return suffix.substring(0, length);
		return suffix.substring(0,length);
		}
	 */	
		
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(generateRandomString(3));
		}
	}
		
public static String generateRandomDescriptionOfNote() {
		
		List<String> list = new ArrayList<>();
        list.add("Treadmill is out of function and called technician to look into it. "); 
        list.add("A suspicious man walked around in the parking lot for half and hour.");  
        list.add("Resident Rita Syne reported lost a diamond ring in the gym."); 
	        Random rand = new Random(); 
	        return list.get(rand.nextInt(list.size())); 	
	}






}



