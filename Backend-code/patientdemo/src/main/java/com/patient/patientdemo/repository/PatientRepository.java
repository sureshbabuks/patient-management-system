package com.patient.patientdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.patient.patientdemo.model.Patient;

public interface PatientRepository extends MongoRepository<Patient,Integer>{

}
