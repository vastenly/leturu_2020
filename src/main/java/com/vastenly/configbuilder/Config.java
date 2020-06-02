package com.vastenly.configbuilder;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.vastenly.configbuilder.env.Environment;
import com.vastenly.configbuilder.env.AppConstants;
import com.vastenly.taf.Classpath;
import com.vastenly.taf.Resources;
import com.vastenly.taf.utils.FileUtils;
import com.vastenly.taf.utils.exceptions.UnknownPropertyException;

public class Config {
	
	private static Logger log = Logger.getLogger(Config.class);
	
	private static String resPath = "./src/main/resources";
	private static String envPath = resPath + "/env";
	private static String fileName = "test.properties";
	private static String filePathPattern = envPath+"/%s/"+fileName;
	private static String classPathPattern = "env/%s/"+fileName;
	
	public Config() {
		
	}
	
	public static String getProperty(Environment environment, String appConstants) {
		String env = environment.name().toLowerCase();
		String value = Classpath.getProperty(String.format(classPathPattern, env), appConstants);
		if (value == null) {
			String filePath = String.format(filePathPattern, env);
			value = FileUtils.isFileExist(filePath) ? Resources.getProperty(filePath, appConstants) : null;
		}
		if (value == null)
			throw new UnknownPropertyException("[Config] Unable to find property [" +appConstants+ "] within [" +environment+ "] environment config.");
		return value;
	}
	
	public static Properties loadProperties(Environment environment) {
		String env = environment.name().toLowerCase();
		return Classpath.loadProperties(String.format(classPathPattern, env));
	}
	
	/**
	 * main method for local test
	 * @param args
	 */
	public static void main(String[] args) {
		String val1 = getProperty(Environment.AUTO, AppConstants.TEST_CONFIG.ENVIRONMENT_TYPE);
		log.info("val1: " +val1);

	}

}
