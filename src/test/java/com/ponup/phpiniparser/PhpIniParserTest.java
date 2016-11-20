package com.ponup.phpiniparser;

import com.ponup.phpiniparser.setting.ShortOpenTag;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class PhpIniParserTest {

	private static PhpIniParser parser;

	@BeforeAll
	public static void beforeAll() {
		parser = new PhpIniParser();
	}

	@Test
	public void testExceptionIsThrownWhenFileIsNotFound() {
		assertThrows(FileNotFoundException.class, () -> parser.parse("testExceptionIsThrownWhenFileIsNotFound.ini"));
	}

	@Test
	public void testSectionIsNotFound() throws FileNotFoundException, IOException {
		PhpIni phpIni = parser.parse("test.ini");
		assertFalse(phpIni.hasSection("PersonalHomePages"));
	}

	@Test
	public void testSectionIsFound() throws FileNotFoundException, IOException {
		PhpIni phpIni = parser.parse("test.ini");
		assertTrue(phpIni.hasSection("PHP"));
	}
	
	@Test
	public void testSettingInSectionIsFound() throws FileNotFoundException, IOException {
		PhpIni phpIni = parser.parse("test.ini");
		Section section = phpIni.getSection("PHP");
		assertTrue(section.hasSetting("short_open_tag"));
	}	

	@Test
	public void testSettingValueInSectionIsFoundAndCorrect() throws FileNotFoundException, IOException {
		PhpIni phpIni = parser.parse("test.ini");
		Section section = phpIni.getSection("PHP");
		Setting setting = section.getSetting("memory_limit");
		assertEquals("128M", setting.getSingleValue());
	}	
	
	@Test
	public void testSettingWithMultipleValuesAreParsed() throws FileNotFoundException, IOException {
		PhpIni phpIni = parser.parse("test.ini");
		Section section = phpIni.getSection("PHP");
		Setting setting = section.getSetting("extension");
		assertArrayEquals(new String[] {"sdl.so", "opengl.so", "glut.so"}, setting.getValues().toArray());
	}
	
	@Test
	public void testSettingReturnsSpecificInstanceIfAvailable() throws FileNotFoundException, IOException {
		PhpIni phpIni = parser.parse("test.ini");
		Section section = phpIni.getSection("PHP");
		Setting setting = section.getSetting("short_open_tag");
		assertTrue(setting instanceof ShortOpenTag);
	}
}
