package com.example.testManagement.domain.model;

import java.util.ArrayList;
import java.util.Collection;

public class UserStory {
	UserStoryId userStoryId;
	StoryStatus storyStatus;
	int storyPoints;
	String storyDescription;
	Collection<TestCase> testCases;
	
	public UserStory(UserStoryId userStoryId, int storyPoints, String storyDescription) {
		this.userStoryId = userStoryId;
		this.storyStatus = StoryStatus.IN_PROGRESS;
		this.storyPoints = storyPoints;
		this.storyDescription = storyDescription;
		this.testCases = new ArrayList<TestCase>();
	}
	
	public UserStory(UserStoryId userStoryId, String storyStatus, int storyPoints, String storyDescription) {
		this.userStoryId = userStoryId;
		this.storyStatus = StoryStatus.valueOf(storyStatus);
		this.storyPoints = storyPoints;
		this.storyDescription = storyDescription;
		this.testCases = new ArrayList<TestCase>();
	}
	
	public UserStoryId getUserStoryId() {
		return userStoryId;
	}
	
	public void changeStoryStatus(StoryStatus storyStatus) {
		this.storyStatus = storyStatus;
	}
	
	public StoryStatus getStoryStatus() {
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
	
	public void addTestCase(TestCase testCase) {
		this.testCases.add(testCase);
	}
	
	public Collection<TestCase> getAllTestCases() {
		return testCases;
	}
	
	public void setTestCases(Collection<TestCase> testCases) {
		this.testCases = testCases;
	}
	
	public int hashCode() {
		return userStoryId.getId();
	}
	
	public String toString() {
		return "------------\nUSER-STORY\nID: '" + this.userStoryId.getId() + "',\nStory-Status: '" + this.storyStatus.toString() + "',\nStory-Punkte: '" + this.storyPoints + "',\nStory-Beschreibung: '" + this.storyDescription + "'\n";
	}
}
