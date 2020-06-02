package com.vastenly.taf.utils.prop;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigProperties {
	
	private static Properties PROPERTIES;
	
	public ConfigProperties() {
		if (PROPERTIES == null) {
			PROPERTIES = new Properties();
			URL props = ClassLoader.getSystemResource("config.properties");
			try {
				PROPERTIES.load(props.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getProperty(String key) {
		PROPERTIES = new Properties();
		URL props = ClassLoader.getSystemResource("config.properties");
		return PROPERTIES.getProperty(key);
	}
}
