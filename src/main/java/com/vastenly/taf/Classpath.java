package com.vastenly.taf;

import static com.vastenly.taf.utils.StringUtils.isNullOrEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Classpath {
	
	private static final Logger log = Logger.getLogger(Classpath.class);
	
	private static Properties properties;
	
	public static <T> InputStream getResourceAsStream(Class<T> clazz, String classPath) {
		InputStream inputStream = clazz.getClassLoader().getResourceAsStream(classPath);
		if (inputStream == null)
			throw new IllegalArgumentException("[Classpath] Defined resource [" +classPath+ "] is NOT found in classpath!");
		return inputStream;
	}
	
	/**
	 * @param classPath
	 * @param key
	 * @return value
	 */
	public static String getProperty(String classPath, String key) {
		if (isNullOrEmpty(classPath))
			throw new IllegalArgumentException("[Classpath] Defined file path is NULL or empty!");
		if (isNullOrEmpty(key))
			throw new IllegalArgumentException("[Classpath] Defined property key is NULL or empty!");
		try {
			properties = new Properties();
			properties.load(getResourceAsStream(Classpath.class, classPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(key);
		log.info("[Classpath] Property [" +key+ "=" +value+ "] loaded.");
		return value;
		
	}
	
	/**
	 * @param classPath
	 * @return absolute filePath value by its @classPath
	 */
	/*private static String getFilePath(String classPath) {
		String filePath = Classpath.class.getProtectionDomain().getCodeSource().getLocation().getPath()+classPath;
		log.info("[Classpath] Filepath for classpath [" +classPath+ "] is [" +filePath+ "].");
		return filePath;
	}
	*/
	public static Properties loadProperties(String classPath) {
		if (isNullOrEmpty(classPath))
			throw new IllegalArgumentException("[Classpath] Defined file path is NULL or empty!");
		try {
			log.info("[Classpath] Load properties from [" +classPath+ "] property file.");
			properties = new Properties();
			properties.load(Classpath.class.getClassLoader().getResourceAsStream(classPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static Properties getProperties() {
		return properties;
	}

}
