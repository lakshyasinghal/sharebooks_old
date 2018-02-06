package com.sharebooks.exception.entities;



public final class DebugException extends Exception {

	private static final String message = "Some error occurred while logging";

	public DebugException(){
		super(message);
	}

	public DebugException(String logPath){
		super(message + "\nPath : " + logPath);
	}

}