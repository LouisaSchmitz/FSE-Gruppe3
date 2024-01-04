package com.example.testing.adapter.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.testing.application.ITestCaseRepo;
import com.example.testing.domain.model.TestCase;
import com.example.testing.domain.model.TestCaseId;

@Component
public class DBTestCaseRepo implements ITestCaseRepo {
	
	private final JDBCTestCaseEntityRepo jdbcTestCaseEntityRepo;
	
	@Autowired
	public DBTestCaseRepo(JDBCTestCaseEntityRepo jdbcTestCaseEntityRepo) {
		this.jdbcTestCaseEntityRepo = jdbcTestCaseEntityRepo;
	}
	
	@Override
	public TestCase findById(TestCaseId testCaseId) {
		Optional<TestCaseEntity> testCaseEntity = jdbcTestCaseEntityRepo.findById(testCaseId.getId());
        if (testCaseEntity.isPresent()) {
            return testCaseEntity.get().toDomain();
        } else {
            return null;
        }
	}

	@Override
	public Collection<TestCase> findAllTestCases() {
		Collection<TestCase> testCases = new ArrayList<TestCase>();	
		Iterable<TestCaseEntity> testCasesEntity = jdbcTestCaseEntityRepo.findAll();
		
		for (TestCaseEntity item : testCasesEntity) {
			testCases.add(item.toDomain());
		}	
		return testCases;
	}

	@Override
	public void save(TestCase testCase) {
		jdbcTestCaseEntityRepo.save(new TestCaseEntity(testCase));
	}
}