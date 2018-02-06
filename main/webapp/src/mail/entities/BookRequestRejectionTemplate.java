package com.sharebooks.mail.entities;


import static com.sharebooks.mail.enums.MailSubjects.*;



public class BookRequestRejectionTemplate extends GenericMailTemplate {


	public BookRequestRejectionTemplate(String[] recipients){
		super(recipients , BOOK_REQUEST_DECLINED);
	}


	public void buildBody() throws Exception{
		try{
			body = "<h1>Your book request has been declined</h1>";
		}
		catch(Exception ex){
			System.out.println("Error in buildBody in BookRequestRejectionTemplate");
			throw ex;
		}
	}
}