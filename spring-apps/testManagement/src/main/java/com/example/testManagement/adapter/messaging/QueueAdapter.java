package com.example.testManagement.adapter.messaging;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.TestCaseTO;
import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.service.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueueAdapter implements IMessageQueue{
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public QueueAdapter (KafkaTemplate<String, String> kafkaTemplate) {
    	this.kafkaTemplate = kafkaTemplate;
    }
    
    public boolean send(DomainEvent domainEvent) {
    	
    	String payload = "";
    	
    	//Überprüfung, ob es sich um das Event der Status-Änderung handelt
    	if (domainEvent.getEvent().equalsIgnoreCase("statusChanged")) {
    		
    		//Objekt des Events zu einem UserStory-Objekt transformieren
    		UserStory userStory = (UserStory)domainEvent.getObject();
    		Collection<TestCaseTO> testCaseList = new ArrayList<TestCaseTO>();
    		
    		//Iteration über alle TestCases
    		for (TestCase testCase : userStory.getAllTestCases())
    			testCaseList.add(new TestCaseTO(testCase));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
    		 
    		//Payload-String wird als JSON-String zusammengesetzt
    		try {
    			payload = objectMapper.writeValueAsString(testCaseList);  		
    		} catch (JsonProcessingException e) {
    			System.out.println("Interner Fehler");
    			e.printStackTrace();
    			return false;
    		}
    		
    		//Debug-Info in der Konsole wird ausgegeben
    		System.out.println("DEBUG INFO Payload: " + payload);   		
    	}
    	
    	//Das Event wird mit Kafka versendet
    	kafkaTemplate.send(domainEvent.getEvent(), payload);
    	return true;
    }
}
