package com.example.testManagement.application;

import java.util.Collection;

import com.example.testManagement.domain.model.TestCase;
import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.service.ChangeStatus;

public class UserStoryService implements IUserStoryService{

	//Definition der benötigten Interfaces und DomainServices
	private IUserStoryRepo userStoryRepo;
	private ITestCaseRepo testCaseRepo;
	private ChangeStatus domainService;
	
	//Konstruktor zur Initialisierung der Interfaces und DomainServices
	public UserStoryService (IUserStoryRepo userStoryRepo, ITestCaseRepo testCaseRepo, ChangeStatus domainService) {
		this.userStoryRepo = userStoryRepo;
		this.testCaseRepo = testCaseRepo;
		this.domainService = domainService;
	}
	
	//Anzeige aller UserStorys
	@Override
	public String showAllUserStorys() {
		String allUserStorys = "";
		
		//Abruf aller UserStorys aus der Datenbank
		Collection<UserStory> userStorys = userStoryRepo.findAll();
		
		//Iteration über alle UserStorys
		for(UserStory item : userStorys) {
			//Hinzufügen der aktuellen UserStory in Stringform zur Konsolen-Ausgabe
			allUserStorys += item.toString();
			
			//Abruf aller TestCases für die aktuelle UserStory
			Collection<TestCase> testCases = testCaseRepo.findByUserStoryId(item.getUserStoryId());
			
			//Hinzufügen aller TEstCases der USerStory zur Konsolen-Ausgabe
			for(TestCase test : testCases)
				allUserStorys += test.toString();
		}		
		return allUserStorys;
	}

	//Änderung des Status
	@Override
	public boolean changeStatus(int id) {
		//Aufruf des DomainServices zur Status-Änderung
		return domainService.changeStatus(id);
	}
}
