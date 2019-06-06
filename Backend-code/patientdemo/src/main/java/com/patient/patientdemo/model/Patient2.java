package com.patient.patientdemo.model;

public class Patient2 {

    private String name;
    private int id;

    private double bp;
    private double heartBeat;
    public double getBp() {
		return bp;
	}

	public void setBp(double bp) {
		this.bp = bp;
	}

	public double getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(double heartBeat) {
		this.heartBeat = heartBeat;
	}

	

  

    public Patient2() {
    }

    public Patient2(String name, int id) {

        this.id = id;
    }

    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id='").append(id).append('\'');
        sb.append("bp=}'").append(bp);
        return sb.toString();
    }

	
}
