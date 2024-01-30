package com.example.testManagement.adapter.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.testManagement.application.ITestCaseRepo;
import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.TestCaseId;
import com.example.testManagement.domain.model.UserStoryId;

@Component
public class DBTestCaseRepo implements ITestCaseRepo {
	
	private final JDBCTestCaseEntityRepo jdbcTestCaseEntityRepo;
	
	//Konstruktor für Injektion des JDBCRepos über Autowiring
	@Autowired
	public DBTestCaseRepo(JDBCTestCaseEntityRepo jdbcTestCaseEntityRepo) {
		this.jdbcTestCaseEntityRepo = jdbcTestCaseEntityRepo;
	}

	//Abruf eines TestCases anhand der ID
	@Override
	public TestCase findById(TestCaseId testCaseId) {
		//Versuch die Entity anhand der ID in der Datenbank zu finden
		Optional<TestCaseEntity> testCaseEntity = jdbcTestCaseEntityRepo.findById(testCaseId.getId());
		
		//Überprüfung ob Entity vorhanden ist
        if (testCaseEntity.isPresent()) {
            return testCaseEntity.get().toDomain();
        } else {
        	//Leere Rückgabe, wenn es keine TestCase-Entität gibt
            return null;
        }
	}

	//Abruf eines TestCases anhand der UserStory-ID
	@Override
	public Collection<TestCase> findByUserStoryId(UserStoryId userStoryId) {
		Collection<TestCase> testCases = new ArrayList<TestCase>();
		
		//Abruf aller TestCases
		Iterable<TestCaseEntity> testCasesEntity = jdbcTestCaseEntityRepo.findAll();
		
		//Iteration über alle TestCases und Überprüfung ob die UserSTory-ID übereinstimmt
		for (TestCaseEntity item : testCasesEntity) {
			if(item.getStoryId() == userStoryId.getId())
				testCases.add(item.toDomain());
		}
		
		return testCases;
	}

	//Speicherung des TestCases in der Datenbank
	@Override
	public void save(TestCase testCase, int storyId) {
		//Umwandlung des TestCases in eine Entity, um diese abspeichern zu können
		jdbcTestCaseEntityRepo.save(new TestCaseEntity(testCase, storyId));
	}
}
