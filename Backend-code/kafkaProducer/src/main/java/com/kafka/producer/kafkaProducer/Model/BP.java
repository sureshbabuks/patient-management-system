package com.kafka.producer.kafkaProducer.Model;

public class BP {

	private double bp;
	private int patientId;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public double getBp() {
		return bp;
	}
	public void setBp(double bp) {
		this.bp = bp;
	}
}
