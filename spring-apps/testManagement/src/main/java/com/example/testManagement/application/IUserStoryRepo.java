package com.example.testManagement.application;

import java.util.Collection;

import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.model.UserStoryId;

public interface IUserStoryRepo {
	public Collection<UserStory> findAll();
	public UserStory findById(UserStoryId userStoryId);
	public void save(UserStory userStory);
}
