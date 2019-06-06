package com.kafka.producer.kafkaProducer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.kafkaProducer.Model.BP;
import com.kafka.producer.kafkaProducer.Model.HeartBeat;
import com.kafka.producer.kafkaProducer.Model.Patient;

@RestController
@RequestMapping("kafka")
public class BPResource {
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	KafkaTemplate<String,BP> bpKafkaTemplate;
	private String topic = "bp";
//	@GetMapping("/bp/{bp}")
//	public String post(@PathVariable("bp") final String value) {
//		bpKafkaTemplate.send(topic, value);
//		return "published successfully";
//	}
	
	@GetMapping("/bp/{patientId}/{bp}")
	public String post2(@PathVariable("bp") final int bp, @PathVariable("patientId") final int patientId) {
		BP pat = new BP();
		pat.setPatientId(patientId);
		pat.setBp(bp);
		bpKafkaTemplate.send(topic, pat);
		return "published successfully";
	}

}
