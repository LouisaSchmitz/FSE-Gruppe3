package com.example.testManagement.domain.model;

public class TestCaseTO {
	
	//Definition der Variablen
	int id;
	String testStatus;
	String testDescription;
	
	//Konstruktor um TO-Objekt mit einzelnen Parametern zu erstellen
	public TestCaseTO(int id, String testStatus, String testDescription) {
		this.id = id;
		this.testStatus = testStatus;
		this.testDescription = testDescription;
	}
	
	//Konstruktor um TO-Objekt mit übergebenem TestCase zu erstellen
	public TestCaseTO(TestCase testCase) {
		this.id = testCase.getTestCaseId().getId();
		this.testStatus = testCase.getTestStatus().name();
		this.testDescription = testCase.getTestDescription();
	}
	
	//Änderung der TO-ID
	public void setId(int id) {
		this.id = id;
	}
	
	//Getter für die TO-ID
	public int getId() {
		return id;
	}
	
	//Getter für den Test-Status
	public String getTestStatus() {
		return testStatus;
	}
	
	//Änderung der Test-Beschreibung
	public void setTestDescription(String description) {
		this.testDescription = description;
	}
	
	//Getter für die Test-Beschreibung
	public String getTestDescription() {
		return testDescription;
	}
	
	//TestTO-Objekt in ein TestCase-Objekt umwandeln
	public TestCase toDomain() {
		return new TestCase(new TestCaseId(this.id), this.testDescription);
	}
}
