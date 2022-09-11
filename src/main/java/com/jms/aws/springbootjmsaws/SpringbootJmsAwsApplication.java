package com.jms.aws.springbootjmsaws;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.ConfigurableApplicationContext;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.jms.aws.springbootjmsaws.sqs.SqsSender;

@SpringBootApplication()
@EnableSqs
@EnableAutoConfiguration
public class SpringbootJmsAwsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootJmsAwsApplication.class);

	public static void main(String[] args) {
		// SpringApplication.run(SpringbootJmsAwsApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(SpringbootJmsAwsApplication.class, args);
		SqsSender sender = context.getBean(SqsSender.class);
		try {
			sender.sendMessage("Test hello 2");
		} catch (IOException e) {

		}

		// MsgConsumer msgConsumer = context.getBean(MsgConsumer.class);
		// msgConsumer.consumeMessage("Test2");
		// Get the wrapped client

		;
	}

}
