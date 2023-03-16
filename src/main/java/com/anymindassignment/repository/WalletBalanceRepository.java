package com.anymindassignment.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anymindassignment.model.WalletBalance;
import com.anymindassignment.model.WalletRecord;

@Repository
public interface WalletBalanceRepository extends JpaRepository<WalletBalance, Long> {

	List<WalletBalance> findByDateTimeBetweenOrderByDateTimeAsc(LocalDateTime startDateTime, LocalDateTime endDateTime);

	Optional<WalletBalance> findFirstByIdOrderByDateTimeDesc(String walletId);

	Optional<WalletBalance> findByWalletRecordAndDateTime(WalletRecord record, LocalDateTime hourStart);

}
