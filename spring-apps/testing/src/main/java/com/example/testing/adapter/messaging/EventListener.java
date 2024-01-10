package com.example.testing.adapter.messaging;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.testing.application.ITestCaseService;
import com.example.testing.domain.model.TestCaseTO;

public class EventListener {

	private ITestCaseService testCaseService;

	public EventListener (ITestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}
	
	@KafkaListener(topics = "statusChanged", groupId = "status")
	public void listen(String message) {
		System.out.println("DEBUGINFO Nachricht: " + message);
		 
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		ObjectMapper mapper = new ObjectMapper();
		TestCaseTO[] testCaseListeTOArray = null;
		
		try {
			testCaseListeTOArray = mapper.readValue(message, TestCaseTO[].class);
		} catch (JsonProcessingException e) {
			System.out.println("Interner Fehler bei der Eventverarbeitung");
			e.printStackTrace();
		}
		
		Collection<TestCaseTO> testCaseListeTO = Arrays.asList(testCaseListeTOArray);
		
		if (!testCaseService.processTestCases(testCaseListeTO))
			System.out.println("Verarbeitung fehlgeschlagen!"); 
	 }
}