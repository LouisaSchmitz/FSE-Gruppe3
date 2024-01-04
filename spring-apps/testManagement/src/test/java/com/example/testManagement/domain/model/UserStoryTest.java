package com.example.testManagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserStoryTest {
	
	private UserStory userStory = new UserStory();
	private TestCase testCase = new TestCase();
	
	/*
	 * UserStory Tests
	 */
	@Test
	public void testUserStoryEquals() throws Exception {
		UserStory userStory1 = new UserStory(new UserStoryId(100), 10, "Test");
		UserStory userStory2 = new UserStory(new UserStoryId(100), 10, "Test");
		
		assertNotSame(userStory1, userStory2);
	}
	
	@Test
	public void testUserStoryLimits() throws Exception {
		boolean failed = false;
		
		try {
			new UserStory(new UserStoryId(-1), -1, "");
			fail("Should have thrown no exception.");
		} catch (Throwable t) {
			failed = true;
		}
		
		assertTrue(failed);
		
		failed = false;
		
		try {
			new UserStory(new UserStoryId(1234567890), 1234567890, "");
			fail("Should have thrown no exception.");
		} catch (Throwable t) {
			failed = true;
		}
		
		assertTrue(failed);
	}
	
	@Test
	public void testChangeUserStoryStatus() throws Exception {
		userStory.changeStoryStatus(StoryStatus.READY_FOR_TEST);
		assertEquals("ready for test", userStory.getStoryStatus().toString());
	}
	
	@Test
	public void testChangeUserStoryPoints() throws Exception {
		userStory.describeUserStory("Test");
		assertEquals("Test", userStory.getStoryDescription());
	}
	
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
	public void testChangeTestCaseStatus() throws Exception {
		testCase.changeTestStatus(StoryStatus.READY_FOR_TEST);
		assertEquals("ready for test", testCase.getTestStatus().toString());
	}
	
	@Test
	public void testChangeTestCaseDescription() throws Exception {
		testCase.describeTest("Test");
		assertEquals("Test", testCase.getTestDescription());
	}
	
}