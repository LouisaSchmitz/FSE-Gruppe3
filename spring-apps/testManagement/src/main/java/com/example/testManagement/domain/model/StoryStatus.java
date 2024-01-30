package com.example.testManagement.domain.model;

public enum StoryStatus {
	IN_PROGRESS("in progress"),
	READY_FOR_TEST("ready for test");
	
	private String statusName;
	
	//Konstruktor für den Status
	private StoryStatus(String status) {
		this.statusName = status;
	}
	
	//Rückgabe des Status als String
	@Override
	public String toString() {
		return statusName;
	}
}
