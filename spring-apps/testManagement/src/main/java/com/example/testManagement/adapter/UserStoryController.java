package com.example.testManagement.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testManagement.application.IUserStoryService;

@RestController
@RequestMapping("/testManager")
public class UserStoryController {

	@Autowired
	private IUserStoryService userStoryService;
	
	public UserStoryController (IUserStoryService userStoryService) {
		this.userStoryService = userStoryService;
	}
	
	/*
	 * curl -X GET http://localhost:8080/testManager/showAllUserStorys
	 */		
	@GetMapping("/showAllUserStorys")
	public String showAllUserStorys() {
		return userStoryService.showAllUserStorys();
	}
	
	/*
	 * curl -X GET http://localhost:8080/testManager/userStoryStatus/1
	 */		
	@GetMapping("/userStoryStatus/{id}")
	public String showStatusUserStory (@PathVariable ("id") int id) {
		return userStoryService.showStatus(id);
	}
	
	/*
	 * curl -X GET http://localhost:8080/testManager/changeUserStoryStatusToReady/1
	 */	
	@GetMapping("/changeUserStoryStatusToReady/{id}")
	public boolean changeStatusUserStory (@PathVariable ("id") int id) {
		return userStoryService.changeStatus(id);
	}
}
