package com.example.testing.domain.model;

public class TestCase {

	//Definition der Variablen
	TestCaseId testCaseId;
	TestStatus testStatus;
	String testDescription;
	
	//Konstruktor zur Neu-Erstellung (Status wird sofort auf "Started" gesetzt)
	public TestCase(TestCaseId testCaseId, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = TestStatus.STARTED;
		this.testDescription = testDescription;
	}
	
	//Konstruktor um TestCase mit Infos aus Datenbank zu befüllen
	public TestCase(TestCaseId testCaseId, String testStatus, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = TestStatus.valueOf(testStatus);
		this.testDescription = testDescription;
	}
	
	//Getter für TestCase-ID
	public TestCaseId getTestCaseId() {
		return testCaseId;
	}
	
	//Änderung des Test-Status
	public void changeTestStatus(TestStatus testStatus) {
		this.testStatus = testStatus;
	}
	
	//Getter für den Test-Status
	public TestStatus getTestStatus() {
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
		return "------------\nTEST-CASE\nID: '" + this.testCaseId.getId() + "',\nTest-Status: '" + this.testStatus.toString() + "',\nTest-Beschreibung: '" + this.testDescription + "'\n";
	}
}