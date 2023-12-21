package com.example.testing.application;

import java.util.Collection;

import com.example.testing.domain.model.TestCase;
import com.example.testing.domain.model.TestCaseId;
import com.example.testing.domain.model.TestCaseTO;
import com.example.testing.domain.model.TestStatus;

public class TestCaseService implements ITestCaseService {
	
	private ITestCaseRepo testCaseRepo;
	
	public TestCaseService(ITestCaseRepo testCaseRepo) {
		this.testCaseRepo = testCaseRepo;
	}

	@Override
	public boolean changeStatusToApproved(int id) {
		
		try {
			TestCase testCase = testCaseRepo.findById(new TestCaseId(id));
			testCase.changeTestStatus(TestStatus.APPROVED);
			testCaseRepo.save(testCase);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean changeStatusToNotApproved(int id) {
		
		try {
			TestCase testCase = testCaseRepo.findById(new TestCaseId(id));
			testCase.changeTestStatus(TestStatus.NOT_APPROVED);
			testCaseRepo.save(testCase);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String showAllTestCases() {
		String allTestCases = "";
		
		Collection<TestCase> testCases = testCaseRepo.findAllTestCases();
		
		for(TestCase item : testCases) {
			if(item.getTestStatus().name() == "STARTED")
				allTestCases += item.toString();
		}
		
		return allTestCases;
	}

	@Override
	public boolean processTestCases(Collection<TestCaseTO> testCaseListeTO) {	
		for(TestCaseTO test : testCaseListeTO) {
			testCaseRepo.save(new TestCase(new TestCaseId(test.getId()), "STARTED", test.getTestDescription()));
		}
		
		return true;
	}
}