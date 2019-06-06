package com.quest.kafka.springbootkafkaconsumerexample.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest.kafka.springbootkafkaconsumerexample.listener.BP_Data;
import com.quest.kafka.springbootkafkaconsumerexample.listener.HeartBeat_Data;
import com.quest.kafka.springbootkafkaconsumerexample.model.BP;
import com.quest.kafka.springbootkafkaconsumerexample.model.HeartBeat;
import com.quest.kafka.springbootkafkaconsumerexample.model.Patient;

@RestController
@RequestMapping("bphb")
public class BPHB_Resource {
	
	@Autowired
	BP_Data bpData;
	@Autowired
	HeartBeat_Data hbData;
	@GetMapping("/bp/{patientId}")
	public List<BP> getBp(@PathVariable("patientId") final int patientId) {
		return bpData.getBp(patientId);
	}
	
	@GetMapping("/heartBeat/{patientId}")
	public List<HeartBeat> getHb(@PathVariable("patientId") final int patientId) {
		return hbData.getHeartBeat(patientId);
	}

}
