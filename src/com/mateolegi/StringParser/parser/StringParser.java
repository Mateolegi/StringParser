package com.mateolegi.StringParser.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mateo Leal
 */
public class StringParser {

	private String text = null;
	private HashMap<String, String> variables = null;
	
	/** Initialize StringParser */
	public StringParser() {
		this.variables = new HashMap<>();
	}
	
	/**
	 * Initialize StringParser
	 * @param text text
	 */
	public StringParser(String text) {
		this.text = text;
	}

	/**
	 * Initialize StringParser
	 * @param text text
	 * @param variables variables set
	 */
	public StringParser(String text, HashMap<String, String> variables) {
		this.text = text;
		this.variables = variables;
	}

	/** @return the text */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 * @return the instance
	 */
	public StringParser setText(String text) {
		this.text = text;
		return this;
	}

	/** @return the variables */
	public HashMap<String, String> getVariables() {
		return variables;
	}

	/**
	 * @param variables the variables to set
	 * @return the instance
	 */
	public StringParser setVariables(HashMap<String, String> variables) {
		this.variables = variables;
		return this;
	}
	
	/**
	 * Add a variable to the set
	 * @param key key of the variable
	 * @param value value key
	 * @return the instance
	 */
	public StringParser addVariable(String key, String value) {
		if(variables == null) variables = new HashMap<>();
		variables.put(key, value);
		return this;
	}
	
	/**
	 * Delete a variable from the set
	 * @param key variable to delete
	 * @return the instance
	 */
	public StringParser deleteVariable(String key) {
		if(variables != null) variables.remove(key);
		else throw new NullPointerException("There is no variables to delete");
		return this;
	}
	
	/**
	 * Replace a existing variable. If not exists, will be created.
	 * @param key variable key
	 * @param newValue value to be replaced
	 * @return the instance
	 */
	public StringParser replaceVariable(String key, String newValue) {
		addVariable(key, newValue);
		return this;
	}
	
	/**
	 * Find variables in the text
	 * @return variables in the text
	 */
	private List<String> findVariables() {
		List<String> variables = new ArrayList<>();
		String regex="(:)((?:[a-z][a-z0-9_]*))";
	    Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(text);
	    while (m.find()) variables.add(m.group(2));
		return variables;
	}
	
	/**
	 * Replace variables in the text
	 * @param textVars variables previously founded
	 */
	private void replaceText(List<String> textVars) {
		for(String var : textVars) {
			if(variables.containsKey(var)) 
				text = text.replaceAll(":"+var, variables.get(var));
		}
	}
	
	/**
	 * Parse the text with the variables
	 * @return parsed text
	 */
	public String parse() {
		replaceText(findVariables());
		return text;
	}
	
	/**
	 * Parse the text with the variables
	 * @param text text to parse
	 * @return parsed text
	 */
	public String parse(String text) {
		setText(text);
		replaceText(findVariables());
		return text;
	}
}
