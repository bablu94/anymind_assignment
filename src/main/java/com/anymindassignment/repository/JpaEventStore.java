package com.anymindassignment.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.anymindassignment.events.EventStore;
import com.anymindassignment.events.WalletEvent;
import com.anymindassignment.model.WalletEventEntity;

@Repository
public class JpaEventStore implements EventStore {

    private final WalletEventRepository walletEventRepository;

    public JpaEventStore(WalletEventRepository walletEventRepository) {
        this.walletEventRepository = walletEventRepository;
    }

    @Override
    public void save(WalletEvent event) {
        WalletEventEntity eventEntity = new WalletEventEntity(event);
        walletEventRepository.save(eventEntity);
    }

    @Override
    public List<WalletEvent> getEventsByAggregateId(String aggregateId) {
        List<WalletEventEntity> eventEntities = walletEventRepository.findByAggregateIdOrderByDateTimeAsc(aggregateId);
        return eventEntities.stream().map(WalletEventEntity::toEvent).collect(Collectors.toList());
    }
}

