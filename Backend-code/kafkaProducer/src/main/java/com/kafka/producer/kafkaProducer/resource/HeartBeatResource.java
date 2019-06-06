package com.kafka.producer.kafkaProducer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.kafkaProducer.Model.HeartBeat;
import com.kafka.producer.kafkaProducer.Model.Patient;

@RestController
@RequestMapping("kafka")
public class HeartBeatResource {

	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	@Autowired
	KafkaTemplate<String,HeartBeat> heartBeatkafkaTemplate;
	private String topic = "bp";
	private String topic2 = "heartBeat";
	
	@GetMapping("/hb/{heartBeat}")
	public String post(@PathVariable("heartBeat") final String value) {
		kafkaTemplate.send(topic, value);
		return "published successfully";
	}
	
	@GetMapping("/hb/{patientId}/{hb}")
	public String post2(@PathVariable("hb") final int hb, @PathVariable("patientId") final int patientId) {
		HeartBeat pat = new HeartBeat();
		pat.setPatientId(patientId);
		pat.setHeartBeat(hb);
		heartBeatkafkaTemplate.send(topic2, pat);
		return "published successfully";
	}
}
