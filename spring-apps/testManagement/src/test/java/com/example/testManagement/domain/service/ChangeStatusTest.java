package com.example.testManagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testManagement.application.ITestCaseRepo;
import com.example.testManagement.application.IUserStoryRepo;
import com.example.testManagement.domain.model.StoryStatus;
import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.TestCaseId;
import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.model.UserStoryId;

@SpringBootTest
public class ChangeStatusTest {

	@Autowired
	private IUserStoryRepo userStoryRepo;
	
	@Autowired
	private ITestCaseRepo testCaseRepo;
	
	@Test
	public void testChangeStatus() throws Exception {
		UserStory userStory1 = new UserStory(new UserStoryId(99), 99, "Test");
		TestCase testCase1 = new TestCase(new TestCaseId(99), "Test");
		
		userStory1.changeStoryStatus(StoryStatus.READY_FOR_TEST);
		userStoryRepo.save(userStory1);
		
		testCase1.changeTestStatus(StoryStatus.READY_FOR_TEST);
		testCaseRepo.save(testCase1, 99);
		
		
		UserStory userStory2 = userStoryRepo.findById(userStory1.getUserStoryId());
		TestCase testCase2 = new TestCase();
		
		Collection<TestCase> testCases = testCaseRepo.findByUserStoryId(userStory1.getUserStoryId());
		
		for(TestCase testCase : testCases)
			testCase2 = testCase;
		
		assertEquals("ready for test", userStory2.getStoryStatus().toString());
		assertEquals("ready for test", testCase2.getTestStatus().toString());
		
		//MessageQueue checken?
	}
}