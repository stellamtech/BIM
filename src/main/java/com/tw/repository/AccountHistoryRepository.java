package com.tw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tw.entity.AccountHistory;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long>, JpaSpecificationExecutor<AccountHistory> {

	AccountHistory findBySaleId(Long id);

	Optional<AccountHistory> findOneBySaleId(Long id);

}
