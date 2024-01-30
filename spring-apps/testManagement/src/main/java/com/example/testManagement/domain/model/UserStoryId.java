package com.example.testManagement.domain.model;

public class UserStoryId {
	int id;
	
	public UserStoryId(int id) {
		super();
		this.id = id;
	}

	//Getter der Story-ID
	public int getId() {
		return id;
	}
	
	//Überprüfung des übergebenen Objekts auf Gleichheit
	public boolean equals (Object o) {
		UserStoryId a = (UserStoryId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}
