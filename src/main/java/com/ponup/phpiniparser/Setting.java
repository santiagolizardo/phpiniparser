package com.ponup.phpiniparser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Setting {

	private String name;
	private List<String> values;

	public Setting(String name) {
		this.name = name;
		this.values = new LinkedList<>();
	}

	public Setting() {
		this(null);
	}

	public Setting(String name, String value) {
		this(name);

		this.values.add(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSingleValue() {
		return getSingleValue(0);
	}

	public String getSingleValue(int index) {
		return values.get(index);
	}

	public List<String> getValues() {
		return values;
	}

	public void addValue(String value) {
		values.add(value);
	}

	@Override
	public String toString() {
		return String.format("Setting{name=%s, values=%s}", name, Arrays.toString(values.toArray()));
	}
}
