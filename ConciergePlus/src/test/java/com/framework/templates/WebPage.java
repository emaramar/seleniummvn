package com.framework.templates;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class WebPage {
	protected final static Logger logger = LogManager.getLogger(WebPage.class.getName());

	/**
	 * Constructor of page object base class
	 * 
	 */
	public WebPage() {
		logger.info("Here is base class of all page objects.");
	}

	/**
	 * Page object navigator, to navigate to the page object
	 */
	abstract public boolean navigateTo();
	/**
	 * To verify page loaded or not
	 */
	abstract public boolean isLoaded();
}