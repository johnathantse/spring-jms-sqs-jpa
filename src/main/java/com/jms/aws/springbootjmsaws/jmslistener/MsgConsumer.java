package com.jms.aws.springbootjmsaws.jmslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgConsumer.class);

    @SqsListener("https://sqs.us-west-1.amazonaws.com/910629648812/MyQueue")
    public void loadMessageFromSQS(String message) {
        LOGGER.info("Received message from SQS queue: {}", message);
    }

}
