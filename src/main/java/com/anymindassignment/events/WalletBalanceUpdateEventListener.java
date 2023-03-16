package com.anymindassignment.events;

import java.math.BigDecimal;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.repository.WalletBalanceRepository;

@Component
public class WalletBalanceUpdateEventListener {

    private final WalletBalanceRepository walletBalanceRepository;

    public WalletBalanceUpdateEventListener(WalletBalanceRepository walletBalanceRepository) {
        this.walletBalanceRepository = walletBalanceRepository;
    }

    @EventListener
    public void onWalletBalanceUpdatedEvent(WalletBalanceUpdatedEvent event) {
    	WalletBalance walletBalance = walletBalanceRepository.findByWalletRecordAndDateTime(event.getWalletRecord(), event.getDateTime())
    	        .orElse(new WalletBalance(event.getWalletRecord(), event.getDateTime(), BigDecimal.ZERO));
        walletBalance.setBalance(event.getBalance());
        walletBalanceRepository.save(walletBalance);
    }
}

