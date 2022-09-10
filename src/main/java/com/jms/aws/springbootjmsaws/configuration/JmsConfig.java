package com.jms.aws.springbootjmsaws.configuration;

import javax.jms.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
// import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Configuration
@EnableJms
public class JmsConfig {
    private final SQSConnectionFactory connectionFactory;

    public JmsConfig() {
        connectionFactory = SQSConnectionFactory.builder()
                // .withRegion(Region.getRegion(Regions.fromName(awsRegion)))
                // .withAWSCredentialsProvider(new AWSStaticCredentialsProvider(
                // new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }
    // public JmsConfig(
    // @Value("${cloud.aws.credentials.access-key}") String awsAccessKey,
    // @Value("${cloud.aws.credentials.secret-key}") String awsSecretKey,
    // @Value("${cloud.aws.region.static}") String awsRegion) {
    // connectionFactory = SQSConnectionFactory.builder()
    // // .withRegion(Region.getRegion(Regions.fromName(awsRegion)))
    // // .withAWSCredentialsProvider(new AWSStaticCredentialsProvider(
    // // new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
    // .build();
    // }

    // @Value("${cloud.aws.region.static}")
    // private String region;

    // @Value("${cloud.aws.credentials.access-key}")
    // private String awsAccessKey;

    // @Value("${cloud.aws.credentials.secret-key}")
    // private String awsSecretKey;

    // SQSConnectionFactory connectionFactory = SQSConnectionFactory.builder()
    // .withRegion(Region.getRegion(Regions.US_WEST_1))
    // .withAWSCredentialsProvider(new AWSStaticCredentialsProvider(
    // new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
    // .build();

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.connectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplate() {
        return new JmsTemplate(this.connectionFactory);
    }

}
