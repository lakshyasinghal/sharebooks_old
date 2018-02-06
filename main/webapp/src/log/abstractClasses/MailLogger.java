package com.sharebooks.logger.abstractClasses;


import java.io.*;
import com.sharebooks.logger.interfaces.Logger;
import com.sharebooks.exception.entities.*;
import com.sharebooks.fileHandling.entities.FileValidator;
import com.sharebooks.mail.models.MailManager;



public abstract class MailLogger implements Logger {

	private boolean debuggingOn = false;
	private String[] ADMIN_MAIL_IDS;

	protected MailLogger(){

	}


	public void init(String[] adminMailIds , String debuggingOn) throws Exception {
		try{
			ADMIN_MAIL_IDS = adminMailIds;
			if(debuggingOn.equals("true")){
				this.debuggingOn = true;
			}
		}
		catch(Exception ex){
			System.out.println("Exception in init method in MailLogger");
			throw ex;
		}
	}



	public synchronized void log(String message) {
		try{
			MailManager.sendLogMailToAdmin(ADMIN_MAIL_IDS , message);
		}
		catch(Exception ex){
			System.out.println("Exception in log method in MailLogger");
		}
	}


	public synchronized void error(String message){
		try{
			MailManager.sendErrorMailToAdmin(ADMIN_MAIL_IDS , message);
		}
		catch(Exception ex){
			System.out.println("Exception in error method in MailLogger");
		}
	}


	public synchronized void debug(String message) {
		try{
			if(!debuggingOn){
				return;
			}

			MailManager.sendDebugMailToAdmin(ADMIN_MAIL_IDS , message);
		}
		catch(Exception ex){
			System.out.println("Exception in debug method in MailLogger");
		}
	} 

}