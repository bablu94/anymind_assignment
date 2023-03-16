package com.anymindassignment.events;

import java.time.LocalDateTime;

import com.anymindassignment.model.WalletRecord;

public interface WalletEvent {
    String getAggregateId();
    LocalDateTime getDateTime();
    String getEventType();
    WalletRecord getWalletRecord();
}

