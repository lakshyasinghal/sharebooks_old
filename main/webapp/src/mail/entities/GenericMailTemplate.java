package com.sharebooks.mail.entities;


import com.sharebooks.mail.interfaces.MailTemplate;



public abstract class GenericMailTemplate implements MailTemplate {

	protected String[] recipients;
	protected String subject;
	protected String body;



	public GenericMailTemplate(String[] recipients , String subject){
		try{
			this.recipients = recipients;
			this.subject = subject;
		}
		catch(Exception ex){
			System.out.println("Error in constructor in GenericMailTemplate");
			throw ex;
		}
	}



	//Getter methods


	public String[] recipients(){
		return recipients;
	} 

	public String subject(){
		return subject;
	}

	public String body(){
		return body;
	}
}