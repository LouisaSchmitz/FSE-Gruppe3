package com.example.testing.adapter.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JDBCTestCaseEntityRepo extends CrudRepository<TestCaseEntity, Integer> {

}