package com.bedi.ibmmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJms
public class IbmMqTestingApplication {

	@Autowired
	private JmsTemplate jmsTemplate;


	public static void main(String[] args) {
		SpringApplication.run(IbmMqTestingApplication.class, args);
	}

	@GetMapping("send")
	public String send() {

		try {
			jmsTemplate.convertAndSend("DEV.QUEUE.1", "hi this is a testing message ");
			return "Testing pass";
		} catch (JmsException e) {
			e.printStackTrace();
		}
		return "Testing Fail";
	}

	@GetMapping("recv")
	public String receive() {

		try {
			jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
			return "Recived message ";
		} catch (JmsException e) {
			e.printStackTrace();
			return "Testing recieved fail";
		}
	}
}
