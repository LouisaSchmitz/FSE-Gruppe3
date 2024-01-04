package com.example.testManagement.domain.model;

public class TestCase {
	TestCaseId testCaseId;
	StoryStatus testStatus;
	String testDescription;
	
	public TestCase(TestCaseId testCaseId, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = StoryStatus.IN_PROGRESS;
		this.testDescription = testDescription;
	}
	
	public TestCase(TestCaseId testCaseId, String testStatus, String testDescription) {
		this.testCaseId = testCaseId;
		this.testStatus = StoryStatus.valueOf(testStatus);
		this.testDescription = testDescription;
	}
	
	public TestCase() {
	}

	public TestCaseId getTestCaseId() {
		return testCaseId;
	}
	
	public void changeTestStatus(StoryStatus testStatus) {
		this.testStatus = testStatus;
	}
	
	public StoryStatus getTestStatus() {
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
		return "------------\n  → TEST-CASE\n  ID: '" + this.testCaseId.getId() + "',\n  Test-Status: '" + this.testStatus.toString() + "',\n  Test-Beschreibung: '" + this.testDescription + "'\n";
	}
}
