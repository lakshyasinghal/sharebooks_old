package com.sharebooks.mail.interfaces;




public interface MailTemplate {
	

	public String[] recipients();
	public String subject();
	public String body();
}
