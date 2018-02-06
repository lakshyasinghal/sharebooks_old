package com.sharebooks.exception.entities;



public final class InvalidPropertyException extends Exception {

	private static final String message = "Invalid property in properties file";

	public InvalidPropertyException(){
		super(message);
	}

	public InvalidPropertyException(String propertyName){
		super(message + "\nProperty : " + propertyName);
	}


	public void log(){
		
	}
}



