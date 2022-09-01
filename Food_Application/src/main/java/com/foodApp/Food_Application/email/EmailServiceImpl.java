package com.foodApp.Food_Application.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.foodApp.Food_Application.dto.FoodOrder;

public class EmailServiceImpl {
//	@Value("${spring.mail.host}")
//	String host;
//	@Value("${spring.mail.port}")
//	String port;
//	
//	@Value("${spring.mail.username}")
//	String username;
//	
//	@Value("${spring.mail.password}")
//	String password;
//	
//	
//	@Value("${spring.mail.properties.mail.smtp.auth}")
//	String auth;
//	
//	@Value("${spring.mail.smtp.ssl.enable}")
//	String ssl;
	
	public String sendSimpleMail(EmailDetails details) throws MessagingException {
		// TODO Auto-generated method stub
		
		
		Properties properties=System.getProperties();
		
		System.out.println("PROPERTIES :"+properties);
		
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable",true );
		properties.put("mail.smtp.auth", true);
		
		Session session=Session.getInstance(properties,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bamotriyasachin6@gmail.com","bamotriya9171726367");
			}
		});
		
		session.setDebug(true);
		
		MimeMessage  m = new MimeMessage(session);
		
		m.setFrom("bamotriyasachin6@gmail.com");
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(details.getRecipient()));
		
		m.setSubject(details.getSubject());
		m.setText(details.getMsgBody());
		
		Transport.send(m);
		System.out.println("Email Sent Successfully");
		return null;
	}
	
	public EmailDetails getEmailDetails(FoodOrder foodOrder) {
		EmailDetails details =new EmailDetails();
		details.setRecipient(foodOrder.getCustomerEmail());
		details.setSubject("Welcome to Food App");
		String Itemmsg=details.setEmail(foodOrder);
		details.setMsgBody(Itemmsg);
		return details;
		
	}
	 

}