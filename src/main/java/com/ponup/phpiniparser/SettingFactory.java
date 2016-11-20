package com.ponup.phpiniparser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingFactory {
	
	private static final Logger LOGGER = Logger.getLogger(SettingFactory.class.getName());

	public static Setting create(String name, String value) {
		String[] words = name.split("_");
		String className = "";
		for(String word : words) {
			className += word.substring(0, 1).toUpperCase();
			className += word.substring(1);
		}

		try {
			Setting setting = (Setting)Class.forName("com.ponup.phpiniparser.setting." + className).newInstance();
			setting.addValue(value);
			return setting;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			LOGGER.log(Level.FINE, ex.getMessage());
		}

		return new Setting(name, value);
	}
}
