package com.quest.kafka.springbootkafkaconsumerexample.listener;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.quest.kafka.springbootkafkaconsumerexample.model.BP;
import com.quest.kafka.springbootkafkaconsumerexample.model.HeartBeat;
import com.quest.kafka.springbootkafkaconsumerexample.model.Patient;

public class DataSender implements Runnable{

	
	List<BP>data;
	List<HeartBeat>data2;
	int patientId =0;
	SimpMessagingTemplate template;
	public DataSender(List<BP>data,int patientId, SimpMessagingTemplate template,List<HeartBeat>data2) {
		this.data=data;
		this.patientId = patientId;
		this.template= template;
		this.data2 = data2;
	}
	@Override
	public void run() {
		
		Iterator it = data.listIterator();
		while(it.hasNext()) {
		template.convertAndSend("/topic/heartBeat", it.next());
   // teplate.convertAndSend("/topic/heartBeat", it.next())

        template.convertAndSend("/topic/bp", it.next());
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //ze = BP_Data.BP_values2.size();		
	}
	}

}
