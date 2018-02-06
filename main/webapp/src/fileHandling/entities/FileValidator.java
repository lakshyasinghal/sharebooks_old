package com.sharebooks.fileHandling.entities;



import java.io.*;
import java.util.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;



public class FileValidator {
	

	public static boolean validateFile(String path) throws Exception{
		try{
			File file = new File(path);
			if(!file.exists() || file.isDirectory()){
				return false;
			}
			
			return true;
		}
		catch(Exception ex){
			System.out.println("Error in validateFile in FileValidator");
			throw ex;
		}
	}


	public static boolean validateDirectory(String path) throws Exception{
		try{
			File file = new File(path);
			if(!file.isDirectory()){
				return false;
			}
			
			return true;
		}
		catch(Exception ex){
			System.out.println("Error in validateDirectory in FileValidator");
			throw ex;
		}
	}	
}