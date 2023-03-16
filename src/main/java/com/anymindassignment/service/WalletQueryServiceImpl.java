package com.anymindassignment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anymindassignment.dto.WalletBalanceDTO;
import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.repository.WalletBalanceRepository;

@Service
public class WalletQueryServiceImpl implements WalletQueryService {

	private final WalletBalanceRepository walletBalanceRepository;

	public WalletQueryServiceImpl(WalletBalanceRepository walletBalanceRepository) {
		this.walletBalanceRepository = walletBalanceRepository;
	}

	@Override
	public WalletBalanceDTO getWalletBalance(String walletId) {
		Optional<WalletBalance> optionalBalance = walletBalanceRepository.findFirstByIdOrderByDateTimeDesc(walletId);
		return optionalBalance.map(balance -> new WalletBalanceDTO(balance.getDateTime(), balance.getBalance()))
				.orElse(new WalletBalanceDTO(LocalDateTime.now(), BigDecimal.ZERO));
	}
}
