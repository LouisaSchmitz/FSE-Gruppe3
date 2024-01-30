package com.example.testManagement.domain.model;

import java.util.ArrayList;
import java.util.Collection;

public class UserStory {
	
	//Definition der Variablen
	UserStoryId userStoryId;
	StoryStatus storyStatus;
	int storyPoints;
	String storyDescription;
	Collection<TestCase> testCases;
	
	//Konstruktor zur Neu-Erstellung (Status wird sofort auf "In-Progress" gesetzt)
	public UserStory(UserStoryId userStoryId, int storyPoints, String storyDescription) {
		this.userStoryId = userStoryId;
		this.storyStatus = StoryStatus.IN_PROGRESS;
		this.storyPoints = storyPoints;
		this.storyDescription = storyDescription;
		this.testCases = new ArrayList<TestCase>();
	}
	
	//Konstruktor um UserStory mit Infos aus Datenbank zu befüllen
	public UserStory(UserStoryId userStoryId, String storyStatus, int storyPoints, String storyDescription) {
		this.userStoryId = userStoryId;
		this.storyStatus = StoryStatus.valueOf(storyStatus);
		this.storyPoints = storyPoints;
		this.storyDescription = storyDescription;
		this.testCases = new ArrayList<TestCase>();
	}
	
	public UserStory() {
	}

	//Getter für UserStory-ID
	public UserStoryId getUserStoryId() {
		return userStoryId;
	}
	
	//Änderung des Story-Status
	public void changeStoryStatus(StoryStatus storyStatus) {
		this.storyStatus = storyStatus;
	}
	
	//Getter für den Story-Status
	public StoryStatus getStoryStatus() {
		return storyStatus;
	}
	
	//Änderung der Story-Points
	public void rateUserStory (int storyPoints) {
		this.storyPoints = storyPoints;
	}
	
	//Getter der Story-Points
	public int getStoryPoints() {
		return storyPoints;
	}
	
	//Änderung der Story-Beschreibung
	public void describeUserStory(String storyDescription) {
		this.storyDescription = storyDescription;
	}
	
	//Getter der Story-Beschreibung
	public String getStoryDescription() {
		return storyDescription;
	}
	
	//Hinzufügen der zugehörigen Test-Cases in eine Liste
	public void addTestCase(TestCase testCase) {
		this.testCases.add(testCase);
	}
	
	//Eine Liste mit allen zugehörigen Test-Cases
	public Collection<TestCase> getAllTestCases() {
		return testCases;
	}
	
	//Eine fertige Liste mit Test-Cases wird übernommen
	public void setTestCases(Collection<TestCase> testCases) {
		this.testCases = testCases;
	}
	
	//Getter für den Hash-Code
	public int hashCode() {
		return userStoryId.getId();
	}
	
	//Konsolen-Ausgabe der User-Story mit allen Attributen als String
	public String toString() {
		return "------------\nUSER-STORY\nID: '" + this.userStoryId.getId() + "',\nStory-Status: '" + this.storyStatus.toString() + "',\nStory-Punkte: '" + this.storyPoints + "',\nStory-Beschreibung: '" + this.storyDescription + "'\n";
	}
}
