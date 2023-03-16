package com.anymindassignment.events;

import java.util.List;

public interface EventStore {
    void save(WalletEvent event);
    List<WalletEvent> getEventsByAggregateId(String aggregateId);
}
