package com.quest.kafka.springbootkafkaconsumerexample.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.quest.kafka.springbootkafkaconsumerexample.model.HeartBeat;
import com.quest.kafka.springbootkafkaconsumerexample.model.Patient;

@Component
public class HeartBeat_Data {

private   Map<Integer,List<HeartBeat>> HeartBeat_values = new HashMap<>();
private  List<Double> HeartBeat_values2 = new ArrayList<>();

	
	public   void addHeartBeat(int patientId,HeartBeat hb) {
		List<HeartBeat> li ;

		if(!this.HeartBeat_values.containsKey(patientId)) {
			li = new ArrayList<HeartBeat>();
		}
		else {
		li =HeartBeat_values.get(patientId);
		}
		
		li.add(hb);
		HeartBeat_values.put(patientId,li);
	}
	
	public   List<HeartBeat> getHeartBeat(int patientId) {
		return this.HeartBeat_values.get(patientId);
}
	
	public   void addHeartBeatToList(double HeartBeatValue) {
		HeartBeat_values2.add(HeartBeatValue);
	}
	
	public   double getHeartBeatFromList(int index) {
		return HeartBeat_values2.get(index);
}
}