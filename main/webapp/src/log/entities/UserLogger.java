package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class UserLogger extends GenericLogger {

	private final static UserLogger userLogger = new UserLogger();

	private UserLogger(){

	}


	public static UserLogger userLogger() throws Exception{
		try{
			return userLogger;
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