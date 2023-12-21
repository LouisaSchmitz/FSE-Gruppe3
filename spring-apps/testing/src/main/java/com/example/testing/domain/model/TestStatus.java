package com.example.testing.domain.model;

public enum TestStatus {
	WAITING("waiting"),
	STARTED("started"),
	NOT_APPROVED("not approved"),
	APPROVED("approved");
	
	private String statusName;
	
	private TestStatus(String status) {
		this.statusName = status;
	}
	
	@Override
	public String toString() {
		return statusName;
	}
}