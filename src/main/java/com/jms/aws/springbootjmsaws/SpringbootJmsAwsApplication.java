package com.jms.aws.springbootjmsaws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;

@SpringBootApplication()
@EnableSqs
public class SpringbootJmsAwsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootJmsAwsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJmsAwsApplication.class, args);

	}

}
