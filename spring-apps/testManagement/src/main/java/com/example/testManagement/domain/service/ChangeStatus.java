package com.example.testManagement.domain.service;

import java.util.Collection;

import com.example.testManagement.adapter.messaging.IMessageQueue;
import com.example.testManagement.application.ITestCaseRepo;
import com.example.testManagement.application.IUserStoryRepo;
import com.example.testManagement.domain.model.StoryStatus;
import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.model.UserStoryId;

public class ChangeStatus {

	//Definition der benötigten Interfaces
	IUserStoryRepo userStoryRepo;
	ITestCaseRepo testCaseRepo;
	IMessageQueue messageQueue;
	
	//Konstruktor zur Initialisierung der Interfaces
	public ChangeStatus (IUserStoryRepo userStoryRepo, ITestCaseRepo testCaseRepo, IMessageQueue messageQueue) {
		this.userStoryRepo = userStoryRepo;
		this.testCaseRepo = testCaseRepo;
		this.messageQueue = messageQueue;
	}
	
	public boolean changeStatus(int id) {
		
		boolean findAllTestCases = true;
		
		//Überprüfung ob es zu der übergeben UserSTory-ID passende TestCases gibt
		for(TestCase testCase : testCaseRepo.findByUserStoryId(new UserStoryId(id))) {		
			if (testCase == null) {
 	 			findAllTestCases = false;
 	 		}
		}
		
		//Wenn es TestCases in der Datenbank gibt...
		if(findAllTestCases) {
			//... wird der Status der UserStory auf "ready-for-test" gesetzt...
			UserStory userStory = userStoryRepo.findById(new UserStoryId(id));
			userStory.changeStoryStatus(StoryStatus.READY_FOR_TEST);
			userStoryRepo.save(userStory);
			
			Collection<TestCase> testCases = testCaseRepo.findByUserStoryId(new UserStoryId(id));
			
			//... und der Status aller passenden TestCases auf "ready-for-test" gesetzt
			for(TestCase testCase : testCases) {
				testCase.changeTestStatus(StoryStatus.READY_FOR_TEST);
				testCaseRepo.save(testCase, id);
				userStory.addTestCase(testCase);
			}
			
			//DomainEvent für die Statusänderung wird erstellt und losgeschickt
			DomainEvent statusChanged = new DomainEvent("statusChanged", userStory);
			messageQueue.send(statusChanged);
			
			return true;
		}
		
		return false;
	}
}
