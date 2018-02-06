package com.sharebooks.exception.entities;



public final class MailFailureException extends Exception {

	private static final String message = "Exception occurred while sending mail";

	public MailFailureException(){
		super(message);
	}

	public MailFailureException(String mailMessage){
		super(message + "\nMail Message : " + mailMessage);
	}
}



