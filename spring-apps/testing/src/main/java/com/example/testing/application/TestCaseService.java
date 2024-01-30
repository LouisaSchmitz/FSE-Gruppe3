package com.example.testing.application;

import java.util.Collection;

import com.example.testing.domain.model.TestCase;
import com.example.testing.domain.model.TestCaseId;
import com.example.testing.domain.model.TestCaseTO;
import com.example.testing.domain.model.TestStatus;

public class TestCaseService implements ITestCaseService {
	
	//Definition der benötigten Interfaces
	private ITestCaseRepo testCaseRepo;
	
	//Konstruktor zur Initialisierung der Interfaces
	public TestCaseService(ITestCaseRepo testCaseRepo) {
		this.testCaseRepo = testCaseRepo;
	}

	//Änderung des Test-Status auf "Approved"
	@Override
	public boolean changeStatusToApproved(int id) {
		
		try {
			//Abruf des TestCases anhand der ID
			TestCase testCase = testCaseRepo.findById(new TestCaseId(id));
			
			//Änderung des Status und Speicherung des aktualisierten Status
			testCase.changeTestStatus(TestStatus.APPROVED);
			testCaseRepo.save(testCase);
			
			return true;
		} catch (Exception e) {
			//Fehlerhandling
			e.printStackTrace();
			return false;
		}
	}
	
	//Änderung des Status auf "Not Approved"
	@Override
	public boolean changeStatusToNotApproved(int id) {
		
		try {
			//Abruf des TestCases anhand der ID
			TestCase testCase = testCaseRepo.findById(new TestCaseId(id));
			
			//Änderung des Status und Speicherung des aktualisierten Status
			testCase.changeTestStatus(TestStatus.NOT_APPROVED);
			testCaseRepo.save(testCase);
			
			return true;
		} catch (Exception e) {
			//Fehlerhandling
			e.printStackTrace();
			return false;
		}
	}

	//Anzeige aller TestCases mit dem Status "Started"
	@Override
	public String showAllTestCases() {
		String allTestCases = "";
		
		//Abruf aller TestCases aus der Datenbank
		Collection<TestCase> testCases = testCaseRepo.findAllTestCases();
		
		//Iteration durch alle TestCases und Hinzufügen zur Ausgabe, wenn Status "Started" ist
		for(TestCase item : testCases) {
			if(item.getTestStatus().name() == "STARTED")
				allTestCases += item.toString();
		}
		
		return allTestCases;
	}

	//Verarbeitung von TestCases
	@Override
	public boolean processTestCases(Collection<TestCaseTO> testCaseListeTO) {	
		//Iteration über alle TestCases und Abspeicherung mit geändertem Status
		for(TestCaseTO test : testCaseListeTO) {
			testCaseRepo.save(new TestCase(new TestCaseId(test.getId()), "STARTED", test.getTestDescription()));
		}
		
		return true;
	}
}