package com.fils.glucose.application.password;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	final String username = "username@gmail.com";
    final String password = "password";

	public void sendEmail(String to, String password) {
		
		
		/*
		 * Properties prop = new Properties(); prop.put("mail.smtp.auth", true);
		 * prop.put("mail.smtp.starttls.enable", "true"); prop.put("mail.smtp.host",
		 * "smtp.gmail.com"); prop.put("mail.smtp.port", "587");
		 * prop.put("mail.smtp.starttls.enable", "true");
		 * 
		 * Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication(username, password); } });
		 * 
		 * try { Message message = new MimeMessage(session); message.setFrom(new
		 * InternetAddress("no-reply@glum.com"));
		 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		 * 
		 * message.setSubject("Your password for GluM Application");
		 * 
		 * String msg = "Hello! The password for your account is " + password;
		 * 
		 * MimeBodyPart mimeBodyPart = new MimeBodyPart(); mimeBodyPart.setContent(msg,
		 * "text/html");
		 * 
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(mimeBodyPart);
		 * 
		 * message.setContent(multipart);
		 * 
		 * Transport.send(message); } catch (MessagingException e) {
		 * e.printStackTrace(); }
		 */
	}

}
