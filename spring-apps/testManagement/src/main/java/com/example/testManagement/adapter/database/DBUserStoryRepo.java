package com.example.testManagement.adapter.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.testManagement.application.IUserStoryRepo;
import com.example.testManagement.domain.model.UserStory;
import com.example.testManagement.domain.model.UserStoryId;

@Component
public class DBUserStoryRepo implements IUserStoryRepo{
	
	private final JDBCUserStoryEntityRepo jdbcUserStoryEntityRepo;
	
	//Konstruktor für Injektion des JDBCRepos über Autowiring
	@Autowired
	public DBUserStoryRepo(JDBCUserStoryEntityRepo jdbcUserStoryEntityRepo) {
		this.jdbcUserStoryEntityRepo = jdbcUserStoryEntityRepo;
	}
	
	//Abruf aller UserStorys aus der Datenbank
	@Override
	public Collection<UserStory> findAll() {
		Collection<UserStory> userStories = new ArrayList<UserStory>();
		
		//Abruf aller UserStory-Entitäten aus der Datenbank
		Iterable<UserStoryEntity> userStorysEntity = jdbcUserStoryEntityRepo.findAll();
		
		//Iteration über alle Entitys und Umwandlung in UserStory-Objekt
		for (UserStoryEntity item : userStorysEntity) {
			userStories.add(item.toDomain());
		}
		
		return userStories;
	}

	//Abruf einer UserStory anhand ihrer ID
	@Override
	public UserStory findById(UserStoryId userStoryId) {
		//Versuch die Entity anhand der ID in der Datenbank zu finden
		Optional<UserStoryEntity> userStoryEntity = jdbcUserStoryEntityRepo.findById(userStoryId.getId());
		
		//Überprüfung ob Entity vorhanden ist
        if (userStoryEntity.isPresent()) {
            return userStoryEntity.get().toDomain();
        } else {
        	//Leere Rückgabe, wenn es keine UserStory-Entität gibt
            return null;
        }
	}

	//Speicherung der UserStory in der Datenbank
	@Override
	public void save(UserStory userStory) {
		//Umwandlung der UserStory in eine Entity, um diese abspeichern zu können
		jdbcUserStoryEntityRepo.save(new UserStoryEntity(userStory));
	}
}
