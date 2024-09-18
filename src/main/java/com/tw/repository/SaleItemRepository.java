package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.tw.entity.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long>, JpaSpecificationExecutor<SaleItem> {

	@Query(value = "SELECT sum(qty) FROM sale_invoice_item where item_id = ?1 and deleted=0", nativeQuery = true)
	double getSumByItemId(Long id);

}
