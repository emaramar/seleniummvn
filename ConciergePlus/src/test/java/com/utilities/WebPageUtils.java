package com.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import config.Constants;

public class WebPageUtils {
	protected final static Logger logger = LogManager.getLogger(WebPageUtils.class.getName());

	public static void gotoSleep() {
		try {
			Thread.sleep(1000 * Constants.PAGE_RENDER_TIME);   
		} catch (InterruptedException e) {
			logger.info("Exception is: ", e);
		}
	}
	

	public static void gotoSleep(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			logger.info("Exception is: ", e);
		}
	}
}
