package com.sharebooks.exception.entities;



public final class IncorrectPathException extends Exception {

	private static final String message = "File path provided is not valid";

	public IncorrectPathException(){
		super(message);
	}


	public IncorrectPathException(String path){
		super(message + "\n" + "Path : " + path);
	}


	public void log(){
		
	}
}



