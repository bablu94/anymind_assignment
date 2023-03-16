package com.anymindassignment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.anymindassignment.events.WalletBalanceUpdatedEvent;
import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.model.WalletRecord;
import com.anymindassignment.repository.WalletBalanceRepository;
import com.anymindassignment.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

	private final WalletRepository walletRepository;
	private final WalletBalanceRepository walletBalanceRepository;
	private final ApplicationEventPublisher eventPublisher;

	public WalletServiceImpl(WalletRepository walletRepository, WalletBalanceRepository walletBalanceRepository,
			ApplicationEventPublisher eventPublisher) {
		this.walletRepository = walletRepository;
		this.walletBalanceRepository = walletBalanceRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void saveRecord(WalletRecord record) {
		walletRepository.save(record);
		WalletBalanceUpdatedEvent event = updateWalletBalance(record);
		eventPublisher.publishEvent(event);
	}

	@Override
	public List<WalletBalance> getWalletBalanceHistory(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return walletBalanceRepository.findByDateTimeBetweenOrderByDateTimeAsc(startDateTime, endDateTime);
	}

	private WalletBalanceUpdatedEvent updateWalletBalance(WalletRecord record) {
		LocalDateTime hourStart = record.getDateTime().truncatedTo(ChronoUnit.HOURS);
		WalletBalance walletBalance = walletBalanceRepository.findByWalletRecordAndDateTime(record, hourStart)
				.orElse(new WalletBalance(record, hourStart, BigDecimal.ZERO));
		BigDecimal newBalance = walletBalance.getBalance().add(record.getAmount());
		walletBalance.setBalance(newBalance);
		walletBalanceRepository.save(walletBalance);
		return new WalletBalanceUpdatedEvent(walletBalance);
	}

}
