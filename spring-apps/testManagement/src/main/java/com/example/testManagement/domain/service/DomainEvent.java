package com.example.testManagement.domain.service;

public class DomainEvent {
	
	//Definition der Variablen
	String event;
	Object object;
	
	//Konstruktor um ein DomainEvent zu erstellen
	public DomainEvent(String event, Object object) {
		super();
		this.event = event;
		this.object = object;
	}

	//Getter für das Event
	public String getEvent() {
		return event;
	}

	//Getter für das Domain-Objekt
	public Object getObject() {
		return object;
	}
}
