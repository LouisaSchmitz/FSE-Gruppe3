package com.example.testManagement.domain.model;

public enum StoryStatus {
	IN_PROGRESS("in progress"),
	READY_FOR_TEST("ready for test");
	
	private String statusName;
	
	private StoryStatus(String status) {
		this.statusName = status;
	}
	
	@Override
	public String toString() {
		return statusName;
	}
}
