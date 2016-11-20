package com.ponup.phpiniparser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Section {

	private String name;
	private List<Setting> settings;
	private Map<String, Setting> settingsMap;

	public Section() {
		this(null);
	}
	
	public Section(String name) {
		this.name = name;
		
		settings = new LinkedList<>();
		settingsMap = new HashMap<>();
	}

	public List<Setting> getSettings() {
		return settings;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasSetting(final String name) {
		return settingsMap.containsKey(name);
	}
	
	public void addSetting(Setting setting) {
		settings.add(setting);
		settingsMap.put(setting.getName(), setting);
	}
	
	public Setting getSetting(final String name) {
		return settingsMap.get(name);
	}

	@Override
	public String toString() {
		return String.format("Section{name=%s}", name);
	}
}
