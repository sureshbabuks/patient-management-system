package com.kafka.producer.kafkaProducer.resource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kafka.producer.kafkaProducer.Model.BP;
import com.kafka.producer.kafkaProducer.Model.HeartBeat;
import com.kafka.producer.kafkaProducer.Model.Patient;
@Component
public class ProduceBPHB {

	
	@Autowired
	KafkaTemplate<String,BP> bpKafkaTemplate;
	
	@Autowired
	KafkaTemplate<String,HeartBeat> hbKafkaTemplate;
	private String topic = "bp2";
	private String topic2 = "heartBeat2";

	ExecutorService pool = Executors.newFixedThreadPool(10);
	
	
	public void produceBP() {
		for(int j= 150; j<160;j++) {
		pool.submit(new PushToKafkaTask("bp2",hbKafkaTemplate,bpKafkaTemplate,j));
		
		
		}
		}
	
	
	public void produceHB() {
		for(int j= 150; j<160;j++) {
			pool.submit(new PushToKafkaTask("heartBeat2",hbKafkaTemplate,bpKafkaTemplate,j));

		}
	}
}
