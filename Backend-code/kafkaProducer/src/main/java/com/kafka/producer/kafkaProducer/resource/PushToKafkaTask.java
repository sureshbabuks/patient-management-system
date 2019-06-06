package com.kafka.producer.kafkaProducer.resource;

import org.springframework.kafka.core.KafkaTemplate;

import com.kafka.producer.kafkaProducer.Model.BP;
import com.kafka.producer.kafkaProducer.Model.HeartBeat;

public class PushToKafkaTask implements Runnable {
	
	KafkaTemplate<String,HeartBeat> hbKafkaTemplate;
	KafkaTemplate<String,BP> bpKafkaTemplate;
	String topic;
	int j ;

	public PushToKafkaTask(String topic, KafkaTemplate<String,HeartBeat> hbKafkaTemplate, KafkaTemplate<String,BP> bpKafkaTemplate, int j) {
		
		this.topic = topic;
		this.bpKafkaTemplate = bpKafkaTemplate;
		this.hbKafkaTemplate = hbKafkaTemplate;
		this.j = j;
	}
	@Override
	public void run() {

		if(topic.equals("bp2")) {
			for(int i=0;i<100; i++) {
				BP bp = new BP();
				bp.setPatientId(j);
				bp.setBp(Math.random() * 49 );
				bpKafkaTemplate.send(topic, bp);
		}
		}
			else if(topic.equals("heartBeat2")) {
				for(int i=0;i<100; i++) {
					HeartBeat bp = new HeartBeat();
					bp.setPatientId(j);
					bp.setHeartBeat(Math.random() * 49 );
					hbKafkaTemplate.send(topic, bp);
			}
			}
	}

}

