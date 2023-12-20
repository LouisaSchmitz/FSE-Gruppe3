package com.example.testManagement.adapter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.testManagement.adapter.database.DBTestCaseRepo;
import com.example.testManagement.adapter.database.DBUserStoryRepo;
import com.example.testManagement.adapter.database.JDBCTestCaseEntityRepo;
import com.example.testManagement.adapter.database.JDBCUserStoryEntityRepo;
import com.example.testManagement.adapter.messaging.IMessageQueue;
import com.example.testManagement.adapter.messaging.QueueAdapter;
import com.example.testManagement.application.ITestCaseRepo;
import com.example.testManagement.application.IUserStoryRepo;
import com.example.testManagement.application.IUserStoryService;
import com.example.testManagement.application.UserStoryService;
import com.example.testManagement.domain.service.ChangeStatus;

@Configuration
public class BeanConfiguration {

	 @Bean
	 IUserStoryService userStoryService(IUserStoryRepo userStoryRepo, ITestCaseRepo testCaseRepo, ChangeStatus domainService) {
		return new UserStoryService(userStoryRepo, testCaseRepo, domainService);
	 }
	 
	 @Bean
	 ChangeStatus domainService(IUserStoryRepo userStoryRepo, ITestCaseRepo testCaseRepo, IMessageQueue messageQueue) {
		 return new ChangeStatus(userStoryRepo, testCaseRepo, messageQueue);
	 }
	 
	 @Bean
	 @Primary
	 IUserStoryRepo userStoryRepo(JDBCUserStoryEntityRepo jdbcUserStoryEntityRepo) {
		 return new DBUserStoryRepo(jdbcUserStoryEntityRepo);
	 }
	 
	 @Bean
	 @Primary
	 ITestCaseRepo testCaseRepo(JDBCTestCaseEntityRepo jdbcTestCaseEntityRepo) {
		 return new DBTestCaseRepo(jdbcTestCaseEntityRepo);
	 }
	 
	 @Bean
	 IMessageQueue messageQueue(AmqpTemplate amqpTemplate) {
		 return new QueueAdapter(amqpTemplate);
	 }
	 
	 @Bean
	 public Queue myQueue() {
		 return new Queue(QueueAdapter.QUEUE_NAME, QueueAdapter.NON_DURABLE);    
	 }
}
