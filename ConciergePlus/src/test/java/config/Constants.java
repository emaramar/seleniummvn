package config;

public final class Constants {
	public static final int IMPLICIT_WAIT_TIME = 5;
	public static final int EXPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 10;
	public static final int PAGE_RENDER_TIME = 1000;   //sleep for 1 second  || Thread.sleep(1000) is to sleep for one second
	public static final int PAGE_RENDER_TIME_3S = 3000; //sleep for 3 second  
	public static final int PAGE_RENDER_TIME_5S = 5000; //sleep for 5 second  
	
	
	public static final String RESOURCE_FOLDER = "src/test/resources/";
	public static final String EXTENT_REPORT_CONFIG = "extent-report-config.xml";
	public static final String TEST_REPORT_FOLDER = "test-output/ExtentReport/";
	public static final String SCREENSHOT_FOLDER = "test-output/ExtentReport/ScreenShot/";
	public static final String SITE_REQUEST_BODY = "ApiRequestBody/SiteMgmt/";
	
	public static final String UPLOAD_FOLDER = "src/test/FileUpload/";
	

	public static final String WIN64_DRIVER_IE = RESOURCE_FOLDER + "IEDrivers/win/IEDriverServer-64.exe";
	public static final String WIN64_DRIVER_CHROME = RESOURCE_FOLDER + "ChromeDrivers/win/chromedriver-64.exe";
	public static final String WIN64_DRIVER_FIREFOX = RESOURCE_FOLDER + "FirefoxDrivers/win/geckodriver-64.exe";
	public static final String WIN64_DRIVER_EDGE = RESOURCE_FOLDER + "msedgedrivers/win/msedgedriver-64.exe";
	public static final String OSX64_DRIVER_CHROME = RESOURCE_FOLDER + "ChromeDrivers/osx/chromedriver-64";
	public static final String OSX64_DRIVER_FIREFOX = RESOURCE_FOLDER + "FirefoxDrivers/osx/geckodriver-64";
	public static final String OSX64_DRIVER_EDGE = RESOURCE_FOLDER + "msedgedrivers/osx/msedgedriver-64.exe";
	
	public static final String imgOfCarKey = UPLOAD_FOLDER + "carkey.exe"; 
	public static final String imgOfDogWalker = UPLOAD_FOLDER + "dogwalker.exe"; 
	public static final String imgOfCondo = UPLOAD_FOLDER + "condo.exe"; 
	public static final String imgOfPdf = UPLOAD_FOLDER + "pdf.exe"; 
	public static final String imgOfText = UPLOAD_FOLDER + "text.exe"; 
	public static final String imgOfPhoto = UPLOAD_FOLDER + "photo.exe"; 
	public static final String closePrintWindow = UPLOAD_FOLDER + "closePrint.exe"; 
	public static final String closeEmailClientWindow = UPLOAD_FOLDER + "closeEmailClient.exe"; 		
	
	public static enum CLICK_METHOD_ENUM {
		CLICK, 
		SENDENTER, 
		SENDRETURN, 
		SUBMIT, 
		RUNJS
	}
}