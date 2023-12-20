package com.example.testManagement.domain.model;

public class TestCaseTO {
	int id;
	String testStatus;
	String testDescription;
	
	public TestCaseTO(int id, String testStatus, String testDescription) {
		this.id = id;
		this.testStatus = testStatus;
		this.testDescription = testDescription;
	}
	
	public TestCaseTO(TestCase testCase) {
		this.id = testCase.getTestCaseId().getId();
		this.testStatus = testCase.getTestStatus().name();
		this.testDescription = testCase.getTestDescription();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTestStatus() {
		return testStatus;
	}
	
	public void setTestDescription(String description) {
		this.testDescription = description;
	}
	
	public String getTestDescription() {
		return testDescription;
	}
	
	public TestCase toDomain() {
		return new TestCase(new TestCaseId(this.id), this.testDescription);
	}
}
