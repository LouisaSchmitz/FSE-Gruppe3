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
    	
    	if (domainEvent.getEvent().equalsIgnoreCase("statusChanged")) {
    		
    		UserStory userStory = (UserStory)domainEvent.getObject();
    		Collection<TestCaseTO> testCaseList = new ArrayList<TestCaseTO>();
    		
    		for (TestCase testCase : userStory.getAllTestCases())
    			testCaseList.add(new TestCaseTO(testCase));
    		
    		ObjectMapper objectMapper = new ObjectMapper();
    		 
    		try {
    			payload = objectMapper.writeValueAsString(testCaseList);  		
    		} catch (JsonProcessingException e) {
    			// TODO Auto-generated catch block
    			// die folgende Meldung gehört eigentlich in ein Log
    			System.out.println("Interner Fehler");
    			e.printStackTrace();
    			return false;
    		}
    		
    		System.out.println("DEBUG INFO Payload: " + payload + "\n Topic-Name: " + topic.getName() + "\nKey: " + key);   		
    	}
    	
    	amqpTemplate.convertAndSend(topic.getName(), key, domainEvent.getEvent() + "/" + payload);
    	return true;
    }
}
