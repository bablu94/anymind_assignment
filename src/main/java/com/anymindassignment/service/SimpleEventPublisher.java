package com.anymindassignment.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.anymindassignment.events.EventPublisher;
import com.anymindassignment.events.WalletEvent;

@Service
public class SimpleEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SimpleEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(WalletEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
