package com.example.testing.adapter;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.example.testing.adapter.database.DBTestCaseRepo;
import com.example.testing.adapter.database.JDBCTestCaseEntityRepo;
import com.example.testing.adapter.messaging.EventListener;
import com.example.testing.application.ITestCaseRepo;
import com.example.testing.application.ITestCaseService;
import com.example.testing.application.TestCaseService;

@EnableKafka
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
	 
	 //Kafka Messaging
	 
	 @Bean
	 public ConsumerFactory<String, String> consumerFactory() {
	     Map<String, Object> props = new HashMap<>();
	     
	     props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
	     props.put(ConsumerConfig.GROUP_ID_CONFIG, "status");
	     props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	     props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	     
	     return new DefaultKafkaConsumerFactory<>(props);
	 }
	 
	 @Bean
	 public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	 }
}
