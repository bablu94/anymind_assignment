package com.anymindassignment.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.anymindassignment.events.WalletEvent;
import com.google.gson.Gson;

@Entity
@Table(name = "wallet_events")
public class WalletEventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "aggregate_id")
	private String aggregateId;

	@Column(name = "event_type")
	private String eventType;

	@Column(name = "event_data")
	private String eventData;

	@Column(name = "datetime")
	private LocalDateTime dateTime;

	public WalletEventEntity() {
	}

	public WalletEventEntity(WalletEvent event) {
		this.aggregateId = event.getAggregateId();
		this.eventType = event.getEventType();
		this.eventData = new Gson().toJson(event);
		this.dateTime = event.getDateTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public WalletEvent toEvent() {
		Class<?> eventClass;
		try {
			eventClass = Class.forName(this.eventType);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find class for event type: " + this.eventType);
		}

		Gson gson = new Gson();
		return (WalletEvent) gson.fromJson(this.eventData, eventClass);
	}

}
