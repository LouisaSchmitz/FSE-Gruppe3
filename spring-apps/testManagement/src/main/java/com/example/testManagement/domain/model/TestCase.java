package com.example.testManagement.domain.model;

public class TestCase {
	
	//Definition der Variablen
	TestCaseId testCaseId;
	StoryStatus testStatus;
	String testDescription;
	
	//Konstruktor zur Neu-Erstellung (Status wird sofort auf "In-Progress" gesetzt)
	public TestCase(TestCaseId testCaseId, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = StoryStatus.IN_PROGRESS;
		this.testDescription = testDescription;
	}
	
	//Konstruktor um TestCase mit Infos aus Datenbank zu befüllen
	public TestCase(TestCaseId testCaseId, String testStatus, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = StoryStatus.valueOf(testStatus);
		this.testDescription = testDescription;
	}
	
	public TestCase() {
	}

	//Getter für TestCase-ID
	public TestCaseId getTestCaseId() {
		return testCaseId;
	}
	
	//Änderung des Test-Status
	public void changeTestStatus(StoryStatus testStatus) {
		this.testStatus = testStatus;
	}
	
	//Getter für den Test-Status
	public StoryStatus getTestStatus() {
		return testStatus;
	}
	
	//Änderung der Test-Beschreibung
	public void describeTest(String testDescription) {
		this.testDescription = testDescription;
	}
	
	//Getter für die Test-Beschreibung
	public String getTestDescription() {
		return testDescription;
	}
	
	//Getter für den Hash-Code
	public int hashCode() {
		return testCaseId.getId();
	}
	
	//Konsolen-Ausgabe des Test-Case mit allen Attributen als String
	public String toString() {
		return "------------\n  → TEST-CASE\n  ID: '" + this.testCaseId.getId() + "',\n  Test-Status: '" + this.testStatus.toString() + "',\n  Test-Beschreibung: '" + this.testDescription + "'\n";
	}
}
