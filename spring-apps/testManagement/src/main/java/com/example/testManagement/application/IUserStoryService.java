package com.example.testManagement.application;

public interface IUserStoryService {
	public String showAllUserStorys();
	public boolean changeStatus(int id);
	public String showStatus(int id);
}
