package com.tw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tw.entity.StockHistory;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long>, JpaSpecificationExecutor<StockHistory> {

//	StockHistory findByGrnitemId(Long forengId);
//
//	Optional<StockHistory> findOneByGrnitemId(Long id);

	StockHistory findBySaleitemId(Long id);

	Optional<StockHistory> findOneBySaleitemId(Long id);

//	Optional<StockHistory> findOneByCredititemId(Long id);
//
//	StockHistory findByCredititemId(Long id);
//
//	Optional<StockHistory> findOneByDebititemId(Long id);
//
//	StockHistory findByDebititemId(Long id);
//
//	Optional<StockHistory> findOneByOpsId(Long id);
//
//	StockHistory findByOpsId(Long id);
//
	Optional<StockHistory> findOneBySioId(Long id);

	StockHistory findBySioId(Long id);
//
//	StockHistory findByDeveryItemId(Long id);

}
