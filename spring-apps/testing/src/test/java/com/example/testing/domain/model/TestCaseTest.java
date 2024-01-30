package com.example.testing.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCaseTest {
	
	private TestCase testCase = new TestCase();
	
	/*
	 * TestCase Tests
	 */
	
	@Test
	public void testTestCaseEquals() throws Exception {
		TestCase testCase1 = new TestCase(new TestCaseId(100), "Test");
		TestCase testCase2 = new TestCase(new TestCaseId(100), "Test");
		
		//Überprüfung, dass die beiden Objekte nicht identisch sind
		assertNotSame(testCase1, testCase2);
	}
	
	@Test
	public void testChangeTestCaseStatus() throws Exception {
		//Änderung des Status und Überprüfung des aktuellen Status
		testCase.changeTestStatus(TestStatus.APPROVED);
		assertEquals("approved", testCase.getTestStatus().toString());
	}
	
	@Test
	public void testChangeTestCaseDescription() throws Exception {
		//Änderung der Beschreibung und Überprüfung der aktuellen Beschreibung
		testCase.describeTest("Test");
		assertEquals("Test", testCase.getTestDescription());
	}
}