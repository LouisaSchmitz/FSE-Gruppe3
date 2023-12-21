package com.example.testing.application;

import java.util.Collection;

import com.example.testing.domain.model.TestCase;
import com.example.testing.domain.model.TestCaseId;

public interface ITestCaseRepo {

	public TestCase findById(TestCaseId testCaseId);
	public Collection<TestCase> findAllTestCases();
	public void save(TestCase testCase);
}