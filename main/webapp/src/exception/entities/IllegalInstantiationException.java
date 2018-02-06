package com.sharebooks.exception.entities;



public final class IllegalInitializationException extends Exception {

	private static final String message = "Some class is illegally initializing a resource";

	public IncorrectPathException(){
		super(message);
	}


	public void log(){
		
	}
}