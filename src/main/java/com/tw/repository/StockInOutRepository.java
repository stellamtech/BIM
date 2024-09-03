package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.tw.entity.StockInOut;

public interface StockInOutRepository extends JpaRepository<StockInOut, Long>, JpaSpecificationExecutor<StockInOut> {

	@Query(value = "SELECT sum(stock_in) FROM stockinout where item_id = ?1 and deleted=0", nativeQuery = true)
	double getSumofStockInByItemId(Long id);

	@Query(value = "SELECT sum(stock_out) FROM stockinout where item_id = ?1 and deleted=0", nativeQuery = true)
	double getSumofStockOutByItemId(Long id);

}
