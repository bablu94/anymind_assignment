package com.anymindassignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anymindassignment.model.WalletEventEntity;

@Repository
public interface WalletEventRepository extends JpaRepository<WalletEventEntity, Long> {

	List<WalletEventEntity> findByAggregateIdOrderByDateTimeAsc(String aggregateId);

}
