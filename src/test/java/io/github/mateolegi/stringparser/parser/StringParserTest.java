package io.github.mateolegi.stringparser.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringParserTest {

	@Test
	void testAddVariable() {
		String key = "key";
		StringParser parser = new StringParser();
		parser.addVariable(key, "value");
		assertTrue(parser.getVariables().containsKey(key));
	}

	@Test
	void testDeleteVariable() {
		String key = "key";
		StringParser parser = new StringParser();
		parser.addVariable(key, "value");
		parser.deleteVariable(key);
		assertFalse(parser.getVariables().containsKey(key));
	}

	@Test
	void testParse() {
		StringParser parser = new StringParser();
		parser.addVariable("name", "Jimmy")
			  .addVariable("lastname", "Sullivan")
			  .setText("Hello, Mr. :name :lastname");
		String parsedText = parser.parse();
		String expected = "Hello, Mr. Jimmy Sullivan";
		assertEquals(expected, parsedText);
	}

	@Test
	void testParseString() {
		StringParser parser = new StringParser();
		parser.addVariable("name", "Jimmy")
			  .addVariable("lastname", "Sullivan");
		String parsedText = parser.parse("Hello, Mr. :name :lastname");
		String expected = "Hello, Mr. Jimmy Sullivan";
		assertEquals(expected, parsedText);
	}

	@Test
	void testParseStringStringArray() {
		String parsedText = StringParser.parse("Hello, Mr. ?1 ?2", "Jimmy", "Sullivan");
		String expected = "Hello, Mr. Jimmy Sullivan ";
		assertEquals(expected, parsedText);
	}
}
