package com.ponup.phpiniparser.setting;

import com.ponup.phpiniparser.Setting;
import java.util.LinkedList;
import java.util.List;

public class PredefinedSetting extends Setting {

	private String defaultValue;
	private List<String> acceptedValues;

	public PredefinedSetting(String name, String value) {
		super(name, value);
		
		acceptedValues = new LinkedList<>();
	}

	public List<String> getAcceptedValues() {
		return acceptedValues;
	}

	public void setAcceptedValues(List<String> acceptedValues) {
		this.acceptedValues = acceptedValues;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
	
	public boolean isValid() {
		return acceptedValues.contains(getSingleValue());
	}
}
