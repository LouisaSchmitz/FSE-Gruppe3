package com.example.testing.application;

import java.util.Collection;

import com.example.testing.domain.model.TestCaseTO;

public interface ITestCaseService {

	public boolean changeStatusToApproved(int id);
	public boolean changeStatusToNotApproved(int id);
	public boolean processTestCases (Collection<TestCaseTO> testCaseListeTO);
	public String showAllTestCases();
}