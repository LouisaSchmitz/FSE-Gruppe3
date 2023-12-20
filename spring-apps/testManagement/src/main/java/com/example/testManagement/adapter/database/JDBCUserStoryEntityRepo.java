package com.example.testManagement.adapter.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JDBCUserStoryEntityRepo extends CrudRepository<UserStoryEntity, Integer> {

}
