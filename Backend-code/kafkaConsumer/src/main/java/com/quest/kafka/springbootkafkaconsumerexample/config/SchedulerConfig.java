package com.quest.kafka.springbootkafkaconsumerexample.config;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.internals.Heartbeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.quest.kafka.springbootkafkaconsumerexample.listener.BP_Data;
import com.quest.kafka.springbootkafkaconsumerexample.listener.BP_Publisher;
import com.quest.kafka.springbootkafkaconsumerexample.listener.DataSender;
import com.quest.kafka.springbootkafkaconsumerexample.listener.HeartBeat_Data;
import com.quest.kafka.springbootkafkaconsumerexample.listener.HeartBeat_Publisher;
import com.quest.kafka.springbootkafkaconsumerexample.model.BP;
import com.quest.kafka.springbootkafkaconsumerexample.model.HeartBeat;
import com.quest.kafka.springbootkafkaconsumerexample.model.Patient;



@Configuration
@EnableScheduling

public class SchedulerConfig {

	
	private static DecimalFormat df = new DecimalFormat("0.00");
    boolean flag = false;
	Random objGenerator = new Random();
	  ExecutorService pool = Executors.newFixedThreadPool(10);
	  @Autowired
		SimpMessagingTemplate template ;
      @Autowired
      BP_Data bpData;
      @Autowired
      HeartBeat_Data hbData;

	
    @Scheduled(fixedRate = 3000)
    @MessageMapping("/message")
    public void sendValues() {
           flag = false;
            int i=0;
            int size = BP_Data.BP_values.size();
            int j =101;
                 while(j<201) {
                  if(!flag) {

                  List<BP> bpData1 = bpData.getBp(j);
                  List<HeartBeat> hbData1 = hbData.getHeartBeat(j);
                  pool.submit(new BP_Publisher(bpData1,j,template));
                  pool.submit(new HeartBeat_Publisher(hbData1,j,template));
                  
                    
                  }else {
                        break;
                  }
                  i++;
           }
                 pool.shutdown();
                // pool.awaitTermination(10000,)
    }
    
//    @SendTo("/topic/message12")
//    @MessageMapping("/send/message")
//    public void  getUser(Patient pat) {
//
//    	this.pat1 = pat;
//        template.convertAndSend("/topic/message12", "ABhijeet");
//
//    	sendValues();
//    }
    
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
              Collection<Object> cl = event.getMessage().getHeaders().values();
              String status = cl.iterator().next().toString();
              System.out.println("Status :"+status);
              if(status.equalsIgnoreCase("DISCONNECT")) {
                     flag = true;
              }
       }
 
       @MessageExceptionHandler
       public String handleException(Throwable exception) {
    	   template.convertAndSend("/errors", exception.getMessage());
              return exception.getMessage();
       }
   
}