package com.quest.kafka.springbootkafkaconsumerexample.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.quest.kafka.springbootkafkaconsumerexample.model.BP;
import com.quest.kafka.springbootkafkaconsumerexample.model.Patient;

@Component
public class BP_Data {

	public static  Map<Integer, List<BP>> BP_values = new HashMap<Integer,List<BP>>();
	public static List<Double> BP_values2 = new ArrayList<>();

	
	public  static void addBp(int patientId,BP bp) {
		List<BP> li ;
		if(!BP_values.containsKey(patientId)) {
			li = new ArrayList<BP>();
		}
		else
		{
	        li = BP_values.get(patientId);
		}
		li.add(bp);
		BP_values.put(patientId, li);
	}
	
	public   List<BP> getBp(int patientId) {
		return BP_values.get(patientId);
	}
	
	public  static void addBPToList(double HeartBeatValue) {
		BP_values2.add(HeartBeatValue);
	}
	
	public  static double getBPDataFromList(int index) {
		return BP_values2.get(index);
}
}
