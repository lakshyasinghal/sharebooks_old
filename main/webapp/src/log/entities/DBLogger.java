package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class DBLogger extends GenericLogger {

	private final static DBLogger dbLogger = new DBLogger();

	private DBLogger(){

	}


	public static DBLogger dbLogger() throws Exception{
		try{
			return dbLogger;
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