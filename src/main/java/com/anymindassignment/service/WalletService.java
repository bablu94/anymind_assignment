package com.anymindassignment.service;

import java.time.LocalDateTime;
import java.util.List;

import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.model.WalletRecord;

public interface WalletService {
	
	void saveRecord(WalletRecord record);

    List<WalletBalance> getWalletBalanceHistory(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
