package com.anymindassignment.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anymindassignment.dto.WalletBalanceDTO;
import com.anymindassignment.exception.WalletRecordNotFoundException;
import com.anymindassignment.service.WalletQueryService;

@RestController
@RequestMapping("/wallet")
public class WalletQueryController {

	private final WalletQueryService walletQueryService;

	public WalletQueryController(WalletQueryService walletQueryService) {
		this.walletQueryService = walletQueryService;
	}

	@GetMapping("/{id}/balance")
	public ResponseEntity<WalletBalanceDTO> getWalletBalance(@PathVariable String id) {
		WalletBalanceDTO walletRecord = walletQueryService.getWalletBalance(id);

		if (walletRecord == null) {
			throw new WalletRecordNotFoundException("Wallet record not found with id " + id);
		}
		return ResponseEntity.ok(walletRecord);
	}
}
