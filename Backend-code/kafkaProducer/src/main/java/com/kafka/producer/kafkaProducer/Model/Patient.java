package com.kafka.producer.kafkaProducer.Model;

public class Patient {

	
	private int id;
	private double heartBEat;
	private double bp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public double getBp() {
		return bp;
	}
	public void setBp(double d) {
		this.bp = d;
	}
	public double getHeartBEat() {
		return heartBEat;
	}
	public void setHeartBEat(double heartBEat) {
		this.heartBEat = heartBEat;
	}
	
}
