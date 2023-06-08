//package com.user.management.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducer {
//	
//	private final Logger LOGGER=LoggerFactory.getLogger(KafkaProducer.class);
//	
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//	
//	public void sendMessage(String message) {
//		kafkaTemplate.send("users", message);
//		LOGGER.info(String.format("message sent %s", message));
//	}
//	
//}
