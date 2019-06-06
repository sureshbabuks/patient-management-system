package com.quest.kafka.springbootkafkaconsumerexample.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.quest.kafka.springbootkafkaconsumerexample.model.Patient;

@RestController
public class PatientController {


	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/addPatient")
	public String savePatient(@RequestBody Patient patient) {
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Patient> entity = new HttpEntity<Patient>(patient,headers);
	      
	      return restTemplate.exchange("http://localhost:8080/addPatient", HttpMethod.POST, entity, String.class).getBody();
	
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getPatient/{id}")
	public Patient getPatientById(@PathVariable int id) {
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8080/getPatient/"+id, HttpMethod.GET, entity, Patient.class).getBody();
	   }
	   
	
	
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllPatients")
	public List<Patient> getAllPAtients() {
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8080/getAllPatients", HttpMethod.GET, entity, List.class).getBody();
	
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deletePatient/{id}")
	public void deletePatientById(@PathVariable int id) {
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
		
		 restTemplate.delete("http://localhost:8080/deletePatient"+id);
	}
}
