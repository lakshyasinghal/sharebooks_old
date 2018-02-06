package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class MailLogger extends GenericLogger {

	private final static MailLogger mailLogger = new MailLogger();

	private MailLogger(){

	}


	public static MailLogger mailLogger() throws Exception{
		try{
			return mailLogger;
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