package com.tw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tw.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {

	Optional<Sale> findBySaleno(String join);

	List<Sale> findBycustomerIdAndPaidflagAndStatus(Long id, boolean b, String authorized);

}
