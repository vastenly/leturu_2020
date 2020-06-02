package com.vastenly.taf;

import static com.vastenly.taf.utils.StringUtils.isNullOrEmpty;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.vastenly.taf.utils.file.props.PropertyFileReader;

public class Resources {
	
	private static final Logger log = Logger.getLogger(Resources.class);
	
	private static Properties properties;

	/**
	 * @param filePath
	 * @param key
	 * @return value
	 */
	public static String getProperty(String filePath, String key) {
		if (isNullOrEmpty(filePath))
			throw new IllegalArgumentException("[Resources] Defined file path is NULL or empty!");
		if (isNullOrEmpty(key))
			throw new IllegalArgumentException("[Resources] Defined property key is NULL or empty!");
		PropertyFileReader pfr = new PropertyFileReader();
		properties = pfr.loadPropFile(filePath).getProperties();
		return properties.getProperty(key);
	}

	public static Properties loadProperties(String filePath) {
		if (isNullOrEmpty(filePath))
			throw new IllegalArgumentException("[Resources] Defined file path is NULL or empty!");
		log.info("[Resources] Load properties from [" +filePath+ "] property file.");
		PropertyFileReader pfr = new PropertyFileReader();
		pfr.loadPropFile(filePath);
		return pfr.getProperties();
	}

	public static Properties getProperties() {
		return properties;
	}
}
