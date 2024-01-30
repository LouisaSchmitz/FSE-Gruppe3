package com.example.testing.adapter.messaging;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.testing.application.ITestCaseService;
import com.example.testing.domain.model.TestCaseTO;

public class EventListener {

	//Definition der benötigten Interfaces
	private ITestCaseService testCaseService;

	public EventListener (ITestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}
	
	//Warten auf eine Nachricht mit dem Thema "stausChanged"
	@KafkaListener(topics = "statusChanged", groupId = "status")
	public void listen(String message) {
		System.out.println("DEBUGINFO Nachricht: " + message);

		//Initialisierung eines ObjectMappers um JSON in Java zu konvertieren
		ObjectMapper mapper = new ObjectMapper();
		TestCaseTO[] testCaseListeTOArray = null;
		
		try {
			//Konvertierung des Payloads in Java
			testCaseListeTOArray = mapper.readValue(message, TestCaseTO[].class);
		} catch (JsonProcessingException e) {
			System.out.println("Interner Fehler bei der Eventverarbeitung");
			e.printStackTrace();
		}
		
		Collection<TestCaseTO> testCaseListeTO = Arrays.asList(testCaseListeTOArray);
		
		//Verarbeitung des TestCases vom TestCsseService
		if (!testCaseService.processTestCases(testCaseListeTO))
			System.out.println("Verarbeitung fehlgeschlagen!"); 
	 }
}