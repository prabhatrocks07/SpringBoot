package com.mylearning.springboot.kafkaconsumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mylearning.springboot.kafkaconsumer.model.User;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "test", groupId="group_id")
	public void consume(String message) {
		System.out.println("Consumed Message: " + message);
	}
	
	@KafkaListener(topics = "test_json", groupId="group_json", containerFactory="userKafkaListenerContainerFactory")
	public void consumeJson(User user) {
		System.out.println("Consumed Json Message: " + user);
	}
}
