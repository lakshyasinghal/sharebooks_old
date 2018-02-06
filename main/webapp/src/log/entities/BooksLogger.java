package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class BooksLogger extends GenericLogger {

	private final static BooksLogger booksLogger = new BooksLogger();

	private BooksLogger(){

	}


	public static BooksLogger booksLogger() throws Exception{
		try{
			return booksLogger;
		}
		catch(Exception ex){
			throw ex;
		}
	}


	public void init(String logPath , String loggingOn) throws Exception {
		try{
			super.init(logPath , loggingOn);
		}
		catch(Exception ex){
			throw ex;
		}
	}	

}