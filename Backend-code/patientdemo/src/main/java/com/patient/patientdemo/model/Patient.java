package com.patient.patientdemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Patient")
public class Patient {

	@Id
	private int id;
	private String name;
	private int age;
	private String gender;
	
	public Patient(int id, String name, int age, String gender) {
		
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public Patient() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
