package com.sharebooks.mail.models;


import com.sharebooks.mail.interfaces.MailTemplate;
import javax.mail.*;
import javax.mail.internet.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;


//We won't be creating instances of this class and will be using it's static methods for sending mails
public class MailSender {

	private static String USERNAME;
	private static String PASSWORD;
	private static String HOST;
	private static String PORT;
	private static Session SESSION;
	//private static Transport TRANSPORT;


	//No instances will be allowed
	private MailSender(){
		//nothing
	}


	public static void init(String username , String password , String host , String port){
		try{
			if(username.equals("") || password.equals("") || host.equals("") || port.equals("")){
				throw new Exception(PROPERTIES_NOT_INITIALIZED);
			}

			USERNAME = username;
			PASSWORD = password;
			HOST = host;
			PORT = port;

			config();
		}
		catch(Exception ex){
			System.out.println("Error in init in MailSender");
			throw ex;
		}
	}


	public static void config() throws Exception{
		try{
			Properties props = System.getProperties();

		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", HOST);
		    props.put("mail.smtp.user", USERNAME);   //from
		    props.put("mail.smtp.password", PASSWORD);
		    props.put("mail.smtp.port", PORT);     //port
		    props.put("mail.smtp.auth", "true");

		    SESSION = Session.getDefaultInstance(props);
		}
		catch(Exception ex){
			System.out.println("Error in config in MailSender");
			throw ex;
		}
	}


	public static void sendMail(MailTemplate mailTemplate) throws Exception {
		try{
			MimeMessage message = new MimeMessage(SESSION);
			message.setFrom(new InternetAddress(USERNAME)); //from
			InternetAddress[] recipients = new InternetAddress[mailTemplate.recipients().length];

            // To get the array of addresses
            for(int i = 0; i < recipients.length; i++) {
                recipients[i] = new InternetAddress((mailTemplate.recipients())[i]);
            }

            for( int i = 0; i < recipients.length; i++) {
                message.addRecipient(Message.RecipientType.TO, recipients[i]);
            }

            message.setSubject(mailTemplate.subject());
            message.setContent(mailTemplate.body() , "text/html");

            Transport transport = SESSION.getTransport("smtp");
            transport.connect(HOST , USERNAME , PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
		}
		catch(Exception ex){
			System.out.println("Error in sendMail in MailSender");
			throw ex;
		}
	}



}