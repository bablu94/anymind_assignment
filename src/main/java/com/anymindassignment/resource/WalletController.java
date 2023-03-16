package com.anymindassignment.resource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anymindassignment.dto.WalletBalanceDTO;
import com.anymindassignment.dto.WalletRecordDTO;
import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.model.WalletRecord;
import com.anymindassignment.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	private final WalletService walletService;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}

	@PostMapping("/records")
	public ResponseEntity<?> saveRecord(@RequestBody WalletRecordDTO recordDTO) {
		WalletRecord record = WalletRecord.builder().dateTime(recordDTO.getDateTime()).amount(recordDTO.getAmount())
				.currency(recordDTO.getCurrency()).build();
		walletService.saveRecord(record);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/balance")
	public ResponseEntity<List<WalletBalanceDTO>> getWalletBalanceHistory(
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {

		List<WalletBalance> walletBalances = walletService.getWalletBalanceHistory(startDateTime, endDateTime);

		List<WalletBalanceDTO> walletBalanceDTOs = walletBalances.stream()
				.map(walletBalance -> new WalletBalanceDTO(walletBalance.getDateTime(), walletBalance.getBalance()))
				.collect(Collectors.toList());

		return ResponseEntity.ok(walletBalanceDTOs);
	}

}
