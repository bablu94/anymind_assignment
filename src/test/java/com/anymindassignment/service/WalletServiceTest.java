package com.anymindassignment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import com.anymindassignment.events.WalletBalanceUpdatedEvent;
import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.model.WalletRecord;
import com.anymindassignment.repository.WalletBalanceRepository;
import com.anymindassignment.repository.WalletRepository;

@RunWith(MockitoJUnitRunner.class)
public class WalletServiceTest {

	@Mock
	private WalletRepository walletRepository;

	@Mock
	private WalletBalanceRepository walletBalanceRepository;

	@Mock
	private ApplicationEventPublisher eventPublisher;

	@InjectMocks
	private WalletServiceImpl walletService;

	@Test
	public void testSaveRecord() {
		
		WalletRecord record = new WalletRecord("test-wallet");

		walletService.saveRecord(record);

		verify(walletRepository, times(1)).save(record);
		verify(walletBalanceRepository, times(1)).findByWalletRecordAndDateTime(eq(record), any(LocalDateTime.class));
		verify(walletBalanceRepository, times(1)).save(any(WalletBalance.class));
		verify(eventPublisher, times(1)).publishEvent(any(WalletBalanceUpdatedEvent.class));
	}

	@Test
	public void testGetWalletBalanceHistory() {

		LocalDateTime startDateTime = LocalDateTime.of(2023, 3, 1, 0, 0, 0);
		LocalDateTime endDateTime = LocalDateTime.of(2023, 3, 15, 0, 0, 0);

		WalletRecord walletRecord = new WalletRecord();

		List<WalletBalance> expectedWalletBalances = Arrays.asList(
				new WalletBalance(walletRecord, startDateTime, BigDecimal.TEN),
				new WalletBalance(walletRecord, endDateTime, BigDecimal.valueOf(15)));
		when(walletBalanceRepository.findByDateTimeBetweenOrderByDateTimeAsc(startDateTime, endDateTime))
				.thenReturn(expectedWalletBalances);

		List<WalletBalance> actualWalletBalances = walletService.getWalletBalanceHistory(startDateTime, endDateTime);

		assertEquals(expectedWalletBalances, actualWalletBalances);
		verify(walletBalanceRepository, times(1)).findByDateTimeBetweenOrderByDateTimeAsc(startDateTime, endDateTime);
	}

}
