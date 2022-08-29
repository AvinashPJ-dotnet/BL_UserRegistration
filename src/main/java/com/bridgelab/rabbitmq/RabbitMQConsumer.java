package com.bridgelab.rabbitmq;

import org.springframework.stereotype.Component;

import com.bridgelab.service.EmailSenderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Component
public class RabbitMQConsumer {
	
	@Autowired
	EmailSenderService mailService;
	
	@Value("${senderMailId}")
	private String SENDER_MAIL_ID;
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = "javainuse.queue")
	@RabbitHandler
	public void recievedMessage(String toMailId) {
//		logger.info("Recieved Message From RabbitMQ: " + emailService);
		mailService.sendEmail("pjavinash42@gmail.com", toMailId, " test Spring mail sender","Test mail sender sent successfully");

	}
}
