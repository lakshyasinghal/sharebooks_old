package com.sharebooks.fileHandling.models.propertiesHandling;


import java.util.*;
import java.io.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import com.sharebooks.fileHandling.interfaces.FileManager;
import com.sharebooks.fileHandling.entities.propertiesHandling.*;


public class PropertyFileManager implements FileManager {


	public Map<String , String> getDataFromFile(String filePath) throws Exception{
		try{
			List<String> lines = null;
			Map<String , String> propertyMapper = null;
			
			isValidFilePath(filePath);

			PropertyFileReader propertyFileReader = new PropertyFileReader();
			lines = propertyFileReader.readFile(filePath);

			if(lines.isEmpty()){
				throw new Exception(EMPTY_PROPERTIES_FILE);
			}

			PropertyParser propertyParser = new PropertyParser();
			propertyMapper = propertyParser.parse(lines);

			return propertyMapper;
		}
		catch(Exception ex){
			System.out.println("Error in getDataFromFile in PropertyFileManager");
			throw ex;
		}
	}


	public void isValidFilePath(String filePath) throws IOException{
		try{
			File file = new File(filePath);
			if(!file.exists()){
				throw new IOException("filePath " + filePath + " does not exist");
			}
			if(file.isDirectory()){
				throw new IOException("filePath " + filePath + " refers to directory and not a file");
			}

		}
		catch(IOException ex){
			//System.out.println("Something is wrong with the file path");
			throw ex;
		}
	}
}