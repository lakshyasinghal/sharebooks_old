package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class CacheLogger extends GenericLogger {

	private final static CacheLogger cacheLogger = new CacheLogger();

	private CacheLogger(){

	}


	public static CacheLogger cacheLogger() throws Exception{
		try{
			return cacheLogger;
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