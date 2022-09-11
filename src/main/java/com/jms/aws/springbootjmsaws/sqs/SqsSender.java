package com.jms.aws.springbootjmsaws.sqs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SqsSender {

    @Autowired
    private JmsTemplate defaultJmsTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String queueDestination;

    public void sendMessage(String message) throws IOException {
        System.out.println(queueDestination);
        defaultJmsTemplate.convertAndSend("MyQueue", message);
    }
}
