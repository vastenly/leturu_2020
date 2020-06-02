package com.leturu.app.test;

import com.vastenly.configbuilder.env.Environment;
import com.vastenly.taf.utils.SystemUtils;
import com.vastenly.taf.utils.string.StringPattern;
import com.vastenly.configbuilder.Config;
import com.vastenly.configbuilder.env.AppConstants;
import com.vastenly.taf.core.selenium.browser.BrowserType;

public interface TestConfig  {

    	Environment env = Environment.valueOf(SystemUtils.getProperty(AppConstants.TEST_CONFIG.ENVIRONMENT_TYPE).toUpperCase());
    	BrowserType BROWSER_TYPE = BrowserType.valueOf(SystemUtils.getProperty(AppConstants.TEST_CONFIG.BROWSER_TYPE).toUpperCase());
		
    	String LETURU_BASE_URL =
		StringPattern.url(
				Config.getProperty(env, AppConstants.LETU.LETU_SERVER_PROTOCOL), 
				Config.getProperty(env, AppConstants.LETU.LETU_SERVER_HOST), 
				Config.getProperty(env, AppConstants.LETU.LETU_SERVER_PORT)
				);
	
    	String LETU_RU_URL = LETURU_BASE_URL + Config.getProperty(env, AppConstants.LETU.LETU_LOGIN_URI_PATH);
}
