package com.kafka.producer.kafkaProducer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
	
	@Autowired
	  ProduceBPHB p1 ;
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {

	  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p1.produceBP();
		p1.produceHB();
  }
}