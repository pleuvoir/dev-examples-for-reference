package io.github.pleuvoir.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {
	
	private static final String PUB_QQ_EMAIL_USERNAME = "pub_email_sender@foxmail.com";
	private static final String PUB_QQ_EMAIL_PASSWORD = "haxbhctpblpdbcfe";
	
	public static void sendTextToMe(String subject, String message) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.qq.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(PUB_QQ_EMAIL_USERNAME, PUB_QQ_EMAIL_PASSWORD));
		email.setSSLOnConnect(true);
		email.setCharset("UTF-8");
		email.setFrom(PUB_QQ_EMAIL_USERNAME);
		email.setSubject(subject);
		email.setMsg(message);
		email.addTo("pleuvior@foxmail.com");
		email.send();
	}

	public static void sendText(String toUsername, String subject, String message) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.qq.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(PUB_QQ_EMAIL_USERNAME, PUB_QQ_EMAIL_PASSWORD));
		email.setSSLOnConnect(true);
		email.setCharset("UTF-8");
		email.setFrom(PUB_QQ_EMAIL_USERNAME);
		email.setSubject(subject);
		email.setMsg(message);
		email.addTo(toUsername);
		email.send();
	}
		//common-qq-email-sender  2979270899
	
}
