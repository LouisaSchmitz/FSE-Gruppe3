package com.example.testManagement.adapter.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.model.UserStoryId;

@Table(name="USER_STORY")
public class UserStoryEntity {

	@Id
	int id;
	@Column(value = "STORYSTATUS")
	String storyStatus;
	@Column(value = "STORYPOINTS")
	int storyPoints;
	@Column(value = "STORYDESCRIPTION")
	String storyDescription;
	
	public UserStoryEntity() {
		
	}
	
	public UserStoryEntity(int id, String storyStatus, int storyPoints, String storyDescription) {
		super();
		this.id = id;
		this.storyStatus = storyStatus;
		this.storyPoints = storyPoints;
		this.storyDescription = storyDescription;
	}
	
	public UserStoryEntity(UserStory userStory) {
		this.id = userStory.getUserStoryId().getId();
		this.storyStatus = userStory.getStoryStatus().name();
		this.storyPoints = userStory.getStoryPoints();
		this.storyDescription = userStory.getStoryDescription();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void changeStoryStatus(String storyStatus) {
		this.storyStatus = storyStatus;
	}
	
	public String getStoryStatus() {
		return storyStatus;
	}
	
	public void rateUserStory (int storyPoints) {
		this.storyPoints = storyPoints;
	}
	
	public int getStoryPoints() {
		return storyPoints;
	}
	
	public void describeUserStory(String storyDescription) {
		this.storyDescription = storyDescription;
	}
	
	public String getStoryDescription() {
		return storyDescription;
	}
	
	public UserStory toDomain() {
		return new UserStory(new UserStoryId(id), storyStatus, storyPoints, storyDescription);
	}
}
