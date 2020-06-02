package com.leturu.app.test;

import org.apache.log4j.Logger;

import com.leturu.app.Application;
import com.vastenly.taf.tools.bdd.cucumber.CucumberBaseTest;


public class BaseTest extends CucumberBaseTest implements TestConfig {
	
	protected final String APP_URL = LETU_RU_URL;
	protected static final Logger log = Logger.getLogger(BaseTest.class);
	public Application app = new Application();
	
	public BaseTest() {
		super(BROWSER_TYPE);
	}
	
	
}
