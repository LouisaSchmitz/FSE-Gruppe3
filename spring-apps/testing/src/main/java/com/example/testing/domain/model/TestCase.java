package com.example.testing.domain.model;

public class TestCase {

	TestCaseId testCaseId;
	TestStatus testStatus;
	String testDescription;
	
	public TestCase(TestCaseId testCaseId, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = TestStatus.STARTED;
		this.testDescription = testDescription;
	}
	
	public TestCase(TestCaseId testCaseId, String testStatus, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = TestStatus.valueOf(testStatus);
		this.testDescription = testDescription;
	}
	
	public TestCase() {
	}

	public TestCaseId getTestCaseId() {
		return testCaseId;
	}
	
	public void changeTestStatus(TestStatus testStatus) {
		this.testStatus = testStatus;
	}
	
	public TestStatus getTestStatus() {
		return testStatus;
	}
	
	public void describeTest(String testDescription) {
		this.testDescription = testDescription;
	}
	
	public String getTestDescription() {
		return testDescription;
	}
	
	public int hashCode() {
		return testCaseId.getId();
	}
	
	public String toString() {
		return "------------\nTEST-CASE\nID: '" + this.testCaseId.getId() + "',\nTest-Status: '" + this.testStatus.toString() + "',\nTest-Beschreibung: '" + this.testDescription + "'\n";
	}
}