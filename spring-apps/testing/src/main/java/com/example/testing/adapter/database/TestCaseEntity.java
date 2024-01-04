package com.example.testing.adapter.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.testing.domain.model.TestCase;
import com.example.testing.domain.model.TestCaseId;

@Table(name="TEST_CASE")
public class TestCaseEntity {

	@Id
	int id;
	@Column(value = "TESTSTATUS")
	String testStatus;
	@Column(value = "TESTDESCRIPTION")
	String testDescription;
	
	public TestCaseEntity() {
		
	}
	
	public TestCaseEntity(int testCaseId, String testStatus, String testDescription) {
		this.id = testCaseId;
		this.testStatus = testStatus;
		this.testDescription = testDescription;
	}
	
	public TestCaseEntity(TestCase testCase) {
		this.id = testCase.getTestCaseId().getId();
		this.testStatus = testCase.getTestStatus().name();
		this.testDescription = testCase.getTestDescription();
	}
	
	public int getTestCaseId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void changeTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	public String getTestStatus() {
		return testStatus;
	}
	
	public void describeTest(String testDescription) {
		this.testDescription = testDescription;
	}
	
	public String getTestDescription() {
		return testDescription;
	}
	
	public TestCase toDomain() {
		return new TestCase(new TestCaseId(id), testStatus, testDescription);
	}
}