package com.example.testManagement.adapter.messaging;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.TestCaseTO;
import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.service.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueueAdapter implements IMessageQueue{

	//Festlegung des Keys für die MessageQueue
    public final String key = "status.change.ready";
    //public final String key = "status.delete";
    
    @Autowired
    private final AmqpTemplate amqpTemplate;
    
    @Autowired
    private TopicExchange topic;
    
    public QueueAdapter (AmqpTemplate amqpTemplate) {
    	this.amqpTemplate = amqpTemplate;
    }
    
    public boolean send(DomainEvent domainEvent) {
    	
    	String payload = "";
    	
    	//Überprüfung, ob es sich um das Event der Status-Änderung handelt
    	if (domainEvent.getEvent().equalsIgnoreCase("statusChanged")) {
    		
    		//Objekt des Events wird zu einem UserStory-Objekt transformiert
    		UserStory userStory = (UserStory)domainEvent.getObject();
    		Collection<TestCaseTO> testCaseList = new ArrayList<TestCaseTO>();
    		
    		//Iteration über alle TestCases
    		for (TestCase testCase : userStory.getAllTestCases())
    			testCaseList.add(new TestCaseTO(testCase));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
    		
    		//Payload-String wird zusammengebaut
    		try {
    			payload = objectMapper.writeValueAsString(testCaseList);  		
    		} catch (JsonProcessingException e) {
    			System.out.println("Interner Fehler");
    			e.printStackTrace();
    			return false;
    		}
    		
    		//Debug-Info in der Konsole wird ausgegeben
    		System.out.println("DEBUG INFO Payload: " + payload + "\n Topic-Name: " + topic.getName() + "\nKey: " + key);   		
    	}
    	
    	//Das Event wird mit RabbitMQ an eine Queue gesendet und wartet dort auf die weitere Verarbeitung
    	amqpTemplate.convertAndSend(topic.getName(), key, domainEvent.getEvent() + "/" + payload);
    	return true;
    }
}
