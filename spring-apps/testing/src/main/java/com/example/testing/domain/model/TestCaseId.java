package com.example.testing.domain.model;

public class TestCaseId {

int id;
	
	public TestCaseId(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals (Object o) {
		TestCaseId a = (TestCaseId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}