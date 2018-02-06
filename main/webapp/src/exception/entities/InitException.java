package com.sharebooks.exception.entities;



public final class InitException extends Exception {

	private static final String message = "Initialization error";

	public InitException(){
		super(message);
	}


	public void log(){
		
	}
}



