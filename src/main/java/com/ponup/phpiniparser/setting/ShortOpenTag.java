package com.ponup.phpiniparser.setting;

public class ShortOpenTag extends PredefinedSetting {

	public ShortOpenTag(String value) {
		super("short_open_tag", value);
		
		setDefaultValue("On");
		getAcceptedValues().add("On");
		getAcceptedValues().add("Off");
	}
	
	public ShortOpenTag() {
		this(null);
	}
}
