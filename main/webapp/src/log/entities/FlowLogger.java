package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class FlowLogger extends GenericLogger {

	private final static FlowLogger flowLogger = new FlowLogger();

	private FlowLogger(){

	}


	public static FlowLogger flowLogger() throws Exception{
		try{
			return flowLogger;
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