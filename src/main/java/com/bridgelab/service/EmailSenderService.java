package com.bridgelab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String fromEmil, String toEmail, String subject, String body) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(fromEmil);
			mailMessage.setTo(toEmail);
			mailMessage.setSubject(subject);
			mailMessage.setText(body);
			
			mailSender.send(mailMessage);
			System.out.println("email sent successfully");

		} catch (MailParseException e) {
			// TODO: handle exception
		}
		catch (MailAuthenticationException e) {
			// TODO: handle exception
		}
		
		catch (MailSendException e) {
			// TODO: handle exception
		}
		
		catch (MailException e) {
			// TODO: handle exception
		}
		
		
	}
}
