package com.jms.aws.springbootjmsaws.sqs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqsProducer {

    @Autowired
    private JmsTemplate defaultJmsTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String queueDestination;

    public void sendMessage(String message) {
        defaultJmsTemplate.convertAndSend("MyQueue", message);

    }
}
