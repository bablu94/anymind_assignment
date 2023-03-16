package com.anymindassignment.service;

import com.anymindassignment.dto.WalletBalanceDTO;

public interface WalletQueryService {

    WalletBalanceDTO getWalletBalance(String walletId);
}
