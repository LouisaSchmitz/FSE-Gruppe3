package com.example.testManagement.adapter.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.model.UserStoryId;

//Namens-Definition der Datenbank-Tabelle
@Table(name="USER_STORY")
public class UserStoryEntity {

	//Festlegung der einzelnen Attribute und den zugehörigen Spalten-Namen in der Datenbank-Tabelle
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
	
	//Konstruktor um UserStory mit Infos aus Datenbank zu befüllen
	public UserStoryEntity(int id, String storyStatus, int storyPoints, String storyDescription) {
		super();
		this.id = id;
		this.storyStatus = storyStatus;
		this.storyPoints = storyPoints;
		this.storyDescription = storyDescription;
	}
	
	//Konstruktor um Entity-Objekt mit übergebener UserStory zu erstellen
	public UserStoryEntity(UserStory userStory) {
		this.id = userStory.getUserStoryId().getId();
		this.storyStatus = userStory.getStoryStatus().name();
		this.storyPoints = userStory.getStoryPoints();
		this.storyDescription = userStory.getStoryDescription();
	}
	
	//Getter für Entity-ID
	public int getId() {
		return id;
	}
	
	//Änderung der Entity-ID
	public void setId(int id) {
		this.id = id;
	}
	
	//Änderung des Entity-Status
	public void changeStoryStatus(String storyStatus) {
		this.storyStatus = storyStatus;
	}
	
	//Getter für Entity-Status
	public String getStoryStatus() {
		return storyStatus;
	}
	
	//Änderung der Entity-Points
	public void rateUserStory (int storyPoints) {
		this.storyPoints = storyPoints;
	}
	
	//Getter für die Entity-Points
	public int getStoryPoints() {
		return storyPoints;
	}
	
	//Änderung der Entity-Beschreibung
	public void describeUserStory(String storyDescription) {
		this.storyDescription = storyDescription;
	}
	
	//Getter für Entity-Beschreibung
	public String getStoryDescription() {
		return storyDescription;
	}
	
	//UserStoryEntity in UserStory-Objekt umwandeln
	public UserStory toDomain() {
		return new UserStory(new UserStoryId(id), storyStatus, storyPoints, storyDescription);
	}
}
