package com.ponup.phpiniparser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhpIni {

	private List<Section> sections;
	private Map<String, Section> sectionsMap;

	public PhpIni() {
		sections = new LinkedList<>();
		sectionsMap = new HashMap<>();
	}

	public List<Section> getSections() {
		return sections;
	}
	
	public void addSection(Section section) {
		sections.add(section);
		sectionsMap.put(section.getName(), section);
	}
	
	public boolean hasSection(final String name) {
		return sectionsMap.containsKey(name);
	}
	
	public Section getSection(final String name) {
		return sectionsMap.get(name);
	}
}
