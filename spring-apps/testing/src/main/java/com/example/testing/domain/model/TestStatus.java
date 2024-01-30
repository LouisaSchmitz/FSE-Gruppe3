package com.example.testing.domain.model;

public enum TestStatus {
	WAITING("waiting"),
	STARTED("started"),
	NOT_APPROVED("not approved"),
	APPROVED("approved");
	
	private String statusName;
	
	//Konstruktor für den Status
	private TestStatus(String status) {
		this.statusName = status;
	}
	
	//Rückgabe des Status als String
	@Override
	public String toString() {
		return statusName;
	}
}