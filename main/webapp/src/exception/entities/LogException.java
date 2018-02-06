package com.sharebooks.exception.entities;



public final class LogException extends Exception {

	private static final String message = "Some error occurred while logging";

	public LogException(){
		super(message);
	}

	public LogException(String logPath){
		super(message + "\nPath : " + logPath);
	}


	public void log(){
		
	}
}



