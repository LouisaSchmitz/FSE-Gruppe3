package com.example.testManagement.adapter.messaging;

import com.example.testManagement.domain.service.DomainEvent;

public interface IMessageQueue {
	public boolean send(DomainEvent domainEvent);
}
