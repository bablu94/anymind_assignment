package com.anymindassignment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wallet_records")
public class WalletRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date_time")
	private LocalDateTime dateTime;

	private BigDecimal amount;

	private String currency;

	public WalletRecord(Long id, LocalDateTime dateTime, BigDecimal amount, String currency) {
		this.id = id;
		this.dateTime = dateTime;
		this.amount = amount;
		this.currency = currency;
	}

	public WalletRecord(String currency) {
		this.currency = currency;
	}
}
