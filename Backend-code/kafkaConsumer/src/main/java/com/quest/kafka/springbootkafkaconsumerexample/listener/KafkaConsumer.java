package com.quest.kafka.springbootkafkaconsumerexample.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.quest.kafka.springbootkafkaconsumerexample.model.BP;
import com.quest.kafka.springbootkafkaconsumerexample.model.HeartBeat;
import com.quest.kafka.springbootkafkaconsumerexample.model.Patient;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics = "bp1", group = "group_id")
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//    }

	@Autowired
	HeartBeat_Data hbData ;
	@Autowired
	BP_Data bpData;

    @KafkaListener(topics = "bp2", group = "group_json",
            containerFactory = "bpKafkaListenerFactory")
    public void consumeJson(BP bp) {
        System.out.println("Consumed JSON Message: " + bp.getPatientId()+" bp: "+bp.getBp());

        try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BP_Data.addBp(bp.getPatientId(), bp);
		BP_Data.addBPToList(bp.getBp());
    }
    
    @KafkaListener(topics = "heartBeat2", group = "group_json",
            containerFactory = "hbKafkaListenerFactory")
    public void consumeJson2(HeartBeat hb) {
        System.out.println("Consumed JSON Message: " + hb.getPatientId()+" bp: "+hb.getHeartBeat());

        try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        hbData.addHeartBeat(hb.getPatientId(), hb);
        hbData.addHeartBeatToList(hb.getHeartBeat());
    }
}
