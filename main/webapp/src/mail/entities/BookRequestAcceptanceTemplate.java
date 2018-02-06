package com.sharebooks.mail.entities;


import static com.sharebooks.mail.enums.MailSubjects.*;



public class BookRequestAcceptanceTemplate extends GenericMailTemplate {


	public BookRequestAcceptanceTemplate(String[] recipients){
		super(recipients , BOOK_REQUEST_APPROVED);
	}


	public void buildBody() throws Exception{
		try{
			body = "<h1>Your book request has been approved</h1>";
		}
		catch(Exception ex){
			System.out.println("Error in buildBody in BookRequestAcceptedTemplate");
			throw ex;
		}
	}
}