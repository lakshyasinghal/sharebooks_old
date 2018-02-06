package com.sharebooks.exception.entities;



public final class NoResultsFoundException extends Exception {

	private static final String message = "No results found for your search";

	public NoResultsFoundException(){
		super(message);
	}


	public NoResultsFoundException(String searchCriteria){
		super(message + "\n" + searchCriteria);
	}


	public void log(){
		
	}
}



