package com.example.testManagement.adapter.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.testManagement.application.IUserStoryRepo;
import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.model.UserStoryId;

@Component
public class DBUserStoryRepo implements IUserStoryRepo{
	
	private final JDBCUserStoryEntityRepo jdbcUserStoryEntityRepo;
	
	@Autowired
	public DBUserStoryRepo(JDBCUserStoryEntityRepo jdbcUserStoryEntityRepo) {
		this.jdbcUserStoryEntityRepo = jdbcUserStoryEntityRepo;
	}
	
	@Override
	public Collection<UserStory> findAll() {
		Collection<UserStory> userStories = new ArrayList<UserStory>();
		
		Iterable<UserStoryEntity> userStorysEntity = jdbcUserStoryEntityRepo.findAll();
		
		for (UserStoryEntity item : userStorysEntity) {
			userStories.add(item.toDomain());
		}
		
		return userStories;
	}

	@Override
	public UserStory findById(UserStoryId userStoryId) {
		Optional<UserStoryEntity> userStoryEntity = jdbcUserStoryEntityRepo.findById(userStoryId.getId());
        if (userStoryEntity.isPresent()) {
            return userStoryEntity.get().toDomain();
        } else {
            return null;
        }
	}

	@Override
	public void save(UserStory userStory) {
		jdbcUserStoryEntityRepo.save(new UserStoryEntity(userStory));
	}
}
