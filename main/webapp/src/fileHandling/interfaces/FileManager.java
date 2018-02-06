package com.sharebooks.fileHandling.interfaces;


import java.util.*;


public interface FileManager {

	public abstract Map<String , String> getDataFromFile(String filePath) throws Exception;
}