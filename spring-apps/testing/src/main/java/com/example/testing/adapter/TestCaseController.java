package com.example.testing.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testing.application.ITestCaseService;

@RestController
@RequestMapping("/testing")
public class TestCaseController {

	@Autowired
	private ITestCaseService testCaseService;
	
	public TestCaseController (ITestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}
	
	/*
	 * curl -X GET http://localhost:8090/testing/showAllTestCases
	 */	
	@GetMapping("/showAllTestCases")
	public String showAllTestCases() {
		return testCaseService.showAllTestCases();
	}
	
	/*
	 * curl -X GET http://localhost:8090/testing/approved/1
	 */	
	@GetMapping("/approved/{id}")
	public boolean changeTestStatusToApproved (@PathVariable ("id") int id) {
		return testCaseService.changeStatusToApproved(id);
	}
	
	/*
	 * curl -X GET http://localhost:8090/testing/notApproved/1
	 */	
	@GetMapping("/notApproved/{id}")
	public boolean changeTestStatusToNotApproved (@PathVariable ("id") int id) {
		return testCaseService.changeStatusToNotApproved(id);
	}

}