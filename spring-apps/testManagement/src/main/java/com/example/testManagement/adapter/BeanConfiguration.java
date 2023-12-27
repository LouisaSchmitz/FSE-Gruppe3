package com.example.testManagement.adapter;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

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
	 IMessageQueue messageQueue(KafkaTemplate<String, String> kafkaTemplate) {
		 return new QueueAdapter(kafkaTemplate);
	 }
	
	 //Kafka Messaging
	 /*@Bean
	 public KafkaAdmin kafkaAdmin() {
	     Map<String, Object> configs = new HashMap<>();
	     configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
	     return new KafkaAdmin(configs);
	 }
	   
	 @Bean
	 public NewTopic topic() {
	     return new NewTopic("statusChanged", 1, (short) 1);
	 }*/
	 
	 @Bean
	 public ProducerFactory<String, String> producerFactory() {
	     Map<String, Object> configProps = new HashMap<>();
	     
	     configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
	     configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	     configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	     
	     return new DefaultKafkaProducerFactory<>(configProps);
	 }

	 @Bean
	 public KafkaTemplate<String, String> kafkaTemplate() {
	    return new KafkaTemplate<>(producerFactory());
	 }
}
