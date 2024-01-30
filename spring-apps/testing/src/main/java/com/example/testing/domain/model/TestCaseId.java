package com.example.testing.domain.model;

public class TestCaseId {
	int id;
	
	public TestCaseId(int id) {
		super();
		this.id = id;
	}

	//Getter der TestCase-ID
	public int getId() {
		return id;
	}
	
	//Überprüfung des übergebenen Objekts auf Gleichheit
	public boolean equals (Object o) {
		TestCaseId a = (TestCaseId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}