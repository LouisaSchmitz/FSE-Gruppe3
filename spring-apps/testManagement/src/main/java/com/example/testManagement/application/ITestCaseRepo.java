package com.example.testManagement.application;

import java.util.Collection;

import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.TestCaseId;
import com.example.testManagement.domain.model.UserStoryId;

public interface ITestCaseRepo {
	public TestCase findById(TestCaseId testCaseId);
	public Collection<TestCase> findByUserStoryId(UserStoryId userStoryId);
	public void save(TestCase testCase, int storyId);
}
