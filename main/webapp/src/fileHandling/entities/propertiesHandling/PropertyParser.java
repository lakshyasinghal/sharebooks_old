package com.sharebooks.fileHandling.entities.propertiesHandling;


import java.io.*;
import java.util.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;



public class PropertyParser {
	private char MARKER = '#';
	private String SPLITTER = "=";
	

	public Map<String , String> parse(List<String> lines) throws Exception{
		try{
			Map<String , String> propertyMapper = new HashMap<String , String>();

			String prop = null;
			String val = null;
			String[] tokens = null;


			for(String line : lines){
				line = line.trim();

				//ignore empty lines
				if(line.equals("")){
					continue;
				}

				if(line.charAt(0) == MARKER){
					continue;
				}

				tokens = line.split(SPLITTER);
				//tokens length should be 2, otherwise throw an exception
				if(tokens.length != 2){
					throw new Exception(INCOMPLETE_PROPERTIES_IN_PROPERTY_FILE + " : " + line);
				}

				prop = tokens[0].trim();
				val = tokens[1].trim();

				propertyMapper.put(prop , val);
			}

			return propertyMapper;
		}
		catch(Exception ex){
			System.out.println("Error in parse in PropertyParser");
			throw ex;
		}
	}
}