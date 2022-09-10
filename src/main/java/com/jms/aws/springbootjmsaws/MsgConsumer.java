package com.jms.aws.springbootjmsaws;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class MsgConsumer implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgConsumer.class);

    @Value("${cloud.aws.end-point.uri}")
    private String destination;

    // @JmsListener(destination = "${cloud.aws.end-point.uri}")
    // public void consumeMessage(String message) {
    // System.out.println(destination);
    // LOGGER.info("Received message: {}", message);
    // }

    @SqsListener("https://sqs.us-west-1.amazonaws.com/910629648812/MyQueue")
    public void loadMessageFromSQS(String message) {
        System.out.println(message);
        LOGGER.info("Recieved message from SQS queue: {}", message);
    }

    @Override
    public void onMessage(Message message) {
        // Cast the received message as TextMessage and print the text to screen.
        if (message != null) {
            System.out.println("Received: " + message);
        }

    }

}
