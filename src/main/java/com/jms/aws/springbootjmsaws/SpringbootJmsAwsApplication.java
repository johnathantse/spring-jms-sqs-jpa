package com.jms.aws.springbootjmsaws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.ConfigurableApplicationContext;

import com.amazonaws.services.sqs.AmazonSQSClient;

@SpringBootApplication()
@EnableSqs
public class SpringbootJmsAwsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootJmsAwsApplication.class);

	// @SqsListener("MyQueue")
	// public void loadMessageFromSQS(String message) {
	// LOGGER.info("Recieved message from SQS queue: {}", message);
	// }

	public static void main(String[] args) {
		// ConfigurableApplicationContext context =
		// SpringApplication.run(SpringbootJmsAwsApplication.class, args);
		SpringApplication.run(SpringbootJmsAwsApplication.class, args);
		// MsgConsumer msgConsumer = context.getBean(MsgConsumer.class);
		// msgConsumer.consumeMessage("Test2");
		// Get the wrapped client

		;
	}

}
