package com.ponup.phpiniparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhpIniParser {

	private Pattern sectionPattern, settingPattern;

	public PhpIniParser() {
		sectionPattern = Pattern.compile("^[[:space:]]*\\[(.+)\\][[:space:]]*$");
		settingPattern = Pattern.compile("^[[:space:]]*?([^;][^=]+?)[[:space:]]*=[[:space:]]*?(.+)[[:space:]]*$");
	}
	
	public PhpIni parse(final String path) throws FileNotFoundException, IOException {
		File file = new File(path);
		if (!file.exists()) {
			throw new FileNotFoundException(path);
		}

		final PhpIni phpIni = new PhpIni();
		List<String> lines = Files.readAllLines(file.toPath());
		String sectionName = null;
		for (String line : lines) {
			Matcher sectionMatcher = sectionPattern.matcher(line);
			Matcher settingMatcher = settingPattern.matcher(line);
			if (sectionMatcher.matches()) {
				sectionName = sectionMatcher.group(1);
				Section section = new Section(sectionName);
				phpIni.addSection(section);
			}
			else if (settingMatcher.matches()) {
				String name = settingMatcher.group(1).trim();
				String value = settingMatcher.group(2).trim();
				Section section = phpIni.getSection(sectionName);
				if (section.hasSetting(name)) {
					section.getSetting(name)
						.addValue(value);
				} else {
					Setting setting = SettingFactory.create(name, value);
					section.addSetting(setting);
				}
			}
		}
		return phpIni;
	}
}
