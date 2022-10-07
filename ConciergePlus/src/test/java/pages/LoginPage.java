package pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.framework.templates.WebPage;
import com.utilities.Function;
import com.utilities.SeleniumWrapper;


public class LoginPage extends WebPage {
	protected final static Logger logger = LogManager.getLogger(LoginPage.class.getName());
	protected String URL;
	protected String userName;
	protected String password;
	protected WebDriver driver;

	/**
	 * Page object constructor, to initialize the page
	 * 
	 * @param driver   web browser driver
	 * @param URL      web page URL
	 * @param userName login user name
	 * @param password login password
	 */
	public LoginPage(WebDriver driver, String URL, String userName, String password) {
		this.driver = driver;
		this.URL = URL;
		this.userName = userName;
		this.password = password;
		PageFactory.initElements(driver, this);
		logger.info("LoginPage is now ready.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	@Override
	public boolean navigateTo() {
		try {
			driver.navigate().to(URL);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: ", e);
			return false;
		}
	}
	
	@Override
	public boolean isLoaded() {
		if(loginButton.isEnabled()) {
			return true;
		}
		return false;
	}
	

	@FindBy(xpath = ".//*[@id=\"username\"]")
	private static WebElement loginUsername;

	/**
	 * Input user name
	 * 
	 * @param userName login user name
	 */
	private boolean inputUsername(String userName) {
		SeleniumWrapper.explicitWaitClickable(driver, loginUsername, 30);
		try {
			loginUsername.clear();
			SeleniumWrapper.implicitWait_Constant(driver);
			loginUsername.sendKeys(userName);
			SeleniumWrapper.implicitWait_Constant(driver);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: ", e);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id=\"password\"]")
	private static WebElement loginPassword;

	/**
	 * Input password
	 * 
	 * @param password login password
	 */
	private boolean inputPassword(String password) {
		SeleniumWrapper.explicitWaitClickable(driver, loginPassword, 30);
		try {
			loginPassword.clear();
			SeleniumWrapper.implicitWait_Constant(driver);
			loginPassword.sendKeys(password);
			SeleniumWrapper.implicitWait_Constant(driver);
			return true;
		} catch (Exception e) {
			logger.error("Exception is: ", e);
			return false;
		}
	}

	@FindBy(xpath = "//*[@id='login-button']")
	private static WebElement loginButton;
	

	/**
	 * Click login button
	 */
	private boolean clickLoginButton() {
		return Function.clickElement(driver, loginButton);
	}

	/**
	 * Login, input user name, input password, and then click login button
	 */
	public boolean login(String userName, String password) {
		if (inputUsername(userName) && inputPassword(password))
			return clickLoginButton();
		return false;
	}

	/**
	 * Logout, click current login username, and then click logout button
	 */
	@FindBy(xpath = "//*[@class='display_name']")
	private static WebElement currentLoginUser;

	@FindBy(xpath = "//a[@class='concierge_logout']")
	private static WebElement logoutBtn;

	public  boolean logout() {
		Function.clickElement(driver, currentLoginUser);
		return Function.clickElement(driver, logoutBtn);
	}

	
}