package com.sharebooks.logging.entities;


import com.sharebooks.logging.interfaces.Logger;
import com.sharebooks.exception.entities.*;



public final class TransactionLogger extends GenericLogger {

	private final static TransactionLogger transactionLogger = new TransactionLogger();

	private TransactionLogger(){

	}


	public static TransactionLogger transactionLogger() throws Exception{
		try{
			return transactionLogger;
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