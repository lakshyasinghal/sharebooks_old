package com.sharebooks.mail.models;


import com.sharebooks.mail.interfaces.MailTemplate;
import com.sharebooks.mail.entities.*;



public class MailManager {



	public void sendBookRequestMail(String[] recipients , Object obj) throws Exception{
		try{
			MailTemplate mailTemplate = new BookRequestTemplate(recipients);
			MailSender.sendMail(mailTemplate);
		}
		catch(MailFailureException ex){
			System.out.println("Exception in sendBookRequestMail in MailManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in sendBookRequestMail in MailManager");
			throw ex;
		}
	}


	public void sendBookRequestAcceptanceMail(String[] recipients , Object obj) throws Exception{
		try{
			MailTemplate mailTemplate = new BookRequestAcceptanceTemplate(recipients);
			MailSender.sendMail(mailTemplate);
		}
		catch(MailFailureException ex){
			System.out.println("Exception in sendBookRequestAcceptanceMail in MailManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in sendBookRequestAcceptanceMail in MailManager");
			throw ex;
		}
	}



	public void sendBookRequestRejectionMail(String[] recipients , Object obj) throws Exception{
		try{
			MailTemplate mailTemplate = new BookRequestRejectionTemplate(recipients);
			MailSender.sendMail(mailTemplate);
		}
		catch(MailFailureException ex){
			System.out.println("Exception in sendBookRequestRejectionMail in MailManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in sendBookRequestRejectanceMail in MailManager");
			throw ex;
		}
	}











	// ADMIN MAILS

	public void sendLogMailToAdmin(String[] recipients , String message) throws Exception{
		try{
			MailTemplate mailTemplate = new ApplicationLogTemplate(recipients , message);
			MailSender.sendMail(mailTemplate);
		}
		catch(MailFailureException ex){
			System.out.println("Exception in sendLogMailToAdmin in MailManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in sendLogMailToAdmin in MailManager");
			throw ex;
		}
	}

	public void sendErrorMailToAdmin(String[] recipients , String message) throws Exception{
		try{
			MailTemplate mailTemplate = new ApllicationErrorTemplate(recipients , message);
			MailSender.sendMail(mailTemplate);
		}
		catch(MailFailureException ex){
			System.out.println("Exception in sendErrorMailToAdmin in MailManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in sendErrorMailToAdmin in MailManager");
			throw ex;
		}
	}


	public void sendDebugMailToAdmin(String[] recipients , String message) throws Exception{
		try{
			MailTemplate mailTemplate = new ApplicationDebugTemplate(recipients , message);
			MailSender.sendMail(mailTemplate);
		}
		catch(MailFailureException ex){
			System.out.println("Exception in sendDebugMailToAdmin in MailManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in sendDebugMailToAdmin in MailManager");
			throw ex;
		}
	}

}