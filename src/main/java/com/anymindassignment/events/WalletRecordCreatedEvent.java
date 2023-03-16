package com.anymindassignment.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.swing.event.DocumentEvent.EventType;

import com.anymindassignment.model.WalletRecord;

public class WalletRecordCreatedEvent implements WalletEvent {

	private final String aggregateId;
	private final LocalDateTime dateTime;
	private final BigDecimal amount;
	private final String currency;
	private final EventType eventType;
	private final WalletRecord walletRecord;

	public WalletRecordCreatedEvent(String aggregateId, LocalDateTime dateTime, BigDecimal amount, String currency,
			EventType eventType, WalletRecord walletRecord) {
		this.aggregateId = aggregateId;
		this.dateTime = dateTime;
		this.amount = amount;
		this.currency = currency;
		this.eventType = eventType;
		this.walletRecord = walletRecord;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public String getEventType() {
		return eventType.toString();
	}

	public WalletRecord getWalletRecord() {
		return walletRecord;
	}
}
