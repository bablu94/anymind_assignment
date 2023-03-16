package com.anymindassignment.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.model.WalletRecord;

public class WalletBalanceUpdatedEvent implements WalletEvent {

	private final String aggregateId;
	private final LocalDateTime dateTime;
	private final BigDecimal balance;
	private final String eventType;
	private final WalletRecord walletRecord;

	public WalletBalanceUpdatedEvent(WalletBalance walletBalance) {
		this.aggregateId = walletBalance.getWalletRecord().getId().toString();
		this.dateTime = walletBalance.getDateTime();
		this.balance = walletBalance.getBalance();
		this.eventType = walletBalance.getEventType().toString();
		this.walletRecord = walletBalance.getWalletRecord();
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getEventType() {
		return eventType;
	}

	public WalletRecord getWalletRecord() {
		return walletRecord;
	}
}
