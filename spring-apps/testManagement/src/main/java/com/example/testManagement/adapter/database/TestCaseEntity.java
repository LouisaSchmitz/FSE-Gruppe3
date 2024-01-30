package com.example.testManagement.adapter.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.TestCaseId;

//Namens-Definition der Datenbank-Tabelle
@Table(name="TEST_CASE")
public class TestCaseEntity {

	//Festlegung der einzelnen Attribute und den zugehörigen Spalten-Namen in der Datenbank-Tabelle
	@Id
	int id;
	@Column(value = "TESTSTATUS")
	String testStatus;
	@Column(value = "TESTDESCRIPTION")
	String testDescription;
	@Column(value = "STORYID")
	int storyId;
	
	public TestCaseEntity() {
		
	}
	
	//Konstruktor um TestCase mit Infos aus Datenbank zu befüllen
	public TestCaseEntity(int testCaseId, String testStatus, String testDescription, int storyId) {
		this.id = testCaseId;
		this.testStatus = testStatus;
		this.testDescription = testDescription;
		this.storyId = storyId;
	}
	
	//Konstruktor um Entity-Objekt mit übergebenem TestCase zu erstellen
	public TestCaseEntity(TestCase testCase) {
		this.id = testCase.getTestCaseId().getId();
		this.testStatus = testCase.getTestStatus().name();
		this.testDescription = testCase.getTestDescription();
	}
	
	//Konstruktor um Entity-Objekt mit übergebenem TestCase + Story-ID zu erstellen
	public TestCaseEntity(TestCase testCase, int storyId) {
		this.id = testCase.getTestCaseId().getId();
		this.testStatus = testCase.getTestStatus().name();
		this.testDescription = testCase.getTestDescription();
		this.storyId = storyId;
	}
	
	//Getter für Entity-ID
	public int getTestCaseId() {
		return id;
	}
	
	//Änderung der Entity-ID
	public void setId(int id) {
		this.id = id;
	}
	
	//Änderung des Entity-Status
	public void changeTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	//Getter für Entity-Status
	public String getTestStatus() {
		return testStatus;
	}
	
	//Änderung der Entity-Beschreibung
	public void describeTest(String testDescription) {
		this.testDescription = testDescription;
	}
	
	//Getter für Entity-Beschreibung
	public String getTestDescription() {
		return testDescription;
	}
	
	//Getter für Story-ID
	public int getStoryId() {
		return storyId;
	}
	
	//TestCaseEntity in TestCase-Objekt umwandeln
	public TestCase toDomain() {
		return new TestCase(new TestCaseId(id), testStatus, testDescription);
	}
}
