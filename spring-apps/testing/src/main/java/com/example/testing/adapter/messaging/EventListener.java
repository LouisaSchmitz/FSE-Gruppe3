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

	private static final String QUEUE_NAME = "changeStatus";

	private ITestCaseService testCaseService;

	public EventListener (ITestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}
	
	//RabbitMQ-Ports
	//15672
	//5672
	
	@RabbitListener(queues = QUEUE_NAME)
	public void listen(String message) {
		//System.out.println("DEBUGINFO Nachricht: " + message);
		 
		String parts[] = message.split(Pattern.quote("/"));
		 
		String event = parts[0];
		String payload = parts[1];
		 
		System.out.println("EVENT: " + event);
		System.out.println("PAYLOAD: " + payload);
		 
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		 
		if (event.equals("statusChanged")) {

			ObjectMapper mapper = new ObjectMapper();
			TestCaseTO[] testCaseListeTOArray = null;
			
			try {
				testCaseListeTOArray = mapper.readValue(payload, TestCaseTO[].class);
			} catch (JsonProcessingException e) {
				System.out.println("Interner Fehler bei der Eventverarbeitung");
				e.printStackTrace();
			}
			
			Collection<TestCaseTO> testCaseListeTO = Arrays.asList(testCaseListeTOArray);
			
			if (!testCaseService.processTestCases(testCaseListeTO))
				System.out.println("Verarbeitung fehlgeschlagen!"); 
				
		 }
	 }
}