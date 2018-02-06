package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class ThreadLogger extends GenericLogger {

	private final static ThreadLogger threadLogger = new ThreadLogger();

	private ThreadLogger(){

	}


	public static ThreadLogger threadLogger() throws Exception{
		try{
			return threadLogger;
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