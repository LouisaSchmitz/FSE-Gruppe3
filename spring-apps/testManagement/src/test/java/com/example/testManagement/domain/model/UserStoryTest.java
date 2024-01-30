package com.example.testManagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

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
		
		//Überprüfung, dass die beiden Objekt nicht identisch sind
		assertNotSame(userStory1, userStory2);
	}
	
	@Test
	public void testChangeUserStoryStatus() throws Exception {
		//Änderung des Status und Überprüfung des aktuellen Status
		userStory.changeStoryStatus(StoryStatus.READY_FOR_TEST);
		assertEquals("ready for test", userStory.getStoryStatus().toString());
	}
	
	@Test
	public void testChangeUserStoryPoints() throws Exception {
		//Änderung der StoryPoints und Überprüfung auf Gleichheit
		userStory.rateUserStory(5);
		assertEquals(5, userStory.getStoryPoints());
	}
	
	@Test
	public void testChangeUserStoryDescription() throws Exception {
		//Änderung der Beschreibung und Überprüfung der aktuellen Beschreibung
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
		
		//Überprüfung, dass die beiden Objekt nicht identisch sind
		assertNotSame(testCase1, testCase2);
	}
	
	@Test
	public void testChangeTestCaseStatus() throws Exception {
		//Änderung des Status und Überprüfung des aktuellen Status
		testCase.changeTestStatus(StoryStatus.READY_FOR_TEST);
		assertEquals("ready for test", testCase.getTestStatus().toString());
	}
	
	@Test
	public void testChangeTestCaseDescription() throws Exception {
		//Änderung der Beschreibung und Überprüfung der aktuellen Beschreibung
		testCase.describeTest("Test");
		assertEquals("Test", testCase.getTestDescription());
	}
	
}