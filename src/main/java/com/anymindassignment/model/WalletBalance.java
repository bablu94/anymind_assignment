package com.anymindassignment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.anymindassignment.constants.WalletEventType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet_balances")
public class WalletBalance {
    
	public WalletBalance(WalletRecord walletRecord, LocalDateTime dateTime, BigDecimal balance) {
		this.walletRecord = walletRecord;
	    this.dateTime = dateTime;
	    this.balance = balance;
	}



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private WalletRecord walletRecord;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private WalletEventType eventType;

}

