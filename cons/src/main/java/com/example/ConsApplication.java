package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(ConsInterfaces.class)
@SpringBootApplication
public class ConsApplication {

	private Logger logger = Logger.getLogger(ConsApplication.class);
	
	@StreamListener("consInput")
	public void receive(Message<String> msg){
		logger.info("Received: " + msg.getPayload());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConsApplication.class, args);
	}
}

interface ConsInterfaces {
	
	@Input
	public SubscribableChannel consInput();
}