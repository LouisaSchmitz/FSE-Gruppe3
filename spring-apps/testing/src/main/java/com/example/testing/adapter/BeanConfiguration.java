package com.example.testing.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.testing.adapter.database.DBTestCaseRepo;
import com.example.testing.adapter.database.JDBCTestCaseEntityRepo;
import com.example.testing.adapter.messaging.EventListener;
import com.example.testing.application.ITestCaseRepo;
import com.example.testing.application.ITestCaseService;
import com.example.testing.application.TestCaseService;

@Configuration
public class BeanConfiguration {

	 @Bean
	 ITestCaseService testCaseService(ITestCaseRepo testCaseRepo) {
		return new TestCaseService(testCaseRepo);
	 }
	
	 @Bean
	 @Primary
	 ITestCaseRepo testCaseRepo(JDBCTestCaseEntityRepo jdbcTestCaseEntityRepo) {
		 return new DBTestCaseRepo(jdbcTestCaseEntityRepo);
	 }
	 
	 @Bean
	 EventListener eventListener(ITestCaseService testCaseService) {
			return new EventListener(testCaseService);
	 }
}