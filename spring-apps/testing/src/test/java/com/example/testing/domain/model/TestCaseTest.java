package com.example.testing.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
		
		assertNotSame(testCase1, testCase2);
	}
	
	@Test
	public void testTestCaseLimits() throws Exception {
		boolean failed = false;
		
		try {
			new TestCase(new TestCaseId(-1), "");
			fail("Should have thrown no exception.");
		} catch (Throwable t) {
			failed = true;
		}
		
		assertTrue(failed);
		
		failed = false;
		
		try {
			new TestCase(new TestCaseId(1234567890), "");
			fail("Should have thrown no exception.");
		} catch (Throwable t) {
			failed = true;
		}
		
		assertTrue(failed);
	}
	
	@Test
	public void testChangeTestCaseDescription() throws Exception {
		testCase.describeTest("Test");
		assertEquals("Test", testCase.getTestDescription());
	}
}