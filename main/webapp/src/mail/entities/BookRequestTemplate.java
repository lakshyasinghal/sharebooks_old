package com.sharebooks.mail.entities;


import static com.sharebooks.mail.enums.MailSubjects.*;
import com.sharebooks.mail.interfaces.MailTemplate;



public class BookRequestTemplate extends GenericMailTemplate {


	public BookRequestTemplate(String[] recipients){
		super(recipients , BOOK_REQUEST);
	}


	public void buildBody() throws Exception{
		try{
			body = "<h1>Book has been been requested from your side</h1>";
		}
		catch(Exception ex){
			System.out.println("Error in buildBody in BookRequestTemplate");
			throw ex;
		}
	}
}