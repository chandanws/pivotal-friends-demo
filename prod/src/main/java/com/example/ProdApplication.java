package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(ProdInterfaces.class)
@SpringBootApplication
public class ProdApplication {

	private Logger logger = Logger.getLogger(ProdApplication.class);
	private MessageChannel mc;
	
	public ProdApplication(ProdInterfaces pi) {
		// TODO Auto-generated constructor stub
		this.mc = pi.prodOutput();
	}
	
	@PostMapping(value="/greet/{name}")
	public void send(@PathVariable String name){
		logger.info("Received from client: " + name);
		Message<String> msg = MessageBuilder.withPayload("Hello " + name).build();
		this.mc.send(msg);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProdApplication.class, args);
	}
}

interface ProdInterfaces{
	@Output
	public MessageChannel prodOutput();
}
