package com.tw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tw.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock>{

	Stock findByItemId(Long id);

	Optional<Stock> findOneByItemId(Long itemid);

	
	
}
