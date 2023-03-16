package com.anymindassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anymindassignment.model.WalletRecord;

@Repository
public interface WalletRepository extends JpaRepository<WalletRecord, Long> {

}
