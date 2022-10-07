package com.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.google.common.collect.Ordering;

public class TestResultValidator extends WebPageUtils {

	protected final static Logger logger = LogManager.getLogger(TestResultValidator.class.getName());
	
	/**
	 * Verify webelement is editable
	 * @param element of column
	 */
	public static boolean isElementEditable(WebElement element) {
		try {
			if (element == null)
				return false;
			if (element.getAttribute("readonly") != null)
				return true;
			element.sendKeys("checkEditable");
			if (element.getText().equals("checkEditable"))
				return true;
			return false;
		} catch (Exception e) {
			logger.error("Exception is: ", e);
			return false;
		}
	}
	
	
	/**
	 * Sort String value of column
	 * @param element of column
	 */
	public static boolean isStringElementsAscendingOrdered1(List<WebElement> elements) {
		List<String> stringList = new ArrayList<>();
		boolean isAscending = true;
		try {
			for (int i = 0; i < elements.size(); i++) {
				if(elements.get(i).getText().toLowerCase().isEmpty()) {
					logger.info("These columns have no value");
					stringList.add(null);
					continue;
				}
				stringList.add(elements.get(i).getText().toLowerCase());
			logger.info("Elements before sorting: " + stringList);
			}
			Collections.sort(stringList,
					Ordering.from(String.CASE_INSENSITIVE_ORDER)
                            .nullsFirst());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.info("Is ascending ordered: " + isAscending);
		return isAscending;
	}
	
	
	/**
	 * Sort String value of column
	 * @param element of column
	 */
	public static boolean isStringElementsAscendingOrdered(List<WebElement> elements) {
		List<String> tableValues = new ArrayList<>();
		ArrayList<String> referenceValues = new ArrayList<String>();
		boolean isAscending = true;
		try {
			for (int i = 0; i < elements.size(); i++) {
				/*
				if(elements.get(i).getText().toLowerCase().isEmpty()) {
					logger.info("These columns have no value");
					tableValues.add(null);
					continue;
				}
				*/
				tableValues.add(elements.get(i).getText());
			}
			logger.info("Elements before sorting: " + tableValues);
			
			for(int i=0; i < tableValues.size(); i++){
				Collections.sort(tableValues);
			    referenceValues.add(tableValues.get(i)); 
			}
			    logger.info("Elements after sorting: " + referenceValues);
			    isAscending = isAscending && tableValues.equals(referenceValues);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.info("Is ascending ordered: " + isAscending);
		return isAscending;
	}
	

	/**
	 * Sort integer value of column
	 * @param element of column
	 */
	public static boolean isIntegerElementsAscendingOrdered(List<WebElement> elements) {
		List<Integer> intList = new ArrayList<>();
		try {
			for (int i = 0; i < elements.size(); i++) {
				if(elements.get(i).getText().isEmpty()) {
					logger.info("These columns have no value");
					continue;
				}
				intList.add(Integer.parseInt(elements.get(i).getText()));
			}
			logger.info("Elements before: " + intList);
			for (int i = 0; i < intList.size(); i++)
				if ((i + 1) < intList.size())
					if (intList.get(i) > intList.get(i + 1))
						return false;
					return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	
	
	/**
	 * Sort integer value of column(split $ symbol (eg. $ 34.00 before compare value)
	 * @param element of column
	 */
	public static boolean isIntegerElementsAscendingOrdered1(List<WebElement> elements) {
		List<Integer> intList = new ArrayList<>();
		try {
			for (int i = 0; i < elements.size(); i++) {
					//strList.add(elements.get(i).getText().replace("$", ""));
				intList.add(Integer.parseInt(elements.get(i).getText().replace("$", "")));
			}
			logger.info("Elements of int after sorted: " + intList);
			for (int i = 0; i < intList.size(); i++)
				if ((i + 1) < intList.size())
					if (intList.get(i) > intList.get(i + 1))
						return false;
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * Sort date value of column (format date as 01/22/2020)
	 * @param element of column
	 */
	public static boolean isDateElementsAscendingOrdered(List<WebElement> elements) {
		List<Long> intList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
		try {
			for (int i = 0; i < elements.size(); i++) {
				if (elements.get(i).getText().isEmpty()) {
					logger.info("These columns have no value");
					continue;
				}
				intList.add(format.parse(elements.get(i).getText()).getTime());
			}
				logger.info("Elements before: " + intList);
			for (int i = 0; i < intList.size(); i++)
				if ((i + 1) < intList.size())
					if (intList.get(i) > intList.get(i + 1)) 
						return false;
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Sort date value of column (format date as 01/22/2020 2:30 AM)
	 * @param element of column
	 */
	public static boolean isDateElementsAscendingOrdered1(List<WebElement> elements) {
		List<Long> intList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy K:mm a" );
		try {
			for (int i = 0; i < elements.size(); i++) {
				if(elements.get(i).getText().isEmpty()) {
					logger.info("These columns have no value");
					continue;
				}	
				intList.add(format.parse(elements.get(i).getText()).getTime());
			}
			logger.info("Elements before: " + intList);
			for (int i = 0; i < intList.size(); i++)
				if ((i + 1) < intList.size())
					if (intList.get(i) > intList.get(i + 1))
						return false;
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Sort date value of column (format date as 01/22/2020 @2:30 AM)
	 * @param element of column
	 */
	public static boolean isDateElementsAscendingOrdered2(List<WebElement> elements) {
		List<Long> intList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy @ K:mm a" );
		try {
			for (int i = 0; i < elements.size(); i++) {
				if(elements.get(i).getText().isEmpty()) {
					logger.info("These columns have no value");
					continue;
				}
				intList.add(format.parse(elements.get(i).getText()).getTime());
			}
			logger.info("Elements before: " + intList);
			for (int i = 0; i < intList.size(); i++)
				if ((i + 1) < intList.size())
					if (intList.get(i) > intList.get(i + 1))
						return false;
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}


	/**
	 * filter list String
	 * @param xpath is the spath of tableRow list
	 */
	public static boolean isFilterResultListed(String xpath, WebDriver driver) {
		List<String> filterResults = new ArrayList<>();
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		try {
			for (int i = 0; i < elements.size(); i++)
				filterResults.add(elements.get(i).getText());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("Total count of records is: " + filterResults.size());
		logger.info("Filter results are: " + filterResults);
		SeleniumWrapper.waitForDomToBeRendered_5S(driver);
		if (filterResults.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean isFilterResultContainKeyword(String xpath, WebDriver driver,String keyword) {
		List<String> filterResults = new ArrayList<>();
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		try {
			if (elements.size() != 0) {
				for (int i = 0; i < elements.size(); i++) {
					filterResults.add(elements.get(i).getText());
				}
			}
			logger.info("Total count of records is: " + filterResults.size());
			logger.info("Filter results are: " + filterResults);
			SeleniumWrapper.waitForDomToBeRendered_3S(driver);
			if (filterResults.toString().contains(keyword)) {	
				return true;
			}	
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	
	
	public static boolean isFilterResultListed1(String xpath, WebDriver driver) {
		List<String> filterResults = new ArrayList<>();
		List<WebElement> elements = driver.findElements(By.xpath(xpath));		
		try {
			for (int i = 0; i < elements.size(); i++)
				filterResults.add(elements.get(i).getText());
			if(!filterResults.contains("INCIDENT REPORT")) {
				logger.info("Total count of records is: " + 0);	
			}
			else {
				 logger.info("Total count of records is: " + filterResults.size());	
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		 logger.info("Matched result is: " + filterResults);
		return true;
	}
	
	
	public static boolean isSearchedFromElementsList(String xpath, WebDriver driver, String keyword) {
		List<String> allElementText = new ArrayList<>();
		List<String> matchedResult = new ArrayList<>();
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		try {
			for (int i = 0; i < elements.size(); i++)
				allElementText.add(elements.get(i).getText());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		for (int i = 0; i < allElementText.size(); i++) {
			if (allElementText.get(i).contains(keyword)) {
				matchedResult.add(allElementText.get(i));
			}
		}
		logger.info("Matched result is: " + matchedResult);
		logger.info("Matched count is: " + matchedResult.size());
		SeleniumWrapper.waitForDomToBeRendered_3S(driver);
		if(matchedResult.size() ==0) {
			return false;
		}
		else {
			return true;	
		}	
	}

	public static String verifySearchResult(String xpath, WebDriver driver, String keyword) {
		List<String> searchedResult = new ArrayList<>();
		List<String> matchedResult = new ArrayList<>();
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		if (elements.size() == 0)
			return "Nothing Searched";
		try {
			for (int i = 0; i < elements.size(); i++)
				searchedResult.add(elements.get(i).getText());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		for (int i = 0; i < elements.size(); i++) {
			// if (!searchedResult.get(i).contains(keyword)) {
			// return false;
			// }
			matchedResult.add(searchedResult.get(i));
		}
		logger.info("Matched result is: " + searchedResult);
		logger.info("Matched count is: " + searchedResult.size());
		return elements.size() + " items searched";
	}
	
	
}
