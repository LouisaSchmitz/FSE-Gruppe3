package com.example.testing.adapter.messaging;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

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
	
	@RabbitListener(queues = "#{statusQueue.name}")
	public void listen(String message) {
		//System.out.println("DEBUGINFO Nachricht: " + message);
		
		//Trennung der Nachricht anhand des Trennzeichens"/"
		String parts[] = message.split(Pattern.quote("/"));
		
		//Extraktion von Event und Payload 
		String event = parts[0];
		String payload = parts[1];
		 
		System.out.println("EVENT: " + event);
		System.out.println("PAYLOAD: " + payload);
		
		//Überprüfung des Event-Typs 
		if (event.equals("statusChanged")) {

			//Initialisierung eines ObjectMappers um JSON in Java zu konvertieren
			ObjectMapper mapper = new ObjectMapper();
			TestCaseTO[] testCaseListeTOArray = null;
			
			try {
				//Konvertierung des Payloads in Java
				testCaseListeTOArray = mapper.readValue(payload, TestCaseTO[].class);
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
}